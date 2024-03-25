package com.QAFox.qa.testcases;


import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.QAFox.qa.Base.Base;
import com.QAFox.qa.utils.Utilities;

public class Login extends Base {
	
	public WebDriver driver;
	
	
	public Login() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		driver = initializingBrowser("browser");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String password) throws Exception {
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return  data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() throws Exception {
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("as6138792"+Utilities.timeStamp()+"@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		
		driver.quit();
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndPassword() throws Exception{
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("as6138792"+Utilities.timeStamp()+"@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualMessage, expectedMessage);
		driver.quit();
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithoutCredentials() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualMessage, expectedMessage);
		driver.quit();
	}
}
