package test.com.pmrodrigues.sisgns.models;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.junit.Assert.*;

/**
 * Created by Marceloo on 29/01/2016.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestOperadora extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void deveFiltrarApenasAsOperadorasQueOUsuarioTemAcesso() {

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();


        final Administradora temAcesso = AdministradoraBuilder.getFactory()
                .comEndereco(enderecoDaAlacoro)
                .comNome("TEM_ACESSO")
                .criar();


        final Administradora naoTemAcesso = AdministradoraBuilder.getFactory()
                .comEndereco(enderecoDaAlacoro)
                .comNome("NAO_TEM_ACESSO")
                .criar();


        final Usuario usuario = UsuarioBuilder.getFactory()
                .comEmail("teste@teste.com")
                .comAdministradora(temAcesso)
                .criar();

        final Modalidade modalidadeComAcesso = ModalidadeBuilder.getFactory()
                .comNome("COM_ACESSO")
                .comAdministradora(temAcesso)
                .criar();

        final Operadora comAcesso = OperadoraBuilder.getFactory()
                .comNome("TEM_ACESSO")
                .comAdministradora(temAcesso)
                .comModalidade(modalidadeComAcesso)
                .criar();

        final Operadora semAcesso = OperadoraBuilder.getFactory().comNome("ACESSO_NEGADO").comAdministradora(naoTemAcesso).comModalidade(modalidadeComAcesso).criar();

        sessionFactory.getCurrentSession().persist(temAcesso);
        sessionFactory.getCurrentSession().persist(naoTemAcesso);
        sessionFactory.getCurrentSession().persist(usuario);
        sessionFactory.getCurrentSession().persist(modalidadeComAcesso);
        sessionFactory.getCurrentSession().persist(comAcesso);
        sessionFactory.getCurrentSession().persist(semAcesso);
        sessionFactory.getCurrentSession().flush();


        sessionFactory.getCurrentSession().enableFilter("administradora")
                .setParameter("usuario", usuario.getId());

        List<Operadora> operadoras = sessionFactory.getCurrentSession()
                .createCriteria(Operadora.class)
                .add(like("nome", "TEM_ACESSO", MatchMode.START))
                .list();

        assertNotNull(operadoras);
        assertFalse(operadoras.isEmpty());

        operadoras = sessionFactory.getCurrentSession()
                .createCriteria(Operadora.class)
                .add(like("nome", "ACESSO_NEGADO", MatchMode.START))
                .list();

        assertNotNull(operadoras);
        assertTrue(operadoras.isEmpty());

    }


    @Test
    public void naoPodeInserirOperadoraComDadosInvalidos() {

        Operadora operadora = OperadoraBuilder.getFactory()
                .comNome(StringUtils.EMPTY)
                .comCodigo(StringUtils.EMPTY)
                .comAdministradora(new Administradora())
                .comModalidade(new Modalidade())
                .criar();

        Set<ConstraintViolation<Operadora>> constraintViolations = validator.validate(operadora);
        assertNotNull(constraintViolations);
        assertFalse(constraintViolations.isEmpty());
        assertEquals(4, constraintViolations.size());


    }
}
