package com.tutorialsninja.qa.testcases;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseTest.BaseLogin;
import com.tutorialsninja.qa.pageobject.AccountPage;
import com.tutorialsninja.qa.pageobject.LoginPage;
import com.tutorialsninja.qa.pageobject.homePage;
import com.tutorialsninja.qa.utlils.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends BaseLogin {
	LoginPage lp;
	public WebDriver driver;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = intializeBrowserAndOpenApplicationOnURL(prop.getProperty("browser"));

		homePage hp = new homePage(driver);
		lp = hp.navigateToLoginpage();
		// hp.clickMyAccount();
		// lp = hp.clickLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// SupplyTestData
	@Test(priority = 1, dataProvider = "validCrendtial")
	public void loginWithValidationCredentials(String Email, String Password) {

		AccountPage ap = lp.login(Email, Password);
		Assert.assertTrue(ap.getDisplayedStatusofeditYourAccountInformationOption(),
				"Edit your account information is not displayed");

		/**
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Logout")).click();
		 * driver.findElement(By.linkText("Continue")).click();
		 **/
	}

	@DataProvider(name = "validCrendtial")
	public Object[][] supplyTestdata() {

		Object[][] data = utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void loginwithInvalidCrendentials() {

		lp.login(utilities.generateEmailwithTimeStamp(), dataProp.getProperty("invalidpassowrd"));
		// String actualtext = lp.getEmailAndPasswordNotMatchedWarningMessage();
		// String exceptedtext = dataProp.getProperty("emailPasswordMatchwarning");
		Assert.assertTrue(
				lp.getEmailAndPasswordNotMatchedWarningMessage()
						.contains(dataProp.getProperty("emailPasswordMatchwarning")),
				"Excepted warning message is not displayed");

	}

	@Test(priority = 3)

	public void verifyLoginWithInvalidEmailandValidPassword() {

		lp.login(utilities.generateEmailwithTimeStamp(), prop.getProperty("validPassword"));
		String actualtext = lp.getEmailAndPasswordNotMatchedWarningMessage();
		String exceptedtext = dataProp.getProperty("emailPasswordMatchwarning");
		Assert.assertTrue(actualtext.contains(exceptedtext), "Excepted warning message is not displayed");

	}

	@Test(priority = 4)

	public void verifyLoginWithvalidEmailandInValidPassword() {

		lp.login(prop.getProperty("ValidEmail"), dataProp.getProperty("invalidpassowrd"));
		String actualtext = lp.getEmailAndPasswordNotMatchedWarningMessage();
		String exceptedtext = dataProp.getProperty("emailPasswordMatchwarning");
		Assert.assertTrue(actualtext.contains(exceptedtext), "Excepted warning message is not displayed");

	}

	@Test(priority = 5)
	public void verifywithoutEnteringUsernameandPassword() {

		lp.clickLogin();
		String actualtext = lp.getEmailAndPasswordNotMatchedWarningMessage();
		String exceptedtext = dataProp.getProperty("emailPasswordMatchwarning");
		Assert.assertTrue(actualtext.contains(exceptedtext), "Excepted warning message is not displayed");

	}

}
