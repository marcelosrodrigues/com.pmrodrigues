package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.junit.Assert.*;

/**
 * Created by Marceloo on 26/01/2016.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestAdministradora extends AbstractTransactionalJUnit4SpringContextTests {


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


        sessionFactory.getCurrentSession().persist(temAcesso);
        sessionFactory.getCurrentSession().persist(naoTemAcesso);
        sessionFactory.getCurrentSession().persist(usuario);
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    public void devePesquisarAdministradora() {

        final Usuario usuario = (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(eq("email", "teste@teste.com"))
                .uniqueResult();


        sessionFactory.getCurrentSession().enableFilter("administradora")
                .setParameter("usuario", usuario.getId());


        List<Administradora> podeRetornar = sessionFactory.getCurrentSession().createCriteria(Administradora.class)
                .add(like("nome", "TEM_ACESSO", MatchMode.START))
                .list();

        assertNotNull(podeRetornar);
        assertFalse(podeRetornar.isEmpty());

        List<Administradora> naoPodeRetornarAdministradora = sessionFactory.getCurrentSession().createCriteria(Administradora.class)
                .add(like("nome", "NAO_TEM_ACESSO", MatchMode.START))
                .list();

        assertNotNull(naoPodeRetornarAdministradora);
        assertTrue(naoPodeRetornarAdministradora.isEmpty());

    }
}
