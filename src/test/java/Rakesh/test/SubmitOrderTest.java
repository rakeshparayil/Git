package Rakesh.test;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Rakesh.Testcomponents.BaseTest;
import Rakesh.pageobjects.Cartpage;
import Rakesh.pageobjects.LandingPage;
import Rakesh.pageobjects.OrderPage;
import Rakesh.pageobjects.ProductCatalogue;
import Rakesh.pageobjects.checkOutPage;
import Rakesh.pageobjects.confirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	String productname = "ZARA COAT 3";
	@Test(dataProvider="getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input ) throws IOException
	{
		
		ProductCatalogue ProductCatalogue = landingpage.LoginAPPLICATION(input.get("email"), input.get("password"));
       List<WebElement> products = ProductCatalogue.getProductList();
       ProductCatalogue.addProductToCart(input.get("product"));
       Cartpage cartpage =ProductCatalogue.goTOCartPage();//childclass has access to parent class
        Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
       Assert.assertTrue(match); //assertions not in page objects
       checkOutPage checkOutPage = cartpage.goToCheckout();
       checkOutPage.selectCountry("india");
       confirmationPage confirmationPage = checkOutPage.submitOrder();
       String confirmationmessage = confirmationPage.getConfirmationMessage();
      Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
      

	}
	//verify if ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		 ProductCatalogue ProductCatalogue = landingpage.LoginAPPLICATION("nsw@gmail.com", "Rs@12345");
		 OrderPage OrderPage =ProductCatalogue.goTOOrdersPage();
		 Assert.assertTrue(OrderPage.VerifyOrderDisplay(productname));
	}
	
	
	//Extend reports
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty(("user.dir")+"src\\test\\java\\Rakesh\\data\\PuchaseOrder.json"));
		return new Object[][] { {data.get(0)} , {data.get(1)}};
		
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "nsw@gmail.com");
		map.put("password", "Rs@12345");
		map.put("product", "ZARA COAT 3");
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "ravikg22@gmail.com");
		map1.put("password", "@Rr12345");
		map1.put("product", "ADIDAS ORIGINAL");
		 // 2-d array return to method-datatype object
		//as we unsure of datatype , object-accepts any kind of datatype
		//second one is valid id and password*/
		
	}

}
