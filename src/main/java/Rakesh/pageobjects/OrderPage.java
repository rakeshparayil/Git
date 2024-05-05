package Rakesh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import Rakesh.AbstractComponents.Abstractcomponent;



public class OrderPage extends Abstractcomponent  {
	WebDriver driver;
	@FindBy(css="tr td:nth-child(3)") 
	List<WebElement> productNames;
	
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyOrderDisplay(String productname)
	{
		Boolean match= productNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
	    return match;
	}
	


}
