package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoWebshop {

    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();                /** To launch the browser **/
        driver.manage().window().maximize();        /** To maximize the browser **/
        driver.manage().deleteAllCookies();         /** to delete the cookies **/
        driver.get("https://demowebshop.tricentis.com/");
        WebElement loginMenu= driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement emailField= driver.findElement(By.name("Email"));
        emailField.sendKeys("krishnatest123@gmail.com");
        WebElement passwordField= driver.findElement(By.name("Password"));
        passwordField.sendKeys("test@123");
      // WebElement loginButton=driver.findElement(By.className("button-1 login-button"));
        WebElement loginButton=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
        loginButton.click();
    }
}
