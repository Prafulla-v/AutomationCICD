package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By meswait = By.cssSelector(".ng-trigger-flyInOut");
	@FindBy(css="#userEmail")
	WebElement username;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	
	@FindBy(css=".btn-block")
	WebElement click;
	
	@FindBy(css=".ng-trigger-flyInOut")
	WebElement message;
	
	public String ErrorMessage() {
		VisibilityWait(meswait);
		return message.getText();
	}
	
	
	public void LoginApplication(String usrname, String pssword) {
		username.sendKeys(usrname);
		password.sendKeys(pssword);
		click.click();
		
	}
	
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	

}