package Rakesh.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Rakesh.pageobjects.Cartpage;
import Rakesh.pageobjects.OrderPage;

public class Abstractcomponent {
	
	WebDriver driver;
	public Abstractcomponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']") //substituting above step
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']") //substituting above step
	WebElement orderHeader;
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	//parent class to all pageobject classes
	public void waitForElemenToAppear(By findby) //return type is by
	//locator should come as a argument
	//return type is By, variable is findby which come from pageobject who is child 
	//of abstract class- from productcatalogue child
	{
     WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
     //enable driver
     //pageobject classes has knowledge about driver
     //child to paraent one can send variable
     //send from childpage landing page to here
     //applu super(driver)
     //create constructor to recieve superkeyword
    
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	//(By.cssSelector(".mb-3"))); this not a webelement
	//return type for this is By
	}
	public void waitForWebElemenToAppear(WebElement findby)
	{
	     WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.visibilityOf(findby));
		
		}
	
	public Cartpage goTOCartPage()
	{
		cartHeader.click();//can be accesses from either landing or product catalog page
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;
	}
	public OrderPage goTOOrdersPage()
	{
		orderHeader.click();//can be accesses from either landing or product catalog page
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//here it is webelement and not 'By' the return type
		
	}
	
}
