package seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours {
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://demo.guru99.com/test/newtours/");
        WebElement register= driver.findElement(By.linkText("REGISTER"));  /** Finding WebElement using  LinkText **/
       // WebElement register= driver.findElement(By.partialLinkText("REG"));  /** Finding WebElement using Partial LinkText **/
        register.click();
        WebElement fName= driver.findElement(By.name("firstName"));
        fName.sendKeys("Priya");
        WebElement lName= driver.findElement(By.name("lastName"));
        lName.sendKeys("Gopal");
        WebElement phoneNumber= driver.findElement(By.name("phone"));
        phoneNumber.sendKeys("9120356214");
        WebElement email= driver.findElement(By.id("userName"));
        email.sendKeys("abc9090@gmail.com");
        WebElement address= driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
        address.sendKeys("House");
        WebElement city= driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
        city.sendKeys("Thiruvalla");
        WebElement state= driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
        state.sendKeys("Kerala");
        WebElement postalCode= driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));
        postalCode.sendKeys("123456");
        WebElement userName= driver.findElement(By.cssSelector("#email"));
        userName.sendKeys("PriyaGopal");
        WebElement password= driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
        password.sendKeys("test@123");
        WebElement confirmPassword= driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));
        confirmPassword.sendKeys("test@123");
        WebElement submitButton= driver.findElement(By.name("submit"));
        submitButton.click();
      //  driver.close();



    }

}
