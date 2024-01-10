package com.tutorialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accountSuccessPage {
	WebDriver driver;
	public accountSuccessPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(xpath = "(//div[@id='content'])/h1")
	private WebElement successTextContent;
	
	@FindBy(linkText = "Continue")
	private WebElement continueButton;
	
	public String retrieveAccountSucessPageHeading() {
		String Content=  successTextContent.getText();
		return Content;
	}
	
	public void clickingContinuebutton() {
		continueButton.click();
	}

}
