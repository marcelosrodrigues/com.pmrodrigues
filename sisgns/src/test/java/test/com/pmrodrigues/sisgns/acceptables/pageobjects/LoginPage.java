package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
import com.pmodrigues.pageobjects.tags.TagFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Marceloo on 30/09/2015.
 */
@URL("http://localhost:8080")
public class LoginPage extends AbstractPageObject {

    public LoginPage(final WebDriver driver) {
        super(driver);
    }

    public LoginPage email(final String email) {
        TagFactory.getInstance(this.getDriver())
                .byName("j_username")
                .setValue(email);
        return this;
    }

    public LoginPage password(final String password) {
        TagFactory.getInstance(this.getDriver())
                .byName("j_password")
                .setValue(password);

        return this;
    }

    @Override
    public AbstractPageObject submit() {
        this.getDriver().findElement(By.name("acao"))
                .click();

        if ("http://localhost:8080/index.do".equals(this.getDriver().getCurrentUrl())) {
            return new DashboardPage(this.getDriver());
        } else {
            return this;
        }
    }
}
