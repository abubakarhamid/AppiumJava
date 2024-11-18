package utils;

import org.testng.Assert;



public class LogHelper {
    //private static Logger log = LogManager.getLogger("### "); //Concatenate project name here as well for logging (From Config File)
    //final Level VERBOSE = Level.forName("VERBOSE", 200);

    protected void logInfo(String comment) {
        System.out.println(comment + "\n");
    }

    public static void logStep(String comment) {
        System.out.println("STEP: " + comment + "\n");
    }

    protected void logVerifyTrue(Object expected, Object actual,String comment) {
        System.out.println(comment + "\n");
        Assert.assertEquals(expected, actual,comment);

    }

    protected void logVerifyContains(String actual, String expected,String comment) {
        System.out.println(comment + "\n");
        Assert.assertTrue(actual.contains(expected),comment);

    }

    protected void logVerifyTrue(Object[] actual, Object[] expected,String comment) {
        System.out.println(comment + "\n");
        for(int i=0;i<actual.length;i++)
            Assert.assertEquals(expected[i], actual[i],comment);

    }

    protected void logVerifyTrue( Object actual,String comment) {
        System.out.println(comment + "\n");
        Assert.assertEquals(true,actual,comment);
    }

    protected void logVerifyFalse(Object actual,String comment) {
        System.out.println(comment + "\n");
        Assert.assertEquals(false,actual,comment);
    }

    protected void logVerifyFalse(Object actual, Object expected,String comment) {
        System.out.println(comment+ "\n");
        Assert.assertFalse(actual.equals(expected),comment);
    }

    protected void logVerifyNull(Object actual,String comment) {
        System.out.println(comment+ "\n");
        Assert.assertNull(actual,comment);
    }

    protected void logVerifyNotNull(Object actual,String comment) {
        System.out.println(comment+ "\n");
        Assert.assertNotNull(actual,comment);
    }

}

