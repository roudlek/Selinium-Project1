package com.sqli.testauto.instagrampages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InstagramHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//div[@class and @dir and contains(text(),'Create')]")
    WebElement createButton;
    @FindBy(xpath = "//button[@type='button' and @class and contains(text(),'Select from computer')]")
    WebElement selectFromComputerButton;


    public InstagramHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
        PageFactory.initElements(driver, this);
    }

    public void clickOnCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void clickOnSelectFromComputerButton() {
        wait.until(ExpectedConditions.elementToBeClickable(selectFromComputerButton)).click();
    }
}
