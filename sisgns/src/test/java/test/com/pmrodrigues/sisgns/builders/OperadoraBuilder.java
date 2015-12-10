package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;

/**
 * Created by Marceloo on 08/12/2015.
 */
public class OperadoraBuilder {
    private final Operadora operadora;

    public OperadoraBuilder() {
        this.operadora = new Operadora("0001", "Operadora nova", new Modalidade(), new Administradora());
    }

    public static OperadoraBuilder getFactory() {
        return new OperadoraBuilder();
    }

    public OperadoraBuilder comModalidade(final Modalidade modalidade) {
        operadora.setModalidade(modalidade);
        return this;
    }

    public OperadoraBuilder comAdministradora(final Administradora administradora) {
        operadora.setAdministradora(administradora);
        return this;
    }

    public Operadora criar() {
        return operadora;
    }

    public OperadoraBuilder comNome(final String nome) {
        this.operadora.setNome(nome);
        return this;
    }

    public OperadoraBuilder comCodigo(final String codigo) {
        this.operadora.setCodigo(codigo);
        return this;
    }
}
