package Rakesh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import Rakesh.AbstractComponents.Abstractcomponent;



public class Cartpage extends Abstractcomponent  {
	WebDriver driver;
	@FindBy(css=".cartSection h3") 
	List<WebElement> productTitles;
	@FindBy(css=".totalRow button") 
	WebElement CheckouEle;
	
	public Cartpage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productname)
	{
		Boolean match= productTitles.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
	    return match;
	}
	
	public checkOutPage goToCheckout()
	{
		CheckouEle.click();
		return new checkOutPage(driver);
	}
	/*List<WebElement> cartproducts= driver.findElements(By.cssSelector(".cartSection h3"));
       
       Boolean match= cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
       
       //traverse from parent to child .classname space tagname
       driver.findElement(By.cssSelector(".totalRow button")).click();*/

}
