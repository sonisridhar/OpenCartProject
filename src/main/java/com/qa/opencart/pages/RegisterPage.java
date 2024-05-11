package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By fName = By.name("firstname");
	private By lName = By.name("lastname");
	private By mailId = By.id("input-email");
	private By mob = By.name("telephone");
	private By pwd = By.id("input-password");
	private By pwdConfrm = By.id("input-confirm");
	private By subYes = By.xpath("//input[@type='radio' and @value='1']");
	private By subNo = By.xpath("//input[@type='radio' and @value='0']");
	private By privacyChkBox = By.xpath("//input[@type='checkbox']");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By successMsg = By.xpath("//div[@id='content']//h1");
	private By continueLink = By.linkText("Continue"); 
	private By logoutLink = By.xpath("//aside[@id='column-right']//a[text()='Logout']");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public String getRegisterPageTitle() {
		String title = eleUtil.getTitle();
		System.out.println("Reg page title is :" + title);
		return title;
	}

	public String getRegisterPageURL() {
		String url = eleUtil.getCurrentUrl();
		System.out.println("Reg page URL is :" + url);
		return url;
	}
	public String getRandmMailID() {
		return "soni_"+System.currentTimeMillis()+"@opencart.com" ;
	}
	
	public boolean doRegister(String fn, String ln, String tel, String mailId,String passwd, String sub) {
		
		TimeUtil.defaultAvgTime();
		eleUtil.isElementDisplayed(fName);
		eleUtil.doSendKeys(fName, fn);
		eleUtil.doSendKeys(lName, ln);
		eleUtil.doSendKeys(mob, tel);
		String emailId = getRandmMailID();
		eleUtil.doSendKeys(this.mailId,emailId );
		eleUtil.doSendKeys(pwd, passwd);
		eleUtil.doSendKeys(pwdConfrm, passwd);
		
		if(sub.equals("yes"))
			eleUtil.click(subYes);
		else
			eleUtil.click(subNo);
		
		eleUtil.isElementDisplayed(privacyChkBox);
		eleUtil.click(privacyChkBox);
		eleUtil.isElementDisplayed(continueBtn);
		eleUtil.click(continueBtn);
		TimeUtil.defaultAvgTime();
		String regSuccMsg = eleUtil.doGetElementText(successMsg);
		if(regSuccMsg.equals(AppConstants.USER_REG_SUCCESS_MSG)) {
			TimeUtil.defaultAvgTime();
			eleUtil.isElementDisplayed(logoutLink);
			eleUtil.click(logoutLink);
			eleUtil.click(registerLink);
			return true;
		}
			return false;
		
	}
	
	

}
