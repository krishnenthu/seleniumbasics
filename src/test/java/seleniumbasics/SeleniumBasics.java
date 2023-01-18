package seleniumbasics;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.List;

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
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("This is before Suite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("This is before Test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("This is before Class");
    }
    @BeforeMethod
    public void setUp() {
        testInitialize("Chrome");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("This is after Suite");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("This is after Test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("This is after Class");
    }

    @AfterMethod
          public void tearDown(ITestResult result) throws IOException {
           if (result.getStatus()==ITestResult.FAILURE){
               TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
               File screenshot=takesScreenshot.getScreenshotAs(OutputType.FILE);
               FileUtils.copyFile(screenshot,new File("./Screenshots/"+result.getName()+".png"));
           }
              driver.close();
            //driver.quit();

          }
    @Test
    public void TC_001_verifyObsquraTitle() {

        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Obsqura Testing1";
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
        String zipValidationExpected = "Please provide a valid zip.";
        Assert.assertEquals(zipValidationActual, zipValidationExpected, "zip field validation message is not correct");
        WebElement termsValidation = driver.findElement(By.xpath("//input[@id='invalidCheck']//following-sibling::div[1]"));
        String termsValidationActual = termsValidation.getText();
        String termsValidationExpected = "You must agree before submitting.";
        Assert.assertEquals(termsValidationActual, termsValidationExpected, "Terms and conditions validation message is not correct");
    }


    @Test
    public void TC_005_verifyEmptyFieldCityStateValidation() {

        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstnameField = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
        firstnameField.sendKeys("krishnenthu");
        WebElement lastnameField = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
        lastnameField.sendKeys("Gopal");
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
        usernameField.sendKeys("Kgopal");
        WebElement zipField = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
        zipField.sendKeys("12345");
        WebElement termsCheckBox = driver.findElement(By.xpath("//input[@id='invalidCheck']"));
        termsCheckBox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submitButton.click();
        WebElement cityValidation = driver.findElement(By.xpath("//input[@id='validationCustom03']//following-sibling::div[1]"));
        String cityValidationActual = cityValidation.getText();
        String cityValidationExpected = "Please provide a valid city.";
        Assert.assertEquals(cityValidationActual, cityValidationExpected, "City field validation message is not correct");
        WebElement stateValidation = driver.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
        String stateValidationActual = stateValidation.getText();
        String stateValidationExpected = "Please provide a valid state.";
        Assert.assertEquals(stateValidationActual, stateValidationExpected, "State field validation message is not correct");
    }

    @Test
    public void TC_006_verifySubmitForm() {
        driver.get("https://selenium.obsqurazone.com/form-submit.php");
        WebElement firstnameField = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
        firstnameField.sendKeys("krishnenthu");
        WebElement lastnameField = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
        lastnameField.sendKeys("Gopal");
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
        usernameField.sendKeys("Kgopal");
        WebElement cityField = driver.findElement(By.xpath("//input[@id='validationCustom03']"));
        cityField.sendKeys("Thiruvalla");
        WebElement stateField = driver.findElement(By.xpath("//input[@id='validationCustom04']"));
        stateField.sendKeys("Kerala");
        WebElement zipField = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
        zipField.sendKeys("12345");
        WebElement termsCheckBox = driver.findElement(By.xpath("//input[@id='invalidCheck']"));
        termsCheckBox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submitButton.click();
        WebElement successMessage = driver.findElement(By.xpath("//div[@id='message-one']"));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Form has been submitted successfully!";
        Assert.assertEquals(actualMessage, expectedMessage, "Failed to submit the form");
    }

    @Test
    public void TC_007_verifyNewsLetterSubscription() {

        driver.get("https://demowebshop.tricentis.com/");
        WebElement newsletter = driver.findElement(By.cssSelector("#newsletter-email"));
        newsletter.sendKeys("Test Value");
        WebElement subscribe = driver.findElement(By.cssSelector("#newsletter-subscribe-button"));
    }

    @Test
    public void TC_008_verifyInstantDemoRequestForm() {
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
    public void TC_009_verifyQuitAndClose() {
        driver.get("https://demo.guru99.com/popup.php");
        WebElement clickHereLink = driver.findElement(By.xpath("//a[text()='Click Here']"));
        clickHereLink.click();
    }

    @Test
    public void TC_010_verifyNavigateTo() {
        // driver.get("https://demowebshop.tricentis.com/");
        driver.navigate().to("https://demowebshop.tricentis.com/");
    }

    @Test
    public void TC_011_verifyRefresh() {
        driver.get("https://demowebshop.tricentis.com/");
        driver.navigate().refresh();
    }

    @Test
    public void TC_012_verifyForwardAndBackward() throws InterruptedException {
        driver.get("https://demowebshop.tricentis.com/");
        WebElement logInLink = driver.findElement(By.xpath("//a[text()='Log in']"));
        logInLink.click();
        Thread.sleep(10000);
        driver.navigate().back();
        Thread.sleep(10000);
        driver.navigate().forward();
    }

    @Test
    public void TC_013_verifyWebElementCommands() throws InterruptedException {
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
    public void TC_014_verifyIsDisplayed() {
        driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement subject = driver.findElement(By.xpath("//input[@id='subject']"));
        subject.sendKeys("Selenium");
        boolean status = subject.isDisplayed();
        System.out.println(status);
        Assert.assertTrue(status, "subject field is not displayed");
    }

    @Test
    public void TC_015_verifyIsSelected() {
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
    public void TC_016_verifyIsEnabled() {
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
    public void TC_017_verifyDifferenceBetweenFindElementAndFindElements() {

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
    public void TC_018_verifyMultipleWindowHandling() {
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
    public void TC_019_verifyMultipleWindowHandlingHomeWork1() {
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
    public void TC_020_verifyMultipleWindowHandlingHomeWork2() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement newWindowbutton = driver.findElement(By.id("windowButton"));
        newWindowbutton.click();
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
    public void TC_021_verifyMultipleWindowHandlingHomeWork3() {
        driver.get("https://demoqa.com/browser-windows");
        String parentWindow = driver.getWindowHandle();
        WebElement windowMessageButton = driver.findElement(By.id("messageWindowButton"));
        windowMessageButton.click();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> handleids = handles.iterator();
        while (handleids.hasNext()) {
            String childWindow = handleids.next();
            if (!childWindow.equals(parentWindow)) {
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

    @Test
    public void TC_022_verifySimpleAlert() {

        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
        clickMe.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();
    }

    @Test
    public void TC_023_verifyConfirmationAlert() {
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
        clickMe.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.dismiss();
    }

    @Test
    public void TC_024_verifyPromptAlert() {
        driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
        WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
        clickMe.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("kgopal");
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();
    }

    @Test
    public void TC_025_verifyTextInaFrame() {
        driver.get("https://demoqa.com/frames");
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        int numberOfFrames = frames.size();
        System.out.println(numberOfFrames);
        //driver.switchTo().frame("frame1");    /** switching to frame using id**/
        // driver.switchTo().frame(4);          /** switching to frame using index**/
        WebElement frame = driver.findElement(By.id("frame1"));  /** switching to frame using webelement**/
        driver.switchTo().frame(frame);
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String text = heading.getText();
        System.out.println(text);
        driver.switchTo().parentFrame();  /** To switch to parent frame **/
        // driver.switchTo().defaultContent()  /** Also used to switch to parent frame **/
    }

    @Test
    public void TC_026_VerifyRightClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClickButton = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(rightClickButton).build().perform();
//      action.contextClick().build().perform();
    }

    @Test
    public void TC_027_verifyDoubleClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClick).build().perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void TC_028_verifyMouseOver() {
        driver.get("https://demoqa.com/menu");
        WebElement mainItem1 = driver.findElement(By.xpath("//a[text()='Main Item 1']"));
        Actions action = new Actions(driver);
        action.moveToElement(mainItem1).build().perform();
        //action.moveToElement(mainItem1,50,50).build().perform();
        //action.moveByOffset(40,80).build().perform();
    }

    @Test
    public void TC_029_verifyDragAndDrop() {
        driver.get("https://demoqa.com/droppable");
        WebElement dragMe = driver.findElement(By.id("draggable"));
        WebElement dropHere = driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        action.dragAndDrop(dragMe, dropHere).build().perform();
    }

    @Test
    public void TC_030_verifyDragAndDropByOffset() {
        driver.get("https://demoqa.com/droppable");
        WebElement dragMe = driver.findElement(By.id("draggable"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(dragMe, 270, 100).build().perform();
    }

    @Test
    public void TC_031_verifyDragAndDropHomeWork() {
        driver.get("https://selenium.obsqurazone.com/drag-drop.php");
        WebElement draggableFirst = driver.findElement(By.xpath("//span[text()='Draggable n°1']"));
        WebElement draggableSecond = driver.findElement(By.xpath("//span[text()='Draggable n°2']"));
        WebElement draggableThird = driver.findElement(By.xpath("//span[text()='Draggable n°3']"));
        WebElement draggableFourth = driver.findElement(By.xpath("//span[text()='Draggable n°4']"));
        WebElement dropZone = driver.findElement(By.id("mydropzone"));
        Actions action = new Actions(driver);
        action.dragAndDrop(draggableFirst, dropZone).build().perform();
        /**action.dragAndDrop(draggableSecond, dropZone).build().perform();
         action.dragAndDrop(draggableThird, dropZone).build().perform();
         action.dragAndDrop(draggableFourth, dropZone).build().perform();**/
    }

    @Test
    public void TC_032_verifyResizeHomeWork() {
        driver.get("https://demoqa.com/resizable");
        WebElement reSize = driver.findElement(By.xpath("//span[@class='react-resizable-handle react-resizable-handle-se']"));
        Actions action = new Actions(driver);
        action.clickAndHold().dragAndDropBy(reSize, 250, 250).build().perform();
        action.release(reSize).build().perform();
    }

    @Test
    public void TC_033_verifyValuesInDropDown() {
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        List<String> expectedDropdownList = new ArrayList<>();
        expectedDropdownList.add("ALBANIA");
        expectedDropdownList.add("ALGERIA");
        expectedDropdownList.add("AMERICAN SAMOA");
        expectedDropdownList.add("ANDORRA");
        List<String> actDropdownList = new ArrayList<>();
        WebElement countryDropDown = driver.findElement(By.name("country"));
        Select select = new Select(countryDropDown);
        List<WebElement> dropdownOptions = select.getOptions();
        /* for (int i=0;i< dropdownOptions.size();i++){
            System.out.println(dropdownOptions.get(i).getText());
        } */
        for (int i = 0; i < 4; i++) {
            System.out.println(dropdownOptions.get(i).getText());
            actDropdownList.add(dropdownOptions.get(i).getText());
        }
        System.out.println(actDropdownList);
        Assert.assertEquals(actDropdownList, expectedDropdownList, "values are not matching");
        //select.selectByVisibleText("INDIA");
        //select.selectByIndex(23);
        select.selectByValue("INDIA");   /** value will be the text inside the value attribute of options tag**/
    }

    @Test
    public void TC_034_verifyMethodsInSelectClass() {
        driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
        WebElement dropDown = driver.findElement(By.xpath("//select[@class='spTextField'][1]"));
        Select select = new Select(dropDown);
        boolean multipleStatus = select.isMultiple();
        System.out.println(multipleStatus);
        select.selectByVisibleText("Performance Testing");
        select.selectByVisibleText("Agile Methodology");
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        for (int i = 0; i < selectedOptions.size(); i++) {
            System.out.println(selectedOptions.get(i).getText());
        }
        select.deselectAll();
    }

    @Test
    public void TC_035_verifyFileUploadInSelenium() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFile = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
        chooseFile.sendKeys("D:\\Javaprograms\\TestFileforFileupload.txt");
        WebElement checkBox = driver.findElement(By.xpath("//input[@id='terms']"));
        checkBox.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        submitButton.click();
    }

    @Test
    public void TC_036_verifyClickAndSendKeysUsingJavaScriptExecutor() {
        driver.get("https://demowebshop.tricentis.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('newsletter-email').value='Test@test.com'");
        js.executeScript("document.getElementById('newsletter-subscribe-button').click()");
    }
    @Test
    public void TC_037_verifyWaitInSelenium() {
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement emailField= driver.findElement(By.xpath("//input[@id='newsletter-email']"));
        emailField.sendKeys("Test@test.com");
        WebElement submitButton= driver.findElement(By.xpath("//input[@id='newsletter-subscribe-button']"));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        FluentWait fwait=new FluentWait<>(driver);
        fwait.withTimeout(Duration.ofSeconds(10));
        fwait.pollingEvery(Duration.ofSeconds(1));
        fwait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
    }
    @Test
    public void TC_038_verifyScrollDownOfWebpage() {
        driver.get("https://demo.guru99.com/test/guru99home/");
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @Test
    public void TC_039_verifyScrollingToViewOfWebElement() {
        driver.get("https://demo.guru99.com/test/guru99home/");
        WebElement linux= driver.findElement(By.linkText("Linux"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",linux);
    }
    @Test
    public void TC_040_verifyScrollToBottomOfThePage() {
        driver.get("https://demo.guru99.com/test/guru99home/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    @Test
    public void TC_041_verifyHorizontalScroll() {
        driver.get("https://demo.guru99.com/test/guru99home/scrolling.html");
        WebElement vbScript= driver.findElement(By.linkText("VBScript"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",vbScript);
    }
    @Test
    public void TC_042_verifyTable() throws IOException {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List <WebElement> rowElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
        List <WebElement> columnElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
        List<ArrayList<String>> actGridData=TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowElements,columnElements);
        List<ArrayList<String>> expGridData=ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx","Table");
        Assert.assertEquals(actGridData,expGridData,"Invalid data found in table");
        System.out.println(actGridData);


    }

    @Test
    public void TC_043_verifyFileUploadUsingRobotClass() throws AWTException, InterruptedException {
        driver.get("https://www.foundit.in/seeker/registration");
        StringSelection s= new StringSelection("D:\\Javaprograms\\TestFileforFileupload.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
        WebElement chooseFile= driver.findElement(By.xpath("//span[text()='Choose CV']"));
        chooseFile.click();
        Robot r= new Robot();
        Thread.sleep(5000);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(5000);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        Thread.sleep(5000);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        Thread.sleep(5000);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }


}
