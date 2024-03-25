package com.QAFox.qa.testcases;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QAFox.qa.Base.Base;

public class Register extends Base{
	


	WebDriver driver;
	
	public String timeStamp() {
		Date date = new Date();
	    String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
	    return timeStamp;
		
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = initializingBrowser("browser");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifyRegisterWithMandatoryFields() {
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataProp.getProperty("FirstName"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(dataProp.getProperty("RegisterEmail"));
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataProp.getProperty("RegisterTelephone"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("RegisterPassword"));
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys(dataProp.getProperty("RegisterPassword"));
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String accountCreationMessage = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
		String expectedAccountMessage = dataProp.getProperty("ExpectedAccountCreationMessage");
		
		Assert.assertTrue(accountCreationMessage.contains(expectedAccountMessage));
		
	}
	
	@Test(priority=2)
	public void verifyRegisterWithExistingEmail() {
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataProp.getProperty("FirstName"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(dataProp.getProperty("RegisterEmail"));
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataProp.getProperty("RegisterTelephone"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("RegisterPassword"));
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys(dataProp.getProperty("RegisterPassword"));
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String accountCreationMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedAccountMessage = dataProp.getProperty("ExpectedErrorMessage");
		
		Assert.assertTrue(accountCreationMessage.contains(expectedAccountMessage));
		
		}
	
	@Test(priority=3) 
	public void verifyRegisterWithoutAnyData() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String acceptTermsMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String acceptTermsMessageActual = dataProp.getProperty("ExpectedPrivacyPolicyErrorMessage");
		Assert.assertEquals(acceptTermsMessage, acceptTermsMessageActual);
		
		String firstNameErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
		String actualFirstNameErrorMessage = dataProp.getProperty("ExpectedFirstNameErrorMessage");
		Assert.assertTrue(firstNameErrorMessage.contains(actualFirstNameErrorMessage));
		
		String lastNameErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
		String actualLastNameErrorMessage = dataProp.getProperty("ExpectedLastNameErrorMessage");
		Assert.assertTrue(lastNameErrorMessage.contains(actualLastNameErrorMessage));
		
		String emailErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
		String actualEmailErrorMessage = dataProp.getProperty("ExpectedEmailErrorMessage");
		Assert.assertTrue(emailErrorMessage.contains(actualEmailErrorMessage));
		
		String telephoneErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText();
		String actualTelephoneErrorMessage = dataProp.getProperty("ExpectedTelephoneErrorMessage");
		Assert.assertTrue(telephoneErrorMessage.contains(actualTelephoneErrorMessage));
		
		String passwordErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText();
		String actualPasswordErrorMessage = dataProp.getProperty("ExpectedPasswordErrorMessage");
		Assert.assertTrue(passwordErrorMessage.contains(actualPasswordErrorMessage));
	}
	
	@Test(priority=4) 
	public void verifyRegisterWithDifferentPasswords(){
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataProp.getProperty("FirstName"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(dataProp.getProperty("RegisterEmail"));
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataProp.getProperty("RegisterTelephone"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(dataProp.getProperty("RegisterPassword"));
		driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys(dataProp.getProperty("RegisterInvalidPassword"));
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String errorMessageExpected = driver.findElement(By.xpath("//div[text()='Password confirmation does not match password!']")).getText();
		String errorMessageActual = dataProp.getProperty("ExpectedDifferentPasswordErrorMessage");
		Assert.assertEquals(errorMessageExpected, errorMessageActual);
		
	}
 }
