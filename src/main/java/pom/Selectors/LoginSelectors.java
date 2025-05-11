package pom.Selectors;

import org.openqa.selenium.By;

public class LoginSelectors {

    public static final By LOGIN_NAV = By.id("nav-link-accountList");
    public static final By EMAIL_FIELD = By.id("ap_email_login");
    public static final By PASSWORD_FIELD = By.id("ap_password");
    public static final By CONTINUE_BUTTON = By.xpath("//input[@type='submit']");
    public static final By SIGN_IN_BUTTON = By.id("signInSubmit");

}
