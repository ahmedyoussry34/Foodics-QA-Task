package pom.Selectors;

import org.openqa.selenium.By;

public class CartSelectors {

    public static final By OPEN_CART = By.id("nav-cart");
    public static final By CART_ITEMS_WHOLE_PRICE = By.xpath("//span[@class='a-price-whole' and ancestor::*[@id='sc-active-cart']]");
    public static final By CART_ITEMS_FRACTION_PRICE = By.xpath("//span[@class='a-price-fraction' and ancestor::*[@id='sc-active-cart']]");
    public static final By NUMBER_OF_ITEMS_IN_CART = By.id("sc-subtotal-label-activecart");

}
