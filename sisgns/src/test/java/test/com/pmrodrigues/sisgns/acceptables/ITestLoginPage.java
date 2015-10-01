package test.com.pmrodrigues.sisgns.acceptables;

import com.pmodrigues.pageobjects.AbstractPageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.DashboardPage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;

import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 30/09/2015.
 */
public class ITestLoginPage {

    private LoginPage page;

    @Before
    public void before() {
        page = new LoginPage();
    }

    @After
    public void after() {
        page.close();
    }

    @Test
    public void doLogin() throws Exception {

        final AbstractPageObject next = page.email("marcelosrodrigues@globo.com")
                .password("12345678")
                .submit();

        assertTrue(next instanceof DashboardPage);
    }

    @Test
    public void doNotLogin() throws Exception {

        final AbstractPageObject next = page.email("marcelosrodrigues@globo.com")
                .password("")
                .submit();

        assertTrue(next.getPageSource().contains("Usuario marcelosrodrigues@globo.com não encontrado ou senha invalida"));

    }


}
