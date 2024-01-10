package com.tutorialsninja.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searchpage {
	 WebDriver driver;

	public Searchpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "HP LP3065")
	WebElement ValidHPproduct;

	@FindBy(xpath = "(//div[@id='content'])/h2/following-sibling::p")
	WebElement inValidProduct;

	public boolean displayStatusofValidproduct() {
		boolean content = ValidHPproduct.isDisplayed();
		return content;
	}

	public String displayStatusOfInvalidProduct() {
		String content = inValidProduct.getText();
		return content;
	}

}
