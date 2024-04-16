package com.mektoube.pages;

import com.mektoube.config.MobilePlatform;
import com.mektoube.service.TestDataService;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Locale;

import static com.mektoube.config.AppiumConfig.driver;
import static com.mektoube.config.AppiumConfig.getDriver;
import static com.mektoube.pages.BasePage.OS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MyAccountPage {
    BasePage common = new BasePage();
    MessageModule messageModule = new MessageModule();
    String innerText = "";

    public void theUserGoToMyAccountMenu() {
        common.callElement("myAccountMenuBtn").click();
    }

    public void theUserClickOnStatisticTab() {
        common.click("statisticsTab");
        innerText = common.driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Activité \"]").getText();
        Assert.assertEquals("Activité ", innerText);
        System.out.println("Statistic tab is displayed !!!");
    }

    public void theUserClickOnFavoritesTab() {
        common.click("favoritesTab");
    }

    public void theUserShouldSeeAFavoritesList() {
        innerText = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("favoritesTitle"))).getText();
        Assert.assertEquals("Favoris", innerText);
        System.out.println("Favorites tab is displayed !!!");
    }

    public void clickOnFirstProfileInDiscovery() {
        String getPseudo = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("getPseudoOfFirstProfileFromDiscovery"))).getText().toLowerCase();
        System.out.println("The first profile that we found is: " + getPseudo);
        common.WriteToFile(getPseudo);
        common.driver.findElement(By.xpath(TestDataService.properties.getProperty("firstProfileFromDiscovery"))).click();
    }

    public void addThemIntoFavoritesByChoose(String addToFavoritesBtn) {
        try {
            common.clickButtonByXpath(addToFavoritesBtn);
            System.out.println("Add to favorites successfully");
//                common.alertMessageWithContentIsShowedOnTop("Supprimé des favoris");
        } catch (Exception e) {
            System.out.println("This profile has been existed in favorites list");
            common.clickButtonByXpath("cancelBtn");
        }
    }

    public void scrollAllPage(String element) {
        String cssSelector = TestDataService.properties.getProperty(element);
        //System.out.println("selector is " + cssSelector);
        //scroll to get all user have choosed before
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        int itemLoaded = 0;
        int totalItem = getDriver().findElements(By.xpath(cssSelector)).size();
        int totalItems = totalItem - 1; // -1 vi bo cai element title di, kh tach ra duocs
        System.out.println("Total number of favorites people: " + totalItems);
        int i = 1;
        while (itemLoaded < totalItems) {
            int intTo = i * 5000;
            //js.executeScript("window.scrollTo(0," + intTo + ");");
            common.waitAboutSeconds(3);
            itemLoaded = totalItems;
            totalItems = common.driver.findElements(By.xpath(cssSelector)).size();
            //totalItems = getDriver().findElements(net.serenitybdd.core.annotations.findby.By.cssSelector(cssSelector)).size();
            i++;
        }
    }

    public void theUserShouldSeeThemInFavoritesList() {
        String name = "";
        try {
            name = common.ReadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String nameFavorite = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("firstProfileFromFavoritesTab"))).getText().toLowerCase(Locale.ROOT);
        System.out.println("The name is found in favorites list: " + nameFavorite);
        assertEquals(name, nameFavorite); //compare name (in discovery) with name (in favorite list)
    }

    public void visitingAProfileFromTheFavoritesTab() {
        common.click("myAccountMenuBtn");
        common.click("favoritesTab");
        try {
            common.click("firstProfileFromFavoritesTab");
        } catch (Exception E) {
            System.out.println("The favorites list is empty");
        }
    }

    public void removeThemFromFavoritesByChoose(String removeFromFavoritesBtn) {
        try {
            common.checkElementWhichDisplayThenClick("addToFavoritesBtn", removeFromFavoritesBtn,
                    "Add to favorites successfully", "That profile has been favorites, so delete now");
        } catch (Exception E) {
            System.out.println("Cannot find this element cause list favorites is empty before");
        }
    }

    public void backToPreviousPage() {
        try {
            if (common.driver.findElementByXPath(TestDataService.properties.getProperty("backToPreviousIcon")).isDisplayed()) {
                common.clickButtonByXpath("backToPreviousIcon");
            }
        } catch (Exception E) {
            System.out.println("Cannot find this element cause list favorites is empty before");
        }
    }

    public void removeThatProfileSuccessfully() {
        String favoriteName = "";
        try {
            favoriteName = common.ReadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //after delete success, case: list still have another profile
        try {
            String getName = common.subStringGetFirstWord("firstProfileFromFavoritesTab");
            String nameCapitalized = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            System.out.println("The first profile is found in favorites list is: " + nameCapitalized);
            assertNotEquals("Remove fail", nameCapitalized, favoriteName);
            System.out.println("Can not find \"" + favoriteName + "\" in list");
        }
        //after delete success, case: list is empty
        catch (Exception E) {
//            Assert.assertEquals("Pas de favoris", common.driver.findElementByXPath(TestDataService.properties.getProperty("favoritesListEmptyTitle")).getText());
            System.out.println("List is empty, so remove successfully");
        }
        System.out.println("Remove successfully");
    }

    public void deleteAProfileFromFavoritesListBySwipeLeftAndClickOn(String deleteSwipeLeftBtn) {
        if (common.driver.findElementByXPath(TestDataService.properties.getProperty("firstItemProfileFavorites")).isDisplayed()) {
            String x = TestDataService.properties.getProperty("firstItemProfileFavorites");
            ArrayList<MobileElement> list = new ArrayList<>(common.driver.findElements(By.xpath(x)));
            System.out.println("A number of profile in list: " + list.size());
            MobileElement element = list.get(0);

            Dimension dimension = element.getSize();
            System.out.println(dimension);

            int Anchor = element.getLocation().getY();

            int startSwipe = (int) Math.round(dimension.getWidth() * 0.7);
            System.out.println(startSwipe);

            int endSwipe = (int) Math.round(dimension.getWidth() * 0.1);
            System.out.println(endSwipe);
            //Swipe save filter display button
            new TouchAction(common.driver).press(PointOption.point(startSwipe, Anchor))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(endSwipe, Anchor))
                    .release().perform();

            common.click(deleteSwipeLeftBtn);
        }
    }

    public void theUserFilterClickOnFilterOption() {
        common.clickButtonByXpath("favoritesFilterIcon");
    }

    public void choose(String option) {
        switch (option) {
            case "Tout":
                common.click("favoritesFilterIconAll");
                break;
            case "En ligne":
                common.click("favoritesFilterIconOnline");
                break;
        }
    }

    public void theShouldBeShown(String results) {
        switch (results) {
            case "toutValidate":
                common.countNumberList("profileItemFavorite");
                break;
            case "onlineAndNoOnline":
                try {
                    //FOR ONLINE
                    common.countNumberList("profileItemFavorite");
                    System.out.println("nen valide green online");
                    ///////CHUA VIETTTTTTTTTTTT CHUA NGHI RA GI DE VIETTTTT////
                } catch (Exception E) {
                    //FOR NO ONLINE
                    Assert.assertEquals("Pas de favoris", common.driver.findElementByXPath(TestDataService.properties.getProperty("favoritesListEmptyTitle")).getText());
                    System.out.println("No one online in list !!!");
                    common.click("favoritesBtn");
                    common.waitUntilDisplayElementByXpath("searchBtn");
                    System.out.println("Redirect to Discovery success");
                }
                break;
        }
    }

    public void theUserClickOnBlacklistAndShouldSeeTheTheirBlacklist() {
        common.clickButtonByXpath("blacklistMenu");
        innerText = common.driver.findElementByXPath(TestDataService.properties.getProperty("blocklistTitle")).getText();
        Assert.assertEquals("Profils bloqués", innerText);
    }

    public void theUserVisitingThatFirstProfileFromMessage() {
        common.click("messengerMenuBtn");
        //IOS cu
//        common.click("firstProfileFromMessage");
//        common.click("partnerNameInChatDetail");

        //Android moi
        messageModule.clickThreadMessageIndexInThreadList(1);
        common.click("partnerNameInChatDetail");
    }

    public void addThemIntoBlacklistByChoose(String element) {
        try {
            /*when the element is found do this*/
            common.clickButtonByXpath(element);
            System.out.println("Add to blacklist success");
        } catch (Exception e) {
            /*include the else part here*/
            System.out.println("Add to backlist before");
            common.clickButtonByXpath("cancelBtn");
        }
    }

    public boolean shouldSeeRedBorderAtTheIncorrectFields() {
        common.waitAboutSeconds(2);
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        ArrayList<WebElement> redBorder = new ArrayList<>(wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(TestDataService.properties.getProperty("emailRedError")))));
        System.out.println("Count red border: " + redBorder.size());
        return redBorder.size() > 0;
    }

    public void theUserClickInto(String button) {
        common.click(button);
        common.click(button);
    }

    public void theUserTypeInto(String content) {
        common.driver.findElementByXPath(TestDataService.properties.getProperty("messageTextInput")).setValue(content);
    }

    public void shouldSeeCGUContent() {
        innerText = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("CGUTitle"))).getText();
        System.out.println("The text: " + innerText);
        assertEquals("1. Legal information", innerText);
    }

    public void shouldSeeViePriveeContent() {
        innerText = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("VieTitle"))).getText();
        System.out.println("The text: " + innerText);
        assertEquals("Charte vie privée", innerText);
    }

    public void shouldSeeMentionsLegalesContent() {
        innerText = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("MentionsTitle"))).getText();
        System.out.println("The text: " + innerText);
        assertEquals("Mentions légales", innerText);
    }

    public void shouldSeeHomePage() {
        if (OS == MobilePlatform.IOS) {
            innerText = common.driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"L'application numéro 1 de la rencontre Musulmane et Maghrébine\"]").getText();
            assertEquals("L'application numéro 1 de la rencontre Musulmane et Maghrébine", innerText);
        } else {
            common.waitUntilDisplayElementByXpath("seConnecterBtn");
        }
    }

    public void theUserLogoutSuccessfully() {
        if (OS == MobilePlatform.IOS) {
            common.click("myProfile");
            common.click("reglages");
            common.waitAboutSeconds(1);
            common.swipe("up",driver.findElement(By.xpath("//XCUIElementTypeScrollView")),0.1);
            common.click(("//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[contains(@name,'connecter')]"));
            common.waitUntilDisplayElementByXpath("seConnecterBtn");
        } else {
            common.click("myProfile");
            common.click("settingBtn");
            common.theUserScrollDownAndClick("connecter");
//            common.waitAboutSeconds(3);
            shouldSeeHomePage();
        }
    }

    public boolean theUserTryToActivesThePhotoFilter() {
        if (OS == MobilePlatform.IOS) {
            MobileElement filterPhotoToggleSwitch = common.driver.findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[3]/XCUIElementTypeButton[1]");
            String state = filterPhotoToggleSwitch.getAttribute("value");
            System.out.println("State: " + state);
            if (filterPhotoToggleSwitch.getAttribute("value").equals("0")) {
                filterPhotoToggleSwitch.click();
                if (filterPhotoToggleSwitch.getAttribute("value").equals("1")) {
                    System.out.println("State now: " + state + " => Turn on => false");
                    return false;
                } else if (filterPhotoToggleSwitch.getAttribute("value").equals("0")) {
                    System.out.println("State now: " + state + " => Turn off => true");
                    return true;
                }
            }
        } else {
            try {
                common.click("filterPhotoToggleSwitch_OFF");
                messageModule.thePageShowMessageContainText("Veuillez mettre au moins une photo pour utiliser cette fonctionnalité");
                System.out.println("Clicked");
            } catch (Exception e) {
                System.out.println("Turn on filter photo before");
            }
        }
        return true;
    }

    public boolean theUserActivesThePhotoFilter() {
        if (OS == MobilePlatform.IOS) {
            MobileElement filterPhotoToggleSwitch = common.driver.findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[3]/XCUIElementTypeButton[1]");
            String state = filterPhotoToggleSwitch.getAttribute("value");
            System.out.print("State: " + state);
            if (filterPhotoToggleSwitch.getAttribute("value").equals("0")) {
                filterPhotoToggleSwitch.click();
                System.out.println(" => Turn on photo filter");
                return true;
            } else if (filterPhotoToggleSwitch.getAttribute("value").equals("1")) {
                System.out.println(" => Photo filer turned on before");
                return true;
            }
        } else {
            try {
                common.click("filterPhotoToggleSwitch_OFF");
            } catch (Exception e) {
                System.out.println("Turn on filter photo before");
            }
            common.checkElementIsDisplay("filterPhotoToggleSwitch");
            System.out.print(" => Turn on photo filter success ");
        }
        return true;
    }

    public boolean theUserInactiveThePhotoFilter() {
        if (OS == MobilePlatform.IOS) {
            MobileElement filterPhotoToggleSwitch = common.driver.findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[3]/XCUIElementTypeButton[1]");
            String state = filterPhotoToggleSwitch.getAttribute("value");
            System.out.print("State: " + state);
            if (filterPhotoToggleSwitch.getAttribute("value").equals("1")) {
                filterPhotoToggleSwitch.click();
                System.out.println(" => Turn off photo filter");
                return true;
            } else if (filterPhotoToggleSwitch.getAttribute("value").equals("0")) {
                System.out.println(" => Photo filer turned off before");
                return true;
            }
        } else {
            try {
                common.click("filterPhotoToggleSwitch");
            } catch (Exception e) {
                System.out.println("Turn off photo filter before");
            }
            common.checkElementIsDisplay("filterPhotoToggleSwitch_OFF");
        }
        return true;
    }

    public void shouldSeeInChatDetail() {
        assertEquals("Test chat when turn on photo filter", common.getText("messageHasJustBeenSent"));
    }

    public void theUserActivesTheAgeFilter() {
        common.click("myAccountMenuBtn");
        common.click("settingTab2");
        if (OS == MobilePlatform.IOS) {
            MobileElement filterPhotoToggleSwitch = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("filterAgeToggleSwitch")));
            String state = filterPhotoToggleSwitch.getAttribute("value");
            System.out.print("State: " + state);
            if (filterPhotoToggleSwitch.getAttribute("value").equals("0")) {
                filterPhotoToggleSwitch.click();
                System.out.println(" => Turn on age filter");
            } else if (filterPhotoToggleSwitch.getAttribute("value").equals("1")) {
                System.out.println(" => Age filer turned on before");
            }
        } else {
            try {
                common.click("filterAgeToggleSwitch_OFF");
            } catch (Exception e) {
                System.out.println("Turn on filter age before");
            }
            common.checkElementIsDisplay("filterAgeToggleSwitch");
            System.out.print(" => Turn on age filter success ");
        }
    }

    public boolean theUserInactiveTheAgeFilter() {
        common.click("myAccountMenuBtn");
        common.click("settingTab2");
        MobileElement filterPhotoToggleSwitch = common.driver.findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[contains(@name,'Filtre âge info')]//XCUIElementTypeButton");
        String state = filterPhotoToggleSwitch.getAttribute("value");
        System.out.print("State: " + state);
        if (filterPhotoToggleSwitch.getAttribute("value").equals("1")) {
            filterPhotoToggleSwitch.click();
            System.out.println(" => Turn off Age filter");
            return true;
        } else if (filterPhotoToggleSwitch.getAttribute("value").equals("0")) {
            System.out.println(" => Age filer turned off before");
            return true;
        }
        return false;
    }

    public void theUserTryToSendToThemAToThem(String messages) {
        switch (messages) {
            case "image":
                common.clickButtonByXpath("photoIcon");
                break;
            case "gif":
                common.clickButtonByXpath("gifButton");
                break;
            case "text":
                common.clickButtonByXpath("inputTextMsg");
                break;
            case "voice":
                common.clickButtonByXpath("voiceButton");
                break;
        }
    }

    public void addThemIntoBlockListByChoose(String button) {
        try {
            common.clickButtonByXpath(button);
            common.alertMessageWithContentIsShowedOnTop("Le blocage est confirmé");
            System.out.println("Add to block list successfully");
        } catch (Exception e) {
            System.out.println("This profile has been existed in block list");
        }
    }

    public boolean shouldNotSeeDiscussAndSmileButtonDisappear() {
        common.waitAboutSeconds(5);
        try {
            common.elementShouldBeDisplay("smileBtn");
            common.elementShouldBeDisplay("discussButton");
            return false;
        } catch (Exception e) {
            System.out.println("Can not see smile and discuss button => pass");
            return true;
        }
    }

    public void theUserUnblockThem() {
        common.clickButtonByXpath("optionIconInBlockList");
        common.clickButtonByXpath("deleteFromBlockListBtn");
        common.waitAboutSeconds(3);
//        common.alertMessageWithContentIsShowedOnTop("Personne débloquée"); // can xem lai cai nay
        System.out.println("Unblock user success");
    }

    public void shouldSeeModalValidateEmail() {
        common.waitAboutSeconds(2);
        String text = "ENVOYER UN LIEN DE VALIDATION";
        MobileElement InvalidEmail = (MobileElement) common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup//android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView")));
        Assert.assertEquals(text, InvalidEmail.getText());
        System.out.println("Account invalid email");
        InvalidEmail.click();
        common.waitAboutSeconds(4);
    }

    public boolean shouldSeeMessagesInvalidEmail() {
        String text1 = "Votre email n'a pas été validé";
        String text2 = "Vous n'avez pas validé votre email";
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.view.ViewGroup/android.widget.TextView[@text='Editer votre prénom']")));
        System.out.println(list.size());
        if (list.size() == 0) {
            String error1 = common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[2]"))).getText();
            Assert.assertEquals(text1, error1);
            System.out.println(error1);
            return true;
        } else {
            String error2 = common.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[2]"))).getText();
            Assert.assertEquals(text2, error2);
            System.out.println(error2);
        }
        return false;
    }

    public void theUserChangeAvatarInvalid() {
        common.click("chooseImageGallery");
        common.click("albumTab");
        common.click("testFolderImage");
        common.click("thePhoto");
    }

    public boolean theVirtualDateIconInThreadChatDisappear() {
        common.waitAboutSeconds(5);
        try {
            common.elementShouldBeDisplay("cupIcon");
            return false;
        } catch (Exception e) {
            System.out.println("Can not see virtual date icon => pass");
            return true;
        }
    }

    public void makeSureTheAppIsnTCrashed() {
        //sau khi up anh, doi 30s de xem co crash app khong
        common.waitAboutSeconds(30);
        try {
            //for edit my profile
            common.checkElementIsDisplay("editMyProfileText");
            //check xem sau khi up anh, co the click vao cac option khong
            common.click("anniversaryOption");
            common.click("backButton");
            common.click("originOption");
            common.click("backButton");
            common.theUserScrollDownAndClick("Localisation");
            common.click("backButton");
        } catch (Exception e) {
            //for edit my criteria
            common.checkElementIsDisplay("editMyCriteriaText");
            common.click("locationOption");
            common.click("backButton");
            common.click("originOption");
            common.click("backButton");
            common.click("pratiquantOption");
            common.click("backButton");
        }
    }

    public int checkCurrentNumberOfPhotos() {
        common.click("avatar");
        common.click("modifyPhotoOption");

        common.waitAboutSeconds(3);
        ArrayList<MobileElement> avatar = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countAvatar"))));
        System.out.println("Number of photo before: " + avatar.size() + " photo");

        common.click("closeViewPhotoIcon");
        return avatar.size();
    }

    public boolean makeSureThisPhotoIsPosted() {
        common.click("avatar");
        common.click("modifyPhotoOption");

        common.waitAboutSeconds(3);
        ArrayList<MobileElement> avatarPresent = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countAvatar"))));
        System.out.println("Number of photo after: " + avatarPresent.size() + " photo");

        return avatarPresent.size() == checkCurrentNumberOfPhotos() + 1;
    }

    public boolean theUserChooseFromADeviceToPostIt(String picture) {
        //check current
        common.click("avatar");
        common.click("modifyPhotoOption");
        common.waitAboutSeconds(3);
        ArrayList<MobileElement> avatar = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countAvatar"))));
        System.out.println("Number of photo before: " + avatar.size() + " photo");
        common.click("closeViewPhotoIcon");

        //post
        common.click("avatar");
        common.click("choosePhotoFromGalleryOption");
        messageModule.iChoosePermissionVoice("allow");
        common.postPhotoFromGallery(picture);
//        common.alertMessageWithContentIsShowedOnTop("Votre photo est envoyée pour modération");

        //check
        common.click("avatar");
        common.click("modifyPhotoOption");
        common.waitAboutSeconds(3);
        ArrayList<MobileElement> avatarPresent = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countAvatar"))));
        System.out.println("Number of photo after: " + avatarPresent.size() + " photo");


        System.out.println(avatar.size() + 1);
        System.out.println(avatarPresent.size());
        return avatarPresent.size() == avatar.size() + 1;

//        String a = (avatarPresent.size());
//        int b = (avatar.size() + 1);
//        System.out.println(a);
//        System.out.println(b);

//        if (avatarPresent.size() != avatar.size()) {
//            System.out.println((avatar.size() + 1));
//            System.out.println(avatarPresent.size());
//            System.out.println("true");
//            return false;
//        } else {
//            System.out.println((avatar.size() + 1));
//            System.out.println(avatarPresent.size());
//            System.out.println("false");
//            return false;
//        }
    }

    public boolean theUserTakeAPhotoFromDeviceAndPostIt() {
        //check current
        common.click("avatar");
        common.click("modifyPhotoOption");
        common.waitAboutSeconds(3);
        ArrayList<MobileElement> avatar = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countAvatar"))));
        System.out.println("Number of photo before: " + avatar.size() + " photo");
        common.click("closeViewPhotoIcon");

//        //post
//        common.click("avatar");
//        common.click("uploadPhotoTakingPhotoOption");
//        messageModule.iChoosePermissionVoice("allow");
//        common.takeAPhoto();

        //check
        common.click("avatar");
        common.click("modifyPhotoOption");
        common.waitAboutSeconds(3);
        ArrayList<MobileElement> avatarPresent = new ArrayList<>(driver.findElements(By.xpath(TestDataService.properties.getProperty("countAvatar"))));
        System.out.println("Number of photo after: " + avatarPresent.size() + " photo");


        System.out.println(avatar.size() + 1);
        System.out.println(avatarPresent.size());
        return avatarPresent.size() == avatar.size() + 1;
    }

    public void theUserGoToTheStatisticsScreen() {
        // get location of device
        System.out.println("dimision :" + driver.manage().window().getSize());
        int y = driver.manage().window().getSize().getHeight() / 2;
        int x = driver.manage().window().getSize().getWidth() / 2;

        common.waitDisplayButtonXpathAndClick("myProfileBtn");
        ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//android.widget.TextView[@text='FERMER']")));
        if (list.size() == 1) {
            list.get(0).click();
        }

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
    }

    public void theUserLogsOutFromCurrentAccount() {
        common.theUserScrollDownAndClick("connecter");
    }

    public void loginFailWithAnAccountHasOldDataByEmailAndPassword(String email, String password) {
        if (OS == MobilePlatform.IOS) {
            MobileElement seConnecterButton = common.driver.findElementByAccessibilityId("SE CONNECTER");
            seConnecterButton.click();

            MobileElement usernameTextInput = common.driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Adresse mail Adresse mail\"])[3]/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
            usernameTextInput.clear();
            usernameTextInput.sendKeys(email);
            MobileElement passwordTextInput = common.driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"Votre mot de passe Votre mot de passe\"])[1]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
            passwordTextInput.sendKeys(password);

            MobileElement el3 = common.driver.findElementByAccessibilityId("ME CONNECTER");
            el3.click();
            MobileElement el4 = common.driver.findElementByAccessibilityId("ME CONNECTER");
            el4.click();
            common.waitAboutSeconds(3);

            MobileElement rencontreText = common.driver.findElementByAccessibilityId("Rencontre");
            String innertext = rencontreText.getText();
            Assert.assertEquals("Rencontre", innertext);

        } else {
            common.waitAboutSeconds(5);
            System.out.println("In Login Page method");
            common.clickButtonByXpath("seConnecterBtn");
            common.waitAboutSeconds(1);
//            common.iClickButtonHasText("SE CONNECTER");
//            common.waitAboutSeconds(3);
            common.clearTextAndInsertTextIntoField(email, "emailInput");
            common.clearTextAndInsertTextIntoField(password, "passwordInput");

            common.iClickButtonHasText("ME CONNECTER");
            common.waitAboutSeconds(3);
            common.checkElementIsDisplay("errorMsg");
        }
    }

    public void theUserShouldSeeThemInBlocklist() {
        common.clickButtonByXpath("myAccountMenuBtn");
        common.clickButtonByXpath("settingTab");
        common.clickButtonByXpath("blacklistMenu");

        String nameActual = "";
        try {
            nameActual = common.ReadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String nameExpected = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("nameOfFirstProfileFromBlocklistAndNotification"))).getText().toLowerCase(Locale.ROOT);
        System.out.println("\nThe name is found in block list: " + nameExpected);
        assertEquals(nameExpected, nameActual); //compare name discovery with name block list
    }

    public void removeThatProfileFromBlocklistSuccessfully() {
        String name = "";
        try {
            name = common.ReadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //after delete success, case: list still have another profile
        try {
            String getName = common.subStringGetFirstWord("nameOfFirstProfileFromBlocklistAndNotification");
            String nameCapitalized = getName.substring(0, 1).toLowerCase() + getName.substring(1);
            System.out.println("The first profile is found in block list is: " + nameCapitalized);
            assertNotEquals("Unblock them from profile but still see in block list => fail", nameCapitalized, name);
            System.out.println("Can not find \"" + name + "\" in list => Remove successfully");
        }
        //after delete success, case: list is empty
        catch (Exception E) {
            System.out.println("List is empty, so remove successfully");
        }
    }

    public void theUserBlockThemFromNotification() {
        common.getNicknameOfUser("getPseudoOfFirstProfileFromNotification");
        common.clickButtonByXpath("optionIconInNotification");
        common.clickButtonByXpath("blockFormNotificationMenu");
    }

    public void shouldSeePhotoFilterButtonAutoTurnOff() {
        common.checkElementIsDisplay("filterPhotoToggleSwitch_OFF");
    }

    public void makeSureTheUserDoesNotHaveAnAvatar() {
        common.waitAboutSeconds(3);
        common.click("myAccountMenuBtn");
        common.checkElementIsDisplay("userDoesNotHaveAvatar");
        System.out.println("The user don't have an avatar => pass");
    }

    public void makeSureTheUserHaveAnAvatar() {
        common.waitAboutSeconds(3);
        common.click("myAccountMenuBtn");
        common.checkElementIsDisplay("userHaveAvatar");
        System.out.println("The user have an avatar => pass");
    }

    public void theUserSelectALabel(String label) {
        common.click("selectLabelService");
        common.click(label);
//        common.checkElementIsDisplay(label);
    }

    public void theUserAddToSendServiceClient(String picture) {
        common.click("chooseImageServiceText");
        common.postPhotoFromGallery(picture);
        common.checkElementIsDisplay("screenshotAdded");
        /* The user can add only one screenshot
           When add success, button “Add a screenshot” disappear */
        common.isDisappeared("chooseImageServiceText");
    }

    public void backToPreviousByUsingBackButton() {
        driver.navigate().back();
    }

    public void theUserShouldSeeBorderColorRedWhenInputAnInvalidEmailAddress() {
        common.clearTextAndInsertTextIntoField("mektoube", "currentPassword");
        common.waitAboutSeconds(2);
        String[] values = {
                "#@%^%#@#@#.com",
                "wrongEmail.com",
                "thaocutp.gmail.com",
                "thao@@gmail.com",
                "001xs.xyz"
        };
        for (String value : values) {
            //click outside to close the keyboard
            common.click("titleChangeEmail");
            common.clearTextAndInsertTextIntoField(value, "newEmailTextField");
            common.waitAboutSeconds(1);
            //click outside to close the keyboard
            common.click("titleChangeEmail");
            common.waitAboutSeconds(1);
            common.click("registerEmailBtn");
            shouldSeeRedBorderAtTheIncorrectFields();
        }
    }

    public void theUserShouldSeeBorderColorRedWhenInputPasswordIsInvalid() {
//        System.out.println("Case01: change password ");
//        common.waitAboutSeconds(10);
//        common.clearTextAndInsertTextIntoField("", "currentPasswordTextField2");
//        common.clearTextAndInsertTextIntoField("", "newPasswordTextField");
//        common.click("confirmChangePassBtn");
//        shouldSeeRedBorderAtTheIncorrectFields();
//
//        System.out.println("Case02: change password ");
//        common.waitAboutSeconds(10);
//        common.clearTextAndInsertTextIntoField("", "currentPasswordTextField2");
//        common.clearTextAndInsertTextIntoField("Mektoube2017", "newPasswordTextField");
//        common.click("confirmChangePassBtn");
//        shouldSeeRedBorderAtTheIncorrectFields();

        System.out.println("Case03: the current password is wrong  ");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("wrongPass", "currentPasswordTextField2");
        common.clearTextAndInsertTextIntoField("", "newPasswordTextField");
        common.waitAboutSeconds(2);
        common.click("confirmChangePassBtn");
        shouldSeeRedBorderAtTheIncorrectFields();

        System.out.println("Case04: the new password is empty ");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("mektoube", "currentPasswordTextField2");
//        common.driver.findElement(By.xpath(TestDataService.properties.getProperty("currentPasswordTextField2"))).sendKeys("mektoube");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("", "currentPasswordTextField2");
//        common.driver.findElement(By.xpath(TestDataService.properties.getProperty("newPasswordTextField"))).sendKeys("");
        common.waitAboutSeconds(2);
        common.click("confirmChangePassBtn");
        shouldSeeRedBorderAtTheIncorrectFields();
    }

    public void theUserShouldSeeAlertErrorMessagingWhenInputAnInvalidEmailAddress() {
        System.out.println("Case01: email do not accept ");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("mektoube", "currentPassword");
        common.clearTextAndInsertTextIntoField("lantiu11@gmail.com", "newEmailTextField");
        common.click("registerEmailBtn");
        common.shouldSeeAlertMessageWithContentIsShowedOnTop("Les adresses mails temporaires ne sont pas acceptées");

        System.out.println("Case02: email exist with another account ");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("mektoube", "currentPassword");
        common.clearTextAndInsertTextIntoField("qa+lily3@mektoube.fr", "newEmailTextField");
        common.click("registerEmailBtn");
        common.shouldSeeAlertMessageWithContentIsShowedOnTop("Cet email est déjà utilisé sur un autre compte");

        System.out.println("Case03: email with the wrong format");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("mektoube", "currentPassword");
        common.clearTextAndInsertTextIntoField("thao.@gmail.com", "newEmailTextField");
        common.click("registerEmailBtn");
        common.shouldSeeAlertMessageWithContentIsShowedOnTop("L'Email n'est pas valide");

        System.out.println("Case04: email with the wrong format");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("mektoube", "currentPassword");
        common.clearTextAndInsertTextIntoField(".thao@gmail.com", "newEmailTextField");
        common.click("registerEmailBtn");
        common.shouldSeeAlertMessageWithContentIsShowedOnTop("L'Email n'est pas valide");

        System.out.println("Case05: email same as current email ");
        common.waitAboutSeconds(2);
        common.clearTextAndInsertTextIntoField("mektoube", "currentPassword");
        common.clearTextAndInsertTextIntoField("qa+rose@mektoube.fr", "newEmailTextField");
        common.click("registerEmailBtn");
        common.shouldSeeAlertMessageWithContentIsShowedOnTop("Cet email est déjà associé à votre compte");
    }

    public boolean emptyFieldIsDisplay(String field) {
        common.waitAboutSeconds(2);
        return common.driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'remplir')]/preceding-sibling::android.widget.TextView[@text='" + field + "']")).isDisplayed();
    }

    public void clickEmptyField(String field) {
        common.waitAboutSeconds(2);
        common.driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'remplir')]/preceding-sibling::android.widget.TextView[@text='" + field + "']")).click();
    }

    public boolean doNotAllowUsersToValidationAllFieldsLeftBlank() {
//        common.iScrollUpToText("À remplir");
//        while (common.driver.findElement(By.xpath(TestDataService.properties.getProperty("blankField"))).isDisplayed()){
//            try {
//                common.click("blankField");
//            } catch (Exception e) {
//                common.theUserScrollDownAndClick("remplir");
//            }
//            common.waitUntilIsInvisiable("modalEditMyProfile");
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Mon accroche")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMonAccroche"))).isDisplayed();
//                common.click("iconBack");
//            } else if (question.contentEquals("Pratiquant")) {
//                common.click("validateButton");
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        return true;
        String field01 = "Mon accroche";
        String field02 = "Pratiquant";
        String field03 = "Sur mektoube pour";
        String field04 = "Situation familiale";
        String field05 = "A des enfants";
        String field06 = "Veut des enfants";
        String field07 = "Style";
        String field08 = "Silhouette";
        String field09 = "Tabac";
        String field10 = "Cheveux";
        String field11 = "Habitudes Alimentaires";
        String field12 = "Langues";
        String field13 = "Domicile";
        String field14 = "Caractères";
        String field15 = "Activités Sportives";
        String field16 = "Sports pratiqués";
        String field17 = "Niveau d'études";
        String field18 = "Secteur d'activité";


//        //Field01
//        if (emptyFieldIsDisplay(field01)){
//            clickEmptyField(field01);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Mon accroche")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMonAccroche"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //Field02
//        try {
//            if (emptyFieldIsDisplay(field02)) {
//                clickEmptyField(field02);
//                String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//                if (question.contentEquals("Pratiquant")) {
//                    common.click("validateButton");
//                    common.waitAboutSeconds(3);
//                    common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                    common.click("iconBack");
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Da validate");
//        }
        //Field03
        common.iScrollUpToText(field03);
//        if (emptyFieldIsDisplay(field03)) {
//            clickEmptyField(field03);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Sur mektoube pour")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //Field04
//        if (emptyFieldIsDisplay(field04)) {
//            clickEmptyField(field04);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Situation familiale")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //field05
//        if (emptyFieldIsDisplay(field05)) {
//            clickEmptyField(field05);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("A des enfants")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //field06
//        if (emptyFieldIsDisplay(field06)) {
//            clickEmptyField(field06);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Veut des enfants")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //field07
//        if (emptyFieldIsDisplay(field07)) {
//            clickEmptyField(field07);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Style")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //field08
//        if (emptyFieldIsDisplay(field08)) {
//            clickEmptyField(field08);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Comment vous décririez-vous physiquement ?")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
        //field09
        common.iScrollUpToText(field09);
//        if (emptyFieldIsDisplay(field09)) {
//            clickEmptyField(field09);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Fumeuse")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
//        //field10
//        if (emptyFieldIsDisplay(field10)) {
//            clickEmptyField(field10);
//            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
//            if (question.contentEquals("Cheveux")) {
//                common.click("validateButton");
//                common.waitAboutSeconds(3);
//                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
//                common.click("iconBack");
//            }
//        }
        //field11
        common.iScrollUpToText(field14);
        if (emptyFieldIsDisplay(field11)) {
            clickEmptyField(field11);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Habitudes Alimentaires")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field12
        if (emptyFieldIsDisplay(field12)) {
            clickEmptyField(field12);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Langues")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field13
        if (emptyFieldIsDisplay(field13)) {
            clickEmptyField(field13);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Domicile")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field14
        if (emptyFieldIsDisplay(field14)) {
            clickEmptyField(field14);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Caractères")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field15
        if (emptyFieldIsDisplay(field15)) {
            clickEmptyField(field15);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Activités Sportives")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field16
        if (emptyFieldIsDisplay(field16)) {
            clickEmptyField(field16);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Sports pratiqués")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field17
        if (emptyFieldIsDisplay(field17)) {
            clickEmptyField(field17);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Quel est votre niveau d'études ?")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
        //field18
        if (emptyFieldIsDisplay(field18)) {
            clickEmptyField(field18);
            String question = common.driver.findElement(By.xpath(TestDataService.properties.getProperty("question"))).getText();
            if (question.contentEquals("Secteur d'activité")) {
                common.click("validateButton");
                common.waitAboutSeconds(3);
                common.driver.findElement(By.xpath(TestDataService.properties.getProperty("errorMessage"))).isDisplayed();
                common.click("iconBack");
            }
        }
//        common.waitUntilIsInvisiable(".filter:nth-child(2)");
//        ArrayList<WebElement> emptyFields = new ArrayList<>(common.driver.findElements(By.cssSelector(".filter__item-aside span.value-text.is-red")));
//        System.out.println(emptyFields.size());
//        for (int i = 0; i < emptyFields.size(); i++) {
//            common.waitAboutSeconds(1);
//            JavascriptExecutor jse = (JavascriptExecutor) common.driver;
//            jse.executeScript("arguments[0].scrollIntoView()", emptyFields.get(i));
//            emptyFields.get(i).click();
//            common.waitUntilIsInvisiable("div[class='setting--modal filter--modal modal is--opened']");
//
//            String question = common.driver.findElement(By.cssSelector(".modal__header h4")).getText();
//
//            if (question.contentEquals("Mon accroche")) {
//                common.waitUntilIsInvisiable("button.modal-circle-check-btn");
//                common.driver.findElement(By.cssSelector("button.modal-circle-check-btn")).click();
//                WebDriverWait wait = new WebDriverWait(common.driver, 10);
//                WebElement popupErrorMsgElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
//                String popupErrorMsgElementText = popupErrorMsgElements.getText();
//                if (!popupErrorMsgElementText.contentEquals("Le champ est vide")) {
//                    System.out.println(i + " " + question + " " + popupErrorMsgElementText);
//                    return false;
//                }
//                common.waitUntilIsInvisiable("a.closer i.material-icons");
//                common.driver.findElement(By.cssSelector("a.closer i.material-icons")).click();
//            } else if (question.contentEquals("Combien mesurez-vous ?")) {
//                common.waitUntilIsInvisiable("a.closer i.material-icons");
//                common.driver.findElement(By.cssSelector("a.closer i.material-icons")).click();
//            } else {
//                common.waitUntilIsInvisiable("button.modal-circle-check-btn");
//                common.driver.findElement(By.cssSelector("button.modal-circle-check-btn")).click();
//                WebDriverWait wait = new WebDriverWait(common.driver, 10);
//                WebElement popupErrorMsgElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
//                String popupErrorMsgElementText = popupErrorMsgElements.getText();
//                System.out.println(popupErrorMsgElementText + " " + i);
//                if (!popupErrorMsgElementText.contentEquals("Sélectionnez un champ pour valider")) {
//                    System.out.println(i + " " + question + " " + popupErrorMsgElementText);
//                    return false;
//                }
//                common.waitUntilIsInvisiable("a.closer i.material-icons");
//                common.driver.findElement(By.cssSelector("a.closer i.material-icons")).click();
//
//            }
//        }
//        emptyFields = new ArrayList<>(common.driver.findElements(By.cssSelector(".filter__item-aside span.value-text.is-red")));
//        System.out.println(emptyFields.size());
        return true;
    }

    public void goToMyProfileAndCheckDisplayBoostProfile() {
        common.waitDisplayButtonXpathAndClick("myProfile");
        common.waitUntilDisplayElementByXpath("//android.widget.ImageView");
        ArrayList<MobileElement> isBoosted = new ArrayList<>(driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[last()]//android.widget.TextView[@text='Obtenir plus de visites']")));
        BasePage.stackInt.add(isBoosted.size());
        System.out.println(BasePage.stackInt);
        if (isBoosted.size() == 1) {
            System.out.println("the profile doesn't boosted");
        } else System.out.println("the profile is boosted");
    }
}