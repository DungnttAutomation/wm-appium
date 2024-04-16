package com.mektoube.pages;

import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;

public class MessageModule extends BasePage {
    DiscoveryPage discoveryPage = new DiscoveryPage();
    LoginPage loginPage = new LoginPage();


    public void clickInMenuBar(String icon) {
        String xpathElement = TestDataService.properties.getProperty(icon);
        if (xpathElement == null) {
            xpathElement = icon;
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(xpathElement);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));

        MobileElement Element = driver.findElement(By.xpath(xpathElement));
        Element.click();
    }

//    public void checkNumberOfNewMessagesWithCounter() {
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']")));
//
//        ArrayList<MobileElement> counterInMenu = new ArrayList<>(driver.findElements(By.xpath("//android.widget.Button [@content-desc=', tab, 3 of 5']/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView")));
//        if (counterInMenu.size() == 1) {
//            String numberIncounter = driver.findElement(By.xpath("//android.widget.Button [@content-desc=', tab, 3 of 5']/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView")).getText();
//            System.out.println("User has new message from: " + numberIncounter + " partner");
//
//            // filter new message
//            clickButtonByXpath("filterMessage");
//            iClickButtonHasText("Messages non lus");
//            waitAboutSeconds(2);
//            System.out.println("filter new message DONE");
//
//            ArrayList<MobileElement> greenCounter = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='5']/android.widget.TextView")));
//            System.out.println("Number of green counter is: " + greenCounter.size());
//
//            if ((Integer.parseInt(numberIncounter) <= 10)) {
//                assertEquals(Integer.parseInt(numberIncounter), greenCounter.size());
//            } else {
//                assertEquals("+10", numberIncounter);
//            }
//        } else {
//            ArrayList<MobileElement> greenCounter = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='4']/android.widget.TextView")));
//            assertEquals(0, greenCounter.size());
//            System.out.println("No new messages");
//        }
//    }

    public void clickThreadMessageIndexInThreadList(int index) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        waitAboutSeconds(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']")));
        System.out.println("Thread list is showed");

        ArrayList<MobileElement> listThreads = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));

        System.out.println("count list: " + listThreads.size());
        String partnerName = listThreads.get(index).findElement(By.xpath("//android.widget.TextView[@index='1']")).getText();
        System.out.println("partnerName in thread list is: " + partnerName);
        listThreads.get(index).click();

        String partnerNameInChatDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView[@index='1']"))).getText();
        System.out.println("partnerName in chat detail is: " + partnerNameInChatDetail);
        assertEquals(partnerName, partnerNameInChatDetail);
    }

    public void conversationIsDisplayed() {
        ArrayList<MobileElement> messages = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (messages.size() >= 1) {
            System.out.println("conversation is loaded");
        } else {
            System.out.println("conversation is empty");
        }
    }

    public void textMessageIsSendRealtime() {
        String btn = TestDataService.properties.getProperty("sentLastTextMessage");
        if (btn == null) {
            btn = "sentLastTextMessage";
        }
        ArrayList<MobileElement> timeSentUnderMessage = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup/following-sibling::android.widget.TextView")));
        System.out.println("time under message is: " + timeSentUnderMessage.get(0).getText());
        stack.push(timeSentUnderMessage.get(0).getText());
        String currentTime = java.time.LocalTime.now().toString().substring(0, 5);
        System.out.println("current time is: " + currentTime);
        System.out.println(timeSentUnderMessage.get(0).getText().substring(0, 2) + " compare hour " + currentTime.substring(0, 2));
        System.out.println(Integer.parseInt(timeSentUnderMessage.get(0).getText().substring(3, 5)) + " compare minute " + Integer.parseInt(currentTime.substring(3, 5)));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date d1 = sdf.parse(timeSentUnderMessage.get(0).getText());
            Date d2 = sdf.parse(currentTime);
            long elapsed = d2.getTime() - d1.getTime();
            System.out.println("time : " + elapsed);
            assertTrue(elapsed >= -60000 & elapsed <= 60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        //check if current time have hour > hour in under message, so check minute under message must be > minute in currentime
//        if (Integer.parseInt(currentTime.substring(0, 2)) > Integer.parseInt(timeSentUnderMessage.get(0).getText().substring(0, 2))) {
//            assertTrue(Integer.parseInt(timeSentUnderMessage.get(0).getText().substring(3, 5)) > Integer.parseInt(currentTime.substring(3, 5)));
//            System.out.println("Current time have hour > hour in under message");
//        }
//        // check if current time have hour == hour in under message, so check minute under message must be <= minute in currentime
//        else {
//            assertTrue(Integer.parseInt(timeSentUnderMessage.get(0).getText().substring(3, 5)) <= Integer.parseInt(currentTime.substring(3, 5)));
//            System.out.println("Current time have hour == hour in under message");
//        }
    }

    public void checkUnreadMessage() {
        ArrayList<MobileElement> counterInMenu = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='message-counter']/android.widget.TextView")));
        System.out.println("Counter: " + counterInMenu.size());
        if (counterInMenu.size() == 1) {
            ArrayList<MobileElement> greenCounter = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup/android.view.ViewGroup[contains(@content-desc,'undefined')]/following-sibling::android.view.ViewGroup[last()]/android.widget.TextView")));
            System.out.println("Number of green counter is: " + greenCounter.size());
            ArrayList<MobileElement> threads = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
            assertEquals(greenCounter.size(), threads.size() - 1);
            assertEquals(Integer.parseInt(counterInMenu.get(0).getText()), threads.size() - 1);
        } else {
            MobileElement greenButtonText = driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='2']/android.widget.TextView"));
            if (greenButtonText.getText().equalsIgnoreCase("AFFICHER TOUS LES MESSAGES")) {
                greenButtonText.click();
                waitAboutSeconds(2);
                ArrayList<MobileElement> threads = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
                assertTrue(threads.size() >= 1);
                System.out.println("show all thread after click green button AFFICHER TOUS LES MESSAGES");
            } else {
                greenButtonText.click();
                waitAboutSeconds(2);
                waitUntilDisplayElementByXpath("searchBtn");
                System.out.println("Im in discovery after click green button DÉCOUVRIR DES PROFILS");
            }
        }
    }

    public void swipeLeftInThreadIndexToDeleteThreadChat(int index) {
        waitAboutSeconds(3);
        ArrayList<MobileElement> threads = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));

        //Add partner name to stack to check thread is disappear after delete success - method check thatThreadIsDeletedSuccess()
        String partner = threads.get(index).findElement(By.xpath("//android.widget.TextView[@index='1']")).getText();
        stack.push(partner);
        waitAboutSeconds(1);

        //Add number in blue counter to stack to check number is reduce after remove a thread has new message
        String numberInBlueCounter = driver.findElement(By.xpath("//android.widget.Button[@content-desc=', tab, 3 of 5']/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView")).getText();
        stack.push(numberInBlueCounter);
        System.out.println("List string in stack: " + stack);
        System.out.println(stack.peek());
        System.out.println(stack.get(1));
        System.out.println(stack.get(0));

        Dimension dimension = threads.get(index).getSize();
        System.out.println(dimension);

        int Anchor = threads.get(index).getLocation().getY();

        int startSwipe = (int) Math.round(dimension.getWidth() * 0.7);
        System.out.println(startSwipe);

        int endSwipe = (int) Math.round(dimension.getWidth() * 0.5);
        System.out.println(endSwipe);

        //Swipe save filter display button
        new TouchAction(driver).press(PointOption.point(startSwipe, Anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endSwipe, Anchor))
                .release().perform();

        waitAboutSeconds(5);
        iClickButtonHasText("Supprimer");
//        alertMessageWithContentIsShowedOnTop("La discussion a été supprimée");
//        waitAboutSeconds(5);
    }

    public void thatThreadIsDeletedSuccess() {
        waitAboutSeconds(3);
        waitUntilIsInvisiable("//android.widget.TextView[contains(@text,'" + stack.get(1) + "')]");
        ArrayList<MobileElement> threads = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
        String partner = threads.get(1).findElement(By.xpath("//android.widget.TextView[@index='1']")).getText();
        assertNotEquals(stack.peek(), partner);
    }

    public void messageIsSendRealtime() {
        ArrayList<MobileElement> abc = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup/following-sibling::android.widget.TextView")));
        System.out.println("time under message is: " + abc.get(0).getText());
        String a = java.time.LocalTime.now().toString().substring(0, 5);
        System.out.println("current time is: " + a);
        System.out.println(abc.get(0).getText().substring(0, 2) + " compare hour " + a.substring(0, 2));
        System.out.println(Integer.parseInt(abc.get(0).getText().substring(3, 5)) + " compare minute " + Integer.parseInt(a.substring(3, 5)));
        assertEquals(abc.get(0).getText().substring(0, 2), a.substring(0, 2));
        assertTrue(Integer.parseInt(abc.get(0).getText().substring(3, 5)) <= Integer.parseInt(a.substring(3, 5)));
    }

    public void readNewestMessageAndCheckCounter() {
        // Check counter before read message
        waitAboutSeconds(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='1']//android.view.ViewGroup[@index='4']/android.widget.TextView")));
        ArrayList<MobileElement> greenCounter = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='1']//android.view.ViewGroup[@index='4']/android.widget.TextView")));
        assertEquals(1, greenCounter.size());
        String numberInBlueCounter = driver.findElement(By.xpath("//android.widget.Button[@content-desc=', tab, 3 of 5']/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView")).getText();
        System.out.println("number in blue counter before : " + numberInBlueCounter);

        clickThreadMessageIndexInThreadList(1);
        clickButtonByXpath("backBtnInConversation");

        // Check counter after read message
        waitAboutSeconds(2);
        ArrayList<MobileElement> greenCounterAfter = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView//android.view.ViewGroup[@index='1']//android.view.ViewGroup[@index='5']/android.widget.TextView")));
        assertEquals(0, greenCounterAfter.size());
        System.out.println("Counter green is disappeared");

        if (numberInBlueCounter.equalsIgnoreCase("10+")) {
            String numberInBlueCounterAfter = driver.findElement(By.xpath("//android.widget.Button [@content-desc=', tab, 3 of 5']/android.view.ViewGroup[@index='2']/android.widget.TextView")).getText();
            assertEquals(numberInBlueCounterAfter, numberInBlueCounter);
        } else {
            if (Integer.parseInt(numberInBlueCounter) == 1) {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.Button [@content-desc=', tab, 3 of 5']/android.view.ViewGroup[@index='2']/android.widget.TextView")));
            } else {
                String numberInBlueCounterAfter = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='message-counter']/android.widget.TextView")).getText();
                assertEquals(Integer.parseInt(numberInBlueCounter) - 1, Integer.parseInt(numberInBlueCounterAfter));
            }
        }
    }

    public void signUp() {
        waitForVisibilityOf(By.xpath("//*[contains(@text,'SE CONNECTER')]"), By.xpath("//*[contains(@text,'SE CONNECTER')]"));
        System.out.println("In Login Page method");
        iClickButtonHasText("INSCRIPTION GRATUITE");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View/android.view.ViewGroup[@index='1']//android.widget.RadioButton"))).click();
        clickButtonByXpath("//android.widget.ScrollView/following-sibling::android.view.ViewGroup/android.widget.TextView");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='JJ']")));
        clickButtonByXpath("//android.widget.ScrollView/following-sibling::android.view.ViewGroup/android.widget.TextView");
        waitAboutSeconds(4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='JJ']"))).sendKeys("15");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='MM']"))).sendKeys("12");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='AAAA']"))).click();
        driver.getKeyboard().sendKeys("2006");

        driver.hideKeyboard();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/following-sibling::android.view.ViewGroup/android.widget.TextView"))).click();
        waitAboutSeconds(4);
    }

    public void clickPhotoIndexInGallery(int index) {
        waitAboutSeconds(2);
        String OS = driver.getCapabilities().getCapability("platformVersion").toString();
        if (OS.equalsIgnoreCase("10")) {
            String btn = TestDataService.properties.getProperty("listPhotoInGallery");
            if (btn == null) {
                btn = "listPhotoInGallery";
            }
            ArrayList<MobileElement> listPhoto = new ArrayList<>(driver.findElements(By.xpath(btn)));
            listPhoto.get(index).click();
            System.out.println("Android 10" + listPhoto.size());
        } else if (OS.equalsIgnoreCase("11")) {
            System.out.println("android 11");
            String btn = TestDataService.properties.getProperty("listPhotoInGallery");
            if (btn == null) {
                btn = "listPhotoInGallery";
            }
            ArrayList<MobileElement> listPhoto = new ArrayList<>(driver.findElements(By.xpath(btn)));
            listPhoto.get(index).click();
        } else if (OS.equals("7")) {
            waitAboutSeconds(3);
            String btn1 = TestDataService.properties.getProperty("listPhotoInGalleryAndroid7");
            if (btn1 == null) {
                btn1 = "listPhotoInGalleryAndroid7";
            }
            ArrayList<MobileElement> listPhoto = new ArrayList<>(driver.findElements(By.xpath(btn1)));
            System.out.println("Android under 9 : " + listPhoto.size());
            listPhoto.get(index).click();
            listPhoto.get(index).click();
        } else {
            String galleryAndroid9 = "//android.widget.ImageView[@resource-id='com.sec.android.gallery3d:id/thumbnail']";
            ArrayList<MobileElement> listPhoto = new ArrayList<>(driver.findElements(By.xpath(galleryAndroid9)));
            System.out.println("Android 9 : " + listPhoto.size());
            listPhoto.get(index).click();
        }
//            if (OS.equalsIgnoreCase("11")) {
//                System.out.println("android 11");
//                String btn = TestDataService.properties.getProperty("listPhotoInGallery");
//                if (btn == null) {
//                    btn = "listPhotoInGallery";
//                }
//                ArrayList<MobileElement> listPhoto = new ArrayList<>(driver.findElements(By.xpath(btn)));
//                listPhoto.get(index).click();
//            } else {
//                waitAboutSeconds(3);
//                String btn1 = TestDataService.properties.getProperty("listPhotoInGalleryAndroid7");
//                if (btn1 == null) {
//                    btn1 = "listPhotoInGalleryAndroid7";
//                }
//                ArrayList<MobileElement> listPhoto1 = new ArrayList<>(driver.findElements(By.xpath(btn1)));
//                System.out.println("Android under 9 " + listPhoto1.size());
//                listPhoto1.get(index).click();
//                listPhoto1.get(index).click();
//            }

    }

    public void lastMessageInThreadIsMessage(int index, String lastMsg) {
        waitUntilDisplayElementByXpath("filterMessage");
        waitAboutSeconds(2);
        String lastMessage = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='" + index + "']//android.widget.TextView[last()]")).getText();
        assertTrue(lastMessage.contains(lastMsg));
    }

    public void showViewedTimeOnPhotoAndCannotViewAgain() {
        String viewedDate = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']//android.widget.TextView[@index='1']")).getText().substring(6, 16);
        System.out.println(viewedDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDate = java.time.LocalDate.now().format(formatter).toString();
        assertEquals(viewedDate, currentDate);
    }

    public void scrollDownToSeeOldMessage() {
        MobileElement ele = driver.findElement(By.xpath("//android.widget.ScrollView"));
        swipe("down", ele, 0.99);
        swipe("down", ele, 0.99);
    }

    public void checkLastMessageIsStillRight() {
        ArrayList<MobileElement> abc = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup/following-sibling::android.widget.TextView")));
        System.out.println("time under message is: " + abc.get(0).getText());
        assertEquals(abc.get(0).getText(), stack.peek());
    }

    public void iLongPressButton(String btn, int seconds) {
        waitUntilDisplayElementByXpath("inputTextMsg");
//        waitAboutSeconds(1);
        String xpath = TestDataService.properties.getProperty(btn);
        MobileElement button = driver.findElement(By.xpath(xpath));
        new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(button)).withDuration(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void iChoosePermissionVoice(String permission) {
//        waitUntilDisplayElementByXpath("allowMek");
//        System.out.println("platformVersion " + driver.getCapabilities().getCapability("platformVersion").toString());
//        String r = driver.getCapabilities().getCapability("platformVersion").toString();
//        String[] r1 = r.split("\\.", 0);
//        if (permission.equals("deny")) {
//            waitDisplayButtonXpathAndClick("//*[contains(@resource-id,'deny')]");
//        } else {
            if (OS == MobilePlatform.ANDROID) {
//                if (Integer.parseInt(r1[0]) <= 9) {
//                    driver.findElement(By.xpath("//*[contains(@resource-id,'permission_allow_button')]")).click();
//                } else if (Integer.parseInt(r1[0]) == 11) {
//                    driver.findElement(By.xpath("//*[contains(@resource-id,'permission_allow_foreground_only_button')]")).click();
//                } else
//                    driver.findElement(By.xpath("//*[contains(@resource-id,'permission_allow_one_time_button')]")).click();
//                click("(//*[contains(@resource-id,'allow')])[1]");
                clickID(permission);
            } else {
//                if (Integer.parseInt(r1[0]) < 13) {
//                    driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]")).click();
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[1]")));
//                    driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Only While Using the App\"]")).click();
//                } else
                    driver.findElement(By.xpath("//XCUIElementTypeButton[@name='OK']")).click();
            }
//        }
    }

    public void sentVoiceMessage() {
        Random rd = new Random();
        int seconds = 3 + rd.nextInt(4);
        System.out.println(seconds);
        stackInt.push(seconds);
        iLongPressButton("voiceButton", seconds);
        waitAboutSeconds(2);
    }

    public boolean sentVoiceMessageSuccess() {
        waitElementByXpath("iconPlayLastMessage");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("iconPlayLastMessage"))));
        if (list.size() == 1) {
            String timeVoice = null;
            if (OS== MobilePlatform.ANDROID) {
                timeVoice = driver.findElement(By.xpath(TestDataService.properties.getProperty("iconPlayLastMessage") + "/following-sibling::android.view.ViewGroup/android.widget.TextView")).getText();
            }else {
                String time = driver.findElement(By.xpath(TestDataService.properties.getProperty("iconPlayLastMessage"))).getAttribute("name");
                timeVoice =time.substring(time.length()-6);
            }
            System.out.println(timeVoice);
            stack.push(timeVoice);
            int seconds = Integer.parseInt(timeVoice.substring(timeVoice.length() - 1));
            System.out.println(seconds);
            return stackInt.peek() >= seconds;
        }
        return false;
    }

    public boolean displayModalAskPermissionAgain() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'to record audio?')]")));
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'to record audio?')]")));
        if (list.size() == 1) {
            System.out.println("done");
            return true;
        }
        return false;
    }

    public void redirectToSetting() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Mektoube')]")));
    }

    public void enablePermissionAndClickBack() {
        String OS = driver.getCapabilities().getCapability("platformVersion").toString();
        if (OS.startsWith("7") || OS.startsWith("9")) {
            MobileElement element = driver.findElement(By.xpath("//android.widget.FrameLayout"));
            swipe("up", element, 0.1);
            driver.findElement(By.xpath("//*[contains(@text,'Permissions')]")).click();
            waitAboutSeconds(1);
            driver.findElement(By.xpath("//*[contains(@text,'Microphone')]")).click();

        } else {
            click("//*[contains(@text,'Permissions')]");
            click("//*[contains(@text,'Microphone')]");
            click("(//*[contains(@resource-id,'allow')])[1]");
            click("//*[contains(@content-desc,'Back')]");
            waitAboutSeconds(1);
        }
        click("//*[contains(@content-desc,'Back')]");
    }

    public void iScrollDown() {
        MobileElement element = driver.findElement(By.xpath("//android.widget.FrameLayout"));
        swipe("down", element, 0.9);
    }

    public void iLockRecordingVoice() {
        String xpath = TestDataService.properties.getProperty("voiceButton");
        waitElementByXpath(xpath);

        MobileElement button = driver.findElement(By.xpath(xpath));
        int x = button.getLocation().getX();
        int y = button.getLocation().getY();
        System.out.println("button:" + button.getLocation());
        waitAboutSeconds(2);
        new TouchAction(driver)
                .longPress(LongPressOptions.longPressOptions().withPosition(PointOption.point(x, y))
                        .withDuration(Duration.ofSeconds(4)))
                .moveTo(PointOption.point(x, (int) Math.round(y * 0.7)))
                .release().perform();
    }

    public boolean displayTextInput() {
        String xpath = TestDataService.properties.getProperty("voiceButton");
        waitElementByXpath(xpath);

        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/following-sibling::android.view.ViewGroup/android.view.ViewGroup//android.widget.EditText")));
        return list.size() == 1;
    }

    public void sentVoiceMesssage() {
        searchUserByPseudoAndGoToConversation("Anied");
        sentVoiceMessage();
        iChoosePermissionVoice("allow");
        waitAboutSeconds(2);
        sentVoiceMessage();
    }

    public void turnOnOrOffFilterPhoto() {
        int x = 0;
        waitDisplayButtonXpathAndClick("myProfileBtn");
        waitAboutSeconds(2);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='FERMER']")));
        if (list.size() == 1) {
            list.get(0).click();
        }
        waitAboutSeconds(1);
        MobileElement scroll = driver.findElement(By.xpath("//android.widget.ScrollView"));
        swipe("left", scroll, 0.01);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));
        MobileElement scroll1 = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup"));
        swipe("left", scroll1, 0.01);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView")));
        MobileElement scroll2 = driver.findElement(By.xpath("//android.widget.ScrollView"));
        swipe("left", scroll2, 0.01);
        waitElementByXpath("//*[contains(@text,'Changer votre mot de passe')]");
        ArrayList<MobileElement> list1 = new ArrayList<>(driver.findElements(By.xpath("//android.widget.Switch")));
        list1.get(0).click();

        while (x == 0) {
            swipe("up", scroll2, 0.01);
            ArrayList<MobileElement> list2 = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'connecter')]")));
            waitAboutSeconds(1);
            System.out.println(list2.size());
            x = list2.size();
            if (x == 1) {
                list2.get(0).click();
            }
        }
    }

    public boolean displayModalCanTSentVoiceWhenEnableFilterPhoto() {


        return false;
    }

    public void sentVoiceMesssageAcceptPermission() {
        waitDisplayButtonXpathAndClick("messageIcon");
        new DiscoveryPage().iClickOnTheFirstMessage();
        iLongPressButton("voiceButton", 2);
        waitAboutSeconds(2);
        sentVoiceMessage();
    }

    public void reactionIconOnMessageIndex(String icon, int index) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='" + index + "']/android.view.ViewGroup/android.widget.TextView"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='" + icon + "']"))).click();
        waitAboutSeconds(3);
    }

    public boolean showLastVoiceInThreadChat() {
        String actual = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc='last-message'])[1]/android.view.ViewGroup/android.widget.TextView[2]")).getText();
        System.out.println("time voice actual :     " + actual);
        return actual.equalsIgnoreCase(stack.peek());
    }

    public void iClickReportVoiceMessage() {
//        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup[@index='1']");
        waitAboutSeconds(1);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='play-btn']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::*[@content-desc='message-menu']")));
        list.get(0).click();
        click("//*[@content-desc='report-message']/android.widget.TextView");
        click("//*[@content-desc='submit']");

    }

    public void iClickReportVoiceMessageAndChooseBlock() {
        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.view.ViewGroup[@index='1']");
        waitAboutSeconds(1);
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='play-btn']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView")));
        list.get(0).click();
        waitDisplayButtonXpathAndClick("//android.view.ViewGroup[@content-desc='play-btn']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.view.ViewGroup[@index='2']");
        waitDisplayButtonXpathAndClick("//*[contains(@text,'Signaler un message vocal')]/following-sibling::android.view.ViewGroup/android.view.ViewGroup");
        waitDisplayButtonXpathAndClick("//*[@text='VALIDER']");
    }

    public void clickThreeDotFellingReportReplyButtonInMessageIndex(int index) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.view.ViewGroup[@content-desc='message-menu'])["+index   +"]"))).click();
    }

    public void checkRepliedMessageIsAndMessageReplyIs(String repliedMsg, String msgReply) {
        String repliedMsgXpath = TestDataService.properties.getProperty("repliedMsg");
        String msgReplyXpath = TestDataService.properties.getProperty("msgReply");
        if (repliedMsgXpath == null && msgReplyXpath == null) {
            repliedMsgXpath = "repliedMsg";
            msgReplyXpath = "msgReply";
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String repliedMsgText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repliedMsgXpath))).getText();
        String msgReplyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(msgReplyXpath))).getText();
        assertEquals(repliedMsg, repliedMsgText);
        assertEquals(msgReply, msgReplyText);
    }

    public void textMessageIsRepliedBy(String textMsg, String msgReply) {
        String repliedMsgXpath = TestDataService.properties.getProperty("repliedMsg");
        String msgReplyXpath = TestDataService.properties.getProperty(msgReply);
        if (repliedMsgXpath == null && msgReplyXpath == null) {
            repliedMsgXpath = "repliedMsg";
            msgReplyXpath = "msgReply";
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String repliedMsgText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repliedMsgXpath))).getText();
        assertEquals(textMsg, repliedMsgText);
        System.out.println("Message is sent: " + wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(msgReplyXpath))).getText());
    }

    public void searchUserByPseudoAndGoToConversation(String pseudo) {
//        searchPage.closeModalEncourageUser();
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("searchBtn");
        waitAboutSeconds(1);
        ArrayList<MobileElement> onlinebutton = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("btnEnligne"))));

        //check case search advande options are showed????
//        if (onlinebutton.size() == 1) {
//            discoveryPage.iScrollUpToText("Critères avancés");
//            waitDisplayButtonXpathAndClick("btnAdvanced");
//        } else {
//            System.out.println("click luon tim pseudo nhé :D");
//        }
//        searchPage.iScrollUpToText("Critères avancés");
//        waitDisplayButtonXpathAndClick("btnAdvanced");

        iScrollUpToText("Pseudo");
        waitDisplayButtonXpathAndClick("btnPseudo");
        discoveryPage.iEnterOnField(pseudo);
        discoveryPage.iClickTickButton("btnValidate1");
        iScrollUpToText("VALIDER");
        waitDisplayButtonXpathAndClick("btnValidate2");
//        waitDisplayButtonXpathAndClick("btnValidate2");
        waitAboutSeconds(5);
//        getNicknameOfUser("getPseudoOfFirstProfileFromDiscovery");
        iScrollUpToText("Discuter avec hann99");
        waitDisplayButtonXpathAndClick("btnDiscuss");
//        waitAboutSeconds(2);
//        searchPage.clickProfileHaveNameAfterSearchPsedou(pseudo);
//        waitDisplayButtonXpathAndClick("discussButton");
        waitAboutSeconds(5);

        ArrayList<MobileElement> modalVirtualDate = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("virtualDateModalInThreadList"))));
        if (modalVirtualDate.size() == 1) {
            System.out.println("User in chat detail <> user when click discuss >> IN VIRTUAL DATE");
            waitUntilDisplayElementByXpath("virtualDateModalInThreadList");
        } else {
            String userNameInChatDetail = driver.findElement(By.xpath("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView[@index='1']")).getText();
            assertTrue(pseudo.toLowerCase(Locale.ROOT).equalsIgnoreCase(userNameInChatDetail.toLowerCase(Locale.ROOT)));
            System.out.println("User in chat detail is the same user when click discuss >> GOOD");
        }
    }

    public void allButtonsAreGrayedOutAndShowMessageVousAvezBloqué(String blockedPartner) {
        clickButtonByXpath("voiceButton");
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView"))).getText();
        assertTrue(message.toLowerCase(Locale.ROOT).contains(blockedPartner.toLowerCase(Locale.ROOT)));
        System.out.println("Message is showed on top after click voice button is: " + message);
        waitUntilIsInvisiable("xmppError");

        waitAboutSeconds(5);
        clickButtonByXpath("gifButton");
        String message1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView"))).getText();
        waitAboutSeconds(5);
        assertTrue(message1.toLowerCase(Locale.ROOT).contains(blockedPartner.toLowerCase(Locale.ROOT)));
        System.out.println("Message is showed on top after click gìf button is: " + message);
        waitUntilIsInvisiable("xmppError");

        waitAboutSeconds(2);
        clickButtonByXpath("photoIcon");
        String message2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='xmpp-message']//android.widget.TextView"))).getText();
        assertTrue(message2.toLowerCase(Locale.ROOT).contains(blockedPartner.toLowerCase(Locale.ROOT)));
        System.out.println("Message is showed on top after click photo button is: " + message);
        waitUntilIsInvisiable("xmppError");
    }

    public void makeSureDontBlockUser(String partner) {
        waitAboutSeconds(1);
        waitDisplayButtonXpathAndClick("reglagesBtn");
        waitAboutSeconds(2);
        goToSettingTabAndClickButtonByText("Blackliste");
        waitUntilDisplayButtonHasText("Profils bloqués");
        ArrayList<MobileElement> threedot = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'" + partner.toLowerCase(Locale.ROOT) + "')]/following-sibling::android.view.ViewGroup[@index=3]/android.widget.TextView")));

        if (threedot.size() == 1) {
            driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + partner.toLowerCase(Locale.ROOT) + "')]/following-sibling::android.view.ViewGroup[@index=3]/android.widget.TextView")).click();
            iClickButtonHasText("SUPPRIMER");
            thePageShowMessageContainText("Personne débloqué");
            clickButtonByXpath("closeBlackListModalBtn");
            clickButtonByXpath("closeReglages");
        } else {
            clickButtonByXpath("closeBlackListModalBtn");
            clickButtonByXpath("closeReglages");
        }
//       ĐANG Ở MyProfile
    }

    public void conversationIsEmpty() {
        ArrayList<MobileElement> viewGroupEmpty = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
        assertTrue(viewGroupEmpty.size() == 2);
    }

    public void playVoiceJustSent() {
        String voiceJustSent = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']//*[@content-desc='play-btn']";
        waitElementByXpath(voiceJustSent);
        waitDisplayButtonXpathAndClick(voiceJustSent);
//        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']//*[@content-desc='pause-btn']");
    }

    public boolean voiceJustPlayIsPaused() {
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='1']//*[@content-desc='play-btn']")));
        System.out.println(list.size() + ": voice just sent is paused");
        return list.size() == 1;
    }

    public void canNotPlayVoice() {
        ArrayList<MobileElement> listPlayVoice = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc='play-btn']")));
        System.out.println(listPlayVoice.size() + ": count voice on screen");
        listPlayVoice.get(0).click();
        ArrayList<MobileElement> listPauseVoice = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc='pause-btn']")));
        System.out.println(listPauseVoice.size() + ": list voice playing");
        if (listPauseVoice.size() == 0) {
            System.out.println("test case done");
        }
    }

    public void playVoiceSentBefore() {
        ArrayList<MobileElement> listPlayVoice = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc='play-btn']")));
        listPlayVoice.get(0).click();
        ArrayList<MobileElement> listPauseVoice = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[@index='2']//*[@content-desc='pause-btn']")));
        if (listPauseVoice.size() == 1) {
            System.out.println("play voice before success");
        }
    }

    public void thePageShowMessageContainText(String message) {
        String messageActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'" + message + "')]"))).getText();
        System.out.println("The page show message: " + messageActual);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'" + message + "')]")));
    }

    public void thePageRedirectScreen(String screen) {
        switch (screen) {
            case "Conditions générales d’utilisations":
                waitUntilDisplayButtonHasText("Conditions générales d’utilisations");
                waitUntilDisplayButtonHasText("1. Legal information");
                waitUntilDisplayButtonHasText("Le site Internet www.mektoube.fr");
                waitUntilDisplayButtonHasText("2. Objet des présentes Conditions Générales de Services et d’Utilisation");
                discoveryPage.iScrollUpToText("21. Droit applicable - Règlement des litiges");
                break;
            case "Politique de Confidentialité":
                waitUntilDisplayButtonHasText("Charte vie privée");
                waitUntilDisplayButtonHasText("1. Les Données déclarées par les Membres et collectées par Mektoube.fr");
                waitUntilDisplayButtonHasText("1.1 Mektoube.fr collecte, lors de la création d’un compte par un internaute");
                waitUntilDisplayButtonHasText("1.2 Mektoube.fr collecte, lors de la modification du profil du Membre");
                discoveryPage.iScrollUpToText("7.4. Comment faire pour refuser le dépôt de cookies ?");
                break;
        }
    }

    public void thePageShowFullModal(String modal) {
        switch (modal) {
            case "Signaler un utilisateur":
                waitUntilDisplayButtonHasText("Signaler un utilisateur");
                waitUntilDisplayButtonHasText("Merci de sélectionner une raison dans la liste ci-dessous :");
                waitUntilDisplayButtonHasText("Votre signalement est et restera anonyme.");
                waitUntilDisplayButtonHasText("Bloquer cette personne");
                break;

            case "Dites-nous en plus sur ce signalement":
                waitUntilDisplayButtonHasText("Dites-nous en plus sur ce signalement");
                waitUntilDisplayButtonHasText("Le service de modération prendra les mesures nécessaires, de l’avertissement à la suppression du compte.");
                waitUntilDisplayButtonHasText("Notre souhait, que vos rencontres sur Mektoube se déroulent dans les meilleures conditions.");
                waitUntilDisplayButtonHasText("Merci de donner des précisions sur le signalement");
                break;
        }
    }

    public void checkNamePartnerAndSendPhotoTime(String partnerName) {
        ArrayList<WebElement> listTextViewNameAndTime = new ArrayList<>(wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//android.view.ViewGroup[@content-desc='icon-back']/preceding-sibling::android.widget.TextView"))));
        String partnerNameInPreview = listTextViewNameAndTime.get(0).getText();
        System.out.println("partnerNameInPreview: " + partnerNameInPreview);
        assertEquals(partnerName.toLowerCase(Locale.ROOT), partnerNameInPreview.toLowerCase(Locale.ROOT));
        String sentTimeInPreview = listTextViewNameAndTime.get(1).getText();
        System.out.println("sentTimeInPreview: " + sentTimeInPreview);

    }

    public void checkActiveStatusOfPartnerIsShowOnHeaderInConversation() {
        String activeStatus = driver.findElement(By.xpath("//*[contains(@content-desc,'undefined')]/following-sibling::android.widget.TextView[last()]")).getText();
        System.out.println("Active status is showed: " + activeStatus);
    }

    public void checkNumberOfThreadsInThreadListBeforeAndAfterRefresh() {
        scrollDownToRefreshPage();
        ArrayList<MobileElement> serviceMsg = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Equipe Mektoube')]")));
        ArrayList<MobileElement> threadsBeforeRefresh = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));

        if (serviceMsg.size() == 1) {
            Integer numberThreadsBeforeRefresh = threadsBeforeRefresh.size();
            System.out.println("Number of thread before refresh = " + threadsBeforeRefresh.size());

            scrollDownToRefreshPage();

            ArrayList<MobileElement> threadsAfterRefresh = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
            Integer numberThreadsAfterRefresh = threadsAfterRefresh.size();
            System.out.println("Number of thread after refresh = " + numberThreadsAfterRefresh);

            assertEquals(numberThreadsBeforeRefresh, numberThreadsAfterRefresh);
            ArrayList<MobileElement> serviceMsg2 = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Equipe Mektoube')]")));
            assertTrue(serviceMsg2.size() == 1);
            System.out.println("This account have SERVICE MESSAGES :D");
        } else {
            Integer numberThreadsBeforeRefresh = threadsBeforeRefresh.size() - 1;
            ArrayList<MobileElement> serviceMsg1 = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'Equipe Mektoube')]")));
            assertTrue(serviceMsg1.size() == 0);

            System.out.println("Number of thread before refresh = " + (threadsBeforeRefresh.size() - 1));

            scrollDownToRefreshPage();

            ArrayList<MobileElement> threadsAfterRefresh = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")));
            Integer numberThreadsAfterRefresh = threadsAfterRefresh.size() - 1;
            System.out.println("Number of thread after refresh = " + numberThreadsAfterRefresh);

            assertEquals(numberThreadsBeforeRefresh, numberThreadsAfterRefresh);
        }
    }

    public void playVoiceReceivedThenPlayVoiceJustSend() {
        waitElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//*[contains(@content-desc,'btn')]");
        //voice received and voice just sent
        ArrayList<MobileElement> voiceReply = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//*[contains(@content-desc,'btn')]")));
        //play voice received
        voiceReply.get(0).click();
        waitAboutSeconds(2);
        String contentDesc = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.view.ViewGroup[1]/*[contains(@content-desc,'btn')]")).getAttribute("content-desc");
        assertEquals("voice received don't play", "pause-btn", contentDesc);
        //play voice reply
        voiceReply.get(1).click();
        waitAboutSeconds(2);
        String contentDesc1 = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.view.ViewGroup[2]/*[contains(@content-desc,'btn')]")).getAttribute("content-desc");
        assertEquals("voice reply don't play", "pause-btn", contentDesc1);
    }

    public void checkStatusOnlineInProfileAndConversation() {
        String statusOnlineInProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("statusOnlineInProfile")))).getText();
        System.out.println("statusOnlineInProfile: " + statusOnlineInProfile);
        click("discussButton");
        String statusOnlineInConversation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("statusOnlineInConversation")))).getText();
        System.out.println("statusOnlineInConversation: " + statusOnlineInConversation);
        assertTrue(statusOnlineInProfile.contains(statusOnlineInConversation));
    }

    public void clickConversationWithInThreadList(String partnerName) {
        WebElement conversation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView[contains(@text,'" + partnerName + "')]")));
        conversation.click();
        assertTrue(partnerName.equalsIgnoreCase(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TestDataService.properties.getProperty("partnerNameInChatDetail")))).getText()));
    }

    public void lastMessageInThreadWithIs(String partnerName, String lastMessage) {
        String lastMessageActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView[contains(@text,'" + partnerName + "')]//following-sibling::android.widget.TextView[last()]"))).getText();
        if (lastMessage.equalsIgnoreCase("photo") || lastMessage.equalsIgnoreCase("voice") || lastMessage.equalsIgnoreCase("gif")) {
            if (lastMessage.equalsIgnoreCase("photo")) {
                assertTrue(lastMessageActual.contains("Message image"));
            }
            if (lastMessage.equalsIgnoreCase("voice")) {
                String lastVoiceMessageActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView[contains(@text,'" + partnerName + "')]//following-sibling::android.view.ViewGroup/android.widget.TextView[last()]"))).getText();
                assertTrue(lastVoiceMessageActual.contains("00:0"));

            }
            if (lastMessage.equalsIgnoreCase("gif")) {
                assertTrue(lastMessageActual.contains("Message gif"));
            }
        } else {
            assertTrue(lastMessageActual.contains(lastMessage));
        }
    }

    public void lastMessageInChatDetailIs(String lastMsgInChatDetail) {

        if (lastMsgInChatDetail.equalsIgnoreCase("photo") || lastMsgInChatDetail.equalsIgnoreCase("voice") || lastMsgInChatDetail.equalsIgnoreCase("gif")) {
            if (lastMsgInChatDetail.equalsIgnoreCase("photo")) {
                //check text in last photo just sent is display "Pas encore vu"
                waitUntilDisplayElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.widget.TextView[contains(@text,'Pas encore vu')]");
            }
            if (lastMsgInChatDetail.equalsIgnoreCase("voice")) {
                //check slider bar of last voice message-host is displayed
                waitUntilDisplayElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.widget.SeekBar");
            }
            if (lastMsgInChatDetail.equalsIgnoreCase("gif")) {
                //check image of last Gif messgae-host is displayed
                waitUntilDisplayElementByXpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.widget.ImageView");
            }
        } else {
            String lastMessageTextHost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.view.ViewGroup/android.widget.TextView"))).getText();
            assertTrue(lastMsgInChatDetail.equalsIgnoreCase(lastMessageTextHost));
        }
    }

    public void theBlueCounterIsReducedOne() {
        System.out.println("Old number in blue counter is: " + stack.peek());
        if (!stack.peek().equalsIgnoreCase("10+")) {
            if (stack.peek().equalsIgnoreCase("1")) {
                dontDisplayThisElement("numberInBlueCounter");
            } else {
                String numberInBlueCounterAfterDelete = driver.findElement(By.xpath("//android.widget.Button[@content-desc=', tab, 3 of 5']/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView")).getText();
                System.out.println("Number in blue counter after delete a thread has new messgae is: " + numberInBlueCounterAfterDelete);
                assertTrue(Integer.parseInt(stack.peek()) == Integer.parseInt(numberInBlueCounterAfterDelete) + 1);
            }
        } else {
            String numberInBlueCounterAfterDelete = driver.findElement(By.xpath("//android.widget.Button[@content-desc=', tab, 3 of 5']/android.view.ViewGroup/android.view.ViewGroup[@index='1']/android.widget.TextView")).getText();
            System.out.println("Number in blue counter after delete a thread has new messgae is: " + numberInBlueCounterAfterDelete);
            assertTrue(numberInBlueCounterAfterDelete.contains("10"));
        }
    }

    public void loginWithUsernameOrEmailAndPasswordAndGoToThreadChat(String email, String password) {
        loginPage.loginWithUsernameOrEmailAndPassword(email, password);
        click("messengerMenuBtn");

    }

    public void filterUnreadMessagesInChatThreadIfAnyThenRead() {
        //filter message  unread
        waitDisplayButtonXpathAndClick("filterMessage");
        waitDisplayButtonXpathAndClick("option2AtFilter");
        waitAboutSeconds(2);
        System.out.println("filtering message...");
        // if it has pink dot must show message unread
        ArrayList<MobileElement> messageCount = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc='message-counter']")));
        ArrayList<MobileElement> messageUnread = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("numberMessageUnreadOfFirstThread"))));
        assertEquals("number message unread at first thread : " + messageUnread.size(), messageCount.size() > 0, messageUnread.size() > 0);
        if (messageUnread.size() > 0 && messageCount.size() > 0) {
            messageUnread.get(0).click(); //read first thread chat
            waitAboutSeconds(2);
            //check name at thread just read match with name in chat detail + don't show counter
            String nameInChatDetail = driver.findElement(By.xpath(TestDataService.properties.getProperty("usernameInChatDetail"))).getText();
            waitDisplayButtonXpathAndClick("backBtnInConversation");
            waitAboutSeconds(1);
            String nameAtFirstThreadChat = driver.findElement(By.xpath(TestDataService.properties.getProperty("nameOfFirstUserThreadChat"))).getText();
            assertEquals("name at chat detail : " + nameInChatDetail + "and name at thread list : " + nameAtFirstThreadChat, nameInChatDetail, nameAtFirstThreadChat);

            ArrayList<MobileElement> displayNumberUnreadFirstThread = new ArrayList<>(driver.findElements(By.xpath("(//android.view.ViewGroup[contains(@content-desc,'thread')])[1]//android.view.ViewGroup[@content-desc='unread-message-counter']/android.widget.TextView")));
            System.out.println(displayNumberUnreadFirstThread.size());
            assertFalse("still show counter message at first thread", displayNumberUnreadFirstThread.size() > 0);
        } else {
            waitDisplayButtonXpathAndClick("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup)[last()]/android.widget.TextView");
            System.out.println("don't have message unread => click green button");
        }
    }

    public void doesNotChangeTheOrderOfConversationsAfterReloadingTheThreadList() {
        //filter all message
        waitDisplayButtonXpathAndClick("filterMessage");
        waitDisplayButtonXpathAndClick("option1AtFilter");
        waitAboutSeconds(2);
        ArrayList<String> nameThreadBeforeReload = new ArrayList<>();
        ArrayList<String> nameThreadAfterReload = new ArrayList<>();
        ArrayList<MobileElement> listNameAtThreadList = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@content-desc='name']")));
        for (MobileElement name : listNameAtThreadList) {
            nameThreadBeforeReload.add(name.getText());
        }
        scrollDownToRefreshPage();
        ArrayList<MobileElement> listNameAtThreadListAfterReload = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@content-desc='name']")));
        for (MobileElement name : listNameAtThreadListAfterReload) {
            nameThreadAfterReload.add(name.getText());
        }
        assertEquals("list name before reload : " + nameThreadBeforeReload + "\nlist name after reload : " + nameThreadAfterReload, nameThreadBeforeReload, nameThreadAfterReload);
    }

    public void checkStatusLastMessageNameAndAvatarInThreadSameAsInChatDetailOfConversationWithName(String name) {
        waitUntilDisplayElementByXpath("//*[contains(@content-desc,'undefined')]");
        ArrayList<String> nameThreadBeforeReload = new ArrayList<>();
        ArrayList<MobileElement> listNameAtThreadList = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc='name']")));
        for (MobileElement nameThread : listNameAtThreadList) {
            nameThreadBeforeReload.add(nameThread.getText());
        }
        if (!nameThreadBeforeReload.contains(name)) {
            iScrollUpToText(name);
        }
        waitAboutSeconds(1);
        String typeMessage;
        //get all infor of user "" at thread list
        String lastMessage = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='name'][@text='" + name + "']/following-sibling::android.view.ViewGroup[@content-desc='last-message']//android.widget.TextView[last()]")).getText();
        boolean statusOnline = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='name'][@text='" + name + "']/following-sibling::android.view.ViewGroup[@content-desc='online-away']")).isDisplayed();
        if (lastMessage.contains(":")) {
            typeMessage = "voice";
        } else if (lastMessage.contains("image")) {
            typeMessage = "image";
        } else if (lastMessage.contains("gif")) {
            typeMessage = "gif";
        } else typeMessage = "text";
        System.out.println("------------------------------------------------------------------------");
        System.out.println("last message : " + lastMessage + "status online : " + statusOnline);

        //go to chat detail
        clickConversationWithName(name);
        waitUntilDisplayElementByXpath("inputTextMsg");
        //check name
        String nameAtChatDetail = driver.findElement(By.xpath(TestDataService.properties.getProperty("usernameInChatDetail"))).getText();
        assertEquals(name, nameAtChatDetail);
        System.out.println("name at chat detail : " + nameAtChatDetail);
        //check last message
        String lastMessageChatDetail = null;
        switch (typeMessage) {
            case "voice": //00:00
                lastMessageChatDetail = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]//android.widget.SeekBar/following-sibling::android.view.ViewGroup/android.widget.TextView")).getText();
                assertEquals("voice : " + lastMessage + "actual : " + lastMessageChatDetail, lastMessage, lastMessageChatDetail);
                break;
            case "image"://Pas encore vu - Vu
                lastMessageChatDetail = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup//android.view.ViewGroup/following-sibling::android.widget.TextView")).getText();
                if (lastMessageChatDetail.contains("Pas encore vu") || lastMessageChatDetail.contains("Vu"))
                    assertTrue(true);
                break;
            case "gif"://GIPHY
                lastMessageChatDetail = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup//android.view.ViewGroup/following-sibling::android.widget.TextView[last()]")).getText();
                assertEquals("GIPHY", lastMessageChatDetail);
                break;
            default:
                lastMessageChatDetail = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup//android.widget.TextView")).getText();
                assertEquals(lastMessage, lastMessageChatDetail);
                break;
        }
        System.out.println("last message in chat detail :" + lastMessageChatDetail);
        System.out.println("------------------------------------------------------------------------");
    }

    private void clickConversationWithName(String name) {
        waitDisplayButtonXpathAndClick("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup//android.view.ViewGroup[contains(@content-desc,'undefined')]/following-sibling::android.widget.TextView[1][@text='" + name + "']");
    }

    public void sentAllTypeMessageSuccess() {
        //sent text message success
        waitDisplayButtonXpathAndClick("inputTextMsg");
        clearTextAndInsertTextIntoField("this is text message sent by auto", "inputTextMsg");
        waitDisplayButtonXpathAndClick("sendButton");
        waitAboutSeconds(3);
        textMessageIsSendRealtime();

        //sent voice message success
        sentVoiceMessage();
        iChoosePermissionVoice("allow");
        waitAboutSeconds(2);
        sentVoiceMessage();
        assertTrue(sentVoiceMessageSuccess());
        //sent photo message by camera success
        sendAEphemeralPhoto();
        //sent gif message success
        waitDisplayButtonXpathAndClick("//android.view.ViewGroup[@content-desc='gif-btn']");
        waitDisplayButtonXpathAndClick("(//android.widget.HorizontalScrollView//android.widget.ImageView)[1]");//sent first gif
        waitDisplayButtonXpathAndClick("sendButton");
        waitAboutSeconds(2);
    }

    public void limitReportAbuse() {
        waitDisplayButtonXpathAndClick("3DotDeleteBlockReportChat");
        waitDisplayButtonXpathAndClick("option2AtFilter");
        //select first reason report
        waitDisplayButtonXpathAndClick("//android.view.ViewGroup[@content-desc='1']");
        waitDisplayButtonXpathAndClick("submitButtonOnPopup");
        //add report second step and click submit
        clearTextAndInsertTextIntoField("this is text message sent by auto", "reportReasonTextField");
        waitDisplayButtonXpathAndClick("//android.view.ViewGroup[@content-desc='submit-report']");
        //check message reponse
        waitUntilDisplayElementByXpath("xmppError");
        System.out.println("message after report : " + driver.findElement(By.xpath(TestDataService.properties.getProperty("xmppError"))).getText());
        waitElementHiddenByXpath("xmppError");
    }


    public void visitTheProfileAndComeback() {
        //block profile from chat detail
        waitDisplayButtonXpathAndClick("3DotDeleteBlockReportChat");
        waitDisplayButtonXpathAndClick("option1AtFilter");
        waitElementByXpath("xmppError");
        System.out.println("message after block : " + driver.findElement(By.xpath(TestDataService.properties.getProperty("xmppError"))).getText());
        waitElementHiddenByXpath("xmppError");
        String name = driver.findElement(By.xpath(TestDataService.properties.getProperty("usernameInChatDetail"))).getText();
        String status = driver.findElement(By.xpath(TestDataService.properties.getProperty("statusOnlineInConversation"))).getText();

        //visit profile
        waitDisplayButtonXpathAndClick("usernameInChatDetail");
        discoveryPage.shouldShowAllInforOfprofile();
        assertTrue(discoveryPage.shouldShowAllInforOfprofile().contains(name) && discoveryPage.shouldShowAllInforOfprofile().contains(status));

        //deblock from profile
        waitDisplayButtonXpathAndClick("threeDotProfileToReport");
        waitDisplayButtonXpathAndClick("option1AtFilter");
        waitElementByXpath("xmppError");
        System.out.println("message after deblock : " + driver.findElement(By.xpath(TestDataService.properties.getProperty("xmppError"))).getText());
        waitElementHiddenByXpath("xmppError");
    }

    public void openThreadMessageWithName(String name) {

        waitUntilDisplayElementByXpath("//*[contains(@content-desc,'undefined')]");
        ArrayList<String> nameAtThread = new ArrayList<>();
        ArrayList<MobileElement> listNameAtThreadList = new ArrayList<>(driver.findElements(By.xpath("//*[@content-desc='name']")));
        for (MobileElement nameThread : listNameAtThreadList) {
            nameAtThread.add(nameThread.getText());
        }
        if (!nameAtThread.contains(name)) {
            iScrollUpToText(name);
        }
        waitAboutSeconds(1);
        clickConversationWithName(name);
    }

    public void reportVoiceAndPhotoMesssage() {
        //REPORT VOICE
        waitUntilDisplayElementByXpath("inputTextMsg");
        //click threedot to report voice message
        waitDisplayButtonXpathAndClick("(//android.view.ViewGroup[@content-desc='play-btn'])[1]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[@content-desc='message-menu']");
        waitDisplayButtonXpathAndClick("iconReportMessage");
        //wait display modal report voice
        waitUntilDisplayElementByXpath("//android.view.ViewGroup[@content-desc='checkbox']");
        //select report with block profile and check message
        waitDisplayButtonXpathAndClick("//android.view.ViewGroup[@content-desc='checkbox']");
        waitDisplayButtonXpathAndClick("submitButtonOnPopup");

        String xmppMessage;
        waitElementByXpath("xmppError");
        xmppMessage = driver.findElement(By.xpath(TestDataService.properties.getProperty("xmppError"))).getText();
        System.out.println("message after report voice 1st : " + xmppMessage);
        assertEquals("Votre signalement a été pris en compte et la personne a été bloquée", xmppMessage);
        waitElementHiddenByXpath("xmppError");
        //report voice second time and check message again
        waitDisplayButtonXpathAndClick("(//android.view.ViewGroup[@content-desc='play-btn'])[1]/parent::android.view.ViewGroup/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup[@content-desc='message-menu']");
        waitDisplayButtonXpathAndClick("iconReportMessage");
        waitDisplayButtonXpathAndClick("submitButtonOnPopup");
        waitElementByXpath("xmppError");
        xmppMessage = driver.findElement(By.xpath(TestDataService.properties.getProperty("xmppError"))).getText();
        System.out.println("message after report voice 2nd : " + xmppMessage);
        assertEquals("Ce message vocal a déjà été signalé", xmppMessage);

        //REPORT PHOTO

    }

    public void reportProfile() {

    }

    public void replyAllTypeMessage() {
    }

    public void sendAEphemeralPhoto() {
        waitDisplayButtonXpathAndClick("photoIcon");
        waitDisplayButtonXpathAndClick("sentPhotoByCamera");
        waitUntilDisplayElementByXpath("allowMek");
        System.out.println("platformVersion " + driver.getCapabilities().getCapability("platformVersion").toString());
        String r = driver.getCapabilities().getCapability("platformVersion").toString();
        String[] r1 = r.split("\\.", 0);
        if (OS == MobilePlatform.ANDROID) {
            if (Integer.parseInt(r1[0]) <= 9) {
                driver.findElement(By.xpath("//*[contains(@resource-id,'permission_allow_button')]")).click();
            } else if (Integer.parseInt(r1[0]) == 11) {
                driver.findElement(By.xpath("//*[contains(@resource-id,'permission_allow_foreground_only_button')]")).click();
            } else
                driver.findElement(By.xpath("//*[contains(@resource-id,'permission_allow_one_time_button')]")).click();
        } else {
            if (Integer.parseInt(r1[0]) < 13) {
                driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeStaticText[1]")));
                driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Only While Using the App\"]")).click();
            } else
                driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow Once\"]")).click();
        }
//        clickPhotoIndexInGallery(0);
        waitDisplayButtonXpathAndClick("//android.view.ViewGroup[@content-desc='Take picture']");
        waitDisplayButtonXpathAndClick("//*[contains(@resource-id,'okay')]");
        waitUntilDisplayElementByXpath("previewPhoto");
        iClickButtonHasText("5s");
        clickButtonByXpath("sendPhotoBtn");
//        waitAboutSeconds(5);
        waitUntilDisplayElementByXpath("sentLastPhoto");
//        messageIsSendRealtime();
    }
}