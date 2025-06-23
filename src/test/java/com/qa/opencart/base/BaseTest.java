package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	protected WebDriver driver;
	DriverFactory df;
	protected Properties prop;
    protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registerPage;
	
	protected SoftAssert softAssert;
	
	@BeforeMethod
	public void setUp()
	{
		df= new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	

}
