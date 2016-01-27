package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.ModalidadeController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
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
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 08/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestModalidadeController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ModalidadeRepository repository;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private SessionFactory sessionFactory;

    private Result result = new MockResult();

    private Validator validator = new MockValidator();

    private ModalidadeController controller;

    @Before
    public void setup() {

        Modalidade primeira = ModalidadeBuilder.getFactory()
                .comNome("TESTE 1")
                .comCodigo("0001")
                .criar();

        sessionFactory.getCurrentSession().persist(primeira);

        Modalidade segunda = ModalidadeBuilder.getFactory()
                .comNome("TESTE 2")
                .comCodigo("0002")
                .criar();

        sessionFactory.getCurrentSession().persist(segunda);

        Modalidade terceira = ModalidadeBuilder.getFactory()
                .comNome("TESTE 3")
                .comCodigo("0003")
                .criar();

        sessionFactory.getCurrentSession().persist(terceira);

        controller = new ModalidadeController(repository, userRepository, result, validator);
    }

    @Test
    public void devePesquisarModalidadesPeloNome() {

        ResultList<Modalidade> resultList = controller.buscarModalidePorNome("TESTE");
        assertEquals(3L, resultList.getQuantidadeRegistros());

    }

    @Test
    public void deveInserirUmaModalidadeParaAMesmaAdministradoraDoUsuarioLogado() {

        Modalidade modalidade = ModalidadeBuilder.getFactory()
                .comCodigo("0004")
                .comNome("COM ADMINISTRADORA IGUAL A DO USUARIO")
                .criar();

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();


        final Administradora administradora = AdministradoraBuilder.getFactory()
                .comEndereco(enderecoDaAlacoro).criar();

        final Usuario usuarioLogado = UsuarioBuilder.getFactory()
                .comAdministradora(administradora)
                .comEmail("teste@teste.com").criar();

        sessionFactory.getCurrentSession().persist(administradora);
        sessionFactory.getCurrentSession().persist(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuarioLogado.getUsername(), usuarioLogado.getPassword()));

        controller.doInsert(modalidade);

        assertNotNull(modalidade.getId());

        Modalidade modalidadeSalva = (Modalidade) sessionFactory.getCurrentSession().get(Modalidade.class, modalidade.getId());
        assertEquals(usuarioLogado.getAdministradora(), modalidadeSalva.getAdministradora());

    }

    @Test
    public void deveSalvarAModalidadeParaUmAdministradoraDiferenteDoUsuarioLogado() {
        Modalidade modalidade = ModalidadeBuilder.getFactory()
                .comCodigo("0004")
                .comNome("COM ADMINISTRADORA IGUAL A DO USUARIO")
                .criar();

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();


        final Administradora administradora = AdministradoraBuilder.getFactory()
                .comEndereco(enderecoDaAlacoro).criar();

        final Usuario usuarioLogado = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com").criar();

        sessionFactory.getCurrentSession().persist(administradora);
        sessionFactory.getCurrentSession().persist(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuarioLogado.getUsername(), usuarioLogado.getPassword()));

        modalidade.setAdministradora(administradora);
        controller.doInsert(modalidade);

        assertNotNull(modalidade.getId());

        Modalidade modalidadeSalva = (Modalidade) sessionFactory.getCurrentSession().get(Modalidade.class, modalidade.getId());
        assertNotNull(modalidadeSalva.getAdministradora());
    }

}