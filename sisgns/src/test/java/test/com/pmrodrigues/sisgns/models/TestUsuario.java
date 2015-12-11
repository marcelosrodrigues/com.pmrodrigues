package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.security.Constante;
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

}
