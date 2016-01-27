package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.security.Constante;
import com.pmrodrigues.security.utilities.MD5;
import com.pmrodrigues.sisgns.exceptions.PasswordNotChangedException;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestUsuario extends AbstractTransactionalJUnit4SpringContextTests {


    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void deveBloquearUsuarioCasoExcedaONumeroDeTentativasInvalidas() {

        Usuario marcelo = UsuarioBuilder.getFactory()
                .comNome("marcelo")
                .comEmail("marcelosrodrigues@globo.com")
                .comSenha("1234567")
                .estaDesbloqueado()
                .comNumeroTentativasInvalidas(Constante.NUMERO_MAXIMO_TENTATIVAS_FALHAS - 1)
                .criar();

        marcelo.realizouTentativaInvalida();
        assertFalse(marcelo.isAccountNonLocked());

    }

    @Test
    public void deveApenasIncrementarONumeroDeTentativasInvalidas() {
        Usuario marcelo = UsuarioBuilder.getFactory()
                .comNome("marcelo")
                .comEmail("marcelosrodrigues@globo.com")
                .comSenha("1234567")
                .estaDesbloqueado()
                .criar();

        marcelo.realizouTentativaInvalida();
        assertEquals(Long.valueOf(1L), marcelo.getTentativas());
    }

    @Test
    public void deveZerarONumeroDeTentativasInvalidasQuandoOUsuarioForDesbloqueado() {

        Usuario marcelo = UsuarioBuilder.getFactory()
                .comNome("marcelo")
                .comEmail("marcelosrodrigues@globo.com")
                .comSenha("1234567")
                .estaBloqueado()
                .comNumeroTentativasInvalidas(Constante.NUMERO_MAXIMO_TENTATIVAS_FALHAS - 1)
                .criar();

        marcelo.desbloquear();
        assertTrue(marcelo.isAccountNonLocked());
        assertEquals(Long.valueOf(0L), marcelo.getTentativas());

    }

    @Test
    public void deveTrocarASenha() throws PasswordNotChangedException {
        String senha = "123456";

        Usuario usuario = UsuarioBuilder.getFactory().comSenha(senha).criar();

        usuario.trocarSenha(senha, "12345678", "12345678");

        assertEquals(MD5.encrypt("12345678"), usuario.getPassword());

    }

    @Test(expected = PasswordNotChangedException.class)
    public void naoPodeTrocarASenhaPorqueASenhaInformadaNaoEIgualaAtual() throws PasswordNotChangedException {

        String senha = "123456";

        Usuario usuario = UsuarioBuilder.getFactory().comSenha(senha).criar();

        usuario.trocarSenha("abcdfg", "12345678", "12345678");

    }

    @Test(expected = PasswordNotChangedException.class)
    public void naoPodeTrocarASenhaPorqueANovaSenhaNaoConfereComAConfirmacao() throws PasswordNotChangedException {

        String senha = "123456";

        Usuario usuario = UsuarioBuilder.getFactory().comSenha(senha).criar();

        usuario.trocarSenha(senha, "12345678", "123456");
    }

    @Test
    public void deveFiltrarOsUsuariosQueTenhoAcesso() {

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();


        final Administradora temAcesso = AdministradoraBuilder.getFactory().comEndereco(enderecoDaAlacoro).comNome("TEM_ACESSO").criar();


        final Administradora naoTemAcesso = AdministradoraBuilder.getFactory().comEndereco(enderecoDaAlacoro).comNome("NAO_TEM_ACESSO").criar();


        final Usuario usuarioQueTenhoAcesso = UsuarioBuilder.getFactory()
                .comNome("TENHO_ACESSO")
                .comEmail("teste@teste.com")
                .comAdministradora(temAcesso)
                .criar();

        final Usuario usuarioQueNaoTenhoAcesso = UsuarioBuilder.getFactory()
                .comNome("NAO_TENHO_ACESSO")
                .comEmail("teste2@teste.com")
                .comAdministradora(naoTemAcesso)
                .criar();


        sessionFactory.getCurrentSession().persist(temAcesso);
        sessionFactory.getCurrentSession().persist(naoTemAcesso);
        sessionFactory.getCurrentSession().persist(usuarioQueTenhoAcesso);
        sessionFactory.getCurrentSession().persist(usuarioQueNaoTenhoAcesso);
        sessionFactory.getCurrentSession().flush();

        sessionFactory.getCurrentSession().enableFilter("administradora")
                .setParameter("usuario", usuarioQueTenhoAcesso.getId());


        List<Usuario> podeRetornar = sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(like("nome", "TENHO_ACESSO", MatchMode.START))
                .list();

        assertNotNull(podeRetornar);
        assertFalse(podeRetornar.isEmpty());

        List<Usuario> naoTenhoAcesso = sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(like("nome", "NAO_TENHO_ACESSO", MatchMode.START))
                .list();


        assertNotNull(naoTenhoAcesso);
        assertTrue(naoTenhoAcesso.isEmpty());

    }


}
