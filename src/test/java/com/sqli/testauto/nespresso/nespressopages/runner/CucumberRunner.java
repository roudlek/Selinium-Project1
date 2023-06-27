package com.sqli.testauto.nespresso.nespressopages.runner;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


//@RunWith(Cucumber.class)
//@CucumberOptions(features = "Features/ ",tags ="@Ready" ,glue = "nespressoStepDefinitionsPages", plugin = {"pretty", "html:target/htmlreports.html","json:target/htmlreports.json"})
public class CucumberRunner {
    @BeforeClass
    public void beforeClass(){

    }
    @AfterTest
    public void afterClass(){

    }
}
