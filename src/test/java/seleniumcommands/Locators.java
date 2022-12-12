package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();                /** To launch the browser **/
        driver.manage().window().maximize();        /** To maximize the browser **/
        driver.manage().deleteAllCookies();         /** to delete the cookies **/
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement messageField= driver.findElement(By.id("single-input-field"));   /** To find the web element 'input field' by using id **/
        messageField.sendKeys("Test");                                   /** To enter the text in the field **/
        WebElement messageButton=driver.findElement(By.id("button-one"));           /** To find the web element 'button' by using id **/
        messageButton.click();                                                      /** To click on the button **/
        WebElement message=driver.findElement(By.id("message-one"));                /** To find the web element 'text message' by using id **/
        String myMessage=message.getText();                                         /** To get the text **/
        System.out.println(myMessage);
        WebElement valueA= driver.findElement(By.id("value-a"));
        valueA.sendKeys("10");
        WebElement valueB= driver.findElement(By.id("value-b"));
        valueB.sendKeys("40");
        WebElement totalButton=driver.findElement(By.id("button-two"));
        totalButton.click();
        WebElement totalText= driver.findElement(By.id("message-two"));
        String myTotal=totalText.getText();
        System.out.println(myTotal);

        //  driver.close();
    }
}