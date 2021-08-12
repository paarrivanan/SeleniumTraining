package mavenDemo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import common.commonUtils;
import common.resusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;

public class demo {

	public static WebDriver driver;
	commonUtils util;

	@Test
	void program13() {

		System.out.println(" Test 1 Starts here ==>   ");
		System.out.println(" Sprint 1 Changes ==>   ");

		//Launch your browser and navigate to a specific URL
		driver.get("https://google.co.in");
		driver.manage().window().maximize();

		//Validations
		String URL = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println(URL);
		System.out.println(title);

	}

	@Test
	void program2() throws InterruptedException {

		System.out.println(" Test 2 Starts here ==>   ");

		//Launch your browser and navigate to a specific URL
		driver.get("https://www.demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		Thread.sleep(3000);

		//Identify different objects
		WebElement firstName = driver.findElement(By.xpath("//*[@id='firstName']"));
		WebElement lastname = driver.findElement(By.xpath("//*[@id='lastName']"));
		WebElement email = driver.findElement(By.xpath("//*[@id='userEmail']"));

		firstName.sendKeys("FN_Test");
		lastname.sendKeys("LN_Test");
		email.sendKeys("email@xyz.com");
		Thread.sleep(3000);

		firstName.sendKeys(Keys.CONTROL + "a");
		firstName.sendKeys(Keys.CONTROL + "c");
		lastname.clear();
		lastname.sendKeys(Keys.CONTROL + "v");
		Thread.sleep(3000);

		WebElement radioMale = driver.findElement(By.xpath("//*[text()='Male']"));
		WebElement radioFemale = driver.findElement(By.xpath("//*[text()='Female']"));
		//WebElement radioOther = driver.findElement(By.xpath("//*[text()='Other']"));

		radioMale.click();
		radioFemale.click();



		//Exception Handling
		try {
			WebElement chkSports = driver.findElement(By.xpath("//*[text()='Sports']"));
			WebElement chkReading = driver.findElement(By.xpath("//*[text()='Reading']"));

			boolean val = chkSports.isDisplayed();
			System.out.println("Displayed -> "+ val);
			val = chkSports.isEnabled();
			System.out.println("Enabled -> "+ val);
			val = chkSports.isSelected();
			System.out.println("Sports Selected -> "+ val);

			if(!val) {
				chkSports.click();
			}

			val = chkReading.isSelected();
			System.out.println("Reading Selected -> "+ val);

			if(!val) {
				chkReading.click();
			}

			val = chkSports.isSelected();
		}
		catch(NoSuchElementException e) {
			System.out.println(e);
			Assert.fail("Element not found in the page ***");
		}





	}

	@Test
	void program3() throws InterruptedException, TimeoutException {

		System.out.println(" Test 3 Starts here ==>   ");

		driver.get("https://demoqa.com/select-menu");
		driver.manage().window().maximize();

		commonUtils utils = new commonUtils(driver);
		WebElement drp = utils.explicitWait(By.xpath("//*[@id='oldSelectMenu']"), 10);


		Select drpDown = new Select(drp);
		drpDown.selectByIndex(2);
		Thread.sleep(2000);
		drpDown.selectByValue("8");
		Thread.sleep(2000);
		drpDown.selectByVisibleText("Aqua");
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("arguments[0].scrollIntoView();", drpDown);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		//Copy the file to a location and use try catch block to handle exception
		try {
			FileUtils.copyFile(screenshot, new File("../SeleniumTraining/src/test/java/screenshots/snapshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		//Select multiSelect = new Select(driver.findElement(By.xpath("//*[@id='cars']")));
		Select multiSelect = new Select(utils.explicitWait(By.xpath("//*[@id='cars']"), 10));
		multiSelect.selectByVisibleText("Audi");
		Thread.sleep(2000);
		multiSelect.selectByIndex(1);
		Thread.sleep(2000);
		multiSelect.selectByValue("volvo");
		Thread.sleep(2000);

		System.out.println(" Get attribute exmaple ==>" + driver.findElement(By.xpath("//*[@id='cars']")).getAttribute("value"));

		driver.navigate().to("https://demoqa.com/droppable/");
		Thread.sleep(3000);



		Actions act = new Actions(driver);
		WebElement dragMe = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement dropHere = driver.findElement(By.xpath("//*[@id='droppable']"));
		act.dragAndDrop(dragMe, dropHere).build().perform();

		System.out.println("Change in the property ==> " + dropHere.getText());



	}


	@Test
	void frames() {

		System.out.println(" Test 4 Starts here ==>   ");

		//Launch your browser and navigate to a specific URL
		driver.get("https://demoqa.com/frames");
		driver.manage().window().maximize();

		//create test case
		ExtentTest test = commonUtils.extent.createTest("Frames", "Validation of Frames").assignAuthor("Paarri");

		//Identify my webelement
		try {
			driver.switchTo().frame(driver.findElement(By.id("frame1")));
			List<WebElement> header = driver.findElements(By.tagName("a"));

			driver.switchTo().defaultContent();
			header = driver.findElements(By.tagName("a"));
			System.out.println("Element 1 ==> " + header.get(0).getText());
			test.pass("Frame validation successful");
		}
		catch(Exception e) {
			test.fail("Test Case failed");
		}

	}

	@Test
	void alert() throws InterruptedException {
		System.out.println(" Test 5 Starts here ==>   ");

		//Launch your browser and navigate to a specific URL
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();

		//create test case
		ExtentTest test = commonUtils.extent.createTest("Alerts", "Validation of alerts").assignAuthor("Paarri");

		//Handling alerts
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='alertButton']")).click();
		String val = driver.switchTo().alert().getText();
		System.out.println("Text is ==> "+ val);
		driver.switchTo().alert().accept();
		test.pass("Alert Validation successfull");

	}

	@Test
	void webTable() {

		System.out.println(" Test 6 Starts here ==>   ");

		//Launch your browser and navigate to a specific URL
		driver.get("https://www.w3schools.com/sql/sql_syntax.asp");
		driver.manage().window().maximize();

		//*[@id="app"]/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[1]
		//*[@id="app"]/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[1]/div/div[6]
		//Option 1
		/*String bXpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[";
		String aXpath = "]/div/div[1]";
		String deptXpath = "]/div/div[6]";
		String salXpath = "]/div/div[5]";

		List<WebElement> row = driver.findElements(By.xpath("//*[@class='rt-td'][text()]/ancestor::div[@class='rt-tr-group']"));

		int i;
		for (i=1; i <= row.size(); i++) {
			String fName = driver.findElement(By.xpath(bXpath+i+aXpath)).getText();
			if(fName.contains("Cierra")){
				System.out.println("First Name is ==> " + fName);

				String dept = driver.findElement(By.xpath(bXpath+i+deptXpath)).getText();
				String sal = driver.findElement(By.xpath(bXpath+i+salXpath)).getText();

				System.out.println("Department is ==> " + dept);
				System.out.println("Salary is ==> " + sal);

				break;
			}

		}*/

		ExtentTest test = commonUtils.extent.createTest("WebTable", "Retrieve City and Country from WebTable usign Coustmer name").assignAuthor("Paarri");

		//Scroll
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		test.info("Scrolled to the WebTabe");
		//js.executeScript("arguments[0].scrollIntoView();", drpDown);

		//Option 2
		resusableMethods rm = new resusableMethods(driver);
		rm.getRecord("Alfreds Futterkiste");
		test.pass("City and COuntry was retieved succesffully for Alfreds Futterkiste");
		rm.getRecord("Berglunds snabbköp");
		test.pass("City and Country was retieved succesffully for Berglunds snabbköp");
		try {
			rm.getRecord("Kierra");
			test.pass("City and Country was retieved succesffully for Kierra");
		}
		catch(Exception e) {
			test.fail("Record not identified.");
		}

	}

	@BeforeMethod
	void setup() {
		// Web driver manager
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}


	@AfterMethod
	void teardown() {
		//close browser
		driver.quit();
		
		System.out.println(" Execution Ends here ==>   ");
	}

	@BeforeSuite
	void bSuite() {
		//report object
		util = new commonUtils(driver);
		util.initReporter();
	}
	
	@AfterSuite
	void aSuite(){
		util.exitReporter();
	}


}
