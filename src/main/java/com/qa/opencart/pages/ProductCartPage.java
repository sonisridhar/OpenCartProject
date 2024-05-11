package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductCartPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	

	// 1. private By locator

	private By totalPriceInAddToCartPage = By
			.xpath("(//div[@class='table-responsive']//tbody//td[@class='text-right'])[2]");
	private By couponCodeLink = By.xpath("//a[@data-toggle='collapse' and contains(text(), 'Coupon')]");
	private By enterCouponCode = By.name("coupon");
	private By applyCouponBtn = By.id("button-coupon");
	private By shippingAndTaxesLink = By.xpath("//a[@data-toggle='collapse' and contains(text(), 'Shipping')]");
	private By countryDropDown = By.id("input-country");
	private By stateDropDown = By.id("input-zone");
	private By postalCode = By.name("postcode");
	private By getQuoteBtn = By.id("button-quote");
	private By shippingRdoBtn = By.name("shipping_method");
	private By applyShippingChargeRdoBtn = By.id("button-shipping");
	private By giftCodeLink = By.xpath("//a[@data-toggle='collapse' and contains(text(), 'Gift')]");
	private By giftCodeTextBar = By.id("input-voucher");
	private By applyGiftBtn = By.id("button-voucher");
	private By checkoutLink = By.linkText("Checkout");
	private By updateIcon = By.xpath("//button[@data-original-title='Update']");
	private By quantityTextBar = By.xpath("//input[contains(@name,'quantity')]");
	private By successMsg = By.xpath("(//button[@data-dismiss='alert']//parent::div)[1]");

	// 2. public constructor

	public ProductCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. public methods

	public boolean getProductNameInCartPage(String productName) {
		By productNameLink_addToCartPage = By.partialLinkText(productName);
		return eleUtil.isElementDisplayed(productNameLink_addToCartPage);
	}

	public boolean getModelInCartPage(String ModelNo) {
		By productModelLink_addToCartPage = By.partialLinkText(ModelNo);
		return eleUtil.isElementDisplayed(productModelLink_addToCartPage);
	}
	
	public String getProductQuantityUpdateinCartPage(int prodQuantity) {
		eleUtil.isElementDisplayed(quantityTextBar);
		eleUtil.doSendKeys(quantityTextBar, String.valueOf(prodQuantity));
		eleUtil.click(updateIcon);
		String cartSuccessMsg = eleUtil.doGetElementText(successMsg);
		System.out.println("Success message after adding product to cart : "+cartSuccessMsg);
		return cartSuccessMsg;
		
	}

	public String getTotalPriceInCartPage() {
		String totalPrice = eleUtil.doGetElementText(totalPriceInAddToCartPage);
		System.out.println(totalPrice);
		return totalPrice;
	}
		
	private void getCouponCodeApply(String coupon) {
		TimeUtil.applyTime(3);
		eleUtil.click(couponCodeLink);
		eleUtil.isElementDisplayed(enterCouponCode);
		eleUtil.doSendKeys(enterCouponCode, coupon);
		eleUtil.click(applyCouponBtn);
	}
	
	private void getEstimateShippingAndTaxes(String Country, String state, String zipCode) {
		eleUtil.click(shippingAndTaxesLink);
		eleUtil.doSelectByVisibleText(countryDropDown,Country);
		eleUtil.doSelectByVisibleText(stateDropDown, state);
		eleUtil.doSendKeys(postalCode, zipCode);
		eleUtil.click(getQuoteBtn);
		eleUtil.isElementDisplayed(shippingRdoBtn);
		eleUtil.click(shippingRdoBtn);
		eleUtil.click(applyShippingChargeRdoBtn);
	}
	
	private void getGiftVoucherApply(String giftVoucher) {
		eleUtil.click(giftCodeLink);
		eleUtil.doSendKeys(giftCodeTextBar, giftVoucher);
		eleUtil.click(applyGiftBtn);
	}
	

	public CheckoutPage getCouponAndAddressAndCheckoutPage(String coupon, String Country, String state, String zipCode, String giftVoucher) {
		getCouponCodeApply(coupon);
		getEstimateShippingAndTaxes(Country, state, zipCode);
		getGiftVoucherApply(giftVoucher);
		eleUtil.click(checkoutLink);
		return new CheckoutPage(driver);

	}

}
