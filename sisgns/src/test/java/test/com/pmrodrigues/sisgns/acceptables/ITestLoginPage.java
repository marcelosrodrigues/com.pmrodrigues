package test.com.pmrodrigues.sisgns.acceptables;

import com.pmodrigues.pageobjects.AbstractPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 30/09/2015.
 */
public class ITestLoginPage extends AbstractAcceptableTests {

    private LoginPage page;

    @Before
    public void before() {
        page = new LoginPage(AbstractAcceptableTests.getDriver());
        update("update usuario set tentativas = 0 , bloqueado = 0 where email = 'marcelosrodrigues@globo.com'");
        commit();
    }

    @After
    public void after() {

        update("update usuario set tentativas = 0 , bloqueado = 0 where email = 'marcelosrodrigues@globo.com'");
        commit();
    }

    @Test
    public void deveEfetuarLogin() throws Exception {

        final AbstractPageObject next = page.email("marcelosrodrigues@globo.com")
                .password("12345678")
                .submit();

        assertTrue(next.getPageSource().contains("Dashboard"));
    }

    @Test
    public void naoDevePermitirLoginComUmUsuarioInvalido() throws Exception {

        final AbstractPageObject next = page.email("marcelosrodrigues@globo.com")
                .password("")
                .submit();

        assertTrue(next.getPageSource().contains("Usuario marcelosrodrigues@globo.com não encontrado ou senha invalida"));

        long tentativas = queryForScalar("select tentativas from usuario where email = 'marcelosrodrigues@globo.com'");
        assertEquals(1L, tentativas);

    }

    @Test
    public void devePermitirLoginComUmUsuarioBloqueado() throws Exception {

        update("update usuario set tentativas = 5 , bloqueado = 1 where email = 'marcelosrodrigues@globo.com'");
        commit();

        final AbstractPageObject next = page.email("marcelosrodrigues@globo.com")
                .password("")
                .submit();

        long tentativas = queryForScalar("select tentativas from usuario where email = 'marcelosrodrigues@globo.com'");
        boolean bloqueado = queryForScalar("select bloqueado from usuario where email = 'marcelosrodrigues@globo.com'");
        assertEquals(5L, tentativas);
        assertTrue(bloqueado);

        assertTrue(next.getPageSource().contains("Seu acesso foi bloqueado. Favor encontrar em contato com o administrador do sistema"));
    }

}
