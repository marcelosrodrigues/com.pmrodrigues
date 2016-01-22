package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.Constante;
import com.pmrodrigues.sisgns.controllers.PlanoController;
import com.pmrodrigues.sisgns.models.*;
import com.pmrodrigues.sisgns.repositories.FaixaEtariaRepository;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;
import test.com.pmrodrigues.sisgns.builders.RegraComissionamentoBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF.cnpj;

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

    private RegraComissionamento primeiraParcelaCorretor;
    private RegraComissionamento segundaParcelaCorretor;
    private RegraComissionamento terceiraParcelaCorretor;
    private Administradora administradora;
    private Operadora operadora;
    private Plano plano;

    @Before
    public void setup() {

        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();


        this.administradora = (Administradora) new Administradora()
                .comNome("Administradora")
                .comNumeroDocumento(cnpj());

        administradora.comEndereco(new Endereco(campoDaAreia.getBairro(),
                campoDaAreia.getBairro().getCidade(),
                campoDaAreia.getBairro().getCidade().getEstado(),
                "84", campoDaAreia.getLogradouro(), campoDaAreia.getCep()));

        administradora.adicionar(new Telefone("021", "33926222"));

        sessionFactory.getCurrentSession().persist(administradora);

        this.primeiraParcelaCorretor = RegraComissionamentoBuilder.getBuilder()
                .comAdministradora(administradora).comNome("REGRA 1").comOrdem(1).criar();

        this.segundaParcelaCorretor = RegraComissionamentoBuilder.getBuilder()
                .comAdministradora(administradora).comNome("REGRA 2").comOrdem(2).criar();
        this.terceiraParcelaCorretor = RegraComissionamentoBuilder.getBuilder()
                .comAdministradora(administradora).comNome("REGRA 3").comOrdem(3).criar();

        sessionFactory.getCurrentSession().persist(primeiraParcelaCorretor);
        sessionFactory.getCurrentSession().persist(segundaParcelaCorretor);
        sessionFactory.getCurrentSession().persist(terceiraParcelaCorretor);

        final Modalidade modalidade = ModalidadeBuilder.getFactory().criar();
        sessionFactory.getCurrentSession().persist(modalidade);
        this.operadora = OperadoraBuilder.getFactory().comAdministradora(administradora).comModalidade(modalidade).criar();
        sessionFactory.getCurrentSession().persist(operadora);
        this.plano = new Plano("PLANO", operadora, administradora);
        sessionFactory.getCurrentSession().persist(plano);
    }

    @Test
    public void testDoUpdate() throws Exception {

        Plano plano = new Plano();
        plano.setId(this.plano.getId());
        plano.setNome("TESTE_1");

        plano.setOperadora(operadora);
        plano.setAdministradora(administradora);

        PlanoController controller = new PlanoController(this.repository, this.faixaEtariaRepository, result, validator);
        controller.doUpdate(plano);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select count(1) from comissionamento_por_plano where plano_id = ?", Long.class, this.plano.getId());
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
        final ResultList<Plano> resultList = controller.buscarPlanoPeloNome(operadora, "plano");


        assertNotNull(resultList);

    }
}