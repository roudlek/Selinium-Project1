package com.sqli.testauto.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NespressoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//a[contains(@class,'AccessibleLink HeaderNavigationBarItem__anchor') and contains(@href,'/en/order/capsules')]")
    WebElement navigationMenuLink;
    @FindBy(xpath = "//a[@class='AccessibleLink HeaderNavigationBarDropdown__medium-link' and contains(@href,'capsules/original')]")
    WebElement orderLink;

    @FindBy(id = "_evidon-banner-acceptbutton")
    WebElement cookieAcceptButton;

    public void AcceptCookieNespressoFR(){
        wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
    }
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
