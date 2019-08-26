package com.CustomerCarePageObject;

import mq.seleniumFW.connection.MQDBConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utility.Navigate;
import Utility.WaitTool;

public class SendEmail 

{
	

			private WebDriver driver;
			public Navigate oNavigate;
			WaitTool oWaitTool;
			private MQDBConnection oDBConnection;

			public By ddlSubject=By.id("strSubject");
			public By ddlPriority=By.id("strPriority");
			public By ddlMessage=By.id("strMessage");
			public By ddladd = By.id("btnaddcustomernublist");
			public By ddlupdate = By.id("btnupdatecustomernublist");
			public By btnsave = By.id("btnsave");
			public By btncancel = By.id("btncancel");
			public By ValidationMessage=By.id("ctl00__infoSpan");	


			String ScreenValidation;
			//num10 id




			public SendEmail(WebDriver driver,MQDBConnection dbConnection)

			{
				this.driver=driver;
				this.oDBConnection=dbConnection;
				this.oNavigate=new Navigate(driver,oDBConnection); 
				PageFactory.initElements(driver, this);
			}


			public  String SendEmailScreen(String sSubject, String sPriority ,String sMessage,String sAddorUpdate,String sCustomerNbr, String sCount ,String sSendorCancel) throws Exception

			{

				
				if(sSubject.length()!=0)
				{ 
					Thread.sleep(1000);
					driver.findElement(ddlSubject).clear();
					driver.findElement(ddlSubject).sendKeys(sSubject+Keys.TAB);
				}
				Thread.sleep(1000);

				if(sPriority.length()!=0)
				{
					new Select(driver.findElement(ddlPriority)).selectByVisibleText(sPriority);
				}
				Thread.sleep(1000);

				if(sMessage.length()!=0)
				{ 
					Thread.sleep(1000);
					driver.findElement(ddlMessage).clear();
					driver.findElement(ddlMessage).sendKeys(sMessage+Keys.TAB);
				}
				Thread.sleep(1000);

				if(sAddorUpdate.contains("Add"))
				{
					driver.findElement(ddladd).click();
				}

				else 
				{
					driver.findElement(ddlupdate).click();
				}

				if(sCustomerNbr.length()!=0)
				{ 
					Thread.sleep(1000);
					//				driver.findElement(ddlSubject).clear();
					driver.findElement(By.id("num"+sCount+"0")).sendKeys(sCustomerNbr+Keys.TAB);
					Thread.sleep(3000);
				}
				Thread.sleep(1000);

				if(sSendorCancel.contains("Send"))
				{
					driver.findElement(btnsave).click();
				}

				else 
				{
					driver.findElement(btncancel).click();
				}

				ScreenValidation=driver.findElement(ValidationMessage).getText();
				Thread.sleep(4000);
				System.out.println(ScreenValidation);

				return ScreenValidation;

			}


}
