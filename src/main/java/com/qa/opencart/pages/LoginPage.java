package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.private By locators
	
	private By emailId = By.name("email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2.Public page class const
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);		
	}
	
	//3. Public page class method
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.getTitle();
		System.out.println("login page title is :"+title);
		return title;
	}
	
	@Step("Getting login page URL")
	public String getLoginPageURL() {
		String url = eleUtil.getCurrentUrl();
		System.out.println("login page URL is :"+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("Login with username {0} and password{1}")
	public AccountsPage doLogin(String un, String pwd ) {
		System.out.println("User credentials are :"+un+ " "+pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.click(loginBtn);
		return new AccountsPage(driver);
		
	}
	
	public RegisterPage navigateToRegistrationPage() {
		eleUtil.click(registerLink);
		return new RegisterPage(driver);
	}
	
	

}
