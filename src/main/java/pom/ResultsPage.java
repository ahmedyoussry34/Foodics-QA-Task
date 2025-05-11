package pom;

import org.openqa.selenium.By;
import org.testng.Assert;
import pom.Selectors.ResultsSelectors;
import utils.SeleniumActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsPage {
    public static void filterByFreeShipping(){
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(ResultsSelectors.FREE_SHIPPING_FILTER_CHECKBOX,true);
        String freeShipping = seleniumActions.getValueFromAttribute(ResultsSelectors.FREE_SHIPPING_ENABLED,"aria-current");
        Assert.assertEquals(freeShipping,"true","Free Shipping is not enabled");
    }

    public static void filterByNewCondition(){
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(ResultsSelectors.NEW_CONDITION);
        String newCondition = seleniumActions.getValueFromAttribute(ResultsSelectors.NEW_CONDITION_ENABLED,"aria-current");
        Assert.assertEquals(newCondition,"true","New Condition is not enabled");
    }

    public static void sortByPrice(){
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(ResultsSelectors.SORT_BY_DROPDOWN);
        seleniumActions.clickOn(ResultsSelectors.PRICE_HIGH_TO_LOW);
    }

    public static Map<String, Float> addItemsToCart(){

        SeleniumActions seleniumActions = new SeleniumActions();
        Map<String ,Float> itemMap =  new HashMap<>();
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<String> priceList = seleniumActions.getTextContentFromAttribute(ResultsSelectors.PRICES);
            List<String> itemNames = seleniumActions.getTextContentFromAttribute(ResultsSelectors.ITEMS_NAMES);
            for (int itemIndex = 1; itemIndex <= priceList.size(); itemIndex++) {

                String cleanedPrice = priceList.get(itemIndex-1).replace("EGP", "")
                        .replace("\u00A0", "")
                        .replaceAll("[^\\d.]", "")
                        .trim();

                float cleanedPriceDecimalValue = Float.parseFloat(cleanedPrice);

                if (cleanedPriceDecimalValue < 15000) {
                    itemMap.put(itemNames.get(itemIndex-1), cleanedPriceDecimalValue);
                    seleniumActions.clickOn(By.xpath(String.format(ResultsSelectors.ADD_TO_CART_BY_INDEX, itemIndex)));
                }

            }

            if(!itemMap.isEmpty())
                break;
            seleniumActions.clickOn(ResultsSelectors.NEXT_BUTTON,true);
        }
     return itemMap;
    }

    public static void verifyCategoryName(String category){
        SeleniumActions seleniumActions = new SeleniumActions();
        List<String> categoryName = seleniumActions.getTextContentFromAttribute(ResultsSelectors.CATEGORY_NAME);
        Assert.assertEquals(categoryName.get(0), category, "Category does not match");
    }

}
