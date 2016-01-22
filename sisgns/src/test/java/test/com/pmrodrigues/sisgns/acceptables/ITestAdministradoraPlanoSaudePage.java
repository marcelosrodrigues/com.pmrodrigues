package test.com.pmrodrigues.sisgns.acceptables;

import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.sisgns.models.Administradora;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.AdministradoraPlanosSaudePage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.DashboardPage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.ListAdministradoraPlanosSaudePage;
import test.com.pmrodrigues.sisgns.acceptables.pageobjects.LoginPage;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Marceloo on 01/10/2015.
 */
public class ITestAdministradoraPlanoSaudePage extends AbstractAcceptableTests {

    private static DashboardPage dashboard;

    @BeforeClass
    public static void before() throws Exception {

        dashboard = (DashboardPage) new LoginPage(AbstractAcceptableTests.getDriver())
                .email("marcelosrodrigues@globo.com")
                .password("12345678")
                .submit();
    }


    @After
    public void after() {
        limparBaseDeDados();
    }

    private void limparBaseDeDados() {


        this.update("DELETE FROM telefone_administrador " +
                " WHERE administradora_id IN ( " +
                "       SELECT ID FROM CEDENTE where numeroDocumento = '99.619.274/0001-20')");
        this.update(" DELETE FROM telefone " +
                " where not exists ( select 1 from telefone_Administrador where telefone_id = id )  " +
                " and not exists ( select 1 from telefone_pessoa where telefone_id = id ) ");

        this.update("DELETE FROM CEDENTE where numeroDocumento = '99.619.274/0001-20'");
        this.update("DELETE FROM ENDERECO  WHERE bairro_id = (select id from bairro where nome like 'Pechincha%' and cidade_id = 7043  and id > 12258)");
        this.update("DELETE FROM BAIRRO WHERE nome like 'Pechincha%' and cidade_id = 7043 and id > 12258");
        this.update("DELETE FROM telefone " +
                " where not exists ( select 1 from telefone_Administrador where telefone_id = id ) " +
                " and not exists(select 1 from telefone_pessoa where telefone_id = id )");

        super.commit();
    }


    @Test
    public void devePesquisarAdministradora() throws Exception {

        final ListAdministradoraPlanosSaudePage page = dashboard
                .navigateTo(ListAdministradoraPlanosSaudePage.class);

        final List<Administradora> administradoras = query("select * from cedente limit 0 , 20", Administradora.class);

        final String pageSource = page.getPageSource();
        for (final Administradora administradora : administradoras) {
            assertTrue(pageSource.contains(administradora.getNome()));
            assertTrue(pageSource.contains(administradora.getNumeroDocumento()));
        }

    }

    @Test
    public void deveAdicionarAdministradora() throws Exception {

        AdministradoraPlanosSaudePage page = dashboard.navigateTo(AdministradoraPlanosSaudePage.class);

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

        AdministradoraPlanosSaudePage page = dashboard.navigateTo(AdministradoraPlanosSaudePage.class);

        ListAdministradoraPlanosSaudePage administradoras = (ListAdministradoraPlanosSaudePage) page.nome("TESTE")
                .cnpj("99619274000120")
                .cep("22222222")
                .estado("RIO DE JANEIRO")
                .cidade("Rio de Janeiro", 19)
                .bairro("PECHINCHA", 7043)
                .logradouro("Estrada campo da Areia")
                .complemento("APTO 206")
                .numero("84")
                .adicionar("021", "33926222")
                .submit();

        assertTrue(administradoras.getPageSource().contains("99.619.274/0001-20"));
    }

    @Test
    public void deveAlterarAAdministradora() throws Exception {


        Telefone telefone = queryForObject("select t.id , ddd , telefone as numero  from telefone t " +
                "  inner join telefone_administrador ta on telefone_id = t.id " +
                "  inner join cedente c on c.id = ta.administradora_id " +
                " where numeroDocumento = '44.757.560/0001-60'", Telefone.class);

        Administradora administradora = queryForObject("select * from cedente where numeroDocumento = '44.757.560/0001-60'", Administradora.class);

        AdministradoraPlanosSaudePage page = (AdministradoraPlanosSaudePage) dashboard.navigateTo(ListAdministradoraPlanosSaudePage.class)
                .abrir(String.valueOf(administradora.getId()));

        page.cep("22743310")
                .apagar("(" + telefone.getDdd() + ") " + telefone.getNumero())
                .adicionar("021", "81363699")
                .submit();


        List<Telefone> telefones = query("select id , ddd , telefone as numero from telefone inner join telefone_administrador on telefone_id = id" +
                " where administradora_id = 5", Telefone.class);
        assertFalse(telefones.isEmpty());
        assertEquals(1, telefones.size());
        telefone = telefones.get(0);
        assertEquals("021", telefone.getDdd());
        assertEquals("8136-3699", telefone.getNumero());


    }

}
