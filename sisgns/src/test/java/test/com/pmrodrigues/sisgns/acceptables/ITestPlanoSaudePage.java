package test.com.pmrodrigues.sisgns.acceptables;

import org.junit.BeforeClass;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.DashboardPage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.PlanoSaudePage;

import java.math.BigDecimal;

/**
 * Created by Marceloo on 13/10/2015.
 */
public class ITestPlanoSaudePage extends AbstractAcceptableTests {

    private static DashboardPage dashboard;

    @BeforeClass
    public static void before() throws Exception {

        dashboard = (DashboardPage) new LoginPage(AbstractAcceptableTests.getDriver())
                .email("marcelosrodrigues@globo.com")
                .password("12345678")
                .submit();
    }

    @Test
    public void deveSalvarPlanoDeSaude() throws Exception {

        final PlanoSaudePage page = dashboard.navigateTo(PlanoSaudePage.class);
        page.administradora("TESTE")
                .operadora("UNIMED")
                .nome("TESTE")
                .regraDeComissionamento("REGRA 1", new BigDecimal(100), 1L)
                .submit();

    }
}
