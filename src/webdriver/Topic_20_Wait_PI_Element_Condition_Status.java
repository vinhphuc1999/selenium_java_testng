package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_PI_Element_Condition_Status {
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
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)
		// Wait cho email address textbox hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
		}

	@Test
	public void TC_02_Invisible_Undisplayed_Innisibility() {
		driver.get("https://www.facebook.com/");
		// 1. Không có trên UI (bắt buộc)
		// 1. Có trong HTML
		// Wait cho email address textbox hiển thị
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("firstname")));
	}

	@Test
	public void TC_02_Invisible_Undisplayed_Innisibility_II() {
		driver.get("https://www.facebook.com/");
		// 1. Không có trên UI (bắt buộc)
		// 1. Không có trong HTML
		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("firstname")));
	}

	@Test
	public void TC_03_Precence_I() {
		driver.get("https://www.facebook.com/");
		// 1. Có trên UI 
		// 1. Có trong HTML (bắt buộc)
		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	
	@Test
	public void TC_03_Precence_II() {
		driver.get("https://www.facebook.com/");
		// 1. Không có trên UI 
		// 1. Có trong HTML (bắt buộc)
		
		// Chờ cho Re-enter Email textbox precense trong HTML hiển thị trong vòng 10s
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstname")));
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		// 1. Không có trên UI (bắt buộc)
		// 1. Không có trong HTML

		// Chờ cho Re-enter Email textbox precense trong HTML hiển thị trong vòng 10s
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// Phase 1: Element có trong HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("firstname"));
		// Thao tác với element khác làm cho element re-enter không có trong DOM nữa
		// Close popup đi
		driver.findElement(By.cssSelector("img.id='u_f_9_0F'")).click();
		// Chờ cho Re-enter Email textbox không hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.stalenessOf((reEnterEmailAddressTextbox)));
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
}
