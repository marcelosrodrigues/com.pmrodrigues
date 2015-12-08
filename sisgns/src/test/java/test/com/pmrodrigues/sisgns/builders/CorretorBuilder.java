package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Corretor;
import org.hibernate.SessionFactory;

/**
 * Created by Marceloo on 08/12/2015.
 */
public class CorretorBuilder {

    private final SessionFactory sessionFactory;
    private final Corretor corretor;

    private CorretorBuilder(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
        corretor = new Corretor("TESTE", "teste@teste.com", "123456", null);

    }

    public static CorretorBuilder getFactory(final SessionFactory sessionFactory) {
        return new CorretorBuilder(sessionFactory);
    }

    public CorretorBuilder comAdministradora(final Administradora administradora) {
        this.corretor.setAdministradora(administradora);
        return this;
    }

    public CorretorBuilder comNome(final String nome) {
        this.corretor.setNome(nome);
        return this;
    }

    public CorretorBuilder comEmail(final String email) {
        this.corretor.setEmail(email);
        return this;
    }

    public Corretor criar() {
        this.sessionFactory.getCurrentSession().persist(this.corretor);
        return corretor;
    }
}
