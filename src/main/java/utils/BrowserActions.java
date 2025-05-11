package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static utils.ConfigReader.getValueFromConfig;

public class BrowserActions {

    public static Map<String, RemoteWebDriver> driverMap = new HashMap<>();

    public static void addWebDriverToMapOfDrivers(String browser, String uniqueKey) {
        RemoteWebDriver driver = createDriver(browser);
        driver.manage().window().maximize();
        driverMap.put(uniqueKey, driver);
    }

    private static RemoteWebDriver createDriver(String browser) {
        switch (browser.trim().toLowerCase()) {
            case "chrome":
                if(getValueFromConfig("headless").equalsIgnoreCase("true")){
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(
                            "--headless=new",
                            "start-maximized",
                            "disable-infobars",
                            "--disable-extensions",
                            "--disable-gpu",
                            "--disable-dev-shm-usage"
                    );
                    return new ChromeDriver(options);
                }
                else
                    return new ChromeDriver();

            case "firefox":
                if(getValueFromConfig("headless").equalsIgnoreCase("true")){
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments(
                            "-headless",
                            "--start-maximized",
                            "--disable-gpu"
                    );
                    return new FirefoxDriver(options);
                }
                else
                    return new FirefoxDriver();
            case "edge":
                if(getValueFromConfig("headless").equalsIgnoreCase("true")){
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments(
                            "--headless=new",
                            "start-maximized",
                            "disable-infobars",
                            "--disable-extensions",
                            "--disable-gpu",
                            "--disable-dev-shm-usage"
                    );
                    return new EdgeDriver(options);
                }
                else
                    return new EdgeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static void closeDriverAndRemoveFromMap(String key) {
        if (driverMap.containsKey(key)) {
            driverMap.get(key).quit();
            driverMap.remove(key);
        }
    }

    public static void closeAllDriversFromMap() {
        Set<String> keys = driverMap.keySet();
        if (!keys.isEmpty()) {
            for (String key : keys) {
                driverMap.get(key).quit();
                driverMap.remove(key);
            }
        }
    }



}
