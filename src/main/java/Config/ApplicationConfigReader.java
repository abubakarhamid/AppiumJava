package Config;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.Classpath("ApplicationConfig.properties")

public class ApplicationConfigReader {

    public ApplicationConfigReader() {
        PropertyLoader.newInstance().populate(this);
    }

    @Property(value = "platformName")
    private String platformName;

    @Property(value = "platformVersion")
    private String platformVersion;

    @Property(value = "deviceName")
    private String deviceName;
    @Property(value = "udId")
    private String udId;
    @Property(value = "noReset")
    private String noReset;
    @Property(value = "fullReset")
    private String fullReset;
    @Property(value = "automationName")
    private String automationName;
    @Property(value = "autoAcceptAlert")
    private String autoAcceptAlert;
    @Property(value = "Environment")
    private String Environment;

    @Property(value = "dbUrl")
    private String dbUrl;

    @Property(value = "dbUserName")
    private String dbUserName;

    @Property(value = "dbPassword")
    private String dbPassword;

    @Property(value = "appPath")
    private String appPath;

    @Property(value = "packageName")
    private String packageName;

    @Property(value = "userName")
    private String userName;

    @Property(value = "userPassword")
    private String password;

    @Property(value = "appActivity")
    private String appActivity;

    @Property(value = "appPackage")
    private String appPackage;

    @Property(value = "appPackageValue")
    private String appPackageValue;

    @Property(value = "appVersion")
    private String appVersion;
    @Property(value = "app")
    private String app;

    @Property(value = "npmPath")
    private String npmPath;

    @Property(value = "language")
    private String language;


    public String getappActivity() {
        return appActivity;
    }
    public String getappPackage() {
        return appPackage;
    }

    public String getAppVersion() {
        return appVersion;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getAutomationName() {
        return automationName;
    }

    public String getAppPath() {
        return appPath;
    }

    public String getEnvironment() {
        return Environment;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getPackageName() {
        return packageName;
    }

    public String appPackageValue() {
        return appPackageValue;
    }

    public String getUdId() {
        return udId;
    }
    public String noReset() {
        return noReset;
    }
    public String fullReset() {
        return fullReset;
    }
    public String getNpmPath() {return npmPath;}
    public String getLanguage() {return language;}
}
