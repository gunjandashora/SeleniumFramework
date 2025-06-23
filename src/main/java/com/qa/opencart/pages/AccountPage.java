package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//OR
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content>h2");
	
	
	
	 public AccountPage(WebDriver driver)
     {
    	 this.driver = driver;
    	 eleUtil = new ElementUtil(this.driver);
     }
	 
	 //page actions
	 
	  public String getAccountPageTitle()
	     {
	    	
	    	 String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
	    	 System.out.println("Account Page title is:" + title);
	    	 return title;
	     }
	 
	 
	 
	 
	 public boolean isLogoutLinkExist()
	 {
		return  eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	 }
	 
	 public void logout()
	 {
		 if(isLogoutLinkExist())
		 {
			 eleUtil.doClick(logoutLink);
			 
		 }
	 }
	 
	 public boolean isSearchFieldExist()
	 {
		 return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	 }
	 
	 public List<String> getAccoutHeaders()
	 {
		List<WebElement> headersList=  eleUtil.waitForVisibilityOfAllElement(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e: headersList)
		{
			String text = e.getText();
			headersValList.add(text);
		}
		
		return headersValList;
	 }
	 
	 public SearchResultPage doSearch(String searchKey)
	 {
		 eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		 eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		 eleUtil.doClick(searchIcon);
		 return new SearchResultPage(driver);
	 }
}
