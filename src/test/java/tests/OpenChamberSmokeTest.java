package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainActivityPage;
import support.AppiumOptionsFactory;
import support.BaseAppiumTest;

public class OpenChamberSmokeTest extends BaseAppiumTest {
    @BeforeClass
    public void launchMainActivity() throws Exception {
        startSession(AppiumOptionsFactory.openChamber());
    }

    @Test
    public void mainActivityShowsOpenChamberScreen() {
        MainActivityPage page = new MainActivityPage(driver);
        Assert.assertTrue(page.isOpenChamberInForeground(),
                "The OpenChamber app should be in the foreground");

        String activity = page.currentActivity();
        Assert.assertTrue(activity != null && activity.trim().endsWith("MainActivity"),
                "Expected MainActivity to be open, but found: " + activity);
    }

}

