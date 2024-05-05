package Rakesh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import Rakesh.AbstractComponents.Abstractcomponent;

public class confirmationPage extends Abstractcomponent {
	
	WebDriver driver;
	
	public confirmationPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(css=".hero-primary") 
		WebElement confirmationMessage;
		
		public String getConfirmationMessage()
		{
			return confirmationMessage.getText();
			//assertions not in pageobject 
		}
		
		
		/* String thankyou= driver.findElement(By.cssSelector(".hero-primary")).getText();
      Assert.assertEquals(thankyou,"Thankyou for the order.");*/
		
	}


