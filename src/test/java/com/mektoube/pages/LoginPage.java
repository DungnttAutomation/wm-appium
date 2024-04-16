package com.mektoube.pages;

import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage {
    DiscoveryPage discovery = new DiscoveryPage();
    BasePage basePage = new BasePage();

    public void theUserClickOnLoginByPhoneNumberTab() {
        if (OS == MobilePlatform.IOS) {
            basePage.clickOnTheTab("phoneTab");
        } if (OS == MobilePlatform.ANDROID) {
            basePage.click("loginByPhone");
        }
    }

    public void iAmOnTheLoginPage() {
        waitForVisibilityOf(By.xpath("//*[contains(@text,'ME CONNECTER')]"), By.xpath("//*[contains(@text,'ME CONNECTER')]"));
        assertThat("Login page is not showed!", isElementPresent(parallelGetElement(By.xpath("//*[contains(@text,'ME CONNECTER')]"), By.xpath("//*[contains(@text,'ME CONNECTER')]")), 15));
        System.out.println("In Login Page method");
    }

    public void iClickOnSubmitButton() {
        ArrayList<MobileElement> meConnecterBtnList = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        for (MobileElement mobileElement : meConnecterBtnList) {
            if (mobileElement.getText().equalsIgnoreCase("ME CONNECTER")) {
                System.out.println("check co dung button khong" + mobileElement.getText());
                mobileElement.click();
            }
        }
        System.out.println("End for");
    }

    public void loginWithUsernameOrEmailAndPassword(String email, String password) {
//        waitAboutSeconds(2);
        waitDisplayButtonXpathAndClick("seConnecterBtn");
//        waitAboutSeconds(2);
        clearTextAndInsertTextIntoField(email, "emailInput");
        clearTextAndInsertTextIntoField(password, "passwordInput");
        waitDisplayButtonXpathAndClick("spaceBtn");
        waitDisplayButtonXpathAndClick("loginButton");
//        cmt cause new design doesn't show popup active permission
        discovery.activeLocationPermissionAfterLoginSuccess();
    }

    public void iLoginWithAccountAndDisablePermission(String email, String pass, String location) {
        if (email.contains("@")) {
            int endIndex = email.indexOf("@");
            DiscoveryPage.stack.push(email.substring(3, endIndex));
        } else {
            DiscoveryPage.stack.push(email);
        }

        clickButtonByXpath("seConnecterBtn");
        clearTextAndInsertTextIntoField(email, "emailInput");
        clearTextAndInsertTextIntoField(pass, "passwordInput");
        waitDisplayButtonXpathAndClick("loginButton");

        String OS = driver.getCapabilities().getCapability("platformVersion").toString();
        System.out.println("Version :" + OS);
        discovery.activeLocationPermissionAfterLoginSuccess();
        System.out.println("Login success");
    }

    public void iCheckErrorMessages() {
        if(OS==MobilePlatform.IOS){
            waitAboutSeconds(1);
            String actual = driver.findElement(By.xpath(TestDataService.properties.getProperty("redError1"))).getText();
            System.out.println(actual);
            List<String> err = new ArrayList<>();
            err.add("Identifiant ou mot de passe incorrect");
            err.add("L'E-Mail ne peut pas être vide");
            err.add("Le mot de passe ne peut pas être vide");
            assertTrue(err.contains(actual));

        }else {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("emailInput"))));
        ArrayList<MobileElement> list2 = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("passwordInput"))));
        //"//XCUIElementTypeSecureTextField[@name=\"input-password\"]"
//        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='1']//android.widget.TextView[@index='0']")));
//        ArrayList<MobileElement> list2 = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='2']//android.widget.TextView[@index='0' and @text='Votre mot de passe']")));
        if (list.isEmpty() && list2.isEmpty()) {
            System.out.println("Please fill email and password");
        } else if (list.isEmpty() && list2.size() == 1) {
            System.out.println("Please fill email");
        } else if (list.size() == 1 && list2.isEmpty()) {
            System.out.println("Please fill password");
            }
        }
    }

    public void messagesErrorIsDisplay(String xpath) {
        String error = TestDataService.properties.getProperty(xpath);
        if (error == null) {
            error = xpath;
        }
        String msgError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(error))).getText();
        System.out.println(msgError);
    }

    public void loginWithEmailAndPassword(String email, String password) {
        if (OS == MobilePlatform.IOS) {
            MobileElement seConnecterButton = (MobileElement) driver.findElementByAccessibilityId("SE CONNECTER");
            seConnecterButton.click();

            MobileElement usernameTextInput = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Adresse mail Adresse mail\"])[3]/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
            usernameTextInput.clear();
            usernameTextInput.sendKeys("qa+alili@mektoube.fr");
            MobileElement passwordTextInput = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Votre mot de passe Votre mot de passe\"])[1]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
            passwordTextInput.clear();
            passwordTextInput.sendKeys("mektoube");

            MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("ME CONNECTER");
            el3.click();
            MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("ME CONNECTER");
            el4.click();
            waitAboutSeconds(3);

            MobileElement rencontreText = (MobileElement) driver.findElementByAccessibilityId("Rencontre");
            String innertext = rencontreText.getText();
            Assert.assertEquals("Rencontre", innertext);

        } else {
            waitAboutSeconds(5);
            waitForVisibilityOf(By.xpath("//*[contains(@text,'SE CONNECTER')]"), By.xpath("//*[contains(@text,'SE CONNECTER')]"));
            System.out.println("In Login Page method");
            iClickButtonHasText("SE CONNECTER");
            waitAboutSeconds(3);
            clearTextAndInsertTextIntoField(email, "emailInput");
            clearTextAndInsertTextIntoField(password, "passwordInput");

            iClickButtonHasText("ME CONNECTER");
//            discovery.activeLocationPermissionAfterLoginSuccess();
            waitAboutSeconds(2);
            ArrayList<MobileElement> elements = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'ENVOYER UN LIEN DE VALIDATION')]")));
            if (elements.size() == 1) {
                System.out.println("Account nay chua validate email");
                clickButtonByXpath("closeBlackListModalBtn");
            } else {
                System.out.println("Account nay da validate email");
            }
            waitUntilDisplayElementByXpath("searchBtn");
            System.out.println("Login success >> I'm in Discovery screen");
        }
    }

    public void loginWithPhoneNbOfCountryAndPassword(String phoneNb, String country, String password) {
        iClickButtonHasText("SE CONNECTER");
        //select flag's country
        waitDisplayButtonXpathAndClick("loginByPhone");
        waitUntilDisplayElementByXpath("flagCountry");
        String countryCode = driver.findElement(By.xpath(TestDataService.properties.getProperty("countryCodePhoneNB"))).getText();
        System.out.println(countryCode);
        switch (countryCode) {
            case "(+33)":
                if (!"France".contains(country)) {
                    selectFlagOfCountry(country);
                }
                break;
            case "(+44)":
                if (!"Royaume-Uni".contains(country)) {
                    selectFlagOfCountry(country);
                }
                break;
            case "(+84)":
                if (!"VietNam".contains(country)) {
                    selectFlagOfCountry(country);
                }
                break;
        }
        clearTextAndInsertTextIntoField(phoneNb, "emailInput");
        clearTextAndInsertTextIntoField(password, "passwordInput");
        iClickButtonHasText("ME CONNECTER");
        discovery.activeLocationPermissionAfterLoginSuccess();
        waitAboutSeconds(2);
        ArrayList<MobileElement> elements = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'ENVOYER UN LIEN DE VALIDATION')]")));
        if (elements.size() == 1) {
            System.out.println("Account nay chua validate email");
            clickButtonByXpath("closeBlackListModalBtn");
        } else {
            System.out.println("Account nay da validate email");
        }
        waitUntilDisplayElementByXpath("searchBtn");
    }

    public void showErrorMessageWhenEnterInvalidPhoneNumber(String message, String element) {
        String xpathElement = TestDataService.properties.getProperty(element);
        if (xpathElement == null) {
            xpathElement = element;
        }
        waitAboutSeconds(2);
        waitElementByXpath(xpathElement);
        String error = driver.findElement(By.xpath(xpathElement)).getText();
//        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView");
//        String error = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")).getText();
        System.out.println("actual message :" + error);
        Assert.assertEquals("two message don't equal", message, error);
    }

    public void searchOfElementAndChooseCountryCode(String country, String element) {
        waitDisplayButtonXpathAndClick("codeInput");
        basePage.iEnterOnTextBox(country, element);
        basePage.clickButtonByXpath("spaceCountry");
        basePage.clickButtonByXpath("coutryCode");
    }

    public void changeCountryCodeToIfForgotOrLoginWithPhoneNumber(String country) {
        waitAboutSeconds(1);
        ArrayList<MobileElement> elements = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='country-code']")));
        if (!elements.isEmpty()){
            click("codeInput");
            clearTextAndInsertTextIntoField(country,"textBox");
            //click text to hide keyboard and cursor
            click("//android.view.ViewGroup[@content-desc='back-button']/following-sibling::android.widget.TextView");
            click("coutryCode");
        }
    }
}
