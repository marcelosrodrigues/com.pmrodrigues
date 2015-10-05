package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
import org.openqa.selenium.WebDriver;

/**
 * Created by Marceloo on 30/09/2015.
 */
@URL("http://localhost:8080/index.do")
public class DashboardPage extends AbstractPageObject {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AbstractPageObject submit() {
        return null;
    }
}
