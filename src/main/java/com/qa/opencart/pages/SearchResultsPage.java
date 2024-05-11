package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchBtn = By.id("button-search");
	private By sortByDropDown = By.id("input-sort");
	private By showDropDown = By.id("input-limit");
	private By productList = By.cssSelector("div.product-thumb");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getSearchResultsPageTitle() {
		String title = eleUtil.getTitle();
		System.out.println("search page title is :" + title);
		return title;
	}

	public int getSearchProductCount() {
		TimeUtil.defaultAvgTime();
		return eleUtil.getElements(productList).size();
	}

	public ProductInfoPage selectProduct(String productName){
		By productLink = By.partialLinkText(productName);
		System.out.println("Searching for : " + productName);

		TimeUtil.defaultShortTime();

		eleUtil.click(productLink);
		TimeUtil.defaultAvgTime();

		return new ProductInfoPage(driver);
	}

}
