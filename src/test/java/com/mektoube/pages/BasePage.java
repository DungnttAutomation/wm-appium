package com.mektoube.pages;

import com.mektoube.config.AppiumConfig;
import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.function.Function;

import static com.mektoube.config.AppiumConfig.getDriver;
import static io.appium.java_client.touch.offset.PointOption.point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasePage {
    static final AppiumDriver<MobileElement> driver = getDriver();
    static final MobilePlatform OS = AppiumConfig.getOS();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    //    WebDriverWait waitIOS = new WebDriverWait(driver, 5);
    Stack<String> stack = new Stack<>();
    static Stack<Integer> stackInt = new Stack<>();

    public void waitAboutSeconds(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void navigateToUrl(String url) {
//        logger.info("Navigating to URL..." + url);
        String xPathElement = TestDataService.properties.getProperty(url);
        if (xPathElement == null) {
            xPathElement = url;
        }
        driver.navigate().to(xPathElement);
    }
    public void webDriverWaitForElementPresentByCss(String element, long timeout) {
//        logger.info("webDriverWaitForElementPresentByCss");
//        String xPathElement = PropertiesFile.getPropValue(element);
        String xPathElement = TestDataService.properties.getProperty(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(xPathElement)));
    }
    public void sendKeysByCss(String element, String content) {
//        logger.info("send keys" + element + "with " + content);
        String xPathElement1 = TestDataService.properties.getProperty(element);
        String xPathElement2 = TestDataService.properties.getProperty(content);
//        String xPathElement1 = PropertiesFile.getPropValue(element);
//        String xPathElement2 = PropertiesFile.getPropValue(content);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        if (xPathElement2 == null) {
            xPathElement2 = content;

        }
        driver.findElement(By.cssSelector(xPathElement1)).sendKeys(xPathElement2);
    }
    public void clickByCss(String element) {
//        logger.info("click" + element);
        String xPathElement = TestDataService.properties.getProperty(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        driver.findElement(By.cssSelector(xPathElement)).click();

    }
    private static void until(Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
//        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    public void untilJqueryIsDone(Long timeoutInSeconds) throws InterruptedException {
        until((d) ->
        {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
        Thread.sleep(1000);
    }

    public void waitForVisibilityOf(By IOSby, By AndroidBy) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, 30);
        if (OS == MobilePlatform.IOS) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(IOSby));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(AndroidBy));
        }
    }

    public boolean isElementPresent(MobileElement element, int timeout) {
        WebDriverWait wait;
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    MobileElement parallelGetElement(By forIOS, By forAndroid) {
        waitForVisibilityOf(forIOS, forAndroid);
        if (OS == MobilePlatform.IOS) {
            return driver.findElement(forIOS);
        } else {
            return driver.findElement(forAndroid);
        }
    }

    // Use this method can lead to fragile tests. Only use this if no other way
    protected MobileElement getElementByXPath(String name) {
        String elementName;
        if (OS == MobilePlatform.IOS) {
            elementName = String.format("//*[@name=\"%s\"]", name);
        } else {
            elementName = String.format("//*[@content-desc=\"%s, \"]", name);
        }

        return driver.findElementByXPath(elementName);
    }

    // Use this method if testing element has accessibility ID on both Android and IOS
    protected MobileElement getElementByAccessibleId(String accessibleId) {
        String elementName;
        if (OS == MobilePlatform.IOS) {
            elementName = accessibleId;
        } else {
            elementName = accessibleId + ",";
        }
        return driver.findElementByAccessibilityId(elementName);
    }

    public void iClickButtonHasText(String textOfElement) {
        if (OS == MobilePlatform.ANDROID) {
            String xpathElement = TestDataService.properties.getProperty(textOfElement);
            if (xpathElement == null) {
                xpathElement = textOfElement;
            }
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@text,'" + textOfElement + "')]"))).click();
            System.out.println("Click button '" + textOfElement + "'");
        }
        if (OS == MobilePlatform.IOS) {
            String xpathElement = TestDataService.properties.getProperty(textOfElement);
            if (xpathElement == null) {
                xpathElement = textOfElement;
            }
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeOther[@name=\"" + textOfElement + "\"]"))).click();
            System.out.println("Click button '" + textOfElement + "'");
        }
    }

    public void waitUntilDisplayButtonHasText(String textOfElement) {
        if (OS == MobilePlatform.IOS) {
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeOther[@name=\"" + textOfElement + "\"]"))).click();
        }
    }

    public void waitUntilDisplayElementByXpath(String element) {
        String xpathElement = TestDataService.properties.getProperty(element);
        if (xpathElement == null) {
            xpathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));
//        System.out.println("Display Element");
    }

    public void clickButtonByXpath(String xpathButton) {
        String xpathElement = TestDataService.properties.getProperty(xpathButton);
        if (xpathElement == null) {
            xpathElement = xpathButton;
        }
        WebElement Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathElement)));
        Element.click();
    }

    public void waitElementHiddenByXpath(String xpathElement) {
        String element = TestDataService.properties.getProperty(xpathElement);
        if (element == null) {
            element = xpathElement;
        }
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
    }

    public void clearTextAndInsertTextIntoField(String text, String xpath) {
        String xpathElement = TestDataService.properties.getProperty(xpath);
        if (xpathElement == null) {
            xpathElement = xpath;
        }
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));
//        element.click();
        element.clear();
        element.sendKeys(text);
//        if (OS==MobilePlatform.IOS){
            hideKeyboard();
//        }

    }
    public void clearTextAndInsertTextIntoFieldNotHideKey(String text, String xpath) {
        String xpathElement = TestDataService.properties.getProperty(xpath);
        if (xpathElement == null) {
            xpathElement = xpath;
        }
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));
//        element.click();
//        element.clear();
//        element.sendKeys(text);


    }

    public void hideKeyboard(){
        if (OS==MobilePlatform.ANDROID){
            driver.hideKeyboard();
        }else {
            waitUntilDisplayElementByXpath("//XCUIElementTypeKey");
//            MobileElement keyboard = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeKey/parent::XCUIElementTypeOther")));

//            int x= keyboard.getLocation().getX() +(keyboard.getSize().getWidth()/2);
//            int y= keyboard.getLocation().getY() -5;
//
//            System.out.println("dimision :  "+x + "     y  :"+y );
//
//            new TouchAction(driver)
//                    .tap(point(x,y))
//                    .perform();
        }
    }

    public void waitDisplayButtonXpathAndClick(String xpath) {
        String btn = TestDataService.properties.getProperty(xpath);
        if (btn == null) {
            btn = xpath;
        }
        waitAboutSeconds(1);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(btn))).click();
    }
    public void openUrl() {
        driver.get("https://staging.mektoube.fr");
    }

    public void buttonDontDisplay(String xpath) {
        String btn = TestDataService.properties.getProperty(xpath);
        if (btn == null) {
            btn = xpath;
        }
        ArrayList<MobileElement> xpathbutton = new ArrayList<>(driver.findElements(By.xpath(btn)));
        if (xpathbutton.isEmpty()) {
            System.out.println(xpath + "do not display");
        } else System.out.println(xpath + " is displayed in DOM");
    }

    public void swipe(String direction, MobileElement element, double swipeToScreen) {
        waitAboutSeconds(1);
        Dimension dimension = driver.manage().window().getSize();
//        System.out.println(dimension);
        int startX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
//        System.out.println("location x of element :" + startX);
        int startY = element.getLocation().getY() + (element.getSize().getHeight() / 2);
//        System.out.println("location Y of element :" + startY);

        int swipe1 = dimension.getWidth();
        int swipe2 = dimension.getHeight();

        switch (direction) {
            case "left":
                new TouchAction<>(driver).press(point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point((int) (swipe1 * swipeToScreen), startY))
                        .release()
                        .perform();
                System.out.println("swipe left........");
                break;
            case "right":
                new TouchAction<>(driver).press(point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point((int) (swipe1 * swipeToScreen), startY))
                        .release()
                        .perform();
                System.out.println("swipe right........");
                break;
            case "up":
                new TouchAction<>(driver).press(point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(startX, (int) (swipe2 * swipeToScreen)))
                        .release()
                        .perform();
                System.out.println("swipe up........");
                break;
            case "down":
                new TouchAction<>(driver).press(point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(startX, (int) (swipe2 * swipeToScreen)))
                        .release()
                        .perform();
                System.out.println("swipe down........");
                break;
            default:
                System.out.println("pls choose left/right/up/down");
                break;
        }

    }

    public void waitElementByXpath(String element) {
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        String ele = TestDataService.properties.getProperty(element);
        if (ele == null) {
            ele = element;
        }
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
    }

    //*----------------Start - Just for iOS - do not write here-----------------*//

    public void theUserClickOn(String data) {
        String element = TestDataService.properties.getProperty(data);
        if (element == null) {
            element = data;
        }
        waitAboutSeconds(3);
//        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
//        Element.click();
    }

    public void click(String xpath) {
        String element = TestDataService.properties.getProperty(xpath);
        if (element == null) {
            element = xpath;
        }
        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        Element.click();
    }

    public void clickID(String data) {
        String element = TestDataService.properties.getProperty(data);
        if (element == null) {
            element = data;
        }
        waitAboutSeconds(3);
//        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
//        Element.click();
    }

    public boolean shouldSeeModal(String modal) {
        String element = TestDataService.properties.getProperty(modal);
        if (element == null) {
            element = modal;
        }
        return driver.findElementByXPath(element).isDisplayed();
    }

    public void shouldSeeAlertMessageWithContentIsShowedOnTop(String messageAlert) {
        if (OS == MobilePlatform.IOS) {
//            String innerText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeOther[@name='xmpp-message']/XCUIElementTypeOther"))).getText();
//            System.out.println(innerText);
//            assertEquals(messageAlert, innerText);
            waitElementHiddenByXpath("//XCUIElementTypeOther[@name='xmpp-message']");

        }
//        else {
//            String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView"))).getText();
//            assertEquals(messageAlert, message);
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView")));
//            System.out.println("Message is showed on top is: " + message);
//        }
    }

    public String getText(String dataIOS) {
        String element = TestDataService.properties.getProperty(dataIOS);
        if (element == null) {
            element = dataIOS;
        }
        return driver.findElementByXPath(element).getText();
    }

    public void checkElementWhichDisplayThenClick(String dataIOS1, String dataIOS2, String mess1, String mess2) {
        String element1 = TestDataService.properties.getProperty(dataIOS1);
        String element2 = TestDataService.properties.getProperty(dataIOS2);
        try {
            if (driver.findElementByXPath(element1).isDisplayed()) {
                waitAboutSeconds(1);
                driver.findElementByXPath(element1).click();
                System.out.println(mess1);
            }
        } catch (Exception e) {
            waitAboutSeconds(3);
            driver.findElementByXPath(element2).click();
            System.out.println(mess2);
        }
    }

    public void checkElementIsDisplay(String data) {
        String element = TestDataService.properties.getProperty(data);
        if (element == null) {
            element = data;
        }
        waitAboutSeconds(3);
        driver.findElementByXPath(element).isDisplayed();
    }

    public String subStringGetFirstWord(String xpathButton) {
        String xpathElement = TestDataService.properties.getProperty(xpathButton);
        if (xpathElement == null) {
            xpathElement = xpathButton;
        }
        WebElement Element = driver.findElementByXPath(xpathElement);
        String getFirstWord = Element.getText();
        if (getFirstWord.contains(" ")) {
            getFirstWord = getFirstWord.substring(0, getFirstWord.indexOf(" "));
        }
        return getFirstWord;
    }

    public void WriteToFile(String value) {
        try {
            FileWriter fw = new FileWriter("varFile.txt", false);
            fw.write(value);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Write To File success...");
    }

    public String ReadFromFile() throws Exception {
        FileReader fr = new FileReader("varFile.txt");
        int i;
        String var = "";
        System.out.print("Read From File: ");
        while ((i = fr.read()) != -1) {
            System.out.print((char) i);
            var = var + (char) i;
        }
        System.out.print("\n");
        fr.close();
        return var;
    }

    public void countNumberList(String data) {
        waitAboutSeconds(10);
        List<MobileElement> xpath = driver.findElements(By.xpath(TestDataService.properties.getProperty(data)));
        int xpathCount = xpath.size();
        System.out.println("Total xpath: " + xpathCount);
    }

    public boolean elementShouldBeDisplay(String element) {
        String Element = TestDataService.properties.getProperty(element);
        if (Element == null) {
            Element = element;
        }
        waitAboutSeconds(3);
        getDriver().findElement(By.xpath(Element)).isDisplayed();
        return true;
    }

    public void selectFlagOfCountry(String country) {
        waitDisplayButtonXpathAndClick("flagCountry");
        waitAboutSeconds(1);
        waitElementByXpath("flagCountry");
        clearTextAndInsertTextIntoField(country, "//android.view.ViewGroup[@content-desc='icon-back']/following-sibling::android.view.ViewGroup/android.widget.EditText");
        waitAboutSeconds(1);
        String xpathFirstFlag = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]//android.widget.ImageView";
        waitDisplayButtonXpathAndClick(xpathFirstFlag);
    }

    //*----------------End - Just for iOS - do not write here-----------------*//

    public void alertMessageWithContentIsShowedOnTop(String messageContent) {
//        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView")));
//        System.out.println("Message is showed on top is: " + message);
//        if (!message.equals("« Vous avez déjà signalé ce profil, le signalement a déjà été pris en compte »")) {
//            assertEquals(messageContent, message.getText());
//        }
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView")));
    }

    public void logoutCurrentAccount() {
        clickButtonByXpath("myProfileBtn");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View//android.view.View[@index='3']//android.view.ViewGroup[@index='2']/android.widget.TextView"))).click();
        waitAboutSeconds(2);
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"connecter\").instance(0))")).click();
        waitAboutSeconds(5);
    }

    public void changeEnvironmentTo(String env) {
        waitUntilDisplayElementByXpath("seConnecterBtn");
        MobileElement envButton = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"secretBtn\"]"));
        envButton.click();
        envButton.click();
        String xpathElement = TestDataService.properties.getProperty(env);
        if (xpathElement == null) {
            xpathElement = env;
        }
//android.view.ViewGroup[@content-desc='register-button']/preceding-sibling::android.view.ViewGroup[2]
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathElement))).click();
        iClickButtonHasText("Validate");
    }

    public void logoutAccount() {
        // get location of device
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = (int) (driver.manage().window().getSize().getHeight() * 0.15);
        int x = driver.manage().window().getSize().getWidth() / 2;

        waitDisplayButtonXpathAndClick("myProfileBtn");
        waitAboutSeconds(1);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='FERMER']")));
        if (list.size() == 1) {
            list.get(0).click();
        }
        waitAboutSeconds(2);
        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();
        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        iScrollUpToText("Se déconnecter");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));
//        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"connecter\").instance(0))")).click();
        waitDisplayButtonXpathAndClick("//*[contains(@text,'Se déconnecter')]");
        System.out.println(3);
    }

    public void logoutCurrentAccountAndLoginAccountAndPassword(String username, String pass) {
        // get location of device
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = (int) (driver.manage().window().getSize().getHeight() * 0.15);
        int x = (driver.manage().window().getSize().getWidth() / 2);

        waitDisplayButtonXpathAndClick("myProfileBtn");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='FERMER']")));
        if (list.size() == 1) {
            list.get(0).click();
        }

        waitAboutSeconds(3);
        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        ArrayList<MobileElement> listFavoris = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Retrouvez vos profils favoris')]")));
        if (listFavoris.size() == 1) {
            System.out.println("Ok đã swipe to favourites");
        } else {
            new TouchAction<>(driver).press(point(x, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(point(x / 50, y))
                    .release()
                    .perform();
            System.out.println("Need swipe again");
        }

        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        ArrayList<MobileElement> listStats = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'De votre profil')]")));
        if (listStats.size() == 1) {
            new TouchAction<>(driver).press(point(x, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(point(x / 50, y))
                    .release()
                    .perform();
            System.out.println("Swipe 3 times fail >> must one more");
        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"connecter\").instance(0))")).click();
        waitUntilDisplayElementByXpath("//*[contains(@text,'SE CONNECTER')]");
        System.out.println("In Login Page method");
        iClickButtonHasText("SE CONNECTER");
        waitAboutSeconds(3);
        clearTextAndInsertTextIntoField(username, "emailInput");
        clearTextAndInsertTextIntoField(pass, "passwordInput");
        iClickButtonHasText("ME CONNECTER");
        waitAboutSeconds(3);
//        new DiscoveryPage().closeModalEncourageUser();
        waitUntilDisplayElementByXpath("searchBtn");
    }

    public void isDisappeared(String xpath) {
        String xpathElement = TestDataService.properties.getProperty(xpath);
        if (xpathElement == null) {
            xpathElement = xpath;
        }
        waitAboutSeconds(3);
        ArrayList<MobileElement> element = new ArrayList<>(driver.findElements(By.xpath("xpathElement")));
        assertEquals(0, element.size());
    }

    public void takeAScreenshot() throws IOException {
        File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temp, new File("my3.png"));
    }

    public void seeRedMessageUnderField(String message, String field) {
        String xpathText = TestDataService.properties.getProperty(field) + "/following-sibling::android.view.ViewGroup/android.widget.TextView";
        System.out.println("Xpath Text: " + xpathText);
        waitAboutSeconds(1);
        String actualMessage = driver.findElement(By.xpath(xpathText)).getText();
        System.out.println("Red message: " + actualMessage);
        assertEquals(message, actualMessage);
    }

    public void waitUntilDontSeeThisElementInScreen(String element) {
        ArrayList<MobileElement> abc = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty(element))));
        assertTrue(abc.size() < 1);
        System.out.println("Pass >>>>>>>>>>> This element is disappear");
    }

    public void scrollDownToRefreshPage() {
        MobileElement ele = driver.findElement(By.xpath("//android.widget.ScrollView"));
        swipe("down", ele, 0.99);
        waitAboutSeconds(2);
    }

    public void goToSettingTabAndClickButtonByText(String text) {
        // get location of device
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = (int) (driver.manage().window().getSize().getHeight() * 0.15);
        int x = driver.manage().window().getSize().getWidth() / 2;

        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='FERMER']")));
        if (list.size() == 1) {
            list.get(0).click();
        }

        waitAboutSeconds(3);
        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        ArrayList<MobileElement> listFavoris = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Retrouvez vos profils favoris')]")));
        if (listFavoris.size() == 1) {
            System.out.println("Ok đã swipe to favourites");
        } else {
            new TouchAction<>(driver).press(point(x, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(point(x / 50, y))
                    .release()
                    .perform();
            System.out.println("Need swipe again");
        }

        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();
        new TouchAction<>(driver).press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(point(x / 50, y))
                .release()
                .perform();

        ArrayList<MobileElement> listStats = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'De votre profil')]")));
        if (listStats.size() == 1) {
            new TouchAction<>(driver).press(point(x, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(point(x / 50, y))
                    .release()
                    .perform();
            System.out.println("Swipe 3 times fail >> must one more");
        }

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))")).click();
    }

    public MobileElement callElement(String xPath) {
        String btn = TestDataService.properties.getProperty(xPath);
        if (btn == null) {
            btn = xPath;
        }
        return driver.findElementByXPath(btn);
    }

    public void getNicknameOfUser(String username) {
        waitAboutSeconds(10);
        String getPseudo = driver.findElement(By.xpath(TestDataService.properties.getProperty(username))).getText().toLowerCase(Locale.ROOT);
        System.out.println("The first profile that we found is: " + getPseudo);
        WriteToFile(getPseudo);
    }

    public void getOfUser(String username) {
        waitAboutSeconds(10);
        String getPseudo = driver.findElement(By.xpath(TestDataService.properties.getProperty(username))).getText().toLowerCase(Locale.ROOT);
        System.out.println("The first profile that we found is: " + getPseudo);
        WriteToFile(getPseudo);
    }

    public void waitUntilIsInvisiable(String xpath) {
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void closeModalRequestValidateEmail() {
        waitElementByXpath("//*[contains(@text,'ENVOYER UN LIEN DE VALIDATION')]");
        ArrayList<MobileElement> elements = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'ENVOYER UN LIEN DE VALIDATION')]")));
        if (elements.size() == 1) {
            System.out.println("Account nay chua validate email");
            clickButtonByXpath("closeBlackListModalBtn");
        } else {
            System.out.println("Account nay da validate email");
        }
    }

    public void checkNameOfAPartnerIsNotDisplayedInFull() {
        ArrayList<MobileElement> name = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'…')]")));
        int i = 1;
        if (name.size() > 1) {
            name.get(i).getText();
            System.out.println("Name partner is hide: " + name.get(i).getText());
        }
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
            } else
                new TouchAction<>(driver).press(point(x, y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(x, y / 50))
                        .release()
                        .perform();
            waitAboutSeconds(1);
        }
    }

//    public void iScrollUpToText2(String field) {
//        int count = 0;
//        System.out.println("dimision :" + driver.manage().window().getSize());
//        int y = driver.manage().window().getSize().getHeight() / 2;
//        int x = driver.manage().window().getSize().getWidth() / 2;
//
//        while (count == 0) {
//            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'remplir')]/preceding-sibling::android.widget.TextView[@text='" + field + "']")));
//            if (list.size() == 1) {
//                list.get(0).click();
//                count = 1;
//            } else
//                new TouchAction<>(driver).press(PointOption.point(x, y))
//                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
//                        .moveTo(PointOption.point(x, y / 50))
//                        .release()
//                        .perform();
//            waitAboutSeconds(1);
//        }
//    }

    public void theUserScrollDownAndClick(String text) {
        iScrollUpToText(text);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + text + "')]")).click();
    }

    public void theUserGoToScreen(String text) {
        click("myProfile");
        click("reglages");
        waitAboutSeconds(1);
        driver.findElement(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[contains(@name,'" + text + "')]")).click();
        waitAboutSeconds(2);
        waitUntilDisplayElementByXpath("seConnecterBtn");
    }

    public void postPhotoFromGallery(String picture) {
        if (OS == MobilePlatform.IOS) {
            waitAboutSeconds(2);
            click(picture);
        }
        if (OS == MobilePlatform.ANDROID){
            String code = AppiumConfig.driver.getCapabilities().getCapability("udid").toString();
            System.out.println("Unique Device Identifier: " + code);
            if (code.equalsIgnoreCase("988a5b4347324d3649")) {
                //for Samsung S8 new
                click("albumTab");
                click("screenshotsAlbum");
                click(picture);
            } else if (code.equalsIgnoreCase("2588f26f")) {
                //Galaxy J2
                click("galleryIconGalaxyJ2");
                click("albumGalaxyJ2");
                click("pictureRandomGalaxyJ2");
            } else if (code.equalsIgnoreCase("04157df48c830518")) {
                //S6Edge
                click("albumsS6Edge");
                click("pictureRandomS6Edge");
            }
        }
    }

    public void takeAPhoto() {
        String code = AppiumConfig.driver.getCapabilities().getCapability("udid").toString();
        System.out.println("Unique Device Identifier: " + code);
        if (code.equalsIgnoreCase("988a5b4347324d3649")) {
            //for Samsung S8 new
            click("Cheer3");
            clickID("OK3");
        } else if (code.equalsIgnoreCase("2588f26f")) {
            //Galaxy J2
            click("CheerJ2");
            click("OKJ2");
        } else if (code.equalsIgnoreCase("04157df48c830518")) {
            //S6 Edge
            click("Cheer");
            click("OK");
        }
    }

    public void dontDisplayThisElement(String element) {
        ArrayList<WebElement> greencounter = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty(element))));
        System.out.println("green counter: " + greencounter);
        TestCase.assertEquals(0, greencounter.size());
    }

    public void showMessageErrorOfElement(String message, String element) {
//        WebElement elementShowMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty(element))));
//        assertEquals(message, elementShowMessage.getText());
    }

    public void enterVerifyCode(String code) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("firstCodeBox")))).sendKeys((code.substring(0, 1)));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("secondCodeBox")))).sendKeys((code.substring(1, 2)));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("thirdCodeBox")))).sendKeys((code.substring(2, 3)));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("4thCodeBox")))).sendKeys((code.substring(3, 4)));
//        clearTextAndInsertTextIntoField(code.substring(0, 1), "firstCodeBox");
//        clearTextAndInsertTextIntoField(code.substring(1, 2), "secondCodeBox");
//        clearTextAndInsertTextIntoField(code.substring(2, 3), "thirdCodeBox");
//        clearTextAndInsertTextIntoField(code.substring(3, 4), "4thCodeBox");
    }

    public void scrollUpToElement(String element) {
        String elementScroll = TestDataService.properties.getProperty(element);
        if (elementScroll == null) {
            elementScroll = element;
        }

        int count = 0;
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = driver.manage().window().getSize().getHeight() / 2;
        int x = driver.manage().window().getSize().getWidth() / 2;

        while (count == 0) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(elementScroll)));
            if (list.size() == 1) {
                count = 1;
            } else
                new TouchAction<>(driver).press(point(x, y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(x, y / 2))
                        .release()
                        .perform();
            waitAboutSeconds(1);
        }
    }

    public void scrollDownToElement(String element) {
        String elementScroll = TestDataService.properties.getProperty(element);
        if (elementScroll == null) {
            elementScroll = element;
        }

        int count = 0;
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = driver.manage().window().getSize().getHeight() / 2;
        int x = driver.manage().window().getSize().getWidth() / 2;

        while (count == 0) {
            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(elementScroll)));
            if (list.size() == 1) {
                count = 1;
            } else
                new TouchAction<>(driver).press(point(x, y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(x, y *2))
                        .release()
                        .perform();
            waitAboutSeconds(1);
        }
    }

    public void scrollUpToTextAt(String text, String option) {
        if (OS == MobilePlatform.ANDROID) {
            int count = 0;
            waitUntilDisplayElementByXpath("//android.view.ViewGroup[@content-desc='option-DE']");
            MobileElement element = driver.findElement(By.xpath("//android.widget.ScrollView"));
            while (count == 0) {
                ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'" + text + "')]")));
                if (list.size() == 1) {
                    count = 1;
                } else
                    swipe("up", element, 0.3);
            }
        }
        if (OS == MobilePlatform.IOS) {
            int count = 0;
            waitUntilDisplayElementByXpath("(//XCUIElementTypeOther[@name='option-DZ'])[2]");
            MobileElement element = driver.findElement(By.xpath(TestDataService.properties.getProperty(option)));
            while (count == 0) {
                ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("(//XCUIElementTypeOther[@name=\"" + text + "\"])")));
                System.out.println("Element found: " + list.size());
                //find out 3 elements so list.size must be > 1
                if (!list.isEmpty()) {
                    count = 1;
                } else
                    swipe("up", element, 0.3);
            }
        }

    }

    public void clickOnTheTab(String btn) {
        String xpathElement = TestDataService.properties.getProperty(btn);
        if (xpathElement == null) {
            xpathElement = btn;
        }
        waitAboutSeconds(3);
        MobileElement elem = (MobileElement) driver.findElementByXPath(xpathElement);

        int width = elem.getSize().getWidth();
        System.out.println("width of button: " + width);

        Actions act = new Actions(driver);
        act.moveToElement(elem).moveByOffset(25, 0).click().perform();
    }

    public void iEnterOnTextBox(String name, String textBox) {
        String xpath = TestDataService.properties.getProperty(textBox);
        if (xpath == null) {
            xpath = textBox;
        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(name);
    }

    public void resetDataTest() {
        //enable Notification permission
        driver.resetApp();
        ArrayList<MobileElement> allowNotiIOS =new ArrayList<>(driver.findElements(By.xpath("//XCUIElementTypeButton[@name='Allow']")));

        if (OS == MobilePlatform.ANDROID) {
            driver.resetApp();
            waitAboutSeconds(1);
            ArrayList<MobileElement> notifPermission = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@resource-id,'com.android.permissioncontroller')]")));
            if (!notifPermission.isEmpty()) {
                waitDisplayButtonXpathAndClick("//*[contains(@resource-id,'permission_allow_button')]");
                System.out.println("enable notifcation success");
            }
        }else if(OS==MobilePlatform.IOS & !allowNotiIOS.isEmpty()){
            waitAboutSeconds(2);
            allowNotiIOS.get(0).click();
            waitAboutSeconds(2);
            ArrayList<MobileElement> nextBtn = new ArrayList<>(driver.findElements(By.xpath("(//XCUIElementTypeStaticText[@name='Next'])[1]")));
            if (!nextBtn.isEmpty()){
                nextBtn.get(0).click();
            }
            ArrayList<MobileElement> startTesting = new ArrayList<>(driver.findElements(By.xpath("(//XCUIElementTypeStaticText[@name='Start Testing'])[1]")));
            if (!startTesting.isEmpty()){
                startTesting.get(0).click();
            }
        }
    }

    public void clickButtonByAction(String button) {
        String element = TestDataService.properties.getProperty(button);
        if (element == null) {
            element = button;
        }
        waitUntilDisplayElementByXpath(element);
        int x= driver.findElement(By.xpath(element)).getLocation().getX() +(driver.findElement(By.xpath(element)).getSize().getWidth()/2);
        int y= driver.findElement(By.xpath(element)).getLocation().getY();
        System.out.println("dimision :  "+x + "y  :"+y );
        new TouchAction(driver)
                .tap(point(x,y))
                .perform();
    }

    public void clickButtonNumberAtButton(int number, String button) {
        if (OS==MobilePlatform.IOS){
            String element = TestDataService.properties.getProperty(button);
            if (element == null) {
                element = button;
            }
            waitUntilDisplayElementByXpath(element);
            int x,y=0;
            switch (number) {
                case 1 :
                    x= driver.findElement(By.xpath(element)).getLocation().getX()+driver.findElement(By.xpath(element)).getSize().getWidth()/3;
                    y= driver.findElement(By.xpath(element)).getLocation().getY()+driver.findElement(By.xpath(element)).getSize().getHeight()/2;
                    System.out.println("dimision :  "+x + " y  :"+y );
                    new TouchAction(driver)
                            .tap(point(x,y))
                            .perform();
                    break;
                case 2 :
                    System.out.println(driver.manage().window().getSize());
                    x= driver.findElement(By.xpath(element)).getLocation().getX() +driver.findElement(By.xpath(element)).getSize().getWidth()*2/3;
                    y= driver.findElement(By.xpath(element)).getLocation().getY()+driver.findElement(By.xpath(element)).getSize().getHeight()/2;
                    System.out.println("dimision :  "+x + " y  :"+y );
                    new TouchAction(driver)
                            .tap(point(x,y))
                            .perform();
                    break;
            }
        }else {
            if (number==2){
                waitDisplayButtonXpathAndClick("phoneTab");
            }
        }
    }

    public void showMessageErrorAfterValidateWithInvalidDataOn(String element) {
        waitAboutSeconds(1);
        String ele = TestDataService.properties.getProperty(element);
        if(ele==null){
            ele=element;
        }
        MobileElement  mobileElement =  driver.findElement(By.xpath(ele));
        assertTrue(mobileElement.isDisplayed());
    }
}
