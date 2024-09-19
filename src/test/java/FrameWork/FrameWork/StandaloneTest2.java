package FrameWork.FrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import FrameWork.FrameWork.TestComponents.BaseClass;
import PageObjects.LoginPage;
import PageObjects.MyCartPage;
import PageObjects.OrderPage;
import PageObjects.PaymentPage;
import PageObjects.ProductPage;

public class StandaloneTest2 extends BaseClass {

	String productname = "ZARA COAT 3";

	@Test(dataProvider = "data")
	public void StandAloneTest2(String EmailId, String Password, String productname)
			throws IOException, InterruptedException {

		loginpage.LoginApplication(EmailId, Password);
		ProductPage productpage = new ProductPage(driver);
		productpage.getproducts();
		WebElement prod = productpage.getproductbyname(productname);
		productpage.addproducttocart(productname);

		MyCartPage cartpage = new MyCartPage(driver);
		cartpage.CartProductConfirmation();
		Boolean match = cartpage.verifymatch(productname);
		Assert.assertTrue(true);
		PaymentPage paymentpage = cartpage.checkoutpage();
		paymentpage.PlaceOrder();
		paymentpage.confirmationmessage();
		String confirmationmessage = paymentpage.confirmationmessage();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("Thankyou for the order."));

		System.out.println("Execution completed");

	}

	@Test(dependsOnMethods = "StandAloneTest2")
	public void OrderHistoryTest() throws InterruptedException {

		loginpage.LoginApplication("amruta@outlook.com", "Amruta@123");
		OrderPage orderpage = new OrderPage(driver);
		Thread.sleep(2000);
		orderpage.ProductConfirmation();
		Boolean productmatch = orderpage.OrderConfirmation(productname);
		Assert.assertTrue(true);
	}

	@DataProvider
	public Object[][] data() {
		return new Object[][] { { "amruta@outlook.com", "Amruta@123", "ZARA COAT 3" },
				{ "VenkateshShetty@outlook.com", "Amruta@123", "ADIDAS ORIGINAL" } };
	}

//	@DataProvider
//	public Object[][] data() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email","amruta@outlook.com");
//		map.put("password","Amruta@123");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email","VenkateshShetty@outlook.com");
//		map1.put("password","Amruta@123");
//		
//		return new Object[][] {{map},{map1}};
//	}
}