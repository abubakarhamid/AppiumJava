package Testcases.Android;

import General.baseTest;
import PageObject.Android.HomePage;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class HomePageTest extends baseTest {

    @Test(priority = 0, description = "verify the Search Button appear on top")
    public static void verifySearchButtonAppear(){
        try {
            HomePage.verifySearchButtonAppear();
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
    @Test(priority = 1, description = "verify the Home Page Banner appear")
    public static void verifyHomePageBannerAppear(){
        try {
            HomePage.verifyHomePageBannerAppear();
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
    @Test(priority = 2, description = "verify the Login banner appear on home")
    public static void verifyLoginAppear(){
        try {
            HomePage.verifyLoginBannerAppear();
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
}
