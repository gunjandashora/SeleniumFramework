package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void loginPageURLTest()
	{
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority=3)
	public void forgotPwdLinkexistLink()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	public void appLogoExist()
	{
		Assert.assertTrue(loginPage.IsLogoExist());
	}
	
	@Test(priority=5)
	public void loginTest() 
	{
	  accountPage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	  Assert.assertTrue(accountPage.isLogoutLinkExist());
	}


}
