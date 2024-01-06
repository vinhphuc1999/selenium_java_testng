package javaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_9_Break_Continue {
	static int studentNumber;
	static boolean status;
	static final String browserName = "Chrome";

	String studentName = "Automation FC";

	// Primitive type/ value type: Kiểu dữ liệu nguyên thủy
	byte var_1;
	short var_2;
	int var_3;
	long var_4;
	float var_5;
	double var_6;
	char var_7;
	boolean var_8;
	
	// Reference type: Kiểu dữ liệu tham chiếu
	// String
	String addressString = "Ho Chi Minh";
	// Array 
	String[] studentAddress = {addressString, "Ho Chi Minh"};
	// Class
	public Topic_9_Break_Continue topic;
	// Interface
	WebDriver driver;
	// Object
	Object aObject;
	// Collection
	// List/ Set/ Queue/ Map
	List<WebElement> homePageLink = driver.findElements(By.tagName(""));
	Set<String> allWindows = driver.getWindowHandles();
	List<String> productName = new ArrayList<String>();
	
	public void clickToElement() {
		addressString.trim();
		studentAddress.clone();
		driver.getCurrentUrl();
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	// Getter: getCurrentUrl/ getTitle/ getText/ getAttribute/ getCssValue/ getSize/
	// getLocation/ getPosition/ v.v
	public String getStudentName() {
		return this.studentName;
	}

	// Setter: click/ sendKeys/ clear/ select/ back/ forward/ refresh/ get/ v.v
	public void setStudentName(String stdName) {
		this.studentName = stdName;
	}

}
