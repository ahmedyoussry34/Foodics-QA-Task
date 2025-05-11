package pom;

import pom.Selectors.PaymentSelectors;
import utils.SeleniumActions;

public class PaymentPage {

    public static void checkOut() {
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(PaymentSelectors.PROCEED_TO_PAY);
        if (seleniumActions.isElementPresent(PaymentSelectors.DECLINE_BUTTON))
            seleniumActions.clickOn(PaymentSelectors.DECLINE_BUTTON);

    }

}
