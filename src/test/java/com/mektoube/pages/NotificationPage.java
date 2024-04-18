package com.mektoube.pages;

import com.mektoube.service.TestDataService;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static com.mektoube.config.AppiumConfig.driver;
import static org.junit.Assert.assertEquals;

public class NotificationPage {
    BasePage common = new BasePage();

    public void iLoginSuccessfullyWithTheUsernameAndPassword(String username, String password) {
        MobileElement seConnecterButton = (MobileElement) common.driver.findElementByAccessibilityId("SE CONNECTER");
        seConnecterButton.click();
        //(//XCUIElementTypeOther[@name="Adresse mail Adresse mail"])[3]/XCUIElementTypeOther[2]/XCUIElementTypeTextField
        ////XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField
        MobileElement usernameTextInput = (MobileElement) common.driver.findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
        usernameTextInput.clear();
        usernameTextInput.setValue(username);
        MobileElement passwordTextInput = (MobileElement) common.driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Votre mot de passe Votre mot de passe\"])[1]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
        passwordTextInput.setValue(password);

        //XCUIElementTypeOther[@name="ME CONNECTER"]
        common.driver.findElementByAccessibilityId("ME CONNECTER").click();
        common.driver.findElementByAccessibilityId("ME CONNECTER").click();

        MobileElement rencontreText = (MobileElement) common.driver.findElementByAccessibilityId("Rencontre");
        String innertext = rencontreText.getText();
        Assert.assertEquals("Rencontre", innertext);
    }

    public void theUserGoToNotificationsMenu() {
        //common.clickButtonByXpath("notificationScreen");

        MobileElement messengerButton = (MobileElement) common.driver.findElementByXPath("//XCUIElementTypeButton[@name=\", tab, 4 of 5\"]");
        messengerButton.click();

        common.waitAboutSeconds(3);
        String innerText = common.driver.findElementByAccessibilityId("Notifications").getText();
        Assert.assertEquals("Notifications", innerText);
        System.out.println("Notifications menu is displayed !!!");
    }

    public void clickOnTheFilterIcon() {
        common.waitAboutSeconds(5);
        common.clickButtonByXpath("filterIcon");
    }

    public void clickButton(String optionFilter) {
        switch (optionFilter) {
            case "Tout":
                common.clickButtonByXpath("filterByAllBtn");
                break;
            case "Smiles":
                common.clickButtonByXpath("filterBySmilesBtn");
                break;
            case "Visites":
                common.clickButtonByXpath("filterByVisitsBtn");
                break;
            case "Rendez-vous virtuel":
                common.clickButtonByXpath("filterByVirtualBtn");
                break;
            case "Info Mektoube":
                common.clickButtonByXpath("filterByInfoMekBtn");
                break;
            case "Signalement Mektoube":
                common.clickButtonByXpath("filterBySignatureMekBtn");
                break;
        }
    }

    public void theNotificationsPageShowsTheOfTheFilterThatTheUserSelectedBefore(String results) {
        switch (results) {
            case "AllIcon":
                ArrayList<MobileElement> notiList = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profileItemNotification"))));
                ArrayList<MobileElement> visitNotiList = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("smileIcon2"))));
                ArrayList<MobileElement> smileNotiList = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("visitIcon2"))));
                ArrayList<MobileElement> refuseNotiList = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("virtualDateIcon"))));
                assertEquals(notiList.size(), refuseNotiList.size() + visitNotiList.size() + smileNotiList.size());
            case "smileIcon":
                ArrayList<MobileElement> notiList1 = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profileItemNotification"))));
                for (MobileElement noti : notiList1) {
                    Assert.assertTrue(noti.findElement(By.xpath(TestDataService.properties.getProperty("smileIcon2"))).isEnabled());
                }
            case "visitesIcon":
                ArrayList<MobileElement> notiList2 = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profileItemNotification"))));
                for (MobileElement noti : notiList2) {
                    Assert.assertTrue(noti.findElement(By.xpath(TestDataService.properties.getProperty("visitIcon2"))).isEnabled());
                }
            case "virtualIcon":
                ArrayList<MobileElement> notiList3 = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profileItemNotification"))));
                for (MobileElement noti : notiList3) {
                    Assert.assertTrue(noti.findElement(By.xpath(TestDataService.properties.getProperty("virtualDateIcon"))).isEnabled());
                }
            case "InfoMekIcon":
                ArrayList<MobileElement> notiList4 = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profileItemNotification"))));
                for (MobileElement noti : notiList4) {
                    Assert.assertTrue(noti.findElement(By.xpath(TestDataService.properties.getProperty("InfoMekIcon"))).isEnabled());
                }
            case "SignatureMekIcon":
                ArrayList<MobileElement> notiList5 = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profileItemNotification"))));
                for (MobileElement noti : notiList5) {
                    Assert.assertTrue(noti.findElement(By.xpath(TestDataService.properties.getProperty("SignatureMekIcon"))).isEnabled());
                }
        }

    }

    /*Android*/
    public void iClickOnThe(String btn) {
        common.clickButtonByXpath(btn);
    }

    public void iClickButton(String btn) {
        switch (btn) {
            case "Smiles":
//                common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Smiles']"))).click();
                System.out.println("Click Smiles filter");
                break;
            case "Visites":
//                common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Visites']"))).click();
                System.out.println("Click Visites filter");
                break;
        }
    }

    public void theNotificationsPageShowsTheOfTheFilter(String icon) {
        common.waitAboutSeconds(2);
        switch (icon) {
            case "smileIcon":
                ArrayList<MobileElement> listSmile = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='icon-smile']")));
                System.out.println(listSmile.size());
                int x = 0;
                if ( x == listSmile.size()) {
                    listSmile.get(x + 1).click();
                } else {
                    listSmile.get(x).click();
                }
                System.out.println("I click profile");
                break;
            case "visitesIcon":
                ArrayList<MobileElement> listVisit = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@content-desc='icon-visit']")));
                System.out.println(listVisit.size());
                int y = 0;
                if (y == listVisit.size()) {
                    listVisit.get(y + 1).click();
                } else {
                    listVisit.get(y).click();
                    System.out.println("I click profile");
                    break;
                }
        }
    }

    public void shouldSeeModalPayment() {
        String text = "Pass Mektoube";
//        String x = common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Pass Mektoube']"))).getText();
//        Assert.assertEquals(text, x);
//        System.out.println(x);
    }

    public void checkBannersToSubscribeIsDisplayInNotif() {
        String text1 = "Parcourir les profils c’est bien, discuter c’est mieux !";
        String text2 = "Découvrez vite qui s'intéresse à vous !";
//        String banner = common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView"))).getText();
//        if (banner.equals(text1)) {
//            iClickOnThe("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView");
//            System.out.println(banner);
//            System.out.println("Account is non-premium");
//        } else if (banner.equals(text2)) {
//            iClickOnThe("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView");
//            System.out.println(banner);
//            System.out.println("Account is non-premium");
//        } else {
//            System.out.println("Account is premium");
//        }
    }

    public void chooseMessageFunctionAtNotiVisitOf(String name) {
        common.waitElementByXpath("//android.widget.TextView[@content-desc=\"icon-visit\"]");
        ArrayList<MobileElement> listNameVisitNoti = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"notif-pseudo\"]")));
        for (int i = 0; i < listNameVisitNoti.size(); i++) {
            if(listNameVisitNoti.get(i).getText().equals(name)){
                driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup["+(i+2)+"]//android.widget.TextView[@content-desc=\"notif-pseudo\"][1]/following-sibling::android.view.ViewGroup[@content-desc=\"notif-option\"]")).click();
            }
        }
        common.clickButtonByXpath("//android.widget.TextView[@text='Message']");
    }
}

