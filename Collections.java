package com.ReceivablesPageObject;

import java.util.ArrayList;
import java.util.Hashtable;
//import org.eclipse.jetty.websocket.common.frames.DataFrame;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import mq.seleniumFW.connection.MQDBConnection;

public class Collections 

{

    static WebDriver driver;
	
	public MQDBConnection oDBConnection;
    public ArrayList<Hashtable<String, String>> records;
    public Hashtable<String, String> record;
    
    public By txtSubscriber = By.name("ctl00$uxPgCPH$txtSubscriber");
    public By Custsrch1custsrchimg = By.name("ctl00$uxPgCPH$Custsrch1$custsrchimg");

    public By txtAmtfrom = By.name("ctl00$uxPgCPH$txtAmtfrom");
    public By txtAmtto = By.name("ctl00$uxPgCPH$txtAmtto");

    public By txtDaysfrom = By.name("ctl00$uxPgCPH$txtDaysfrom");
    public By txtDaysto = By.name("ctl00$uxPgCPH$txtDaysto");

    public By entsearchtxtEntity = By.name("ctl00$uxPgCPH$entsearch$txtEntity");

    public By entsearchentsrchimg = By.name("ctl00$uxPgCPH$entsearch$entsrchimg");

    public By btnSearch = By.name("ctl00$uxPgCPH$btnSearch");

    public By txtCollectiondate = By.name("ctl00$uxPgCPH$txtCollectiondate");

    public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
    public By btncancel = By.name("ctl00$uxPgCPH$btncancel");


    public By ddlCustomertype = By.name("ctl00$uxPgCPH$ddlCustomertype");
    public By ddlStages = By.name("ctl00$uxPgCPH$ddlStages");

    public By ddlMarkreason = By.name("ctl00$uxPgCPH$ddlMarkreason");
    public By ddlMarkasstage = By.name("ctl00$uxPgCPH$ddlMarkasstage");
    public By txtNotes = By.name("ctl00$uxPgCPH$txtNotes");

    
    

	public Collections(WebDriver driver,MQDBConnection dbConnection) 
	{
		this.driver=driver;
		this.oDBConnection=dbConnection;
		PageFactory.initElements(driver, this);		
    }
    
    
    
	public void collectionHeader(String Customer,String Dueamountfrom,String Dueamountto,String CustomerType,String DaysSinceFrom,String DaysSinceTo,String CollectionStage,String OperationalEntity ) throws InterruptedException
	
	{	
    
		System.out.println("entred in to Collection's method");
		// Enter the Customer Number
				if(Customer.isEmpty())
				{
				System.out.println("\n");	
				}
				else if (Customer.length()!=0)
				{
				driver.findElement(txtSubscriber).clear();	
				driver.findElement(txtSubscriber).sendKeys(Customer+Keys.TAB);	
				Thread.sleep(1000);
				}
			
				
				
				
				

				// Enter the From Date
				if(Dueamountfrom.isEmpty())
				{
				
				}
				else if(Dueamountfrom.length()!=0)
				{
				driver.findElement(txtDaysfrom).clear();
				driver.findElement(txtDaysfrom).sendKeys(Dueamountfrom+Keys.TAB);	
				Thread.sleep(1000);
				}
				
				// Enter the Dueamountto
				if(Dueamountto.isEmpty())
				{
				
				}
				else if (Dueamountto.length()!=0)
				{
					driver.findElement(txtDaysfrom).clear();
				driver.findElement(txtAmtfrom).sendKeys(Dueamountto+Keys.TAB);	
				Thread.sleep(1000);	
				}
			
				// Enter the Customer type
				if(CustomerType.isEmpty())
				{
				System.out.println("\n");	
				}
				else if (CustomerType.length()!=0)
				{
					Thread.sleep(1000);
				    new Select(driver.findElement(ddlCustomertype)).selectByVisibleText(CustomerType);	
				}
		
		
				
				// Enter the DaysSinceFrom
				if(DaysSinceFrom.isEmpty())
				{
				
				}
				else if (DaysSinceFrom.length()!=0)
				{
					driver.findElement(txtDaysfrom).clear();
				driver.findElement(txtDaysfrom).sendKeys(DaysSinceFrom+Keys.TAB);	
				Thread.sleep(1000);	
				}
				
				// Enter the DaysSinceTo
				if(DaysSinceTo.isEmpty())
				{
				
				}
				else if (DaysSinceTo.length()!=0)
				{
					driver.findElement(txtDaysfrom).clear();
				driver.findElement(txtDaysto).sendKeys(DaysSinceTo+Keys.TAB);	
				Thread.sleep(1000);	
				}
		// CollectionStage,String OperationalEntity
				
				// Enter the CollectionStage
				if(CollectionStage.isEmpty())
				{
				
				}
				else if (CollectionStage.length()!=0)
				{
					Thread.sleep(1000);
				    new Select(driver.findElement(ddlStages)).selectByVisibleText(CollectionStage);	
				}
		
	
				// Clicking in Search button.
				driver.findElement(btnSearch).click();
				
    
    }
	
	
	
	
	
	
	
	
	
	
	
	public void collectionGrid(String CollectionDate,String Reason,String MarkSelectedCustomersAs,String Notes, String save_Cancel) throws InterruptedException
	{	
    
		
		driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvCollections_ctl01_CheckAll']")).click();
		

		           // Enter the Collection Date
		if(CollectionDate.isEmpty())
		{

		}
		else if (CollectionDate.length()!=0)
		{
			driver.findElement(txtCollectiondate).clear();
			driver.findElement(txtCollectiondate).sendKeys(CollectionDate);	
			Thread.sleep(1000);	
		}


		// Enter the Reason
		if(Reason.isEmpty())
		{

		}
		else if (Reason.length()!=0)
		{
			Thread.sleep(1000);
			new Select(driver.findElement(ddlMarkreason)).selectByVisibleText(Reason);	
		}



		// Enter the MarkSelectedCustomersAs
		if(MarkSelectedCustomersAs.isEmpty())
		{
		}
		else if (MarkSelectedCustomersAs.length()!=0)
		{
			Thread.sleep(1000);
			new Select(driver.findElement(ddlMarkasstage)).selectByVisibleText(MarkSelectedCustomersAs);	
		}



		// Enter the Notes
		if(Notes.isEmpty())
		{
		}
		else if (Notes.length()!=0)
		{
			driver.findElement(txtNotes).clear();
			driver.findElement(txtNotes).sendKeys(Notes);	
			Thread.sleep(1000);	
		}



		//Click on the save or cancel button
		if(save_Cancel.contains("Cancel"))
		{
			//Click on the Cancel button
			driver.findElement(btncancel).click();
		}
		else
		{
			//Click on the save button
			driver.findElement(btnsave).click();
		}

		
		
		System.out.println("Collection's Stage as Updated successfully");		
		
		
		
    
    }
    
    
    
}
