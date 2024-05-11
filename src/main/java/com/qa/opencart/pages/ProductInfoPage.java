package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private Map<String, String> productMap = new HashMap<String, String>();
	
	//1.private By locator
	private By productHeader = By.tagName("h1");
	private By imageList = By.xpath("//div[@id ='content']//ul[@class='thumbnails']//li");
	private By productQuantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]");
	private By shoppingCartLink = By.linkText("shopping cart");
	private By successMsg = By.xpath("//button[@data-dismiss='alert']//parent::div");
	
	
	//2.public constructor
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.public methods
	public String getProductHeader() {
		String header = eleUtil.doGetElementText(productHeader);
		System.out.println("product header : "+header);
		return header;
	}
	
	public int getImageCount() {
		List<WebElement>productIimages =  eleUtil.getElements(imageList);
		 int imageCount =productIimages.size();
		 return imageCount;
	}
	
	private void getProductMetaDataDetails() {
		List<WebElement>productdetails =  eleUtil.getElements(productMetaData);
		for(WebElement e: productdetails) {
			String details = e.getText();
			System.out.println(details);
			String metaDataKey = details.split(":")[0].trim();
			String metaDataValue = details.split(":")[1].trim();
			productMap.put(metaDataKey, metaDataValue);
		}
	}
	
	
	private void getProductPriceDataDetails() {
		List<WebElement>priceDetails =  eleUtil.getElements(productPriceData);
		String productPrice []= priceDetails.get(0).getText().split("Ex");
		String price = productPrice[0].trim();
		System.out.println("Price is : "+price);
		String exTaxPrice = productPrice[1].split(":")[1].trim();
		System.out.println("Excluding tax is : "+exTaxPrice);
		productMap.put("productPrice", price);
		productMap.put("extaxPrice", exTaxPrice);
	}
	
	public Map<String, String> getProductInfo() {
		productMap.put("header", getProductHeader());
		productMap.put("imagesCount", String.valueOf(getImageCount()));
		getProductMetaDataDetails();
		getProductPriceDataDetails();
		return productMap;
	}
	
	public String getSuccessMsgInAddingProduct() {
		eleUtil.getElement(productQuantity).clear();
		eleUtil.doSendKeys(productQuantity,"1");
		eleUtil.click(addToCartBtn);
		TimeUtil.applyTime(1);
		String cartSuccessMsg = eleUtil.doGetElementText(successMsg);
		System.out.println("Success message after adding product to cart : "+cartSuccessMsg);
		return cartSuccessMsg;
	}
	
	public ProductCartPage getProductAddToCart() {
		getSuccessMsgInAddingProduct();
		eleUtil.click(shoppingCartLink);
		return new ProductCartPage(driver);
	}

}
