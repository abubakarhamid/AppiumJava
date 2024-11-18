package General;

import Config.configProperties;
import io.appium.java_client.android.options.UiAutomator2Options;

public class capabilitiesGenerator {

    public static UiAutomator2Options getAndroidCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName(configProperties.platformName)
                .setPlatformVersion(configProperties.platformVersion)
                .setAutomationName(configProperties.automationName)
                .setDeviceName(configProperties.deviceName)
                .setAppActivity(configProperties.appActivity).setNoReset(true)
                .setAutoGrantPermissions(true)
            .setApp(configProperties.appPath);
        return options;
    }
}
