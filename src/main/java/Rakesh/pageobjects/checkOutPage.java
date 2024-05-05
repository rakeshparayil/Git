package Rakesh.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Rakesh.AbstractComponents.Abstractcomponent;

public class checkOutPage extends Abstractcomponent {
WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[placeholder='Select Country']") 
	WebElement country;
	//@FindBy(css=".action__submit") 
	//WebElement submit;
	@FindBy(css =".ta-item:nth-of-type(2)") 
	WebElement selectCountry;
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryname)
	{
		Actions a = new Actions(driver);
		//in actions cant use pagefactory
	       
	       a.sendKeys(country,countryname).build().perform();
	       waitForElemenToAppear(By.cssSelector(".ta-results"));
	       selectCountry.click();
	}
	
	public confirmationPage submitOrder()
	{
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));

	       JavascriptExecutor js = (JavascriptExecutor) driver;

	        js.executeScript("arguments[0].click();", submit);
	submit.click();
	 return new confirmationPage(driver);
	}
	
	
}
	
	
	
       


