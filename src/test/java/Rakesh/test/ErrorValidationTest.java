package Rakesh.test;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Rakesh.Testcomponents.BaseTest;
import Rakesh.Testcomponents.Retry;
import Rakesh.pageobjects.Cartpage;
import Rakesh.pageobjects.ProductCatalogue;
import Rakesh.pageobjects.checkOutPage;
import Rakesh.pageobjects.confirmationPage;

public class ErrorValidationTest extends BaseTest {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException
	{
		
		 landingpage.LoginAPPLICATION("ns@mail.com", "Rs@15");
	Assert.assertEquals("Incorrect email or password.", landingpage.geterrormessage());
		//div[@aria-label='Incorrect email or password.']
		//div[aria-label='Incorrect email or password.']
		
	}
	
	@Test
	public void productErrorValidation() throws IOException
	{
		String productname = "ZARA COAT 3";
	    ProductCatalogue ProductCatalogue = landingpage.LoginAPPLICATION("nsw1@gmail.com", "Rs@123456");
       List<WebElement> products = ProductCatalogue.getProductList();
       ProductCatalogue.addProductToCart(productname);
       Cartpage cartpage =ProductCatalogue.goTOCartPage();//childclass has access to parent class
        Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
       Assert.assertTrue(match); //assertions not in page objects
       

	}


}
