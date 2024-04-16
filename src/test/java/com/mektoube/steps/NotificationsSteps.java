package com.mektoube.steps;

import com.mektoube.pages.NotificationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NotificationsSteps {
    NotificationPage notificationPage = new NotificationPage();

    @And("The user go to Notifications menu")
    public void theUserGoToNotificationsMenu() {
        notificationPage.theUserGoToNotificationsMenu();
    }

    @When("click on the filter icon")
    public void clickOnTheFilterIcon() {
        notificationPage.clickOnTheFilterIcon();
    }

    @When("click {string} button")
    public void clickButton(String optionFilter) {
        notificationPage.clickButton(optionFilter);
    }

    @Then("The notifications page shows the {string} of the filter that the user selected before")
    public void theNotificationsPageShowsTheOfTheFilterThatTheUserSelectedBefore(String results) {
        notificationPage.theNotificationsPageShowsTheOfTheFilterThatTheUserSelectedBefore(results);
    }

    @When("login successfully with the username {string} and password {string}")
    public void loginSuccessfullyWithTheUsernameAndPassword(String username, String password) {
        notificationPage.iLoginSuccessfullyWithTheUsernameAndPassword(username, password);
    }

/*Android*/
    @When("I click on the {string}")
    public void iClickOnThe(String btn) {
        notificationPage.iClickOnThe(btn);
    }

    @When("I click {string} button")
    public void iClickButton(String btn) {
        notificationPage.iClickButton(btn);
    }

    @Then("The notifications page shows the {string} of the filter")
    public void theNotificationsPageShowsTheOfTheFilter(String icon) {
        notificationPage.theNotificationsPageShowsTheOfTheFilter(icon);
    }

    @And("should see modal Payment")
    public void shouldSeeModalPayment() {
        notificationPage.shouldSeeModalPayment();
    }

    @And("check banners to subscribe is display in Notif")
    public void checkBannersToSubscribeIsDisplayInNotif() {
        notificationPage.checkBannersToSubscribeIsDisplayInNotif();
    }

    @When("choose message function at noti visit of {string}")
    public void chooseMessageFunctionAtNotiVisitOf(String name) {
        notificationPage.chooseMessageFunctionAtNotiVisitOf(name);
    }

}
