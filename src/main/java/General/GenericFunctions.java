package General;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Config.configProperties.appPackageValue;
import static General.webDriverFactory.driver;
import static java.time.Duration.ofSeconds;

public class GenericFunctions extends baseTest {

    public static By pageTitle = By.xpath("//android.view.ViewGroup[@resource-id='"+appPackageValue+":id/toolbar']//android.widget.TextView");

    public static By textMessage = By.id(appPackageValue + "txtMessage");

    public static void clearInputField(WebElement e) {
        e.clear();
    }
    public static void clearInputField(By locator) {
        WebElement e = webDriverFactory.getDriver().findElement(locator);
        e.clear();
    }
    public static void clearInput(By locator) {
        WebElement e = webDriverFactory.getDriver().findElement(locator);
        e.clear();
    }
    //Fetch Day
    public static String getDayFromDate() {

        Date now = new Date();

//        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
//        System.out.println(simpleDateformat.format(now));

        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        return simpleDateformat.format(now);

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // the day of the week in numerical format

    }

    public static void switchContext(String context) {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        if (driver instanceof AndroidDriver) {
            androidDriver = (AndroidDriver) driver;
            System.out.println("Before Switching:" + androidDriver.getContext());
            Set<String> con = androidDriver.getContextHandles();
            for (String c : con) {
                System.out.println("Available context:" + c);
                if (c.contains(context)) {
                    androidDriver.context(c);
                    break;
                }
                System.out.println("After Switching:" + androidDriver.getContext());
            }
        }
    }

    public void installAppAndroid(String app) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.installApp(app);
        }
    }

    public static void activateAppAndroid(String bundleId) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.activateApp(bundleId);
        }
    }


    public static void activateAppAndroid(String bundleId, String appPath) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;

            if (!androidDriver.isAppInstalled(bundleId)) {
                // If the app is not installed, install it
                androidDriver.installApp(appPath);
            }

            // Activate the app
            androidDriver.activateApp(bundleId);
        }
    }


    public static void closeApp() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.close();
        }
    }
    public static void killApp(String bundleId) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.terminateApp(bundleId);
        }
    }


    //Set device time to any date
    public static void setEmulatorTime(String command) throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder();

        // -- Linux --

        // Run a shell command
        processBuilder.command("bash", "-c", command);

        // Run a shell script
        //processBuilder.command("path/to/hello.sh");

        // -- Windows --

        // Run a command
//        processBuilder.command("cmd.exe", "/c", command);

        // Run a bat file
        //processBuilder.command("C:\\Users\\mkyong\\hello.bat");

        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success! Time changed to" + " " + envGlobals.dateToSet);
                System.out.println(output);
//                System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Generate Random String
    public static String generateRandomString(int length) {
        String name = RandomStringUtils.randomAlphabetic(length);
        String first_letter = name.substring(0, 1).toUpperCase();
        String other_letters = name.substring(1).toLowerCase();
        String finalname = "dummyKey" + first_letter + other_letters;
        return finalname;
    }

    public static String getText(By locator) {
        WebElement el = webDriverFactory.getDriver().findElement(locator);
        String text = el.getText();
        return text;
    }

    public static void typeInto(By locator, String text) {
        WebElement el = webDriverFactory.getDriver().findElement(locator);
        el.sendKeys(text);

    }

    public static void verifyErrorMessageIconAppear(By locator)
    {

        Assert.assertTrue(GenericFunctions.isDisplayed(locator));

    }
    private static void sleep(int millis) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < millis) {
            // Busy-wait loop for delay
        }
    }

    public static void tap(By locator){
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            webDriverWaits.waitForElToBeAppeared(locator);
            WebElement el = webDriverFactory.getDriver().findElement(locator);
            el.click();
//            logger.log(LogStatus.INFO,methodName);
    }
    public static void actionTap(By element) {
        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        action.tap(TapOptions.tapOptions().withElement(ElementOption.element(driver.findElement(element)))).perform();
    }

    public static boolean isEnabled(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
            return button.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isClickable(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getTextByLocator(By locator) {
        WebDriver driver = webDriverFactory.getDriver();
        WebElement element = driver.findElement(locator);
        return element.getText();
    }
    public static void moveBackUntilTextDisplayed(String text, By locator) {
        while (true) {
            try {
                WebElement element = driver.findElement(locator);
                if (element.getText().contains(text) && element.isDisplayed()) {
                    break; // Stop navigating back if the element with the specified text is displayed
                }
            } catch (Exception e) {
                // Element not found or not displayed, continue navigating back
            }
            driver.navigate().back();
        }
    }
    public static void moveBackUntil(By locator)
    {
        while (true) {
            try {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    break; // Stop navigating back if the element with the specified text is displayed
                }
            } catch (Exception e) {
                // Element not found or not displayed, continue navigating back
            }
            driver.navigate().back();
        }
    }
        public static boolean isDisplayed (By locator) {
            try {
                WebElement el = webDriverFactory.getDriver().findElement(locator);
                return el.isDisplayed();
            } catch (NoSuchElementException e) {
                throw new AssertionError("Element not found: " + locator, e);
            }
        }

    public static boolean isNotDisplayed(By locator)
        {

            return webDriverFactory.getDriver().findElements(locator).isEmpty();
        }


        public static String getPageTitleText () {
            webDriverWaits.visibilityOfElementLocated(pageTitle);
            return getText(pageTitle);
        }

        public static String getPageTitleText (By locator){
            webDriverWaits.visibilityOfElementLocated(locator);
            return getText(locator);
        }

        public static void timevisibiletillelement(By locator)
        {
            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5000));
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.invisibilityOf(popup));
//            wait.until(ExpectedConditions.invisibilityOf(By.xpath("locator"));
        }

        public static String getTextMessage () {
            webDriverWaits.visibilityOfElementLocated(textMessage);
            return getText(textMessage);
        }

        public static void allowActions (String keytosend){
            Actions action = new Actions(webDriverFactory.getDriver());
            action.sendKeys(keytosend).build().perform();
        }
        //remove words and comma and spaces from string
        public static String removeWordsAndSpaces (String inputString, Set < String > wordsToRemove){
            // Remove specific words from the string
            for (String word : wordsToRemove) {
                inputString = inputString.replaceAll("\\b" + word + "\\b", "");
            }

            // Remove spaces and commas from the modified string
            return inputString.replaceAll("[\\s,]", "");
        }
        //Check Substring
        public static boolean isSubset (String string1, String string2){
            // Convert strings to lowercase for case-insensitive comparison
            string1 = string1.toLowerCase();
            string2 = string2.toLowerCase();
            System.out.println("+++++++++++++++++String 1" + string1 + "+++++++++++++++++++");
            System.out.println("+++++++++++++++++String 2" + string2 + "+++++++++++++++++++");

            // Check if string2 is a subset of string1
            return string2.contains(string1);
        }
        public static void verifyRadioButtonsText (By radioButtonLocator, List < String > expectedTexts){
            // Get all radio buttons
            List<WebElement> radioButtons = driver.findElements(radioButtonLocator);

            // Verify text for each radio button
            for (int i = 0; i < radioButtons.size(); i++) {
                WebElement radioButton = radioButtons.get(i);
                String actualText = radioButton.getText();
                String expectedText = expectedTexts.get(i);

                Assert.assertEquals(expectedText, actualText);
                // Compare actual and expected text
                if (actualText.equals(expectedText)) {
                    System.out.println("Radio button " + (i + 1) + " text verification passed. Expected: " + expectedText + ", Actual: " + actualText);
                } else {
                    System.out.println("Radio button " + (i + 1) + " text verification failed. Expected: " + expectedText + ", Actual: " + actualText);
                    // You can add your handling for the failure case
                }
            }
        }
    public static void turnOffLocation() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.toggleLocationServices();
            System.out.println("Location turned off");
        }
    }

    public static void turnOnLocation() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.toggleLocationServices();
            System.out.println("Location turned on");
        }
    }

    public static void turnOffWifi() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.toggleWifi();
            System.out.println("WiFi turned off");
        }
    }

    public static void turnOnWifi() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.toggleWifi();
            System.out.println("WiFi turned on");
        }
    }

    public static void verifyNotificationAppear(String expectedNotificationText)
    {
//        By notificationLocator = By.xpath("//android.widget.TextView[@text='"+expectedNotificationText+"']");
        By notificationLocator = By.xpath("//*[contains(@text, '"+expectedNotificationText+"')]");
        webDriverWaits.waitForElToBeAppeared(notificationLocator);
        Assert.assertTrue(GenericFunctions.isDisplayed(notificationLocator),expectedNotificationText+ "Not Appeared");
    }
    public static WebElement createDynamicLocatorWithText(String text)
    {
        return webDriverFactory.getDriver().findElement(By.xpath("//*[@text='" + text + "']"));
    }
    public static double extractValueFromString(String text) {
        // Split the string by space and take the second part
        String[] parts = text.split(" ");
        if (parts.length > 1) {
            try {
                // Convert the second part to a double
                return Double.parseDouble(parts[1]);
            } catch (NumberFormatException e) {
                System.err.println("Unable to parse the value to double.");
            }
        }
        return 0.0; // Return 0.0 or handle as per your requirement if extraction fails
    }
    public static float getFareFromLocator(By estimatedFare) {

        WebElement fareElement = webDriverFactory.getDriver().findElement(estimatedFare);
        String fareText = fareElement.getText();
        fareText = fareText.replaceAll("[^\\d.]", "");
        return Float.parseFloat(fareText);
    }


    public static WebElement findElementWithTimeout(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (WebDriverException e) {
            return null;
        }
    }
    public static double getNumericValue(String str) {
        Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        } else {
            throw new NumberFormatException("No numeric value found in the string");
        }
    }
    public static void hideKeyboard()
    {
        if (driver instanceof AndroidDriver) {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.hideKeyboard();
    }
        if (driver instanceof IOSDriver) {
            IOSDriver iosDriver = (IOSDriver) driver;
            iosDriver.hideKeyboard();
        }

    }
    public static void openAndroidNotificationTray()
    {
        if (driver instanceof AndroidDriver) {
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.openNotifications();
        }
    }

    public static void performTwoFingerSwipe(double x1, double x2, double startY, double endY, boolean isUp) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;

        int startYCoord = (int) (height * startY);
        int endYCoord = (int) (height * endY);
        int x1Coord = (int) (width * x1);
        int x2Coord = (int) (width * x2);

        // Create PointerInput for each finger
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        // Create sequences for each finger
        Sequence sequence1 = new Sequence(finger1, 1);
        Sequence sequence2 = new Sequence(finger2, 1);

        // Define the swipe action for the first finger
        sequence1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x1Coord, isUp ? endYCoord : startYCoord));
        sequence1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), x1Coord, isUp ? startYCoord : endYCoord));
        sequence1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Define the swipe action for the second finger
        sequence2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x2Coord, isUp ? endYCoord : startYCoord));
        sequence2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), x2Coord, isUp ? startYCoord : endYCoord));
        sequence2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the multi-touch action
        driver.perform(Arrays.asList(sequence1, sequence2));
    }
    public static void oneFingerScrollByPixelsDownwards(Integer pixels) {
        // Get the window size
        Dimension windowSize = driver.manage().window().getSize();
        int startX = windowSize.getWidth() / 2;
        int startY = windowSize.getHeight() / 2;

        // Calculate end coordinates based on the number of pixels to scroll
        int endY = startY + pixels;

        // Create a PointerInput instance for touch actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create a list of sequences for actions
        List<Sequence> sequences = new ArrayList<>();

        // Define the touch action sequence
        Sequence swipeSequence = new Sequence(finger, 1);
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipeSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Add the sequence to the list
        sequences.add(swipeSequence);
        driver.perform(sequences);
    }

    public static void oneFingerScrollByPixelsUpward(Integer pixels) {
        // Get the window size
        Dimension windowSize = driver.manage().window().getSize();
        int startX = windowSize.getWidth() / 2;
        int startY = windowSize.getHeight() / 2;

        // Calculate end coordinates based on the number of pixels to scroll
        int endY = startY - pixels;

        // Create a PointerInput instance for touch actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create a list of sequences for actions
        List<Sequence> sequences = new ArrayList<>();

        // Define the touch action sequence
        Sequence swipeSequence = new Sequence(finger, 1);
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipeSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Add the sequence to the list
        sequences.add(swipeSequence);
        driver.perform(sequences);

    }

    private static boolean isElementVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void oneFingerScrollByPixels(By locator ,Integer pixels) {
        // Find the element based on locator and locator type
        WebElement element = driver.findElement(locator);

        // Calculate the center coordinates of the provided element
        int startX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int startY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        // Calculate end coordinates based on the number of pixels to scroll
        // To scroll upwards, subtract pixels from startY
        int endY = startY - pixels;

        // Create a PointerInput instance for touch actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create a list of sequences for actions
        List<Sequence> sequences = new ArrayList<>();

        // Define the touch action sequence
        Sequence swipeSequence = new Sequence(finger, 1);
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipeSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Add the sequence to the list
        sequences.add(swipeSequence);

        driver.perform(sequences);
    }


    public static void oneFingerScrollByPixelsDownwards(By locator,Integer pixels) {
        // Find the element based on locator and locator type
        WebElement element = driver.findElement(locator);

        // Calculate the center coordinates of the provided element
        int startX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int startY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        // Calculate end coordinates based on the number of pixels to scroll
        int endY = startY + pixels;

        // Create a PointerInput instance for touch actions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create a list of sequences for actions
        List<Sequence> sequences = new ArrayList<>();

        // Define the touch action sequence
        Sequence swipeSequence = new Sequence(finger, 1);
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipeSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Add the sequence to the list
        sequences.add(swipeSequence);
        driver.perform(sequences);

    }
    public static void tapWithActionClass(By locator) {
        webDriverWaits.waitForElToBeAppeared(locator);
        WebElement el = webDriverFactory.getDriver().findElement(locator);

        // Initialize the Actions class with the driver
        Actions actions = new Actions(webDriverFactory.getDriver());

        // Perform the click action using Actions class
        actions.moveToElement(el).click().perform();
    }
}


