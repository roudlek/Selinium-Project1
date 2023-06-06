package com.sqli.testauto.cookieclicker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

        wait.until(ExpectedConditions.elementToBeClickable(By.id("bigCookie")));



        for (int i = 0; i < 5000; i++) {
            for(int j = 0;j <100; j++ ){
                driver.findElement(By.id("bigCookie")).click();
            }
        }
    }
}
