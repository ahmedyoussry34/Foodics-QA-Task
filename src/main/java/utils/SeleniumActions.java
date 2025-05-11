package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.ConfigReader.getValueFromConfig;
import static utils.DriverFactory.getDriver;

public class SeleniumActions {
    public static final Duration tenSeconds = Duration.of(10, ChronoUnit.SECONDS);
    public static final Duration twentySeconds = Duration.of(20, ChronoUnit.SECONDS);
    public static final Duration twentyFiveSeconds = Duration.of(25, ChronoUnit.SECONDS);

    public static List<WebElement> waitUntilElements(By locator, ExpectedConditionsEnum condition, Duration seconds) {

        List<WebElement> elements;
        switch (condition) {
            case presenceOfElements:
                elements = new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
                return elements;
            case visibilityOfElementsLocated:
                elements = new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
                return elements;
            default:
                elements = null;
                Assert.fail("Wrong condition");
        }
        return elements;
    }

    public void setText(By locator, String text, boolean clear, boolean... assertOnActualValue) {
        WebElement element = waitUntil(locator, ExpectedConditionsEnum.presenceOfElement, tenSeconds);
        if (element != null) {
            try {
                if (clear) {
                    element.clear();
                }
                element.sendKeys(text);
                if (assertOnActualValue.length >= 1 && assertOnActualValue[0]) {
                    String actualValue;
                    if (element.getAttribute("value") == null) {
                        if (element.getAttribute("innerHTML") == null) {
                            actualValue = element.getText();
                        } else {
                            actualValue = element.getAttribute("innerHTML");
                        }
                    } else {
                        actualValue = element.getAttribute("value");
                    }
                    assertEquals(actualValue, text, "Wrong text entered");
                }
            } catch (Exception e) {
                String message = String.format("Couldn't set text to element with selector:" +
                        " %s because of the exception: %s", locator, e.getMessage());
//                logger.severe(message);
                Assert.fail(message);
            }
        } else {
            String message = String.format("Element with selector: %s is null", locator);
//            logger.severe(message);
            Assert.fail(message);
        }
    }

    public void clickOn(By locator, boolean... waitUntilVisibleOnly) {
        WebElement element;
        if (waitUntilVisibleOnly.length > 0 && waitUntilVisibleOnly[0]) {
            element = waitUntil(locator, ExpectedConditionsEnum.visibilityOfElementLocated, tenSeconds);
        } else {
            element = waitUntil(locator, ExpectedConditionsEnum.ElementToBeClickable, twentyFiveSeconds);
        }

        if (element != null) {
            try {
                element.click();
            } catch (Exception e) {
                try {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
                } catch (Exception c) {
                    String message = String.format("Couldn't click on button with selector:" +
                            " %s because of the exception: %s", locator, c.getMessage());
                    Assert.fail(message);
                }
            }
            new WebDriverWait(getDriver(), tenSeconds).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } else {
            String message = String.format("Element with selector: %s is null", locator);
            Assert.fail(message);
        }
    }

    public List<String> getTextContentFromAttribute(By locator) {
        List<WebElement> elements = waitUntilElements(locator, ExpectedConditionsEnum.presenceOfElements, tenSeconds);
        List<String> visibleTexts = new ArrayList<>();
        if (elements == null || elements.isEmpty()) {
            String message = String.format("No elements found with locator: %s", locator);
            Assert.fail(message);
        }
        for (WebElement element : elements) {
            visibleTexts.add(element.getAttribute("textContent"));
        }

        return visibleTexts;
    }

    public String getValueFromAttribute(By locator, String attribute) {
        WebElement element = waitUntil(locator, ExpectedConditionsEnum.presenceOfElement, tenSeconds);
        String visibleText;
        if (element == null) {
            String message = String.format("No element found with locator: %s", locator);
            Assert.fail(message);
        }
        visibleText = element.getAttribute(attribute);
        return visibleText;
    }

    public boolean isElementPresent(By locator) {
        return !getDriver().findElements(locator).isEmpty();
    }

    public void getURL(String endpoint) {
        getDriver().get(getValueFromConfig("base.url") + endpoint);
    }

    public WebElement waitUntil(By locator, ExpectedConditionsEnum condition, Duration seconds) {

        WebElement element;
        switch (condition) {
            case presenceOfElement:
                element = (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
                return element;
            case ElementToBeClickable:
                element = (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.elementToBeClickable(locator));
                return element;
            case visibilityOfElementLocated:
                element = (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
                return element;
            case invisibilityOfElementLocated:
                (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
                return null;
            default:
                element = null;
                Assert.fail("Wrong condition");
        }
        return element;

    }

    public enum ExpectedConditionsEnum {
        presenceOfElement,
        ElementToBeClickable,
        visibilityOfElementLocated,
        invisibilityOfElementLocated,
        presenceOfElements,
        visibilityOfElementsLocated
    }
}

