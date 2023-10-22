package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biến WebElement element
		// By: id/ class/ name/ xpath/ css/ tagname/ linkText/ partialLinkText

	}

	@Test
	public void TC_01_Login() {
		// Tương tác với Element thì phải tìm được Element đó
		// Thông qua locator của nó
		driver.get("https://r2.gdesk.io/auth/login");
		
		WebElement user_name = driver.findElement(By.xpath("//div[@class='user-box']/input[@class='field-input undefined']"));
		// Xóa dữ liệu đi trước khi nhập text
		// Dùng cho các textbox/ textarea/ dropdown (Edit table)
		user_name.clear();
		// Nhập dữ liệu
		user_name.sendKeys("phuc.phung@namlongsoft.net");
		
		WebElement pass_word = driver.findElement(By.xpath("//div[@class='pwd-box']/input[@class='field-input undefined']"));
		// Xóa dữ liệu đi trước khi nhập text
		// Dùng cho các textbox/ textarea/ dropdown (Edit table)
		pass_word.clear();
		// Nhập dữ liệu
		pass_word.sendKeys("Pvpgtvt2017!");
		
		WebElement log_in = driver.findElement(By.xpath("//div[@class='gd-getstarted v2-btn-main gd-btn-getstarted fw-500 ']"));
		// Click vào các button/ link/ checkbox/ radio/ v.v
		log_in.click();
		
//		// Lấy giá trị của các thuộc tính
//		String searchAttribute = element.getAttribute("");
//		// GUI: Font/ Size/ Location / .v.v
//		// Lấy giá trị CSS
//		element.getCssValue(searchAttribute);
//		// Giá vị trí của element so với website
//		Point point = element.getLocation();
//		point.x = 324;
//		point.y = 324;
//		// Kích thước của element
//		Dimension di = element.getSize();
//		di.getHeight();
//		di.getWidth();
//		// Kết hợp cả Location và getSize
//		Rectangle rec = element.getRect();
//		// Chụp hình khi testcase fail
//		element.getScreenshotAs(OutputType.BYTES);
//		element.getScreenshotAs(OutputType.FILE);
//		element.getScreenshotAs(OutputType.BASE64);
//		// Cần lấy tên thẻ HTML của element đó
//		element.getTagName();
//		// Lấy text từ Error message success
//		element.getText();
//		// Dùng để verify xem 1 element hiển thị hoặc không
//		// Sử dụng cho tất cả Element
//		Assert.assertTrue(element.isDisplayed());
//		Assert.assertFalse(element.isDisplayed());
//		// Dùng để verify xem 1 element có thao tác được hay không
//		// Sử dụng cho tất cả Element
//		Assert.assertTrue(element.isEnabled());
//		Assert.assertFalse(element.isEnabled());
//		// Dùng để verify xem 1 element có được chọn hay chưa
//		// Sử dụng cho Checkbox/ Radio
//		Assert.assertTrue(element.isSelected());
//		Assert.assertFalse(element.isSelected());
//		// Các element nằm trong thẻ form
//		// Tương ứng với hành vi enter của end-user
//		element.submit();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
