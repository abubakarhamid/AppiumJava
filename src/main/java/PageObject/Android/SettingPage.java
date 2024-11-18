package PageObject.Android;

import General.GenericFunctions;
import General.envGlobals;
import org.testng.Assert;

public class SettingPage {
    public static void verifySettingPageTitle()
    {
        String actualPageTitle;
        actualPageTitle = GenericFunctions.getPageTitleText();
        Assert.assertEquals(actualPageTitle, envGlobals.expectedSettingPageTitleText);
    }
}
