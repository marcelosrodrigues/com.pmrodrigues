package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.Action;
import com.pmodrigues.pageobjects.annotations.ByName;
import com.pmodrigues.pageobjects.annotations.URL;

/**
 * Created by Marceloo on 30/09/2015.
 */
@Action(byName = "acao")
@URL(url = "http://localhost:8080")
public class LoginPage extends AbstractPageObject {

    @ByName("j_username")
    private String login;

    @ByName("j_password")
    private String password;

    public LoginPage() {
        super();
    }

    public LoginPage email(final String email) {
        this.login = email;
        return this;
    }

    public LoginPage password(final String password) {
        this.password = password;
        return this;
    }

}
