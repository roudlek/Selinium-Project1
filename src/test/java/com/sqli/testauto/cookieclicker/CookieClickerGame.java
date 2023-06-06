package com.sqli.testauto.cookieclicker;

import com.sqli.testauto.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespressopages.NesspressoProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class CookieClickerGame {
    private WebDriver driver;
    private ChromeOptions option;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp(){


        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        cookieHandler = new CookieHandler(driver);
        driver.manage().window().maximize();
        driver.get("https://orteil.dashnet.org/cookieclicker/");

    }
    @Test
    public void spamClicks() {
        this.wait = new WebDriverWait(driver, 40);
        WebElement englishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("langSelect-EN")));
        englishButton.click();

        this.wait = new WebDriverWait(driver, 0);
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("bigCookie")));
        WebElement upgrade0 = wait.until(ExpectedConditions.elementToBeClickable(By.id("product0")));
        WebElement upgrade1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("product1")));
        WebElement upgrade2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("product2")));
        WebElement upgrade3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("product3")));
        WebElement upgrade4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("product4")));

        for (int i = 0; i < 5000; i++) {
            for(int j = 0;j <500; j++ ){
                cookieButton.click();
            }
            if(upgrade0 != null){
                upgrade0.click();
            }
            if(upgrade1 != null){
                upgrade1.click();
            }
            if(upgrade2 != null){
                upgrade2.click();
            }
            if(upgrade3 != null){
                upgrade3.click();
            }
            if(upgrade4 != null){
                upgrade4.click();
            }
        }
    }
}
