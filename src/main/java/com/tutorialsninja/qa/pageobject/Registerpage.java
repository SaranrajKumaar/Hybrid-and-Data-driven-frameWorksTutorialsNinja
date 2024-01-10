package com.tutorialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registerpage {
	WebDriver driver;
	
	public Registerpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);	
		
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNamefield;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy (id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(css="input[type='checkbox']")
	private WebElement AgreeCheckboxField;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "(//input[@type='radio'])[2]")
	private WebElement newsLettercheckbox;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privarypolicyWarning;
	
	@FindBy(xpath =" //input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public String retrevie_emailWarningText() {
		String Content =emailWarning.getText();
		return Content;
	}
	
	public String retrevietelephoneWarningText() {
		String Content =telephoneWarning.getText();
		return Content;
	}
	
	public String retreviepasswordWarningText() {
		String Content =passwordWarning.getText();
		return Content;
	}
	
	public String retrevielastNameWarningText() {
		String Content =lastNameWarning.getText();
		return Content;
	}
	
	public String retrevieprivarypolicyWarningText() {
		String Content =privarypolicyWarning.getText();
		return Content;
	}
	public String retreviefirstNameWarningText() {
		String Content =firstNameWarning.getText();
		return Content;
	}
	

	public void enteringFirstname(String fName) {
		 firstNamefield.sendKeys(fName);
	}
	public void enteringLastname(String lName) {
		lastNameField.sendKeys(lName);
	}
	public void enteringEmail(String Email) {
		emailField.sendKeys(Email);
	}
	public void enteringTelephone(String Telephone) {
		telephoneField.sendKeys(Telephone);
	}
	public void enteringPassword(String password) {
		passwordField.sendKeys(password);
	}
	public void enteringConfirmPassword(String cPassword) {
		confirmPasswordField.sendKeys(cPassword);
	}
	
	public void agreeClickCheckBox() {
		AgreeCheckboxField.click();
	}
	
	public accountSuccessPage clickingSubmitButton() {
		submitButton.click();
		return new accountSuccessPage(driver);
	}
	
	public void clickingonNewLetter() {
		newsLettercheckbox.click();
	}
	
	public String retreiveDuplicateEmailWarningMessage() {
		String contentDuplicate=duplicateEmailWarning.getText();
		return contentDuplicate;
	}
	
	public accountSuccessPage RegisterWithMandatortField(String fName,String lName,String Email,String Telephone,String password,String cPassword) {
		firstNamefield.sendKeys(fName);
		lastNameField.sendKeys(lName);
		emailField.sendKeys(Email);
		telephoneField.sendKeys(Telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(cPassword);
		AgreeCheckboxField.click();
		submitButton.click();
		return new accountSuccessPage(driver);
	}
	public accountSuccessPage RegisterwithAllMandatorfield(String fName,String lName,String Email,String Telephone,String password,String cPassword) {
		firstNamefield.sendKeys(fName);
		lastNameField.sendKeys(lName);
		emailField.sendKeys(Email);
		telephoneField.sendKeys(Telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(cPassword);
		newsLettercheckbox.click();
		AgreeCheckboxField.click();
		submitButton.click();
		return new accountSuccessPage(driver);
	}
}
