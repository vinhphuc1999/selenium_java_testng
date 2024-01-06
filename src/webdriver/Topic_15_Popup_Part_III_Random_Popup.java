package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Popup_Part_III_Random_Popup {
	WebDriver driver;
	Random ran = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email = "pvphuc" + getRandomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		// ImplicitlyWait ảnh hưởng đến việc tìm Element (findElement, findElements)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Random_In_DOM_1() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
		By lePopup = By.xpath("//div[@id='lepopup-popup-123'] //div[@data-page='1']");
		if (driver.findElement(lePopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.lepopup-input input[name='lepopup-10']")).sendKeys(email);
			driver.findElement(By.xpath("//span[text()='Get the Books']")).click();
			Assert.assertEquals(driver.findElement(By.xpath(
					"//div[@id='lepopup-popup-123'] //h4[@style='text-align: center; font-size: 18px; font-weight: bold;']"))
					.getText(), "Thank you!");
			Assert.assertTrue(driver.findElement(By.xpath(
					"//div[@class='lepopup-element-html-content'] //p[text()='Your sign-up request was successful. We will contact you shortly. Please ']"))
					.getText().contains("Your sign-up request was successful."));
			sleepInSecond(5);
			driver.findElement(By.cssSelector("a.tie-search-trigger-mobile span.tie-icon-search")).click();
			driver.findElement(By.cssSelector("input.tie-popup-search-input")).click();
			driver.findElement(By.cssSelector("input.tie-popup-search-input")).sendKeys("Agile Testing Explained");
			driver.findElement(By.cssSelector("button.tie-popup-search-submit span.tie-icon-search")).click();
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).getText(),
					"Agile Testing Explained");

		} else {
			driver.findElement(By.cssSelector("a.tie-search-trigger-mobile span.tie-icon-search")).click();
			driver.findElement(By.cssSelector("input.tie-popup-search-input")).click();
			driver.findElement(By.cssSelector("input.tie-popup-search-input")).sendKeys("Agile Testing Explained");
			driver.findElement(By.cssSelector("button.tie-popup-search-submit span.tie-icon-search")).click();
		}
	}

//	@Test
//	public void TC_02_Random_In_DOM_2() {
//		driver.get("https://www.facebook.com/");
//	}
//
//	@Test
//	public void TC_03_Not_Random_In_DOM() {
//		driver.get("https://www.facebook.com/");
//	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
