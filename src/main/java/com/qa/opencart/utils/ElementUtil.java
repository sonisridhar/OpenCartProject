package com.qa.opencart.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	
private WebDriver driver;
private JavaScriptUtil jsUtil;

	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public WebElement getElement(By locator) {
		WebElement element = null;
				try {
					element =  driver.findElement(locator);
					checkHighlight(element);
					
				}catch(NoSuchElementException e) {
					System.out.println("Element is not present on the page");
				}
		return element;
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	@Step("entering values: {1} in element :{0}")
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear(); 
		getElement(locator).sendKeys(value);
	}
	
	public void click(By locator) {
		getElement(locator).click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	@Step("Checking the webElement {0} is present")
	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}
	
	public String getAttributeValue(By locator, String Att_Value) {
		return getElement(locator).getAttribute(Att_Value);
	}
	
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void checkHighlight(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
	}

}
