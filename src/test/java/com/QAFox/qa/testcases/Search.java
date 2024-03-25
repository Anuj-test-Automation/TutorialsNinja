package com.QAFox.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.QAFox.qa.Base.Base;

public class Search extends Base{
	
	
	WebDriver driver;
	

	@BeforeMethod
	public void setUp() {
		driver = initializingBrowser("browser");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("imac");
		driver.findElement(By.xpath("//button[@type='button' and contains(@class, 'btn-default') and contains(@class, 'btn-lg')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='iMac']")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() throws Exception {
		
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("FitBit");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='button' and contains(@class, 'btn-default') and contains(@class, 'btn-lg')]")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).isDisplayed());
		Thread.sleep(3000);
		
		}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyData() {
		
		driver.findElement(By.xpath("//button[@type='button' and contains(@class, 'btn-default') and contains(@class, 'btn-lg')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).isDisplayed());
		
		}
	}
