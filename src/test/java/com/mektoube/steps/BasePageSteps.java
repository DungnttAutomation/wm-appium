package com.mektoube.steps;

import com.mektoube.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class BasePageSteps {
    BasePage basePage = new BasePage();

    @And("wait about {int} seconds")
    public void waitAboutSeconds(int second) { basePage.waitAboutSeconds(second); }

    @And("I click button has text {string}")
    public void iClickButtonHasText(String buttonText) { basePage.iClickButtonHasText(buttonText); }

    @And("wait until display button has text {string}")
    public void waitUntilDisplayButtonHasText(String buttonText) { basePage.waitUntilDisplayButtonHasText(buttonText); }

    @And("Wait until display element by xpath {string}")
    public void waitUntilDisplayElementByXpath(String element) { basePage.waitUntilDisplayElementByXpath(element); }

    @When("click button by xpath {string}")
    public void clickButtonByXpath(String xpathButton) { basePage.clickButtonByXpath(xpathButton); }

    @When("insert {string} into field {string}")
    public void insertIntoField(String text, String xpath) {
        basePage.clearTextAndInsertTextIntoField(text,xpath); }

    @Then("{string} button dont display")
    public void buttonDontDisplay(String xpath) {
        basePage.buttonDontDisplay(xpath);
    }
    
    @When("Alert message with content {string} is showed on top")
    public void alertMessageWithContentIsShowedOnTop(String messageContent) {
        basePage.alertMessageWithContentIsShowedOnTop(messageContent);
    }
    @When("scroll up to element {string}")
    public void scrollUpToElement(String xpath) {
        basePage.scrollUpToElement(xpath);
    }

    @When("Logout current account")
    public void logoutCurrentAccount() { basePage.logoutCurrentAccount();
    }

    @Given("Change environment to {string}")
    public void changeEnvironmentTo(String env) { basePage.changeEnvironmentTo(env);
    }

    @When("Logout current account and login account {string} and password {string}")
    public void logoutCurrentAccountAndLoginAccountAndPassword(String username, String pass) { basePage.logoutCurrentAccountAndLoginAccountAndPassword(username,pass);
    }

    @When("clear text and insert text {string} into field {string}")
    public void clearTextAndInsertTextIntoField(String text, String field) {basePage.clearTextAndInsertTextIntoField(text,field);
    }
    @When("clear text and insert text {string} into field {string} not hide key")
    public void clearTextAndInsertTextIntoFieldNotHideKey(String text, String field) {basePage.clearTextAndInsertTextIntoFieldNotHideKey(text,field);
    }

    @When("the user click on {string}")
    public void theUserClickOn(String button) {
        basePage.theUserClickOn(button);
    }

    @And("{string} is disappeared")
    public void isDisappeared(String xpath) { basePage.isDisappeared(xpath);
    }

    @When("the user type {string} in {string}")
    public void theUserTypeIn(String value, String dataIOS) {
        basePage.clearTextAndInsertTextIntoField(value, dataIOS);
    }

    @Then("should see {string} modal")
    public void shouldSeeModal(String modal) {
        basePage.shouldSeeModal(modal);
    }

    @When("Take a screenshot")
    public void takeAScreenshot() throws IOException {
        basePage.takeAScreenshot();
    }

    @Then("should see alert message with content {string} is showed on top")
    public void shouldSeeAlertMessageWithContentIsShowedOnTop(String messageAlert) {
        basePage.shouldSeeAlertMessageWithContentIsShowedOnTop(messageAlert);
    }

    @Then("See red message {string} under field {string}")
    public void seeRedMessageUnderField(String message, String field) {
        basePage.seeRedMessageUnderField(message,field);
    }

    @Then("wait until dont see this element {string} in screen")
    public void waitUntilDontSeeThisElementInScreen(String element) {
        basePage.waitUntilDontSeeThisElementInScreen(element);
    }

    @When("Scroll down to refresh page")
    public void scrollDownToRefreshPage() {
        basePage.scrollDownToRefreshPage();
    }

    @When("go to Setting tab and click button by text {string}")
    public void goToSettingTabAndClickButtonByText(String text) {
        basePage.goToSettingTabAndClickButtonByText(text);
    }

    @When("get nickname of user")
    public void getNicknameOfUser(String username) {
        basePage.getNicknameOfUser(username);
    }

    @When("wait until {string} is invisiable")
    public void waitUntilIsInvisiable(String xpath) { basePage.waitUntilIsInvisiable(xpath);
    }

    @When("close modal request validate email")
    public void closeModalRequestValidateEmail() { basePage.closeModalRequestValidateEmail();
    }

    @And("check name of a partner is not displayed in full")
    public void checkNameOfAPartnerIsNotDisplayedInFull() {
        basePage.checkNameOfAPartnerIsNotDisplayedInFull();
    }

    @Then("dont display this element {string}")
    public void dontDisplayThisElement(String element) { basePage.dontDisplayThisElement(element);
    }

    @And("show message error {string} of element {string}")
    public void showMessageErrorOfElement(String message, String element) {
        basePage.showMessageErrorOfElement(message, element);
    }

    @When("Enter verify code {string}")
    public void enterVerifyCode(String code) {
        basePage.enterVerifyCode(code);
    }

    @And("scroll up to {string} text at {string}")
    public void scrollUpToTextAt(String text, String option) {
        basePage.scrollUpToTextAt(text,option);
    }

    @When("click on the tab {string}")
    public void clickOnTheTab(String btn) {
        basePage.clickOnTheTab(btn);
    }

    @When("wait until display {string}")
    public void waitUntilDisplay(String xpath) {
        basePage.checkElementIsDisplay(xpath);
    }

    @Given("reset data test")
    public void resetDataTest() {
        basePage.resetDataTest();
    }

    @And("click button by action {string}")
    public void clickButtonByAction(String button) {
        basePage.clickButtonByAction(button);
    }

    @And("click button number {int} at button {string}")
    public void clickButtonNumberAtButton(int number, String button) {
        basePage.clickButtonNumberAtButton(number,button);
    }
}