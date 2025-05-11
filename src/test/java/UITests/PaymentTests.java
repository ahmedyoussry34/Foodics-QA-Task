package UITests;

import base.TestBase;
import org.testng.annotations.Test;

import java.util.Map;

import static pom.AddressPage.addAddress;
import static pom.CartPage.cartDetails;
import static pom.HomePage.choosingCategoryFromSideMenu;
import static pom.HomePage.openWebsite;
import static pom.LoginPage.login;
import static pom.PaymentPage.checkOut;
import static pom.ResultsPage.*;

public class PaymentTests extends TestBase {


    @Test
    public void validateItemsAddedToCart() {
        openWebsite();
        login();
        addAddress();
        choosingCategoryFromSideMenu();
        filterByFreeShipping();
        filterByNewCondition();
        sortByPrice();
        Map<String, Float> itemsAddedToCart = addItemsToCart();
        cartDetails(itemsAddedToCart);
        checkOut();
    }

}
