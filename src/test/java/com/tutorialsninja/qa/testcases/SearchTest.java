package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseTest.BaseLogin;
import com.tutorialsninja.qa.pageobject.Searchpage;
import com.tutorialsninja.qa.pageobject.homePage;

public class SearchTest extends BaseLogin {
	homePage hp;
	public WebDriver driver;
	Searchpage sp;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndOpenApplicationOnURL(prop.getProperty("browser"));
		hp = new homePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	public void verifySearchwithValidProduct() {

		hp.Enteringvaluesearchbox(dataProp.getProperty("validproductName"));
		sp = hp.clickingSearchboxbutton();

		Assert.assertTrue(sp.displayStatusofValidproduct(), "valid HP is not displayed");
		// Assert.assertEquals(actualtextlaptopName,
		// dataProp.getProperty("ValidProductNameText"), "Not Matched");

	}

	@Test(priority = 2)
	public void verifySearchwithInvaidProduct() {

		hp.Enteringvaluesearchbox(dataProp.getProperty("InvalidProductName"));
		sp = hp.clickingSearchboxbutton();
		Assert.assertEquals(sp.displayStatusOfInvalidProduct(), dataProp.getProperty("InvalidProductValidation"),
				"No Matched");

	}

	@Test(priority = 1, dependsOnMethods = { "verifySearchwithInvaidProduct", "verifySearchwithValidProduct" })
	public void verifySearchwithoutanyProduct() {

		sp = hp.clickingSearchboxbutton();
		String ActualInvaildtext = sp.displayStatusOfInvalidProduct();
		Assert.assertEquals(ActualInvaildtext, dataProp.getProperty("InvalidProductValidation"), "No Matched");

	}

}
