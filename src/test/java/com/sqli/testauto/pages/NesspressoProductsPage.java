package com.sqli.testauto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NesspressoProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//button[contains(@class,'AddToBagButton')]")
    WebElement addToCartButton;

    @FindBy(id = "ta-quantity-selector__custom-field")
    WebElement quantitySelector;
    @FindBy(id = "ta-quantity-selector__custom-ok")
    WebElement okButton;
    @FindBy(xpath = "//button[contains(@class,'MiniBasketButton--not-empty')]")
    WebElement filledCart;
    @FindBy(className = "MiniBasketItemCategory__item-count']")
    WebElement cartCountElement;
    @FindBy(id = "ta-mini-basket__checkout")
    WebElement proceedToCheckoutButton;


    //for assert
    @FindBy(xpath = "//div[@class='AddToBagButtonSmall__quantity'][1]")
    WebElement numberOfItemsInButtonDiv;
    @FindBy(xpath = "//span[@class='MiniBasketItemPriceAndName__price-calc'][1]")
    WebElement numberOfItemsInCartSpan;

    public NesspressoProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
        PageFactory.initElements(driver, this);
    }

    public void clickOnAddToCartButton(){
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        }

        public void setQuantity(String quantity){
            wait.until(ExpectedConditions.elementToBeClickable(quantitySelector)).sendKeys(quantity);
        }

        public void clickOnOKButton(){
            okButton.click();
        }

        public void clickOnFilledCart(){
            wait.until(ExpectedConditions.elementToBeClickable(filledCart)).click();
        }

        public void verifyItemCount(){
            wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsInButtonDiv));
            wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsInCartSpan));

            String stringNumberOfItemsInButtonDiv = numberOfItemsInButtonDiv.getText();
            String stringNumberOfItemsInCartSpan = numberOfItemsInCartSpan.getText();

            Assert.assertTrue(stringNumberOfItemsInCartSpan.contains(stringNumberOfItemsInButtonDiv));
            System.out.println("stringNumberOfItemsInButtonDiv is " + stringNumberOfItemsInButtonDiv);
            System.out.println("stringNumberOfItemsInCartSpan is " + stringNumberOfItemsInCartSpan);

        }
//        public void getCartItemCount() {
//            clickOnFilledCart();
//            //find span text
//            String itemCountText = wait.until(ExpectedConditions.elementToBeClickable(cartCountElement)).getText();
//            String itemCountValue = itemCountText.replaceAll("[^0-9]", "");
//            int itemCount = Integer.parseInt(itemCountValue);
//            System.out.println(itemCount);
//        }

        public void proceedToCheckout(){
            proceedToCheckoutButton.click();
        }
}
