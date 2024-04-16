package com.mektoube.steps;

import com.mektoube.pages.BasePage;
import com.mektoube.pages.HomePage;
import com.mektoube.pages.RegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomePageSteps {
//    RegistrationSteps registrationSteps = new RegistrationSteps();
//    DiscoveryPageSteps discoveryPageSteps = new DiscoveryPageSteps();
//    BasePageSteps basePageSteps = new BasePageSteps();
    RegistrationPage registrationPage = new RegistrationPage();
    BasePage    basePage = new BasePage();

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() {
        new HomePage().iAmOnTheHomePage();
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton()  {
        new HomePage().iClickOnLoginButton();
    }

    @And("Change environment to{string}")
    public void changeEnvironmentTo(String env) {
        new HomePage().changeEnvironmentTo(env);
    }

    @When("Create account with email {string} username {string} pass {string} success and go to discovery")
    public void createAccountWithEmailUsernamePassSuccessAndGoToDiscovery(String email,String username, String pass) {
        registrationPage.createAccountWithEmailUsernamePassSuccessAndGoToDiscovery(email,username,pass);
    }

    @And("show message error after validate with invalid data on {string}")
    public void showMessageErrorAfterValidateWithInvalidDataOn(String element) {
        basePage.showMessageErrorAfterValidateWithInvalidDataOn(element);
    }
}
