package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumBasics {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("Edge")) {
            driver = new EdgeDriver();
        } else if (browser.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            //  throw new RuntimeException("Invalid browser");
            try {
                throw new Exception("Invalid Browser");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @BeforeMethod
    public void setUp() {
        testInitialize("Chrome");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void TC_001_verifyObsquraTitle() {
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Obsqura Testing";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }

    @Test
    public void TC_002_simpleInputForm() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement messageField = driver.findElement(By.id("single-input-field"));
        messageField.sendKeys("Test message");
        WebElement messageButton = driver.findElement(By.id("button-one"));
        messageButton.click();
        WebElement text = driver.findElement(By.id("message-one"));
        String actualText = text.getText();
        String expectedText = "Your Message : Test message";
        Assert.assertEquals(actualText, expectedText, "Result matched");
    }

    @Test
    public void TC_003_verifyTotal() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement valueA = driver.findElement(By.id("value-a"));
        valueA.sendKeys("10");
        WebElement valueB = driver.findElement(By.id("value-b"));
        valueB.sendKeys("40");
        WebElement totalButton = driver.findElement(By.id("button-two"));
        totalButton.click();
        WebElement totalText = driver.findElement(By.id("message-two"));
        String actualTotal = totalText.getText();
        String expectedTotal="Total A + B : 50";
        Assert.assertEquals(actualTotal,expectedTotal,"Result matched");
    }
}
