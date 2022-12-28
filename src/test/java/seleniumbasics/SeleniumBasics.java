package seleniumbasics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    /* @AfterMethod
      public void tearDown() {
          driver.close();
      }*/
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
        String expectedTotal = "Total A + B : 50";
        Assert.assertEquals(actualTotal, expectedTotal, "Result matched");
    }

    /*** Homework starts **/

    @Test
    public void TC_004_verifyEmptyFieldsValidation() {

        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement submitbutton = driver.findElement(By.xpath("//button[text()='Submit form']"));
        submitbutton.click();

        WebElement firstnameValidation = driver.findElement(By.xpath("//input[@id='validationCustom01']//following-sibling::div[1]"));
        String FirstnameValidationActual = firstnameValidation.getText();
        String FirstnameValidationExpected = "Please enter First name.";
        Assert.assertEquals(FirstnameValidationActual, FirstnameValidationExpected, "Firstname field validation message is not correct");

        WebElement lastnameValidation = driver.findElement(By.xpath("//input[@id='validationCustom02']//following-sibling::div[1]"));
        String LastnameValidationActual = lastnameValidation.getText();
        String LastnameValidationExpected = "Please enter Last name.";
        Assert.assertEquals(LastnameValidationActual, LastnameValidationExpected, "Lastname field validation message is not correct");

        WebElement usernameValidation = driver.findElement(By.xpath("//input[@id='validationCustomUsername']//following-sibling::div[1]"));
        String UsernameValidationActual = usernameValidation.getText();
        String UsernameValidationExpected = "Please choose a username.";
        Assert.assertEquals(UsernameValidationActual, UsernameValidationExpected, "Username field validation message is not correct");

        WebElement cityValidation = driver.findElement(By.xpath("//input[@id='validationCustom03']//following-sibling::div[1]"));
        String cityValidationActual = cityValidation.getText();
        String cityValidationExpected = "Please provide a valid city.";
        Assert.assertEquals(cityValidationActual, cityValidationExpected, "City field validation message is not correct");

        WebElement stateValidation = driver.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
        String stateValidationActual = stateValidation.getText();
        String stateValidationExpected = "Please provide a valid state.";
        Assert.assertEquals(stateValidationActual, stateValidationExpected, "State field validation message is not correct");

        WebElement zipValidation = driver.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));
        String zipValidationActual = zipValidation.getText();
        String zipValidationExpected = "Please provide a valid state.";
        Assert.assertEquals(zipValidationActual, zipValidationExpected, "zip field validation message is not correct");

        WebElement termsValidation = driver.findElement(By.xpath("//input[@id='invalidCheck']//following-sibling::div[1]"));
        String termsValidationActual = termsValidation.getText();
        String termsValidationExpected = "You must agree before submitting.";
        Assert.assertEquals(termsValidationActual, termsValidationExpected, "Terms and conditions validation message is not correct");
    }

    /**
     * Homework ends
     **/

    @Test
    public void TC_005_verifyNewsLetterSubscription() {

        driver.get("https://demowebshop.tricentis.com/");
        WebElement newsletter = driver.findElement(By.cssSelector("#newsletter-email"));
        newsletter.sendKeys("Test Value");
        WebElement subscribe = driver.findElement(By.cssSelector("#newsletter-subscribe-button"));
    }

    @Test
    public void TC_006_verifyInstantDemoRequestForm() {

        driver.get("https://phptravels.com/demo/");
        WebElement fName = driver.findElement(By.cssSelector("input[name='first_name']"));
        fName.sendKeys("priya");
        WebElement lName = driver.findElement(By.cssSelector("input[name='last_name']"));
        lName.sendKeys("Gopal");
        WebElement businessName = driver.findElement(By.cssSelector("input[name='business_name']"));
        businessName.sendKeys("ABC");
        WebElement email = driver.findElement(By.cssSelector("input[name='email']"));
        email.sendKeys("ABC@abc.com");
        WebElement firstNumber = driver.findElement(By.cssSelector("span[id='numb1']"));
        int fnumber = Integer.parseInt((String) firstNumber.getText());
        WebElement secondNumber = driver.findElement(By.cssSelector("span[id='numb2']"));
        int snumber = Integer.parseInt((String) secondNumber.getText());
        int total = fnumber + snumber;
        String totalR = String.valueOf(total);
        WebElement totalResult = driver.findElement(By.cssSelector("input[id='number']"));
        totalResult.sendKeys(totalR);
        WebElement button = driver.findElement(By.cssSelector("button[id='demo']"));
        button.click();
    }

    @Test
    public void TC_007_verifyWebElementCommands() throws InterruptedException {

        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subject = driver.findElement(By.xpath("//input[@id='subject']"));
        subject.sendKeys("Selenium");
        WebElement description = driver.findElement(By.xpath("//textarea[@id='description']"));
        description.sendKeys("Automation Testing");
        subject.clear();
        String classAttributeValue = subject.getAttribute("class");
        System.out.println(classAttributeValue);
        String tagname = subject.getTagName();
        System.out.println(tagname);
        subject.sendKeys("WebElement commands");
        WebElement submit = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        submit.click();
        Thread.sleep(10000);
        WebElement message = driver.findElement(By.xpath("//div[@id='message-one']"));
        String messageActual = message.getText();
        String messageExpected = "Form has been submitted successfully!";
        Assert.assertEquals(messageExpected, messageActual, "Invalid Message");
    }

    @Test

    public void TC_008_verifyIsDisplayed() {

        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subject = driver.findElement(By.xpath("//input[@id='subject']"));
        subject.sendKeys("Selenium");
        boolean status = subject.isDisplayed();
        System.out.println(status);
        Assert.assertTrue(status, "subject field is not displayed");
    }

    @Test

    public void TC_009_verifyIsSelected() {

        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement singleCheckbox = driver.findElement(By.id("gridCheck"));
        boolean statusBeforeClick = singleCheckbox.isSelected();
        System.out.println(statusBeforeClick);
        Assert.assertFalse(statusBeforeClick, "Checkbox is selected");
        singleCheckbox.click();
        boolean statusAfterClick = singleCheckbox.isSelected();
        System.out.println(statusAfterClick);
        Assert.assertFalse(statusAfterClick, " Checkbox is not selected");
    }

    @Test
    public void TC_010_verifyIsEnabled() {

        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        boolean status = submitButton.isEnabled();
        System.out.println(status);
        Assert.assertTrue(status, "Submit button not enabled");
        Point point = submitButton.getLocation();                          /** To get the location of the webelement **/
        System.out.println(point.x + "," + point.y);
        Dimension dimension = submitButton.getSize();                    /** To get the dimension of the webelement **/
        System.out.println(dimension);
        String backgroundColor = submitButton.getCssValue("background-color");
        System.out.println(backgroundColor);
        WebElement element = driver.findElement(By.tagName("input"));
        System.out.println(element);
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        System.out.println(elements);
        submitButton.submit();
    }

    @Test
    public void TC_011_verifyDifferenceBetweenFindElementAndFindElements() {

        driver.get("https://selenium.obsqurazone.com/radio-button-demo.php");
        //WebElement radioButton = driver.findElement(By.id("inlineRadio11"));
        //  radioButton.click();
        List<WebElement> genders = driver.findElements(By.xpath("//input[@name='student-gender']"));  /** List has both male and femaele value**/
        System.out.println(genders);
        for (int i = 0; i < genders.size(); i++) {
            String gender = genders.get(i).getAttribute("value");  /** male will be saved **/
            if (gender.equals("Male")) {
                genders.get(i).click();
            }
        }
    }

    @Test
    public void TC_012_verifyMultipleWindowHandling() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println("parent window id = " + parentWindow);
        WebElement clickLink = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickLink.click();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement emailField = driver.findElement(By.name("emailid"));
                emailField.sendKeys("priya@gmail.com");
                WebElement submitButton = driver.findElement(By.name("btnLogin"));
                submitButton.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_013_verifyMultipleWindowHandlingHomeWork1() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                String actualText = text.getText();
                String expectedText = "This is a sample page";
                Assert.assertEquals(actualText, expectedText, "Text displayed is not correct");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_013_verifyMultipleWindowHandlingHomeWork2() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement newWindowbutton = driver.findElement(By.id("windowButton"));
        newWindowbutton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (! childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                String actualText = text.getText();
                String expectedText = "This is a sample page";
                Assert.assertEquals(actualText, expectedText, "Text displayed is not correct");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test
    public void TC_013_verifyMultipleWindowHandlingHomeWork3() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement windowMessageButton = driver.findElement(By.id("messageWindowButton"));
        windowMessageButton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (! childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement text = driver.findElement(By.xpath("/html/body/text()"));
                String actualText = text.getText();
                String expectedText = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";
                Assert.assertEquals(actualText, expectedText, "Text displayed is not correct");
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);

    }
}