package webdriver;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_PVI_Mix_Implicit_Explicit_Wait {
	WebDriver driver;
	Random ran = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email = "pvphuc" + getRandomNumber() + "@gmail.com";
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	String CCCD = "z4226170206224_8dbfadb6b980a81ffde6a0392546ac1a.jpg";
	String CCCD_1 = "z4226170253468_394943efba8bdce07798d6d1fdc821ad.jpg";

	String CCCDFilePath = projectPath + "\\uploadFiles" + CCCD;
	String CCCDFilePath_1 = projectPath + "\\uploadFiles" + CCCD;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		// ImplicitlyWait ảnh hưởng đến việc tìm Element (findElement, findElements)
		driver.manage().window().maximize();
		// Ép kiểu của driver sang JavascriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
	}

//	@Test
//	public void TC_01_Element_Found() {
//		// Element có xuất hiện và không cần chờ timeout
//		// Dù có set 2 loại wait thì cũng không ảnh hưởng
//		explicitWait = new WebDriverWait(driver, 15);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("https://facebook.com/");
//		// ExplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
//		System.out.println("Thời gian kết thúc: " + getTimeStamp());
//		// ImplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		driver.findElement(By.cssSelector("input#email"));
//		System.out.println("Thời gian kết thúc: " + getTimeStamp());
//	}

//	@Test
//	public void TC_02_Element_Not_Found_Implicit() {
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.get("https://facebook.com/");
//		// ImplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		try {
//			driver.findElement(By.cssSelector("input#selenium"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Thời gian kết thúc: " + getTimeStamp());
//		}
//		// Output
//		// Có cơ chế tìm lại element sau mỗi 0.5s
//		// Khi hết timeout sẽ đánh fail testcase này
//		// Throw ra 1 exception: NoSuchElement
//	}

//	@Test
//	public void TC_04_Element_Not_Found_Implicit_Explicit() {
//		// 3.1 - Implicit = Explicit
//		// 3.2 - Implicit > Explicit
//		// 3.3 - Implicit < Explicit
//		driver.get("https://facebook.com/");
//		explicitWait = new WebDriverWait(driver, 5);
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		// ImplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		try {
//			driver.findElement(By.cssSelector("input#selenium"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Thời gian kết thúc: " + getTimeStamp());
//		}
//		// ExplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		try {
//			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
//		} finally {
//			System.out.println("Thời gian kết thúc: " + getTimeStamp());
//		}
//	}

//	@Test
//	public void TC_04_Element_Not_Found_Explicit_By() {
//		explicitWait = new WebDriverWait(driver, 5);
//		driver.get("https://gofile.io/welcome");
//		// Expicit - By là tham số nhận vào của hàm explicit -
//		// visibilityOfElementLocated(By)
//		// Implicit = 0
//		// Tổng time = Explicit
//		// ExplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		try {
//			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
//		} finally {
//			System.out.println("Thời gian kết thúc: " + getTimeStamp());
//		}
//	}
	
//	@Test
//	public void TC_05_Element_Not_Found_Explicit_Element() {
//		explicitWait = new WebDriverWait(driver, 5);
//		driver.get("https://gofile.io/welcome");
//		// Expicit - By là tham số nhận vào của hàm explicit -
//		// visibilityOfElementLocated(By)
//		// Implicit = 0
//		// Tổng time = Explicit
//		// ExplicitWait
//		System.out.println("Thời gian bắt đầu: " + getTimeStamp());
//		try {
//			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
//		} finally {
//			System.out.println("Thời gian kết thúc: " + getTimeStamp());
//		}
//	}
	
	@Test
	public void TC_06_Real() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://gofile.io/welcome");
		// Wait 
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		// Action
		driver.findElement(By.cssSelector(null)).sendKeys(null);
		
	}

	// Hàm thực thi code javascript
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	// Hàm lấy domain
	public String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain;");
	}

	// Hàm lấy text
	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	// Hàm scroll đến cuối trang
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	// Hàm di chuyển trang url
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	// Hàm click vào một element
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	// Hàm scroll đến đầu trang
	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	// Hàm scroll đến cuối trang
	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	// Hàm senkey
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');",
				getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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

	// Show ra time-Stamp tại thời điểm gọi hàm
	// time-Stamp: ngày-giờ-phút-giây
	public String getTimeStamp() {
		Date date = new Date(0);
		return date.toString();
	}
}
