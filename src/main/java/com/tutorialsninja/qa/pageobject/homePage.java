package com.tutorialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
	WebDriver driver;

	public homePage(WebDriver driver) {
		this.driver = driver; // homepage.class
		PageFactory.initElements(driver, this);
	}

	// object
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOptions;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBox;

	@FindBy(xpath = "(//div[@id='search'])/descendant::button")
	private WebElement searchboxClick;

	// Action

	public void Enteringvaluesearchbox(String validproduct) {
		searchBox.sendKeys(validproduct);

	}

	public Searchpage clickingSearchboxbutton() {
		searchboxClick.click();
		return new Searchpage(driver);
	}

	public void clickMyAccount() {
		myAccountDropMenu.click();

	}

	public LoginPage clickLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginpage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}

	public Registerpage clickRegisterOption() {
		registerOptions.click();
		return new Registerpage(driver);
	}
	
	public Registerpage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOptions.click();
		return new Registerpage(driver);
	}
}
