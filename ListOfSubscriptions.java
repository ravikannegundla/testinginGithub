package com.OrderManagementPageObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ListOfSubscriptions 

{



	
	static WebDriver driver;
	public MQDBConnection oDBConnection;
 	
	
	
	// expire
	
	public By expireDuration=By.id("ddlDurtype");
	public By expirefrom_date=By.id("from_date");
	public By expireend_date=By.id("end_date");
	public By expire_btndownload=By.id("expiry_btndownload");
	
	// Expired
	
	public By ExpiredDuration=By.id("ddlExpDurtype");
	public By Expiredfrom_date=By.id("from_date1");
	public By Expiredend_date=By.id("end_date1");
	public By Expired_btndownload=By.id("expired_btndownload");
	
	
	// Activations 
	
	public By ActivationsStatustype=By.id("ddlStatustype");
	public By Activations_Ordertype=By.id("ddlOrdertype");
	public By Activationsfrom_date=By.id("from_date2");
	public By Activationsend_date=By.id("end_date2");
	public By Activations_btndownload=By.id("activation_btndownload");
	
	
	
	public By ddlbtnSearch=By.id("btnSearch");
//	public By ddlclearFilters=By.id("clear");
	public By ddlclearFilters =By.xpath(".//*[@class='clear-filter']");
	
	
	

	
	
	
	
	public ListOfSubscriptions(WebDriver driver,MQDBConnection dbConnection) 
	{
		this.driver=driver;
		this.oDBConnection=dbConnection;
		PageFactory.initElements(driver, this);		
    }


		

	public void Abouttoexpire(String sCustomerNumber,String sContractNumber,String sDuration,String sDateRangeFrom,String sDateRangeTo,String sSearch, String sDownload,String sClearFilters) throws InterruptedException
	{


		if(sClearFilters.length()!=0)
		{ 

			if (sClearFilters=="Y")
			{				
				Thread.sleep(1000);
				driver.findElement(ddlclearFilters).click();
			}			
		}


		if(sDuration.length()!=0)
		{
			new Select(driver.findElement(expireDuration)).selectByVisibleText(sDuration);
		}
		Thread.sleep(3000);


		if(sDateRangeFrom.length()!=0)
		{ 
			Thread.sleep(1000);
			driver.findElement(expirefrom_date).clear();
			driver.findElement(expirefrom_date).sendKeys(sDateRangeFrom+Keys.TAB);
		}
		Thread.sleep(1000);


		if(sDateRangeTo.length()!=0)
		{ 
			Thread.sleep(1000);
			driver.findElement(expireend_date).clear();
			driver.findElement(expireend_date).sendKeys(sDateRangeTo+Keys.TAB);
		}
		Thread.sleep(1000);


		// sSearch, String sDownload

		if(sSearch.length()!=0)
		{ 

			if (sSearch=="Y")
			{				
				Thread.sleep(1000);
				driver.findElement(ddlbtnSearch).click();
			}			
		}


		if(sDownload.length()!=0)
		{ 

			if (sDownload=="Y")
			{				
				Thread.sleep(1000);
				driver.findElement(expire_btndownload).click();
			}			
		}


		
		
		




	}

	

	
	
	public void Expired(String sCustomerNumber,String sContractNumber,String sDuration,String sDateRangeFrom,String sDateRangeTo,String sSearch, String sDownload,String sClearFilters) throws InterruptedException
	{
		
		
		

		if(sDuration.length()!=0)
		{
			new Select(driver.findElement(ExpiredDuration)).selectByVisibleText(sDuration);
		}
		Thread.sleep(3000);
		
		
		if(sDateRangeFrom.length()!=0)
		{ 
			Thread.sleep(1000);
			driver.findElement(Expiredfrom_date).clear();
			driver.findElement(Expiredfrom_date).sendKeys(sDateRangeFrom+Keys.TAB);
		}
		Thread.sleep(1000);
		
		
		if(sDateRangeTo.length()!=0)
		{ 
			Thread.sleep(1000);
			driver.findElement(Expiredend_date).clear();
			driver.findElement(Expiredend_date).sendKeys(sDateRangeTo+Keys.TAB);
		}
		Thread.sleep(1000);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
	public void Activations(String sCustomerNumber,String sContractNumber,String sStatus,String sOrderType,String sDateRangeFrom,String sDateRangeTo,String sSearch, String sDownload,String sClearFilters) throws InterruptedException
	{
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	







}
