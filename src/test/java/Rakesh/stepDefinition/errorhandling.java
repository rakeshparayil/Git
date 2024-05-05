package Rakesh.stepDefinition;


import java.io.IOException;


import org.testng.Assert;

import Rakesh.Testcomponents.BaseTest;

import Rakesh.pageobjects.LandingPage;
import Rakesh.pageobjects.ProductCatalogue;




import Rakesh.pageobjects.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class errorhandling extends BaseTest {
	
	public LandingPage landingpage; 
	public ProductCatalogue ProductCatalogue;
	public confirmationPage confirmationPage;

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException
	{
		landingpage =launchApplication();
	}
	@When("^Logged in with username (.+) and password (.+)$") 
	//^$ for regular expression
	public void logged_in_with_username_and_password(String username, String password)
	//catching variables in strings
	{
		ProductCatalogue = landingpage.LoginAPPLICATION(username,password);
	}
	
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
		
		Assert.assertEquals(strArg1, landingpage.geterrormessage());

    }
}

