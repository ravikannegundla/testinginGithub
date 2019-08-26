package com.ProvisioningPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProvisioningRequests 
{
	static WebDriver driver;
	public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");
	public By locScreenBalance = By.xpath(".//*[@id='ctl00_uxPgCPH_uxSearchCtrl_sctrlresultdisplaytbl4']/tbody/tr/td[3]/div/span");
	public By locOperationScreens = By.xpath(".//*[@id='label1']");
	String ScreenMessage;
	
	public By loctxtcust_no = By.name("ctl00$uxPgCPH$txtcust_no");
	public By locuxSearchCtrlcustsrchimg = By.name("ctl00$uxPgCPH$uxSearchCtrl$custsrchimg");
	public By locuniqid = By.name("ctl00$uxPgCPH$uniqid");
	public By locuxFromDate = By.name("ctl00$uxPgCPH$uxFromDate");
	public By locuxToDate = By.name("ctl00$uxPgCPH$uxToDate");
	public By locbtnretreive = By.name("ctl00$uxPgCPH$btnretreive");
	public By locbtncancel1 = By.name("ctl00$uxPgCPH$btncancel1");
	public By locproentity = By.name("ctl00$uxPgCPH$proentity");
	public By locreqtype = By.name("ctl00$uxPgCPH$reqtype");
	public By locstatusid = By.name("ctl00$uxPgCPH$statusid");
	public By locchangstatusid = By.xpath(".//*[@id='ctl00_uxPgCPH_changstatusid']");
	public By locentsearchcmbEntity = By.name("ctl00$uxPgCPH$entsearch$cmbEntity");
		
	String ScreenValidation,PoolStatus;	
	
	
	
	public ProvisioningRequests(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
			
	}


	
	// ProvisioningRequestsScreen Method
	
	public String ProvisioningRequestsScreen(String CustomerNumber,String UniqueId,String FromDate,String ToDate,String ProvisioningSystem ,String RequestType,String Status,String ChangeStatus,String OperationalEntity,String SearchOrCancelBtn,String SaveOrCancelBtn) throws InterruptedException 
	{
		// Enter the Customer Number
		if(CustomerNumber.isEmpty())
		{
		System.out.println("\n");	
		}
		else if (CustomerNumber.length()!=0)
		{
		driver.findElement(loctxtcust_no).clear();	
		driver.findElement(loctxtcust_no).sendKeys(CustomerNumber+Keys.TAB);	
		Thread.sleep(1000);
		}
	
		
		// Enter the Unique Id
		if(UniqueId.isEmpty())
		{
		System.out.println("\n");	
		}
		else if (UniqueId.length()!=0)
		{
		driver.findElement(locuniqid).sendKeys(UniqueId);	
		}
	
	
		// Enter the From Date
		if(FromDate.isEmpty())
		{
		System.out.println("\n");	
		}
		else if(FromDate.length()!=0)
		{
		driver.findElement(locuxFromDate).clear();
		driver.findElement(locuxFromDate).sendKeys(FromDate);	
		}
		
		
		// Enter the To Date
		if(ToDate.isEmpty())
		{
		System.out.println("\n");	
		}
		else if (ToDate.length()!=0)
		{
		driver.findElement(locuxToDate).clear();
		driver.findElement(locuxToDate).sendKeys(ToDate);	
		}
			
		
	    //select the Provisioning System  details in drop down list
	    if(ProvisioningSystem .isEmpty())
	    {
		System.out.println("\n");
		}
		else if(ProvisioningSystem.length()!=0)
		{ 
			  Thread.sleep(1000);
	    new Select(driver.findElement(locproentity)).selectByVisibleText(ProvisioningSystem);
		}
	  

	    //select the Request Type details in drop down list
	    if(RequestType.isEmpty())
	    {
		System.out.println("\n");
		}
		else if (RequestType.length()!=0)
		{ 
			   Thread.sleep(1000);
		     new Select(driver.findElement(locreqtype)).selectByVisibleText(RequestType);
		}
	 

	    //select the Status details in drop down list
	    if(Status.isEmpty())
	    {
		System.out.println("\n");
		}
		else if (Status.length()!=0)
		{ 
		    Thread.sleep(1000);
	    new Select(driver.findElement(locstatusid)).selectByVisibleText(Status);
		}

	    
	    
	    
	    //Click on search or cancel button
        if(SearchOrCancelBtn.contains("Cancel"))
        {
        //Click on the cancel button
        driver.findElement(locbtncancel1).click();
        }
        else
        {
        //click on the search button
        	
        	
        driver.findElement(locbtnretreive).click();
        }

	    //select the Change Status details in drop down list
	    if(ChangeStatus.isEmpty())
	    {
		System.out.println("\n");
		}
		else if (ChangeStatus.length()!=0)
		{ 
			 Thread.sleep(1000);
 	         new Select(driver.findElement(locchangstatusid)).selectByVisibleText(ChangeStatus);
		}
	   

	    //select the Operational Entity details in drop down list
	    if(OperationalEntity.isEmpty())
	    {
		System.out.println("\n");
		}
		else if(OperationalEntity.length()!=0)
		{ 
	          new Select(driver.findElement(locentsearchcmbEntity)).selectByVisibleText(OperationalEntity);
		}
	  

		
	    //select the Change Status details in drop down list
	    if(ChangeStatus.isEmpty())
	    {
		System.out.println("\n");
		}
		else if(ChangeStatus.length()!=0)
		{ 
	           new Select(driver.findElement(locchangstatusid)).selectByVisibleText(ChangeStatus);
		}
	    Thread.sleep(1000);

		
		
		//Click on the save or cancel button
		if(SaveOrCancelBtn.contains("Cancel"))
		{
		//Click on the Cancel button
		driver.findElement(locbtncancel1).click();
		}
		else
		{
		//Click on the save button
		driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
		
		
		
		//Capturing the screen Output 
		Thread.sleep(1000);
		ScreenValidation=driver.findElement(ScreenOutPut).getText();
	    System.out.println("Screen Validation is : "+ScreenValidation);
		}
	    return ScreenValidation; 	

	}

	

}
