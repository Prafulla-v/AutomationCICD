package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By listtoappear = By.cssSelector(".mb-3");
	By toastwait = By.cssSelector("#toast-container");
	By roundwait = By.cssSelector(".ng-animating");
	By cartproduct = By.cssSelector(".card-body button[class='btn w-10 rounded']");

	@FindBy(css = (".mb-3"))
	List<WebElement> productlist;
	@FindBy(css = ("[routerlink*='cart']"))
	WebElement clickoncartbutton;

	public List<WebElement> getproducts() {
		VisibilityWait(listtoappear);
		return productlist;
	}

	public WebElement getproductbyname(String productname) {
		WebElement prod = productlist.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addproducttocart(String productname) throws InterruptedException {
		WebElement prod = getproductbyname(productname);
		Thread.sleep(1000);
		prod.findElement(cartproduct).click();
		VisibilityWait(toastwait);
		InVisibilityWait(roundwait);
		clickoncartbutton.click();
	}
}