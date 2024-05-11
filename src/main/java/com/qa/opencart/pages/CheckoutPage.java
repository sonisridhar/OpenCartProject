package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CheckoutPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. Private By locator
	
	private By header = By.tagName("h1");
	
	//2. Public constructor()
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. public methods
	public String getCheckOutPageHeader() {
		return eleUtil.doGetElementText(header);
	}

}
