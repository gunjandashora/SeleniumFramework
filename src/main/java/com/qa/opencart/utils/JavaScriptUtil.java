package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public String getTitleByJS()
	{
        return js.executeScript("return document.title").toString();
	}
	
	public String getURLByJS()
	{
        return js.executeScript("return document.URL").toString();
	}
	
	public void generateJSAlert(String msg)
	{
		js.executeScript("alert('"+msg+"')");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	public void generateJSConfirmation(String msg)
	{
		js.executeScript("confirm('"+msg+"')");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	public void generateJSPrompt(String msg, String value)
	{
		js.executeScript("prompt('"+msg+"')");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}
	
	public void gobackWithJs()
	{
		js.executeScript("history.go(-1)");
	}
	
	public void goforwardWithJs()
	{
		js.executeScript("history.go(1)");
	}
	
	public void pageRefreshWithJs()
	{
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnerText()
	{
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void scrollPageDown()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollMiddlePageDown()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
	}
	
	public void scrollPageDown(String height)
	{
		js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	
	public void scrollPageUp()
	{
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void scrollIntoView(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

}
