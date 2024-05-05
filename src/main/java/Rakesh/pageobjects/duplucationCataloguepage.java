package Rakesh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Rakesh.AbstractComponents.Abstractcomponent;

public class duplucationCataloguepage extends Abstractcomponent {

	WebDriver driver;
	public duplucationCataloguepage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//two driver elements
	
	@FindBy(css=".mb-3") 
	List<WebElement> products;
	//product need to appear
	By productby = By.cssSelector(".mb-3");
	By addToCart =By.cssSelector(".card-body button:last-of-type");
	By b = By.cssSelector("b");
	By toastMessage = By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating") 
	List<WebElement> spinner;
	
	public List<WebElement> getProductList()
	{
		waitForElemenToAppear(productby);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(s->s.findElement(b)
			    .getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addtocart(String productName)
	{
		WebElement prod = getProductByName( productName);
		prod.findElement(addToCart).click();
		//this will pass the productname into getProDuctByNmae/return prod falls in 
		//addtocart
		waitForElemenToAppear(toastMessage);
	}
	
	
	
	
	//three by elements
	//three actions classes- to return prudocts(getProductList, 
	//return prod(getProductByName), add to cart(add product to cart)
	

}
