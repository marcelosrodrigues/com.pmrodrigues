package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import com.pmrodrigues.vraptor.crud.exceptions.UniqueException;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 09/10/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestCorretorRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CorretorRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setup() {
        jdbcTemplate.update("insert into usuario ( DTYPE ,  nome , email , password , bloqueado , tentativas ) values " +
                "( 'Corretor' , 'teste' , 'teste@teste.com' , md5('12345678') , false , 0)");
    }

    @Test
    public void deveAdicionar() throws Exception {

        Corretor corretor = new Corretor();
        corretor.setEmail("marsilvarodrigues@globo.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.add(corretor);

        long count = jdbcTemplate.queryForObject("select count(1) from usuario where email = ? and dtype = 'Corretor'",
                Long.class, corretor.getEmail());

        assertEquals(1L, count);

    }

    @Test(expected = UniqueException.class)
    public void naoPodeSalvarOCorretor() {
        Corretor corretor = new Corretor();
        corretor.setEmail("teste@teste.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.add(corretor);
    }

    @Test
    public void deveAtualizarOCorretor() {
        long id = jdbcTemplate.queryForObject("select id from usuario where dtype = 'Corretor' and email = ?",
                Long.class, "teste@teste.com");
        Corretor corretor = new Corretor();
        corretor.setId(id);
        corretor.setEmail("marcelosrodrigues@teste.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.set(corretor);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select id from usuario where email = ? and dtype = 'Corretor'",
                Long.class, corretor.getEmail());

        assertEquals(id, count);
    }

    @Test
    public void deveAtualizarOCorretorSemAlterarOEmail() {
        long id = jdbcTemplate.queryForObject("select id from usuario where dtype = 'Corretor' and email = ?",
                Long.class, "teste@teste.com");
        Corretor corretor = new Corretor();
        corretor.setId(id);
        corretor.setEmail("teste@teste.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.set(corretor);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select id from usuario where email = ? and dtype = 'Corretor'",
                Long.class, corretor.getEmail());

        assertEquals(id, count);
    }

    @Test(expected = UniqueException.class)
    public void naoPodeAtualizarOCorretor() {

        long id = jdbcTemplate.queryForObject("select id from usuario where dtype = 'Corretor' and email = ?",
                Long.class, "teste@teste.com");


        String email = jdbcTemplate.queryForObject("select email from usuario where dtype = 'Corretor' and id != ? limit 1", String.class, id);

        Corretor corretor = new Corretor();
        corretor.setId(id);
        corretor.setEmail(email);
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.set(corretor);

    }
}