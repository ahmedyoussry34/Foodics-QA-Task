package pom.Selectors;

import org.openqa.selenium.By;

public class HomeSelectors {

    public static final By ICON = By.id("nav-logo-sprites");
    public static final By ACCOUNT_NAME = By.id("nav-link-accountList-nav-line-1");
    public static final By All_FROM_SIDE_BAR = By.id("nav-hamburger-menu");
    public static final By SEE_ALL_CATEGORIES = By.xpath("//*[@aria-label='See All Categories']");;
    public static final By VIDEO_GAMES_CATEGORY = By.xpath("//*[@role='button']/child::*[text()='Video Games']");
    public static final By ALL_VIDEO_GAMES = By.xpath("//*[text()='All Video Games']");

}
