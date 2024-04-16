package com.mektoube.steps;


import com.mektoube.pages.BasePage;
import com.mektoube.pages.MessageModule;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class MessageModuleSteps {

    MessageModule messagePage = new MessageModule();
    BasePage basePage = new BasePage();
//    DiscoveryPage discoveryPage = new DiscoveryPage();

    @When("click {string} in menu bar")
    public void clickInMenuBar(String icon) {
        messagePage.clickInMenuBar(icon);
    }

    @When("click thread message index {int} in thread list")
    public void clickThreadMessageIndexInThreadList(int index) {
        messagePage.clickThreadMessageIndexInThreadList(index);
    }

    @Then("conversation is displayed")
    public void conversationIsDisplayed() {
        messagePage.conversationIsDisplayed();
    }

    @Then("Text message is send realtime")
    public void textMessageIsSendRealtime() {
        messagePage.textMessageIsSendRealtime();
    }

    @Then("check unread message")
    public void checkUnreadMessage() {
        messagePage.checkUnreadMessage();
    }

    @When("Swipe left in thread index {int} to delete thread chat")
    public void swipeLeftInThreadIndexToDeleteThreadChat(int index) {
        messagePage.swipeLeftInThreadIndexToDeleteThreadChat(index);
    }

    @Then("That thread is deleted success")
    public void thatThreadIsDeletedSuccess() {
        messagePage.thatThreadIsDeletedSuccess();
    }

    @Then("message is send realtime")
    public void messageIsSendRealtime() {
        messagePage.messageIsSendRealtime();
    }

    @When("read newest message and check counter")
    public void readNewestMessageAndCheckCounter() {
        messagePage.readNewestMessageAndCheckCounter();
    }

    @Given("Sign up")
    public void signUp() {
        messagePage.signUp();
    }

    @When("Click photo index {int} in gallery")
    public void clickPhotoIndexInGallery(int index) {
        messagePage.clickPhotoIndexInGallery(index);
    }

    @When("Select time view {string}")
    public void selectTimeView(String seconds) {
        messagePage.iClickButtonHasText(seconds);
    }

    @When("Send a text message {string}")
    public void sendATextMessage(String text) {
        basePage.waitDisplayButtonXpathAndClick("inputTextMsg");
        basePage.clearTextAndInsertTextIntoField(text, "inputTextMsg");
        basePage.waitDisplayButtonXpathAndClick("sendButton");
        basePage.waitAboutSeconds(3);
        messagePage.textMessageIsSendRealtime();
    }

    @Then("last message in thread {int} is {string} message")
    public void lastMessageInThreadIsMessage(int index, String lastMsg) {
        messagePage.lastMessageInThreadIsMessage(index, lastMsg);
    }

    @When("I long press button {string} with {int} seconds")
    public void iLongPressButtonWithSeconds(String btn, int seconds) {
        messagePage.iLongPressButton(btn, seconds);
    }

    @When("I choose {string} permission voice")
    public void iChoosePermissionVoice(String permission) {
        messagePage.iChoosePermissionVoice(permission);
    }

    @When("sent voice message")
    public void sentVoiceMessage() {
        messagePage.sentVoiceMessage();
    }

    @Then("Sent voice message success")
    public void sentVoiceMessageSuccess() {
        Assert.assertTrue(messagePage.sentVoiceMessageSuccess());
    }

    @Then("Display modal ask permission again")
    public void displayModalAskPermissionAgain() {
        Assert.assertTrue(messagePage.displayModalAskPermissionAgain());
    }

    @Then("Redirect to setting")
    public void redirectToSetting() {
        messagePage.redirectToSetting();
    }

    @And("Enable permission and click back")
    public void enablePermissionAndClickBack() {
        messagePage.enablePermissionAndClickBack();
    }

    @When("I scroll down")
    public void iScrollDown() {
        messagePage.iScrollDown();
    }

    @When("I lock recording voice")
    public void iLockRecordingVoice() {
        messagePage.iLockRecordingVoice();
    }

    @Then("Display text input")
    public void displayTextInput() {
        Assert.assertTrue(messagePage.displayTextInput());
    }

    @When("Sent voice messsage")
    public void sentVoiceMesssage() {
        messagePage.sentVoiceMesssage();
    }

    @When("Turn On or Off filter photo")
    public void turnOnOrOffFilterPhoto() {
        messagePage.turnOnOrOffFilterPhoto();
    }

    @Then("Display modal can't sent voice when enable filter photo")
    public void displayModalCanTSentVoiceWhenEnableFilterPhoto() {
        Assert.assertTrue(messagePage.displayModalCanTSentVoiceWhenEnableFilterPhoto());
    }

    @When("Sent voice messsage \\(accept permission)")
    public void sentVoiceMesssageAcceptPermission() {
        messagePage.sentVoiceMesssageAcceptPermission();
    }

    @When("Send a ephemeral photo")
    public void sendAEphemeralPhoto() {
        messagePage.sendAEphemeralPhoto();
    }

    @When("Send a gif message")
    public void sendAGifMessage() {
        basePage.waitDisplayButtonXpathAndClick("gifButton");
        basePage.waitUntilDisplayElementByXpath("firstGifInList");
        basePage.waitAboutSeconds(2);
        basePage.clickButtonByXpath("firstGifInList");
        basePage.clickButtonByXpath("sendButton");
        basePage.waitUntilDisplayElementByXpath("lastSentGif");
        messageIsSendRealtime();
    }

    @Then("Show viewed time on photo and cannot view again")
    public void showViewedTimeOnPhotoAndCannotViewAgain() {
        messagePage.showViewedTimeOnPhotoAndCannotViewAgain();
    }

    @When("Scroll down to see old message")
    public void scrollDownToSeeOldMessage() {
        messagePage.scrollDownToSeeOldMessage();
    }

    @Then("Check last message is still right")
    public void checkLastMessageIsStillRight() {
        messagePage.checkLastMessageIsStillRight();
    }

    @When("Reaction {string} icon on message index {int}")
    public void reactionIconOnMessageIndex(String icon, int index) {
        messagePage.reactionIconOnMessageIndex(icon, index);
    }

    @Then("Show last voice in thread chat")
    public void showLastVoiceInThreadChat() {
        Assert.assertTrue(messagePage.showLastVoiceInThreadChat());
    }

    @When("I click report voice message")
    public void iClickReportVoiceMessage() {
        messagePage.iClickReportVoiceMessage();
    }

    @When("I click report voice message and choose block")
    public void iClickReportVoiceMessageAndChooseBlock() {
        messagePage.iClickReportVoiceMessageAndChooseBlock();
    }

    @When("click three dot felling report reply button in message index {int}")
    public void clickThreeDotFellingReportReplyButtonInMessageIndex(int index) {
        messagePage.clickThreeDotFellingReportReplyButtonInMessageIndex(index);
    }

    @When("View newest ephemeral photo in conversation")
    public void viewNewestEphemeralPhotoInConversation() {
        basePage.waitDisplayButtonXpathAndClick("newPhotoMessageInChat");
        basePage.waitUntilDisplayElementByXpath("previewPhoto");
        basePage.waitUntilDisplayElementByXpath("viewedPhoto");
        messagePage.showViewedTimeOnPhotoAndCannotViewAgain();
    }

    @Then("Check replied message is {string} and message reply is {string}")
    public void checkRepliedMessageIsAndMessageReplyIs(String repliedMsg, String msgReply) {
        messagePage.checkRepliedMessageIsAndMessageReplyIs(repliedMsg, msgReply);
    }

    @Then("Text message {string} is replied by {string}")
    public void textMessageIsRepliedBy(String textMsg, String msgReply) {
        messagePage.textMessageIsRepliedBy(textMsg, msgReply);
    }

    @When("Send a voice message")
    public void sendAVoiceMessage() {
        basePage.waitUntilDisplayElementByXpath("voiceButton");
        messagePage.iLongPressButton("voiceButton", 2);
        messagePage.iChoosePermissionVoice("allow");
        basePage.waitAboutSeconds(3);
        messagePage.sentVoiceMessage();
    }

    @When("Search user by pseudo {string} and go to conversation")
    public void searchUserByPseudoAndGoToConversation(String pseudo) {
        messagePage.searchUserByPseudoAndGoToConversation(pseudo);
    }

    @Then("All buttons are grayed out and show message Vous avez bloqué {string}")
    public void allButtonsAreGrayedOutAndShowMessageVousAvezBloqué(String blockedPartner) {
        messagePage.allButtonsAreGrayedOutAndShowMessageVousAvezBloqué(blockedPartner);
    }

    @When("Make sure dont block user {string}")
    public void makeSureDontBlockUser(String partner) {
        messagePage.makeSureDontBlockUser(partner);
    }

    @Then("Conversation is empty")
    public void conversationIsEmpty() {
        messagePage.conversationIsEmpty();
    }

    @And("play voice just sent")
    public void playVoiceJustSent() {
        messagePage.playVoiceJustSent();
    }

    @Then("Voice just play is paused")
    public void voiceJustPlayIsPaused() {
        Assert.assertTrue(messagePage.voiceJustPlayIsPaused());
    }

    @And("Can not play voice")
    public void canNotPlayVoice() {
        messagePage.canNotPlayVoice();
    }

    @When("play voice sent before")
    public void playVoiceSentBefore() {
        messagePage.playVoiceSentBefore();
    }

    @When("The page show message contain text {string}")
    public void thePageShowMessageContainText(String message) {
        messagePage.thePageShowMessageContainText(message);
    }

    @Then("The page redirect {string} screen")
    public void thePageRedirectScreen(String screen) {
        messagePage.thePageRedirectScreen(screen);
    }

    @Then("The page show full modal {string}")
    public void thePageShowFullModal(String modal) {
        messagePage.thePageShowFullModal(modal);
    }

    @When("Check name partner {string} and send photo time")
    public void checkNamePartnerAndSendPhotoTime(String partnerName) {
        messagePage.checkNamePartnerAndSendPhotoTime(partnerName);
    }

    @Then("check active status of partner is show on header in conversation")
    public void checkActiveStatusOfPartnerIsShowOnHeaderInConversation() {
        messagePage.checkActiveStatusOfPartnerIsShowOnHeaderInConversation();
    }

    @Then("check number of threads in thread list before and after refresh")
    public void checkNumberOfThreadsInThreadListBeforeAndAfterRefresh() {
        messagePage.checkNumberOfThreadsInThreadListBeforeAndAfterRefresh();
    }

    @Then("play voice received then play voice just send")
    public void playVoiceReceivedThenPlayVoiceJustSend() {
        messagePage.playVoiceReceivedThenPlayVoiceJustSend();
    }

    @Then("Check status online in profile and conversation")
    public void checkStatusOnlineInProfileAndConversation() {
        messagePage.checkStatusOnlineInProfileAndConversation();
    }

    @When("click conversation with {string} in thread list")
    public void clickConversationWithInThreadList(String partnerName) {
        messagePage.clickConversationWithInThreadList(partnerName);
    }

    @When("last message in thread with {string} is {string}")
    public void lastMessageInThreadWithIs(String partnerName, String lastMessage) {
        messagePage.lastMessageInThreadWithIs(partnerName, lastMessage);
    }

    @When("last message in chat detail is {string}")
    public void lastMessageInChatDetailIs(String lastMsgInChatDetail) {
        messagePage.lastMessageInChatDetailIs(lastMsgInChatDetail);
    }

    @Then("the blue counter is reduced one")
    public void theBlueCounterIsReducedOne() {
        messagePage.theBlueCounterIsReducedOne();
    }

    @Given("login with username or email {string} and password {string} and go to Thread chat")
    public void loginWithUsernameOrEmailAndPasswordAndGoToThreadChat(String email, String password) {
        messagePage.loginWithUsernameOrEmailAndPasswordAndGoToThreadChat(email, password);
    }

    @When("filter unread messages in chat thread if any then read")
    public void filterUnreadMessagesInChatThreadIfAnyThenRead() {
        messagePage.filterUnreadMessagesInChatThreadIfAnyThenRead();
    }

    @When("does not change the order of conversations after reloading the thread list")
    public void doesNotChangeTheOrderOfConversationsAfterReloadingTheThreadList() {
        messagePage.doesNotChangeTheOrderOfConversationsAfterReloadingTheThreadList();
    }

    @When("check status, last message, name and avatar in thread same as in chat detail of conversation with name {string}")
    public void checkStatusLastMessageNameAndAvatarInThreadSameAsInChatDetailOfConversationWithName(String name) {
        messagePage.checkStatusLastMessageNameAndAvatarInThreadSameAsInChatDetailOfConversationWithName(name);

    }

    @When("sent all type message success")
    public void sentAllTypeMessageSuccess() {
        messagePage.sentAllTypeMessageSuccess();
    }

    @And("limit report abuse")
    public void limitReportAbuse() {
        messagePage.limitReportAbuse();
    }

    @Then("visit the profile and comeback")
    public void visitTheProfileAndComeback() {
        messagePage.visitTheProfileAndComeback();
    }

    @When("open thread message with name {string}")
    public void openThreadMessageWithName(String name) {
        messagePage.openThreadMessageWithName(name);
    }


    @And("report voice and photo messsage")
    public void reportVoiceAndPhotoMesssage() {
        messagePage.reportVoiceAndPhotoMesssage();
    }

    @When("report profile")
    public void reportProfile() {
        messagePage.reportProfile();
    }

    @Then("reply all type message")
    public void replyAllTypeMessage() {
        messagePage.replyAllTypeMessage();
    }

}