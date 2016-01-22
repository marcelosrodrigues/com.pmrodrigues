package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.security.Constante;
import com.pmrodrigues.security.utilities.MD5;
import com.pmrodrigues.sisgns.exceptions.PasswordNotChangedException;
import com.pmrodrigues.sisgns.models.security.Usuario;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import static org.junit.Assert.*;

public class TestUsuario {

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

}
