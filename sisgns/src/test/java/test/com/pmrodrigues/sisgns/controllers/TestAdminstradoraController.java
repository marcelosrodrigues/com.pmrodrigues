package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.boletos.models.Cedente;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.endereco.repositories.LogradouroRepository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.AdminstradoraController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 24/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestAdminstradoraController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AdministradoraRepository administradoraRepository;

    @Autowired
    private LogradouroRepository logradouroRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setup() {

        final Cedente cedente = jdbcTemplate.query("select * from cedente where numeroDocumento = '04.769.697/0001-10'", new ResultSetExtractor<Cedente>() {
            @Override
            public Cedente extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                final Cedente cedente = Cedente.novoCedente(rs.getString("nome"), rs.getString("numeroDocumento"));
                cedente.setId(rs.getLong("id"));
                final Endereco endereco = new Endereco();
                endereco.setId(rs.getLong("endereco_id"));
                cedente.setEndereco(endereco);

                return cedente;

            }
        });

        List<Telefone> telefones = jdbcTemplate.query("select telefone_id from telefone_administrador where administradora_id = ?", new RowMapper<Telefone>() {
            @Override
            public Telefone mapRow(ResultSet rs, int rowNum) throws SQLException {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getLong("telefone_id"));
                return telefone;
            }
        }, cedente.getId());

        for (Telefone telefone : telefones) {
            jdbcTemplate.update("delete from telefone_administrador where telefone_id = ?", telefone.getId());
            jdbcTemplate.update("delete from telefone where id = ?", telefone.getId());
        }
        jdbcTemplate.update("delete from plano where administradora_id = ?", cedente.getId());
        jdbcTemplate.update("delete from cedente where id = ?", cedente.getId());
        jdbcTemplate.update("delete from endereco where id = ?", cedente.getEndereco().getId());


    }


    @Test
    public void deveIncluirAdministradora() throws Exception {

        final MockResult result = new MockResult();
        final MockValidator validator = new MockValidator();
        final Logradouro logradouro = logradouroRepository.getByCEP("22743310");

        final AdminstradoraController controller = new AdminstradoraController(administradoraRepository, result, validator);

        final Administradora administradora = new Administradora();
        final Endereco endereco = new Endereco().comLogradouro(logradouro.getLogradouro())
                .comBairro(logradouro.getBairro())
                .comCEP(logradouro.getCep())
                .comCidade(logradouro.getBairro().getCidade())
                .comEstado(logradouro.getBairro().getCidade().getEstado())
                .comNumero("84")
                .comComplemento("apto 206");

        administradora.setEndereco(endereco);
        administradora.setNumeroDocumento("04.769.697/0001-10");
        administradora.setNome("TESTE");
        administradora.adicionar(new Telefone("21", "33926222"));

        controller.salvar(administradora);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select count(1) from cedente " +
                " inner join endereco on endereco.id = cedente.endereco_id" +
                " inner join telefone_administrador on cedente.id = telefone_administrador.administradora_id " +
                " inner join telefone on telefone.id = telefone_administrador.telefone_id " +
                "where numeroDocumento = ?", Long.class, administradora.getNumeroDocumento());

        assertEquals(1L, count);
    }

    @Test
    public void devenBuscarAdministradoraPorNome() {
        final MockResult result = new MockResult();
        final MockValidator validator = new MockValidator();
        final Logradouro logradouro = logradouroRepository.getByCEP("22743310");

        final AdminstradoraController controller = new AdminstradoraController(administradoraRepository, result, validator);

        final Administradora administradora = new Administradora();
        final Endereco endereco = new Endereco().comLogradouro(logradouro.getLogradouro())
                .comBairro(logradouro.getBairro())
                .comCEP(logradouro.getCep())
                .comCidade(logradouro.getBairro().getCidade())
                .comEstado(logradouro.getBairro().getCidade().getEstado())
                .comNumero("84")
                .comComplemento("apto 206");

        administradora.setEndereco(endereco);
        administradora.setNumeroDocumento("04.769.697/0001-10");
        administradora.setNome("TESTE");
        administradora.adicionar(new Telefone("21", "33926222"));

        controller.salvar(administradora);

        sessionFactory.getCurrentSession().flush();

        ResultList<Administradora> resultList = controller.buscarAdministradoraPeloNome("TESTE");
        long count = jdbcTemplate.queryForObject("select count(1) from cedente where nome like 'TESTE%'", Long.class);
        assertEquals(count, resultList.getQuantidadeRegistros());

        resultList = controller.buscarAdministradoraPeloNome("04.769.697/0001-10");
        count = jdbcTemplate.queryForObject("select count(1) from cedente where numeroDocumento like '04.769.697/0001-10%'", Long.class);
        assertEquals(count, resultList.getQuantidadeRegistros());
    }
}