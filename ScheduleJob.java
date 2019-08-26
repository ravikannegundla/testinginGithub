package com.JobsPageObject;

import java.util.ArrayList;
import java.util.Hashtable;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ScheduleJob {

	static WebDriver driver;
	public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");

	public By loctxtName = By.name("ctl00$uxPgCPH$txtName");
	public By locbtnSetParams = By.name("ctl00$uxPgCPH$btnSetParams");
	public By locbtnSchedule = By.name("ctl00$uxPgCPH$btnSchedule");
	public By locbtnEmailRep = By.name("ctl00$uxPgCPH$btnEmailRep");
	public By locbtnFtpDtl = By.name("ctl00$uxPgCPH$btnFtpDtl");
	public By locbtnschedulenow = By.name("ctl00$uxPgCPH$btnschedulenow");
	public By locbtnsave = By.name("ctl00$uxPgCPH$btnsave");
	public By locbtncancel = By.name("ctl00$uxPgCPH$btncancel");


	public By locddlCategory = By.name("ctl00$uxPgCPH$ddlCategory");
	public By locddlProgram = By.name("ctl00$uxPgCPH$ddlProgram");
	public By locddlModule = By.name("ctl00$uxPgCPH$ddlModule");
	public By locddlApplserver = By.name("ctl00$uxPgCPH$ddlApplserver");
	public By loctxtDescr = By.name("ctl00$uxPgCPH$txtDescr");
	public By locInstanceName = By.xpath(".//*[@id='ctl00_uxPgCPH_sbelem_94_18']");
	
	public MQDBConnection oDBConnection;
	private ArrayList<Hashtable<String, String>> records;
	private Hashtable<String, String> record;


	String sReturnValue,AvailableBalanceFinal;
	
	public ScheduleJob(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);	
		
	}

	
	public String ScheduleJobMessageDaemon(String Name, String Category,String Program, String Description, String Module, String ApplicationServer,String ScheduleButtons,String ScreenButton) throws InterruptedException 
	{
		//Enter the Name
		if(Name.isEmpty())
		 {
		 System.out.println("\n");
		 }
		else
		{
		driver.findElement(loctxtName).clear();
		driver.findElement(loctxtName).sendKeys(Name);
		}
		//Select the Category
		if(Category.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlCategory)).selectByVisibleText(Category);	
		}
		Thread.sleep(800);
		//Select the Program
		if(Program.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlProgram)).selectByVisibleText(Program);	
		}
	    Thread.sleep(800);
	    //Enter the Description
	  	if(Description.isEmpty())
	  	{
	  	System.out.println("\n");
	  	}
	  	else
	  	{
	  	driver.findElement(loctxtDescr).clear();
	  	driver.findElement(loctxtDescr).sendKeys(Description);
	  	}
		//Select the Module
		if(Module.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlModule)).selectByVisibleText(Module);	
		}
		Thread.sleep(800);
		//Select the ApplicationServer
		if(ApplicationServer.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlApplserver)).selectByVisibleText(ApplicationServer);	
		}
		Thread.sleep(1000);	
		if(ScheduleButtons.equalsIgnoreCase("Schedule"))
		{
		//Schedule
			
			driver.findElement(By.xpath("//*[@id='ctl00_uxPgCPH_btnschedulenow']")).click();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
		
		}
		else if(ScheduleButtons.equalsIgnoreCase("Email"))
		{
		//Email	
		}
		else if(ScheduleButtons.equalsIgnoreCase("Ftp Details"))
		{
		//Ftp Details	
		}
		else
		{
		//Set Parameters
		
		}
		
		//Click on the screen buttons
		if(ScreenButton.contains("Save"))
		{
		//click on save button
		driver.findElement(locbtnsave).click();
		}
		else if(ScreenButton.contains("Cancle"))
		{
		//click on cancel button
		driver.findElement(locbtncancel).click();
		}
		else
		{
		//click on Schedule now button
		driver.findElement(locbtnschedulenow).click();	
		Thread.sleep(800);
		//click on the 'ok' in the popup
		Alert alert=driver.switchTo().alert();
		 alert.accept();
		 

}
		Thread.sleep(3000);
		//Capturing the Screen Message
	    String sreturnValue =driver.findElement(ScreenOutPut).getText();
	 	return sreturnValue;
	}
	
	
	
	
	public String ScheduleJobScreen(String Name, String Category,String Program, String Description, String Module, String ApplicationServer,String ScheduleButtons,String ScreenButton) throws InterruptedException 
	{
		//Enter the Name
		if(Name.isEmpty())
		 {
		 System.out.println("\n");
		 }
		else
		{
		driver.findElement(loctxtName).clear();
		driver.findElement(loctxtName).sendKeys(Name);
		}
		//Select the Category
		if(Category.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlCategory)).selectByVisibleText(Category);	
		}
		Thread.sleep(800);
		//Select the Program
		if(Program.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlProgram)).selectByVisibleText(Program);	
		}
	    Thread.sleep(800);
	    //Enter the Description
	  	if(Description.isEmpty())
	  	{
	  	System.out.println("\n");
	  	}
	  	else
	  	{
	  	driver.findElement(loctxtDescr).clear();
	  	driver.findElement(loctxtDescr).sendKeys(Description);
	  	}
		//Select the Module
		if(Module.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlModule)).selectByVisibleText(Module);	
		}
		Thread.sleep(800);
		//Select the ApplicationServer
		if(ApplicationServer.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlApplserver)).selectByVisibleText(ApplicationServer);	
		}
		Thread.sleep(1000);	
		if(ScheduleButtons.equalsIgnoreCase("Schedule"))
		{
		//Schedule
		
		}
		else if(ScheduleButtons.equalsIgnoreCase("Email"))
		{
		//Email	
		}
		else if(ScheduleButtons.equalsIgnoreCase("Ftp Details"))
		{
		//Ftp Details	
		}
		else
		{
		//Set Parameters
		
		}
		
		//Click on the screen buttons
		if(ScreenButton.contains("Save"))
		{
		//click on save button
		driver.findElement(locbtnsave).click();
		}
		else if(ScreenButton.contains("Cancle"))
		{
		//click on cancel button
		driver.findElement(locbtncancel).click();
		}
		else
		{
		//click on Schedule now button
		driver.findElement(locbtnschedulenow).click();	
		Thread.sleep(800);
		//click on the 'ok' in the popup
		Alert alert=driver.switchTo().alert();
		 alert.accept();
		 
//			//click on the 'ok' in the popup
//			Alert alert1=driver.switchTo().alert();
//			 alert1.accept();
}
		Thread.sleep(4000);
		//Capturing the Screen Message
	    String sreturnValue =driver.findElement(ScreenOutPut).getText();
	 	return sreturnValue;
	}
	
	
	
	
	
	public String ScheduleJobProgramGetRequest(String Name, String Category,String Program, String Description, String Module, String ApplicationServer,String ScheduleButtons,String InstanceType,String ScreenButton) throws InterruptedException 
	{
		//Enter the Name
		if(Name.isEmpty())
		 {
		 System.out.println("\n");
		 }
		else
		{
		driver.findElement(loctxtName).clear();
		driver.findElement(loctxtName).sendKeys(Name);
		}
		//Select the Category
		if(Category.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlCategory)).selectByVisibleText(Category);	
		}
		Thread.sleep(800);
		//Select the Program
		if(Program.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlProgram)).selectByVisibleText(Program);	
		}
	    Thread.sleep(800);
	    //Enter the Description
	  	if(Description.isEmpty())
	  	{
	  	System.out.println("\n");
	  	}
	  	else
	  	{
	  	driver.findElement(loctxtDescr).clear();
	  	driver.findElement(loctxtDescr).sendKeys(Description);
	  	}
		//Select the Module
		if(Module.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlModule)).selectByVisibleText(Module);	
		}
		Thread.sleep(800);
		//Select the ApplicationServer
		if(ApplicationServer.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlApplserver)).selectByVisibleText(ApplicationServer);	
		}
		Thread.sleep(1000);	
		if(ScheduleButtons.equalsIgnoreCase("Schedule"))
		{
		//Schedule
		
		}
		else if(ScheduleButtons.equalsIgnoreCase("Email"))
		{
		//Email	
		}
		else if(ScheduleButtons.equalsIgnoreCase("Ftp Details"))
		{
		//Ftp Details	
		}
		else
		{
		//Set Parameters
		
		
		}
		//Select the Instance Type from the drop down field
		if(InstanceType.isEmpty())
		{
		System.out.println("\n");	
		}
		else
		{
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_sbelem_142_32']"))).selectByVisibleText(InstanceType);	
		}
		
		
		
		//Click on the screen buttons
		if(ScreenButton.contains("Save"))
		{
		//click on save button
		driver.findElement(locbtnsave).click();
		}
		else if(ScreenButton.contains("Cancel"))
		{
		//click on cancel button
		driver.findElement(locbtncancel).click();
		}
		else
		{
		//click on Schedule now button
		driver.findElement(locbtnschedulenow).click();	
		Thread.sleep(800);
		//click on the 'ok' in the popup
		Alert alert=driver.switchTo().alert();
		 alert.accept();
		 

}
		Thread.sleep(3000);
		//Capturing the Screen Message
	    String sreturnValue =driver.findElement(ScreenOutPut).getText();
	 	return sreturnValue;
	}
	

	
	
	
	public String ScheduleJobScheduledActions(String Name, String Category,String Program, String Description, String Module, String ApplicationServer,String ScheduleButtons,String InstanceName,String QueueName,String ScreenButton) throws InterruptedException 
	{
		//Enter the Name
		if(Name.isEmpty())
		 {
		 System.out.println("\n");
		 }
		else
		{
		driver.findElement(loctxtName).clear();
		driver.findElement(loctxtName).sendKeys(Name);
		}
		//Select the Category
		if(Category.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlCategory)).selectByVisibleText(Category);	
		}
		Thread.sleep(800);
		//Select the Program
		if(Program.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlProgram)).selectByVisibleText(Program);	
		}
	    Thread.sleep(800);
	    //Enter the Description
	  	if(Description.isEmpty())
	  	{
	  	System.out.println("\n");
	  	}
	  	else
	  	{
	  	driver.findElement(loctxtDescr).clear();
	  	driver.findElement(loctxtDescr).sendKeys(Description);
	  	}
		//Select the Module
		if(Module.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlModule)).selectByVisibleText(Module);	
		}
		Thread.sleep(800);
		//Select the ApplicationServer
		if(ApplicationServer.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlApplserver)).selectByVisibleText(ApplicationServer);	
		}
		Thread.sleep(1000);	
		if(ScheduleButtons.equalsIgnoreCase("Schedule"))
		{
		//Schedule
		
		}
		else if(ScheduleButtons.equalsIgnoreCase("Email"))
		{
		//Email	
		}
		else if(ScheduleButtons.equalsIgnoreCase("Ftp Details"))
		{
		//Ftp Details	
		}
		else
		{
		//Set Parameters
		
		
		}
		//Select the Instance Type from the drop down field
		
		
		if(InstanceName.isEmpty())
		{
		System.out.println("\n");	
		}
		else if (InstanceName.length()!=0)
		{
		driver.findElement(locInstanceName).sendKeys(InstanceName);	
		}
	
		if(QueueName.isEmpty())
		{
		System.out.println("\n");	
		}
		else
		{
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_sbelem_168_18']"))).selectByIndex(1);	
		}
		
		
		
		//Click on the screen buttons
		if(ScreenButton.contains("Save"))
		{
		//click on save button
		driver.findElement(locbtnsave).click();
		}
		else if(ScreenButton.contains("Cancel"))
		{
		//click on cancel button
		driver.findElement(locbtncancel).click();
		}
		else
		{
		//click on Schedule now button
		driver.findElement(locbtnschedulenow).click();	
		Thread.sleep(800);
		//click on the 'ok' in the popup
		Alert alert=driver.switchTo().alert();
		 alert.accept();
		 

}
		Thread.sleep(3000);
		//Capturing the Screen Message
	    String sreturnValue =driver.findElement(ScreenOutPut).getText();
	 	return sreturnValue;
	}

	
	
	
	

	public String ScheduleJobAutoExpiry_SingleCustomer(String Name, String Category,String Program, String Description, String Module, String ApplicationServer,String ScheduleButtons,String BatchId,String BatchInstance,String CustomerId,String ScreenButton) throws InterruptedException
	{
		//Enter the Name
		if(Name.isEmpty())
		 {
		 System.out.println("\n");
		 }
		else
		{
		driver.findElement(loctxtName).clear();
		driver.findElement(loctxtName).sendKeys(Name);
		}
		//Select the Category
		if(Category.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlCategory)).selectByVisibleText(Category);	
		}
		Thread.sleep(800);
		//Select the Program
		if(Program.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlProgram)).selectByVisibleText(Program);	
		}
	    Thread.sleep(800);
	    //Enter the Description
	  	if(Description.isEmpty())
	  	{
	  	System.out.println("\n");
	  	}
	  	else
	  	{
	  	driver.findElement(loctxtDescr).clear();
	  	driver.findElement(loctxtDescr).sendKeys(Description);
	  	}
		//Select the Module
		if(Module.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlModule)).selectByVisibleText(Module);	
		}
		Thread.sleep(800);
		//Select the ApplicationServer
		if(ApplicationServer.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlApplserver)).selectByVisibleText(ApplicationServer);	
		}
		Thread.sleep(1000);	
		if(ScheduleButtons.equalsIgnoreCase("Schedule"))
		{
		//Schedule
		
		}
		else if(ScheduleButtons.equalsIgnoreCase("Email"))
		{
		//Email	
		}
		else if(ScheduleButtons.equalsIgnoreCase("Ftp Details"))
		{
		//Ftp Details	
		}
		else
		{
		//Set Parameters
		}
		//Select the BatchId from the drop down field
		if(BatchId.isEmpty())
		{
		System.out.println("\n");	
		}
		else if (BatchId.length()!=0)
		{
			new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_sbelem_2_2']"))).selectByVisibleText(BatchId);	
		}
	
		// Select the Batch Instance
		if(BatchInstance.isEmpty())
		{
		System.out.println("\n");	
		}
		else
		{
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ddlbatchinstance']"))).selectByVisibleText(BatchInstance);	
		}
		
		// Enter the Customer Id value
		if(CustomerId.isEmpty())
		{
		System.out.println("\n");	
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_tbbatch_25_0']")).clear();
			driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_tbbatch_25_0']")).sendKeys(CustomerId);
		}
		
		//Click on the screen buttons
		if(ScreenButton.contains("Save"))
		{
		//click on save button
		driver.findElement(locbtnsave).click();
		}
		else if(ScreenButton.contains("Cancel"))
		{
		//click on cancel button
		driver.findElement(locbtncancel).click();
		}
		else
		{
		//click on Schedule now button
		driver.findElement(locbtnschedulenow).click();	
		Thread.sleep(800);
		//click on the 'Ok' in the Pop up
		Alert alert=driver.switchTo().alert();
		alert.accept();
		}
	
		Thread.sleep(3000);
		//Capturing the Screen Message
	    String sreturnValue =driver.findElement(ScreenOutPut).getText();
	 	return sreturnValue;
	
	}

	
	
	// RenewEntitlement_Job

	public String RenewEntitlement_Job(String Name, String Category,String Program, String Description, String Module, String ApplicationServer,String ScheduleButtons,String ProvisioningSystem,String BatchInstance,String BatchId,String ScreenButton) throws InterruptedException
	{
		//Enter the Name
		if(Name.isEmpty())
		 {
		 System.out.println("\n");
		 }
		else
		{
		driver.findElement(loctxtName).clear();
		driver.findElement(loctxtName).sendKeys(Name);
		}
		//Select the Category
		if(Category.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlCategory)).selectByVisibleText(Category);	
		}
		Thread.sleep(800);
		//Select the Program
		if(Program.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlProgram)).selectByVisibleText(Program);	
		}
	    Thread.sleep(800);
	    //Enter the Description
	  	if(Description.isEmpty())
	  	{
	  	System.out.println("\n");
	  	}
	  	else
	  	{
	  	driver.findElement(loctxtDescr).clear();
	  	driver.findElement(loctxtDescr).sendKeys(Description);
	  	}
		//Select the Module
		if(Module.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlModule)).selectByVisibleText(Module);	
		}
		Thread.sleep(800);
		//Select the ApplicationServer
		if(ApplicationServer.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
	    new Select(driver.findElement(locddlApplserver)).selectByVisibleText(ApplicationServer);	
		}
		Thread.sleep(1000);	
		if(ScheduleButtons.equalsIgnoreCase("Schedule"))
		{
		//Schedule
		
		}
		else if(ScheduleButtons.equalsIgnoreCase("Email"))
		{
		//Email	
		}
		else if(ScheduleButtons.equalsIgnoreCase("Ftp Details"))
		{
		//Ftp Details	
		}
		else
		{
		//Set Parameters
		}

		if(ProvisioningSystem.isEmpty())
		{
		System.out.println("\n");	
		}
		else
		{
			
		new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_sbelem_61_29']"))).selectByVisibleText(ProvisioningSystem);	
		}
		
		//Select the BatchId from the drop down field
		// Select the Batch Instance
				if(BatchInstance.isEmpty())
				{
				System.out.println("\n");	
				}
				else
				{
					
				new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ddlbatchinstance']"))).selectByVisibleText(BatchInstance);	
				}
		
		if(BatchId.isEmpty())
		{
		System.out.println("\n");	
		}
		else if (BatchId.length()!=0)
		{
			
			new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_sbelem_2_29']"))).selectByVisibleText(BatchId);	
		}
		
		//Click on the screen buttons
		
		
		
		if(ScreenButton.contains("Save"))
		{
		//click on save button
		driver.findElement(locbtnsave).click();
		}
		else if(ScreenButton.contains("Cancel"))
		{
		//click on cancel button
		driver.findElement(locbtncancel).click();
		}
		else
		{
		//click on Schedule now button
		driver.findElement(locbtnschedulenow).click();	
		Thread.sleep(800);
		//click on the 'Ok' in the Pop up
		Alert alert=driver.switchTo().alert();
		alert.accept();
		}
	
		Thread.sleep(3000);
		//Capturing the Screen Message
	    String sreturnValue =driver.findElement(ScreenOutPut).getText();
	 	return sreturnValue;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
