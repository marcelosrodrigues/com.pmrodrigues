package test.com.pmrodrigues.sisgns.services;

import com.pmrodrigues.security.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 23/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestUsuarioService extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserService service;

    @Test
    public void testLoadUserByUsername() throws Exception {

        jdbcTemplate.update("insert into usuario ( DTYPE ,  nome , email , password , bloqueado , tentativas ) values " +
                "( 'Usuario' , 'teste' , 'teste@teste.com' , md5('12345678') , false , 0)");

        UserDetails usuario = service.loadUserByUsername("teste@teste.com");
        assertNotNull(usuario);

    }
}