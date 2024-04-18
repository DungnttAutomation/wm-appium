package com.mektoube.steps;

import com.mektoube.pages.BasePage;
import com.mektoube.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();
    @And("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        loginPage.iAmOnTheLoginPage();
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() {
        loginPage.iClickOnSubmitButton();
    }

    @When("login with username or email {string} and password {string}")
    public void loginWithUsernameOrEmailAndPassword(String email, String password) throws InterruptedException {
//        loginPage.login(email, password);
        basePage.openUrl();
        basePage.waitUntilDisplayElementByXpath("MOBILE_BTN_LOGIN");
        basePage.click("MOBILE_BTN_LOGIN");
//        Thread.sleep(10000);
        basePage.waitAboutSeconds(5);
        basePage.webDriverWaitForElementPresentByCss("LOGIN_TXT_EMAIL",10);
        basePage.sendKeysByCss("LOGIN_TXT_EMAIL", email);
        basePage.sendKeysByCss("LOGIN_TXT_PASSWORD", password);
        basePage.clickByCss("LOGIN_BTN");
        basePage.untilJqueryIsDone(50L);
    }


    @Then("I check error messages")
    public void iCheckErrorMessages() {
        loginPage.iCheckErrorMessages();
    }


    @When("login with email {string} and password {string}")
    public void loginWithEmailAndPassword(String email, String password) {
        loginPage.loginWithEmailAndPassword(email, password);
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
