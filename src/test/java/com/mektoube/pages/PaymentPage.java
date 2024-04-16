package com.mektoube.pages;

import com.mektoube.config.MobilePlatform;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PaymentPage extends BasePage {
    MessageModule messageModule = new MessageModule();
    DiscoveryPage discoveryPage = new DiscoveryPage();

    public void choosePassMoisAndCheckTotalPriceInValiderButton(int numberMois) {

        switch (numberMois) {
            case 1:
                waitAboutSeconds(2);
                iClickButtonHasText("Accès illimité 1 mois");
                waitAboutSeconds(2);
                String paymentTotalPrice1 = driver.findElement(By.xpath("//*[contains(@text,'Paiement de')]")).getText();
                System.out.println("I choose Pass 1 mois: " + paymentTotalPrice1);
                assertEquals("Paiement de 21,99€", paymentTotalPrice1);
                break;
            case 3:
                iClickButtonHasText("Un paiement unique de 39,99");
                waitAboutSeconds(2);
                String paymentTotalPrice3 = driver.findElement(By.xpath("//*[contains(@text,'Paiement de')]")).getText();
                System.out.println("I choose Pass 3 mois: " + paymentTotalPrice3);
                assertEquals("Paiement de 43,99€", paymentTotalPrice3);
                break;
            case 6:
                iClickButtonHasText("Un paiement unique de 59,99");
                waitAboutSeconds(2);
                String paymentTotalPrice6 = driver.findElement(By.xpath("//*[contains(@text,'Paiement de')]")).getText();
                System.out.println("I choose Pass 6 mois: " + paymentTotalPrice6);
                assertEquals("Paiement de 65,99€", paymentTotalPrice6);
                break;
        }
    }

    public boolean paymentByPayboxScreenIsShowFull() {
        waitUntilDisplayElementByXpath("//android.widget.ImageView");
        ArrayList<MobileElement> listImageCard = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ImageView")));
        System.out.println("Number of image card is: " + listImageCard.size());

        MobileElement numberPlaceHolder = driver.findElement(By.xpath("//*[contains(@text,'Numéro de')]"));
        waitUntilDisplayElementByXpath("//*[contains(@text,'Numéro de')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
        String datePlaceHolder = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=4]//android.widget.TextView")).getText();
        waitUntilDisplayElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=4]//android.widget.TextView/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
        String cvvPlaceHolder = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=5]//android.widget.TextView")).getText();
        waitUntilDisplayElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=5]//android.widget.TextView/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
        String checkBoxText = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=6]/android.widget.TextView")).getText();
        String conditionLink = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=6]/android.view.ViewGroup[@index=2]/android.widget.TextView")).getText();
        String inforText = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView")).getText();

        System.out.println(numberPlaceHolder.getText() +"\n" + datePlaceHolder + "\n" + cvvPlaceHolder + "\n" + checkBoxText + "\n" + conditionLink + "\n" + inforText);

        if (listImageCard.size() == 3 && datePlaceHolder.contains("Date d'expiration") && numberPlaceHolder.getText().contains("Numéro de carte") && cvvPlaceHolder.contains("CVV")
                && checkBoxText.contains("Je certifie être majeur(e) et j’accepte les") && conditionLink.contains("Conditions générales d’utilisations")
                && inforText.contains("Vos données personnelles sont collectées et traitées par Mektoube et communiquées au service client de Mektoube et aux autres utilisateurs du site/application dans & hors l'UE conformément à la charte privée.")) {
            return true;
        }
        return false;
    }

    public void modalAbonnementMoisIsShow(int pass) {
        waitAboutSeconds(3);
        String textAbonnement = driver.findElement(By.xpath("//android.widget.LinearLayout[@index=2]//android.widget.LinearLayout/android.widget.LinearLayout[@index=1]//android.widget.TextView[@index=0]")).getText();
        System.out.println(textAbonnement);
        assertTrue(textAbonnement.equalsIgnoreCase("Abonnement " + pass + " mois"));
    }

    public void paymentIfGoogleTesterAccountIsLoginedOnPlayStore() {
        waitAboutSeconds(3);
        ArrayList<MobileElement> subscribseBtn = new ArrayList<> (driver.findElements(By.xpath("//android.widget.Button[last()]")));
        System.out.println("subscribseBtn: " + subscribseBtn.size());
        if (subscribseBtn.size() == 1) {
            System.out.println("Google tester account is logined >> can make a payment");
            iClickButtonHasText("Subscribe");
//            messageModule.thePageShowMessageContainText("Félicitations, vous êtes abonné !");
            waitAboutSeconds(5);
            discoveryPage.iAmOnTheDiscoveryPage();
        }
        else {
            System.out.println("Google tester account isn't logined >> can't make a payment");
        }
    }

    public void checkButtonInInformationsDeVotreAbonnementModalAndAction() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> redButton = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=5]/android.view.ViewGroup//android.widget.TextView[contains(@text,'ANNULER LE DÉSABONNEMENT')]")));
        ArrayList<MobileElement> unsubButton = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Arrêter mon abonnement')]")));
        ArrayList<MobileElement> ANNULERButton = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'ANNULER')]")));
        System.out.println("red button " + redButton.size());
        if (redButton.size()==1) {
            iClickButtonHasText("ANNULER LE DÉSABONNEMENT");
            messageModule.thePageShowMessageContainText("Votre annulation de désabonnement a bien été prise en compte");
            System.out.println(">>>>>>>>>>>>>>>>>>>>> Cancel unsubcription process by red button");
        }
        else {
            if (unsubButton.size()==1) {
                iClickButtonHasText("Arrêter mon abonnement");
                clearTextAndInsertTextIntoField("mektoube","passwordFieldInDesabonnementModal");
                iClickButtonHasText("VALIDER");
                messageModule.thePageShowMessageContainText("Un email vous a été envoyé");
                System.out.println(">>>>>>>>>>>>>>>>>>>>> Unsubcription success");
            }
            else if (ANNULERButton.size()==1) {
                String paymentInfor = driver.findElement(By.xpath("//*[contains(@text,'Votre abonnement a été souscrit via')]")).getText();
                if (paymentInfor.contains("Google")) {
                    System.out.println("This account payment by Google: " + paymentInfor );
                }
                else {
                    System.out.println("This account payment by Itunes " + paymentInfor );
                }
                iClickButtonHasText("ANNULER");
            }
        }
    }

    public boolean iNeedAnAndroidPhone() {
        return OS == MobilePlatform.ANDROID;
    }
}
