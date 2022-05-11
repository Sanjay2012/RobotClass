package testClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {
	WebDriver driver;

	@BeforeTest
	public void setupApplication() {
		WebDriverManager.chromedriver().setup();
		Reporter.log("===== Browser Session Started=====", true);
		driver = new ChromeDriver();
		driver.get("https://google.com/");
		Reporter.log("=====Application Started=====", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	@Test()
	public void GoogleSetting() throws InterruptedException, AWTException {
		// Click on setting
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F10);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);

		for (int i = 0; i < 17; i++) {
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(200);
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(500);

		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		for (int i = 0; i < 3; i++) {
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
		}

		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);

		// click on Appearance link
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	}

	@Test(enabled = false)
	public void VerifyGoogleAccountName() {

		// click on sign in button
		driver.findElement(By.xpath("//*[text()='Sign in']")).click();

		// enter email
		driver.findElement(By.xpath("//*[@type=\"email\"]")).sendKeys("validEmail");

		// click on next
		driver.findElement(By.xpath("//*[text()=\"Next\"]")).click();

		// enter pass
		driver.findElement(By.xpath("//*[@type=\"password\"]")).sendKeys("validPassword");

		// click on next
		driver.findElement(By.xpath("//*[text()=\"Next\"]")).click();

		// click on profile name
		driver.findElement(By.xpath("//*[@class=\"gb_Aa gbii\"]")).click();

		// click on manage account
		driver.findElement(By.xpath("//a[@class='ksO4Qc ZWVAt R37Fhd']")).click();

		// click on personal info
		driver.findElement(By.xpath("//*[@class=\"VZLjze Wvetm zCVEd EhlvJf\"]//div[text()='Personal info']")).click();

		// get text
		String displayName = driver
				.findElement(By.xpath("//*[@id=\"c13\"]//div[@class=\"xoXYwe\"]//div[@class=\"bJCr1d\"]")).getText();
		System.out.println(displayName);
	}

	@Test(enabled = false)
	public void ReadGoogleApp() {

		// click on app menu
		driver.findElement(By.xpath("//*[name()='svg' and @class='gb_Ve']")).click();

		// By executing a java script
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are " + numberOfFrames);

		// By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
		System.out.println("The total number of iframes are " + iframeElements.size());

		// App are shown in under an IFrame
		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.switchTo().frame(1);
		// Locating all app names

		List<WebElement> allMenus = driver.findElements(By.xpath("//*[@jsname and @jsaction]//li/a/span"));

		System.out.println("Total menu count : " + allMenus.size());

		// Printing product names
		System.out.println("All Apps names are : ");
		for (WebElement ele : allMenus) {
			System.out.println(ele.getText());
		}

	}

	@AfterTest
	public void closeApplication() {
		driver.quit();
		Reporter.log("=====Browser Session End=====", true);

	}

}
