package com.mektoube.pages;

//import com.mektoube.util.SelectorUtil;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage extends BasePage {
    public void iAmOnTheHomePage() {
        waitForVisibilityOf(By.xpath("//*[contains(@text,'SE CONNECTER')]"), By.xpath("//*[contains(@text,'SE CONNECTER')]"));
        assertThat("Home page is not opened!", isElementPresent(parallelGetElement(By.xpath("//*[contains(@text,'SE CONNECTER')]"), By.xpath("//*[contains(@text,'SE CONNECTER')]")),15));
        System.out.println("In Home Page method");
    }

    public void iClickOnLoginButton() {
        MobileElement loginButton = driver.findElements(By.className("android.widget.TextView")).get(1);
        loginButton.click();
        System.out.println("Clicked login button");
    }
}
