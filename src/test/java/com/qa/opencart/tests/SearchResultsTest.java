package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsTest extends BaseTest {
	
	
	@BeforeClass
	public void searchResultSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("emailId"), prop.getProperty("password"));
	}
	
	@Test
	public void searchResultTest() {
		seaResPage = accPage.doSearch("Samsung");
		Assert.assertEquals(seaResPage.getSearchProductCount(), 2);
		
	}
	@Test
	public void selectProductTest() {
		infoPage = seaResPage.selectProduct("Samsung Galaxy Tab");
		
	}
	
	

}
