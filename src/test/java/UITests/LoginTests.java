package UITests;

import base.TestBase;
import org.testng.annotations.Test;

import static pom.HomePage.openWebsite;
import static pom.HomePage.validateAccountLoggedIn;
import static pom.LoginPage.login;

public class LoginTests extends TestBase {


    @Test
    public void loginTest() {
        openWebsite();
        login();
        validateAccountLoggedIn();
    }

}
