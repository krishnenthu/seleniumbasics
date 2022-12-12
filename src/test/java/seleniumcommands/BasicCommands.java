package seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicCommands {

    public static void main(String[] args) {
        WebDriver driver;
        /**  setting the path explicitly
         System.setProperty("webdriver.chrome.driver","C:\\Users\\KRISHNENTHU\\IdeaProjects\\seleniumbasics\\src\\test\\resources\\driverfiles\\chromedriver.exe");
         String driverPath=System.getProperty("user.dir");
         System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles\\chromedriver.exe");
         **/

        /** Launching Chrome Browser **/
        // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driverfiles\\chromedriver.exe");
        driver = new ChromeDriver();                /** To launch the browser **/
        driver.manage().window().maximize();        /** To maximize the browser **/
        driver.manage().deleteAllCookies();         /** to delete the cookies **/
        driver.get("https://selenium.obsqurazone.com/index.php");
        String currentUrl = driver.getCurrentUrl();   /** to get the url **/
        System.out.println(currentUrl);
        String title= driver.getTitle();             /** to get the title **/
        System.out.println(title);
        String sourceCode= driver.getPageSource();   /** to get the source code **/
        System.out.println(sourceCode);
        driver.close();                          /** To close the browser **/

//        /** Launch Edge Browser **/
//        driver = new EdgeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://selenium.obsqurazone.com/index.php");
//        driver.close();

//        /** Launch Firefox Browser **/
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.get("https://selenium.obsqurazone.com/index.php");
//         driver.close();

    }
}
