package test.com.pmrodrigues.sisgns.acceptables;

import com.pmodrigues.pageobjects.factory.WebDriverFactory;
import com.pmrodrigues.sisgns.models.Administradora;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.AdministradoraPlanosSaudePage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.DashboardPage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.ListAdministradoraPlanosSaudePage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 01/10/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class ITestAdministradoraPlanoSaudePage extends AbstractTransactionalJUnit4SpringContextTests {

    private static DashboardPage dashboard;

    private static WebDriver webdriver = WebDriverFactory.createWebDriver();

    @AfterClass
    public static void finaliza() {
        webdriver.close();
        webdriver.quit();
    }

    @BeforeClass
    public static void before() throws Exception {

        dashboard = (DashboardPage) new LoginPage(webdriver)
                .email("marcelosrodrigues@globo.com")
                .password("12345678")
                .submit();
    }


    @After
    public void after() {
        limparBaseDeDados();
    }

    private void limparBaseDeDados() {


        jdbcTemplate.update("DELETE FROM telefone_administrador " +
                " WHERE administradora_id IN ( " +
                "       SELECT ID FROM CEDENTE where numeroDocumento = '99.619.274/0001-20')");
        jdbcTemplate.update(" DELETE FROM telefone " +
                " where not exists ( select 1 from telefone_Administrador where telefone_id = id )  " +
                " and not exists ( select 1 from telefone_pessoa where telefone_id = id ) ");

        jdbcTemplate.update("DELETE FROM CEDENTE where numeroDocumento = '99.619.274/0001-20'");
        jdbcTemplate.update("DELETE FROM ENDERECO  WHERE bairro_id = (select id from bairro where nome like 'Pechincha%' and cidade_id = 7043  and id > 12258)");
        jdbcTemplate.update("DELETE FROM BAIRRO WHERE nome like 'Pechincha%' and cidade_id = 7043 and id > 12258");
        jdbcTemplate.update("DELETE FROM telefone " +
                " where not exists ( select 1 from telefone_Administrador where telefone_id = id ) " +
                " and not exists(select 1 from telefone_pessoa where telefone_id = id )");

        jdbcTemplate.update("commit");
    }


    @Test
    public void devePesquisarAdministradora() throws Exception {

        final ListAdministradoraPlanosSaudePage page = (ListAdministradoraPlanosSaudePage) dashboard
                .navigateTo(ListAdministradoraPlanosSaudePage.class);

        final List<Administradora> administradoras = jdbcTemplate.query("select * from cedente limit 0 , 20", new RowMapper<Administradora>() {
            @Override
            public Administradora mapRow(ResultSet rs, int rowNum) throws SQLException {
                return (Administradora) new Administradora()
                        .comNome(rs.getString("nome"))
                        .comNumeroDocumento(rs.getString("numeroDocumento"));
            }
        });

        final String pageSource = page.getPageSource();
        for (final Administradora administradora : administradoras) {
            assertTrue(pageSource.contains(administradora.getNome()));
            assertTrue(pageSource.contains(administradora.getNumeroDocumento()));
        }

    }

    @Test
    public void deveAdicionarAdministradora() throws Exception {

        AdministradoraPlanosSaudePage page = (AdministradoraPlanosSaudePage) dashboard.navigateTo(AdministradoraPlanosSaudePage.class);

        ListAdministradoraPlanosSaudePage administradoras = (ListAdministradoraPlanosSaudePage) page.nome("TESTE")
                .cnpj("99619274000120")
                .cep("22743310")
                .complemento("APTO 206")
                .numero("84")
                .adicionar("021", "33926222")
                .submit();

        assertTrue(administradoras.getPageSource().contains("99.619.274/0001-20"));
    }

    @Test
    public void deveAdicionarInformandoUmCEPQueNaoEstaCadastrado() throws Exception {

        AdministradoraPlanosSaudePage page = (AdministradoraPlanosSaudePage) dashboard.navigateTo(AdministradoraPlanosSaudePage.class);

        ListAdministradoraPlanosSaudePage administradoras = (ListAdministradoraPlanosSaudePage) page.nome("TESTE")
                .cnpj("99619274000120")
                .cep("22222222")
                .estado("RIO DE JANEIRO")
                .cidade("Rio de Janeiro")
                .bairro("PECHINCHA")
                .logradouro("Estrada campo da Areia")
                .complemento("APTO 206")
                .numero("84")
                .adicionar("021", "33926222")
                .submit();

        assertTrue(administradoras.getPageSource().contains("99.619.274/0001-20"));
    }

}
