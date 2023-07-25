package nespressoPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsefullFonctions {

    private static WebDriver driver;
    private static WebDriverWait wait;
    public UsefullFonctions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }
    public static String capitalizeEachWordInString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static void WaitForTextToBeChanged(final WebElement element) {
        try {
            String oldValueOfCTextOfWebElement = element.getText();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, oldValueOfCTextOfWebElement)));
        } catch (Exception e) {
            System.out.println("Text of element didn't change");
        }
    }
    public static String getInnerElementText(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].innerText", element);
    }
}
