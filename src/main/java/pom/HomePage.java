package pom;

import org.testng.Assert;
import pom.Selectors.HomeSelectors;
import utils.SeleniumActions;

import static pom.ResultsPage.verifyCategoryName;
import static utils.ConfigReader.getValueFromConfig;

public class HomePage {
    public static void choosingCategoryFromSideMenu() {
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.clickOn(HomeSelectors.All_FROM_SIDE_BAR);
        seleniumActions.clickOn(HomeSelectors.SEE_ALL_CATEGORIES);
        seleniumActions.clickOn(HomeSelectors.VIDEO_GAMES_CATEGORY);
        seleniumActions.clickOn(HomeSelectors.ALL_VIDEO_GAMES);
        verifyCategoryName("Video Games");

    }

    public static void openWebsite() {
        SeleniumActions seleniumActions = new SeleniumActions();
        seleniumActions.getURL("");
        String countryUrl = seleniumActions.getTextContentFromAttribute(HomeSelectors.ICON)
                .get(0)
                .replaceAll("\\s+", "");
        Assert.assertEquals(countryUrl, ".eg", "you are not in the home page of amazon egypt");

    }

    public static void validateAccountLoggedIn() {
        SeleniumActions seleniumActions = new SeleniumActions();
        String actualAccountName = getValueFromConfig("amazon.account.name");
        String accountName = seleniumActions.getTextContentFromAttribute(HomeSelectors.ACCOUNT_NAME)
                .get(0).replace("Hello,", "")
                .replaceAll("\\s+", "");
        Assert.assertEquals(accountName, actualAccountName, "you are not logged in");

    }

}
