package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;

import AbstractComponents.AbstractComponents;

public class MyCartPage extends AbstractComponents {

	WebDriver driver;

	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By matchwait = By.cssSelector(".totalRow button");

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartedprod;

	public List<WebElement> CartProductConfirmation() {
		List<WebElement> cartedproducts = cartedprod;
		return cartedprod;
	}

	public Boolean verifymatch(String productname) {
		List<WebElement> cartedproducts = CartProductConfirmation();
		Boolean productmatch = cartedprod.stream().anyMatch(cartedprod -> cartedprod.getText().equalsIgnoreCase(productname));
		return productmatch;

	}

	public PaymentPage checkoutpage() {
		ClickVisibilityWait(matchwait);
		PaymentPage paymentpage = new PaymentPage(driver);
		return paymentpage;
	}

}
