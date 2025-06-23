package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeMethod
	public void regSetUp()
	{
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getrandomEmailId()
	{
		return "testAutomation"+System.currentTimeMillis()+"@openCart.com";
		
		//return "testAutomation"+ UUID.randomUUID()+"openCart.com";
	}
	
	/*@DataProvider
	public Object[][] getUserRegData()
	{
		return new Object[][]
				{
			       {"Peter", "Marsh", "99999999989", "Peter@123", "Peter@123", "yes"},
			       {"Michele", "Stroba","99459999989", "Michele@123", "Michele@123", "yes"},
			       {"Tony", "Phan", "99995999989", "Phan@123", "Phan@123", "yes"}
				};
	}*/
	
	@DataProvider
	public Object[][] getUserRegTestExcelData()
	{
		Object regData[][]= ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String confirmPassword,String subscribe)
	{
	 boolean isRegDone = registerPage.userRegistration(firstName, lastName, getrandomEmailId(),telephone, password, confirmPassword, subscribe);
	 
	 Assert.assertTrue(isRegDone);
	}
	
      
}
