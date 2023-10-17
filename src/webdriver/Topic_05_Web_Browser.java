package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biến WebElement element

	}

	@Test
	public void TC_01_() {
		// Đóng tab đang đứng hiện tại
		driver.close();
		// Đóng trình duyệt
		driver.quit();
		// Tìm 1 element
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("pvphuc999@gmail.com");
		// Tìm nhiều element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		// Mở một url nào đó
		driver.get("https://www.facebook.com/");
		// Trả về url của page hiện tại
		String vietnamesePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(vietnamesePageUrl,"facebook");
		// Trả về source code HTML của hiện tại
		driver.getPageSource();
		// Trả về title của website
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Facebook - Dang nhap hoac dang ki !");
		// WebDriver API - Windows / Tabs
		// Lấy ra được ID của Window / Tab mà driver đang đứng (active)
		String loginWindowID = driver.getWindowHandle();
		// Lấy tất cả ID của tất cả Window / Tab
		Set<String> allIDs = driver.getWindowHandles();
		// Cookies / Cache
		Options opt = driver.manage();
		// Login thanh cong -> Luu lai
		opt.getCookies();
		// Testcase khác -> Set cookies vào lại -> Không cần phải login nữa
		opt.logs();
		Timeouts time = opt.timeouts();
		// Khoảng thời gian chờ cho Element xuất hiện
		time.implicitlyWait(15, TimeUnit.SECONDS);
		// Khoảng thời gian chờ cho page load xong
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		// Khoảng thời gian chờ script được thực thi xong
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		win.fullscreen();
		win.maximize();
		
		//Test GUI
		win.getPosition();
		win.getSize();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		
		nav.to("https://www.facebook.com/");
		TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame("");
		tar.window("");
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
