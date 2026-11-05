package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Navigation and screen queries for the connect-by-address flow. */
public class ConnectByAddressPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ConnectByAddressPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void waitForConnectOptions() {
        wait.until(currentDriver -> currentDriver.getPageSource().contains("hint=\"Server URL\""));
    }

    public void waitForHomeScreen() {
        wait.until(currentDriver -> currentDriver.getPageSource().toLowerCase()
                .contains("connect by address"));
    }

    public void openConnectByAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Connect by address\")")))
                .click();
    }

    private String getScreenSource() {
        return driver.getPageSource();
    }

    public boolean showsServerUrlField() {
        return screenContains("hint=\"Server URL\"");
    }

    public boolean showsNameField() {
        return screenContains("hint=\"Name\"");
    }

    public boolean showsClientTokenField() {
        return screenContains("hint=\"Client token\"");
    }

    public boolean showsConnectOption() {
        return screenContains("text=\"connect\"");
    }

    private boolean screenContains(String value) {
        return getScreenSource().contains(value);
    }
}

