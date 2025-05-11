package pom.Selectors;

import org.openqa.selenium.By;

public class ResultsSelectors {

    public static final By FREE_SHIPPING_FILTER_CHECKBOX = By.xpath("//*[@class='a-link-normal']/child::*[text()='Free Shipping']");
//    public static final By NEW_CONDITION = By.id("p_n_condition-type/28071525031");
    public static final By SORT_BY_DROPDOWN  = By.id("a-autoid-0-announce");
    public static final By PRICE_HIGH_TO_LOW  = By.id("s-result-sort-select_2");
    public static final By PRICES = By.xpath("//button[@name='submit.addToCart']/ancestor::*[*[@data-cy='price-recipe']]//span[@class='a-offscreen' and parent::*[@class='a-price']]");
    public static final By NEXT_BUTTON = By.xpath("//*[@role='button' and text()='Next']");
    public static String ADD_TO_CART_BY_INDEX ="(//span[@class='a-offscreen' and parent::*[@class='a-price']]/ancestor::*[*[@data-cy='price-recipe']]//button[@name='submit.addToCart'])[%s]";
    public static By ITEMS_NAMES =By.xpath("//button[@name='submit.addToCart']/ancestor::*[*[@data-cy='title-recipe']]//h2/span");
    public static By CATEGORY_NAME =By.id("nav-search-label-id");
    public static By FREE_SHIPPING_ENABLED =By.xpath("//*[@aria-label='Remove the filter Free Shipping to expand results']");
    public static By NEW_CONDITION_ENABLED =By.xpath("//*[@id='filter-p_n_condition-type']//*[text()='New']/parent::*");
    public static final By NEW_CONDITION = By.xpath("//*[@id='p_n_condition-type-title']/following-sibling::*//*[text()='New']/parent::*");

}
