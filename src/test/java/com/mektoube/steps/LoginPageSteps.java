package com.mektoube.steps;

import com.mektoube.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
    LoginPage loginPage = new LoginPage();
    
    @And("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        loginPage.iAmOnTheLoginPage();
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() {
        loginPage.iClickOnSubmitButton();
    }

    @When("login with username or email {string} and password {string}")
    public void loginWithUsernameOrEmailAndPassword(String email, String password) {
        loginPage.loginWithUsernameOrEmailAndPassword(email, password);
    }

    @Given("I login with account {string} and {string} and {string} permission")
    public void iLoginWithAccountAndAndPermission(String email, String pass, String location) {
        loginPage.iLoginWithAccountAndDisablePermission(email,pass,location);
    }
    @Then("I check error messages")
    public void iCheckErrorMessages() {
        loginPage.iCheckErrorMessages();
    }

    @Then("Messages error is display {string}")
    public void messagesErrorIsDisplay(String errorMsg) {
        loginPage.messagesErrorIsDisplay(errorMsg);
    }

    @When("login with email {string} and password {string}")
    public void loginWithEmailAndPassword(String email, String password) {
        loginPage.loginWithEmailAndPassword(email, password);
    }

    @Given("login with phone nb {string} of country {string} and password {string}")
    public void loginWithPhoneNbOfCountryAndPassword(String phoneNb, String country, String password) {
        loginPage.loginWithPhoneNbOfCountryAndPassword(phoneNb,country,password);
    }

    @Then("show error message {string} of element {string} when enter invalid phone number")
    public void showErrorMessageOfElementWhenEnterInvalidPhoneNumber(String message, String element) {
        loginPage.showErrorMessageWhenEnterInvalidPhoneNumber(message,element);
    }

    @When("search {string} of element {string} and choose country code")
    public void searchOfElementAndChooseCountryCode(String country, String element) {
        loginPage.searchOfElementAndChooseCountryCode(country,element);
    }

    @When("the user click on login by phone number tab")
    public void theUserClickOnLoginByPhoneNumberTab() {
        loginPage.theUserClickOnLoginByPhoneNumberTab();

    }

    @And("change country code to {string} if forgot or login with phone number")
    public void changeCountryCodeToIfForgotOrLoginWithPhoneNumber(String country) {
        loginPage.changeCountryCodeToIfForgotOrLoginWithPhoneNumber(country);
    }
}
