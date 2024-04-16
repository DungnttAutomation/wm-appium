package com.mektoube.pages;

import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RegistrationPage extends BasePage {

    public void leaveChooseAndValidate(String submit) {
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick(submit);
        System.out.println("I leave choose and submit");
    }

    public void shouldSeeMessagesError(String message) {
        if (OS == MobilePlatform.ANDROID) {
            waitAboutSeconds(1);
            String messageActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name,'" + message + "')]"))).getText();
            System.out.println("The page show message: " + messageActual);
            Assert.assertEquals(message, messageActual);
        }
        if (OS == MobilePlatform.IOS) {
            waitAboutSeconds(1);
            String messageActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'" + message + "')]"))).getText();
            System.out.println("The page show message: " + messageActual);
            Assert.assertEquals(message, messageActual);
        }
    }


    public void enterAValidDateOfBirthAndValidate() {
        waitUntilDisplayElementByXpath("((//android.widget.SeekBar[@content-desc=', '])[2]/preceding-sibling::android.widget.SeekBar)[4]");
        MobileElement mobileElement = driver.findElementByXPath("((//android.widget.SeekBar[@content-desc=', '])[2]/preceding-sibling::android.widget.SeekBar)[4]");
        swipe("down", mobileElement, 0.9);
//        swipe("down",mobileElement,0.9);
        clickButtonByXpath("submitBtn");
        System.out.println("Enter date of birth and validate");
    }

    public void iSelectOriginHasTextAndSubmit(String buttontext, String submit) {
        waitAboutSeconds(5);
        MobileElement Origin = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'" + buttontext + "')]")));
        Origin.click();
        Origin.click();
        waitDisplayButtonXpathAndClick(submit);
        System.out.println("Select origin " + Origin.getText() + " and submit");
    }

    public void activePermission(String permission) {
        waitAboutSeconds(2);
        //iClickButtonHasText("ACTIVER LA GÉOLOCALISATION");
//        ArrayList<MobileElement> location = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("allowMek"))));
//        if (!location.isEmpty()) {
            if (OS == MobilePlatform.ANDROID) {
//                int OS = Integer.parseInt(driver.getCapabilities().getCapability("platformVersion").toString());
//                System.out.println(OS);
//                if (OS >= 11) {
//                    click("//*[contains(@resource-id,'permission_allow_foreground_only_button')]");
//                } else {
//                    click("(//*[contains(@resource-id,'allow')])[1]");
//                }
                click("(//*[contains(@resource-id,'allow')])[1]");
                System.out.println("Active permission success on Android");
            }
            if (OS == MobilePlatform.IOS) {
                waitAboutSeconds(3);
                click("allowLocationPermission");
                waitAboutSeconds(2);
                click("allowLocationPermissionAreYouKeeping");
                System.out.println("Active permission success on iOS");
            }
//        }
    }

    public void iSelectVilleAndSubmit(int index, String submit) {
        MobileElement Ville;
        if (OS == MobilePlatform.ANDROID) {
            waitUntilDisplayElementByXpath("//android.widget.TextView[@text='Ma ville']");
             Ville = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.widget.ScrollView//android.view.ViewGroup[" + (index) + "]//android.view.ViewGroup/android.widget.TextView")));
        }else {
//            waitUntilDisplayElementByXpath("//XCUIElementTypeStaticText[@name='Ma ville']");
            Ville = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther)[last()-1]")));
//            Ville = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther)["+(index+2)+"]")));
        }
        Ville.click();
//        Ville.click();
        waitDisplayButtonXpathAndClick(submit);
//        System.out.println("Select ville: " + Ville.getText() + " and submit");
    }

    public void iSelectPaysOptionAndSubmit(int index, String submit) {
        waitAboutSeconds(2);
        MobileElement Pays = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.widget.ScrollView//android.view.ViewGroup[" + (index) + "]//android.view.ViewGroup/android.widget.TextView")));
        Pays.click();
        Pays.click();
        waitDisplayButtonXpathAndClick(submit);
        System.out.println("Select pays: " + Pays.getText() + " and submit");
    }

    public void iSelectRégionOptionAndSubmit(int index, String submit) {
        waitAboutSeconds(2);
        MobileElement Region = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.widget.ScrollView//android.view.ViewGroup[" + (index) + "]//android.view.ViewGroup/android.widget.TextView")));
        Region.click();
        Region.click();
        waitDisplayButtonXpathAndClick(submit);
        System.out.println("Select Région: " + Region.getText() + " and submit");
    }

    public void shouldSeeMessageErrorAtTop() {
        String error1 = "Les adresses mails temporaires ne sont pas acceptées";
        String error2 = "Catte adresse email est déjà prise. Voulez-vous vous connecter ou récupérer votre mot de passe?";
        String error3 = "Le mail est incorrect";
        String error4 = "Merci de choisir un prénom avec 2 chiffres maximum";
        String error5 = "Merci de choisir un pseudo de 4 à 15 caractères";
        String error6 = "Ce prénom n’est pas accepté, veuillez en choisir un autre";
        String error7 = "Géolocalisation indisponible";
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='0']"))).getText();
        System.out.println(message);
        if (message.equals(error1)) {
            System.out.println("The email has been blacklist");
        } else if (message.equals(error2)) {
            System.out.println("The email has already use");
        } else if (message.equals(error3)) {
            System.out.println("The email incorrect");
        } else if (message.equals(error4)) {
            System.out.println("Please choose a name with a maximum of 2 digits");
        } else if (message.equals(error5)) {
            System.out.println("Please choose a first name of 4 to 15 characters");
        } else if (message.equals(error6)) {
            System.out.println("The user name has been blacklist");
        } else {
            assertEquals(error7, message);
            System.out.println("Geolocation unavailable");
        }
    }

    public void shouldSeeMessagesErrorUnderLineEmail() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView"))).getText();
        System.out.println(message);
    }

    public void shouldSeeMessagesErrorUnderLineUserName() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.TextView"))).getText();
        System.out.println(message);
    }

    public void shouldSeeMessagesErrorUnderLinePassword() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.TextView"))).getText();
        System.out.println(message);
    }

    public void shouldSeeMessagePopupCGUAtTop() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.widget.TextView[@index='0' and @text='Vous devez accepter les CGU']"))).getText();
        System.out.println(message);
    }

    public void checkEyeButtonWorkingInSignUpScreen() {
        String inputPassWord = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc='input-password']"))).getText();
        if (inputPassWord.contentEquals("mektoube")) {
            System.out.println(inputPassWord + " => pass");
        }
        System.out.println(inputPassWord + " => fail");
    }

    public void redirectToScreen(String text) {
        waitAboutSeconds(2);
        String check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'" + text + "')]"))).getText();
        if (check.contains("Félicitations") || check.contains("Inscription")) {
            System.out.println(check + " pass");
        } else {
            System.out.println(check + " fail");
        }
    }

    public void clickPasserCetteÉtape() {
        for (int i = 0; i < 8; i++) {
            clickButtonByXpath("skipBtn");
            System.out.println("Skip Verify");
        }
    }

    public void createAWomanAccountWithEmailAntiUsername(String antiUsername, String permission) {
        clickButtonByXpath("female");
        clickButtonByXpath("submitBtn");
        enterAValidDateOfBirthAndValidate();
        iSelectOriginHasTextAndSubmit("Pakistan", "submitOrigin");
        clickButtonByXpath("submitBtn");
        activePermission(permission);
        iSelectVilleAndSubmit(1, "submitBtn");
        clearTextAndInsertTextIntoField("qa+techiz@mektoube.fr", "emailLabel");
        clearTextAndInsertTextIntoField(antiUsername, "nameLabel");
        clearTextAndInsertTextIntoField("mektoube", "passLabel");
        clickButtonByXpath("TermsOfUse1");
        clickButtonByXpath("TermsOfUse2");
        clickButtonByXpath("loginButton");
        System.out.println("Creat A Woman Account");
    }

    public void insertAntiUsernameAndClick(String name, String submit) {
        clearTextAndInsertTextIntoField(name, "nameLabel");
        driver.hideKeyboard();
        waitAboutSeconds(1);
        clickButtonByXpath(submit);
        System.out.println("Enter antiusername");
    }

    public void createAManAccountWithEmailUsernameAndPermission(String email, String name, String location) {
        clickButtonByXpath("male");
        clickButtonByXpath("submitBtn");
        enterAValidDateOfBirthAndValidate();

        click("firstOptionOrigin");//select first origine
//        selectFirstOptionAfterSearchAtField("France", "textField");
        clickButtonByXpath("submitBtn");
        waitAboutSeconds(1);
        clickButtonByXpath("submitBtn");
        if (location.equals("allow")) {
            activePermission(location);
            waitAboutSeconds(1);
        } else {
            activePermission(location);
            iClickButtonHasText("Ma localisation");
            iSelectPaysOptionAndSubmit(1, "submitBtn");
            iSelectRégionOptionAndSubmit(1, "submitBtn");
            waitAboutSeconds(1);
        }
        iSelectVilleAndSubmit(1, "submitBtn");
        clearTextAndInsertTextIntoField(name, "nameLabel");
        click("submitBtn");
        clearTextAndInsertTextIntoField(email, "emailLabel");
        clearTextAndInsertTextIntoField("mektoube", "passLabel");
        clickButtonByXpath("TermsOfUse1");
        new DiscoveryPage().iScrollUp();
        clickButtonByXpath("TermsOfUse2");
        clickButtonByXpath("submitBtn");
        System.out.println("Creat A Man Account");
        waitAboutSeconds(4);
    }

    public void checkMessageOnTopAfterChoosePhotoInGallery() {
        String x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView"))).getText();
        String text1 = "Votre photo est envoyée pour modération";
        String text2 = "Le téléchargement de la photo a échoué. Veuillez réessayer";
        if (x.equals(text1)) {
            System.out.println("Upload photo success");
        } else if (x.equals(text2)) {
            System.out.println("Upload photo unsuccessful");
        }
    }

    public void showRedErrorMessageAbovePhoneNumberField(String error) {
        waitAboutSeconds(1);
        waitUntilDisplayElementByXpath("errorAbovePhoneNb");
        String errActual = driver.findElement(By.xpath(TestDataService.properties.getProperty("errorAbovePhoneNb"))).getText();
        System.out.println("error actual : \t" + errActual);
        Assert.assertEquals(error, errActual);
    }

    public void selectFirstOptionAfterSearchAtField(String text, String field) {
        waitUntilDisplayElementByXpath("//android.widget.EditText");
        clearTextAndInsertTextIntoField(text, field);
        waitAboutSeconds(1);
        driver.findElement(By.xpath("(//android.widget.ScrollView)[2]//android.widget.TextView")).click();
        clickButtonByXpath("submitBtn");
    }

    public void createAccountWithEmailUsernamePassSuccessAndGoToDiscovery(String email, String username, String pass) {
        clickButtonByXpath("regisBtn");

        enterAValidDateOfBirthAndValidate();
        selectFirstOptionAfterSearchAtField("France", "textField");
        iClickButtonHasText("Ma localisation");
        scrollUpToTextAt("France", "listOption");
        iClickButtonHasText("France");
        clickButtonByXpath("submitBtn");
        waitAboutSeconds(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).sendKeys("31500");
        waitDisplayButtonXpathAndClick("submitBtn");
        System.out.println("Enter code postal success");
        waitAboutSeconds(2);
        MobileElement Ville = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.widget.ScrollView//android.view.ViewGroup[1]//android.view.ViewGroup/android.widget.TextView")));
        Ville.click();
        waitDisplayButtonXpathAndClick("submitBtn");
        System.out.println("Select ville: " + Ville.getText() + " and submit");
        clearTextAndInsertTextIntoField(email, "emailLabel");
        clearTextAndInsertTextIntoField(pass, "passLabel");
        clickButtonByXpath("TermsOfUse1");
        clickButtonByXpath("TermsOfUse2");
        clickButtonByXpath("loginButton");
    }

    public void selectAtPaysScreen(String text) {
        if (OS == MobilePlatform.ANDROID) {
            iClickButtonHasText(text);
        }
        if (OS == MobilePlatform.IOS) {
            click("Germany");
        }
    }

    public void clickSignupWithoutCGU() {
        clickButtonByXpath("loginGoogle");
        shouldSeeAlertMessageWithContentIsShowedOnTop("Vous devez accepter les CGU");
    }

    public void validateWithInvalidPseudoAndValidPseudo() {
        clearTextAndInsertTextIntoField("mina244", "nameLabel");
        click("submitBtn");
        shouldSeeAlertMessageWithContentIsShowedOnTop("Merci de choisir un prénom avec 2 chiffres maximum");
        clearTextAndInsertTextIntoField("gangbang", "nameLabel");
        click("submitBtn");
        shouldSeeAlertMessageWithContentIsShowedOnTop("Ce prénom n’est pas accepté, veuillez en choisir un autre");
        clearTextAndInsertTextIntoField("mi4", "nameLabel");
        click("submitBtn");
        shouldSeeAlertMessageWithContentIsShowedOnTop("Merci de choisir un pseudo de 4 à 15 caractères");
        clearTextAndInsertTextIntoField("mektoubee12", "nameLabel");
        click("submitBtn");
    }

    public void clickCountryAt(String country, String element) {
        if (OS == MobilePlatform.ANDROID) {
            int count = 0;
            waitUntilDisplayElementByXpath("//android.view.ViewGroup[@content-desc='option-DE']");
            MobileElement element1 = driver.findElement(By.xpath("//android.widget.ScrollView"));
            while (count == 0) {
                ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc='option-"+country+"']")));
                if (!list.isEmpty()) {
                    count = 1;
                    list.get(0).click();
                } else
                    swipe("up", element1, 0.3);
            }
        }
        if (OS == MobilePlatform.IOS) {
            int count = 0;
            waitUntilDisplayElementByXpath("(//XCUIElementTypeOther[@name='option-DZ'])[2]");
            MobileElement element1 = driver.findElement(By.xpath(TestDataService.properties.getProperty(element)));
            while (count == 0) {
                ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("(//XCUIElementTypeOther[@name='option-" + country + "'])")));
                System.out.println("Element found: " + list.size());
                //find out 3 elements so list.size must be > 1
                if (!list.isEmpty()) {
                    count = 1;
                    list.get(0).click();
                } else
                    swipe("up", element1, 0.3);
            }
        }

    }
}
