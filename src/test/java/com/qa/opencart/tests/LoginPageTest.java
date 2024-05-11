package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design open cart login page")
@Story("US 101: Design login page feature for open cart application")
public class LoginPageTest extends BaseTest {

//	@BeforeClass
//	public void loginPageSetUp() {
//		if(prop.getProperty("registerAccount").equals("yes")) {
//			regPage = loginPage.navigateToRegistrationPage();
//			//regPage.doRegister("Soni", "Srid", "7810345500", "Misti@123", "Yes");
//		}
//	}
	@Description("Checking login page title....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, "Account Login");
	}

	@Description("Checking login page URL....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String url = loginPage.getLoginPageURL();
		Assert.assertTrue(url.contains("account/login"), "URL not corrected");
	}

	@Description("Checking forgot password link....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgtPwdExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());

	}

	
	@Description("Checking user is able to login....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginUserTest() {
		accPage = loginPage.doLogin(prop.getProperty("emailId"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), "My Account");
	}

}
