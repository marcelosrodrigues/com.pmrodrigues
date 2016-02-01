package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.OperadoraController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.sisgns.securities.SecurityContext;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF.cnpj;

/**
 * Created by Marceloo on 29/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestOperadoraController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OperadoraRepository repository;

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private SessionFactory sessionFactory;

    private Result result = new MockResult();

    private MockValidator validator = new MockValidator();

    private Administradora administradora;
    private Modalidade modalidade;
    private Logradouro campoDaAreia;

    @Before
    public void setup() {

        this.campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        this.administradora = AdministradoraBuilder.getFactory()
                .comEndereco(campoDaAreia)
                .criar();

        sessionFactory.getCurrentSession().persist(administradora);

        this.modalidade = ModalidadeBuilder.getFactory().comAdministradora(administradora).criar();

        sessionFactory.getCurrentSession().persist(modalidade);


        Operadora unimed = OperadoraBuilder.getFactory()
                .comNome("UNIMED")
                .comAdministradora(administradora)
                .comCodigo("0001")
                .comModalidade(modalidade)
                .criar();


        sessionFactory.getCurrentSession().persist(unimed);
    }

    @Test
    public void deveBuscarOperadoraPeloNome() throws Exception {

        final OperadoraController controller = new OperadoraController(repository, securityContext, result, validator);
        ResultList<Operadora> resultList = controller.buscarOperadoraPeloNome("UNIMED");


        final long count = jdbcTemplate.queryForObject("select count(1) from operadora where nome like 'UNIMED%' and excluido = 0", Long.class);
        assertEquals(count, resultList.getQuantidadeRegistros());
    }

    @Test
    public void deveInserirOperadoraNaMesmaAdministradoraDoUsuarioLogado() throws Exception {

        final OperadoraController controller = new OperadoraController(repository, securityContext, result, validator);


        Administradora outraAdministradora = AdministradoraBuilder.getFactory()
                .comNome("TESTE")
                .comCNPJ(cnpj())
                .comEndereco(campoDaAreia)
                .criar();


        sessionFactory.getCurrentSession().persist(outraAdministradora);

        Usuario usuarioLogado = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com")
                .comAdministradora(administradora)
                .criar();

        sessionFactory.getCurrentSession().persist(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuarioLogado.getUsername(), usuarioLogado.getPassword()));

        Operadora novaOperadora = OperadoraBuilder.getFactory().comNome("TESTE")
                .comModalidade(modalidade)
                .comAdministradora(outraAdministradora)
                .criar();

        controller.doInsert(novaOperadora);

        assertNotNull(novaOperadora.getId());
        assertEquals(usuarioLogado.getAdministradora(), novaOperadora.getAdministradora());

    }

    @Test
    public void deveInserirOperadoraComAdministradoraDefinidaPeloFormulario() {
        final OperadoraController controller = new OperadoraController(repository, securityContext, result, validator);


        Administradora outraAdministradora = AdministradoraBuilder.getFactory()
                .comNome("TESTE")
                .comCNPJ(cnpj())
                .comEndereco(campoDaAreia)
                .criar();


        sessionFactory.getCurrentSession().persist(outraAdministradora);

        Usuario usuarioLogado = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com")
                .comAdministradora(null)
                .criar();

        sessionFactory.getCurrentSession().persist(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuarioLogado.getUsername(), usuarioLogado.getPassword()));

        Operadora novaOperadora = OperadoraBuilder.getFactory().comNome("TESTE")
                .comModalidade(modalidade)
                .comAdministradora(outraAdministradora)
                .criar();

        controller.doInsert(novaOperadora);

        assertNotNull(novaOperadora.getId());
        assertNotNull(novaOperadora.getAdministradora());
    }

    @Test
    public void deveAlterarAOperadoraSentandoAMesmaAdministradoraDoUsuarioLogado() {

        final OperadoraController controller = new OperadoraController(repository, securityContext, result, validator);


        Administradora outraAdministradora = AdministradoraBuilder.getFactory()
                .comNome("TESTE")
                .comCNPJ(cnpj())
                .comEndereco(campoDaAreia)
                .criar();


        sessionFactory.getCurrentSession().persist(outraAdministradora);

        Usuario usuarioLogado = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com")
                .comAdministradora(administradora)
                .criar();

        sessionFactory.getCurrentSession().persist(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuarioLogado.getUsername(), usuarioLogado.getPassword()));

        Operadora novaOperadora = OperadoraBuilder.getFactory().comNome("TESTE")
                .comModalidade(modalidade)
                .comAdministradora(outraAdministradora)
                .criar();

        sessionFactory.getCurrentSession().persist(novaOperadora);

        controller.doUpdate(novaOperadora);

        sessionFactory.getCurrentSession().flush();

        assertEquals(usuarioLogado.getAdministradora(), novaOperadora.getAdministradora());
    }

    @Test
    public void deveAlterarAOperadoraSentandoAdministradoraDiferenteDoUsuarioLogado() {

        final OperadoraController controller = new OperadoraController(repository, securityContext, result, validator);


        Administradora outraAdministradora = AdministradoraBuilder.getFactory()
                .comNome("TESTE")
                .comCNPJ(cnpj())
                .comEndereco(campoDaAreia)
                .criar();


        sessionFactory.getCurrentSession().persist(outraAdministradora);

        Usuario usuarioLogado = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com")
                .comAdministradora(null)
                .criar();

        sessionFactory.getCurrentSession().persist(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuarioLogado.getUsername(), usuarioLogado.getPassword()));

        Operadora novaOperadora = OperadoraBuilder.getFactory().comNome("TESTE")
                .comModalidade(modalidade)
                .comAdministradora(outraAdministradora)
                .criar();

        sessionFactory.getCurrentSession().persist(novaOperadora);

        controller.doUpdate(novaOperadora);

        sessionFactory.getCurrentSession().flush();

        assertNotNull(novaOperadora.getAdministradora());
        assertEquals(outraAdministradora, novaOperadora.getAdministradora());
    }

}