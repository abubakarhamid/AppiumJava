package General;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class webDriverWaits {
    public static WebDriverWait wait = new WebDriverWait(webDriverFactory.getDriver(),Duration.ofSeconds(20));

    public static void visibilityOfElementLocated( By locator )
    {
       new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void visibilityOfElementLocatedWithTime( By locator ,Integer time)
    {
        new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void inVisibilityOfElementLocated( By locator )
    {
        new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static void inVisibilityOfElementLocatedWithTime( By locator ,Integer time)
    {
        new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(time)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static void visibilityOfElementLocated1( By locator )
    {
        new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void invisibilityOfElementLocated( By locator )
    {
        new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(50)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static void elementToBeClickableWithTime( By locator ,Integer time)
    {
        new WebDriverWait(webDriverFactory.getDriver(),Duration.ofSeconds(time)).until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void elementToBeClickable( By locator )
    {
        new WebDriverWait(webDriverFactory.getDriver(),Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static WebElement waitUntilElementIsClickable( WebElement webElement)
    {
        webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }
    public static void visibilityOf(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static boolean waitForElToBeAppeared(By locator) {
        try {
            webDriverFactory.getDriver().manage().timeouts()
                    .implicitlyWait(60000, TimeUnit.MILLISECONDS);

            WebDriverWait wait = new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(10), Duration.ofSeconds(10));

            return wait
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(element))).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
    public static void waitUntilElementVisible(By locator){
        new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfElementLocated(locator)); // wait for Loading panel to close
    }
    public static WebElement waitUntilElementNotDisplayed(final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriverFactory.getDriver(), Duration.ofSeconds(50));
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return true;
                }
                catch (NoSuchElementException e ) {
                    return false;
                }
            }
        };
        wait.until(elementIsDisplayed);
        return webElement;
    }
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleep250(){
        sleep(250);
    }
    public static void sleep500(){
        sleep(500);
    }
    public static void sleep1000(){
        sleep(1000);
    }
public static void sleep1(Integer time) throws InterruptedException {
        Thread.sleep(time);
}

    public static void visibilityOfElementLocatedFluently(By locator, int maxTime, int pollingTime) {
        Wait<AppiumDriver> fluentWait = new FluentWait<>(webDriverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(maxTime))  // Maximum wait time
                .pollingEvery(Duration.ofMillis(pollingTime))  // Polling interval
                .ignoring(NoSuchElementException.class)  // Ignore specific exceptions
                .ignoring(StaleElementReferenceException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
