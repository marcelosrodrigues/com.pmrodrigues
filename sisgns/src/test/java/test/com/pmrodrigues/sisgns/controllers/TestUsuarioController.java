package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.security.utilities.MD5;
import com.pmrodrigues.sisgns.Constante;
import com.pmrodrigues.sisgns.controllers.UsuarioController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.security.Perfil;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.PerfilRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.PerfilBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.*;

/**
 * Created by Marceloo on 11/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestUsuarioController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private SessionFactory sessionFactory;

    private MockResult result = new MockResult();

    private MockValidator validator = new MockValidator();
    private Collection<Perfil> perfils = new ArrayList<>();
    private Administradora administradora;

    @Before
    public void preparaBaseDeDados() {

        Perfil administrador = PerfilBuilder.getFactory().comNome("ADMINISTRADOR").criar();
        sessionFactory.getCurrentSession().persist(administrador);

        Perfil financeiro = PerfilBuilder.getFactory().comNome("FINANCEIRO").criar();
        sessionFactory.getCurrentSession().persist(financeiro);

        this.perfils.addAll(asList(administrador, financeiro));

        this.administradora = AdministradoraBuilder.getFactory().criar();
        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();
        administradora.comEndereco(new Endereco(campoDaAreia.getBairro(),
                campoDaAreia.getBairro().getCidade(),
                campoDaAreia.getBairro().getCidade().getEstado(),
                "84", campoDaAreia.getLogradouro(), campoDaAreia.getCep()));

        administradora.adicionar(new Telefone("021", "33926222"));

        sessionFactory.getCurrentSession().persist(administradora);

    }

    @Test
    public void deveIncluirListaDePerfisParaMontagemDaTela() {

        final UsuarioController controller = new UsuarioController(userRepository, perfilRepository, result, validator);
        controller.montaTela();

        Collection<Perfil> perfis = result.included(Constante.PERFIS);
        assertNotNull(perfis);
        assertFalse(perfis.isEmpty());
        assertTrue(perfis.containsAll(this.perfils));

    }

    @Test
    public void deveTrocarSenha() {

        final UsuarioController controller = new UsuarioController(userRepository, perfilRepository, result, validator);
        Usuario marcelo = UsuarioBuilder.getFactory()
                .comNome("marcelo")
                .comSenha("12345678")
                .comAdministradora(this.administradora)
                .criar();

        this.sessionFactory.getCurrentSession().persist(marcelo);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(marcelo.getUsername(), "12345678"));

        controller.trocarSenha("12345678", "123456", "123456");
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
        marcelo = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, marcelo.getId());

        assertEquals(MD5.encrypt("123456"), marcelo.getPassword());
        assertEquals("Senha trocada com sucesso", result.included(com.pmrodrigues.vraptor.crud.Constante.SUCESSO));

    }

    @Test
    public void naoPodeTrocarSenha() {
        final UsuarioController controller = new UsuarioController(userRepository, perfilRepository, result, validator);
        Usuario marcelo = UsuarioBuilder.getFactory()
                .comNome("marcelo")
                .comSenha("12345678")
                .comAdministradora(this.administradora)
                .criar();

        this.sessionFactory.getCurrentSession().persist(marcelo);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(marcelo.getUsername(), "12345678"));

        controller.trocarSenha("12345678", "123456", "ABCDFG");

        assertTrue(validator.hasErrors());
    }

    @Test
    public void naoPodeTrocarASenhaComCamposVazios() {

        final UsuarioController controller = new UsuarioController(userRepository, perfilRepository, result, validator);
        Usuario marcelo = UsuarioBuilder.getFactory()
                .comNome("marcelo")
                .comSenha("12345678")
                .comAdministradora(this.administradora)
                .criar();

        this.sessionFactory.getCurrentSession().persist(marcelo);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(marcelo.getUsername(), "12345678"));

        controller.trocarSenha(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        assertTrue(validator.hasErrors());

    }

}