package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
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

	// Case 1: Chỉ có duy nhất 2 window hoặc 2 tab
	@Test
	public void TC_01_ID() {
		// Parent Page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Window và tab sẽ có 2 hàm để lấy ra ID của window và tab đó
		// 1 - Hàm lấy ra ID của window và tab mà driver đang đứng (active)
		String parentPageWindowID = driver.getWindowHandle();
		System.out.println("Parent Page =" + parentPageWindowID);

		// Click vào google link để bật ra một tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		// Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(parentPageWindowID)) {
				driver.switchTo().window(id);
			}

		}
		// Nếu như ID nào khác với ID của parent thì switch vào
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		// Quay về main Page
		String googleWindowID = driver.getWindowHandle();
		switchToWindowByID(googleWindowID);
		System.out.println(driver.getCurrentUrl());

	}

	// Case 2: Nhiều hơn 2 window hoặc 2 tab
	@Test
	public void TC_02_Title() {
		// Parent Page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Click vào google link để bật ra một tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		// Quay về main Page
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByPageTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getCurrentUrl(), "https://tiki.vn/");
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		switchToWindowByPageTitle("Lazada - Mua Sắm Hàng Chất Giá Tốt Online");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.lazada.vn/");
	}

	// Dùng được cho duy nhất 2 ID (Window / Tab)
	public void switchToWindowByID(String oderID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(oderID)) {
				driver.switchTo().window(id);
			}
		}
	}

	// Dùng được cho từ 2 ID (Window / Tab) trờ lên
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		int count = 1;
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			// Switch từng ID trước
			driver.switchTo().window(id);
			// Lấy ra title của page này
			String actualPageTitle = driver.getTitle();
			System.out.println("Actual title = " + actualPageTitle + " - " + count);
			count++;
			// Kiểm tra title page hiện tại với title page mong muốn
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	// Đóng tất cả các Window ngoại trừ main Window
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); // Set không cho phép trùng/ null
		// Dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
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
