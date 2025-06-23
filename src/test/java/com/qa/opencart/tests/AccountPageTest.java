package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class AccountPageTest extends BaseTest {
	
	@BeforeMethod
	public void accSetUp()
	{
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountpageTitleTest() 
	{
		Assert.assertEquals(accountPage.getAccountPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void isLogoutLinkExistTest()
	{
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExist()
	{
		Assert.assertTrue(accountPage.isSearchFieldExist());
	}
	
	@Test
	public void accPageHeadersCountTest()
	{
		List<String> accPageHeadersList = accountPage.getAccoutHeaders();
		Assert.assertEquals(accPageHeadersList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersTest()
	{
		List<String> accPageHeadersList = accountPage.getAccoutHeaders();
		System.out.println(accPageHeadersList);
		Assert.assertEquals(accPageHeadersList, AppConstants.ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest()
	{
		searchResultPage = accountPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String actualProductHeader = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actualProductHeader, "MacBook Pro");
	}
	
   
}
