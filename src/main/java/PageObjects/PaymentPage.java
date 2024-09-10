package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By clickwait = By.cssSelector(".ta-results button:last-of-type");
	By lastwait = By.cssSelector(".hero-primary");
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountry;
	@FindBy(css = ".ta-results button:last-of-type")
	WebElement countryclick;
	@FindBy(css = ".action__submit")
	WebElement clickonplace;
	@FindBy(css = ".hero-primary")
	WebElement cmessage;

	public void PlaceOrder() {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, "india").build().perform();
		VisibilityWait(clickwait);
		countryclick.click();
		clickonplace.click();
	}

	public String confirmationmessage() {
		VisibilityWait(lastwait);
		String confirmation = cmessage.getText();
		return confirmation;
	}

}