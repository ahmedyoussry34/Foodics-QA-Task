package pom.Selectors;

import org.openqa.selenium.By;

public class PaymentSelectors {

    public static final By PROCEED_TO_PAY = By.name("proceedToRetailCheckout");
    public static final By CHOOSE_LOCATION = By.xpath("//input[following-sibling::*[@id='checkout-primary-continue-button-id-announce']]");
    public static final By CASH_RADIO_BUTTON = By.xpath("//input[following-sibling::*/*[contains(text(), 'Cash on Delivery (COD)')]]");
    public static final By USE_PAYMENT_METHOD = By.xpath("//input[following-sibling::*[@id='checkout-secondary-continue-button-id-announce']]");
    public static final By SHIPPING_FEE = By.xpath("//*[contains(text(), 'Shipping & handling:')]/parent::*/parent::*/following-sibling::*//span");
    public static final By CASH_FEE = By.xpath("//*[contains(text(), 'Cash on Delivery Fee')]/parent::*/parent::*/following-sibling::*//span");
    public static final By TOTAL = By.xpath("//*[contains(text(), 'Order total')]/parent::*/following-sibling::*");
    public static final By DECLINE_BUTTON = By.id("prime-decline-button");

}
