package com.mektoube.steps;

import com.mektoube.pages.PaymentPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PaymentPageSteps {
    PaymentPage paymentPage = new PaymentPage();
    @Then("choose Pass {int} mois and check total price in valider button")
    public void choosePassMoisAndCheckTotalPriceInValiderButton(int numberMois) { paymentPage.choosePassMoisAndCheckTotalPriceInValiderButton(numberMois);
    }

    @Then("Payment by paybox screen is show full")
    public void paymentByPayboxScreenIsShowFull() { paymentPage.paymentByPayboxScreenIsShowFull();
    }

    @When("modal Abonnement {int} mois is show")
    public void modalAbonnementMoisIsShow(int pass) { paymentPage.modalAbonnementMoisIsShow(pass);
    }

    @Then("Payment if google tester account is logined on PlayStore")
    public void paymentIfGoogleTesterAccountIsLoginedOnPlayStore() { paymentPage.paymentIfGoogleTesterAccountIsLoginedOnPlayStore();
    }

    @Then("Check button in Informations de votre abonnement modal and action")
    public void checkButtonInInformationsDeVotreAbonnementModalAndAction() { paymentPage.checkButtonInInformationsDeVotreAbonnementModalAndAction();
    }

    @Given("I need an Android phone")
    public void iNeedAnAndroidPhone() {
        assertThat(paymentPage.iNeedAnAndroidPhone(), is(equalTo(true)));
    }
}

