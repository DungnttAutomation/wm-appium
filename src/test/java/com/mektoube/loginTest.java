package com.mektoube;

import com.mektoube.pages.BasePage;
import com.mektoube.pages.LoginPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;
//features = "src/test/resources/features/Priority",

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/mektoube/tests", plugin = { "json:target/loginTest.json",
        "pretty" }, glue = { "com.mektoube.base", "com.mektoube.pages" })
public class loginTest {
}
