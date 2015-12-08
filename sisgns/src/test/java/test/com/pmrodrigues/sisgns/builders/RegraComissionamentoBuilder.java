package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.RegraComissionamento;
import org.hibernate.SessionFactory;

/**
 * Created by Marceloo on 08/12/2015.
 */
public class RegraComissionamentoBuilder {

    private Administradora administradora;
    private SessionFactory sessionFactory;
    private RegraComissionamento regraComissionamento;

    private RegraComissionamentoBuilder(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        administradora = AdministradoraBuilder.getFactory(sessionFactory).criar();
        regraComissionamento = new RegraComissionamento("REGRA 1", 1, administradora);
    }

    public static RegraComissionamentoBuilder getBuilder(final SessionFactory sessionFactory) {
        return new RegraComissionamentoBuilder(sessionFactory);
    }

    public RegraComissionamentoBuilder comNome(final String nome) {
        regraComissionamento.setNome(nome);
        return this;
    }

    public RegraComissionamentoBuilder comOrdem(final Integer ordem) {
        regraComissionamento.setOrdem(ordem);
        return this;
    }

    public RegraComissionamentoBuilder comAdministradora(final Administradora administradora) {
        regraComissionamento.setAdministradora(administradora);
        return this;
    }

    public RegraComissionamento criar() {
        sessionFactory.getCurrentSession().persist(this.regraComissionamento);
        return regraComissionamento;
    }


}
