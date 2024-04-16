package com.mektoube.steps;

import com.mektoube.pages.BasePage;
import com.mektoube.pages.DiscoveryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DiscoveryPageSteps {
    DiscoveryPage discoveryPage = new DiscoveryPage();
    BasePage basePage = new BasePage();

    @Then("^I am on the discovery page")
    public void iAmOnTheDiscoveryPage() {
        new DiscoveryPage().iAmOnTheDiscoveryPage();
    }

    @And("active location permission after login success")
    public void activeLocationPermissionAfterLoginSuccess() {
        discoveryPage.activeLocationPermissionAfterLoginSuccess();
    }

    @Given("I login with account {string} and {string}")
    public void iLoginWithAccountAnd(String userName, String pass) {
        discoveryPage.iLoginWithAccountAnd(userName, pass);
    }

    @Given("wait display button {string} and click")
    public void waitDisplayButtonXpathAndClick(String searchBtn) {
        discoveryPage.waitDisplayButtonXpathAndClick(searchBtn);
    }

    @When("click button {string}")
    public void clickButton(String btn) {
        basePage.waitDisplayButtonXpathAndClick(btn);
    }
    @When("open url")
    public void openUrl() {
        basePage.openUrl();
    }
    @Then("Display search box have tag {string}")
    public void displaySearchBoxHaveTag(String tagName) {
        Assert.assertTrue("fail", discoveryPage.displaySearchBoxHaveTag(tagName));
    }

    @Then("Redirect to Discovery page")
    public void redirectToDiscoveryPage() {
        discoveryPage.redirectToDiscoveryPage();

    }

    @When("I click on slider {string} around {int}")
    public void iClickOnSliderAround(String xpath, int around) {
        discoveryPage.iClickOnSliderAround(xpath, around);
    }

    @Then("Display on the search box have tag {string}")
    public void displayOnTheSearchBoxHaveTag(String tagAround) {
        //   discoveryPage.displayOnTheSearchBoxHaveTag(tagAround);
    }

    @When("click on the user profile")
    public void clickOnTheUserProfile() {
        discoveryPage.clickOnTheUserProfile();
    }

    @Then("The distance between two accounts in the range matches the filter")
    public void theDistanceBetweenTwoAccountsInTheRangeMatchesTheFilter() {
        Assert.assertTrue(discoveryPage.theDistanceBetweenTwoAccountsInTheRangeMatchesTheFilter());
    }

    @When("I click button {string} ui")
    public void iClickButtonUi(String btn) {
        discoveryPage.iClickButtonUi(btn);
    }

    @Then("Should see text {string}")
    public void shouldSeeText(String text) {
        Assert.assertTrue(discoveryPage.shouldSeeText(text));
    }

//    @When("Scroll and click button {string}")
//    public void scrollAndClickButton(String xpath) {
//        discoveryPage.scrollAndClickButton(xpath);
//    }

    @Then("I click {string} to see photo of user")
    public void iClickToSeePhotoOfUser(String btnPhoto) {
        discoveryPage.iClickToSeePhotoOfUser(btnPhoto);
    }

    @When("choose option {string}")
    public void chooseOption(String option) {
        discoveryPage.scrollAndChooseOption(option);

    }

    @Then("See origine of user account")
    public void seeOrigineOfUserAccount() {
//        discoveryPage.seeOrigineOfUserAccount();
        Assert.assertTrue("fail", discoveryPage.seeOrigineOfUserAccount());
    }

    @And("I click tick button {string}")
    public void iClickTickButton(String btnTick) {
        discoveryPage.iClickTickButton(btnTick);
    }

    @Then("See pays of user account")
    public void seePaysOfUserAccount() {

    }

    @And("I handle slider {string} and {string}")
    public void iHandleSliderAnd(String sldMax, String sldMin) {
        discoveryPage.iHandleSliderAnd(sldMax, sldMin);
    }

    @Then("See taille of user account")
    public void seeTailleOfUserAccount() {
        Assert.assertTrue(discoveryPage.seeTailleOfUserAccount());
    }

    @And("Get value filter {int} and {int}")
    public void getValueFilterAnd(int value1, int value2) {
        discoveryPage.getValueFilterAnd(value1, value2);
    }

    @Then("See age of user account")
    public void seeAgeOfUserAccount() {
        Assert.assertTrue(discoveryPage.seeAgeOfUserAccount());
    }


    @And("Get value filter age {int} and {int}")
    public void getValueFilterAgeAnd(int value1, int value2) {
        discoveryPage.getValueFilterAgeAnd(value1, value2);
    }

    @Then("See Silhouette of user account")
    public void seeSilhouetteOfUserAccount() {
        Assert.assertTrue(discoveryPage.seeSilhouetteOfUserAccount());
    }

    @Then("See Situation familiale of user account")
    public void seeSituationFamilialeOfUserAccount() {
        Assert.assertTrue(discoveryPage.seeSituationFamilialeOfUserAccount());
    }

    @Then("See {string} of user account value {string}")
    public void seeOfUserAccountValue(String key, String value) {
        Assert.assertTrue(discoveryPage.seeOfUserAccountValue(key, value));
    }

    @Then("See Niveau d'études of user")
    public void seeNiveauDÉtudesOfUser() {
        Assert.assertTrue(discoveryPage.seeNiveauDÉtudesOfUser());
    }

    @When("I enter {string} on field")
    public void iEnterOnField(String name) {
        discoveryPage.iEnterOnField(name);
    }

    @Then("See name of user")
    public void seeNameOfUser() {
        Assert.assertTrue(discoveryPage.seeNameOfUser());
    }

    @When("I choose filter age")
    public void iChooseFilterAge() {
        discoveryPage.iChooseFilterAge();
    }

    @When("I choose filter pseudo")
    public void iChooseFilterPseudo() {
        discoveryPage.iChooseFilterPseudo();
    }

    @When("I choose filter origine")
    public void iChooseFilterOrigine() {
        discoveryPage.iChooseFilterOrigine();
    }

    @When("I choose filter fumeuse")
    public void iChooseFilterFumeuse() {
        discoveryPage.iChooseFilterFumeuse();
    }

    @When("I choose filter niveau")
    public void iChooseFilterNiveau() {
        discoveryPage.iChooseFilterNiveau();
    }

    @Then("See profile of account")
    public void seeProfileOfAccount() {
        Assert.assertTrue(discoveryPage.seeProfileOfAccount());
    }

    @When("I filter pseudo")
    public void iFilterPseudo() {
        discoveryPage.iFilterPseudo();
    }

    @When("I click save filter and enter name save filter")
    public void iClickSaveFilterAndEnterNameSaveFilter() {
        discoveryPage.iClickSaveFilterAndEnterNameSaveFilter();
    }

    @When("I click on first save filter")
    public void iClickOnFirstSaveFilter() {
        discoveryPage.iClickOnFirstSaveFilter();
    }

    @Then("Remove save filter")
    public void removeSaveFilter() {
        discoveryPage.removeSaveFilter();
    }

    @When("I rename save filter")
    public void iRenameSaveFilter() {
        discoveryPage.iRenameSaveFilter();
    }

    @Then("See new name of save filter")
    public void seeNewNameOfSaveFilter() {
        Assert.assertTrue(discoveryPage.seeNewNameOfSaveFilter());
    }

    @Then("See error message")
    public void seeErrorMessage() {
        Assert.assertTrue(discoveryPage.seeErrorMessage());
    }

    @Then("Click button search on discovery page and don't display search box")
    public void clickButtonSearhOnDiscoveryPageAndDonTDisplaySearchBox() {
        Assert.assertTrue(discoveryPage.clickButtonSearchOnDiscoveryPageAndDonTDisplaySearchBox());
    }

    @When("I filter account with name {string}")
    public void iFilterAccountWithName(String name) {
        discoveryPage.iFilterAccountWithName(name);
    }

    @Then("Close popup sent contact request")
    public void closePopupSentContactRequest() {
        Assert.assertTrue(discoveryPage.closePopupSentContactRequest());
    }

    @Then("Display error message")
    public void displayErrorMessage() {
        Assert.assertTrue(discoveryPage.displayErrorMessage());
    }

    @Then("Display show popup sent contact request success")
    public void displayShowPopupSentContactRequestSuccess() {
        discoveryPage.displayShowPopupSentContactRequestSuccess();
    }

    @And("I click any someidea")
    public void iClickAnySomeidea() {
        discoveryPage.iClickAnySomeidea();
    }

    @When("I sent contact request to user has name {string}")
    public void iSentContactRequestToUserHasName(String name) {
        discoveryPage.iSentContactRequestToUserHasName(name);
    }

    @When("I logout account")
    public void iLogoutAccount() {
        discoveryPage.iLogoutAccount();
    }

    @When("I click on contact request page")
    public void iClickOnContactRequestPage() {
        discoveryPage.iClickOnContactRequestPage();
    }

    @When("Click accept contact request most recent")
    public void clickAcceptContactRequestMostRecent() {
        discoveryPage.clickAcceptContactRequestMostRecent();
    }

    @And("I choose answer now")
    public void iChooseAnswerNow() {
        discoveryPage.iChooseAnswerNow();
    }

    @And("I choose answer later")
    public void iChooseAnswerLater() {
        discoveryPage.iChooseAnswerLater();
    }

    @When("I click on the first message")
    public void iClickOnTheFirstMessage() {
        discoveryPage.iClickOnTheFirstMessage();
    }

    @Then("See message and profile of partner")
    public void seeMessageAndProfileOfPartner() {
        Assert.assertTrue(discoveryPage.seeMessageAndProfileOfPartner());
    }

    @When("I click refulse contact request")
    public void iClickRefulseContactRequest() {
        discoveryPage.iClickRefulseContactRequest();
    }

    @When("I click list refule")
    public void iClickListRefule() {
        discoveryPage.iClickListRefule();

    }

    @Then("Display user has been refulse")
    public void displayUserHasBeenRefulse() {
        Assert.assertTrue(discoveryPage.displayUserHasBeenRefulse());
    }


    @When("I click profile most recent on contact request")
    public void iClickProfileMostRecentOnContactRequest() {
        discoveryPage.iClickProfileMostRecentOnContactRequest();
    }

    @When("I click refulse on profile")
    public void iClickRefulseOnProfile() {
        discoveryPage.iClickRefulseOnProfile();
    }

    @When("I first profile on list reulse")
    public void iFirstProfileOnListReulse() {
        discoveryPage.iFirstProfileOnListReulse();
    }

    @When("I click accept on profile")
    public void iClickAcceptOnProfile() {
        discoveryPage.iClickAcceptOnProfile();
    }

    @And("I choose answer now on profile")
    public void iChooseAnswerNowOnProfile() {
        discoveryPage.iChooseAnswerNowOnProfile();
    }

    @And("I choose answer later on profile")
    public void iChooseAnswerLaterOnProfile() {
        discoveryPage.iChooseAnswerLaterOnProfile();
    }

    @When("I click accpept on list refulse")
    public void iClickAccpeptOnListRefulse() {
        discoveryPage.iClickAccpeptOnListRefulse();
    }


    @When("I click button reset on discovery page")
    public void iClickButtonResetOnDiscoveryPage() {
        discoveryPage.iClickButtonResetOnDiscoveryPage();
    }

    @Then("The default filter state is hidden")
    public void theDefaultFilterStateIsHidden() {
        Assert.assertTrue(discoveryPage.theDefaultFilterStateIsHidden());
    }

    @When("I scroll up")
    public void iScrollUp() {
        discoveryPage.iScrollUp();
    }

    @Then("I check name of result")
    public void iCheckNameOfResult() {
        Assert.assertTrue(discoveryPage.iCheckNameOfResult());
    }

    @When("I click on boost profile")
    public void iClickOnBoostProfile() {
        discoveryPage.iClickOnBoostProfile();
    }

    @And("I enter {string} on the field")
    public void iEnterOnTheField(String text) {
        discoveryPage.iEnterOnTheField(text);
    }

    @Then("See error message {string}")
    public void seeErrorMessage(String message) {
        Assert.assertTrue(discoveryPage.seeErrorMessageWithText(message));
    }


    @Then("See error message has text {string}")
    public void seeErrorMessageHasText(String message) {
        Assert.assertTrue(discoveryPage.seeErrorMessageHasText(message));
    }

    @Given("I change environment to {string}")
    public void iChangeEnvironmentTo(String env) {
        discoveryPage.iChangeEnviromentTo(env);
    }

    @When("I boost profile and logout")
    public void iBoostProfileAndLogout() {
        discoveryPage.iBoostProfileAndLogout();
    }

    @Then("Can't click star green and hide boost profile")
    public void canTClickStarGreenAndHideBoostProfile() {
        Assert.assertTrue(discoveryPage.canTClickStarGreenAndHideBoostProfile());
    }


    @Then("Display star green in user {string}")
    public void displayStarGreenInUser(String nameBoost) {
        Assert.assertTrue(discoveryPage.displayStarGreenInUser(nameBoost));
    }

    @Given("I filter avec photo")
    public void iFilterAvecPhoto() {
        discoveryPage.iFilterAvecPhoto();
    }

    @Then("I click see all photo of user")
    public void iClickSeeAllPhotoOfUser() {
        discoveryPage.iClickSeeAllPhotoOfUser();
    }

    @When("I scoll to profile similar")
    public void iScollToProfileSimilar() {
        discoveryPage.iScollToProfileSimilar();
    }

    @And("I click profile similar")
    public void iClickProfileSimilar() {
        discoveryPage.iClickProfileSimilar();
    }

    @Then("Show the selected profile")
    public void showTheSelectedProfile() {
        Assert.assertTrue(discoveryPage.showTheSelectedProfile());
    }

    @When("I next profile on similar")
    public void iNextProfileOnSimilar() {
        discoveryPage.iNextProfileOnSimilar();
    }

    @Then("I check list user on similar profile")
    public void iCheckListUserOnSimilarProfile() {
        Assert.assertTrue(discoveryPage.iCheckListUserOnSimilarProfile());
    }

    @And("Get value {string}")
    public void getValue(String value) {
        discoveryPage.getValue(value);
    }

    @Then("Check name of next user")
    public void checkNameOfNextUser() {
        Assert.assertTrue(discoveryPage.checkNameOfNextUser());
    }

    @And("I click on the user profile has name {string}")
    public void iClickOnTheUserProfileHasName(String name) {
        discoveryPage.iClickOnTheUserProfileHasName(name);
    }

    @Then("Display message warning {string} on {string}")
    public void displayMessageWarningOn(String message, String xpath) {
        Assert.assertTrue(discoveryPage.displayMessageWarning(message, xpath));
    }

    @Then("Sent message success")
    public void sentMessageSuccess() {
        Assert.assertTrue(discoveryPage.sentMessageSuccess());
    }

    @And("I choose gif")
    public void iChooseGif() {
        discoveryPage.iChooseGif();
    }

    @When("I click button  sent gif")
    public void iClickButtonSentGif() {
        discoveryPage.iClickButtonSentGif();
    }

    @Then("Sent gif success")
    public void sentGifSuccess() {
        //can't check sent gif success?
    }

    @And("I choose message")
    public void iChooseMessage() {
        discoveryPage.iChooseMessage();
    }

    @Then("Sent message suggest success")
    public void sentMessageSuggestSuccess() {
        Assert.assertTrue(discoveryPage.sentMessageSuggestSuccess());
    }

    @When("Click profile have name {string} after search psedou")
    public void clickProfileHaveNameAfterSearchPsedou(String username) {
        discoveryPage.clickProfileHaveNameAfterSearchPsedou(username);
    }

    @When("Click discuter")
    public void clickDiscuter() {
        discoveryPage.clickDiscuter();
    }

    @Then("See status of user")
    public void seeStatusOfUser() {
        Assert.assertTrue(discoveryPage.seeStatusOfUser());
    }

    @When("I click on see profile user")
    public void iClickOnSeeProfileUser() {
        discoveryPage.iClickOnSeeProfileUser();
    }

    @Then("Check list user on discovery page")
    public void checkListUserOnDiscoveryPage() {
        Assert.assertTrue(discoveryPage.checkListUserOnDiscoveryPage());
    }

    @When("I go to {string} and back to discovery")
    public void iGoToAndBackToDiscovery(String page) {
        Assert.assertTrue(discoveryPage.iGoToAndBackToDiscovery(page));
    }

    @When("Save list name user")
    public void saveListNameUser() {
        discoveryPage.saveListNameUser();
    }

    @Then("Display new notification smile")
    public void displayNewNotificationSmile() {
        Assert.assertTrue(discoveryPage.displayNewNotificationSmile());
    }

    @Then("Display button Discuter")
    public void displayButtonDiscuter() {
        discoveryPage.displayButtonDiscuter();
    }

    @When("I click any reason")
    public void iClickAnyReason() {
        discoveryPage.iClickAnyReason();
    }

    @Then("User account is blocked")
    public void userAccountIsBlocked() {
        Assert.assertTrue(discoveryPage.userAccountIsBlocked());
    }

    @When("check user has name {string} has in blacklist")
    public void checkUserHasNameHasInBlacklist(String name) {
        Assert.assertTrue(discoveryPage.checkUserHasNameHasInBlacklist(name));
//        discoveryPage.iCheckUserHasNameHasInBlacklist(name);
    }

    @When("I go to Discovery page and search user block recently")
    public void iGoToDiscoveryPageAndSearchUserBlockRecently() {
        discoveryPage.iGoToDiscoveryPageAndSearchUserBlockRecently();
    }

    @Then("Result not have user block recently")
    public void resultNotHaveUserBlockRecently() {
        Assert.assertTrue(discoveryPage.resultNotHaveUserBlockRecently());
    }

    @When("I get all name user")
    public void iGetAllNameUser() {
        discoveryPage.iGetAllNameUser();
    }

    @Then("Don't show button discuter and button smile")
    public void donTShowButtonDiscuterAndButtonSmile() {
        Assert.assertTrue(discoveryPage.donTShowButtonDiscuterAndButtonSmile());
    }

    @Then("Show button discuter and button smile")
    public void showButtonDiscuterAndButtonSmile() {
        Assert.assertTrue(discoveryPage.showButtonDiscuterAndButtonSmile());
    }

    @When("I scroll up to {string} text")
    public void iScrollUpToText(String text) {
        discoveryPage.iScrollUpToText(text);
    }

    @Then("Check value of Pratiquante on profile")
    public void checkValueOfPratiquanteOnProfile() {
        Assert.assertTrue(discoveryPage.checkValueOfPratiquanteOnProfile());
    }

    @And("I click profile has photo")
    public void iClickProfileHasPhoto() {
        discoveryPage.iClickProfileHasPhoto();
    }

    @Then("I click photo of profile {string}")
    public void iClickPhotoOfProfile(String photo) {
        discoveryPage.iClickPhotoOfProfile(photo);
    }

    @And("should see pop up premium")
    public void shouldSeePopUpPremium() {
        Assert.assertTrue(discoveryPage.shouldSeePopUpPremium());
    }

    @Then("I click button {string} and view profile")
    public void iClickButtonAndViewProfile(String btn) {
        discoveryPage.iClickButtonAndViewProfile(btn);
    }

    @And("I click button {string} then click button {string}")
    public void iClickButtonThenClickButton(String smile, String next) {
//        Assert.assertTrue(discoveryPage.iClickButtonThenClickButton(smile, next));
        discoveryPage.iClickButtonThenClickButton(smile, next);
    }

    @Then("See text on first screen of modal {string}")
    public void seeTextOnFirstScreenOfModal(String text) {
        Assert.assertTrue(discoveryPage.seeTextOnFirstScreenOfModal(text));
    }

    @And("See text on second screen of modal {string}")
    public void seeTextOnSecondScreenOfModal(String text) {
        Assert.assertTrue(discoveryPage.seeTextOnSecondScreenOfModal(text));
    }

    @And("See text on third screen of modal {string}")
    public void seeTextOnThirdScreenOfModal(String text) {
        Assert.assertTrue(discoveryPage.seeTextOnThirdScreenOfModal(text));
    }

    @Then("Do not display list refulse")
    public void doNotDisplayListRefulse() {
        discoveryPage.doNotDisplayListRefulse();
    }

    @Then("Display list refulse and has name {string} just refulse")
    public void displayListRefulseAndHasNameJustRefulse(String name) {
        Assert.assertTrue(discoveryPage.displayListRefulseAndHasNameJustRefulse(name));
    }

    @Then("Search pesudo {string}")
    public void searchPesudo(String name) {
        discoveryPage.searchPesudo(name);
    }

    @Then("Should see pop up invalid email")
    public void shouldSeePopUpInvalidEmail() {
        Assert.assertTrue(discoveryPage.shouldSeePopUpInvalidEmail());
    }

    @When("I logout account just login")
    public void iLogoutAccountJustLogin() {
        discoveryPage.iLogoutAccountJustLogin();
    }

    @Given("Clear stack")
    public void clearStack() {
        discoveryPage.clearStack();
    }

    @When("I check invitation for woman account")
    public void iCheckInvitationForWomanAccount() {
        discoveryPage.iCheckInvitationForWomanAccount();
    }

    @Then("Display icon boost and show countdown on profile")
    public void displayIconBoostAndShowCountdownOnProfile() {
        discoveryPage.displayIconBoostAndShowCountdownOnProfile();
    }

    @And("check banners to subscribe is display in Discovery Page")
    public void checkBannersToSubscribeIsDisplayInDiscoveryPage() {
        Assert.assertTrue(discoveryPage.checkBannersToSubscribeIsDisplayInDiscoveryPage());
//        discoveryPage.checkBannersToSubscribeIsDisplayInDiscoveryPage();
    }

    @And("check button Block not show")
    public void checkButtonBlockNotShow() {
        discoveryPage.checkButtonBlockNotShow();
    }

    @And("Click on profile has name {string} at contact request screen")
    public void clickOnProfileHasNameAtContactRequestScreen(String name) {
        discoveryPage.clickOnProfileHasNameAtContactRequestScreen(name);
    }

    @Then("Don't show visit of user {string} at noti screen")
    public void donTShowVisitOfUserAtNotiScreen(String name) {
        Assert.assertTrue(discoveryPage.donTShowVisitOfUserAtNotiScreen(name));
    }

    @Then("display red dot on contact icon")
    public void displayRedDotOnContactIcon() {
        Assert.assertTrue(discoveryPage.displayRedDotOnContactIcon());
    }

    @When("go to contact page and refulse all invite")
    public void goToContactPageAndRefulseAllInvite() {
        discoveryPage.goToContactPageAndRefulseAllInvite();
    }

    @Then("go to discovery and hide red dot")
    public void goToDiscoveryAndHideRedDot() {
        Assert.assertTrue(discoveryPage.goToDiscoveryAndHideRedDot());
    }

    @When("see status connection of user on profile")
    public void seeStatusConnectionOfUserOnProfile() {
        discoveryPage.seeStatusConnectionOfUserOnProfile();
    }

    @And("go to chat detail with user")
    public void goToChatDetailWithUser() {
        discoveryPage.goToChatDetailWithUser();

    }

    @Then("check status connection")
    public void checkStatusConnection() {
        Assert.assertTrue(discoveryPage.checkStatusConnection());

    }

    @Then("Display modal ask location and choose {string}")
    public void displayModalAskLocationAndChoose(String opt) {
        discoveryPage.displayModalAskLocationAndChoose(opt);
    }

    @Then("show result seach location")
    public void showResultSeachLocation() {
        Assert.assertTrue(discoveryPage.showResultSeachLocation());
    }

    @Then("Don't hide blue button active location and when click again show modal ask location")
    public void donTHideBlueButtonActiveLocationAndWhenClickAgainShowModalAskLocation() {
        Assert.assertTrue(discoveryPage.donTHideBlueButtonActiveLocationAndWhenClickAgainShowModalAskLocation());
    }

    @When("enable permission location again and click back to app")
    public void enablePermissionLocationAgainAndClickBackToApp() {
        discoveryPage.enablePermissionLocationAgainAndClickBackToApp();
    }

    @Then("check modal permission location")
    public void checkModalPermissionLocation() {
        discoveryPage.checkModalPermissionLocation();
    }

    @Then("show modal sent contact request")
    public void showModalSentContactRequest() {
        Assert.assertTrue(discoveryPage.showModalSentContactRequest());
    }

    @And("check button ACTIVER and click")
    public void checkButtonAndClick( ) {
        discoveryPage.checkButtonAndClick();
    }

    @And("display error when don't select and click validate")
    public void displayErrorWhenDonTSelectAndClickValidate() {
        Assert.assertTrue(discoveryPage.displayErrorWhenDonTSelectAndClickValidate());
    }

    @When("choose search region")
    public void chooseSearchRegion() {
        discoveryPage.chooseSearchRegion();
    }

    @And("filter man with criteria pilosite")
    public void filterManWithCriteriaPilosite() {
        discoveryPage.filterManWithCriteriaPilosite();
    }

    @Then("check information of {int} user")
    public void checkInformationOfUser(int number) {
        Assert.assertTrue(discoveryPage.checkInformationOfUser(number));
    }

    @And("save all name profile at similar to stack")
    public void saveAllNameProfileAtSimilarToStack() {
        discoveryPage.saveAllNameProfileAtSimilarToStack();
    }

    @Then("check similar profile diffirent with each user")
    public void checkSimilarProfileDiffirentWithEachUser() {
        Assert.assertTrue(discoveryPage.checkSimilarProfileDiffirentWithEachUser());
    }

    @Then("do not show field levoile and converti at profile")
    public void doNotShowFieldLevoileAndConvertiAtProfile() {
        Assert.assertTrue(discoveryPage.doNotShowFieldLevoileAndConvertiAtProfile());
    }

    @Then("Check value of Origine on profile")
    public void checkValueOfOrigineOnProfile() {
        Assert.assertTrue(discoveryPage.checkValueOfOrigineOnProfile());
    }

    @Then("the page show all profile that have above Pilosité")
    public void thePageShowAllProfileThatHaveAbovePilosité() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAbovePilosité());
    }

    @And("filter man with criteria le voile")
    public void filterManWithCriteriaLeVoile() {
        discoveryPage.filterManWithCriteriaLeVoile();
    }

    @Then("the page show all profile that have above Le voile")
    public void thePageShowAllProfileThatHaveAboveLeVoile() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAboveLeVoile());
    }

    @Then("display error must select one option in field search")
    public void displayErrorMustSelectOneOptionInFieldSearch() {
        Assert.assertTrue(discoveryPage.displayErrorMustSelectOneOptionInFieldSearch());
    }

    @And("select any multi option")
    public void selectAnyMultiOption() {
        discoveryPage.selectAnyOption("many");
    }

    @Then("the page show all profile that have above Silhouette")
    public void thePageShowAllProfileThatHaveAboveSilhouette() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAboveSilhouette());
    }

    @Then("the page show all profile that have above Silhouette Familiale")
    public void thePageShowAllProfileThatHaveAboveSilhouetteFamiliale() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAboveSilhouetteFamiliale());
    }

    @Then("the page show all profile that have above Enfants")
    public void thePageShowAllProfileThatHaveAboveEnfants() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAboveEnfants());
    }

    @Then("the page show all profile that have above Fumeur")
    public void thePageShowAllProfileThatHaveAboveFumeur() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAboveFumeur());
//        discoveryPage.thePageShowAllProfileThatHaveAboveFumeur();
    }

    @Then("the page show all profile that have above Niveau")
    public void thePageShowAllProfileThatHaveAboveNiveau() {
        Assert.assertTrue(discoveryPage.thePageShowAllProfileThatHaveAboveNiveau());
    }

    @Then("should be show user boosted profile at top of list")
    public void shouldBeShowUserBoostedProfileAtTopOfList() {
        Assert.assertTrue(discoveryPage.shouldBeShowUserBoostedProfileAtTopOfList());
    }

    @Then("Show modal boost profile random")
    public void showModalBoostProfileRandom() {
        Assert.assertTrue(discoveryPage.showModalBoostProfileRandom());
    }

    @Then("click boost profile at my profile show modal")
    public void clickBoostProfileAtMyProfileShowModal() {
        Assert.assertTrue(discoveryPage.clickBoostProfileAtMyProfileShowModal());
    }

    @Then("show red error message when enter invalid pseudo")
    public void showRedErrorMessageWhenEnterInvalidPseudo() {
        Assert.assertTrue(discoveryPage.showRedErrorMessageWhenEnterInvalidPseudo());
    }

    @Then("click green button show error empty name save search")
    public void clickGreenButtonShowErrorEmptyNameSaveSearch() {
        discoveryPage.clickGreenButtonShowErrorEmptyNameSaveSearch();
    }

    @Then("remove all criteria at search box")
    public void removeAllCriteriaAtSearchBox() {
        discoveryPage.removeAllCriteriaAtSearchBox();
    }

    @Then("click back at search form back to discovery")
    public void clickBackAtSearchFormBackToDiscovery() {
        discoveryPage.clickBackAtSearchFormBackToDiscovery();
    }

    @When("close modal encourage user")
    public void closeModalEncourageUser() {
        discoveryPage.closeModalEncourageUser();
    }

    @Then("show modal encourage user")
    public void showModalEncourageUser() {
        discoveryPage.showModalEncourageUser();
    }

    @When("click fill modal encourage and close")
    public void clickFillModalEncourageAndClose() {
        discoveryPage.clickFillModalEncourageAndClose();
    }

    @When("click change email at modal validate email")
    public void clickChangeEmailAtModalValidateEmail() {
        discoveryPage.clickChangeEmailAtModalValidateEmail();
    }

    @Then("show screen select enfant")
    public void showScreenSelectEnfant() {
        Assert.assertTrue(discoveryPage.showScreenSelectEnfant());
    }

    @Then("always show four list profile and show boost button if do not boosted, thumbnail,moderation")
    public void alwaysShowFourListProfileAndShowBoostButtonIfDoNotBoostedThumbnailModeration() {discoveryPage.alwaysShowFourListProfileAndShowBoostButtonIfDoNotBoostedThumbnailModeration();
    }

    @Then("visit right person when click profile from four list")
    public void visitRightPersonWhenClickProfileFromFourList() {discoveryPage.visitRightPersonWhenClickProfileFromFourList();
    }

    @When("show Skip button and Love button when scroll to end page")
    public void showSkipButtonAndLoveButtonWhenScrollToEndPage() {discoveryPage.showSkipButtonAndLoveButtonWhenScrollToEndPage();
    }

    @When("block user from profile should show at blacklist")
    public void blockUserFromProfileShouldShowAtBlacklist() {discoveryPage.blockUserFromProfileShouldShowAtBlacklist();
    }

    @Then("report profile and only report one time")
    public void reportProfileAndOnlyReportOneTime() {discoveryPage.reportProfileAndOnlyReportOneTime();
    }

    @When("react content of profile with name {string}")
    public void reactContentOfProfileWithName(String name) {discoveryPage.reactContentOfProfileWithName(name);
    }

    @When("filter online, birthday,origine, region and check result")
    public void filterOnlineBirthdayOrigineRegionAndCheckResult() {discoveryPage.filterOnlineBirthdayOrigineRegionAndCheckResult();
    }

    @When("filter age, profile recent and check result")
    public void filterAgeProfileRecentAndCheckResult() {discoveryPage.filterAgeProfileRecentAndCheckResult();
    }

    @When("filter has photo, tall, enfant and check result")
    public void filterHasPhotoTallEnfantAndCheckResult() {discoveryPage.filterHasPhotoTallEnfantAndCheckResult();
    }

    @When("filter pratiquant,femeur, study and check result")
    public void filterPratiquantFemeurStudyAndCheckResult() {discoveryPage.filterPratiquantFemeurStudyAndCheckResult();
    }

    @Then("save search, edit search, rename and remove save search")
    public void saveSearchEditSearchRenameAndRemoveSaveSearch() {
        discoveryPage.saveSearchEditSearchRenameAndRemoveSaveSearch();
    }
}

