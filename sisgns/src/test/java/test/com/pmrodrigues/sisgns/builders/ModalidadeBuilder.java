package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.Modalidade;

/**
 * Created by Marceloo on 08/12/2015.
 */
public class ModalidadeBuilder {

    private final Modalidade modalidade;

    private ModalidadeBuilder() {
        this.modalidade = new Modalidade("0001", "MODALIDADE");
    }

    public static ModalidadeBuilder getFactory() {
        return new ModalidadeBuilder();
    }

    public ModalidadeBuilder comNome(final String nome) {
        this.modalidade.setNome(nome);
        return this;
    }

    public ModalidadeBuilder comCodigo(final String codigo) {
        this.modalidade.setCodigo(codigo);
        return this;
    }

    public Modalidade criar() {
        return modalidade;
    }
}
