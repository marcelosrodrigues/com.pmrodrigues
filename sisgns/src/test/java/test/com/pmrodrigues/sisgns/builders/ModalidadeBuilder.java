package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.Modalidade;
import org.hibernate.SessionFactory;

/**
 * Created by Marceloo on 08/12/2015.
 */
public class ModalidadeBuilder {
    private final SessionFactory sessionFactory;
    private final Modalidade modalidade;

    private ModalidadeBuilder(final SessionFactory sessionFactory) {
        this.modalidade = new Modalidade();
        this.sessionFactory = sessionFactory;
    }

    public static ModalidadeBuilder getFactory(SessionFactory sessionFactory) {
        return new ModalidadeBuilder(sessionFactory);
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
        this.sessionFactory.getCurrentSession().persist(modalidade);
        return modalidade;
    }
}
