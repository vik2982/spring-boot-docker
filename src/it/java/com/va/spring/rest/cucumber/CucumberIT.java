package com.va.spring.rest.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature",
    plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"})
public class CucumberIT {


}
