package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductsResultPageTest extends BaseTest {
	
	@BeforeMethod
	public void productInfoSetUp()
	{
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1}
			
		};
	}
	
	
	@Test(dataProvider ="getSearchData")
	public void productImagesTest(String searchKey, String productName, int imageCount)
	{
		searchResultPage = accountPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	}

	@Test
	public void productInfoTest()
	{
		searchResultPage = accountPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap= productInfoPage.getProductDetails();
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
		
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
}
