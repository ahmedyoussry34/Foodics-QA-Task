package UITests;

import base.TestBase;
import org.testng.annotations.Test;

import static pom.AddressPage.addAddress;
import static pom.AddressPage.removeAddress;
import static pom.HomePage.openWebsite;
import static pom.HomePage.validateAccountLoggedIn;
import static pom.LoginPage.login;

public class AddressTests extends TestBase {


    @Test
    public void verifyAddressIsAdded() {
        openWebsite();
        login();
        validateAccountLoggedIn();
        addAddress();
    }

    @Test(dependsOnMethods = {"verifyAddressIsAdded"})
    public void verifyAddressIsRemoved() {
        openWebsite();
        login();
        validateAccountLoggedIn();
        removeAddress();
    }

}
