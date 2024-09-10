package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul/li[3]/button")
	WebElement clickonorder;

	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> Orderpageprods;

	public List<WebElement> ProductConfirmation() {
		clickonorder.click();
		List<WebElement> products = Orderpageprods;
		return Orderpageprods;
	}

	public Boolean OrderConfirmation(String productname) {
		
		List<WebElement> products = ProductConfirmation();
		Boolean match = products.stream().anyMatch(cartedprod -> cartedprod.getText().equalsIgnoreCase(productname));
		return match;
	}

}
