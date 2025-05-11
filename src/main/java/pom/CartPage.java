package pom;

import org.testng.Assert;
import pom.Selectors.CartSelectors;
import utils.SeleniumActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartPage {

    public static void cartDetails(Map<String, Float> itemsAddedToCart) {

        SeleniumActions seleniumActions = new SeleniumActions();
        List<Float> totalItemSPrice = new ArrayList<>();
        float subtotalFromCart = 0;
        float subtotalAddedToCart = 0;
        seleniumActions.clickOn(CartSelectors.OPEN_CART);
        String itemCountFromCart = seleniumActions.getValueFromAttribute(CartSelectors.NUMBER_OF_ITEMS_IN_CART, "textContent");
        List<String> wholePrice = seleniumActions.getTextContentFromAttribute(CartSelectors.CART_ITEMS_WHOLE_PRICE);
        List<String> fractionPrice = seleniumActions.getTextContentFromAttribute(CartSelectors.CART_ITEMS_FRACTION_PRICE);
        for (int i = 0; i < wholePrice.size(); i++) {
            float price = Float.parseFloat(wholePrice.get(i).replace(",", "") + fractionPrice.get(i));
            subtotalFromCart += price;
            totalItemSPrice.add(i, price);
        }
        for (String itemName : itemsAddedToCart.keySet()) {
            subtotalAddedToCart += itemsAddedToCart.get(itemName);
            Assert.assertTrue(totalItemSPrice.contains(itemsAddedToCart.get(itemName)), "item price not found");
        }
        Assert.assertEquals(subtotalFromCart, subtotalAddedToCart, "subtotal do not match");
        Assert.assertEquals(Integer.parseInt(itemCountFromCart.replaceAll("\\D", "")), itemsAddedToCart.size(), "number of items in cart do not match");

    }

}
