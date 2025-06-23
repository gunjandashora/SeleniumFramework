package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locator or Object repository
	
     private By userName = By.id("input-email");
     private By password = By.id("input-password");
     private By loginBtn = By.xpath("//input[@value='Login']");
     private By forgotPwdLink = By.linkText("Forgotten Password1");
     private By logo = By.xpath("//a[text()='Qafox.com']");
     
     private By registerLink = By.linkText("Register");
     //page constructor
     
     public LoginPage(WebDriver driver)
     {
    	 this.driver = driver;
    	 eleUtil = new ElementUtil(this.driver);
     }
     
     //page actions/ behavior
     
     public String getLoginPageTitle()
     {
    	
    	 String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
    	 System.out.println("Login Page title is:" + title);
    	 return title;
     }
     
     public String getLoginPageUrl()
     {
    	 String url = eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
    	 System.out.println("Login page url is" +url);
    	 return url;
     }
     
     public boolean isForgotPwdLinkExist()
     {
    	return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
    	
     }
     
     public boolean IsLogoExist()
     {
    	 return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
     }
     
     public AccountPage doLogin(String username, String pwd)
     {
    	 eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
    	 eleUtil.doSendKeys(password, pwd);
    	 eleUtil.doClick(loginBtn);
    	 
    	 return new AccountPage(driver);
     }
     
     public RegistrationPage navigateToRegisterPage()
     {
    	 eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
    	 return new RegistrationPage(driver);
     }

}
