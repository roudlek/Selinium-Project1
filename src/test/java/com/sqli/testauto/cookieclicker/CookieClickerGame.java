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

        wait.until(ExpectedConditions.elementToBeClickable(By.id("textareaPrompt"))).sendKeys("Mi4wNTJ8fDE2ODU5MTg1NjE2MzU7MTY4NTkxODU2MTYzNTsxNjg2MTc4MzQyMDE3O0Rpc2NvIENsaWNrO25meHJhOzAsMSwwLDAsMCwwLDB8MTExMTExMDExMDAxMDExMDAxMDEwMTEwMDAxfDEyMjg1NjMuOTMxMTM5NjMyOzEwNjMwMTc5NC45MzEyNTA3NDs1MTAxOzQ7MjE0MzYuNTg3NTAxOTk5OTczOzk7MDswOzA7MDswOzA7MDswOzA7NDswOzA7MDswOzA7MDs7MDswOzA7MDswOzA7MDstMTstMTstMTstMTstMTswOzA7MDswOzc1OzA7MDstMTstMTsxNjg1OTE4NTYxNjM1OzA7MDs7NDE7MDswOzc5OTU5LjIyMTA1NjAwMDAxOzUwOzA7MDt8NzgsNzgsNTA0MzE2MywwLCwwLDc4OzU4LDU4LDEwNzg0NjU3LDAsLDAsNTg7NDAsNDAsMTA1NzI0OTAsMCwsMCw0MDszMCwzMCwyNDM4OTY5NiwwLCwwLDMwOzIwLDIwLDQwMzQ3Njg5LDAsLDAsMjA7NSw1LDE0MjY0MjQ0LDAsLDAsNTsxLDEsNjY5MDkyLDAsLDAsMTswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7MCwwLDAsMCwsMCwwOzAsMCwwLDAsLDAsMDswLDAsMCwwLCwwLDA7fDExMTExMTExMTEwMDAwMTExMTExMTExMTExMTExMTAwMTExMTExMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTExMDExMTAxMDEwMTAxMDEwMDAwMDAwMDAxMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDExMTExMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDExMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAxMDAwMDAwMDAwMTAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAxMDEwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDB8MTExMTEwMDAwMDAwMDAwMDExMTExMDAwMDAwMDAwMTAwMDExMTAwMDExMDEwMDEwMDEwMDAwMDAwMDAwMDAwMDAwMDEwMDAxMTAxMDEwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMTAwMDAxMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMHx8%21END%21");
        driver.findElement(By.id("promptOption0")).click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='close menuClose' and contains(text(),'x')]")));
        }
        catch (Exception e){
            driver.findElement(By.xpath("//div[@class='close menuClose' and contains(text(),'x')]")).click();
        }

//        wait.until(ExpectedConditions.elementToBeClickable(By.id("bigCookie")));

        List<WebElement> upgrades = driver.findElements(By.xpath("//div[contains(@id,'upgrade')]"));
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@id,'product')]"));
        for (int i = 0; i < 5000; i++) {
            for (int j = 0;j < 100;j++){
                driver.findElement(By.id("bigCookie")).click();
            }
            try {
                for(int x = 0; x < upgrades.size(); x++){
                    upgrades.get(x).click();
                }
            }
            catch (Exception e){
                for(int x = 0; x < upgrades.size(); x++){
                    upgrades.get(x).click();
                }
            }

            try {
                for(int x = 0; x < products.size(); x++){
                    products.get(x).click();
                }
            }
            catch (Exception e){
                for(int x = 0; x < products.size(); x++){
                    products.get(x).click();
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
