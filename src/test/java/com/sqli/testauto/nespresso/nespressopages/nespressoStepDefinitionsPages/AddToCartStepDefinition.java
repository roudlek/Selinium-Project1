package com.sqli.testauto.nespresso.nespressopages.nespressoStepDefinitionsPages;

import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
public class AddToCartStepDefinition {
    WebDriver driver;

    @Before()
    public void BeforeEachScenarioRunThis(){

    }
    @Given("^user is in home page$")
    public void user_is_in_home_page() throws Throwable {
        throw new PendingException();
    }

    @When("^user typed valid quantity (.+)$")
    public void user_typed_valid_quantity(String quantity) throws Throwable {
        throw new PendingException();
    }

    @Then("^compare operation happens and success message$")
    public void compare_operation_happens_and_success_message() throws Throwable {
        throw new PendingException();
    }

    @And("^user navigates to products page$")
    public void user_navigates_to_products_page() throws Throwable {
        throw new PendingException();
    }

    @And("^user clicked on add to cart button of specified product$")
    public void user_clicked_on_add_to_cart_button_of_specified_product() throws Throwable {
        throw new PendingException();
    }

    @And("^clicked on ok button$")
    public void clicked_on_ok_button() throws Throwable {
        throw new PendingException();
    }

    @And("^opened filled cart$")
    public void opened_filled_cart() throws Throwable {
        throw new PendingException();
    }
}