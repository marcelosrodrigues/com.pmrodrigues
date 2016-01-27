package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.security.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.junit.Assert.*;

/**
 * Created by Marceloo on 26/01/2016.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestModalidade extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setup() {

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();


        final Administradora temAcesso = AdministradoraBuilder.getFactory().comEndereco(enderecoDaAlacoro).comNome("TEM_ACESSO").criar();


        final Administradora naoTemAcesso = AdministradoraBuilder.getFactory().comEndereco(enderecoDaAlacoro).comNome("NAO_TEM_ACESSO").criar();


        final Usuario usuario = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com")
                .comAdministradora(temAcesso)
                .criar();

        final Modalidade modalidadeComAcesso = ModalidadeBuilder.getFactory().comNome("COM_ACESSO").comAdministradora(temAcesso).criar();
        final Modalidade modalidadeSemAcessoAcesso = ModalidadeBuilder.getFactory().comNome("SEM_ACESSO").comAdministradora(naoTemAcesso).criar();

        sessionFactory.getCurrentSession().persist(temAcesso);
        sessionFactory.getCurrentSession().persist(naoTemAcesso);
        sessionFactory.getCurrentSession().persist(usuario);
        sessionFactory.getCurrentSession().persist(modalidadeComAcesso);
        sessionFactory.getCurrentSession().persist(modalidadeSemAcessoAcesso);
        sessionFactory.getCurrentSession().flush();

    }

    @Test
    public void devePesquisarModalidadesQueTenhoAcesso() {

        Usuario usuario = (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(eq("email", "teste@teste.com"))
                .uniqueResult();

        sessionFactory.getCurrentSession().enableFilter("administradora")
                .setParameter("usuario", usuario.getId());

        List<Modalidade> tenhoAcesso = sessionFactory.getCurrentSession()
                .createCriteria(Modalidade.class)
                .add(like("nome", "COM_ACESSO", MatchMode.START))
                .list();

        assertNotNull(tenhoAcesso);
        assertFalse(tenhoAcesso.isEmpty());
    }

    @Test
    public void naoDevePesquisarModalidadesQueTenhoAcesso() {

        Usuario usuario = (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(eq("email", "teste@teste.com"))
                .uniqueResult();

        sessionFactory.getCurrentSession().enableFilter("administradora")
                .setParameter("usuario", usuario.getId());

        List<Modalidade> tenhoAcesso = sessionFactory.getCurrentSession()
                .createCriteria(Modalidade.class)
                .add(like("nome", "SEM_ACESSO", MatchMode.START))
                .list();

        assertNotNull(tenhoAcesso);
        assertTrue(tenhoAcesso.isEmpty());
    }

}
