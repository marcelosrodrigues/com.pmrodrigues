package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.pessoa.models.PessoaFisica;
import com.pmrodrigues.pessoa.models.PessoaJuridica;
import com.pmrodrigues.sisgns.models.*;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static java.util.Arrays.asList;
import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 16/11/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestContrato extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    private Plano plano;

    private Administradora administradora;

    private Corretor corretor;

    private Endereco endereco = new Endereco();

    @Before
    public void setup() {
        this.plano = (Plano) this.sessionFactory.getCurrentSession().get(Plano.class, 1L);
        this.administradora = (Administradora) this.sessionFactory.getCurrentSession().get(Administradora.class, 1L);
        this.corretor = (Corretor) this.sessionFactory.getCurrentSession().get(Corretor.class, 136L);
        final Logradouro logradouro = (Logradouro) this.sessionFactory
                .getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        endereco.comLogradouro(logradouro.getLogradouro())
                .comNumero("84")
                .comComplemento("apto 206")
                .comEstado(logradouro.getBairro().getCidade().getEstado())
                .comCidade(logradouro.getBairro().getCidade())
                .comBairro(logradouro.getBairro())
                .comCEP(logradouro.getCep());
    }


    @Test
    public void deveAdicionarUmClienteNovoSendoEleBeneficiario() {


        PessoaFisica novo = (PessoaFisica) new PessoaFisica()
                .comNome("MARCELO")
                .comDocumento("070.323.277-02")
                .comEmail("marcelosrodrigues@globo.com")
                .comEndereco(this.endereco);

        novo.setDataNascimento(DateTime.now().toDate());

        Beneficiario beneficiario = new Beneficiario()
                .comNome("MARCELO")
                .comDataNascimento(DateTime.now().toDate());

        Contrato contrato = new Contrato()
                .daAdministradora(this.administradora)
                .comPlano(this.plano)
                .vendidoPor(this.corretor)
                .comNumeroContrato("1")
                .comDataAssinatura(DateTime.now().toDate())
                .paraCliente(novo)
                .comBeneficiarios(asList(beneficiario));


        this.sessionFactory.getCurrentSession().save(contrato);
        this.sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate
                .queryForObject("select count(1) from pessoa where email = 'marcelosrodrigues@globo.com'",
                        Long.class);
        assertEquals(1L, count);

        count = jdbcTemplate.queryForObject("select count(1) from beneficiario", Long.class);
        assertEquals(1L, count);

    }

    @Test
    public void deveAdicionarUmContratoParaPessoaJuridica() {

        PessoaJuridica cliente = (PessoaJuridica) new PessoaJuridica()
                .comDocumento("04.769.697/0001-10")
                .comNome("TESTE")
                .comEmail("marsilvarodrigues@gmail.com")
                .comEndereco(this.endereco);


        Beneficiario beneficiario = new Beneficiario()
                .comNome("MARCELO")
                .comDataNascimento(DateTime.now().toDate());

        beneficiario.setDataNascimento(DateTime.now().toDate());

        Contrato contrato = new Contrato()
                .daAdministradora(this.administradora)
                .comPlano(this.plano)
                .vendidoPor(this.corretor)
                .comNumeroContrato("1")
                .comDataAssinatura(DateTime.now().toDate())
                .paraCliente(cliente)
                .comBeneficiarios(asList(beneficiario));


        this.sessionFactory.getCurrentSession().save(contrato);
        this.sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate
                .queryForObject("select count(1) from pessoa where email = 'marsilvarodrigues@gmail.com'",
                        Long.class);
        assertEquals(1L, count);

        count = jdbcTemplate.queryForObject("select count(1) from beneficiario", Long.class);
        assertEquals(1L, count);
    }

    @Test
    public void deveAdicionarOContratoIncluindoOsDependentes() {

        PessoaJuridica cliente = (PessoaJuridica) new PessoaJuridica()
                .comDocumento("04.769.697/0001-10")
                .comNome("TESTE")
                .comEmail("marsilvarodrigues@gmail.com")
                .comEndereco(this.endereco);


        Beneficiario beneficiario = new Beneficiario()
                .comNome("MARCELO")
                .comDataNascimento(DateTime.now().toDate());

        Beneficiario dependente = new Beneficiario()
                .comNome("MARCELO")
                .comDataNascimento(DateTime.now().toDate());

        beneficiario.setDependentes(asList(dependente));


        beneficiario.setDataNascimento(DateTime.now().toDate());
        dependente.setDataNascimento(DateTime.now().toDate());

        Contrato contrato = new Contrato()
                .daAdministradora(this.administradora)
                .comPlano(this.plano)
                .vendidoPor(this.corretor)
                .comNumeroContrato("1")
                .comDataAssinatura(DateTime.now().toDate())
                .paraCliente(cliente)
                .comBeneficiarios(asList(beneficiario, dependente));


        this.sessionFactory.getCurrentSession().save(contrato);
        this.sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate
                .queryForObject("select count(1) from pessoa where email = 'marsilvarodrigues@gmail.com'",
                        Long.class);
        assertEquals(1L, count);

        count = jdbcTemplate.queryForObject("select count(1) from beneficiario", Long.class);
        assertEquals(2L, count);


    }

}