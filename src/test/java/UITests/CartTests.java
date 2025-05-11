package UITests;

import base.TestBase;
import org.testng.annotations.Test;

import java.util.Map;

import static pom.CartPage.cartDetails;
import static pom.HomePage.choosingCategoryFromSideMenu;
import static pom.HomePage.openWebsite;
import static pom.LoginPage.login;
import static pom.ResultsPage.*;

public class CartTests extends TestBase {

    @Test
    public void validateItemsAddedToCart() {
        openWebsite();
        login();
        choosingCategoryFromSideMenu();
        filterByFreeShipping();
        filterByNewCondition();
        sortByPrice();
        Map<String, Float> itemsAddedToCart = addItemsToCart();
        cartDetails(itemsAddedToCart);
    }

}
