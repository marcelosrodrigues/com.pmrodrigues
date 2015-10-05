package test.com.pmrodrigues.sisgns.acceptables;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.factory.WebDriverFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 30/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class ITestLoginPage extends AbstractTransactionalJUnit4SpringContextTests {

    private LoginPage page;

    private static WebDriver webdriver = WebDriverFactory.createWebDriver();

    @AfterClass
    public static void finaliza() {
        webdriver.close();
    }

    @Before
    public void before() {
        page = new LoginPage(webdriver);
        jdbcTemplate.update("update usuario set tentativas = 0 , bloqueado = 0 where email = 'marcelosrodrigues@globo.com'");
        jdbcTemplate.update("commit");
    }

    @After
    public void after() {

        jdbcTemplate.update("update usuario set tentativas = 0 , bloqueado = 0 where email = 'marcelosrodrigues@globo.com'");
        jdbcTemplate.update("commit");
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

        long tentativas = jdbcTemplate.queryForObject("select tentativas from usuario where email = 'marcelosrodrigues@globo.com'", Long.class);
        assertEquals(1L, tentativas);

    }

    @Test
    public void devePermitirLoginComUmUsuarioBloqueado() throws Exception {

        jdbcTemplate.update("update usuario set tentativas = 5 , bloqueado = 1 where email = 'marcelosrodrigues@globo.com'");
        jdbcTemplate.update("commit");

        final AbstractPageObject next = page.email("marcelosrodrigues@globo.com")
                .password("")
                .submit();

        long tentativas = jdbcTemplate.queryForObject("select tentativas from usuario where email = 'marcelosrodrigues@globo.com'", Long.class);
        boolean bloqueado = jdbcTemplate.queryForObject("select bloqueado from usuario where email = 'marcelosrodrigues@globo.com'", Boolean.class);
        assertEquals(5L, tentativas);
        assertTrue(bloqueado);

        assertTrue(next.getPageSource().contains("Seu acesso foi bloqueado. Favor encontrar em contato com o administrador do sistema"));
    }

}
