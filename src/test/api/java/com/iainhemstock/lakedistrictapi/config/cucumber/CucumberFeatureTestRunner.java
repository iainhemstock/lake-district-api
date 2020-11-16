package com.iainhemstock.lakedistrictapi.config.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = "pretty",
    features = "src/test/api/resources/features")
public class CucumberFeatureTestRunner {

}
