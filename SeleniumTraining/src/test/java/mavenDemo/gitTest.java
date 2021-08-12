package mavenDemo;

import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.sikuli.script.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class gitTest {

	WebDriver driver; 

	@Test
	public void gitpush() throws InterruptedException, AWTException {

		//Launch your browser and navigate to a specific URL
		driver.get("https://www.demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		Thread.sleep(3000);

		WebElement ele = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
		//ele.click();
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).click().build().perform();
		Thread.sleep(3000);


		//enterText("D:\\Training\\Selenium\\Sample.jpg");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_SLASH);

		//		Thread.sleep(150);
		//		robot.keyPress(KeyEvent.VK_A);
		//		robot.keyPress(KeyEvent.VK_M);
		//		robot.keyPress(KeyEvent.VK_P);
		//		robot.keyPress(KeyEvent.VK_L);
		//		robot.keyPress(KeyEvent.VK_E);
		//		robot.keyPress(KeyEvent.VK_PERIOD);
		//		robot.keyPress(KeyEvent.VK_J);
		//		robot.keyPress(KeyEvent.VK_P);
		//		robot.keyPress(KeyEvent.VK_G);
		//		Thread.sleep(150);
		//		robot.keyPress(KeyEvent.VK_ENTER);

		Thread.sleep(4000);


	}

	@Test
	void sikuliTest() throws InterruptedException, FindFailed {
		//Launch your browser and navigate to a specific URL
		driver.get("https://www.demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		String filepath = "D:\\Training\\Selenium\\";
		
		boolean val1 = new File(filepath + "filesName_Image.png").exists();
		boolean val2 = new File(filepath + "open_Image.png").exists();
		
		System.out.println("file -> "+ val1 + " . Open - " + val2);
		

		WebElement ele = driver.findElement(By.xpath("//*[@id='uploadPicture']"));
		//ele.click();
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).click().build().perform();
		Thread.sleep(3000);
		
		Screen s = new Screen();		
		//s.click(filepath + "documents_Image.png");
		Pattern FileInputPath = new Pattern(filepath + "filesName_Image.png");
		Thread.sleep(2000);
		Pattern OpenPath = new Pattern(filepath + "filesName_Image.png");
		Thread.sleep(2000);
		
		s.type(FileInputPath, filepath + "Sample.jpg");
		Thread.sleep(2000);
		s.click(OpenPath);
		Thread.sleep(2000);
		
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

	public void enterText(String data) throws AWTException, InterruptedException {

		// Create an instance of Robot class
		Robot robot = new Robot();
		String s=data;

		char c;
		int a=s.length(),b=0,m=0;
		while(b<a)
		{
			c=s.charAt(b);
			m=(int) c; //converts character to Unicode.
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(m));//Types the string on the screen
			b++;
			Thread.sleep(150);
		}

	}
}
