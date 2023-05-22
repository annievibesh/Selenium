import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class hw {

    private WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new EdgeDriver(); 
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        driver.manage().window().maximize();
    }

    @Test(priority=1,enabled=false)
    public void inspectSingleFieldElements() throws InterruptedException {
    	SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(), "Obsqura Testing");
        softAssert.assertTrue(driver.getCurrentUrl().contains("selenium.obsqurazone.com/"));
        driver.findElement(By.id("single-input-field")).click();
		driver.findElement(By.id("single-input-field")).sendKeys("Admin");
		String expectedCode="Admin";
		driver.findElement(By.xpath("//button[@id='button-one']")).click();
		Thread.sleep(3000);
		WebElement displayedCodeElement = driver.findElement(By.id("message-one")); 
        String displayedCode = displayedCodeElement.getText();
        System.out.println(displayedCode);
        String input=displayedCode;
        int startIndex = input.indexOf(":") + 1; // Get the index after the colon
        String extractedText = input.substring(startIndex).trim();
        System.out.println(extractedText);
        Assert.assertEquals(extractedText, expectedCode, "Labels mismatch!");
               
       }
    @Test(priority=2,enabled=false)
    public void inspectTwoInputFieldElements() throws InterruptedException
    {
    	 driver.findElement(By.id("value-a")).click();
 		driver.findElement(By.id("value-a")).sendKeys("4");
 		int num1 = 4;
 		 driver.findElement(By.id("value-b")).click();
  		driver.findElement(By.id("value-b")).sendKeys("5");
        int num2 = 5;
        int expectedSum =num1+num2;
        
        String strExpectedSum = String.valueOf(expectedSum);
        
        String concatenatedString = "Total A + B : " + strExpectedSum;
        System.out.println(concatenatedString);
        driver.findElement(By.xpath("//button[@id='button-two']")).click();
        Thread.sleep(3000);
		WebElement displayedCodeElement = driver.findElement(By.xpath("//div[@id='message-two']")); 
        
        String displayedSumming = displayedCodeElement.getText();
       
        System.out.println(displayedSumming);

        Assert.assertEquals(displayedSumming, concatenatedString, "Code mismatch!");

        
    }
   
    @Test(priority=3)
    public void inspectSingleCheckBox() throws InterruptedException
    {
    	driver.navigate().to("https://selenium.obsqurazone.com/check-box-demo.php");
    	driver.manage().window().maximize();
    	WebElement checkbox = driver.findElement(By.xpath("//label[@for='gridCheck']"));
    	 System.out.println("Initial Checkbox State: " + checkbox.isSelected());
    	checkbox.click();
    	Thread.sleep(3000);
//    	 WebDriverWait wait = new WebDriverWait(driver, 10);
//         wait.until(ExpectedConditions.elementSelectionStateToBe(checkbox, true));

        
    	System.out.println("Checkbox State after Click: " + checkbox.isSelected());
   	    //SoftAssert softAssert = new SoftAssert();
   	    softAssert.assertTrue(checkbox.isSelected(), "Checkbox is not selected after clicking.");
    	softAssert.assertAll();
    }
    @Test(priority=4,enabled=false)
    public void inspectMultipleCheckBox() throws InterruptedException
    {
    	driver.navigate().to("https://selenium.obsqurazone.com/check-box-demo.php");
    	driver.manage().window().maximize();
    	WebElement checkbox1 =driver.findElement(By.xpath("//input[@id='check-box-one']"));
    	System.out.println("Initial Checkbox State: " + checkbox1.isSelected());
    	checkbox1.click();
    	Thread.sleep(3000);
    	System.out.println("Checkbox State after Click: " + checkbox1.isSelected());
    	if(!checkbox1.isSelected())
    		checkbox1.click();
    	System.out.println("Checkbox State after Clicking again: " + checkbox1.isSelected());
    	
    	//SoftAssert softAssert = new SoftAssert();
    	softAssert.assertTrue(checkbox1.isSelected(), "Checkbox1 is not selected after clicking.");
    	softAssert.assertAll();
    }
    @Test(priority=5,enabled=false)
    public void inspectRadioButtonDemo() throws InterruptedException
    {
    	driver.navigate().to("https://selenium.obsqurazone.com/radio-button-demo.php");
    	driver.manage().window().maximize();
    	WebElement radiobutton =driver.findElement(By.xpath("//input[@id='inlineRadio1']"));
    	radiobutton.click();
    	Thread.sleep(3000);
    	WebElement radiobutton1 =driver.findElement(By.xpath("//input[@id='inlineRadio2']"));
    	radiobutton1.click();
    	Thread.sleep(3000);
    	softAssert.assertTrue(radiobutton.isSelected(), "Radio button  is not selected after clicking.");
    	softAssert.assertTrue(radiobutton1.isSelected(), "Radio button 1 is not selected after clicking.");
    	softAssert.assertAll();
    }
    @Test(priority=6,enabled=false)
    public void inspectingStaicDropDown() throws InterruptedException 
    {
    	driver.navigate().to("https://selenium.obsqurazone.com/select-input.php");
    	driver.manage().window().maximize();
    	WebElement radio =driver.findElement(By.id("single-input-field"));
    	Select select = new Select(radio);
    	radio.click();
    	select.selectByValue("Red");
    	String expected = "Selected Color :Red " ;
    	String color=driver.findElement(By.id("message-one")).getText();
    	System.out.println(color);
    	softAssert.assertEquals(color, expected, "Red is not selected");
    	Thread.sleep(3000);
    	
    }
    @Test
    public void inspectDynamicDropDown()
    {
    	driver.navigate().to("https://selenium.obsqurazone.com/jquery-select.php");
    	driver.manage().window().maximize();
    	driver.findElement(By.xpath("//input[@aria-label='Search']")).click();
    	List<WebElement> dynamicRadio = (List<WebElement>) driver.findElement(By.xpath("//li[@role='option']"));
    	                            
    	for(WebElement web:dynamicRadio)
    	{
    		String options =web.getText();
    		System.out.println(options);
    		
    	}
    }
    
    @AfterMethod
    public void tearDown()
    {
    	driver.quit();
    	
    }
    @AfterClass
    public void endTesting() {
    	
        driver.quit();
    }
}
