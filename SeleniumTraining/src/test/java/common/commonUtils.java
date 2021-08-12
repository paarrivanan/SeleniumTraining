package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class commonUtils {

	public WebDriver driver;
	public static ExtentReports extent;
	private String path = "extent-output/result.html";

	public commonUtils(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement explicitWait(By by, int time) {
		WebElement ele;		
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			ele = wait.until(ExpectedConditions.elementToBeClickable(by));
			return ele;
		}
		catch(NoSuchElementException e) {
			System.out.println("Element not found in the desired time");
			return null;
		}

	}

	public void initReporter() {
		//Initiate my Extent report
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
		}
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		extent.attachReporter(spark);

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Test automation results");
		
	}
	
	public void exitReporter(){
		if (Objects.nonNull(extent)) {
		extent.flush();
		try {
			Desktop.getDesktop().browse((new File(path)).toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO Exception");
		}
		}
	}

}
