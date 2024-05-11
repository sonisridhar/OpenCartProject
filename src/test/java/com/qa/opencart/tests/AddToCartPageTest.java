package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AddToCartPageTest extends BaseTest {
	
	@BeforeClass
	public void addToCartSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("addTocart_EmailId"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyProductDetailsInCartPageTest() throws InterruptedException {
		seaResPage = accPage.doSearch("macbook");
		infoPage = seaResPage.selectProduct("MacBook Pro");
		cartPage = infoPage.getProductAddToCart();
		verify.assertTrue(cartPage.getProductNameInCartPage("MacBook Pro"),"product selected in search page is not same as cart page");
		//verify.assertTrue(cartPage.getModelInCartPage("Product 18"),"product model selected in search page is not same as cart page");
		String totalPrice = cartPage.getTotalPriceInCartPage();
		verify.assertEquals(totalPrice,"$2000.00","price mismatch in searchPage and cart page");
		
	}
	
	@Test(priority=2)
	public void updateProductQntyTest() throws InterruptedException {
		seaResPage = accPage.doSearch("macbook");
		infoPage = seaResPage.selectProduct("MacBook Pro");
		cartPage = infoPage.getProductAddToCart();
		String updateSuccMsg = cartPage.getProductQuantityUpdateinCartPage(3);
		System.out.println(updateSuccMsg);
		System.out.println("Success: You have modified your shopping cart!");
		Assert.assertTrue(updateSuccMsg.contains(AppConstants.CARTPAGE_SUCCESSMSG));
		
	}
	
	@Test(priority=3)
	public void updateCouponsAndAddressTest() throws InterruptedException {
		seaResPage = accPage.doSearch("macbook");
		infoPage = seaResPage.selectProduct("MacBook Pro");
		cartPage = infoPage.getProductAddToCart();
		checkoutPage = cartPage.getCouponAndAddressAndCheckoutPage("test%coupon", "India", "Orissa", "754103", "test#coupons");
		verify.assertEquals(checkoutPage.getCheckOutPageHeader(), "Checkout",AppError.HEADER_NOT_FOUND);
	}
}
