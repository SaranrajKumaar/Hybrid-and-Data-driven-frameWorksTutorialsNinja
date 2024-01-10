package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseTest.BaseLogin;
import com.tutorialsninja.qa.pageobject.AccountPage;
import com.tutorialsninja.qa.pageobject.Registerpage;
import com.tutorialsninja.qa.pageobject.accountSuccessPage;
import com.tutorialsninja.qa.pageobject.homePage;
import com.tutorialsninja.qa.utlils.utilities;

public class RegisterTest extends BaseLogin {
	homePage hp;
	Registerpage rp;
	accountSuccessPage asp;
	public WebDriver driver;

	public RegisterTest() {
		super();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@BeforeMethod
	public void setUp() {
		driver = intializeBrowserAndOpenApplicationOnURL(prop.getProperty("browser"));
		hp = new homePage(driver);
		rp = hp.navigateToRegisterPage();
	}

	@Test(priority = 1)
	public void VerifyregisteringAnAccountwithManadatoryFiled() {
		asp = rp.RegisterWithMandatortField(dataProp.getProperty("FirstName"), dataProp.getProperty("LastName"),
				utilities.generateEmailwithTimeStamp(), dataProp.getProperty("TelePhone"),
				dataProp.getProperty("password"), dataProp.getProperty("password"));

		Assert.assertEquals(asp.retrieveAccountSucessPageHeading(), dataProp.getProperty("actualTextAccountcreated"),
				"Excepted result is not matched");
		asp.clickingContinuebutton();

	}

	@Test(priority = 2)
	public void VerifyregisteringAnAccountwithallFiled() {
		asp = rp.RegisterwithAllMandatorfield(dataProp.getProperty("FirstName"), dataProp.getProperty("LastName"),
				utilities.generateEmailwithTimeStamp(), dataProp.getProperty("TelePhone"),
				dataProp.getProperty("password"), dataProp.getProperty("password"));

		Assert.assertEquals(asp.retrieveAccountSucessPageHeading(), dataProp.getProperty("actualTextAccountcreated"),
				"Excepted result is not matched");
		asp.clickingContinuebutton();

	}

	@Test(priority = 3)
	public void VerifyregisteringAccountwithExistingEmail() {
		asp = rp.RegisterwithAllMandatorfield(dataProp.getProperty("FirstName"), dataProp.getProperty("LastName"),
				prop.getProperty("ValidEmail"), dataProp.getProperty("TelePhone"), dataProp.getProperty("password"),
				dataProp.getProperty("password"));
		Assert.assertEquals(rp.retreiveDuplicateEmailWarningMessage(), dataProp.getProperty("ValidEmailAcualText"),
				"Excepted result is not matched");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() throws InterruptedException {
		Thread.sleep(4000);
		rp.clickingSubmitButton();
		Assert.assertEquals(rp.retrevieprivarypolicyWarningText(), dataProp.getProperty("ActualValidPrivarytext"),
				"Message is not matching");
		Assert.assertEquals(rp.retreviefirstNameWarningText(), dataProp.getProperty("FirstNameValidText"),
				"Not matched");
		Assert.assertEquals(rp.retrevielastNameWarningText(), dataProp.getProperty("LastNameValidText"), "Not Matched");
		Assert.assertEquals(rp.retrevie_emailWarningText(), dataProp.getProperty("EmailValidText"), "Not matched");
		String Telephonetext = rp.retrevietelephoneWarningText();
		Assert.assertEquals(Telephonetext, dataProp.getProperty("TelephoneValidText"), "Not Macthed");
		Assert.assertEquals(rp.retreviepasswordWarningText(), dataProp.getProperty("passwordValidText"), "Not Matched");

	}
}
