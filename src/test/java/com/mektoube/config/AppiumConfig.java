package com.mektoube.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class AppiumConfig {
//    private static final String APPIUM_PATH = System.getenv("APPIUM_PATH");
     private static final String APPIUM_PATH = "/usr/local/lib/node_modules/appium/build/lib/main.js";

    private static final String WINDOWS_APPIUM_PATH = "C:\\Users\\admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    private static final String IOS_CONFIG_PATH = "src/configs/ios-config.properties";
    private static final String ANDROID_CONFIG_PATH = "src/configs/android-config.properties";
    private static AppiumConfig instance = null;
    private static AppiumDriverLocalService server;
    private static MobilePlatform OS;
    public static AppiumDriver<MobileElement> driver;

    private AppiumConfig(final MobilePlatform OS) {
        AppiumConfig.OS = OS;
    }

    public static synchronized AppiumConfig getInstanceFor(MobilePlatform OS){
        if (instance == null)
            instance = new AppiumConfig(OS);
        return instance;
    }

    public static MobilePlatform getOS() {
        return OS;
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }


    public void startServer() {
        String os = System.getProperty("os.name");
        System.out.println("Operating System: " + os);
        if (os.equals("Linux") || os.equals("Mac OS X")) {
            server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .withAppiumJS(new File(APPIUM_PATH))
                    .usingPort(4723).withIPAddress("127.0.0.1"));
            // server.start();
            System.out.println("APPIUM SERVER has started successfully at " + server.getUrl());
        } else if (os.equals("Windows 10")) {
            server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .withAppiumJS(new File(WINDOWS_APPIUM_PATH))
                    .usingPort(4723).withIPAddress("127.0.0.1"));
            server.start();
            System.out.println("APPIUM SERVER has started successfully at " + server.getUrl());
        }
    }

    public void startSession() throws IOException {
        String pathName;
        if (OS == MobilePlatform.IOS) {
            pathName = IOS_CONFIG_PATH;
        } else {
            pathName = ANDROID_CONFIG_PATH;
        }
        // create file input stream object for the properties file
        FileInputStream fis = new FileInputStream(pathName);
        // create Properties class object to access properties file
        Properties prop = new Properties();
        // load the properties file
        prop.load(fis);

        // Set capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        @SuppressWarnings("unchecked")
        Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
        while (enums.hasMoreElements()) {
            String capabilityName = enums.nextElement();
            String value = prop.getProperty(capabilityName);
            capabilities.setCapability(capabilityName, value);
        }
        if (OS == MobilePlatform.IOS) {
            capabilities.setCapability("appium:usePreinstalledWDA", true);
            driver =  new IOSDriver<MobileElement>(server.getUrl(), capabilities);
        } else {
            driver = new AndroidDriver<MobileElement>(server.getUrl(), capabilities);
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // wait until by pass splash screen on Android
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id("android:id/content"))).click();
        }
        System.out.println("New Appium session has set up successfully");
    }

    public void stopServer() {
        server.stop();
    }
}
