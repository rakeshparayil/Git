package Rakesh.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Rakesh.AbstractComponents.Abstractcomponent;

public class LandingPage extends Abstractcomponent {
	//variables, methods in abstratccomponent is applicable here too
	
	WebDriver driver; //driver to be initialized from constructor, created here as local variable
    //line 1
	public LandingPage( WebDriver driver) //*constructor //calling webdriver
	//driver scope is within this methos only
	//first line of code to execute in this class
	{
		super(driver);
		this.driver=driver; //this local driver equals the driver in another clas
		//create object in other class to call this,
		//this will send driver scope to the entire class
		PageFactory.initElements(driver, this);
		//first argument is driver and second is this which is current class driver in line 1
		//pagefactory is only for driver elements
	}
	
	//WebElement userid = driver.findElement(By.id("userEmail"));
	//to bring knowledge about the driver create constructor
	// a) has same name as object
	// b) First method to execute in class
	
	

	//Pagefactory designed pattern
	@FindBy(id="userEmail") //substituting above step
	WebElement userid;
	//how this not driver without explicitly telling driver.
	//create a method initielement within object which  will initialize the elements
	@FindBy(id="userPassword") 
	WebElement passwordEle;
	
	@FindBy(id="login") 
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']") 
	WebElement errorMessage;
	
	//div[@aria-label='Incorrect email or password.']
	
	//Actionmethod for login
	
	public ProductCatalogue LoginAPPLICATION(String email, String password)
	//whatever email or password sent from test case
	{
		userid.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue ProductCatalogue = new ProductCatalogue(driver);
		return ProductCatalogue;
	}
	public String geterrormessage()
	{
		waitForWebElemenToAppear(errorMessage);//errormessage is the webelement
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
