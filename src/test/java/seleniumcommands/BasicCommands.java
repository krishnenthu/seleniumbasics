package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicCommands {

    public static void main(String[] args) {

       /*
         String driverPath=System.getProperty("user.dir");
         System.setProperty("webdriver.chrome.driver","C:\\Users\\KRISHNENTHU\\IdeaProjects\\seleniumbasics\\src\\test\\resources\\driverfiles");
        */

        /* Launch Chrome Browser */

        WebDriver driver = new ChromeDriver();      //To launch the browser
        driver.manage().window().maximize();        // To maximize the browser
        driver.get("https://www.obsqurazone.com");
        //driver.close();                             //To close the browser

        /* Launch Edge Browser */

        WebDriver driver1= new EdgeDriver();
        driver1.manage().window().maximize();
        driver1.get("https://www.obsqurazone.com");

        /* Launch Firefox Browser */

        WebDriver driver2= new FirefoxDriver();
        driver2.manage().window().maximize();
        driver2.get("https://www.obsqurazone.com");


    }
}
