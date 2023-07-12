

//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    WebDriver driver;
    @BeforeMethod
    public void setMobileDeviceCapabilities() throws MalformedURLException {
//        File app = new File("");
//        desiredCapabilities.setCapability("platform","Android");
//         desiredCapabilities.setCapability("deviceName", "device");
//         desiredCapabilities.setCapability("fullReset", "false");
//         desiredCapabilities.setCapability("appActivity", "myAppActivity");
//
//         // Connect to the Appium server
//        driver = new AndroidDriver<>(new URL(""), desiredCapabilities);
////         URL appiumServerUrl = new URL("http://localhost:4723/wd/hub");
////         AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(appiumServerUrl, desiredCapabilities);
     }
}
