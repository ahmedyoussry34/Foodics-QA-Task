package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static utils.DriverFactory.getDriver;
import static utils.DriverFactory.quitWebDriver;

public class TestBase {


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitWebDriver();
    }

}
