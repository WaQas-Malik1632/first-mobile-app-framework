package tests;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import support.BaseAppiumTest;
import support.Environment;

public class LoginPageTest extends BaseAppiumTest {

    @BeforeClass
    public void startApp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(Environment.orDefault("ANDROID_DEVICE_NAME", "Android Emulator"));

        String appPath = System.getenv("ANDROID_APP_PATH");
        if (appPath != null && !appPath.isBlank()) {
            String normalizedAppPath = appPath.toLowerCase();
            if (!normalizedAppPath.endsWith(".apk") && !normalizedAppPath.endsWith(".apks")) {
                throw new IllegalArgumentException(
                        "ANDROID_APP_PATH must point to an .apk or .apks file. "
                                + "Convert an .aab with bundletool before running this test.");
            }
            options.setApp(appPath);
        } else {
            options.setAppPackage(Environment.required("ANDROID_APP_PACKAGE"));
            options.setAppActivity(Environment.required("ANDROID_APP_ACTIVITY"));
        }

        startSession(options);
    }

    @Test
    public void userCanLogIn() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(Environment.required("APP_TEST_USERNAME"), Environment.required("APP_TEST_PASSWORD"));
        Assert.assertTrue(loginPage.isSignedIn(), "Expected the signed-in screen to appear");
    }
}

