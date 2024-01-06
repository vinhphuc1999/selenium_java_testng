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

public class Topic_24_Wait_PV_Explicit_Wait {
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
		// Apply 10s cho các điều kiện/trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Thiếu thời gian để cho 1 element tiếp theo hoạt động
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		// Wait cho loading icon biến mất
		// Thiếu thời gian để cho 1 element tiếp theo hoạt động
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_03_Ajax_Loading() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		// Wait cho Date Picker hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		// Verify cho Selected Dates không có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ct100_ContentPlaceholder1_Label1")).getText(),
				"No Selected Dates to display.");
		// Wait cho ngày 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		// Click vào ngày 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		// Wait cho Ajax icon loading biến mất (Invisible)
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		// Wait cho ngày vừa được chọn được phép click trở lại
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
		// Verify cho Selected Dates là "Tueday, July 19, 2022"
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ct100_ContentPlaceholder1_Label1")).getText(),
				"Tueday, July 19, 2022");
	}

	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/welcome");
		explicitWait = new WebDriverWait(driver, 45);
		// Wait cho Add Files button được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(CCCDFilePath + "\n" + CCCDFilePath_1 + "\n");
		// Wait cho các loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));
		// Wait cho Upload message thành công được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your file have been successfully uploaded']")));
		// Wait message succes displayed
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your file have been successfully uploaded']")).isDisplayed());
		// Wait + click cho Show file button được clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		// Wait + verify cho file name và button download/play hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='CCCDFilePath.jpg']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
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
