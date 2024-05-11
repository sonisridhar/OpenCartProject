package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("emailId"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void accPageTitleTest() {
		String title = accPage.getAccountsPageTitle();
		Assert.assertEquals(title, "My Account");
	}

	@Test(priority=2)
	public void accPageURLTest() {
		String url = accPage.getAccountsPageURL();
		Assert.assertTrue(url.contains("account/account"), AppError.URL_NOT_FOUND);
	}
	
	@Test(priority=6)
	public void searchProductTest() {
		seaResPage= accPage.doSearch("macbook");
		Assert.assertTrue(seaResPage.getSearchResultsPageTitle().contains("Search"), AppError.TITLE_NOT_FOUND);
		//Assert.assertEquals(seaResPage.getSearchResultsPageTitle(), "Search - macbook");
	}
	
	@Test(priority = 3)
	public void accPageHeadersTest() {
		accPage.getAccountsPageHeaderList();
	}
	
	@Test(priority = 4)
	public void verifyAccPageLinksCountTest() {
		int actLinksCount = accPage.accPageLink_CountVerification();
		Assert.assertEquals(actLinksCount, 13,AppError.AccPage_LINKS_COUNTS);
		
	}
	
	@Test(priority = 5)
	public void verifyAccPageLinksTest() {
		boolean actLinks = accPage.getLinkVerification();
		Assert.assertTrue(actLinks, AppError.AccPage_LINKS_ORDER);
		
	}
	
	
	

}
