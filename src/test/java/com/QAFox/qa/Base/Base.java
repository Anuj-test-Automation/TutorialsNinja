package com.QAFox.qa.Base;

import java.io.File;  
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.QAFox.qa.utils.Utilities;

public class Base {
	
	public Properties prop;
	public Properties dataProp;
	public WebDriver driver;
	
	public Base() {
		
		try {
			dataProp = new Properties();
			File dpFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\QAFox\\qa\\testcases\\dataConfig.properties");
			FileInputStream fip = new FileInputStream(dpFile);
			dataProp.load(fip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	try {	
		prop = new Properties();
		File propertiesFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\QAFox\\qa\\Properties\\config.properties");
		FileInputStream ip = new FileInputStream(propertiesFile);
		prop.load(ip);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
		public WebDriver initializingBrowser(String browserName) {
			
		browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("Edge")) {
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Provide valid browser Namer");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIMEOUT));
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
}
