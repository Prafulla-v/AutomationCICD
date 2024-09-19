package FrameWork.FrameWork.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjects.LoginPage;

public class BaseClass {

	public LoginPage loginpage;
	public WebDriver driver = null;

	public WebDriver Initializedriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Intelliagent1\\Downloads\\CICD\\FrameWork\\src\\test\\java\\FrameWork\\FrameWork\\Resources\\GlobalData.properties");
		prop.load(fis);
		// String browsername = System.getProperty("browser")!=null ?
		// System.getProperty("browser") :prop.getProperty("browser");
		String browsername = prop.getProperty("browser");

		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browsername.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchapplication() throws IOException {
		driver = Initializedriver();
		loginpage = new LoginPage(driver);
		loginpage.GoTo();
		return loginpage;
	}

	@AfterMethod(alwaysRun = true)
	public void closebrowser() {
		driver.close();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// WebElement element = driver.findElement(By.cssSelector(".toast-error"));
		// File src = element.getScreenshotAs(OutputType.FILE); -- particular element

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // -- wholepage screenshot
		// screenshot
		File perm = new File("C:\\Users\\Intelliagent1\\Downloads\\CICD\\FrameWork\\Reports\\errorscreenshot.png");
		FileUtils.copyFile(src, perm);
		return "C:\\Users\\Intelliagent1\\Downloads\\CICD\\FrameWork\\Reports\\errorscreenshot.png";
	}
}
