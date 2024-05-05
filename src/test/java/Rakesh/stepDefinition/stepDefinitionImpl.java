package Rakesh.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Rakesh.Testcomponents.BaseTest;
import Rakesh.pageobjects.Cartpage;
import Rakesh.pageobjects.LandingPage;
import Rakesh.pageobjects.ProductCatalogue;



import Rakesh.pageobjects.checkOutPage;
import Rakesh.pageobjects.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
	
	public LandingPage landingpage; 
	public ProductCatalogue ProductCatalogue;
	public confirmationPage confirmationPage;

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException
	{
		landingpage =launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$") 
	//^$ for regular expression
	public void logged_in_with_username_and_password(String username, String password)
	//catching variables in strings
	{
		ProductCatalogue = landingpage.LoginAPPLICATION(username,password);
	}
	@When("^ I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName)
	{
		 List<WebElement> products = ProductCatalogue.getProductList();
	       ProductCatalogue.addProductToCart(productName);
	}
	@When(" ^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		 Cartpage cartpage =ProductCatalogue.goTOCartPage();//childclass has access to parent class
	        Boolean match = cartpage.VerifyProductDisplay(productName);
	       Assert.assertTrue(match); //assertions not in page objects
	       checkOutPage checkOutPage = cartpage.goToCheckout();
	       checkOutPage.selectCountry("india");
	       confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_confirmationpage(String string)
	{
		 String confirmationmessage = confirmationPage.getConfirmationMessage();
	      Assert.assertTrue(confirmationmessage.equalsIgnoreCase(string));
	}
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
		
		Assert.assertEquals(strArg1, landingpage.geterrormessage());

    }
	//tidy gherkin a chrome plugin can pull all methods automatically from feature file
}


