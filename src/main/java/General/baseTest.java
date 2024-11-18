package General;

//import Testcases.Android.Passenger.loginTest;

import Config.configProperties;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.LogHelper;
//import utils.TestData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;


public class baseTest extends LogHelper {
public static boolean testFailed=false;
    static ExtentTest logger;
    @BeforeSuite()
    public void startReport() throws IOException, InterruptedException {
        String language = configProperties.language;
//        TestData.loadTestData(configProperties.app,language);
        MainCall.startReport();
        webDriverFactory.startDriver();
//        if(Objects.equals(configProperties.app, "passenger"))
//        {
//        OnboardingTest.applicationLaunch();
//        } else if (Objects.equals(configProperties.app, "driver"))
//        {
//            webDriverWaits.sleep(5000);
////            GenericFunctions.closeApp();
// //           GenericFunctions.activateAppAndroid(configProperties.driverAppBundleID);
//            OnBoardingTest.driverAppLaunch();
//        }
    }

    @BeforeMethod()
    public void beforeTest(Method method) throws IOException {
        //webDriverFactory.startDriver();
        logger = MainCall.getExtentReport().startTest(method.getName(), "");
        logger.setStartedTime(getTime());
    }

    @AfterMethod()
    public void QuitDriver(ITestResult result) throws IOException {

            if (result.getStatus() == ITestResult.FAILURE || testFailed)
            {
                logger.log(LogStatus.FAIL, "Test Case Failed reason is: " + result.getThrowable());
             //   webDriverWaits.sleep1000();
                logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshots.takeScreenshot(result.getMethod().getMethodName())));
                testFailed=false;

            } else if (result.getStatus() == ITestResult.SKIP) {
                logger.log(LogStatus.SKIP, "Test Case Skipped is: " + result.getName());
            } else {
                logger.log(LogStatus.PASS, result.getMethod().getMethodName() + " is Passed");
            }
            logger.setEndedTime(getTime());
            MainCall.getExtentReport().endTest(logger);
            MainCall.getExtentReport().flush();
        }

    private Date getTime()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    @AfterSuite()
    public void endReport() throws IOException, MessagingException {
      //  emailSending.sendTestExecutionReport();
        webDriverFactory.finishDriver();
    }

   public static void clickEnterFromSearchKeyBoardAndroid()
   {
       ((AndroidDriver) webDriverFactory.getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
   }
   public static void failTest()
   {
       testFailed=true;
   }
   public void addLogsIntoReport(String log)
   {
       logger.log(LogStatus.INFO,log);
   }

}


