package Rakesh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Rakesh.AbstractComponents.Abstractcomponent;

public class ProductCatalogue extends Abstractcomponent {
	
	WebDriver driver; //driver to be initialized from constructor, created here as local variable
    //line 1
	public ProductCatalogue( WebDriver driver) //calling webdriver
	//driver scope is within this methos only
	{
		super(driver);
		this.driver=driver; //this local driver equals the driver in another clas
		//create object in other class to call this,
		//this will send driver scope to the entire class
		PageFactory.initElements(driver, this);
		//first argument is driver and second is this which is current class driver in line 1
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3")); //elements
	
	

	//Pagefactory designed pattern
	@FindBy(css=".mb-3") //substituting above step
	List<WebElement> products;
	@FindBy(css=".ng-animating") //substituting above step
	WebElement spinner;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	public List<WebElement> getProductList()
	{
		waitForElemenToAppear(productsBy);// once product loaded return them
		return products;//returning list<WebElement> and not void
		//this action method return products to the test case
		
	}
	
	//action method with nice description to get product by name
	public WebElement getProductByName(String productName)//product name should come from test
	//products is replaced by getProductList() because products will wait till the elemnet apperar
	//as from previous step and instead of putting in a variable just return it
	{
		 WebElement prod = getProductList().stream().filter(s->s.findElement(By.cssSelector("b"))
		    .getText().equals(productName)).findFirst().orElse(null);
		 return prod; //return webelement and not void
		 //get PoductList() substituted from previous actionmethod for fast execution
	}
	
	public void addProductToCart(String productName) // productname should come from test
	{
		//The actual which we find in above step.findelement
		//By method and add to cart as pagefactory can be used only in case of driver.
		//As scope of search is within product which we get in 'getProductByName' the 
		//previous action method
		//create object of that method to call it within the scope
		WebElement prod = getProductByName (productName); //prod from previous method
		prod.findElement(addToCart).click();//by locator not pagefactory
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElemenToAppear(toastMessage);
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//CALLED FROM ABSTRACT CLASS like previously in this  class
		//expecting By. and not driver.
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		waitForElementToDisappear(spinner);
	}

	
	
	
}
