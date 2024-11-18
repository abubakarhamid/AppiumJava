package PageObject.Android;

import General.GenericFunctions;
import org.openqa.selenium.By;
import org.testng.Assert;

import static Config.configProperties.appPackageValue;

public class HomePage {
    public static By homePageLogin=By.id(appPackageValue+":id/hp_login_button");
    public static By searchButton=By.id(appPackageValue+":id/laz_homepage_search_btn_text");
    public static By homePageBanner=By.id(appPackageValue+":id/laz_homepage_search_btn_text");

    public static By account = By.xpath("//*[@text='Account']");
    public static By settingGearIcon = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]//android.widget.ImageView[2]");

    public static void verifySearchButtonAppear()
    {
        Assert.assertTrue(GenericFunctions.isDisplayed(searchButton));
    }
    public static void verifyLoginBannerAppear()
    {
        Assert.assertTrue(GenericFunctions.isDisplayed(homePageLogin));
    }
    public static  void verifyHomePageBannerAppear()
    {
        Assert.assertTrue(GenericFunctions.isDisplayed(homePageBanner));
    }

    public static void tapOnAccount()
    {
        GenericFunctions.tap(account);
    }
    public static void tapOnSettingGearIcon()
    {
        GenericFunctions.tap(settingGearIcon);
    }

}
