package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
import com.pmodrigues.pageobjects.tags.TagFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;

/**
 * Created by Marceloo on 13/10/2015.
 */
@URL("http://localhost:8080/plano/novo.do")
public class PlanoSaudePage extends AbstractPageObject {

    public PlanoSaudePage(final WebDriver driver) {
        super(driver);
    }

    public PlanoSaudePage(final WebDriver driver, final boolean navigateTo) {
        super(driver, navigateTo);
    }

    public PlanoSaudePage administradora(final String administradora) {

        TagFactory.getInstance(super.getDriver()).byId("object.administradora.nome").setValue(administradora);

        boolean atualizouAdministradora = new WebDriverWait(super.getDriver(), 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver input) {
                return TagFactory.getInstance(input).byId("object.administradora").getValue() != null;
            }
        });
        return this;
    }

    public PlanoSaudePage operadora(final String operadora) {
        TagFactory.getInstance(super.getDriver()).byId("object.operadora.nome").setValue(operadora);

        boolean atualizouOperadora = new WebDriverWait(super.getDriver(), 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver input) {
                return TagFactory.getInstance(input).byId("object.operadora").getValue() != null;
            }
        });

        return this;
    }

    public PlanoSaudePage regraDeComissionamento(final String regra, final BigDecimal percentual, final Long ordem) {

        TagFactory.getInstance(super.getDriver()).byId("comissionamento.nome").setValue(regra);
        TagFactory.getInstance(super.getDriver()).byId("comissionamento.percentual").setValue(percentual);
        TagFactory.getInstance(super.getDriver()).byId("comissionamento.ordem").setValue(ordem);

        super.getDriver().findElement(By.id("adicionar-regra")).click();

        boolean atualizouRegraDeComissionnamento = new WebDriverWait(super.getDriver(), 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver input) {
                return input.getPageSource().contains(regra) &&
                        input.getPageSource().contains(String.valueOf(percentual)) &&
                        input.getPageSource().contains(String.valueOf(ordem));
            }
        });

        return this;
    }

    public PlanoSaudePage nome(final String nome) {
        TagFactory.getInstance(super.getDriver()).byId("object.nome").setValue(nome);
        return this;
    }

    @Override
    public AbstractPageObject submit() {
        this.getDriver().findElement(By.name("acao"))
                .click();

        return new ListPlanosDeSaudePage(this.getDriver());
    }

    @Override
    public AbstractPageObject abrir(String value) throws Exception {
        return null;
    }
}
