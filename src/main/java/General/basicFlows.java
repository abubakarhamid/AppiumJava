package General;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

import static General.webDriverFactory.driver;
import static General.webDriverFactory.getDriver;
import static java.time.Duration.ofMillis;

public class basicFlows {
    public static Dimension size;
    public static int startx = 0;
    public static int endx = 0;
    public static int starty = 0;
    public static int endy = 0;
    public void swipeHorizontal(String swipe){
        size = driver.manage().window().getSize();
        //Find swipe start and end point from screen's with and height.
        //Find startx point which is at right side of screen.

        switch (swipe){
            case "Left":
            case "left":
                startx = (int) (size.width * 0.90);
                //Find endx point which is at left side of screen.
                endx = (int) (size.width * 0.05);
                //Find vertical point where you wants to swipe. It is in middle of screen height.
                starty = size.height / 2;

                //Swipe from Right to Left.
                new TouchAction((PerformsTouchActions) getDriver())
                        .press(PointOption.point(startx, starty))
                        .waitAction(WaitOptions.waitOptions(ofMillis(3000)))
                        .moveTo(PointOption.point(endx, starty))
                        .release().perform();
                break;
            case "Right":
            case "right":
                 starty = size.height / 2;
                 startx = (int) (size.width * 0.05);
                 endx = (int) (size.width * 0.90);
                //Swipe from Left to Right.
                new TouchAction((PerformsTouchActions) getDriver())
                        .press(PointOption.point(startx, starty))
                        .waitAction(WaitOptions.waitOptions(ofMillis(3000)))
                        .moveTo(PointOption.point(endx, starty))
                        .release().perform();
                //driver.swipe(endx, starty, startx, starty, 3000);
                break;
        }
    }

    public void swipeVertical(String swipe){
        size = driver.manage().window().getSize();

        //Find swipe start and end point from screen's with and height.
        //Find starty point which is at bottom side of screen.
        int starty = (int) (size.height * 0.80);
        //Find endy point which is at top side of screen.
        int endy = (int) (size.height * 0.20);
        //Find horizontal point where you wants to swipe. It is in middle of screen width.
       int startx = size.width / 2;

        switch (swipe){
            case "Up":
            case "up":
                //Swipe from Bottom to Top.
                new TouchAction((PerformsTouchActions) getDriver())
                        .press(PointOption.point(startx, starty))
                        .waitAction(WaitOptions.waitOptions(ofMillis(3000)))
                        .moveTo(PointOption.point(startx, endy))
                        .release().perform();
                //driver.swipe(startx, starty, startx, endy, 3000);
//                }
                break;
            case "Down":
            case "down":
                //Swipe from Top to Bottom.
                new TouchAction((PerformsTouchActions) getDriver())
                        .press(PointOption.point(startx, endy))
                        .waitAction(WaitOptions.waitOptions(ofMillis(3000)))
                        .moveTo(PointOption.point(startx, starty))
                        .release().perform();

                //driver.swipe(startx, endy, startx, starty, 3000);
                break;
        }
    }
    public void swipeVerticalCustom(String swipe,double start, double end){
        size = driver.manage().window().getSize();

        //Find swipe start and end point from screen's with and height.
        //Find starty point which is at bottom side of screen.
        int starty = (int) (size.height * start);
        //Find endy point which is at top side of screen.
        int endy = (int) (size.height * end);
        //Find horizontal point where you wants to swipe. It is in middle of screen width.
        int startx = size.width / 2;
        switch (swipe){
            case "Up":
            case "up":
                //Swipe from Bottom to Top.
                new TouchAction((PerformsTouchActions) getDriver())
                        .press(PointOption.point(startx, starty))
                        .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                        .moveTo(PointOption.point(startx, endy))
                        .release().perform();
                //driver.swipe(startx, starty, startx, endy, 3000);
//                }
                break;
            case "Down":
            case "down":
                //Swipe from Top to Bottom.
                new TouchAction((PerformsTouchActions) getDriver())
                        .press(PointOption.point(startx, endy))
                        .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                        .moveTo(PointOption.point(startx, starty))
                        .release().perform();
                //driver.swipe(startx, endy, startx, starty, 3000);
                break;
        }
    }

    public void moveBack(){
        driver.navigate().back();
    }
    public void moveBackMultiple(Integer count){
        for(int i=0;i<count;i++) {
            driver.navigate().back();
            webDriverWaits.sleep(1000);
        }
    }
    public void swipe(){
        Dimension size=webDriverFactory.getDriver().manage().window().getSize();
        int width= size.width/2;
        int startPoint=(int)(size.getHeight() * 0.70);
        int endPoint=(int)(size.getHeight() * 0.20);
        int duration=6000;
        new TouchAction((PerformsTouchActions) getDriver())
                .press(PointOption.point(width, startPoint))
                .waitAction(WaitOptions.waitOptions(ofMillis(duration)))
                .moveTo(PointOption.point(width, endPoint))
                .release().perform();
        //webDriverFactory.getDriver().swipe(width, startPoint, width, endPoint, duration);
    }
    public void swipeNew(Point start, Point end, Duration duration)
    {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence swipe = new Sequence(input,0);
        swipe.addAction(input.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),start.x,start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration,PointerInput.Origin.viewport(),end.x,end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    public void swipeLess(){
        Dimension size=webDriverFactory.getDriver().manage().window().getSize();
        int width= size.width/2;
        int startPoint=(int)(size.getHeight() * 0.70);
        int endPoint=(int)(size.getHeight() * 0.20);
        int duration=2000;
        new TouchAction((PerformsTouchActions) getDriver())
                .press(PointOption.point(width, startPoint))
                .waitAction(WaitOptions.waitOptions(ofMillis(duration)))
                .moveTo(PointOption.point(width, endPoint))
                .release().perform();
        //webDriverFactory.getDriver().swipe(width, startPoint, width, endPoint, duration);
    }

    public void swipeFullUpDown(int x1, int y1, int x2, int y2) {
        TouchAction action = new TouchAction((PerformsTouchActions) driver);
                action.press(PointOption.point(945, 308))
                .moveTo(PointOption.point(22, 1013))
                .release()
                .perform();
    }

    //Swipe by elements
    public void swipeByElements (WebElement startElement, WebElement endElement)
    {

        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        new TouchAction((PerformsTouchActions) getDriver())
                .press(PointOption.point(startX,startY))
                .moveTo(PointOption.point(endX, endY))
                        .release()
                        .perform();
    }

    public static void scrollToElementIOS(String elementText)
    {
        JavascriptExecutor js=webDriverFactory.getDriver();
        HashMap scrollObject=new HashMap<>();
        scrollObject.put("predicateString","value== '"+elementText+ "'");
        scrollObject.put("direction","down");
        js.executeScript("mobile:scroll",scrollObject);
    }
    public static void scrollToElementIOSUp(String elementText)
    {
        JavascriptExecutor js=webDriverFactory.getDriver();
        HashMap scrollObject=new HashMap<>();
        scrollObject.put("predicateString","value== '"+elementText+ "'");
        scrollObject.put("direction","up");
        js.executeScript("mobile:scroll",scrollObject);
    }
    public static void scrollUp()
    {
        webDriverFactory.getDriver().executeScript("experitest:client.swipe(\"Up\", 0, 1000)");
    }

    public static void scrollDown()
    {
        webDriverFactory.getDriver().executeScript("experitest:client.swipe(\"Down\", 0, 1000)");
    }

    public void swipeHorizontal(double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * finalPercentage);
        new TouchAction((PerformsTouchActions) getDriver()).press(PointOption.point(startPoint, anchor)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endPoint, anchor)).release().perform();
    }

    public void leftRightSwipe(int timeduration) {
        // duration should be in milliseconds
        size = driver.manage().window().getSize();
        System.out.println(size);
        startx = 800;
        endx = 0;
        starty = 2000;
        System.out.println("Start swipe operation");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startx, starty));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(timeduration),
                PointerInput.Origin.viewport(), endx, starty));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Arrays.asList(swipe));
    }
    public void upDownSwipe(int timeduration) {
        // duration should be in milliseconds
        size = driver.manage().window().getSize();
        System.out.println(size);
        startx = size.width / 2;  // Starting x coordinate at the middle of the screen
        starty = (int) (size.height * 0.8);  // Starting y coordinate near the bottom of the screen
        endy = (int) (size.height * 0.2);  // Ending y coordinate near the top of the screen
        System.out.println("Start vertical swipe operation");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startx, starty));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(timeduration),
                PointerInput.Origin.viewport(), startx, endy));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

}



