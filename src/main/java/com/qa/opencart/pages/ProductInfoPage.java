package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricing  = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	//private Map<String, String> productMap = new HashMap<String, String>();
	//private Map<String, String> productMap = new LinkedHashMap<String, String>();
	private Map<String, String> productMap = new TreeMap<String, String>();
	
	
	
	public ProductInfoPage(WebDriver driver)
    {
   	   this.driver = driver;
   	   eleUtil = new ElementUtil(this.driver);
    }
	
	public String getProductHeaderName()
	{
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println(productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfAllElement(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product" + getProductHeaderName()+ "imagesCount" + imagesCount);
		return imagesCount;
		
	}
	
	//Brand: Apple
	//Product Code:Product 18
	//Reward Points:800
	//Availability:Out Of Stock
	
	private void getProductMetaData()
	{
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfAllElement(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		for(WebElement e : metaDataList)
		{
			String metaData = e.getText();
			String metaKey  = metaData.split(":")[0].trim();
			String metaValue = metaData.split(":")[1].trim();
			
			productMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPriceData()
	{
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfAllElement(productPricing, AppConstants.MEDIUM_DEFAULT_WAIT);
		String productPrice = metaDataList.get(0).getText();
		String productExTaxPrice = metaDataList.get(1).getText().split(":")[1].trim();
		
		productMap.put("price", productPrice);
		productMap.put("extaxprice", productExTaxPrice);
	}
	
	public Map<String, String> getProductDetails()
	{
		productMap.put("productname", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		
		return productMap;
		
	}
}
