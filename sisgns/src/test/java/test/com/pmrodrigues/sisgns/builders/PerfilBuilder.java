package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.security.Filtro;
import com.pmrodrigues.sisgns.models.security.Perfil;

/**
 * Created by Marceloo on 11/12/2015.
 */
public class PerfilBuilder {

    private Perfil perfil = new Perfil("perfil");

    public static PerfilBuilder getFactory() {
        return new PerfilBuilder();
    }

    public PerfilBuilder comNome(String nome) {
        this.perfil.setNome(nome);
        return this;
    }

    public Perfil criar() {
        return perfil;
    }

    public PerfilBuilder comFiltro(final Filtro filtro) {
        this.perfil.getFiltrosDeDados().add(filtro);
        filtro.setPerfil(this.perfil);
        return this;
    }
}
