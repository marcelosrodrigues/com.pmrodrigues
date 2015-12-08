package test.com.pmrodrigues.sisgns.builders;

import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.sisgns.models.Administradora;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import static org.hibernate.criterion.Restrictions.eq;
import static test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF.cnpj;

/**
 * Created by Marceloo on 08/12/2015.
 */
@Service
public class AdministradoraBuilder {

    private SessionFactory sessionFactory;
    private Administradora adminstradora;

    private AdministradoraBuilder(final SessionFactory sessionFactory) {
        this();
        this.sessionFactory = sessionFactory;

        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        adminstradora.comEndereco(new Endereco(campoDaAreia.getBairro(),
                campoDaAreia.getBairro().getCidade(),
                campoDaAreia.getBairro().getCidade().getEstado(),
                "84", campoDaAreia.getLogradouro(), campoDaAreia.getCep()));


    }

    private AdministradoraBuilder() {
        adminstradora = (Administradora) new Administradora()
                .comNome("Administradora")
                .comNumeroDocumento(cnpj());

        adminstradora.adicionar(new Telefone("021", "33926222"));
    }

    public static AdministradoraBuilder getFactory(final SessionFactory sessionFactory) {
        return new AdministradoraBuilder(sessionFactory);
    }

    public static AdministradoraBuilder getFactory() {
        return new AdministradoraBuilder();
    }

    public AdministradoraBuilder comNome(final String nome) {
        adminstradora.setNome(nome);
        return this;
    }

    public AdministradoraBuilder comCNPJ(final String cnpj) {
        adminstradora.comNumeroDocumento(cnpj);
        return this;
    }

    public AdministradoraBuilder comEndereco(final Logradouro logradouro) {
        adminstradora.comEndereco(new Endereco(logradouro.getBairro(),
                logradouro.getBairro().getCidade(),
                logradouro.getBairro().getCidade().getEstado(),
                "84", logradouro.getLogradouro(), logradouro.getCep()));

        return this;
    }

    public AdministradoraBuilder comEndereco(final Endereco endereco) {
        adminstradora.comEndereco(endereco);
        return this;
    }

    public Administradora criar() {
        if (sessionFactory != null) {
            sessionFactory.getCurrentSession().persist(adminstradora);
        }
        return adminstradora;
    }

    public AdministradoraBuilder comId(final Long id) {
        adminstradora.setId(id);
        return this;
    }
}
