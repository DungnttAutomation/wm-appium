package com.mektoube.base;

import com.mektoube.config.AppiumConfig;
import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mektoube.config.AppiumConfig.driver;

public class Hook {
//    private final AppiumConfig config = AppiumConfig.getInstanceFor(MobilePlatform.valueOf(System.getenv("MOBILE_PLATFORM")));
     private final AppiumConfig config = AppiumConfig.getInstanceFor(MobilePlatform.IOS);
//     private final AppiumConfig config = AppiumConfig.getInstanceFor(MobilePlatform.ANDROID);

    @Before
    public void initializeTest() {
        try{
            config.startServer();
            config.startSession();
        }catch(Exception e){
            System.out.println(e.getMessage());
            config.stopServer();
        }

        if (AppiumConfig.getOS() == MobilePlatform.IOS) {
            TestDataService.setProperties("//src//test//resources//data_test//dataIOS.properties");
        }
        else {
            TestDataService.setProperties("//src//test//resources//data_test//dataAndroid.properties");
        }
    }

    @After
    public void tearDownTest(Scenario scenario) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date).replace("/", "-");
        if (scenario.isFailed()) {
            try {
                String featureName = FilenameUtils.getBaseName(scenario.getUri());
                String testName = scenario.getName();
                TakesScreenshot ts =  driver;
                File src = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(".//target/screenshot/"+featureName+"/"+testName+ "\t"+ currentDate+".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(AppiumConfig.getOS() == MobilePlatform.IOS){
            driver.terminateApp("com.ltservices.mektoube");
        }else {
            driver.closeApp();
        }
        AppiumConfig.getDriver().quit();
        config.stopServer();
    }
}
