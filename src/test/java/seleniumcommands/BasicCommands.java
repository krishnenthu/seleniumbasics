package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicCommands {

    public static void main(String[] args) {
        WebDriver driver;
       /**
         String driverPath=System.getProperty("user.dir");
         System.setProperty("webdriver.chrome.driver","C:\\Users\\KRISHNENTHU\\IdeaProjects\\seleniumbasics\\src\\test\\resources\\driverfiles");
         System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles");
        **/

        /** Launching Chrome Browser **/

        driver = new ChromeDriver();      //To launch the browser
        driver.manage().window().maximize();        // To maximize the browser
        driver.get("https://www.obsqurazone.com");
        //driver.close();                             //To close the browser

        /** Launch Edge Browser **/

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.obsqurazone.com");

        /** Launch Firefox Browser **/

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.obsqurazone.com");

    }
}
