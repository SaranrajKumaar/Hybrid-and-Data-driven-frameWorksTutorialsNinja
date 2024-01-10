package com.tutorialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	

	@FindBy(id = "input-email")
	private WebElement EmailAddressField;
	@FindBy(id = "input-password")
	private WebElement passwordAddressField;
	@FindBy(css = "input[type='submit']")
	private WebElement loginButton;
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotmatchingWarningMessage;
	
	public void enterEmailAddress(String emailText) {
		EmailAddressField.sendKeys(emailText);
	}
	public void enteringPassword(String password) {
		passwordAddressField.sendKeys(password);
	}
	public AccountPage clickLogin() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage login(String emailText,String password) {
		EmailAddressField.sendKeys(emailText);
		passwordAddressField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String getEmailAndPasswordNotMatchedWarningMessage() {
		String warningText= emailPasswordNotmatchingWarningMessage.getText();
		return warningText;
	}
}
