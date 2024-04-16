package com.mektoube;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/Priority", plugin = { "json:target/TestSuiteRunner.json",
        "pretty" }, glue = { "com.mektoube.base", "com.mektoube.steps" })
public class TestSuiteRunner {
}