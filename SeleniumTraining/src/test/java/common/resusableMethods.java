package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class resusableMethods {
	
	public WebDriver driver;
	
	public resusableMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getRecord(String name) {
		//String fName = driver.findElement(By.xpath("//*[text()='"+name+"']")).getText();
		String city = driver.findElement(By.xpath("//*[text()='"+name+"']/following-sibling::td[3]")).getText();
		String country = driver.findElement(By.xpath("//*[text()='"+name+"']/following-sibling::td[5]")).getText();
		//System.out.println(name+"'s First name is ==> " + fName);
		System.out.println(name+"'s City is ==> " + city);
		System.out.println(name+"'s Country is ==> " + country);
		System.out.println("the result end here");
	}

}
