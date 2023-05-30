package com.sqli.testauto.handlers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookieHandler {
    private WebDriver driver;
    public CookieHandler(WebDriver driver){
        this.driver = driver;
    }
    public void acceptCookies(WebElement acceptCookieButton, int timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try {
            WebElement CookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
            CookieButton.click();
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while waiting for the cookie accept button.");
        } catch (NoSuchElementException e) {
            System.out.println("Cookie accept button not found in the DOM.");
        } catch (Exception e) {
            System.out.println("An unexpected exception occurred: " + e.getMessage());
        }
    }
}
