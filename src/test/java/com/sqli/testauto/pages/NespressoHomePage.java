package com.sqli.testauto.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class NespressoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//a[contains(@class,'AccessibleLink HeaderNavigationBarItem__anchor') and contains(@href,'/en/order/capsules')]")
    WebElement navigationMenuLink;
    @FindBy(xpath = "//a[@class='AccessibleLink HeaderNavigationBarDropdown__medium-link' and contains(@href,'capsules/original')]")
    WebElement orderLink;
    @FindBy(id = "_evidon-banner-acceptbutton")
    WebElement cookieAcceptButtonFR;

    @FindBy(id="_evidon-accept-button")
    WebElement cookieAcceptButtonUK;


    public NespressoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
        PageFactory.initElements(driver, this);
    }

    public void hoverOverNavigationMenuLink(){
        wait.until(ExpectedConditions.elementToBeClickable((navigationMenuLink)));
        Actions action = new Actions(driver);
        action.moveToElement(navigationMenuLink).perform();
    }

    public void clickOnLink(){
        wait.until(ExpectedConditions.elementToBeClickable(orderLink)).click();
    }
}
