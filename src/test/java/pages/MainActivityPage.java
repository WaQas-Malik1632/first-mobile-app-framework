package pages;

import io.appium.java_client.android.AndroidDriver;

/** State queries for the OpenChamber main activity. */
public class MainActivityPage {
    private final AndroidDriver driver;

    public MainActivityPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isOpenChamberInForeground() {
        return "com.openchamber.app".equals(driver.getCurrentPackage());
    }

    public String currentActivity() {
        return driver.currentActivity();
    }
}

