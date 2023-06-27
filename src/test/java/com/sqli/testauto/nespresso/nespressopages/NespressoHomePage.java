package com.sqli.testauto.nespresso.nespressopages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NespressoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//a[contains(@class,'AccessibleLink HeaderNavigationBarItem__anchor') and contains(@href,'/capsules')]")
    WebElement capsulesNavigationMenuLink;
    @FindBy(xpath = "//a[contains(@class,'AccessibleLink HeaderNavigationBarItem__anchor') and contains(@href,'/machines')]")
    WebElement machinesNavigationMenuLink;
    @FindBy(xpath = "//a[@class='AccessibleLink HeaderNavigationBarDropdown__medium-link' and contains(@href,'/original')]")
    WebElement orderLink;
    @FindBy(id = "_evidon-banner-acceptbutton")
    WebElement cookieAcceptButtonFR;
    @FindBy(id="_evidon-accept-button")
    WebElement cookieAcceptButtonUK;
    public NespressoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
    public void acceptCookie(){
        try {
            WebElement CookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButtonFR));
            CookieButton.click();
        } catch (TimeoutException e) {
            System.out.println("Timeout occurred while waiting for the cookie accept button.");
        }
    }

    public void hoverOverNavigationMenuLink(WebElement webElement){
        try{
            Actions action = new Actions(driver);
            action.moveToElement(webElement).perform();
        }
        catch (NoSuchElementException e){
            wait.until(ExpectedConditions.elementToBeClickable((webElement)));
            Actions action = new Actions(driver);
            action.moveToElement(webElement).perform();
        }
    }

    public void clickOnLink(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(orderLink)).click();
    }
    public void goToCapsulesPage(){
        hoverOverNavigationMenuLink(capsulesNavigationMenuLink);
        clickOnLink(orderLink);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Capsules')]")));
        driver.findElement(By.xpath("//title[contains(text(),'Capsules')]"));
    }

    public void goToMachinesPage() {
        hoverOverNavigationMenuLink(machinesNavigationMenuLink);
        clickOnLink(orderLink);
    }
    public void reinitializeSession() {
        // Clear cookies
        driver.manage().deleteAllCookies();

        // Refresh the page
        driver.navigate().refresh();
    }
}
