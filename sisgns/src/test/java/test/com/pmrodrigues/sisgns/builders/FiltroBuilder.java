package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.security.Filtro;

/**
 * Created by Marceloo on 11/12/2015.
 */
public class FiltroBuilder {

    private Filtro filtro = new Filtro("administradores");

    public static FiltroBuilder getFactory() {
        return new FiltroBuilder();
    }

    public Filtro criar() {
        return filtro;
    }
}
