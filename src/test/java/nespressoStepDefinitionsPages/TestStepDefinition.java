package nespressoStepDefinitionsPages;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class TestStepDefinition {
    @Before("@simpleTestsHooks")
    public void beforeOfSimpleTest() {
        System.out.println("from before of @beforeOfSimpleTest");
    }
    @After("@simpleTestsHooks")
    public void afterMethod(){
        System.out.println("in after method of simple test");
    }
    @Given("User enters coffee shop")
    public void userEntersCoffeeShop() {
        System.out.println("client enters");
    }

    @When("User asks for coffee")
    public void userAsksForCoffee() {
        System.out.println("i want espresso please");
    }

    @Then("We gave him coffe")
    public void weGaveHimCoffe() throws Exception {
        System.out.println("here is your order");
//        Assert.fail();
//        throw new Exception("");
    }
}
