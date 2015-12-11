package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.security.Perfil;
import com.pmrodrigues.sisgns.models.security.Usuario;

import java.lang.reflect.Field;

/**
 * Created by Marceloo on 11/12/2015.
 */
public class UsuarioBuilder {

    private Usuario usuario = new Usuario("teste", "teste@teste.com", "12345", null);

    public static UsuarioBuilder getFactory() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder comNome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder comEmail(String email) {
        this.usuario.setEmail(email);
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.usuario.setPassword(senha);
        return this;
    }

    public UsuarioBuilder estaDesbloqueado() {
        this.usuario.setBloqueado(false);
        return this;
    }

    public UsuarioBuilder comNumeroTentativasInvalidas(Long numeroTentativasRealizadas) {
        try {
            Field tentativas = this.usuario.getClass().getDeclaredField("tentativas");
            tentativas.setAccessible(true);
            tentativas.set(this.usuario, numeroTentativasRealizadas);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public Usuario criar() {
        return usuario;
    }

    public UsuarioBuilder estaBloqueado() {
        this.usuario.bloquear();
        return this;
    }

    public UsuarioBuilder noPerfil(final Perfil perfil) {
        this.usuario.getPerfis().add(perfil);
        return this;
    }

    public UsuarioBuilder comId(Long id) {
        this.usuario.setId(id);
        return this;
    }
}
