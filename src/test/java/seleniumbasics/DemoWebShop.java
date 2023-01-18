package seleniumbasics;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoWebShop {

    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
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
    @Parameters({"browser", "base_url"})
    public void setUp(String browserName, String url) {
        testInitialize(browserName);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
        }
        driver.close();
    }

    @Test
    public void TC_001_verifyDemoWebShopTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }

    @Test
    public void TC_002_verifyLogin() {
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        String emailid = "krishnatestnew@gmail.com";
        WebElement username = driver.findElement(By.id("Email"));
        username.sendKeys(emailid);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("test123");
        WebElement button = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        button.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(emailid, actualEmail, "Login failed");
    }

    @Test
    public void TC_003_verifyRegistration() {
        WebElement registerlink = driver.findElement(By.xpath("//a[text()='Register']"));
        registerlink.click();
        List<WebElement> genders = driver.findElements(By.xpath("//input[@name='Gender']//following-sibling::label"));
        selectGender(genders, "Female");
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("priya");
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys("gopal");
        WebElement emailField = driver.findElement(By.id("Email"));
        String email = "priyagopaltestn@gmail.com";
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("Password"));
        passwordField.sendKeys("test123");
        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys("test123");
        WebElement register = driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(actualEmail, email, "Registration failed");
    }

    public void selectGender(List<WebElement> genders, String gen) {
        for (int i = 0; i < genders.size(); i++) {
            String gender = genders.get(i).getText();
            if (gender.equals(gen)) {
                genders.get(i).click();
            }
        }
    }

    @Test
    public void TC_004_verifyDemoWebShopTitleWithExcelHomeWork() throws IOException {
        String actualTitle = driver.getTitle();
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "HomePage");
        String expectedTitle = data.get(0).get(1);
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }

    @Test(dataProvider = "Credentials")
    public void TC_005_verifyInvalidLoginWithDataProvider(String userName, String pword) {
        WebElement login1 = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login1.click();
        //String mail = userName;
        WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
        //email.sendKeys(mail);
        email.sendKeys(userName);
        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys(pword);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement error = driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
        String actMsg = error.getText();
        String expMsg = "Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actMsg, expMsg, "error message not found");
    }

    @DataProvider(name = "Credentials")
    public Object[][] userCredentials() {
        Object[][] data = {{"priyagopaltestn@gmail.com", "test13"}, {"gopaltest@gmail.com", "test123"}, {"gopaltest@gmail.com", "test3"}};
        return data;
    }

    @Test
    public void TC_006_verifyRegistrationDataRandomGeneration() {
        WebElement reg1 = driver.findElement(By.xpath("//a[@class='ico-register']"));
        reg1.click();
        String fName = RandomDataUtility.getfName();
        String lName = RandomDataUtility.getlName();
        String email = RandomDataUtility.getRandomEmail();
        String pWord = fName + "@123";
        List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
        selectGender(gender, "M");
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(fName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(lName);
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("Password"));
        passwordField.sendKeys(pWord);
        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys(pWord);
        WebElement register = driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(actualEmail, email, "Registration failed");
    }

    @Test(dataProvider = "ValidCredentials")
    public void TC_007_verifyValidLoginWithDataProviderHomework(String userName, String pword) {
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(userName);
        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys(pword);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(actualEmail, userName, "Login failed");
    }

    @DataProvider(name = "ValidCredentials")
    public Object[][] validUserCredentials() {
        Object[][] data = {{"priyagopaltestn@gmail.com", "test123"}, {"krishnatest123@gmail.com", "test@123"}};
        return data;
    }

    @Test
    @Parameters({"username_email", "pass_password"})
    public void TC_008_verifyValidLoginWithParameterHomework(String username, String password) {
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        WebElement emailField = driver.findElement(By.xpath("//input[@id='Email']"));
        emailField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='Password']"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(actualEmail, username, "Login failed");
    }

    @Test
    public void TC_009_verifyRegistrationWithExcelAndRandomDataGeneration() throws IOException {
        WebElement registerLink = driver.findElement(By.xpath("//a[@class='ico-register']"));
        registerLink.click();
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "RegistrationPage");
        String genderData=data.get(0).get(1);
        String fName = data.get(1).get(1);
        String lName = data.get(2).get(1);
        String password = data.get(3).get(1);
        String confirmPassword = data.get(4).get(1);
        String email=RandomDataUtility.getRandomEmail();
        if (genderData.equals("Male")){
            WebElement gender=driver.findElement(By.id("gender-male"));
            gender.click();
        }else {
            WebElement gender=driver.findElement(By.id("gender-female"));
            gender.click();
        }
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(fName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(lName);
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("Password"));
        passwordField.sendKeys(password);
        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
        passwordConfirm.sendKeys(confirmPassword);
        WebElement register = driver.findElement(By.id("register-button"));
        register.click();
        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualEmail = userAccount.getText();
        Assert.assertEquals(actualEmail, email, "Registration failed");
    }
}
