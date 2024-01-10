package com.tutorialsninja.qa.baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utlils.utilities;

public class BaseLogin {

	WebDriver driver;
	public Properties prop ;
	public Properties dataProp;
	public  BaseLogin() {
		dataProp =new Properties();
		
		File dataPropFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream datafis =new FileInputStream(dataPropFile);
		dataProp.load(datafis);
		}catch (Exception e) {
			e.printStackTrace();
		}
		prop= new Properties();
		
		File propFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {	
		FileInputStream fis =new FileInputStream(propFile)	;	
		prop.load(fis);
		}
		catch (Throwable e){
			e.printStackTrace();
			
		}
		
	}
	
	public WebDriver intializeBrowserAndOpenApplicationOnURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver =new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utilities.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("URL"));
		return driver;
	
	}

}
