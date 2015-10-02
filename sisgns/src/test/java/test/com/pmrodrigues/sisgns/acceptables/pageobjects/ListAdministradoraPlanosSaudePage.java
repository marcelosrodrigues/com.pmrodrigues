package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
import com.pmodrigues.pageobjects.annotations.URLs;
import org.openqa.selenium.WebDriver;

/**
 * Created by Marceloo on 01/10/2015.
 */
@URLs(
        urls = {
                @URL(url = "http://localhost:8080/administradora/index.do"),
                @URL(url = "http://localhost:8080/administradora/salvar.do")
        }
)
public class ListAdministradoraPlanosSaudePage extends AbstractPageObject {

    public ListAdministradoraPlanosSaudePage(final WebDriver driver) {
        super(driver);
    }
}
