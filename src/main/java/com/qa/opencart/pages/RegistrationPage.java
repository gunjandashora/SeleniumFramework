package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastname  = By.id("input-lastname");
	private By email     = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password  = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[@class='radio-inline'] [position()=1]/input[@type='radio']");
	private By subscribeNo  = By.xpath("//label[@class='radio-inline'] [position()=2]/input[@type='radio']");
	
	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");
	
	private By sucessMsg = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver)
    {
   	   this.driver = driver;
   	   eleUtil = new ElementUtil(this.driver);
    }
	
	public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password,
			                     String confirmPassword, String subscribe)
	{
	  eleUtil.waitForVisibilityOfElement(this.firstName,AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);
	  eleUtil.doSendKeys(this.lastname, lastName);
	  eleUtil.doSendKeys(this.email, email);
	  eleUtil.doSendKeys(this.telephone, telephone);
	  eleUtil.doSendKeys(this.password, password);
	  eleUtil.doSendKeys(this.confirmPassword, confirmPassword);
	  
	  if(subscribe.equalsIgnoreCase("yes"))
	  {
		  eleUtil.doClick(subscribeYes);
	  }
	  
	  else
	  {
		  eleUtil.doClick(subscribeNo);
	  }
	  
	  eleUtil.doClick(agreeCheckbox);
	  eleUtil.doClick(continueButton);
	  
	 String sucessMesg = eleUtil.waitForVisibilityOfElement(sucessMsg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
	 System.out.println(sucessMesg);
	 
	 if(sucessMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESG))
	 {
		 eleUtil.doClick(logoutLink);
		 eleUtil.doClick(registerLink);
		 
		 return true;
	 }
	 else
	 {
		 return false;
	 }
	}
 
}
