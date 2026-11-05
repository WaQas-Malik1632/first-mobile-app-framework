package support;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;

import java.net.URI;

/** Shared Appium session lifecycle for UI tests. */
public abstract class BaseAppiumTest {
    protected AndroidDriver driver;

    protected final AndroidDriver startSession(UiAutomator2Options options) throws Exception {
        String serverUrl = Environment.orDefault("APPIUM_SERVER_URL", "http://127.0.0.1:4723");
        driver = new AndroidDriver(URI.create(serverUrl).toURL(), options);
        return driver;
    }

    @AfterClass(alwaysRun = true)
    public void stopSession() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}

