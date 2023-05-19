package com.sqli.testauto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    WebDriver driver ;
    public static final String path = "/home/elidrissiabdelaziz/Desktop/chromedriver_linux64/chromedriver";

    public static void main(String[] args) {

    }
    // executed once before and after all the test methods in a test class.
    //initializing resources, establishing database connections, or starting a web server.
    @BeforeClass
    public void setUpDriver(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver(option);
        // navigate using, method is handled by the browser as a fragment identifier and JavaScript variables are retained from the previous URL.:
        driver.get("https://google.com/");
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("y");

        // Simulate search button click
        searchInput.submit();
        // Validate search results page
        WebElement searchResults = driver.findElement(By.id("search"));
        Assert.assertTrue(searchResults.isDisplayed(), "Search results not displayed");

//        // navigate using,method is handled by the browser as a address/location/URL bar input and JavaScript variables are not retained from the previous URL.:
//        driver.navigate().to("https://instagram.com/");
//        //refresh instagram page:
//        driver.navigate().refresh();
//
//
//        //Use the ChromeDriver to locate the search input element and enter a search query.
//        driver.findElement(By.name("input"));



    }

    @Test(priority = 0)
    public void gptExercice(){

    }

    @Test(priority = 1)
    public void getTitle(){
        String title = driver.getTitle();
        System.out.println(driver.getTitle());
        Assert.assertEquals(title,"Google");
    }
    @Test(priority = 2)
    public void maximizeWindowThenMinimize() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.manage().window().minimize();
    }
//    @Test
//    public void sayHello(){
//        System.out.println("hello world");
//    }
    @Test(priority = 3)
    public void navigateTo(){
        try {
            Thread.sleep(7000);
            driver.navigate().to("https://google.com");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    @AfterClass
    public void exit(){
//        driver.quit();
    }


}
