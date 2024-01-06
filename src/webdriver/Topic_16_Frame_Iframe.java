package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_Iframe {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		// Verify Facebook iframe hiển thị
		// Facebook iframe vẫn là 1 element của trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'plugins')]")).isDisplayed());
		// Chỉ cần switch qua đúng iframe chứa element đó
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'plugins')]")));
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"168K followers");
		// Switch về main page
		driver.switchTo().defaultContent();
		// Switch qua iframe khác
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.xpath("//div[@ng-if='isMobile != true']")).click();
		driver.findElement(By.xpath(
				"//input[@class='input_name standalone mobile_error_icon meshim_widget_widgets_TextField ltr ng-pristine ng-valid desktop']"))
				.sendKeys("Phung Vinh Phuc");
		driver.findElement(By.xpath(
				"//input[@class='input_phone mobile_error_icon meshim_widget_widgets_PhoneField ltr ng-scope ng-pristine ng-valid desktop']"))
				.sendKeys("0986745978");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Devtest");
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).click();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("excel");
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys(Keys.ENTER);
		List<WebElement> courseName = driver.findElements(By.xpath("//div[@class='content']//h4"));
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

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
