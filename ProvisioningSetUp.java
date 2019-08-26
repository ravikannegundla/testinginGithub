package com.ProvisioningPageObject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProvisioningSetUp
{

	static WebDriver driver;
	public By ScreenOutPut =By.xpath(".//*[@id='ctl00__infoSpan']");
	public By locScreenBalance = By.xpath(".//*[@id='ctl00_uxPgCPH_uxSearchCtrl_sctrlresultdisplaytbl4']/tbody/tr/td[3]/div/span");
	public By locOperationScreens = By.xpath(".//*[@id='label1']");
	String ScreenMessage;
	
	public By locuxSearchCtrltxtCustnbr = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtCustnbr");
	public By locuxSearchCtrlsearchimgcustsrchimg = By.name("ctl00$uxPgCPH$uxSearchCtrl$searchimg$custsrchimg");
	public By locuxSearchCtrltxtsearch_external_id = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_external_id");
	public By locuxSearchCtrltxtsearch_device_no = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_device_no");
	public By locuxSearchCtrltxtsearch_contact = By.name("ctl00$uxPgCPH$uxSearchCtrl$txtsearch_contact");
	public By loctxtStatus = By.name("ctl00$uxPgCPH$txtStatus");
	public By locbtnsave = By.name("ctl00$uxPgCPH$btnsave");
	public By locbtncancel = By.name("ctl00$uxPgCPH$btncancel");


	public By loccmbContract = By.name("ctl00$uxPgCPH$cmbContract");
	public By loccmbPlanName = By.name("ctl00$uxPgCPH$cmbPlanName");
	public By loccmbService = By.name("ctl00$uxPgCPH$cmbService");

	
	
    String ScreenValidation,PoolStatus;	
		
		
	public ProvisioningSetUp(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
			// TODO Auto-generated constructor stub
	}



	public String ProvisioningSetUpScreen(String CustomerNumber,String ContractNumber,String Plan,String ServiceNumber,String Status,String ParameterVoip,String ParameterPassword,String ParameterService,String SaveOrCancelBtn,String ReturnValue) throws InterruptedException 
	{
	   //Enter the Customer Number in the customer field text box
		if(CustomerNumber.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
		driver.findElement(locuxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);
		}
		Thread.sleep(1000);
		//Select the contract number
		if(ContractNumber.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
		new Select(driver.findElement(loccmbContract)).selectByVisibleText(ContractNumber);
		}
		Thread.sleep(600);
		//Select the Plan number
		if(Plan.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
		new Select(driver.findElement(loccmbPlanName)).selectByVisibleText(Plan);
		}
		Thread.sleep(600);
		//Select the Service number
		if(ServiceNumber.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
		new Select(driver.findElement(loccmbService)).selectByVisibleText(ServiceNumber);
		}
		Thread.sleep(600);
	    //Enter the Status in the Status field text box
		if(Status.isEmpty())
		{
		System.out.println("\n");
		}
		else
		{
		driver.findElement(loctxtStatus).sendKeys(Status);
		}


		Thread.sleep(3000);
		//finding the total number of Rows in grid
	    List<WebElement> Row= driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvParameters']")).findElements(By.tagName("tr"));
	    int RowSize=Row.size();
	    System.out.println("Row Size is :"+RowSize);
	    for(int i=1;i<=RowSize;i++)
	    {
		Thread.sleep(1000);
	    //calculating the no.of Columns in a Particular Rows
		List<WebElement> Col= Row.get(i).findElements(By.tagName("td"));
		System.out.println("Col.size()----"+Col.size());
		for(int j=1;j<=RowSize;j++)
		{
		Thread.sleep(2000);
	    //Capturing the VOIP number
		String VOIP = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvParameters']/tbody/tr[1]/td[2]")).getText();
		System.out.println("VOIP is : "+VOIP);
		String Password = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvParameters']/tbody/tr[2]/td[2]")).getText();
		System.out.println("Password is : "+Password);
		String Service = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvParameters']/tbody/tr[3]/td[2]")).getText();
        System.out.println("Service is : "+Service);
        Thread.sleep(2000);
        if(VOIP.contains(ParameterVoip))
        {
        ReturnValue = VOIP;
        System.out.println("ReturnValue present inside the grid is : "+ReturnValue);
        break;
        }
      
        
		}
		break;
	    }

	    Thread.sleep(1000);
	    //click on the save or cancel button
	    if(SaveOrCancelBtn.isEmpty())
	    {
	    System.out.println("\n");	
	    }
	    else if(SaveOrCancelBtn.contains("Cancel"))
	    {
	    //Click on the cancel button	
	    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btncancel']")).click();
	    }
	    else
	    {
		System.out.println("\n");
	    }
	    //Capturing the Page tittle
		 String PageTittle = driver.findElement(By.xpath(".//*[@id='ctl00_pagetitle']")).getText();
		 Thread.sleep(4000);
	    
	    System.out.println("Page Tittle is : "+PageTittle);
		return PageTittle;
	}
	
	
	//if we remove the custom Procedure in data base at that time we are going to use this method for the reserved number in the number reservation grid
	public String ProvisioningSetUpSearch(String CustomerNumber,String ContractNumber,String Plan,String ServiceNumber,String Status,String VIOPNumber,String Category,String Pool,String SearchOrCncelOrCloseButton,String SaveOrCancelBtn) throws InterruptedException
	{
		   //Enter the Customer Number in the customer field text box
			if(CustomerNumber.isEmpty())
			{
			System.out.println("\n");
			}
			else
			{
			driver.findElement(locuxSearchCtrltxtCustnbr).sendKeys(CustomerNumber+Keys.TAB);
			}
			Thread.sleep(1000);
			//Select the contract number
			if(ContractNumber.isEmpty())
			{
			System.out.println("\n");
			}
			else
			{
			new Select(driver.findElement(loccmbContract)).selectByVisibleText(ContractNumber);
			}
			Thread.sleep(600);
			//Select the Plan number
			if(Plan.isEmpty())
			{
			System.out.println("\n");
			}
			else
			{
			new Select(driver.findElement(loccmbPlanName)).selectByVisibleText(Plan);
			}
			Thread.sleep(600);
			//Select the Service number
			if(ServiceNumber.isEmpty())
			{
			System.out.println("\n");
			}
			else
			{
			new Select(driver.findElement(loccmbService)).selectByVisibleText(ServiceNumber);
			}
			Thread.sleep(600);
		    //Enter the Status in the Status field text box
			if(Status.isEmpty())
			{
			System.out.println("\n");
			}
			else
			{
			driver.findElement(loctxtStatus).sendKeys(Status);
			}

			//Click on the Voip Search button present beside the voip input parameter 
			driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvParameters_ctl02_searchimg']")).click();
			
			//It navigates to the search Voip numbers Pop up  window,in this we can search based on the Voip number or Category or Pool
			//Switching to Voip Numbers Popup window
		    Thread.sleep(2000);
		    driver.switchTo().frame("modalDialogiFrame");
		    Thread.sleep(2000);
		    //we can search based on number or category or pool
		    //based on Voip number
		    if(VIOPNumber.isEmpty())
		    {
		    System.out.println("\n");
		    }
		    else
		    {
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_txtVoipNo']")).sendKeys(VIOPNumber);
		    }
		    //based on Category
		    if(Category.isEmpty())
		    {
		    System.out.println("\n");
		    }
		    else
		    {
		    new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ddlCategory']"))).selectByVisibleText(Category);
		    }
		    //based on Pool
		    if(Pool.isEmpty())
		    {
		    System.out.println("\n");
		    }
		    else
		    {
		    new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_ddlPool']"))).selectByVisibleText(Pool);
		    }
		    Thread.sleep(3000);
		    //Click on the search Or cancel  Or close button
		    if(SearchOrCncelOrCloseButton.contains("Close"))
		    {
		    //Click on the close button in the Search Voip numbers window
		    driver.findElement(By.xpath(".//*[@id='btnclose']")).click();
		    }
		    else if(SearchOrCncelOrCloseButton.contains("Cancel"))
		    {
			//Click on the cancel button in the Search Voip numbers window	   
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btncancel']")).click();	
		    }
		    else
		    {
			//Click on the Search button in the Search Voip numbers window	   
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsearch']")).click();	
		    }
		   // Thread.sleep(1000);
		    
			Thread.sleep(3000);
			//finding the total number of Rows in grid
		    List<WebElement> Row1= driver.findElement(By.className("gridlist")).findElements(By.tagName("tr"));
		    int RowSize1=Row1.size();
		    System.out.println("Row Size is :"+RowSize1);
		    for(int k=0;k<=RowSize1;k++)
		    {
			Thread.sleep(1000);
		    //calculating the no.of Columns in a Particular Rows
			List<WebElement> Col1= Row1.get(k).findElements(By.tagName("td"));
			System.out.println("Col.size()----"+Col1.size());
			for(int l=1;l<=RowSize1;l++)
			{
		    String SearchViopNumber = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).getText();
		    System.out.println("SearchViopNumber--"+SearchViopNumber);
		    String SearchCategoryValue = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[2]")).getText();
		    System.out.println("SearchCategoryValue--"+SearchCategoryValue);   
		    String SearchPoolText = driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[3]")).getText();
		    System.out.println("SearchPoolText--"+SearchPoolText); 
		    Thread.sleep(4000);
		    //Comparing with the input values
		    if(VIOPNumber.isEmpty())
		    {
		    //comparing with category description
		    if(SearchCategoryValue.contains(Category))
		    {
		    //click on the edit button
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).click();
		    break;
		    }
		    else
		    {
		    //click on the pool button
			driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).click();
	        break;
		     }
		    }
		    else if(Category.isEmpty())
		    {
		    
		    //comparing with category description
		    if(SearchViopNumber.contains(VIOPNumber))
		    {
	        //click on the edit button
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).click();
		    break;
			 }
		     else
		     {
		    //click on the pool button
			driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).click();
			break;
		     }
		    
		    }
		    else
		    {
		     //comparing with category description
		     if(SearchViopNumber.contains(VIOPNumber))
		    {
	        //click on the link button button
			 driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).click();
			 break;
			 }
		     else
		    {
		    //click on the Category button		
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvReserNum']/tbody/tr/td[1]/a")).click();
			break;
			}
		    	
		    }
	        }//for loop for l
			break;
		    }//for loop for k
		    
	        
			
		    Thread.sleep(2000);
		  
		    //swith to default page
		    driver.switchTo().defaultContent();
		    Thread.sleep(6000);
			


		    //Thread.sleep(1000);
		    //click on the save or cancel button
		    if(SaveOrCancelBtn.isEmpty())
		    {
		    System.out.println("\n");	
		    }
		    else if(SaveOrCancelBtn.contains("Cancel"))
		    {
		    //Click on the cancel button	
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btncancel']")).click();
		    }
		    else
		    {
			//Click on the save button	
		    driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_btnsave']")).click();
		    }
		
			//Capturing the screen Output 
			Thread.sleep(4000);
			ScreenValidation=driver.findElement(ScreenOutPut).getText();
		    System.out.println("Screen Validation is : "+ScreenValidation);
	        
			return ScreenValidation;
			}
	
	
	
	
	

}
