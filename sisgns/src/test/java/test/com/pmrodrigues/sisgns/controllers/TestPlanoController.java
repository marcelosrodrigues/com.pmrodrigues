package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.Constante;
import com.pmrodrigues.sisgns.controllers.PlanoController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.FaixaEtariaRepository;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 08/10/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestPlanoController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PlanoRepository repository;

    @Autowired
    private FaixaEtariaRepository faixaEtariaRepository;

    @Autowired
    private SessionFactory sessionFactory;

    private MockResult result = new MockResult();

    private Validator validator = new MockValidator();

    private long planoId;
    private long operadoraId;
    private long cedenteId;
    private long enderecoId;

    @Before
    public void setup() {

        limparBaseDeDados();

        jdbcTemplate.update("insert into operadora (nome) values ( 'TESTE') ");
        this.operadoraId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        jdbcTemplate.update("insert into endereco (complemento,numero,logradouro , cep , cidade_id , bairro_id , estado_id ) " +
                " values ('TESTE','TESTE','TESTE' , '22743310' , 7043 , 12258 , 19 ) ");

        this.enderecoId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        jdbcTemplate.update("insert into cedente (DTYPE , convenio, nome, numeroDocumento , endereco_id) values ( 'Administradora' , '1' , 'TESTE' , ? , ?)",
                GeradorCNPJCPF.cnpj(), enderecoId);
        this.cedenteId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        jdbcTemplate.update("insert into plano (nome, operadora_id, administradora_id) values ( 'plano_1' , ? , ? ) ",
                operadoraId, cedenteId);

        this.planoId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);

        jdbcTemplate.update("insert into comissionamento ( plano_id , nome , percentual , ordem ) values ( ? , 'NOME' , 100 , 1)", this.planoId);

    }

    private void limparBaseDeDados() {
        jdbcTemplate.update("delete from comissionamento where plano_id in ( select id from plano where operadora_id in ( select id from operadora where nome like 'TESTE%'))");
        jdbcTemplate.update("delete from cedente where endereco_id = ?", enderecoId);
        jdbcTemplate.update("delete from plano where operadora_id in ( select id from operadora where nome like 'TESTE%')");
        jdbcTemplate.update("delete from operadora where nome like 'TESTE%'");

    }

    @Test
    public void testDoUpdate() throws Exception {

        Plano plano = new Plano();
        plano.setId(this.planoId);
        plano.setNome("TESTE_1");
        Operadora operadora = new Operadora();
        operadora.setId(this.operadoraId);
        plano.setOperadora(operadora);
        Administradora administradora = new Administradora();
        administradora.setId(this.cedenteId);
        plano.setAdministradora(administradora);

        PlanoController controller = new PlanoController(this.repository, this.faixaEtariaRepository, result, validator);
        controller.doUpdate(plano);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select count(1) from comissionamento where plano_id = ?", Long.class, this.planoId);
        assertEquals(0L, count);

    }

    @Test
    public void deveAdicionarFaixasEtariasNoResult() throws Exception {
        PlanoController controller = new PlanoController(this.repository, this.faixaEtariaRepository, result, validator);
        controller.doBefore();

        assertNotNull(result.included(Constante.FAIXA_ETARIA));
    }

    @Test
    public void deveBuscarPlano() {
        PlanoController controller = new PlanoController(this.repository, this.faixaEtariaRepository, result, validator);
        Operadora operadora = new Operadora();
        operadora.setId(this.operadoraId);
        final ResultList<Plano> resultList = controller.buscarPlanoPeloNome(operadora, "plano");


        assertNotNull(resultList);

    }
}