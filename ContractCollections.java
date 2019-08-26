package com.ReceivablesPageObject;

import java.util.List;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class ContractCollections
{
	
			public MQDBConnection oDBConnection;
			static WebDriver driver;
			public By loctxtCustnbr = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtCustnbr");
			public By locCustsrch1custsrchimg=By.name("ctl00$uxPgCPH$uxSearchCtrl$searchimg$custsrchimg");
			public By locExtid=By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_external_id");
			public By locDevice=By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_device_no");
			public By locContract=By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_contact");
			public By locReason=By.xpath(".//*[@id='ctl00_uxPgCPH_gvcontractdetails_ctl02_ddlreason']");
			public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");
			public By locSuspend=By.linkText("Suspend");
			public By locReactivate=By.linkText("Reactivate");
			String Message;


			public ContractCollections(WebDriver driver,MQDBConnection dbConnection) 
			{
				this.driver=driver;
				this.oDBConnection=dbConnection;
				PageFactory.initElements(driver, this);		
		    }
			

			
			
			// This method is to Suspend a Contract for the Customer 
			public String SuspendCustomer(String CustomerNumber,String Reason,String ContractNumber) throws Exception
			{
				try
				{
					Thread.sleep(1000);
					//		Enter the Customer Number in a text box 
					if(CustomerNumber.isEmpty())
					{
						System.out.println("\n");	
					}
					else if (CustomerNumber.length()!=0)
					{
					driver.findElement(loctxtCustnbr).clear();	
					driver.findElement(loctxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
					Thread.sleep(1000);
					}
					List<WebElement> Row1= driver.findElements(By.xpath("//TABLE[@id='ctl00_uxPgCPH_gvcontractdetails']"));
					int RowSize1=Row1.size();
					System.out.println("Row Size is :"+RowSize1);
					for(int j=2;j<3;j++)
					{
						//			Capturing the Service Number from the grid
						String contractNumber1 = driver.findElement(By.xpath("//table[@id='ctl00_uxPgCPH_gvcontractdetails']/tbody/tr["+j+"]/td[1]")).getText();
						System.out.println("contract number is...."+contractNumber1);
						if(contractNumber1.contains(ContractNumber))
						{	
							//		 Selecting the reason from the drop down list
							Thread.sleep(1000);
							if(Reason.isEmpty())
							{
							System.out.println("\n");	
							}
							else if (Reason.length()!=0)
							{
								Thread.sleep(1000);
								new Select(driver.findElement(locReason)).selectByVisibleText(Reason);	
							}
							Thread.sleep(3000);
							driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvcontractdetails_ctl02_lnksuspend']")).click();
						}
						else
						{
							System.out.println("Contract Not exists");
						}
					}
					Message=driver.findElement(ScreenOutPut).getText();
				}   
				catch(Exception e)
				{
					System.out.println("Test_____%Failed  "+e);
				}
				return Message; 
			}

			
			
			
			
			// This method is to Reactivate a Contract for the Customer
			public String ReactivateCustomer(String CustomerNumber,String Reason,String ContractNumber) throws Exception
			{
				try
				{
					Thread.sleep(1000);
					//		 Enter the Customer Number in a text box 
					if(CustomerNumber.isEmpty())
					{
						System.out.println("\n");	
					}
					else if (CustomerNumber.length()!=0)
					{
					driver.findElement(loctxtCustnbr).clear();	
					driver.findElement(loctxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);	
					Thread.sleep(1000);
					}

					List<WebElement> Row1= driver.findElements(By.xpath("//TABLE[@id='ctl00_uxPgCPH_gvcontractdetails']"));
					int RowSize1=Row1.size();
					System.out.println("Row Size is :"+RowSize1);
					for(int j=2;j<3;j++)
					{
						//			 Capturing the Service Number from the grid
						String contractNumber1 = driver.findElement(By.xpath("//table[@id='ctl00_uxPgCPH_gvcontractdetails']/tbody/tr["+j+"]/td[1]")).getText();
						System.out.println("contract number is...."+contractNumber1);
						if(contractNumber1.contains(ContractNumber))
						{	
							//		 Selecting the reason from the drop down list
							Thread.sleep(1000);
							if(Reason.isEmpty())
							{
							System.out.println("\n");	
							}
							else if (Reason.length()!=0)
							{
								Thread.sleep(1000);
								new Select(driver.findElement(locReason)).selectByVisibleText(Reason);	
							}
							Thread.sleep(3000);
							driver.findElement(By.xpath("//*[text()='Reactivate']")).click();
						} 
						else
						{
							System.out.println("Contract Not exists");
						}
					}
					Message=driver.findElement(ScreenOutPut).getText();
				}
				catch(Exception e)
				{
					System.out.println("Test_____%Failed  "+e);
				}
				return Message; 
			}
			
			
			
			
			
}
