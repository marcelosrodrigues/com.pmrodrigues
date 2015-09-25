package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 23/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestUsuarioRepository extends AbstractTransactionalJUnit4SpringContextTests {


    @Autowired
    private UsuarioRepository repository;

    @Test
    public void deveBuscarPeloEmail() throws Exception {

        jdbcTemplate.update("insert into usuario ( DTYPE ,  nome , email , password , bloqueado , tentativas ) values " +
                "( 'Usuario' , 'teste' , 'teste@teste.com' , md5('12345678') , false , 0)");

        Usuario usuario = repository.findByEmail("teste@teste.com");
        assertNotNull(usuario);

    }
}