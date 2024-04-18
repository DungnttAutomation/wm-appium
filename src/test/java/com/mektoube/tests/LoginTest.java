package com.mektoube.tests;

import com.mektoube.pages.BasePage;
import com.mektoube.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest {
    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    public void setUpMyAcc(String baseUrl, String email, String pass) throws InterruptedException {
        if(baseUrl.equals("https://staging.mektoube.fr")){
            basePage.navigateToUrl(baseUrl);
        }
//        loginPage.acceptAllCookies();
        loginPage.login(email,pass);
    }
    @Test(priority = 1, description = "Change PERSONAL INFORMATION ")
    public void testCase_PI_1() throws InterruptedException {
        setUpMyAcc(" ","COM_INP_DATA_EMAIL_STAGE","COM_INP_DATA_PASS_STAGE");
    }
}
