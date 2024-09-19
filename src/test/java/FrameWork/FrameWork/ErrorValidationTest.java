package FrameWork.FrameWork;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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
import org.testng.Reporter;
import org.testng.annotations.Test;

import FrameWork.FrameWork.TestComponents.BaseClass;
import PageObjects.LoginPage;
import PageObjects.MyCartPage;
import PageObjects.PaymentPage;
import PageObjects.ProductPage;

public class ErrorValidationTest extends BaseClass {
	@Test
	public void LoginErrorValidation() throws IOException {

		String productname = "ZARA COAT 3";

		loginpage.LoginApplication("amruta@outlook.com", "Amruta@@123");
		Assert.assertEquals("Incorrect email password.", loginpage.ErrorMessage());
		Reporter.log("ErrorMessage validated successfully", true);

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {

		loginpage.LoginApplication("amruta@outlook.com", "Amruta@123");
		ProductPage productpage = new ProductPage(driver);
		productpage.getproducts();
		WebElement prod = productpage.getproductbyname("ZARA COAT 3");
		productpage.addproducttocart("ZARA COAT 3");

		MyCartPage cartpage = new MyCartPage(driver);
		cartpage.CartProductConfirmation();
		Boolean match = cartpage.verifymatch("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}