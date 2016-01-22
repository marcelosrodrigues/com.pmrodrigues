package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.Plano;

/**
 * Created by Marceloo on 28/12/2015.
 */
public class PlanoBuilder {
    private final Plano plano;

    public static PlanoBuilder getFactory() {
        return new PlanoBuilder();
    }

    private PlanoBuilder() {
        this.plano = new Plano("PLANO", new Operadora(), new Administradora());
    }

    public PlanoBuilder comOperadora(final Operadora operadora) {
        this.plano.setOperadora(operadora);
        return this;
    }

    public PlanoBuilder comAdminstradora(final Administradora administradora) {
        this.plano.setAdministradora(administradora);
        return this;
    }

    public Plano criar() {
        return this.plano;
    }

    public PlanoBuilder comNome(String nome) {
        this.plano.setNome(nome);
        return this;
    }
}
