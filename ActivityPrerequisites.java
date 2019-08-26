package com.ProvisioningPageObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utility.Navigate;
import Utility.WaitTool;
import mq.seleniumFW.connection.MQDBConnection;

public class ActivityPrerequisites 
{

	
			private WebDriver driver;
			public Navigate oNavigate;
			WaitTool oWaitTool;

			private MQDBConnection oDBConnection;
			public ArrayList<Hashtable<String, String>> records;
			public Hashtable<String,String> record;


			public By btnsave = By.name("ctl00$uxPgCPH$btnsave");
			public By btncancel = By.name("ctl00$uxPgCPH$btncancel");

			public By ddlProvSystem = By.name("ctl00$uxPgCPH$ddlProvSystem");
			public By ddlServiceClass = By.name("ctl00$uxPgCPH$ddlServiceClass");

			public By ddladd = By.id("ctl00_uxPgCPH_ancAdd");
			public By ddlupdate = By.id("ctl00_uxPgCPH_ancUpdate");
			public By ValidationMessage=By.id("ctl00__infoSpan");



			String ScreenValidation;
			int record1;

			public ActivityPrerequisites(WebDriver driver,MQDBConnection dbConnection)

			{
				this.driver=driver;
				this.oDBConnection=dbConnection;
				this.oNavigate=new Navigate(driver,oDBConnection); 
				PageFactory.initElements(driver, this);
			}




			// Need to call this method to add one action one pre-RequisiteAction to the given Prov system.  
			public  void ProvActivityPrerequisitesMapping(String sProvisioningSystem, String sServiceClass ,String sActionId,String sRequisiteAction,String sBehaviour,String sSentOptional, int sColoumNumber) throws Exception

			{
				// Deleteing the actions already mapped with that Given Provisioning System

				String InactiveTheOldActivityPrerequisites = "update provsion_activity_prerequisite set STATUS='I'  where SYSTEM_ID IN (select PROV_SYSTEM_ID from PROVISIONING_SYSTEM  WHERE PROV_SYSTEM_NAME='"+sProvisioningSystem+"') and STATUS='A'";
				this.record1=oDBConnection.updateRecords(InactiveTheOldActivityPrerequisites);
				System.out.println(" Records are updated and commited succefully");
				Thread.sleep(2000);

				if(sProvisioningSystem.length()!=0)
				{
					new Select(driver.findElement(ddlProvSystem)).selectByVisibleText(sProvisioningSystem);
				}
				Thread.sleep(3000);

				if(sServiceClass.length()!=0)
				{
					new Select(driver.findElement(ddlServiceClass)).selectByVisibleText(sServiceClass);
				}
				Thread.sleep(3000);

				driver.findElement(ddladd).click();
				int i=sColoumNumber;
				int j=i+1;

				System.out.println("i value is " +i);
				System.out.println("Then j value is " +j);
				Thread.sleep(3000);
				String getActionCode="select UPPER(ACTION_DESC) from PROVSION_ACTION where ACTION_ID='"+sActionId+"'";
				this.records=oDBConnection.fecthRecords(getActionCode);
				this.record=this.records.get(0);
				String getActionCode1=record.get("UPPER(ACTION_DESC)");
				System.out.println("Action desc  is "+getActionCode1);

				// Getting the Action Code from the data base with the Given RequisiteAction
				String getRequisiteActionCode="select UPPER(ACTION_DESC) from PROVSION_ACTION where ACTION_ID='"+sRequisiteAction+"'";
				this.records=oDBConnection.fecthRecords(getRequisiteActionCode);
				this.record=this.records.get(0);
				String getRequisiteActionCode1=record.get("UPPER(ACTION_DESC)");
				System.out.println("Action desc  is "+getRequisiteActionCode1);

				if(sActionId.length()!=0)
				{
					new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvActPreRequisites']/tbody/tr["+j+"]/td[1]/select"))).selectByVisibleText(getActionCode1);
				}
				Thread.sleep(2000);

				if(sRequisiteAction.length()!=0)
				{
					new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvActPreRequisites']/tbody/tr["+j+"]/td[2]/select"))).selectByVisibleText(getRequisiteActionCode1);
				}
				Thread.sleep(2000);

				if(sBehaviour.length()!=0)
				{
					new Select(driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvActPreRequisites']/tbody/tr["+j+"]/td[3]/select"))).selectByVisibleText(sBehaviour);
				}
				Thread.sleep(2000);

				if(sSentOptional.contains("Y"))
				{
					driver.findElement(By.xpath(".//*[@id='ctl00_uxPgCPH_gvActPreRequisites']/tbody/tr["+j+"]/td[4]/span/input")).click();
				}

				else 
				{
					System.out.println("Send Optional as N");
				}

				Thread.sleep(2000);
				driver.findElement(ddlupdate).click();
				Thread.sleep(3000);

				// updateing the value's and  to be handle the save button in the test case itself.
    }

}

