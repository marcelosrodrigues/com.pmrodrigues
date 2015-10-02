package test.com.pmrodrigues.sisgns.acceptables;

import com.pmrodrigues.boletos.models.Cedente;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.sisgns.models.Administradora;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.AdministradoraPlanosSaudePage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.DashboardPage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.ListAdministradoraPlanosSaudePage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 01/10/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class ITestAdministradoraPlanoSaudePage extends AbstractTransactionalJUnit4SpringContextTests {

    private DashboardPage dashboard;

    @Before
    public void before() throws Exception {

        dashboard = (DashboardPage) new LoginPage()
                .email("marcelosrodrigues@globo.com")
                .password("12345678")
                .submit();
    }


    @After
    public void after() {
        dashboard.close();
        limparBaseDeDados();
    }

    private void limparBaseDeDados() {

        if (!jdbcTemplate.queryForObject("select count(1) > 0  from cedente where numeroDocumento = '99.619.274/0001-20'", Boolean.class)) {
            return;
        }

        final Cedente cedente = jdbcTemplate.query("select * from cedente where numeroDocumento = '99.619.274/0001-20'", new ResultSetExtractor<Cedente>() {
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

        jdbcTemplate.update("commit");
    }


    @Test
    public void devePesquisarAdministradora() throws Exception {

        final ListAdministradoraPlanosSaudePage page = (ListAdministradoraPlanosSaudePage) dashboard
                .goTo(ListAdministradoraPlanosSaudePage.class);

        final List<Administradora> administradoras = jdbcTemplate.query("select * from cedente limit 0 , 20", new RowMapper<Administradora>() {
            @Override
            public Administradora mapRow(ResultSet rs, int rowNum) throws SQLException {
                return (Administradora) new Administradora()
                        .comNome(rs.getString("nome"))
                        .comNumeroDocumento(rs.getString("numeroDocumento"));
            }
        });

        final String pageSource = page.getPageSource();
        for (final Administradora administradora : administradoras) {
            assertTrue(pageSource.contains(administradora.getNome()));
            assertTrue(pageSource.contains(administradora.getNumeroDocumento()));
        }

    }

    @Test
    public void deveAdicionarAdministradora() throws Exception {

        AdministradoraPlanosSaudePage page = (AdministradoraPlanosSaudePage) dashboard.goTo(AdministradoraPlanosSaudePage.class);

        ListAdministradoraPlanosSaudePage administradoras = (ListAdministradoraPlanosSaudePage) page.nome("TESTE")
                .cnpj("99619274000120")
                .cep("22743310")
                .complemento("APTO 206")
                .numero("84")
                .adicionar("021", "33926222")
                .submit();

        assertTrue(administradoras.getPageSource().contains("99.619.274/0001-20"));
    }

    @Test
    public void deveAdicionarInformandoUmCEPQueNaoEstaCadastrado() throws Exception {

        AdministradoraPlanosSaudePage page = (AdministradoraPlanosSaudePage) dashboard.goTo(AdministradoraPlanosSaudePage.class);

        ListAdministradoraPlanosSaudePage administradoras = (ListAdministradoraPlanosSaudePage) page.nome("TESTE")
                .cnpj("99619274000120")
                .cep("22222222")
                .estado("RIO DE JANEIRO")
                .cidade("RIO DE JANEIRO")
                .bairro("PECHINCHA")
                .logradouro("Estrada campo da Areia")
                .complemento("APTO 206")
                .numero("84")
                .adicionar("021", "33926222")
                .submit();

        assertTrue(administradoras.getPageSource().contains("99.619.274/0001-20"));
    }

}
