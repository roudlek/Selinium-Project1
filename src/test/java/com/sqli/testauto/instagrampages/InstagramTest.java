package com.sqli.testauto.instagrampages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InstagramTest {
    private WebDriver driver;
    private ChromeOptions option;
    private InstagramHomePage instagramHomePage;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        instagramHomePage = new InstagramHomePage(driver);

    }

    @Test
    public void uploadAnImageToInstagram(){
        driver.get("https://www.instagram.com/");

    }
}
