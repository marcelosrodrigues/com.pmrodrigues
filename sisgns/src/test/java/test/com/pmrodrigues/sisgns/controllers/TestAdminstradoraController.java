package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.endereco.repositories.LogradouroRepository;
import com.pmrodrigues.sisgns.controllers.AdminstradoraController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

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
}