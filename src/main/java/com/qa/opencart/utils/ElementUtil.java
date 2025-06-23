package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

public class ElementUtil {
	
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public WebElement getElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	public void doSendKeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public String doElementGetText(By locator)
	{
	    return getElement(locator).getText();	
	}
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public int getElementsCount(By locator)
	{
		return getElements(locator).size();
	}
	
	//WAF : capture the text of all the page links and return List<String>
	
	public  List<String> getElementsTextList(By locator)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList)
		{
			String text = e.getText();
			
				eleTextList.add(text);
			
			
		}
		return eleTextList;
		
		
	}
	
	public  List<String> getElementsAttributeList(By locator, String attName)
	{
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttributeList = new ArrayList<String>();
		  for(WebElement e : eleList)
		  {
			  String attValue= e.getDomAttribute(attName);
			  
              eleAttributeList.add(attValue);
		  }
		  
		  return eleAttributeList;
	}
	
	 public  void search(By searchField, By suggesstions, String searchKey, String suggName) throws InterruptedException
	   {
		   //driver.findElement(By.name("q")).sendKeys("Selenium automation");
		   
		   doSendKeys(searchField,searchKey);
			
			Thread.sleep(3000);
			
			
			List<WebElement> suggestionList = getElements(suggesstions);
			System.out.println(suggestionList.size());
			
			for(WebElement e : suggestionList)
			{
				String text = e.getText();
				System.out.println(text);
				
				if(text.contains(suggName))
				{
					e.click();
					break;
				}
			}
			
			
	   }
	 
	 public  boolean checkElementPresent(By locator)
		{
			return getElements(locator).size()==1 ? true:false;
		}
	 
	 
	 //*************Select Drop down utils********************\\
	 
	 
	 private Select createSelect(By locator)
	 {
		 Select select = new Select(getElement(locator));
		 return select;
	 }
	 
	 
	 public  void doSelectByIndex(By locator, int index)
		{
			createSelect(locator).selectByIndex(index);
			
					
		}
		
		public  void doSelectByValue(By locator, String value )
		{
			createSelect(locator).selectByValue(value);;
					
		}
		
		public void doSelectByVisibleText(By locator, String visibeText )
		{
			createSelect(locator).selectByVisibleText(visibeText);;
					
		}
		
		public  void doSelectWithGetOptions(By locator, String value)
		{
			List <WebElement> optionsList= createSelect(locator).getOptions();
			//List <WebElement> optionsList= select.getOptions();
			for(WebElement e: optionsList)
			{
				String text = e.getText();
				System.out.println(text);
				
				if(text.equals(value))
				{
					e.click();
					break;
				}
			}
		}
		
		public  int getDropDownOptionsCount(By locator)
		{
			Select select = new Select(getElement(locator));
			return select.getOptions().size();
		}
		
		public List<String> getDropdownOptions(By locator)
		{
			Select select = new Select(getElement(locator));
			List <WebElement> optionsList= select.getOptions();
			List<String> optionsTextList = new ArrayList<String>();
			for(WebElement e: optionsList)
			{
				String text = e.getText();
				optionsTextList.add(text);
				
			}
			
			return optionsTextList;
		}
		
		 public  void selectValuefromDropDown(By locator, String value)
		    {
			 
			 List<WebElement> countryList=getElements(locator);
			    
			    for (WebElement e: countryList)
			    {
			    	String text = e.getText();
			    	if(text.equals(value))
			    	{
			    		e.click();
			    		break;
			    	}
			    }
		    	
		    }
		 /**
			 * This method is used to select the values from drop down. It can select:
			 * 1. Single selection
			 * 2.Multiple selection
			 * 3. All selection: Please pass "all" as a value to select the values.
			 * @param locator
			 * @param values
			 */
			
		 public  void selectDropdownMultipleValues(By locator,By optionLocator , String...values)
			{
				Select select = new Select(getElement(locator));
				
				if(isDropDownMultiple(locator))
				{
					if(values[0].equalsIgnoreCase("all"))
					{
						List<WebElement> optionList = getElements(optionLocator);
						for(WebElement e: optionList)
						{
							e.click();
						}
					}
					else {
					for(String value:values)
					{
						createSelect(locator).selectByVisibleText(value);
					}
				}
			}
			}
			
			public  boolean isDropDownMultiple(By locator)
			{
				//Select select = new Select(getElement(locator));
				return createSelect(locator).isMultiple()? true: false;
				
			}
			
			
			//*************Actions utils********************\\

			public void twoLevelMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException
			{
				Actions action = new Actions(driver);
				action.moveToElement(getElement(parentMenuLocator)).build().perform();
				Thread.sleep(1000);
				doClick(childMenuLocator);
						
			}
			
			public  void fourLevelMenuHandling(By parentMenuLocator, By ChildMenuLocator, By secondChildMenuLocator, By thirdChildMenuLocator) throws InterruptedException
			{
				Actions action = new Actions(driver);
				
				  doClick(parentMenuLocator);
				  Thread.sleep(1000);
				
				action.moveToElement(getElement(ChildMenuLocator)).build().perform();
				Thread.sleep(1000);
				
				action.moveToElement(getElement(secondChildMenuLocator)).build().perform();
				
				Thread.sleep(1000);
				
				doClick(thirdChildMenuLocator);
				
			}
			
			public  void doActionsSendkeys(By locator, String value)
			{
				Actions act = new Actions(driver);
				act.sendKeys(getElement(locator),value).perform();
				
			}
			
			public  void doActionsClick(By locator)
			{
				Actions act = new Actions(driver);
				act.click(getElement(locator)).perform();
			}
			
			public void doPauseWithsendKeys(By locator, String value)
			{
		        Actions act =new Actions(driver);
				
				char val[]= value.toCharArray();
				
				for(char c: val)
				{
					act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).perform();
				}
			}
			
			/**********************Wait Utils************************************************\
			
			/**
			 * An expectation for checking that an element is present on the DOM of a page. 
			 * This does not necessarily mean that the element is visible.
			 * @param locator
			 * @param timeOut
			 * @param intervalTime
			 * @return
			 */
			
			
			public  WebElement waitForPresenceOfElement(By locator, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				
			}
			
			public  WebElement waitForPresenceOfElement(By locator, int timeOut, int intervalTime)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofSeconds(intervalTime));
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				
			}
			
			/**
			 * An expectation for checking that there is at least one element present on a web page.
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			public  List<WebElement> waitForPresenceOfAllElements(By locator, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
				
			}
			
			
			
			/**
			 * An expectation for checking that an element is present on the DOM of a page and visible.
			 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
			 * @param locator
			 * @param timeOut
			 * @return
			 */
			
			public  WebElement waitForVisibilityOfElement(By locator, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				
			}
			
			public List<WebElement> waitForVisibilityOfAllElement(By locator, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
				
			}
			
			public  void doClickWithWait(By locator, int timeout)
			{
				waitForVisibilityOfElement( locator,  timeout);
			}
		    
			public void doSendKeysWithWait(By locator, String value, int timeout)
			{
				waitForVisibilityOfElement(locator,timeout).sendKeys(value);;
			}
			
			
			public  String waitForTitleContains(String titleFraction, int timeout)
			{

				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
				try {
				
				if(wait.until(ExpectedConditions.titleContains(titleFraction)))
				{
					return driver.getTitle();
				}
				}
				catch(TimeoutException e)
				{
					System.out.println(titleFraction +" " + "title value is not present");
					
				}
				return null;
				
				
			}

			public  String waitForTitleIs(String title, int timeout)
			{

				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
				try {
				
				if(wait.until(ExpectedConditions.titleIs(title)))
				{
					return driver.getTitle();
				}
				}
				catch(TimeoutException e)
				{
					System.out.println(title +" " + "title value is not present");
					
				}
				return null;
				
				
			}
			
			public  String waitForUrlContains(String UrlFraction, int timeout)
			{

				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
				try {
				
				if(wait.until(ExpectedConditions.urlContains(UrlFraction)))
				{
					return driver.getCurrentUrl();
				}
				}
				catch(TimeoutException e)
				{
					System.out.println(UrlFraction +" " + "url value is not present");
					
				}
				return null;
				
				
			}
			
			public  String waitForUrlToBe(String Url, int timeout)
			{

				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
				try {
				
				if(wait.until(ExpectedConditions.urlContains(Url)))
				{
					return driver.getCurrentUrl();
				}
				}
				catch(TimeoutException e)
				{
					System.out.println(Url +" " + "url value is not present");
					
				}
				return null;
			}
			
			public  Alert waitForJSAlert(int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.alertIsPresent());
				
			}
			
			public void acceptJSAlert(int timeOut)
			{
				waitForJSAlert(timeOut).accept();
			}
		     
			public  void dismissJSAlert(int timeOut)
			{
				waitForJSAlert(timeOut).dismiss();
			}
			
			public  String getJSAlertText(int timeOut)
			{
				return waitForJSAlert(timeOut).getText();
			}
			
			public  void enterValueOnJSAlertText(int timeOut, String value)
			{
				 waitForJSAlert(timeOut).sendKeys(value);;
			}
			
			public  void waitForFrameByLocator(By frameLocator, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			}
			
			public void waitForFrameByIndex(int frameIndex, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			}
			
			public void waitForFrameByIDOrName(String IdOrName, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IdOrName));
			}
			
			public  boolean checkNewWindowExist(int timeOut, int expectedNoOfWindow)
			{
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));	
				try {
				if(wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNoOfWindow)))
				{
					return true;
				}
				}
				catch(TimeoutException e)
				{
					 System.out.println("No of windows are not same...");
				}
				return false;
			}
			
			/**
			 * An expectation for checking an element is visible and enabled such that you can click it.
			 * @param locator
			 * @param timeOut
			 */
			public void clickElementWhenReady(By locator, int timeOut)
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				try {
				wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
				}
				catch(TimeoutException e)
				{
					System.out.println("element is not clickabe or enabled");
				}
			}
			
			public  WebElement waitForElementWithFluentWait(By locator, int timeout, int pollingTime)
		    {
		    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		                .withTimeout(Duration.ofSeconds(timeout))
		                .pollingEvery(Duration.ofSeconds(pollingTime))
		                .withMessage("Timeout is done...element is not found...")
		                .ignoring(NoSuchElementException.class)
		                .ignoring(ElementNotInteractableException.class);

		    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	
		    }
			
			public void waitForFrameWithFluentWait(String frameIdOrName, int timeout, int intervelTime)
		    {
		    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		                .withTimeout(Duration.ofSeconds(timeout))
		                .pollingEvery(Duration.ofSeconds(intervelTime))
		                .withMessage("Timeout is done...frame is not found...")
		                .ignoring(NoSuchFrameException.class);

		     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
	
		    }
			
			public Alert waitForJSAlertWithFluentWait( int timeout, int intervelTime)
		    {
		    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		                .withTimeout(Duration.ofSeconds(timeout))
		                .pollingEvery(Duration.ofSeconds(intervelTime))
		                .withMessage("Timeout is done...Alert is not appeared...")
		                .ignoring(NoAlertPresentException.class);

		     return wait.until(ExpectedConditions.alertIsPresent());
	
		    }
	//*************************Custom Wait******************************************
			
			public WebElement retryingElement(By locator, int timeOut)
			{
				WebElement element = null;
				int attempts =0;
				
				while(attempts < timeOut)
				{
					try {
					element = getElement(locator);
					System.out.println("Element is not found" + locator+"in attempts" +attempts);
					break;
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Element is not found..." + locator +"in attempts" +attempts);
					try {
						  Thread.sleep(500);//default polling time
						}
					catch(InterruptedException e1)
					{
						e1.printStackTrace();
					}
					}
					
					attempts++;
				}
				
				if(element==null) {
					System.out.println("Element is not found...tried for" +timeOut+ " times" + "with the interval of" + 500+ "mileseconds" );
					throw new FrameworkException("No Such element ");
				}
				
				return element;
			}
			
			public WebElement retryingElement(By locator, int timeOut, int intervalTime)
			{
				WebElement element = null;
				int attempts =0;
				
				while(attempts < timeOut)
				{
					try {
					element = getElement(locator);
					System.out.println("Element is not found" + locator+"in attempts" +attempts);
					break;
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Element is not found..." + locator +"in attempts" +attempts);
					try {
						  Thread.sleep(intervalTime);//custom polling time
						}
					catch(InterruptedException e1)
					{
						e1.printStackTrace();
					}
					}
					
					attempts++;
				}
				
				if(element==null) {
					System.out.println("Element is not found...tried for" +timeOut+ " times" + "with the interval of" + 500+ "mileseconds" );
					throw new FrameworkException("No Such element ");
				}
				
				return element;
			}
    
			public Boolean isPageLoaded(int timeOut)
			{
				WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==='complete'")).toString();
				return Boolean.parseBoolean(flag);
			}
}
