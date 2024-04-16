package com.mektoube.steps;

import com.mektoube.pages.RegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {
    RegistrationPage registrationPage = new RegistrationPage();

    @When("Leave choose and validate {string}")
    public void leaveChooseAndValidate(String submit) {
        registrationPage.leaveChooseAndValidate(submit);
    }

    @And("Should see messages error {string}")
    public void shouldSeeMessagesError(String message) {
        registrationPage.shouldSeeMessagesError(message);
    }

    @Then("Enter a valid date of birth and validate")
    public void enterAValidDateOfBirthAndValidate() {
        registrationPage.enterAValidDateOfBirthAndValidate();
    }
    @And("I select Origin has text {string} and submit {string}")
    public void iSelectOriginHasTextAndSubmit(String buttontext, String submit) {
        registrationPage.iSelectOriginHasTextAndSubmit(buttontext, submit);
    }

    @And("Active {string} permission")
    public void activePermission(String permission) {
        registrationPage.activePermission(permission);
    }

    @Then("I select ville option {int} and submit {string}")
    public void iSelectVilleAndSubmit(int index, String submit) {
        registrationPage.iSelectVilleAndSubmit(index, submit);
    }

    @When("I select pays option {int} and submit {string}")
    public void iSelectPaysOptionAndSubmit(int index, String button) {
        registrationPage.iSelectPaysOptionAndSubmit(index, button);
    }
    @And("I select région option {int} and submit {string}")
    public void iSelectRégionOptionAndSubmit(int index, String submit) {
        registrationPage.iSelectRégionOptionAndSubmit(index, submit);
    }

    @Then("Should see message error at top")
    public void shouldSeeMessageErrorAtTop() {
        registrationPage.shouldSeeMessageErrorAtTop();
    }

    @Then("Should see messages error under line Email")
    public void shouldSeeMessagesErrorUnderLineEmail() {
        registrationPage.shouldSeeMessagesErrorUnderLineEmail();
    }

    @Then("Should see messages error under line UserName")
    public void shouldSeeMessagesErrorUnderLineUserName() {
        registrationPage.shouldSeeMessagesErrorUnderLineUserName();
    }

    @Then("Should see messages error under line Password")
    public void shouldSeeMessagesErrorUnderLinePassword() {
        registrationPage.shouldSeeMessagesErrorUnderLinePassword();
    }

    @Then("Should see message popup CGU at top")
    public void shouldSeeMessagePopupCGUAtTop() {
        registrationPage.shouldSeeMessagePopupCGUAtTop();
    }

    @And("Check eye button working in SignUp Screen")
    public void checkEyeButtonWorkingInSignUpScreen() {
        registrationPage.checkEyeButtonWorkingInSignUpScreen();
    }

    @And("Redirect to {string} Screen")
    public void redirectToScreen(String text) {
        registrationPage.redirectToScreen(text);
    }

    @Then("Click Passer cette étape")
    public void clickPasserCetteÉtape() {
        registrationPage.clickPasserCetteÉtape();
    }

    @When("Create a woman account with email anti username {string} and {string} permission")
    public void createAWomanAccountWithEmailAntiUsername(String antiUsername,String location) {
        registrationPage.createAWomanAccountWithEmailAntiUsername(antiUsername,location);
    }

    @When("Insert anti username {string} and click {string}")
    public void insertAntiUsernameAndClick(String name, String submit) {
        registrationPage.insertAntiUsernameAndClick(name, submit);
    }

    @Then("Create a man account with email {string} username {string} and {string} permission")
    public void createAManAccountWithEmailUsernameAndPermission(String email, String name, String location) {
        registrationPage.createAManAccountWithEmailUsernameAndPermission(email, name, location);
    }

    @Then("check message on top after choose photo in gallery")
    public void checkMessageOnTopAfterChoosePhotoInGallery() {
        registrationPage.checkMessageOnTopAfterChoosePhotoInGallery();
    }

    @Then("show red error message {string} above phone number field")
    public void showRedErrorMessageAbovePhoneNumberField(String error) {
        registrationPage.showRedErrorMessageAbovePhoneNumberField(error);
    }

    @When("select first option after search {string} at field {string}")
    public void selectFirstOptionAfterSearchAtField(String text, String field) {
        registrationPage.selectFirstOptionAfterSearchAtField(text,field);
    }

    @And("select {string} at pays screen")
    public void selectAtPaysScreen(String text) {
        registrationPage.selectAtPaysScreen(text);
    }

    @And("click signup without CGU")
    public void clickSignupWithoutCGU() {
        registrationPage.clickSignupWithoutCGU();
    }

    @When("validate with invalid pseudo and valid pseudo")
    public void validateWithInvalidPseudoAndValidPseudo() {
        registrationPage.validateWithInvalidPseudoAndValidPseudo();
    }

    @And("click country {string} at {string}")
    public void clickCountryAt(String country, String element) {
        registrationPage.clickCountryAt(country,element);
    }
}

