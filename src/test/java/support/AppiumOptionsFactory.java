package support;

import io.appium.java_client.android.options.UiAutomator2Options;

public final class AppiumOptionsFactory {
    private static final String OPEN_CHAMBER_PACKAGE = "com.openchamber.app";

    private AppiumOptionsFactory() {}

    public static UiAutomator2Options openChamber() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(Environment.orDefault("ANDROID_DEVICE_NAME", "Android Emulator"))
                .setAppPackage(OPEN_CHAMBER_PACKAGE)
                .setAppActivity(".MainActivity")
                .setNoReset(true);

        String udid = System.getenv("ANDROID_UDID");
        if (udid != null && !udid.isBlank()) {
            options.setUdid(udid);
        }
        options.setCapability("appium:forceAppLaunch", true);
        options.setCapability("appium:shouldTerminateApp", false);
        return options;
    }
}

