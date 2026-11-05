package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Environment;

import java.time.Duration;

/** Actions and state queries for the application's login screen. */
public class LoginPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void logIn(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id(Environment.required("LOGIN_USERNAME_ID")))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id(Environment.required("LOGIN_PASSWORD_ID")))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id(Environment.required("LOGIN_BUTTON_ID")))).click();
    }

    public boolean isSignedIn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id(Environment.required("LOGIN_SUCCESS_MARKER_ID")))).isDisplayed();
    }
}

