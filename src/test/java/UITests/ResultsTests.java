package UITests;

import base.TestBase;
import org.testng.annotations.Test;

import static pom.HomePage.choosingCategoryFromSideMenu;
import static pom.HomePage.openWebsite;
import static pom.LoginPage.login;
import static pom.ResultsPage.*;

public class ResultsTests extends TestBase {

    @Test
    public void filterByCategoryTest() {
        openWebsite();
        login();
        choosingCategoryFromSideMenu();
    }

    @Test
    public void filterByFreeShippingTest() {
        openWebsite();
        login();
        choosingCategoryFromSideMenu();
        filterByFreeShipping();
    }

    @Test
    public void sortByPriceHighToLowTest() {
        openWebsite();
        login();
        choosingCategoryFromSideMenu();
        filterByFreeShipping();
        sortByPrice();
    }

    @Test
    public void filterByNewConditionTest() {
        openWebsite();
        login();
        choosingCategoryFromSideMenu();
        filterByNewCondition();
    }

    @Test
    public void addItemsToCartTest() {
        openWebsite();
        login();
        choosingCategoryFromSideMenu();
        filterByFreeShipping();
        filterByNewCondition();
        sortByPrice();
        addItemsToCart();
    }

}
