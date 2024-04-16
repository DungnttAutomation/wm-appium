package com.mektoube.steps;

import com.mektoube.pages.BasePage;
import com.mektoube.pages.MyAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MyAccountSteps {
    MyAccountPage myAccountPage = new MyAccountPage();

    @And("the user go to My Account menu")
    public void theUserGoToMyAccountMenu() {
        myAccountPage.theUserGoToMyAccountMenu();
    }

    @When("The user click on Statistic Tab")
    public void theUserClickOnStatisticTab() {
        myAccountPage.theUserClickOnStatisticTab();
    }

    /*Favorites.feature*/
    @When("The user click on Favorites Tab")
    public void theUserClickOnFavoritesTab() {
        myAccountPage.theUserClickOnFavoritesTab();
    }

    @Then("the user should see a favorites list")
    public void theUserShouldSeeAFavoritesList() {
        myAccountPage.theUserShouldSeeAFavoritesList();
    }

    @When("click on first profile in discovery")
    public void clickOnFirstProfileInDiscovery() {
        myAccountPage.clickOnFirstProfileInDiscovery();
    }

    @And("add them into favorites by choose {string}")
    public void addThemIntoFavoritesByChoose(String addToFavoritesBtn) {
        myAccountPage.addThemIntoFavoritesByChoose(addToFavoritesBtn);
    }

    @Then("the user should see them in favorites list")
    public void theUserShouldSeeThemInFavoritesList() {
        myAccountPage.theUserShouldSeeThemInFavoritesList();
    }

    @When("visiting a profile from the favorites Tab")
    public void visitingAProfileFromTheFavoritesTab() {
        myAccountPage.visitingAProfileFromTheFavoritesTab();
    }

    @When("remove them from favorites by choose {string}")
    public void removeThemFromFavoritesByChoose(String removeFromFavoritesBtn) {
        myAccountPage.removeThemFromFavoritesByChoose(removeFromFavoritesBtn);
    }

    @When("back to previous page")
    public void backToPreviousPage() {
        myAccountPage.backToPreviousPage();
    }

    @Then("remove that profile successfully")
    public void removeThatProfileSuccessfully() {
        myAccountPage.removeThatProfileSuccessfully();
    }

    @When("delete a profile from favorites list by swipe left and click on {string}")
    public void deleteAProfileFromFavoritesListBySwipeLeftAndClickOn(String deleteSwipeLeftBtn) {
        myAccountPage.deleteAProfileFromFavoritesListBySwipeLeftAndClickOn(deleteSwipeLeftBtn);
    }


    @When("the user filter click on filter option")
    public void theUserFilterClickOnFilterOption() {
        myAccountPage.theUserFilterClickOnFilterOption();
    }

    @When("choose {string}")
    public void choose(String option) {
        myAccountPage.choose(option);
    }

    @Then("the {string} should be shown")
    public void theShouldBeShown(String results) {
        myAccountPage.theShouldBeShown(results);
    }

    /* start black list*/

    @Then("the user click on Blacklist and should see the their blacklist")
    public void theUserClickOnBlacklistAndShouldSeeTheTheirBlacklist() {
        myAccountPage.theUserClickOnBlacklistAndShouldSeeTheTheirBlacklist();
    }

    @When("the user visiting that first profile from message")
    public void theUserVisitingThatFirstProfileFromMessage() {
        myAccountPage.theUserVisitingThatFirstProfileFromMessage();
    }

    @When("add them into blacklist by choose {string}")
    public void addThemIntoBlacklistByChoose(String element) {
        myAccountPage.addThemIntoBlacklistByChoose(element);
    }

    @When("scroll All page {string}")
    public void scrollAllPage(String element) {
        myAccountPage.scrollAllPage(element);
    }

    @When("The user type into {string}")
    public void theUserTypeInto(String content) {
        myAccountPage.theUserTypeInto(content);
    }


    @Then("should see CGU content")
    public void shouldSeeCGUContent() {
        myAccountPage.shouldSeeCGUContent();
    }

    @Then("should see Vie Privee content")
    public void shouldSeeViePriveeContent() {
        myAccountPage.shouldSeeViePriveeContent();
    }

    @Then("should see Mentions Legales content")
    public void shouldSeeMentionsLegalesContent() {
        myAccountPage.shouldSeeMentionsLegalesContent();
    }

    @Then("should see home page")
    public void shouldSeeHomePage() {
        myAccountPage.shouldSeeHomePage();
    }

    @When("the user click into {string}")
    public void theUserClickInto(String button) {
        myAccountPage.theUserClickInto(button);
    }

    @Then("should see red border at the incorrect fields")
    public void shouldSeeRedBorderAtTheIncorrectFields() {
        assertThat(myAccountPage.shouldSeeRedBorderAtTheIncorrectFields(), is(equalTo(true)));
    }

    @When("the user logout successfully")
    public void theUserLogoutSuccessfully() {
        myAccountPage.theUserLogoutSuccessfully();
    }

    @When("the user actives the photo filter")
    public void theUserActivesThePhotoFilter() {
        assertThat(myAccountPage.theUserActivesThePhotoFilter(), is(equalTo(true)));
    }

    @When("the user inactive the photo filter")
    public void theUserInactiveThePhotoFilter() {
        assertThat(myAccountPage.theUserInactiveThePhotoFilter(), is(equalTo(true)));
    }

    @When("the user try to actives the photo filter")
    public void theUserTryToActivesThePhotoFilter() {
        assertThat(myAccountPage.theUserTryToActivesThePhotoFilter(), is(equalTo(true)));
    }

    @Then("should see {string} in chat detail")
    public void shouldSeeInChatDetail() {
        myAccountPage.shouldSeeInChatDetail();
    }

    @When("the user actives the Age Filter")
    public void theUserActivesTheAgeFilter() {
        myAccountPage.theUserActivesTheAgeFilter();
    }

    @When("the user inactive the Age Filter")
    public void theUserInactiveTheAgeFilter() {
        assertThat(myAccountPage.theUserInactiveTheAgeFilter(), is(equalTo(true)));
    }


    @When("the user try to send to them a {string} to them")
    public void theUserTryToSendToThemAToThem(String messages) {
        myAccountPage.theUserTryToSendToThemAToThem(messages);
    }

    @And("add them into block list by choose {string}")
    public void addThemIntoBlockListByChoose(String button) {
        myAccountPage.addThemIntoBlockListByChoose(button);
    }

    @Then("should see smile and discuss button are disappear")
    public void shouldNotSeeDiscussAndSmileButtonDisappear() {
        assertThat(myAccountPage.shouldNotSeeDiscussAndSmileButtonDisappear(), is(equalTo(true)));
    }

    @When("the user unblock them")
    public void theUserUnblockThem() {
        myAccountPage.theUserUnblockThem();
    }

    @When("the user change avatar invalid")
    public void theUserChangeAvatarInvalid() {
        myAccountPage.theUserChangeAvatarInvalid();
    }

//Android
    @Then("Should see modal validate email")
    public void shouldSeeModalValidateEmail() {
        myAccountPage.shouldSeeModalValidateEmail();

    }

    @Then("Should see messages invalid email")
    public void shouldSeeMessagesInvalidEmail() {
        assertThat(myAccountPage.shouldSeeMessagesInvalidEmail(), is(equalTo(true)));
    }

    @Then("the virtual date icon in thread chat disappear")
    public void theVirtualDateIconInThreadChatDisappear() {
        assertThat(myAccountPage.theVirtualDateIconInThreadChatDisappear(), is(equalTo(true)));
    }

    @When("make sure the app isn't crashed")
    public void makeSureTheAppIsnTCrashed() {
        myAccountPage.makeSureTheAppIsnTCrashed();
    }

    @Then("make sure this photo is posted")
    public void makeSureThisPhotoIsPosted() {
        assertThat(myAccountPage.makeSureThisPhotoIsPosted(), is(equalTo(true)));
    }

    @When("the user choose {string} from a device to post it")
    public void theUserChooseFromADeviceToPostIt(String picture) {
        assertThat(myAccountPage.theUserChooseFromADeviceToPostIt(picture), is(equalTo(true)));
    }

    @When("the user take a photo from device and post it")
    public void theUserTakeAPhotoFromDeviceAndPostIt() {
        assertThat(myAccountPage.theUserTakeAPhotoFromDeviceAndPostIt(), is(equalTo(true)));
    }

    @When("check current number of photos")
    public void checkCurrentNumberOfPhotos() {
        myAccountPage.checkCurrentNumberOfPhotos();
    }

    @When("the user go to the statistics screen")
    public void theUserGoToTheStatisticsScreen() {
        myAccountPage.theUserGoToTheStatisticsScreen();
    }

    @When("the user logs out from current account")
    public void theUserLogsOutFromCurrentAccount() {
        myAccountPage.theUserLogsOutFromCurrentAccount();
    }

    @Then("login fail with an account has old data by email {string} and password {string}")
    public void loginFailWithAnAccountHasOldDataByEmailAndPassword(String email, String password) {
        myAccountPage.loginFailWithAnAccountHasOldDataByEmailAndPassword(email, password);
    }

    @Then("the user should see them in block list")
    public void theUserShouldSeeThemInBlocklist() {
        myAccountPage.theUserShouldSeeThemInBlocklist();
    }

    @When("remove that profile from block list successfully")
    public void removeThatProfileFromBlocklistSuccessfully() {
        myAccountPage.removeThatProfileFromBlocklistSuccessfully();
    }

    @When("the user block them from notification")
    public void theUserBlockThemFromNotification() {
        myAccountPage.theUserBlockThemFromNotification();
    }

    @When("get {string} of user")
    public void getOfUser(String username) {
        new BasePage().getOfUser(username);
    }

    @Then("should see photo filter button auto turn off")
    public void shouldSeePhotoFilterButtonAutoTurnOff() {
        myAccountPage.shouldSeePhotoFilterButtonAutoTurnOff();
    }

    @When("make sure the user does not have an avatar")
    public void makeSureTheUserDoesNotHaveAnAvatar() {
        myAccountPage.makeSureTheUserDoesNotHaveAnAvatar();
    }

    @When("make sure the user have an avatar")
    public void makeSureTheUserHaveAnAvatar() {
        myAccountPage.makeSureTheUserHaveAnAvatar();
    }

    @When("the user select a {string} label")
    public void theUserSelectALabel(String label) {
        myAccountPage.theUserSelectALabel(label);
    }

    @When("the user add {string} to send service client")
    public void theUserAddToSendServiceClient(String picture) {
        myAccountPage.theUserAddToSendServiceClient(picture);
    }

    @When("back to previous by using back button")
    public void backToPreviousByUsingBackButton() {
        myAccountPage.backToPreviousByUsingBackButton();
    }

    @When("make sure {string} do not display")
    public void makeSureDoNotDisplay(String xpath) {
        new BasePage().isDisappeared(xpath);
    }

    @When("the user scroll down and click {string}")
    public void theUserScrollDownAndClick(String text) {
        new BasePage().theUserScrollDownAndClick(text);
    }

    @When("the user go to {string} screen")
    public void theUserGoToScreen(String text) {
        new BasePage().theUserGoToScreen(text);
    }

    @When("the user should see border color red when input an invalid email address")
    public void theUserShouldSeeBorderColorRedWhenInputAnInvalidEmailAddress() {
        myAccountPage.theUserShouldSeeBorderColorRedWhenInputAnInvalidEmailAddress();
    }

    @When("the user should see border color red when input password is invalid")
    public void theUserShouldSeeBorderColorRedWhenInputPasswordIsInvalid() {
        myAccountPage.theUserShouldSeeBorderColorRedWhenInputPasswordIsInvalid();
    }

    @When("the user should see alert error messaging when input an invalid email address")
    public void theUserShouldSeeAlertErrorMessagingWhenInputAnInvalidEmailAddress() {
        myAccountPage.theUserShouldSeeAlertErrorMessagingWhenInputAnInvalidEmailAddress();
    }

    @When("do not allow users to validation all fields left blank")
    public void doNotAllowUsersToValidationAllFieldsLeftBlank() {
        assertThat(myAccountPage.doNotAllowUsersToValidationAllFieldsLeftBlank(), is(equalTo(true)));
    }

    @When("go to my profile and check display boost profile")
    public void goToMyProfileAndCheckDisplayBoostProfile() {
        myAccountPage.goToMyProfileAndCheckDisplayBoostProfile();
    }
}
