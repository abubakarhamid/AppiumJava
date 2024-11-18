package General;

import Config.configProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Config.configProperties.platformName;
public class webDriverFactory {
    static AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
    public static AppiumDriver driver;
    static WebDriverWait wait;
   // public static AppiumDriverLocalService service;
    public static AppiumDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized");
        }
    }
    public static AppiumDriver startDriver() throws IOException {
        service = new AppiumServiceBuilder().withAppiumJS(new File(configProperties.npmPath))
                .withIPAddress("127.0.0.1").usingPort(4723). withTimeout(Duration.ofSeconds(200)).build();

        service.start();
        switch (platformName.toLowerCase())
        {
            case "android":
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilitiesGenerator.getAndroidCapabilities());
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                break;

            case "ios":
//                driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), capabilitiesGenerator.getIOSCapabilities());
//                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
    public static String getDevice()
    {
        webDriverWaits.sleep250();
        return configProperties.platformName;
    }
    public static void finishDriver() throws IOException {
        if (driver != null)
        {
            webDriverWaits.sleep1000();
            driver.quit();
            driver = null;
            service.stop();
        }
    }
}
