package FrameWork.FrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
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

public class ExcelDrivenRunning extends BaseClass {

	String productname = "ZARA COAT 3";
	DataFormatter df = new DataFormatter();

	@Test(dataProvider = "ExcelData")
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

	@DataProvider(name = "ExcelData")
	public Object[][] ExcelData() throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("C:/Users/Intelliagent1/Downloads/EcommerceCredentialsLogin.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		Row rows = sheet.getRow(0);
		int colcount = rows.getLastCellNum();
		Object data[][] = new Object[rowcount - 1][colcount];

		for (int i = 0; i < rowcount - 1; i++) {
			Row row = sheet.getRow(i + 1);
			for (int j = 0; j < colcount; j++) {
				Cell cell = row.getCell(j);
				data[i][j] = df.formatCellValue(cell);
			}
		}
		return data;

	}

}