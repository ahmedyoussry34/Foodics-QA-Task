package pom;

import pom.Selectors.LoginSelectors;
import utils.SeleniumActions;

import static utils.ConfigReader.getValueFromConfig;

public class LoginPage {

    public static void login() {
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(LoginSelectors.LOGIN_NAV);
        seleniumActions.setText(LoginSelectors.EMAIL_FIELD, getValueFromConfig("username"), true, true);
        seleniumActions.clickOn(LoginSelectors.CONTINUE_BUTTON);
        seleniumActions.setText(LoginSelectors.PASSWORD_FIELD, getValueFromConfig("password"), true, true);
        seleniumActions.clickOn(LoginSelectors.SIGN_IN_BUTTON);
    }

}