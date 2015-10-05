package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
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
}
