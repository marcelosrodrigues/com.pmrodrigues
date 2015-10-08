package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@URL("http://localhost:8080/administradora/index.do")
public class ListAdministradoraPlanosSaudePage extends AbstractPageObject {

    public ListAdministradoraPlanosSaudePage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public AbstractPageObject submit() {
        return null;
    }

    public AbstractPageObject abrir(final String value) throws Exception {
        this.getDriver().findElement(By.linkText(value))
                .click();

        return new AdministradoraPlanosSaudePage(this.getDriver(), true);
    }
}
