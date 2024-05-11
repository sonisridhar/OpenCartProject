package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void infoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("emailId"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductHeader() {
		return new Object[][] {
			{"samsung","Samsung Galaxy Tab"},
			{"macbook","MacBook Air"},
			{"canon","Canon EOS 5D"},
			{"nikon","Nikon D300"}
		};
	}
	
	@Test(dataProvider = "getProductHeader")
	public void productHeaderTest(String productKey,String productName) throws InterruptedException {
		seaResPage = accPage.doSearch(productKey);
		infoPage = seaResPage.selectProduct(productName);
		String prodHeader =infoPage.getProductHeader();
		Assert.assertTrue(prodHeader.contains(productName));
	}
	
	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"samsung","Samsung Galaxy Tab",7},
			{"macbook","MacBook Air",4},
			{"canon","Canon EOS 5D",3},
			{"nikon","Nikon D300",5},
		};
	}
	
	@Test(dataProvider = "getProductImageData")
	public void productImageCountTest(String productKey, String productName, int imgCount) throws InterruptedException {
		seaResPage = accPage.doSearch(productKey);
		infoPage = seaResPage.selectProduct(productName);
		int imageCount = infoPage.getImageCount();
		Assert.assertEquals(imageCount,imgCount);
	}

	@Test
	public void selectProductTest() throws InterruptedException {
		seaResPage = accPage.doSearch("samsung");
		infoPage = seaResPage.selectProduct("Samsung Galaxy Tab");
		Map<String, String> actDetails = infoPage.getProductInfo();
		verify.assertEquals(actDetails.get("Product Code"), "SAM1");
		verify.assertEquals(actDetails.get("Reward Points"), "1000");
		verify.assertEquals(actDetails.get("Availability"), "Pre-Order");
		verify.assertEquals(actDetails.get("productPrice"), "$241.99");
		verify.assertEquals(actDetails.get("extaxPrice"), "$241.99");
		verify.assertTrue(actDetails.get("header").contains("Samsung Galaxy Tab"), "Header not visible");
		verify.assertEquals(actDetails.get("imagesCount"), "7");

	}

	@Test
	public void addProductToCartTest() throws InterruptedException {
		seaResPage = accPage.doSearch("samsung");
		infoPage = seaResPage.selectProduct("Samsung Galaxy Tab");
		String successMsg = infoPage.getSuccessMsgInAddingProduct();
		verify.assertTrue(successMsg.contains("Success: You have added Samsung Galaxy Tab 10.1 to your shopping cart"), 
				"Success Message is not as expected");
		cartPage = infoPage.getProductAddToCart();
		verify.assertTrue(cartPage.getProductNameInCartPage("Samsung Galaxy Tab"));

	}

}
