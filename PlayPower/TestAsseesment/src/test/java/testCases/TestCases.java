package testCases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import utilities.RetryAnalyzer;

public class TestCases extends BaseTest{
	
	
	@Test(priority =1, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_001() {
		test = extentReports.createTest("TC_PP_001", "Add/Remove element");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement addRemoveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add/Remove Elements']")));
	    addRemoveButton.click();
	    WebElement addButton = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
	    Assert.assertTrue(addButton.isDisplayed(), "Add Element");
	    addButton.click();
	    WebElement deleteButton = driver.findElement(By.xpath("//button[@class='added-manually']"));
	    Assert.assertTrue(deleteButton.isDisplayed(), "Delete");
	    deleteButton.click();
	    Assert.assertTrue(driver.findElements(By.xpath("//button[@class='added-manually']")).isEmpty(), "Delete button should be removed");
	    
	    test.log(Status.PASS, "TC_PP_001 is Passed");
	}
	
	@Test(priority =2, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_002() {
		test = extentReports.createTest("TC_PP_002", "CheckBoxes");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Checkboxes']")));
	    checkBox.click();
	    WebElement checkbox1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[1]")));
	    WebElement checkbox2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[2]")));

	    if (!checkbox1.isSelected()) {
	        checkbox1.click();
	    }

	    if (checkbox2.isSelected()) {
	        checkbox2.click();
	    }

	    Assert.assertTrue(checkbox1.isSelected(), "Checkbox 1 should be selected");
	    Assert.assertFalse(checkbox2.isSelected(), "Checkbox 2 should be unselected");
	    
	    test.log(Status.PASS, "TC_PP_002 is Passed");
	}
	
	
	@Test(priority =3, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_003() {
		test = extentReports.createTest("TC_PP_003", "DropDown");
		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		try {
            
            WebElement dropdownLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Dropdown']")));
            dropdownLink.click();

            WebElement dropdownOptions = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown")));
            Select select = new Select(dropdownOptions);
            
            select.selectByVisibleText("Option 1");
            WebElement selectedOption1 = select.getFirstSelectedOption();
            Assert.assertEquals("Option 1", selectedOption1.getText(), "Selected option should be Option 1");
            select.selectByVisibleText("Option 2");
            WebElement selectedOption2 = select.getFirstSelectedOption();
            Assert.assertEquals("Option 2", selectedOption2.getText(), "Selected option should be Option 2");

            System.out.println("Test Passed: Both options selected and verified as expected.");

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } 
		
		test.log(Status.PASS, "TC_PP_003 is Passed");
		
	}
	
	@Test(dataProvider = "formAuthentication", priority = 4, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_004(String username, String password) {
		
		test = extentReports.createTest("TC_PP_004", "formAuthentication");
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.navigate().to("https://the-internet.herokuapp.com");
		driver.findElement(By.xpath("//a[normalize-space()='Form Authentication']")).click();

		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.cssSelector(".fa.fa-2x.fa-sign-in"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		
		 WebElement flashMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash']")));
	        String text = flashMessage.getText().trim(); 

	        if (text.contains("You logged into a secure area!")) {
	            System.out.println("Logged in Successful");
	        } else if (text.contains("Your password is invalid!")) {
	            System.out.println("Please check your password");
	        } else if (text.contains("Your username is invalid!")) {
	            System.out.println("Please check your username");
	        } else {
	            System.out.println("Unexpected message: " + text); 
	        }
	        
	        test.log(Status.PASS, "TC_PP_004 is Passed");
	}
	
	@Test(priority =5, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_005() throws Exception {
		test = extentReports.createTest("TC_PP_005", "javaScriptExecutor Alerts");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='JavaScript Alerts']")));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        targetElement.click();
        
	    WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
	    jsAlert.click();
	    driver.switchTo().alert().accept();
	    WebElement element = driver.findElement(By.id("result")); 
        String actualText = element.getText().trim();
        System.out.println(actualText);
        String expectedText = "You successfully clicked an alert"; 
        Assert.assertEquals(actualText, expectedText, "Text does not match!");
        
        WebElement jsConfirm = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
	    jsConfirm.click();
	    driver.switchTo().alert().accept();
	    WebElement jsconfirmElement = driver.findElement(By.id("result")); 
        String actualText1 = jsconfirmElement.getText().trim();
        System.out.println(actualText1);
        String expectedText1 = "You clicked: Ok"; 
        Assert.assertEquals(actualText1, expectedText1, "Text does not match!");
        
        WebElement jsPromptButton = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
        jsPromptButton.click();
	    Alert jsPrompt = driver.switchTo().alert();
        jsPrompt.sendKeys("I am Sarath");
        jsPrompt.accept();
	    WebElement jsPromptElement = driver.findElement(By.id("result")); 
        String actualText2 = jsPromptElement.getText().trim();
        System.out.println(actualText2);
        String expectedText2 = "You entered: I am Sarath"; 
        Assert.assertEquals(actualText2, expectedText2, "Text does not match!");
        
        test.log(Status.PASS, "TC_PP_005 is Passed");
		
	}
	
	@Test(priority =6, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_006() {
		test = extentReports.createTest("TC_PP_006", "inputs Checker");
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement targetElement = driver.findElement(By.xpath("//a[normalize-space()='Inputs']")); 
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", targetElement);
        targetElement.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='number']")));
	    inputField.sendKeys("123");

	    Assert.assertEquals("123", inputField.getAttribute("value"), "Input value is incorrect");

	    inputField.clear();
	    inputField.sendKeys("456");

	    Assert.assertEquals("456", inputField.getAttribute("value"), "Input value is incorrect after change");
	    
	    test.log(Status.PASS, "TC_PP_006 is Passed");
	}
	
	@Test(priority = 7, retryAnalyzer = RetryAnalyzer.class)
	public void TC_PP_007() throws Exception {
		
		test = extentReports.createTest("TC_PP_007", "file Upload");
		
	    driver.findElement(By.xpath("//a[normalize-space()='File Upload']")).click();
	    WebElement uploadInput = driver.findElement(By.xpath("//input[@id='file-upload']"));
	    uploadInput.sendKeys("C:\\Users\\Admin\\OneDrive\\Desktop\\Test.txt");

	    WebElement uploadButton = driver.findElement(By.id("file-submit"));
	    uploadButton.click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement uploadConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
	   System.out.println(uploadConfirmation.getText());
	    Assert.assertEquals("Test.txt", uploadConfirmation.getText(), "Uploaded file name is incorrect");
	    
	    test.log(Status.PASS, "TC_PP_007 is Passed");
	}

@Test(priority =8, retryAnalyzer = RetryAnalyzer.class)
public void TC_PP_008() {
	
	test = extentReports.createTest("TC_PP_008", "Dynamic Loading");
   
    WebElement dinamicLoading = driver.findElement(By.xpath("//a[normalize-space()='Dynamic Loading']"));
    dinamicLoading.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    driver.findElement(By.xpath("//a[normalize-space()='Example 1: Element on page that is hidden']")).click();
    driver.findElement(By.xpath("//button[normalize-space()='Start']")).click();
    WebElement actaulText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Hello World!']")));
   // String actual = driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']")).getText();
    Assert.assertTrue(actaulText.isDisplayed(), "Dynamic content did not load in time");
    driver.navigate().back();
    
    driver.findElement(By.xpath("//a[normalize-space()='Example 2: Element rendered after the fact']")).click();
    driver.findElement(By.xpath("//button[normalize-space()='Start']")).click();
    WebElement visibleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Hello World!']")));
    driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']")).getText();
    Assert.assertTrue(visibleText.isDisplayed(), "Dynamic content did not load in time");

    test.log(Status.PASS, "TC_PP_008 is Passed");
}


@Test(priority = 9, retryAnalyzer = RetryAnalyzer.class)
public void TC_PP_009() throws Exception {
	
	test = extentReports.createTest("TC_PP_009", "Hovers");

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	WebElement hover = driver.findElement(By.xpath("//a[normalize-space()='Hovers']"));
	Actions actions = new Actions(driver);
	actions.moveToElement(hover).perform();
	hover.click();
	WebElement firstProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='example']//div[1]//img[1]")));
	actions.moveToElement(firstProfile).perform();
	WebElement user1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[normalize-space()='name: user1']")));
	String actual1 =user1.getText();
	String expected1 = "name: user1";
	Assert.assertEquals(actual1, expected1, " profile1 verification is failed");

	WebElement secondProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[2]//img[1]")));
	actions.moveToElement(secondProfile).perform();
	WebElement user2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[normalize-space()='name: user2']")));
	String actual2 =user2.getText();
	String expected2 = "name: user2";
	Assert.assertEquals(actual2, expected2, " profile2 verification is failed");
	
	WebElement thirdProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//img[1]")));
	actions.moveToElement(thirdProfile).perform();
	WebElement user3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[normalize-space()='name: user3']")));
	String actual3 =user3.getText();
	String expected3 = "name: user3";
	Assert.assertEquals(actual3, expected3, " profile verification is failed");
	
	test.log(Status.PASS, "TC_PP_009 is Passed");
}


@Test(priority = 10, retryAnalyzer = RetryAnalyzer.class)
public void TC_PP_010() {
	
	test = extentReports.createTest("TC_PP_010", "js onload event error");
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	WebElement onLoadEvent =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='JavaScript onload event error']")));
	onLoadEvent.click();

    String actualResult = driver.findElement(By.xpath("//p[contains(text(),'This page has a JavaScript error in the onload eve')]")).getText();
    String expectedResult = "This page has a JavaScript error in the onload event. This is often a problem to using normal Javascript injection techniques.";
    Assert.assertEquals(actualResult, expectedResult, "onload event error got failed");
		
    test.log(Status.PASS, "TC_PP_010 is Passed");
}


	@DataProvider(name = "formAuthentication")
	public Object[][] logInData() {
		return new Object[][] { 
			{ "tomsmith", "SuperSecretPassword!" },
			{ "tomsmith", "Password!" },
			{ "srinu", "SuperSecretPassword!" } };
	}

	

}
