package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ConnectByAddressPage;
import pages.MainActivityPage;
import support.AppiumOptionsFactory;
import support.BaseAppiumTest;

public class ConnectByAddressOptionsTest extends BaseAppiumTest {

    @BeforeClass
    public void launchMainActivity() throws Exception {
        startSession(AppiumOptionsFactory.openChamber());
    }

    @Test
    public void verifyOptionsAvailableAfterClickingConnectByAddress() {
        Assert.assertTrue(new MainActivityPage(driver).isOpenChamberInForeground(),
                "The OpenChamber app should be in the foreground");
        ConnectByAddressPage page = new ConnectByAddressPage(driver);
        page.waitForHomeScreen();
        page.openConnectByAddress();
        page.waitForConnectOptions();

        Assert.assertTrue(page.showsServerUrlField(), "Server URL field should be displayed");
        Assert.assertTrue(page.showsNameField(), "Name field should be displayed");
        Assert.assertTrue(page.showsClientTokenField(), "Client token field should be displayed");
        Assert.assertTrue(page.showsConnectOption(), "Connect option should be displayed");
    }
}

