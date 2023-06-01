package com.sqli.testauto.scrapingTests;

import com.sqli.testauto.nespressopages.NespressoHomePage;
import com.sqli.testauto.nespressopages.NesspressoProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScrapTest {
    private WebDriver driver;
    private ChromeOptions option;

    //    private WebDriver.Options options;
//    Cookie cookie;
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-blink-features=AutomationControlled");
//
        option.addArguments("--disable-gpu");
        option.addArguments("--disable-dev-shm-usage");
        option.addArguments("--no-sandbox");
        option.addArguments("--headless"); // Run in headless mode
        option.setPageLoadStrategy(PageLoadStrategy.NONE);
//        options.addCookie();
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        cookieHandler = new CookieHandler(driver);

//        driver.manage().window().maximize();
//        driver.get("https://www.avito.ma/");
//
//        for (int i = 0; i < 10; i++) {
//            driver.get("https://www.avito.ma/");
//            System.out.println(driver.getTitle());
//        }
    }

    @Test
    public void startTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            final int index = i;
            executorService.execute(new Runnable() {

                public void run() {
                    // Execute JavaScript code to send a request
                    ((JavascriptExecutor) driver).executeScript("var xhr = new XMLHttpRequest(); xhr.open('GET', 'https://www.example.com/api/request" +
                            index + "', true); xhr.send();");

                    // Get the title of the page
                    String pageTitle = driver.getTitle();
                    System.out.println("Title of request " + index + ": " + pageTitle);

                    // Count down the latch
                    latch.countDown();
                }
            });
        }

        // Wait for all requests to complete
        latch.await();

        // Shutdown the executor service
        executorService.shutdown();
//        option.addArguments("--headless"); // Run in headless mode

//        for (int i = 0; i < 5; i++) {
            // Create a new ChromeDriver instance with the configured options

//        }

            // Quit the driver
//        driver.quit();
        }
    }


