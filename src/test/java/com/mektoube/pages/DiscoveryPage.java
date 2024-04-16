package com.mektoube.pages;

import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class DiscoveryPage extends BasePage {
    BasePage basePage = new BasePage();
    int count = 0;
    public static Stack<String> stack = new Stack<>();
    Set<String> stringSet = new HashSet<>();
    Set<String> stringSet2 = new HashSet<>();


    public void iAmOnTheDiscoveryPage() {
        waitAboutSeconds(5);
        closeModalEncourageUser();
        waitElementByXpath("searchBtn");
        System.out.println("I am on the Discovery page");
    }

    public void activeLocationPermissionAfterLoginSuccess() {
        waitAboutSeconds(4);
//        ArrayList<MobileElement> location = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("allowMek"))));
//        System.out.printf("location " + location);
//        if (!location.isEmpty()) {
//            System.out.println("platformVersion " + driver.getCapabilities().getCapability("platformVersion").toString());
//            String r = driver.getCapabilities().getCapability("platformVersion").toString();
//            String[] r1 = r.split("\\.", 0);
            if (OS == MobilePlatform.ANDROID) {
//                click("(//*[contains(@resource-id,'allow')])[1]");
                clickID("com.android.packageinstaller:id/permission_allow_button");
                waitAboutSeconds(2);
                clickButtonByXpath("//*[contains(@text,\"Passer\")]");
            }
            else {
//                if (Integer.parseInt(r1[0]) < 13) {
//                    driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]")).click();
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[1]")));
//                    driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Only While Using the App\"]")).click();
//                } else
                    driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Allow While Using App']")).click();
                    ArrayList<MobileElement> locationIos = new ArrayList<>(driver.findElements(By.xpath("//XCUIElementTypeButton[@name='Change to Always Allow']")));
                    if(!locationIos.isEmpty()){
                        locationIos.get(0).click();
                    }

            }
//            waitAboutSeconds(4);
//        }
//        waitAboutSeconds(3);
//        ArrayList<MobileElement> backIsDisplay = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("closeModal"))));
//        System.out.println(backIsDisplay.size());
//        if (!backIsDisplay.isEmpty()) {
//            backIsDisplay.get(0).click();
//            waitAboutSeconds(4);
//        }
//        ArrayList<MobileElement> closeHighlight;
//        if(OS==MobilePlatform.IOS){
//                closeHighlight = new ArrayList<>(driver.findElements(By.xpath("//XCUIElementTypeOther[@name='\uE633']")));
//        }else {
//            closeHighlight = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")));
//        }
//        System.out.println(closeHighlight.size());
//        if (!closeHighlight.isEmpty()) {
//            closeHighlight.get(closeHighlight.size()-1).click();
//            waitAboutSeconds(4);
//        }
//
//        ArrayList<MobileElement> superTooltip = new ArrayList<>(driver.findElements(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")));
//        System.out.println(superTooltip.size());
//        if (!superTooltip.isEmpty()) {
//            superTooltip.get(0).click();
//        }
    }

    public void iLoginWithAccountAnd(String userName, String pass) {
        String seConnect = "//android.widget.TextView[@text='SE CONNECTER']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(seConnect))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"))).clear();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)"))).clear();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).sendKeys(pass);
        String meConnect = "//*[contains(@text,'ME CONNECTER')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(meConnect))).click();
        String btnLocation = "//*[contains(@text,'ACTIVER')]";

        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(btnLocation)));
        if (list.size() == 1) {
            list.get(0).click();
        }
        System.out.println(stack);
        waitAboutSeconds(1);
        closeModalEncourageUser();
    }

    public boolean displaySearchBoxHaveTag(String tagName) {
        iScrollDownToText("Reset");
        waitAboutSeconds(1);
        String text = TestDataService.properties.getProperty(tagName);
        if (text == null) {
            text = tagName;
        }
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='4']/android.view.ViewGroup/android.widget.TextView")));
        for (MobileElement mobileElement : list) {
            System.out.println(mobileElement.getText());
            if (mobileElement.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void redirectToDiscoveryPage() {
        closeModalEncourageUser();
        waitUntilDisplayElementByXpath("//android.view.ViewGroup[@content-desc='btn-filter']");
        System.out.println("Go to DiscoveryPage");
    }

    public void iClickOnSliderAround(String xpath, int around) {
        basePage.waitAboutSeconds(2);
        String x = TestDataService.properties.getProperty(xpath);
        MobileElement seekBar = driver.findElement(By.xpath(x));
        int startX = seekBar.getLocation().getX();
        int endX = seekBar.getSize().getWidth();
        int startY = seekBar.getLocation().getY();

        int moveToXDirectionAt = (endX + around);

        //Moving seekbar using TouchAction class.
        new TouchAction(driver).press(PointOption.point(startX, startY)).moveTo(PointOption.point(moveToXDirectionAt, startY)).release().perform();
    }

    public void clickOnTheUserProfile() {
        Random rd = new Random();

        waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");//wait display user at discovery
        waitAboutSeconds(1);
        ArrayList<MobileElement> listUser = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]")));//get all user show on screen at discovery
        if (listUser.size() != 1) {
            int index = 1 + rd.nextInt(listUser.size() - 1);
            System.out.println("index :" + index + "  size :" + listUser.size());
            listUser.get(index).click();
        } else listUser.get(0).click();
        waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");
        //save name of user who just click
        waitAboutSeconds(2);
        stack.push(driver.findElement(By.xpath(TestDataService.properties.getProperty("nameXpath"))).getText());
        System.out.println("click on user has name :" + stack.peek());
    }

    public boolean theDistanceBetweenTwoAccountsInTheRangeMatchesTheFilter() {
        return false;
    }

    public void iClickButtonUi(String btn) {
        String query = TestDataService.properties.getProperty(btn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(query))).click();
    }

    public boolean shouldSeeText(String text) {
        String text1 = TestDataService.properties.getProperty(text);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().text(\"C'est son anniversaire aujourd'hui !\")")));
        String text2 = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"C'est son anniversaire aujourd'hui !\")")).getText();
        return text1.equals(text2);
    }

    public void iClickToSeePhotoOfUser(String btnPhoto) {
        String x = TestDataService.properties.getProperty(btnPhoto);
        basePage.waitAboutSeconds(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(x))).click();
        waitDisplayButtonXpathAndClick("//android.widget.TextView");
        waitAboutSeconds(1);
    }

    public void scrollAndChooseOption(String option) {
        basePage.waitAboutSeconds(1);
        String x = TestDataService.properties.getProperty(option);
        if (x == null) {
            x = option;
        }
        count = 0;
        MobileElement ele = driver.findElement(By.xpath("//android.widget.FrameLayout"));
        while (count == 0) {
            ArrayList<MobileElement> element = new ArrayList<>(driver.findElements(By.xpath(x)));
            if (element.size() == 0) {
                swipe("up", ele, 0.1);
            }
            count = element.size();
        }
        driver.findElement(By.xpath(x)).click();
    }

    public boolean seeOrigineOfUserAccount() {
        int index = 0;
        waitAboutSeconds(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Silhouette\").instance(0))"));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("Origine")) {
                index = i + 1;
            }
        }
        String origine = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();

        return origine.contains("Algérien");
    }

    public void iClickTickButton(String btnTick) {
        String btn = TestDataService.properties.getProperty(btnTick);
        waitDisplayButtonXpathAndClick(btn);
        basePage.waitAboutSeconds(1);
    }

    public void iHandleSliderAnd(String sldMax, String sldMin) {
        basePage.waitAboutSeconds(2);
//        String sekBarMax = TestDataService.properties.getProperty(sldMax);
        String sekBarMin = TestDataService.properties.getProperty(sldMin);
        MobileElement a = driver.findElement(By.xpath(sekBarMin));
        waitElementByXpath(sldMin);
        swipe("up", a, 0.4);

    }

    public void getValueFilterAnd(int value1, int value2) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Reset\").instance(0))"));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='4']/android.view.ViewGroup/android.widget.TextView")));
        for (MobileElement mobileElement : list) {
            if (mobileElement.getText().contains("cm")) {
                stackInt.push(Integer.parseInt(mobileElement.getText().substring(value1, value1 + 3)));
                stackInt.push(Integer.parseInt(mobileElement.getText().substring(value2, value2 + 3)));

            }
        }
    }

    public boolean seeTailleOfUserAccount() {
        int index = 0;
        int y;
        waitAboutSeconds(2);
        iScrollUpToText("mètres");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("mètres")) {
                index = i - 1;
            }
        }
        String tall = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();
        if (tall.equals("-")) {
            System.out.println(tall);
            return true;
        }
        String cm = tall.substring(2);
        int met = Integer.parseInt(tall.substring(0, 1));
        int centi = Integer.parseInt(cm);
        int x = cm.length();
        System.out.println(met);
        if (x != 2) {
            y = centi * 10 + met * 100;
        } else {
            y = met * 100 + centi;
        }
        System.out.println(y);
        return y >= stackInt.get(0) && y <= stackInt.get(1);
    }

    public boolean seeAgeOfUserAccount() {
        int index = 0;
        basePage.waitAboutSeconds(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"mètres\").instance(0))"));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("mètres")) {
                index = i - 3;
            }
        }
        String age = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();
        int aGe = Integer.parseInt(age);
        System.out.println("age :" + aGe);
        System.out.println(stackInt);
        return aGe >= stackInt.get(0) && aGe <= stackInt.get(1);
    }

    public void getValueFilterAgeAnd(int value1, int value2) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Votre Recherche\").instance(0))"));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='4']/android.view.ViewGroup/android.widget.TextView")));
        for (MobileElement mobileElement : list) {
            if (mobileElement.getText().contains("ans")) {
                stackInt.push(Integer.parseInt(mobileElement.getText().substring(value1, value1 + 2)));
                stackInt.push(Integer.parseInt(mobileElement.getText().substring(value2, value2 + 2)));
            }
        }
    }

    public boolean seeSilhouetteOfUserAccount() {
        int index = 0;
        waitAboutSeconds(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Silhouette\").instance(0))"));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("Silhouette")) {
                index = i + 1;
            }
        }
        String silhouette = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();

        return silhouette.contains("Mince");
    }

    public boolean seeSituationFamilialeOfUserAccount() {
        int index = 0;
        waitAboutSeconds(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Silhouette\").instance(0))"));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("Situation familiale")) {
                index = i + 1;
            }
        }
        String silhouette = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();

        return silhouette.contains("Jamais mari");
    }


    public boolean seeOfUserAccountValue(String key, String value) {
        waitAboutSeconds(1);
        int index = 0;
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains(key)) {
                index = i + 1;
                break;
            }
        }
        String text = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();
        System.out.println("value :" + text);
        return text.contains(value) || text.equals("");
    }


    public boolean seeNiveauDÉtudesOfUser() {
        int index = 0;
        waitAboutSeconds(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Niveau\").instance(0))"));
        waitAboutSeconds(1);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("Niveau")) {
                index = i + 1;
            }
        }
        String text = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").instance(" + index + ")")).getText();
        System.out.println(text);
        return text.contains("Bac +5 et plus");
    }

    public void iEnterOnField(String name) {
        String x = TestDataService.properties.getProperty(name);
        if (x == null) {
            x = name;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).sendKeys(x);
    }

    public boolean seeNameOfUser() {
        String expectName = TestDataService.properties.getProperty("name");
        waitAboutSeconds(2);
        String name = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='2']/android.widget.TextView[@index='0']")).getText();
        System.out.println(expectName + "expect");
        System.out.println(name + "actual");
        return name.toUpperCase().contains(expectName.toUpperCase());
    }

    public void iChooseFilterAge() {
        waitDisplayButtonXpathAndClick("btnAge");
        iClickOnSliderAround("sldAge", 250);
        iClickTickButton("btnTick");
    }

    public void iChooseFilterPseudo() {
        iScrollUpToText("Pseudo");
        waitDisplayButtonXpathAndClick("btnPseudo");
        iEnterOnField("name");
        iClickTickButton("btnTick");
    }

    public void iChooseFilterOrigine() {
        iScrollUpToText("Origine");
        waitDisplayButtonXpathAndClick("btnOrigine");
        scrollAndChooseOption("optFrance");
        waitDisplayButtonXpathAndClick("btnTick");
    }

    public void iChooseFilterNiveau() {
        iScrollUpToText("Pseudo");
        waitDisplayButtonXpathAndClick("btnNiveau");
        scrollAndChooseOption("optBac+5");
        iClickTickButton("btnTick");
    }


    public void iChooseFilterFumeuse() {
        iScrollUpToText("Pseudo");
        waitDisplayButtonXpathAndClick("btnFumeur");
        scrollAndChooseOption("optOuiLaCigare");
        iClickTickButton("btnTick");
    }

    public boolean seeProfileOfAccount() {
        boolean niveau = seeNiveauDÉtudesOfUser();
        System.out.println(niveau);
        boolean fumeur = seeOfUserAccountValue("Tabac", "Oui, la cigarette");
        System.out.println(fumeur);
        return niveau && fumeur;
    }

    public void iFilterPseudo() {
        iScrollUpToText("Critères avancés");
        waitDisplayButtonXpathAndClick("btnAdvanced");
        iChooseFilterPseudo();
        waitDisplayButtonXpathAndClick("btnValidate");
        waitAboutSeconds(2);
    }

    public void iClickSaveFilterAndEnterNameSaveFilter() {
        waitAboutSeconds(3);
        waitDisplayButtonXpathAndClick("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[@index='8']");
        iEnterOnTheField("testsavefilter");
        waitDisplayButtonXpathAndClick("//*[@text='OK']");
    }

    public void iClickOnFirstSaveFilter() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='7']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")));
        System.out.println(list.size());
        list.get(0).click();
    }

    public void removeSaveFilter() {
        waitAboutSeconds(1);
        String x = "//android.widget.ScrollView//android.view.ViewGroup[@index='7']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(x)));
        System.out.println("size list  " + list.size());
        MobileElement element = list.get(0);

        Dimension dimension = element.getSize();
        System.out.println(dimension);

        int Anchor = element.getLocation().getY();

        int startSwipe = (int) Math.round(dimension.getWidth() * 0.7);
        System.out.println(startSwipe);

        int endSwipe = (int) Math.round(dimension.getWidth() * 0.1);
        System.out.println(endSwipe);
        //Swipe save filter display button
        new TouchAction(driver).press(PointOption.point(startSwipe, Anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endSwipe, Anchor))
                .release().perform();
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("//*[contains(@text,'Supprimer')]");
    }

    public void iRenameSaveFilter() {
        waitAboutSeconds(1);
        String x = "//android.widget.ScrollView//android.view.ViewGroup[@index='7']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup";
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(x)));
        System.out.println("size list  " + list.size());
        MobileElement element = list.get(0);

        Dimension dimension = element.getSize();
        System.out.println(dimension);

        int Anchor = element.getLocation().getY();

        int startSwipe = (int) Math.round(dimension.getWidth() * 0.7);
        System.out.println(startSwipe);

        int endSwipe = (int) Math.round(dimension.getWidth() * 0.1);
        System.out.println(endSwipe);
        //Swipe save filter display button
        new TouchAction(driver).press(PointOption.point(startSwipe, Anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endSwipe, Anchor))
                .release().perform();
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("//*[contains(@text,'Renommer')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.widget.EditText"))).sendKeys("testSave");
        waitDisplayButtonXpathAndClick("//*[contains(@text,'OK')]");
    }

    public boolean seeNewNameOfSaveFilter() {
        waitDisplayButtonXpathAndClick("searchBtn");
        waitAboutSeconds(1);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='expanded']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[1]//android.widget.TextView/preceding-sibling::android.view.ViewGroup/android.widget.TextView")));
        System.out.println(list.size());
        for (MobileElement mobileElement : list) {
            System.out.println(mobileElement.getText());
            if (mobileElement.getText().equals("testSave")) {
                return true;
            }
        }
        return false;
    }

    public boolean seeErrorMessage() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        System.out.println(list.size());
        return list.get(list.size() - 2).getText().contains("Le Pseudo ne peut pas");
    }

    public boolean clickButtonSearchOnDiscoveryPageAndDonTDisplaySearchBox() {
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("searchBtn");
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='4']/android.view.ViewGroup/android.widget.TextView")));
        if (list.size() == 0) {
            System.out.println("Don't show search box");
            return true;
        }
        return false;
    }

    public void iFilterAccountWithName(String name) {
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("searchBtn");
        waitAboutSeconds(1);
        iScrollUpToText("Critères avancés");
        waitDisplayButtonXpathAndClick("btnAdvanced");
        iScrollUpToText("Pseudo");
        waitDisplayButtonXpathAndClick("btnPseudo");
        iEnterOnField(name);
        iClickTickButton("btnTick");
        waitDisplayButtonXpathAndClick("btnValidate");
    }

    public boolean closePopupSentContactRequest() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Donnez-lui envie de croiser votre destin !')]")));
        if (list.size() == 0) {
            System.out.println("close popup sent contect request");
            return true;
        }
        return false;
    }

    public boolean displayErrorMessage() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        for (MobileElement x : list) {
            if (x.getText().equalsIgnoreCase("Le contenu du message est requis.")) {
                return true;
            }
        }
        return false;
    }

    public void displayShowPopupSentContactRequestSuccess() {
    }

    public void iClickAnySomeidea() {
        Random rd = new Random();
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.widget.TextView")));
        int x = rd.nextInt(list.size());
        if (x == list.size() - 1) {
            stack.push(list.get(list.size() - 1).getText());
            System.out.println(list.get(list.size() - 1).getText());
            list.get(list.size() - 1).click();
        } else
            stack.push(list.get(x).getText());
        System.out.println(list.get(x).getText());
        list.get(x).click();
        System.out.println(stack);
        waitAboutSeconds(2);
    }

    public void iSentContactRequestToUserHasName(String name) {
        iFilterAccountWithName(name);
        clickOnTheUserProfile();
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("btnCross");
        waitAboutSeconds(2);
        waitDisplayButtonXpathAndClick("btnSuggest");
        System.out.println(1);
        iClickAnySomeidea();
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("btnSent");
        waitAboutSeconds(3);
    }

    public void iLogoutAccount() {
        logoutAccount();
    }

    public void iClickOnContactRequestPage() {
        waitDisplayButtonXpathAndClick(("contactRequestIcon"));
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        list.get(0).click();
    }

    public void clickAcceptContactRequestMostRecent() {
        waitAboutSeconds(2);
        waitDisplayButtonXpathAndClick("btnAcceptFirstContact");
    }

    public void iChooseAnswerNow() {
        waitDisplayButtonXpathAndClick("btnAnswerNow");
        waitAboutSeconds(4);
    }


    public void iChooseAnswerLater() {
        waitDisplayButtonXpathAndClick("btnAnswerLater");
        waitAboutSeconds(2);
    }

    public void iClickOnTheFirstMessage() {
        if (OS == MobilePlatform.ANDROID) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.view.ViewGroup/android.view.ViewGroup[contains(@content-desc,'undefined')]")));
            driver.findElement(By.xpath("(//android.widget.ScrollView//android.view.ViewGroup/android.view.ViewGroup[contains(@content-desc,'undefined')])[1]")).click();
        } else {
            waitUntilDisplayElementByXpath("(//XCUIElementTypeOther[@name='delete']/following-sibling::XCUIElementTypeOther[contains(@name,'thread')])[1]");
            waitDisplayButtonXpathAndClick("(//XCUIElementTypeOther[@name='delete']/following-sibling::XCUIElementTypeOther[contains(@name,'thread')])[1]");
        }
    }
    public boolean seeMessageAndProfileOfPartner() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        System.out.println("message  :" + list.get(1).getText());
//        String nameMan = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='icon-back']/following-sibling::android.view.ViewGroup/android.widget.TextView[@index=1]")).getText();
//        System.out.println("name  :" + nameMan);

        String message = list.get(1).getText();
        System.out.println(stack + "  stack");
        for (int i = 0; i < list.size(); i++) {
            if (stack.peek().equals(message)
//                    && nameMan.equalsIgnoreCase(stack.get(0))
            ) {
                return true;
            }
        }
        return false;
    }

    public void iClickRefulseContactRequest() {
        waitAboutSeconds(2);
        waitDisplayButtonXpathAndClick("btnRefuseFirstContact");
    }

    public void iClickListRefule() {
        waitDisplayButtonXpathAndClick("btnList5Refuse");
        waitAboutSeconds(1);
    }

    public boolean displayUserHasBeenRefulse() {
        String xpathNameFirst = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='0']//android.widget.TextView[@index='1']";
        String nameFirstList5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathNameFirst))).getText();
        System.out.println("name actual: " + nameFirstList5);
        System.out.println("name expect: " + stack.get(0));
        return nameFirstList5.toLowerCase(Locale.ROOT).equals(stack.get(0).toLowerCase(Locale.ROOT));
    }

    public void iClickProfileMostRecentOnContactRequest() {
        waitAboutSeconds(2);
        waitDisplayButtonXpathAndClick("firstProfileContact");

    }

    public void iClickRefulseOnProfile() {
        waitDisplayButtonXpathAndClick("refuseProfile");
        waitAboutSeconds(2);
    }

    public void iFirstProfileOnListReulse() {
        waitDisplayButtonXpathAndClick("firstProfileOnList5");
        waitAboutSeconds(2);
    }

    public void iClickAcceptOnProfile() {
        waitDisplayButtonXpathAndClick("acceptProfile");
        waitAboutSeconds(2);
    }

    public void iChooseAnswerNowOnProfile() {
        waitDisplayButtonXpathAndClick("btnAnswerNowProfile");
        waitAboutSeconds(4);
    }

    public void iChooseAnswerLaterOnProfile() {
        waitDisplayButtonXpathAndClick("btnAnswerLaterProfile");
        waitAboutSeconds(2);
    }

    public void iClickAccpeptOnListRefulse() {
        waitDisplayButtonXpathAndClick("btnAcceptFirstProfileOnList5");
        waitAboutSeconds(1);
    }

    public void iClickButtonResetOnDiscoveryPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ScrollView")));
        String filter = driver.findElement(By.xpath("//*[contains(@text,'Votre Recherche')]/following-sibling::android.widget.TextView")).getText();
        System.out.println(filter);
        MobileElement list = driver.findElement(By.xpath("//android.widget.TextView[@text='CRITÈRES']/following-sibling::android.view.ViewGroup/android.widget.TextView"));
        System.out.println(list.getText());
        MobileElement element = driver.findElement(By.className("android.widget.ScrollView"));
        swipe("up", element, 0.01);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Reset']"))).click();
    }

    public boolean theDefaultFilterStateIsHidden() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text,'CRIT')]")));
        String count2 = driver.findElement(By.xpath("//android.widget.TextView[@text='CRITÈRES']/following-sibling::android.view.ViewGroup/android.widget.TextView")).getText();
        String filter = driver.findElement(By.xpath("//*[contains(@text,'Votre Recherche')]/following-sibling::android.widget.TextView")).getText();
        return filter.equals("") && count2.equals("0");
    }

    public void iScrollUp() {
        waitElementByXpath("//android.widget.ScrollView");
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = driver.manage().window().getSize().getHeight() / 2;
        int x = driver.manage().window().getSize().getWidth() / 2;

        new TouchAction<>(driver).press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x, y / 50))
                .release()
                .perform();
    }

    public boolean iCheckNameOfResult() {
        waitElementByXpath("//android.widget.ScrollView");
        MobileElement element = driver.findElement(By.className("android.widget.ScrollView"));
        swipe("up", element, 0.2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ImageView/parent::android.view.ViewGroup/following-sibling::android.widget.TextView[@index='1']")));
        System.out.println(list.size());
        for (int i = 0; i <= list.size(); i++) {
            if (i == (list.size())) {
                return true;
            }
            if (list.get(i).getText().toLowerCase().contains("anie")) {
                System.out.println("i :" + i);
            }
        }
        return false;
    }

    public void iClickOnBoostProfile() {
        int x = 0;
        waitAboutSeconds(2);
        MobileElement element = driver.findElement(By.className("android.widget.ScrollView"));
        //wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"CLIQUEZ ICI\").instance(0))"))).click();
        while (x == 0) {
            swipe("up", element, 0.1);
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'CLIQUEZ ICI')]")));
            waitAboutSeconds(1);
            x = list.size();
            System.out.println(x);
        }
        driver.findElement(By.xpath("//*[contains(@text,'CLIQUEZ ICI')]")).click();
    }

    public void iEnterOnTheField(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).sendKeys(text);
    }

    public boolean seeErrorMessageWithText(String message) {
        String x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Code invalide')]"))).getText();
        System.out.println(x);
        return x.equalsIgnoreCase(message);
    }

    public boolean seeErrorMessageHasText(String message) {
        String mes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText/parent::android.view.ViewGroup/following-sibling::android.widget.TextView"))).getText();
        return mes.contains(message);
    }

    public void iChangeEnviromentTo(String env) {
        changeEnvironmentTo(env);
    }

    public void iBoostProfileAndLogout() {
        iClickOnBoostProfile();
        iEnterOnTheField("X5596J93");
        iScrollUpToText("VALIDER VOTRE CODE");
        waitDisplayButtonXpathAndClick("btnValider");
        waitElementByXpath("discoveryPage");
        ArrayList<MobileElement> iconBoost = new ArrayList<>(driver.findElementsByAccessibilityId("boost-icon"));
        System.out.println("display icon boost on profile :" + iconBoost.size());
        Assert.assertEquals(1, iconBoost.size());
        logoutAccount();
    }

    public boolean canTClickStarGreenAndHideBoostProfile() {
        int x = 0;
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("btnReset");
        waitAboutSeconds(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));
        MobileElement element = driver.findElement(By.className("android.widget.ScrollView"));
        waitAboutSeconds(1);
        String starCount = TestDataService.properties.getProperty("starGreen");
        ArrayList<MobileElement> abc = new ArrayList<>(driver.findElements(By.xpath(starCount)));
        System.out.println("list star :" + abc.size());
        abc.get(0).click();
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.EditText")));
        if (list.size() != 0) {
            return false;
        }
        while (x <= 2) {
            swipe("up", element, 0.1);
            ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'CLIQUEZ ICI')]")));
            waitAboutSeconds(1);
            if (list1.size() == 0) {
                x++;
            } else return false;
        }
        return true;
    }

    public boolean displayStarGreenInUser(String nameBoost) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));

        MobileElement element = driver.findElement(By.className("android.widget.ScrollView"));
        swipe("up", element, 0.1);
        waitDisplayButtonXpathAndClick("btnReset");
        swipe("up", element, 0.05);

        ArrayList<MobileElement> listNameBoost = new ArrayList<>(driver.findElements(By.xpath("nameBoostProfile")));
        waitAboutSeconds(1);
        String starCount = TestDataService.properties.getProperty("starGreen");
        ArrayList<MobileElement> listStarGreen = new ArrayList<>(driver.findElements(By.xpath(starCount)));
        for (MobileElement mobileElement : listNameBoost) {
            System.out.println(mobileElement.getText());
            if (!nameBoost.contains(mobileElement.getText())) {
                return false;
            }
        }
        listStarGreen.get(0).click();
        waitAboutSeconds(5);
        return true;
    }

    public void iFilterAvecPhoto() {
        waitDisplayButtonXpathAndClick("searchBtn");
        iScrollUpToText("Critères avancés");
        waitDisplayButtonXpathAndClick("btnAdvanced");
        waitDisplayButtonXpathAndClick("btnAvecPhoto");
        waitDisplayButtonXpathAndClick("btnValidate");
    }

    public void iClickSeeAllPhotoOfUser() {
        int x = 1;
        waitAboutSeconds(1);
        String xpath = TestDataService.properties.getProperty("numberPhoto");
        int countPhoto = Integer.parseInt(driver.findElement(By.xpath(xpath)).getText());
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ImageView")));
        list.get(1).click();
        String next = TestDataService.properties.getProperty("btnNextPhoto");
        System.out.println(countPhoto);
        while (x < countPhoto) {
            waitAboutSeconds(1);
            ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath(next)));
            list1.get(list1.size() - 1).click();
            x++;
            System.out.println(x);
        }
    }

    public void iScollToProfileSimilar() {
        int number = 0;
        waitElementByXpath("//android.widget.ScrollView");
        MobileElement element = driver.findElement(By.xpath("//android.widget.ScrollView"));
        while (number == 0) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Profils similaires')]")));
            swipe("up", element, 0.01);
            number = list.size();
        }
    }

    public void iClickProfileSimilar() {
        ArrayList<MobileElement> arrayList = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Profils similaires')]/following-sibling::android.view.ViewGroup")));
        arrayList.get(1).click();
        waitAboutSeconds(1);
        String nameSimilarXpath = TestDataService.properties.getProperty("nameOfUserSimilar");
        ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath(nameSimilarXpath)));
        stack.push(list1.get(5).getText());
        list1.get(5).click();
        waitAboutSeconds(2);
    }

    public boolean showTheSelectedProfile() {
        String name = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='2']/android.widget.TextView[@index='0']")).getText().toLowerCase();
        System.out.println(name);
        System.out.println(stack.peek());
        return name.equals(stack.peek().toLowerCase());
    }

    public void iNextProfileOnSimilar() {
        String nameSimilarXpath = TestDataService.properties.getProperty("nameOfUserSimilar");
        ArrayList<MobileElement> arrayList = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Profils similaires')]/following-sibling::android.view.ViewGroup")));
        ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath(nameSimilarXpath)));
        stack.push(list1.get(0).getText());
        stack.push(list1.get(5).getText());
        System.out.println(stack);
        arrayList.get(1).click();
        arrayList.get(1).click();
        arrayList.get(1).click();
        waitAboutSeconds(1);
    }

    public boolean iCheckListUserOnSimilarProfile() {
        String nameSimilarXpath = TestDataService.properties.getProperty("nameOfUserSimilar");
        ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath(nameSimilarXpath)));
        System.out.println("list :" + list1.get(0).getText());
        System.out.println("list :" + list1.get(5).getText());
        ArrayList<String> list = new ArrayList<>();
        list.add(list1.get(0).getText());
        list.add(list1.get(5).getText());
        System.out.println("stack :" + stack);
        return !list.equals(stack);
    }

    public void getValue(String value) {
        String abc = TestDataService.properties.getProperty(value);
        String x = driver.findElement(By.xpath(abc)).getText();
        stack.push(x);
    }

    public boolean checkNameOfNextUser() {
        waitElementByXpath("nameXpath");
        String xpath = TestDataService.properties.getProperty("nameXpath");
        String nameActual = driver.findElement(By.xpath(xpath)).getText();
        return !nameActual.equals(stack.peek());
    }

    public void clickProfileHaveNameAfterSearchPsedou(String username) {
        // Tên user trong list discovery luôn viết hoa chữ cái đầu >> cần format lại để click đúng trong mọi case
        String name = username.substring(0, 1).toUpperCase(Locale.ROOT) + username.substring(1, username.length()).toLowerCase(Locale.ROOT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='" + name + "']/preceding-sibling::android.view.ViewGroup"))).click();
    }

    public void iClickOnTheUserProfileHasName(String name) {
        int x = 0;
        String xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='" + name + "']/preceding-sibling::android.view.ViewGroup";
        while (x == 0) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(xpath)));
            if (list.size() == 1) {
                x = 1;
            } else iScrollUp();
        }

        waitDisplayButtonXpathAndClick(xpath);
        waitAboutSeconds(1);
    }

    public boolean displayMessageWarning(String message, String xpath) {
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty(xpath)))).getText();
        System.out.println(message);
        System.out.println(actual);
        return actual.contains(message);
    }

    public boolean sentMessageSuccess() {
        String firstMes = TestDataService.properties.getProperty("firstMes");
        waitElementByXpath(firstMes);
        String message = driver.findElement(By.xpath(firstMes)).getText();
        return message.equalsIgnoreCase("first message");
    }

    public void iChooseGif() {
        waitAboutSeconds(2);
        waitElementByXpath("//android.widget.HorizontalScrollView//android.view.ViewGroup[@index='2']");
        waitDisplayButtonXpathAndClick("//android.widget.HorizontalScrollView//android.view.ViewGroup[@index='2']");

    }

    public void iClickButtonSentGif() {
        waitElementByXpath("//android.widget.HorizontalScrollView/following-sibling::android.view.ViewGroup");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.HorizontalScrollView/following-sibling::android.view.ViewGroup")));
        list.get(list.size() - 1).click();
    }

    public void iChooseMessage() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> listSuggest = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView")));
        Random cd = new Random();
        int x = cd.nextInt(listSuggest.size() - 1);
        System.out.println(x);
        stack.push(listSuggest.get(x).getText());
        listSuggest.get(x).click();
    }

    public boolean sentMessageSuggestSuccess() {
        String firstMes = TestDataService.properties.getProperty("firstMes");
        waitElementByXpath(firstMes);
        String message = driver.findElement(By.xpath(firstMes)).getText();
        System.out.println("expect :" + message);
        System.out.println("stack :" + stack.peek());
        return message.equalsIgnoreCase(stack.peek());
    }

    public void clickDiscuter() {
        String nameExpect = driver.findElement(By.xpath(TestDataService.properties.getProperty("nameXpath"))).getText();
        stack.push(nameExpect);
        waitDisplayButtonXpathAndClick("btnDiscuter");
        waitDisplayButtonXpathAndClick("profileSmallIcon");
        waitDisplayButtonXpathAndClick("btnDiscuter");
    }

    public boolean seeStatusOfUser() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> elements = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("statusConnect"))));
        if (elements.size() != 0) {
            String status = elements.get(0).getText();
            if (status.equals("En ligne") || status.equals("Vient de se déconnecter")) {
                waitAboutSeconds(1);
                waitDisplayButtonXpathAndClick("btnBackProfile");
                return true;
            }
        }
        waitDisplayButtonXpathAndClick("btnBackProfile");
        return false;
    }

    public void iClickOnSeeProfileUser() {
        String xpathListName = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.widget.TextView[@index='1']";
        waitElementByXpath(xpathListName);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(xpathListName)));
        stack.push(list.get(0).getText());
        stack.push(list.get(1).getText());
        driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup/android.view.ViewGroup")).click();
    }

    public boolean checkListUserOnDiscoveryPage() {
        String xpathListName = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.widget.TextView[@index='1']";
        waitElementByXpath(xpathListName);
        driver.findElement(By.xpath(xpathListName)).getText();
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(xpathListName)));
        for (MobileElement name : list) {
            System.out.println("list 2 :" + name.getText());
        }
        System.out.println("stack :" + stack);
        return stack.contains(list.get(0).getText()) && stack.contains(list.get(1).getText());
    }

    public boolean iGoToAndBackToDiscovery(String page) {
        String xpathListName = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.widget.TextView[@index='1']";
        waitElementByXpath(xpathListName);
        waitDisplayButtonXpathAndClick(page);
        if (page.equals("contactRequestIcon")) {
            waitAboutSeconds(1);
            waitElementByXpath("//android.widget.TextView");
            ArrayList<MobileElement> x = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
            x.get(0).click();
        }
        waitDisplayButtonXpathAndClick("discoveryPage");
        waitElementByXpath(xpathListName);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(xpathListName)));
        for (MobileElement mobileElement : list) {
            System.out.println(mobileElement.getText());
            if (stack.contains(mobileElement.getText())) {
                System.out.println(page);
                return true;
            }
        }

        return false;
    }

    public void saveListNameUser() {
        String xpathListName = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.widget.TextView[@index='1']";
        waitElementByXpath(xpathListName);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(xpathListName)));
        for (MobileElement mobileElement : list) {
            stack.add(mobileElement.getText());
        }
        System.out.println(stack);
    }

    public boolean displayNewNotificationSmile() {
        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=1]");
        String nameOfUserOnFirstNoti = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("nameOfFirstNoti")))).getText();
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=1]//*[@content-desc='icon-smile']")));
        System.out.println(nameOfUserOnFirstNoti);
        System.out.println(list.size());
        System.out.println(stack);
        return list.size() == 1 && stack.contains(nameOfUserOnFirstNoti);
    }

    public void displayButtonDiscuter() {
        waitElementByXpath("btnDiscuter");
    }

    public void iClickAnyReason() {
        waitAboutSeconds(1);
        waitElementByXpath("//android.widget.ScrollView//android.view.ViewGroup[@index=2]");
        Random random = new Random();
        int index = random.nextInt(5);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index=2]/android.view.ViewGroup[@index=1]/android.view.View//android.widget.TextView")));
//        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.View/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView")));
        list.get(index).click();
    }

    public boolean userAccountIsBlocked() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("btnSmile"))));
        return list.size() == 0;
    }

    public boolean checkUserHasNameHasInBlacklist(String name) {
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = (int) (driver.manage().window().getSize().getHeight() * 0.15);
        int x = driver.manage().window().getSize().getWidth() / 2;

        waitDisplayButtonXpathAndClick("myProfileBtn");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='FERMER']")));
        if (list.size() == 1) {
            list.get(0).click();
        }
        waitAboutSeconds(2);
        new TouchAction<>(driver).press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x / 50, y))
                .release()
                .perform();
        new TouchAction<>(driver).press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x / 50, y))
                .release()
                .perform();
        new TouchAction<>(driver).press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x / 50, y))
                .release()
                .perform();
        //click blacklist
        waitDisplayButtonXpathAndClick("blackList");
        //check user in blacklist
        waitElementByXpath("//*[contains(@text,'Profils')]");
        ArrayList<MobileElement> listName = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup/following-sibling::android.widget.TextView[@index=1]")));
        ArrayList<MobileElement> listThreedot = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index=3]")));
        for (int i = 0; i < listName.size(); i++) {
            if (listName.get(i).getText().contains(name) || listName.get(i).getText().contains(stack.peek())) {
                listThreedot.get(i).click();
                waitDisplayButtonXpathAndClick("//*[@text='SUPPRIMER']");
                waitAboutSeconds(3);
                return true;
            }
        }
        return false;
    }

    public void iGoToDiscoveryPageAndSearchUserBlockRecently() {
        waitDisplayButtonXpathAndClick("discoveryPage");
        iFilterAccountWithName(stack.peek());
    }

    public void iGetAllNameUser() {
        int result = 0;
        String nameDiscoveryXpath = TestDataService.properties.getProperty("nameDiscoveryXpath");
        waitUntilDisplayElementByXpath(nameDiscoveryXpath);
        while (result == 0) {
            //add all name user to Set
            ArrayList<MobileElement> listOld = new ArrayList<>(driver.findElements(By.xpath(nameDiscoveryXpath)));
            if (stringSet.contains(listOld.get(listOld.size() - 1).getText())) {
                result = 1;
            } else {
                for (MobileElement mobileElement : listOld) {
                    stringSet.add(mobileElement.getText());
                }
                //swipe up
                MobileElement scroll = driver.findElement(By.xpath("//android.widget.ScrollView"));
                swipe("up", scroll, 0.1);
                waitAboutSeconds(2);
            }
        }
        System.out.println("list old :" + stringSet);
    }

    public boolean resultNotHaveUserBlockRecently() {
        waitDisplayButtonXpathAndClick("discoveryPage");
        //research again
        MobileElement scroll = driver.findElement(By.xpath("//android.widget.ScrollView"));
        swipe("down", scroll, 0.6);
        waitElementByXpath("//*[contains(@text,'couvrir')]");
        waitDisplayButtonXpathAndClick("//*[contains(@text,'couvrir')]/following-sibling::android.view.ViewGroup[@index=3]");
        waitDisplayButtonXpathAndClick("btnValidate");

        String xpath = TestDataService.properties.getProperty("nameDiscoveryXpath");
        waitUntilDisplayElementByXpath(xpath);
        int result = 0;
        while (result == 0) {
            //add all name user to Set
            ArrayList<MobileElement> listOld = new ArrayList<>(driver.findElements(By.xpath(xpath)));
            if (stringSet2.contains(listOld.get(listOld.size() - 1).getText())) {
                result = 1;
            } else {
                for (MobileElement mobileElement : listOld) {
                    stringSet2.add(mobileElement.getText());
                }
                //swipe up
                swipe("up", scroll, 0.1);
                waitAboutSeconds(2);
            }
        }
        stringSet.removeAll(stringSet2);
        System.out.println("StringSet 2 :" + stringSet2);
        return stringSet.contains(stack.peek());
    }

    public boolean donTShowButtonDiscuterAndButtonSmile() {
        ArrayList<MobileElement> btnSmile = new ArrayList<>(driver.findElements(By.xpath("//*[@text='SMILE']")));
        return btnSmile.size() == 0;
    }

    public boolean showButtonDiscuterAndButtonSmile() {
        ArrayList<MobileElement> btnSmile = new ArrayList<>(driver.findElements(By.xpath("//*[@text='SMILE']")));
        return btnSmile.size() == 1;
    }

    public void iScrollUpToText(String text) {
        int count = 0;
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = driver.manage().window().getSize().getHeight() / 2;
        int x = driver.manage().window().getSize().getWidth() / 2;

        while (count == 0) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'" + text + "')]")));
            if (list.size() == 1) {
                count = 1;
            } else {
                new TouchAction<>(driver).press(PointOption.point(x, y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(PointOption.point(x, y / 4))
                        .release()
                        .perform();
                waitAboutSeconds(1);
            }
        }
    }

    public void iScrollDownToText(String text) {
        waitAboutSeconds(2);
        int count = 0;
        MobileElement ele = driver.findElement(By.xpath("//android.widget.ScrollView"));
        while (count == 0) {
            waitAboutSeconds(2);
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'" + text + "')]")));
            if (list.size() == 1) {
                count = 1;
            } else
                swipe("up", ele, 0.99);
        }
    }

    public boolean checkValueOfPratiquanteOnProfile() {
        //get value if pratiquante
        waitElementByXpath("//*[contains(@text,'Pratiquant')]");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Pratiquant')]/following-sibling::android.widget.TextView")));
        String value = list.get(0).getText();
        System.out.println("value :" + value);
        //check value
        if (value.contains("Oui")) {
            MobileElement ele = driver.findElement(By.xpath("//android.widget.ScrollView"));
            swipe("down", ele, 0.9);
            swipe("down", ele, 0.9);
            //check display pratiquante
            ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Pratiquant')]")));
            return list1.size() == 1;
        } else if (value.contains("Non") || value.equals("")) {
            System.out.println("Pratiquante : Non");
            return true;
        }
        return false;
    }

    public void iClickProfileHasPhoto() {
        waitAboutSeconds(2);
        int x = 0;
        while (x == 0) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"undefined has-avatar\"]//android.widget.ImageView[@index='0']")));
            System.out.println(list.size());
            if (list.size() == 0) {
                iScrollUp();
            } else {
                list.get(0).click();
                x = 1;
            }
        }
        System.out.println("I click profile");
    }

    public void iClickPhotoOfProfile(String photo) {
        waitDisplayButtonXpathAndClick(photo);
        System.out.println("I click photo profile");
    }

    public boolean shouldSeePopUpPremium() {
        String text1 = "Pour pouvoir voir la galerie de photos, prenez un Pass Mektoube.";
        String text2 = "Pour pouvoir utiliser la recherche avancée, prenez un Pass Mektoube.";
        String text3 = "Pour pouvoir visiter plus de profils, prenez un Pass Mektoube.";
        String text4 = "Pour pouvoir smiler plus de profils, prenez un Pass Mektoube.";
        String x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Pour pouvoir')]"))).getText();
        if (x.equals(text1)) {
            return true;
        } else if (x.equals(text2)) {
            return true;
        } else if (x.equals(text3)) {
            return true;
        } else if (x.equals(text4)) {
            return true;
        }
        waitAboutSeconds(3);
        System.out.println(x);
        return false;
    }

    int clicked = 0;

    public void iClickButtonAndViewProfile(String btn) {
        for (int i = 0; i < 30; i++) {
            waitDisplayButtonXpathAndClick(btn);
            clicked++;
        }
        System.out.println("I click view " + clicked + " profile");
    }

    public void iClickButtonThenClickButton(String smile, String next) {
        count = 0;
        while (count <= 10) {
            waitDisplayButtonXpathAndClick(smile);
            if (count == 10) {
                shouldSeePopUpPremium();
                System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Pour pouvoir')]"))).getText());
                count++;
            } else {
                String messageError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Smile envoyé') or contains(@text,'Personne déjà smilée')]"))).getText();
                if (messageError.equals("Smile envoyé")) {
                    count++;
                    System.out.println("Smile success " + count + " profile");
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView")));
                    waitDisplayButtonXpathAndClick(next);
                } else {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView")));
                    waitDisplayButtonXpathAndClick(next);
                }
            }
        }
    }

    public boolean seeTextOnFirstScreenOfModal(String expect) {
        waitElementByXpath("//*[@text='Suivant']");
        String actual = driver.findElement(By.xpath("//android.widget.ImageView/following-sibling::android.widget.TextView")).getText();
        System.out.println("actual :" + actual);
        System.out.println("expect :" + expect);
        if (actual.contains(expect)) {
            System.out.println("dimision :" + driver.manage().window().getSize());
            int y = (int) (driver.manage().window().getSize().getHeight() * 0.15);
            int x = driver.manage().window().getSize().getWidth() / 2;
            new TouchAction<>(driver).press(PointOption.point(x, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(x / 50, y))
                    .release()
                    .perform();
            return true;
        }
        return false;

    }

    public boolean seeTextOnSecondScreenOfModal(String expect) {
        waitElementByXpath("//*[@text='Suivant']");
        String actual = driver.findElement(By.xpath("//android.widget.ImageView/following-sibling::android.widget.TextView")).getText();
        System.out.println("actual :" + actual);
        System.out.println("expect :" + expect);
        if (actual.contains(expect)) {
            System.out.println("dimision :" + driver.manage().window().getSize());
            int y = (int) (driver.manage().window().getSize().getHeight() * 0.15);
            int x = driver.manage().window().getSize().getWidth() / 2;
            new TouchAction<>(driver).press(PointOption.point(x, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(x / 50, y))
                    .release()
                    .perform();
            return true;
        }
        return false;
    }

    public boolean seeTextOnThirdScreenOfModal(String expect) {
        waitElementByXpath("//*[@text='Précédent']");
        String actual = driver.findElement(By.xpath("//android.widget.ImageView/following-sibling::android.widget.TextView")).getText();
        System.out.println("actual :" + actual);
        System.out.println("expect :" + expect);
        return actual.contains(expect);
    }

    public void doNotDisplayListRefulse() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'5 derniers refus')]")));
        if (list.size() == 0) {
            System.out.println("don't display list 5 refulse");
        } else System.out.println("display list 5 refulse");
    }


    public boolean displayListRefulseAndHasNameJustRefulse(String name) {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'5 derniers refus')]")));
        if (list.size() == 1) {
            System.out.println("display list 5 refulse");
            list.get(0).click();
            String xpathNameFirst = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='0']//android.widget.TextView[@index='1']";
            String nameFirstList5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathNameFirst))).getText();
            System.out.println("name actual: " + nameFirstList5);
            if (nameFirstList5.contains(name)) {
                return true;
            }
        }
        System.out.println("don't display list 5 refulse");
        return false;
    }

    public void iLogoutAccountJustLogin() {
        waitDisplayButtonXpathAndClick("myProfileBtn");
        waitDisplayButtonXpathAndClick("//android.view.View/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@content-desc='setting-tab']");
        waitAboutSeconds(1);
        iScrollUpToText("Se déconnecter");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
        list.get(list.size() - 2).click();
    }

    public void searchPesudo(String name) {
        iFilterAccountWithName(name);
        iClickOnTheUserProfileHasName(name);
    }

    public boolean shouldSeePopUpInvalidEmail() {
        String text = "Vous n'avez toujours pas validé votre email";
        String x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Vous')]"))).getText();
        if (x.equals(text)) {
            return true;
        }
        waitAboutSeconds(3);
        System.out.println(x);
        return false;
    }

    public void clearStack() {
        stack.clear();
    }

    public void iCheckInvitationForWomanAccount() {
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'INVITER')]")));
        if (list.size() == 1) {
            waitDisplayButtonXpathAndClick("btnCross");
        } else {
            waitDisplayButtonXpathAndClick("btnNextProfile");
            waitDisplayButtonXpathAndClick("btnCross");
        }
        System.out.println("Inviter woman account");
    }

    public void displayIconBoostAndShowCountdownOnProfile() {
        System.out.println("dimision :" + driver.manage().window().getSize());
        waitElementByXpath("myProfileBtn");
        //check icon boost
        ArrayList<MobileElement> iconBoost = new ArrayList<>(driver.findElementsByAccessibilityId("boost-icon"));
        System.out.println("display icon boost on profile :" + iconBoost.size());
        Assert.assertEquals(1, iconBoost.size());
        //click setting profile and check countdown
        waitDisplayButtonXpathAndClick("myProfileBtn");
        ArrayList<MobileElement> countdown = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countDownTimeBoost"))));
        System.out.println("display countdown on setting profile :" + countdown.size());
        Assert.assertEquals(1, countdown.size());
    }

    public boolean checkBannersToSubscribeIsDisplayInDiscoveryPage() {
        String text1 = "N’attendez pas !";
        String text2 = "Qu’attendez-vous ?";
        String banner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'attendez')]"))).getText();
        System.out.println(banner);
        if ((text1.equals(banner)) || (text2.equals(banner))) {
            waitDisplayButtonXpathAndClick("profileSub");
            System.out.println("Click button");
            return true;
        }
        return false;
    }

    public void checkButtonBlockNotShow() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Bloquer cette personne')]")));
        if (list.size() == 0) {
            System.out.println("Button Block not show");
        }
    }

    public void clickOnProfileHasNameAtContactRequestScreen(String name) {
        iClickOnContactRequestPage();//go to contact screen
        waitElementByXpath("btnAcceptFirstContact");//wait display button accept first
        int x = 0;
        //scroll up if hasn't user
        while (x < 1) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]/following-sibling::android.widget.TextView[@index=1]")));
            for (MobileElement mobileElement : list) {
                System.out.println("name user on contact page :" + mobileElement.getText());
                if (mobileElement.getText().contains(name)) {
                    mobileElement.click();
                    waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");
                    waitDisplayButtonXpathAndClick("btnBackProfile");
                    x++;
                    break;
                }
                iScrollUp();
            }
        }
    }

    public boolean donTShowVisitOfUserAtNotiScreen(String name) {
        waitDisplayButtonXpathAndClick("notificationScreen");//go to noti screen
        //get all name of user visit
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc=\"icon-visit\"]//preceding-sibling::*[@content-desc=\"notif-pseudo\"]")));
        for (MobileElement userName : list) {
            System.out.println("name user on noti page :" + userName.getText());
            if (userName.getText().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean displayRedDotOnContactIcon() {
        waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");//wait avt of user display
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElementsByAccessibilityId("contact-request-dot"));
        System.out.println(list.size() == 1);
        return list.size() == 1;
    }

    public void goToContactPageAndRefulseAllInvite() {
        waitDisplayButtonXpathAndClick(("contactRequestIcon"));
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        list.get(0).click();

        int count = 1;
        while (count > 0) {
            ArrayList<MobileElement> listBtnRefulse = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup/android.view.ViewGroup[@index='5']")));
            count = listBtnRefulse.size();
            System.out.println("list button refulse :" + count);
            if (listBtnRefulse.size() != 0) {
                listBtnRefulse.get(0).click();
                waitAboutSeconds(2);
            }
        }
    }

    public boolean goToDiscoveryAndHideRedDot() {
        waitDisplayButtonXpathAndClick("discoveryPage");
        waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");//wait avt of user display
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElementsByAccessibilityId("contact-request-dot"));
        System.out.println(list.size() == 0);
        return list.size() == 0;
    }

    public void seeStatusConnectionOfUserOnProfile() {
        waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");//wait avt of user display
        //get status
        String status = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index=2]/android.widget.TextView[@index=2]")).getText();
        System.out.println("status connection on profile :" + status);
        stack.push(status);
    }

    public void goToChatDetailWithUser() {
        int count = 0;
        //next util to profile don't sent contact to me
        while (count < 1) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'IL SOUHAITE VOUS INVITER À DISCUTER')]")));
            if (list.size() != 0) {
                count = list.size();
                waitDisplayButtonXpathAndClick("btnNextProfile");
                waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");
            } else break;
        }
        waitDisplayButtonXpathAndClick("//*[@text='DISCUTER']");
    }

    public boolean checkStatusConnection() {
        waitElementByXpath("//*[contains(@text,'Ecrivez votre message...')]");
        String status = driver.findElement(By.xpath("//*[contains(@content-desc,\"undefined\")]/following-sibling::android.widget.TextView[2]")).getText();
        System.out.println("status connection on chat details :" + status + "\nstatus on profile :" + stack.peek());
        return stack.peek().contains(status);
    }

    public void displayModalAskLocationAndChoose(String opt) {
        waitDisplayButtonXpathAndClick("btnActiveModal");
        //set permission
        if (opt.equals("allow")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
        } else if (opt.contains("don't ask again")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/do_not_ask_checkbox"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_deny_button"))).click();
        } else
            wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_deny_button"))).click();

    }

    public boolean showResultSeachLocation() {
        waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]");//wait avt of user display.
        //get green text on discovery
        waitAboutSeconds(1);
        String textGreen = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"btn-save\"]/preceding-sibling::android.widget.TextView[1]")).getText();
        System.out.println(textGreen);
        return textGreen.equals("Autour de moi");
    }


    public boolean donTHideBlueButtonActiveLocationAndWhenClickAgainShowModalAskLocation() {
        waitDisplayButtonXpathAndClick("btnBlueActiveLocation");//click blue button to open modal geolocation
        waitDisplayButtonXpathAndClick("btnActiveModal");//active on modal geolocation
        waitAboutSeconds(2);
        //check display button deny
        ArrayList<MobileElement> btnDeny = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@resource-id,'permission_deny_button')]")));
        return btnDeny.size() == 1;
    }

    public void enablePermissionLocationAgainAndClickBackToApp() {
        iScrollUpToText("Permissions");//scroll to setting permission
        waitDisplayButtonXpathAndClick("//*[contains(@text,'Permissions')]");// open setting permission
        waitDisplayButtonXpathAndClick("//*[contains(@text,'Location')]");//enable location
        waitDisplayButtonXpathAndClick("//*[contains(@content-desc,'Navigate up')]");//back to app
    }

    public void checkModalPermissionLocation() {
        waitAboutSeconds(3);
        ArrayList<MobileElement> permission = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@resource-id,'content_container')]")));
        if (permission.size() == 1) {
            System.out.println("Show modal permission location");
        } else {
            System.out.println("Don't show permission location");
        }

        ArrayList<MobileElement> askagain = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@resource-id,'do_not_ask_checkbox')]")));
        if (askagain.size() == 1) {
            driver.findElement(By.xpath("//*[contains(@resource-id,'permission_deny_button')]")).click();
        }

        ArrayList<MobileElement> elements = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'ENVOYER UN LIEN DE VALIDATION')]")));
        if (elements.size() == 1) {
            clickButtonByXpath("closeBlackListModalBtn");
        }
    }

    public void checkButtonAndClick() {
        waitAboutSeconds(3);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'ACTIVER')]")));
//        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("activeBtn")));
        //*[contains(@text,'ACTIVER')]
        if (list.size() == 1) {
            System.out.println("Button 'ACTIVER' is display");
            waitDisplayButtonXpathAndClick("activeBtn");
        } else {
            System.out.println("Button 'ACTIVER' is not displayed");
        }
    }

    public boolean showModalSentContactRequest() {
        //check display button ANNULER
        String textButtonCancel = driver.findElement(By.xpath("(//android.view.ViewGroup)[last()]/android.widget.TextView")).getText();
        System.out.println(textButtonCancel);
        return textButtonCancel.equals("ANNULER");
    }

    public boolean displayErrorWhenDonTSelectAndClickValidate() {
        waitAboutSeconds(1);
        MobileElement eleErr = driver.findElement(By.xpath("//android.widget.ScrollView/following-sibling::android.view.ViewGroup[1]/android.widget.TextView"));
        String textErr = eleErr.getText();
        System.out.println("actual :" + eleErr);
        return textErr.equals("Sélectionnez un champ pour valider");
    }

    public void chooseSearchRegion() {
        scrollAndChooseOption("//*[contains(@text,'Région')]");
        waitAboutSeconds(1);
        scrollAndChooseOption("//*[contains(@text,'Corse')]");
        waitDisplayButtonXpathAndClick("btnTick");
    }

    public void filterManWithCriteriaPilosite() {
        iScrollUpToText("Pilosité");
        iClickButtonHasText("Pilosité");
        selectAnyOption("many");
        waitDisplayButtonXpathAndClick("btnTick");
    }

    public void selectAnyOption(String option) {
        waitAboutSeconds(1);
        ArrayList<MobileElement> mobileElements = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")));
        Random rd = new Random();
        ArrayList<String> optionIsSelected = new ArrayList<>();
        //select than 1 option
        if (!option.equals("1")) {
            int numberOption = 2 + rd.nextInt(3);
            System.out.println("number option is selected :" + numberOption);
            //get all option
            ArrayList<String> options = new ArrayList<>();
            for (WebElement webElement : mobileElements) {
                options.add(webElement.getText());
            }
            //select multiple option
            if (numberOption > options.size()) {
                numberOption = options.size();//if number option select > number option
            }
            //list option is selected
            for (int i = 1; i <= numberOption; i++) {
                int index = rd.nextInt(options.size());
                optionIsSelected.add(options.get(index));
                driver.findElement(By.xpath("//*[contains(@text,'" + options.get(index) + "')]")).click();
                options.remove(options.get(index));
                waitAboutSeconds(1 / 2);
            }
        } else {
            int index = rd.nextInt(mobileElements.size());
            optionIsSelected.add(mobileElements.get(index).getText());
            mobileElements.get(index).click();

        }
        stack.addAll(optionIsSelected);
        System.out.println(stack);
        System.out.println("option is selected : " + optionIsSelected);

    }

    public void closeModalEncourageUser() {
        waitAboutSeconds(1);

        ArrayList<MobileElement> closeEmail = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("closeModalValidateEmail"))));
        System.out.println("list size close email :" + closeEmail.size());

        if (closeEmail.size() != 0) {
            System.out.println("modal validate email");
            closeEmail.get(0).click();
            waitDisplayButtonXpathAndClick("messageIcon");
            waitElementByXpath("//*[contains(@text,'Messages')]");
            waitDisplayButtonXpathAndClick("discoveryPage");
            waitAboutSeconds(1);

        }
//        waitElementByXpath("btnSaveFilter2");
        ArrayList<MobileElement> closeEncourageUser = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("closeModalEncourageUser"))));
        System.out.println("list size close encourage :" + closeEncourageUser.size());
        if (closeEncourageUser.size() == 1) {
            System.out.println("model encourage user");
            closeEncourageUser.get(0).click();
        }
    }

    public boolean checkInformationOfUser(int number) {
        int count = 1;
        while (count <= number) {
            waitElementByXpath("//android.view.ViewGroup[contains(@content-desc,'avatar')]");
            waitAboutSeconds(2);
            System.out.println("At profile of user :" + driver.findElement(By.xpath(TestDataService.properties.getProperty("nameXpath"))).getText());

            //get all value need check
            ArrayList<MobileElement> btnDiscussInvite = new ArrayList<>(driver.findElements(By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'SMILE')]/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup)[last()]")));
            ArrayList<MobileElement> btnSmile = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'SMILE')]")));
            ArrayList<MobileElement> hasPhoto = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='undefined has-avatar']")));
            ArrayList<MobileElement> numberPhoto = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("numberPhoto"))));
            ArrayList<MobileElement> iconOrigin = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'Mes pays d')]")));
            MobileElement ele = driver.findElement(By.xpath("//android.widget.ScrollView"));
            swipe("up", ele, 0.2);
            swipe("up", ele, 0.2);
            waitAboutSeconds(1);
            String distance = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='moi']/preceding-sibling::android.widget.TextView[2]")).getText();
            String origin = driver.findElement(By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='A des enfants :']/preceding-sibling::android.widget.TextView)[last()]")).getText();
            String location = driver.findElement(By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='Situation familiale :']/preceding-sibling::android.widget.TextView)[last()]")).getText();
            iScrollUpToText("Âge :");
            waitAboutSeconds(1);
            iClickButtonHasText("En voir plus");
            waitAboutSeconds(1);
            String signeAstrologique = driver.findElement(By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[contains(@text,'En voir moins')]/parent::android.view.ViewGroup/preceding-sibling::android.widget.TextView)[last()]")).getText();
            System.out.println("button discus/invite :" + btnDiscussInvite.size() +
                    "\n" + "button smile :" + btnSmile.size() +
                    "\n" + "has photo ?" + hasPhoto.size() +
                    "\n" + "distance :" + distance +
                    "\n" + "origin :" + origin +
                    "\n" + "city :" + location +
                    "\n" + "signe astrologique :" + signeAstrologique);
            System.out.println("----------------------------------------------------");
            //check show button invite/discuss
            if (btnDiscussInvite.size() != 1) {
                System.out.println("don't display button 1");
                return false;
            }
            //check show button smile
            if (btnSmile.size() != 1) {
                System.out.println("don't display button 2");
                return false;
            }
            //check if has photo show number photo
            if (hasPhoto.size() == 1 && numberPhoto.size() == 0) {
                System.out.println("don't display number photo");
                return false;
            }
            //check if user has origin show origin icon
            if (!origin.equals("") && !origin.equals("Je le garde pour moi")) {
                if (iconOrigin.size() != 1) {
                    System.out.println("don't display origin icon");
                    return false;
                }
            }
            //check field don't accept empty
            if (distance.equals("") || location.equals("") || signeAstrologique.equals("")) {
                System.out.println("don't display distance/location/signe astrologique");
                return false;
            }
            waitDisplayButtonXpathAndClick("btnNextProfile");
            count++;
        }
        return true;
    }

    public void saveAllNameProfileAtSimilarToStack() {
        ArrayList<String> name = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waitElementByXpath("//android.widget.HorizontalScrollView//android.view.ViewGroup[contains(@content-desc,'undefined')]");
            ArrayList<MobileElement> listNameSimilar = new ArrayList<>(driver.findElements(By.xpath("//android.widget.HorizontalScrollView//android.view.ViewGroup[contains(@content-desc,'undefined')]/following-sibling::android.widget.TextView[1]")));
            name.add(listNameSimilar.get(0).getText());
            name.add(listNameSimilar.get(1).getText());
            waitDisplayButtonXpathAndClick("btnNextProfileSimilar");
            waitDisplayButtonXpathAndClick("btnNextProfileSimilar");
        }
        System.out.println(name);
        stack.addAll(name);
    }

    public boolean checkSimilarProfileDiffirentWithEachUser() {
        ArrayList<String> name = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waitElementByXpath("//android.widget.HorizontalScrollView//android.view.ViewGroup[contains(@content-desc,'undefined')]");
            ArrayList<MobileElement> listNameSimilar = new ArrayList<>(driver.findElements(By.xpath("//android.widget.HorizontalScrollView//android.view.ViewGroup[contains(@content-desc,'undefined')]/following-sibling::android.widget.TextView[1]")));
            name.add(listNameSimilar.get(0).getText());
            name.add(listNameSimilar.get(1).getText());
            waitDisplayButtonXpathAndClick("btnNextProfileSimilar");
            waitDisplayButtonXpathAndClick("btnNextProfileSimilar");
        }
        System.out.println(name);
        return !name.equals(stack);
    }

    public boolean doNotShowFieldLevoileAndConvertiAtProfile() {

        return true;
    }

    public boolean checkValueOfOrigineOnProfile() {
        //get origine at field of user
        String origine = driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup/android.widget.TextView[@text='Origine :']/following-sibling::android.widget.TextView[1]")).getText();
        String[] listOri = {origine};
        if (origine.contains(",")) {
            listOri = origine.split(", ");
        }
        ArrayList<String> ori = new ArrayList<>(Arrays.asList(listOri));
        System.out.println(ori);
        //check origine at top profile
        if (!ori.isEmpty()) {
            MobileElement ele = driver.findElement(By.xpath("//android.widget.ScrollView"));
            swipe("down", ele, 0.9);
            swipe("down", ele, 0.9);
            ArrayList<MobileElement> flagOri = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//*[contains(@text,'Mes pays d’origine')]/following-sibling::android.widget.ImageView/following-sibling::android.widget.TextView[1]")));
            for (MobileElement x : flagOri) {
                if (!ori.contains(x.getText())) {
                    System.out.println(x.getText());
                    return false;
                }
            }
        }
        return true;
    }

    public boolean thePageShowAllProfileThatHaveAbovePilosité() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("Langues :");
            waitAboutSeconds(1);
            String pilosite = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='Pilosité :']/following-sibling::android.widget.TextView[1]")).getText();
            if (!stack.contains(pilosite)) {
                System.out.println(pilosite);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public void filterManWithCriteriaLeVoile() {
        iScrollUpToText("Le voile");
        iClickButtonHasText("Le voile");
        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
        ArrayList<MobileElement> mobileElements = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")));
        Random random = new Random();
        int index = 1 + random.nextInt(2);
        System.out.println(index);
        switch (index) {
            case 1:
                mobileElements.get(1).click();
                stack.add(mobileElements.get(1).getText());
                break;
            case 2:
                mobileElements.get(0).click();
                stack.add(mobileElements.get(0).getText());
                mobileElements.get(1).click();
                stack.add(mobileElements.get(1).getText());
                break;
        }
        waitDisplayButtonXpathAndClick("btnTick");
    }

    public boolean thePageShowAllProfileThatHaveAboveLeVoile() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("Veut des enfants :");
            waitAboutSeconds(1);
            String Levoile = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='Le voile :']/following-sibling::android.widget.TextView[1]")).getText();

            if (!stack.contains(Levoile)) {
                System.out.println(Levoile);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public boolean displayErrorMustSelectOneOptionInFieldSearch() {
        String error = "Sélectionnez un champ pour valider";
        String actual = driver.findElement(By.xpath("//android.widget.ScrollView/following-sibling::android.view.ViewGroup[1]/android.widget.TextView")).getText();
        System.out.println("text display at search field :" + actual);
        return actual.equals(error);
    }

    public boolean thePageShowAllProfileThatHaveAboveSilhouette() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("Silhouette");
            waitAboutSeconds(1);
            String Silhouette = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='Silhouette :']/following-sibling::android.widget.TextView[1]")).getText();
            System.out.println(Silhouette);
            if (!stack.contains(Silhouette) && !Silhouette.equals("")) {
                System.out.println(Silhouette);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public boolean thePageShowAllProfileThatHaveAboveSilhouetteFamiliale() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("Situation familiale :");
            waitAboutSeconds(1);
            String Silhouette = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='Situation familiale :']/following-sibling::android.widget.TextView[1]")).getText();
            System.out.println(Silhouette);
//            if(Silhouette.equals("veuf")){
//                Silhouette = "Veuve";
//            }
            if (!stack.contains(Silhouette) && !Silhouette.equals("")) {
                System.out.println(Silhouette);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public boolean thePageShowAllProfileThatHaveAboveEnfants() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("A des enfants :");
            waitAboutSeconds(1);
            String enfants = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='A des enfants :']/following-sibling::android.widget.TextView[1]")).getText();
            System.out.println(enfants);
            if (!stack.contains(enfants) && !enfants.equals("")) {
                System.out.println(enfants);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public boolean thePageShowAllProfileThatHaveAboveFumeur() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("Tabac :");
            waitAboutSeconds(1);
            String fumeur = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='Tabac :']/following-sibling::android.widget.TextView[1]")).getText();
            if (fumeur.equals("J’essaie d’arrêter")) {
                fumeur = "Il essaie d’arrêter";
            }
            System.out.println(fumeur);
            if (!stack.contains(fumeur) && !fumeur.equals("")) {
                System.out.println(fumeur);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public boolean thePageShowAllProfileThatHaveAboveNiveau() {
        for (int i = 1; i < 6; i++) {
            clickOnTheUserProfile();
            iScrollUpToText("Niveau d");
            waitAboutSeconds(1);
            String niveau = driver.findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView[contains(@text,'Niveau d')]/following-sibling::android.widget.TextView[1]")).getText();
            if (!stack.contains(niveau) && !niveau.equals("")) {
                System.out.println(niveau);
                return false;
            }
            waitDisplayButtonXpathAndClick("discoveryPage");
            iScrollUp();
            iScrollUp();
        }
        return true;
    }

    public boolean shouldBeShowUserBoostedProfileAtTopOfList() {
        waitAboutSeconds(1);
        //lisst green star after choose deny location
        ArrayList<MobileElement> starGreen = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[contains(@content-desc,'undefined')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup")));
        System.out.println("number profile show at top after login :" + starGreen.size());
        return starGreen.size() != 0;
    }

    public boolean showModalBoostProfileRandom() {
        Set<String> nameAndBoost = new HashSet<>();
        int count = 0;
        //get text(name) of 12 profile on the top
        while (count < 12) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.view.ViewGroup[1]/following-sibling::android.widget.TextView[1]")));
            for (MobileElement name : list) {
                if (!name.getText().equals("Rencontre") && !name.getText().equals("CRITÈRES")) {
                    nameAndBoost.add(name.getText());
                }
            }
            count = nameAndBoost.size();
            if (count == 12) {
                break;
            }
            iScrollUp();
        }
        System.out.println(nameAndBoost);
        return nameAndBoost.contains("Augmentez vos visites !");
    }

    public boolean clickBoostProfileAtMyProfileShowModal() {
        //display modal boost profile
        waitElementByXpath("//android.view.ViewGroup[@content-desc='icon-back']");
        ArrayList<MobileElement> fieldEnterCode = new ArrayList<>(driver.findElements(By.xpath("//android.widget.EditText")));
        if (fieldEnterCode.size() == 0 || !fieldEnterCode.get(0).getText().equals("Code d'importation")) {
            System.out.println("actual error :" + fieldEnterCode.get(0).getText());
            return false;
        }
        //enter empty code
        iScrollUpToText("VALIDER VOTRE CODE");
        iClickButtonHasText("VALIDER VOTRE CODE");
        waitAboutSeconds(1);
        ArrayList<MobileElement> errorEmptyCode = new ArrayList<>(driver.findElements(By.xpath("//android.widget.EditText/parent::android.view.ViewGroup/following-sibling::android.widget.TextView")));
        if (errorEmptyCode.size() == 0 || !errorEmptyCode.get(0).getText().equals("Code d’accès est requis.")) {
            System.out.println("actual error :" + errorEmptyCode.get(0).getText());
            return false;
        }
        //enter valid code
        fieldEnterCode.get(0).sendKeys("Code test invalid");
        iClickButtonHasText("VALIDER VOTRE CODE");

        return true;
    }

    public boolean showRedErrorMessageWhenEnterInvalidPseudo() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.className("android.widget.TextView")));
        System.out.println(list.size());
        System.out.println(list.get(list.size() - 2).getText());
        return list.get(list.size() - 2).getText().equals("La recherche de pseudo ne doit pas contenir de caractères spéciaux");
    }

    public boolean clickGreenButtonShowErrorEmptyNameSaveSearch() {
        //click green button
        waitDisplayButtonXpathAndClick("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup)[last()]");
        //check error message
        waitAboutSeconds(1);
        String errorActual = driver.findElement(By.xpath("//android.widget.EditText/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView")).getText();
        if (!errorActual.equals("Le nom est requis.")) {
            System.out.println("error actual :  " + errorActual);
            return false;
        }
        //close popup enter name save search
        waitDisplayButtonXpathAndClick("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[last()]/preceding-sibling::android.view.ViewGroup)[last()]");
        return true;
    }

    public void removeAllCriteriaAtSearchBox() {
        int count = 1;
        while (count != 0) {
            waitAboutSeconds(1);
            ArrayList<MobileElement> listCriteriaSearchBox = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='4']/android.view.ViewGroup/android.view.ViewGroup")));
            count = listCriteriaSearchBox.size() - 1;
            listCriteriaSearchBox.get(0).click();
        }
        waitDisplayButtonXpathAndClick("btnValidate");
    }

    public void clickBackAtSearchFormBackToDiscovery() {
        iScrollUp();
        iScrollUp();
        waitAboutSeconds(1);
        int numberCriteria = Integer.parseInt(driver.findElement(By.xpath("//android.widget.ScrollView/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup[last()]//android.widget.TextView")).getText());
        System.out.println("number criteria :   " + numberCriteria);
        Assert.assertEquals("number criteria does not match", 0, numberCriteria);
    }

    public boolean showModalEncourageUser() {
        waitAboutSeconds(1);

        ArrayList<MobileElement> closeEmail = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("closeModalValidateEmail"))));
        System.out.println("list size close email :" + closeEmail.size());

        if (closeEmail.size() != 0) {
            System.out.println("modal validate email");
            closeEmail.get(0).click();
            waitDisplayButtonXpathAndClick("messageIcon");
            waitElementByXpath("//*[contains(@text,'Messages')]");
            waitDisplayButtonXpathAndClick("discoveryPage");
            waitAboutSeconds(1);

        }
//        waitElementByXpath("btnSaveFilter2");
        ArrayList<MobileElement> closeEncourageUser = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("closeModalEncourageUser"))));
        System.out.println("list size close encourage :" + closeEncourageUser.size());
        if (closeEncourageUser.size() != 1) {
            System.out.println("model encourage user don't show");
            return false;
        }
        return true;
    }

    public void clickFillModalEncourageAndClose() {
        //click button fill
        iClickButtonHasText("REMPLIR");
        //wait show modal and close
        waitElementByXpath("//android.widget.RadioButton");
        driver.findElementByAccessibilityId("icon-back").click();
    }

    public void clickChangeEmailAtModalValidateEmail() {
        //click change email
        waitElementByXpath("//*[contains(@text,'JE SOUHAITE MODIFIER MON EMAIL')]");
        iClickButtonHasText("JE SOUHAITE MODIFIER MON EMAIL");
        //close modal change email
        waitDisplayButtonXpathAndClick("//android.widget.ScrollView//android.view.ViewGroup[2]/android.widget.TextView");
    }

    public boolean showScreenSelectEnfant() {
        waitElementByXpath("//android.widget.TextView[@text='Avez-vous des enfants ?']");
        ArrayList<MobileElement> optionIsSelected = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='Oui, 1 enfant']/following-sibling::android.widget.RadioButton/android.view.ViewGroup/android.view.ViewGroup")));
        System.out.println(optionIsSelected.size());
        return optionIsSelected.size() == 1;
    }

    public void alwaysShowFourListProfileAndShowBoostButtonIfDoNotBoostedThumbnailModeration() {
        waitDisplayButtonXpathAndClick("discoveryPage");

        waitUntilDisplayElementByXpath("//android.webkit.WebView");

        ArrayList<MobileElement> listRecommend = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stRecommend"))));
        ArrayList<MobileElement> listOnline = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stOnline"))));
        ArrayList<MobileElement> listReverse = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stReverse"))));
        ArrayList<MobileElement> listSignUpRecently = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stSignUpRecently"))));

        ArrayList<MobileElement> blockBoostDiscovery = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("blockBoostProfileDiscovery"))));

        ArrayList<MobileElement> thumbnailYoutube = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("captionYouTubeDiscovery"))));

        ArrayList<MobileElement> moderationSession = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("moderationSession"))));


        ArrayList<MobileElement> titleListSignUpRecently = new ArrayList<>();
        System.out.println("before scroll discovery");

        while (titleListSignUpRecently.size() == 0) {
            if (listRecommend.size() == 0) {
                listRecommend = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stRecommend"))));
            }
            if (listOnline.size() == 0) {
                listOnline = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stOnline"))));
            }
            if (listReverse.size() == 0) {
                listReverse = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stReverse"))));
            }
            if (listSignUpRecently.size() == 0) {
                listSignUpRecently = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("profile1stSignUpRecently"))));
            }
            if (thumbnailYoutube.size() == 0) {
                thumbnailYoutube = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("captionYouTubeDiscovery"))));
            }
            if (moderationSession.size() == 0) {
                moderationSession = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("moderationSession"))));
            }
            titleListSignUpRecently = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@content-desc='Ils viennent de s’inscrire']")));

            iScrollUp();
        }

        //check display 4 list profile
        if (listRecommend.size() != 1 || listOnline.size() != 1 || listReverse.size() != 1 || listSignUpRecently.size() != 1) {
            System.out.println("list recommend : " + listRecommend.size());
            System.out.println("list online : " + listOnline.size());
            System.out.println("list reverse : " + listReverse.size());
            System.out.println("list  just signup : " + listSignUpRecently.size());
            Assert.fail();
        }

        //check show boost
        assertEquals(blockBoostDiscovery.size(), stackInt.peek());
        //check show thumbnail YouTube
        assertEquals(thumbnailYoutube.size(), 1, "don't show thumbnail YouTube at Discovery");
        //check show moderation session
        assertEquals(moderationSession.size(), 1, "don't show moderation session at Discovery");
    }

    public void visitRightPersonWhenClickProfileFromFourList() {
        String nameAtDiscovery;
        String ageAtDiscovery;
        String locationAtDiscovery;

        String ageAndLocation;
        //visit profile from list just signup
        //get infor at Discovery
        nameAtDiscovery = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-JUST_REGISTER']//android.widget.TextView[1]")).getText().trim();
        ageAndLocation = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-JUST_REGISTER']//android.widget.TextView[2]")).getText();
        ageAtDiscovery = ageAndLocation.substring(0, ageAndLocation.indexOf(",")).trim();
        locationAtDiscovery = ageAndLocation.substring(ageAndLocation.indexOf(",") + 1).trim();
        System.out.println("list sign up : ");
        System.out.println("name :  " + nameAtDiscovery + "\nage : " + ageAtDiscovery + "\nlocation :  " + locationAtDiscovery);
        System.out.println("------------------------------------------------");
        //visit profile and check
        waitDisplayButtonXpathAndClick("profile1stSignUpRecently");
        assertTrue(shouldShowAllInforOfprofile().contains(nameAtDiscovery)
                && shouldShowAllInforOfprofile().contains(ageAtDiscovery)
                && shouldShowAllInforOfprofile().contains(locationAtDiscovery));
        System.out.println(shouldShowAllInforOfprofile());
        waitDisplayButtonXpathAndClick("discoveryPage");

        //visit profile from list reverse
        scrollDownToElement("profile1stReverse");
        //get infor at Discovery
        nameAtDiscovery = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-REVERSE_MATCH']//android.widget.TextView[1]")).getText().trim();
        ageAndLocation = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-REVERSE_MATCH']//android.widget.TextView[2]")).getText();
        ageAtDiscovery = ageAndLocation.substring(0, ageAndLocation.indexOf(",")).trim();
        locationAtDiscovery = ageAndLocation.substring(ageAndLocation.indexOf(",") + 1).trim();
        System.out.println("list reverse : ");
        System.out.println("name :  " + nameAtDiscovery + "\nage : " + ageAtDiscovery + "\nlocation :  " + locationAtDiscovery);
        System.out.println("------------------------------------------------");
        //visit profile and check
        waitDisplayButtonXpathAndClick("profile1stReverse");
        assertTrue(shouldShowAllInforOfprofile().contains(nameAtDiscovery)
                && shouldShowAllInforOfprofile().contains(ageAtDiscovery)
                && shouldShowAllInforOfprofile().contains(locationAtDiscovery));
        System.out.println(shouldShowAllInforOfprofile());
        waitDisplayButtonXpathAndClick("discoveryPage");

        //visit profile from list online
        scrollDownToElement("profile1stOnline");
        //get infor at Discovery
        nameAtDiscovery = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-ONLINE']//android.widget.TextView[1]")).getText().trim();
        ageAndLocation = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-ONLINE']//android.widget.TextView[2]")).getText();
        ageAtDiscovery = ageAndLocation.substring(0, ageAndLocation.indexOf(",")).trim();
        locationAtDiscovery = ageAndLocation.substring(ageAndLocation.indexOf(",") + 1).trim();


        System.out.println("list online : ");
        System.out.println("name :  " + nameAtDiscovery + "\nage : " + ageAtDiscovery + "\nlocation :  " + locationAtDiscovery);
        System.out.println("------------------------------------------------");
        //visit profile and check
        waitDisplayButtonXpathAndClick("profile1stOnline");
        assertTrue(shouldShowAllInforOfprofile().contains(nameAtDiscovery)
                && shouldShowAllInforOfprofile().contains(ageAtDiscovery)
                && shouldShowAllInforOfprofile().contains(locationAtDiscovery));
        System.out.println(shouldShowAllInforOfprofile());
        waitDisplayButtonXpathAndClick("discoveryPage");

        //visit profile from list recommend
        scrollDownToElement("profile1stRecommend");
        //get infor at Discovery
        nameAtDiscovery = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-RECOMMEND']//android.widget.TextView[1]")).getText().trim();
        ageAndLocation = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='item-1-RECOMMEND']//android.widget.TextView[2]")).getText();
        ageAtDiscovery = ageAndLocation.substring(0, ageAndLocation.indexOf(",")).trim();
        locationAtDiscovery = ageAndLocation.substring(ageAndLocation.indexOf(",") + 1).trim();


        System.out.println("list recommend : ");
        System.out.println("name :  " + nameAtDiscovery + "\nage : " + ageAtDiscovery + "\nlocation :  " + locationAtDiscovery);
        System.out.println("------------------------------------------------");
        //visit profile and check
        waitDisplayButtonXpathAndClick("profile1stRecommend");
        assertTrue(shouldShowAllInforOfprofile().contains(nameAtDiscovery)
                && shouldShowAllInforOfprofile().contains(ageAtDiscovery)
                && shouldShowAllInforOfprofile().contains(locationAtDiscovery));
        System.out.println(shouldShowAllInforOfprofile());
    }

    public void showSkipButtonAndLoveButtonWhenScrollToEndPage() {
        iScrollUpToText("Soyez respectueux & bienveillant.");
        boolean skipProfileBtn = driver.findElement(By.xpath(TestDataService.properties.getProperty("skipProfileBtn"))).isDisplayed();
        boolean loveProfileBtn = driver.findElement(By.xpath(TestDataService.properties.getProperty("loveProfileBtn"))).isDisplayed();

    }

    public void blockUserFromProfileShouldShowAtBlacklist() {
    }

    public void reportProfileAndOnlyReportOneTime() {
    }

    public void reactContentOfProfileWithName(String name) {
    }

    public void filterOnlineBirthdayOrigineRegionAndCheckResult() {
    }

    public void filterAgeProfileRecentAndCheckResult() {
    }

    public void filterHasPhotoTallEnfantAndCheckResult() {
    }

    public void filterPratiquantFemeurStudyAndCheckResult() {
    }

    public void saveSearchEditSearchRenameAndRemoveSaveSearch() {
    }

    public List<String> shouldShowAllInforOfprofile() {
        waitUntilDisplayElementByXpath("//android.widget.ImageView");
        String name = driver.findElement(By.xpath(TestDataService.properties.getProperty("nameProifle"))).getText();
        String age = driver.findElement(By.xpath(TestDataService.properties.getProperty("ageProifle"))).getText();
        String status = driver.findElement(By.xpath(TestDataService.properties.getProperty("statusProifle"))).getText();
        boolean hasLocation = driver.findElement(By.xpath(TestDataService.properties.getProperty("locationProifle"))).isDisplayed();
        String locationProifle = driver.findElement(By.xpath(TestDataService.properties.getProperty("locationProifle"))).getText();
//        String origine = driver.findElement(By.xpath(TestDataService.properties.getProperty("origineProifle"))).getText();
        return Arrays.asList(name, age, status, Boolean.toString(hasLocation), locationProifle);
    }
}