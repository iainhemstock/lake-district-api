package com.iainhemstock.lakedistrictapi.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = "pretty",
    features = "src/test/features/resources/features")
public class CucumberFeatureTestRunner {

}
