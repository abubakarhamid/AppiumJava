package Testcases.Android;

import PageObject.Android.HomePage;
import PageObject.Android.SettingPage;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class SettingPageTest {
    @Test(priority = 0, description = "verify Setting Page title")
    public static void verifySettingPageTitle(){
        try {
            HomePage.tapOnAccount();
            HomePage.tapOnSettingGearIcon();
            SettingPage.verifySettingPageTitle();
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
}
