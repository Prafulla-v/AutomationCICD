package FrameWork.FrameWork;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandaloneTest {
	public static void main(String[] args) {
		
		String productname = "ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = null;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("amruta@outlook.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Amruta@123");
		driver.findElement(By.cssSelector(".btn-block")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button[class='btn w-10 rounded']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartedprods = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartedprods.stream().anyMatch(cartedprod -> cartedprod.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(true);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button"))).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results button:last-of-type")));
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));
		String confirmationmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		}
}