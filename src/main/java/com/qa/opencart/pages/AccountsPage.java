package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By myAccPageLinks = By.xpath("//aside[@id='column-right']//a");
	private By headersList = By.xpath("//div[@id='content']//h2");
	private By searchField = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']//button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String title = eleUtil.getTitle();
		System.out.println("Acc page title is :" + title);
		return title;
	}

	public String getAccountsPageURL() {
		String url = eleUtil.getCurrentUrl();
		System.out.println("Acc page URL is :" + url);
		return url;
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Searching for :" + searchKey);
		eleUtil.doSendKeys(searchField, searchKey);
		TimeUtil.defaultShortTime();
		eleUtil.click(searchIcon);
		return new SearchResultsPage(driver);

	}

	public void getAccountsPageHeaderList() {
		List<WebElement> headersEleList = eleUtil.getElements(headersList);
		List<String> myHeaderList = new ArrayList<String>();
		for (WebElement e : headersEleList) {
			String headers = e.getText();
			// myHeaderList.add(headers);
			System.out.println(headers);
		}
		// return myHeaderList;
	}

	/**
	 * this will fetch the data from acc page
	 * 
	 */
	public List<String> getAccPageLinks() {
		List<WebElement> myLinks = eleUtil.getElements(myAccPageLinks);
		List<String> accPageLinks = new ArrayList<String>();
		for (WebElement e : myLinks) {
			String links = e.getText();
			accPageLinks.add(links);
		}
		return accPageLinks;
	}

	/**
	 * hard coded value of all the links in left side panel of acc page are here
	 * 
	 */
	public ArrayList<String> getAllLink() {

		ArrayList<String> leftSideLinks = new ArrayList<String>(Arrays.asList("My Account", "Edit Account", "Password",
				"Address Book", "Wish List", "Order History", "Downloads", "Recurring payments", "Reward Points",
				"Returns", "Transactions", "Newsletter", "Logout"));
		System.out.println("Size of acc page left side links are :" + leftSideLinks);
		return leftSideLinks;
	}

	/**
	 * This method returns the number of links available in the left side panel
	 * @return count of left side links
	 */
	public int accPageLink_CountVerification() {
		return getAllLink().size();
	}

	/**
	 * This method compares the data from web page and the data we provided manually
	 * @return the data we provide same as the data mentioned in acc page
	 */
	public boolean getLinkVerification() {
		boolean flag = getAccPageLinks().containsAll(getAllLink());
		return flag;
	}

}
