package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.sisgns.models.*;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;
import test.com.pmrodrigues.sisgns.builders.RegraComissionamentoBuilder;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Marceloo on 07/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestComissionamento extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;
    private Administradora administradora;
    private RegraComissionamento primeiraParcelaCorretor;
    private RegraComissionamento segundaParcelaCorretor;
    private RegraComissionamento terceiraParcelaCorretor;
    private Operadora nova;

    @Before
    public void setup() {

        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        this.administradora = AdministradoraBuilder.getFactory()
                .comEndereco(campoDaAreia)
                .criar();

        sessionFactory.getCurrentSession().persist(administradora);

        this.primeiraParcelaCorretor = RegraComissionamentoBuilder
                .getBuilder()
                .comAdministradora(administradora)
                .criar();

        sessionFactory.getCurrentSession().persist(primeiraParcelaCorretor);

        this.segundaParcelaCorretor = RegraComissionamentoBuilder
                .getBuilder()
                .comAdministradora(administradora)
                .comNome("REGRA 2")
                .comOrdem(2)
                .criar();

        sessionFactory.getCurrentSession().persist(segundaParcelaCorretor);

        this.terceiraParcelaCorretor = RegraComissionamentoBuilder
                .getBuilder()
                .comAdministradora(administradora)
                .comNome("REGRA 3")
                .comOrdem(3)
                .criar();

        sessionFactory.getCurrentSession().persist(terceiraParcelaCorretor);

        final Modalidade modalidade = ModalidadeBuilder.getFactory().criar();
        sessionFactory.getCurrentSession().persist(modalidade);

        this.nova = OperadoraBuilder.getFactory()
                .comModalidade(modalidade)
                .comAdministradora(administradora)
                .criar();

        sessionFactory.getCurrentSession().persist(this.nova);

    }

    @Test
    public void deveCriarUmaNovaRegraDeComissaoParaOperadoras() {

        Collection<ComissionamentoPorOperadora> regrasDeComissionamento = asList(
                new ComissionamentoPorOperadora(BigDecimal.TEN, primeiraParcelaCorretor),
                new ComissionamentoPorOperadora(BigDecimal.ONE, segundaParcelaCorretor),
                new ComissionamentoPorOperadora(BigDecimal.TEN, terceiraParcelaCorretor));
        nova.setRegrasDeComissionamento(regrasDeComissionamento);

        sessionFactory.getCurrentSession().persist(nova);
        sessionFactory.getCurrentSession().flush();


        long quantidadeDeRegrasSalvas = jdbcTemplate.queryForObject("select count(1) from comissionamento_por_operadora where operadora_id = ?",
                Long.class, nova.getId());

        assertEquals(3L, quantidadeDeRegrasSalvas);

        nova = (Operadora) this.sessionFactory.getCurrentSession().get(Operadora.class, nova.getId());
        assertFalse(nova.getRegrasDeComissionamento().isEmpty());
        assertEquals(3, nova.getRegrasDeComissionamento().size());
    }

    @Test
    public void deveCriarUmaNovaRegraDeComissaoParaPlanos() {
        Plano plano = new Plano("PLANO", nova, administradora);

        Collection<ComissionamentoPorPlano> regrasDeComissionamento = asList(
                new ComissionamentoPorPlano(BigDecimal.TEN, primeiraParcelaCorretor),
                new ComissionamentoPorPlano(BigDecimal.ONE, segundaParcelaCorretor),
                new ComissionamentoPorPlano(BigDecimal.TEN, terceiraParcelaCorretor));

        plano.setRegrasDeComissionamento(regrasDeComissionamento);


        sessionFactory.getCurrentSession().persist(nova);
        sessionFactory.getCurrentSession().persist(plano);
        sessionFactory.getCurrentSession().flush();

        long quantidadeDeRegrasSalvas = jdbcTemplate.queryForObject("select count(1) from comissionamento_por_plano where plano_id = ?",
                Long.class, plano.getId());

        assertEquals(3L, quantidadeDeRegrasSalvas);

        plano = (Plano) this.sessionFactory.getCurrentSession().get(Plano.class, plano.getId());
        assertFalse(plano.getRegrasDeComissionamento().isEmpty());
        assertEquals(3, plano.getRegrasDeComissionamento().size());
    }
}
