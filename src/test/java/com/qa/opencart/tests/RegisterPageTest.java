package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	
	@BeforeClass
	public void regBaseSetUp() {
		regPage = loginPage.navigateToRegistrationPage();
	}
	
	@DataProvider
	public Object[][] userData() {
		return new Object[][] {
			{"Soni", "Srid", "8610345568",regPage.getRandmMailID(), "Misti@123", "Yes"},
			{"Ara", "Adu", "8939783844",regPage.getRandmMailID(), "Misti@123", "No"},
			{"Asu", "Soni", "9668220333",regPage.getRandmMailID(), "Advick@123", "Yes"}
			
		};
	}
	
	
	@DataProvider
	public Object[][] userDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	
	
	@Test(dataProvider="userData")
	public void regUserTest(String fn, String ln,String mob,String mailId,String pwd,String sub) {
		
		Assert.assertTrue(regPage.doRegister(fn,ln,mob,mailId,pwd,sub));
	}

}
