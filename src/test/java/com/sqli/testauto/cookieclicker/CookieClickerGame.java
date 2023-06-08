package com.sqli.testauto.cookieclicker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("langSelect-EN"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("bigCookie")));

//        driver.findElement(By.id("bigCookie")).click();
//        driver.findElement(By.id("bigCookie")).click();
//        driver.findElement(By.id("bigCookie")).click();
//        driver.findElement(By.id("bigCookie")).click();
//        driver.findElement(By.id("bigCookie")).click();
//        driver.findElement(By.id("bigCookie")).click();

//        driver.navigate().refresh();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='prefsButton' and @class='panelButton']"))).click();
        }
        catch (Exception E){
            driver.findElement(By.xpath("//div[@id='prefsButton' and @class='panelButton']")).click();
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='option smallFancyButton' and contains(text(),'Import save')]"))).click();
        }
        catch (Exception e){
            driver.findElement(By.xpath("//a[@class='option smallFancyButton' and contains(text(),'Import save')]")).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.id("textareaPrompt"))).sendKeys("Mi4wNTJ8fDE2ODU5MTg1NjE2MzU7MTY4NTkxODU2MTYzNTsxNjg2MjYxNTAzMTI4O0Rpc2NvIENsaWNrO25meHJhOzAsMSwwLDAsMCwwLDB8MTExMTExMDExMDAxMDExMDAxMDEwMTEwMDAxfDc1MTEyMi4xOTc3OTg5NTg2OzI5ODUxMTk3Ni4xOTc2NzA0OzUzMDM7NDsyODM5MDQuMzE2ODE0MTU5OTsxMjswOzA7MDswOzA7MDswOzA7MDs0OzA7MDswOzA7MDswOzswOzA7MDswOzA7MDswOy0xOy0xOy0xOy0xOy0xOzA7MDswOzA7NzU7MDswOy0xOy0xOzE2ODU5MTg1NjE2MzU7MDswOzs0MTswOzA7MTg0ODc4Ljk1NDI5NjAwMDA0OzUwOzA7MDt8NzgsNzgsMTgyNzE0OTYsMCwsMCw3ODs2MCw2MCwyNjQzMzMzNCwwLCwwLDYwOzQyLDQyLDE5Mjk3ODcxLDAsLDAsNDI7MzAsMzAsNTQ5MDU2MjQsMCwsMCwzMDsyNCwyNCw5MjkwMzE5MCwwLCwwLDI0OzE0LDE0LDY5MDMzNTYzLDAsLDAsMTQ7MywzLDE3MTczNjYwLDAsLDAsMzswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7fDExMTExMTExMTEwMDAwMTExMTExMTExMTExMTExMTAwMTExMTExMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTExMDExMTAxMDEwMTAxMDEwMTAwMDAwMDAxMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDExMTExMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDExMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTExMTAwMDAwMDAwMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAxMDEwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDB8MTExMTEwMDAwMDAwMDAwMDExMTExMDAwMDAwMDAwMTEwMDExMTAwMDExMDEwMDEwMDEwMDAwMDAwMDAwMDAwMDAwMDEwMDAxMTAxMDEwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAwMDAxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMHx8%21END%21");
        driver.findElement(By.id("promptOption0")).click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='close menuClose' and contains(text(),'x')]")));
        }
        catch (Exception e){
            driver.findElement(By.xpath("//div[@class='close menuClose' and contains(text(),'x')]")).click();
        }

//        wait.until(ExpectedConditions.elementToBeClickable(By.id("bigCookie")));

        List<WebElement> upgrades = driver.findElements(By.xpath("//div[contains(@class,'crate upgrade enabled')]"));
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'product unlocked enabled')]"));




        for (int i = 0; i < 5000; i++) {
            for (int j = 0;j < 100;j++){
                driver.findElement(By.id("bigCookie")).click();
            }
            for (int x = 0; x < upgrades.size(); x++) {
                try {
                    upgrades.get(x).click();
                } catch (StaleElementReferenceException e) {
                    // Refresh the element list
                    upgrades = driver.findElements(By.xpath("//div[contains(@id,'upgrade')]"));
                    x--; // Decrement x to retry clicking the same index
                }
            }

            for (int x = 0; x < products.size(); x++) {
                try {
                    products.get(x).click();
                } catch (StaleElementReferenceException e) {
                    // Refresh the element list
                    products = driver.findElements(By.xpath("//div[contains(@class,'product unlocked enabled')]"));
                    x--; // Decrement x to retry clicking the same index
                }
            }
        }
    }

//    @AfterTest
    public void saveAndClose(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='prefsButton' and @class='panelButton']"))).click();
        }
        catch (Exception E){
            driver.findElement(By.xpath("//div[@id='prefsButton' and @class='panelButton']")).click();
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='option smallFancyButton' and contains(text(),'Save to file')]"))).click();
        }
        catch (Exception e){
            driver.findElement(By.xpath("//a[@class='option smallFancyButton' and contains(text(),'Save to file')]")).click();
        }


    }
}
