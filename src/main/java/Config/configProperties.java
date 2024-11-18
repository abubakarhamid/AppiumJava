package Config;


public class configProperties {

    public static ApplicationConfigReader appConfig = new ApplicationConfigReader();

    public static String platformName = appConfig.getPlatformName();
    public static String platformVersion = appConfig.getPlatformVersion();
    public static String deviceName = appConfig.getDeviceName();
    public static String automationName = appConfig.getAutomationName();
    public static String appPath = appConfig.getAppPath();
    public static String userName = appConfig.getUserName();
    public static String password = appConfig.getPassword();
    public static String appActivity = appConfig.getappActivity();
    public static String appPackage = appConfig.getappPackage();
    public static String appPackageValue = appConfig.appPackageValue();
    public static String Environment = appConfig.getEnvironment();
    public static String dbUrl = appConfig.getDbUrl();
    public static String dbUserName = appConfig.getDbUserName();
    public static String dbPassword = appConfig.getDbPassword();
    public static String udID = appConfig.getUdId();
    public static String noReset = appConfig.noReset();
    public static String fullReset = appConfig.fullReset();

    public static String packageName = appConfig.getPackageName();

    public static String npmPath = appConfig.getNpmPath();
    public static String language = appConfig.getLanguage();
}
