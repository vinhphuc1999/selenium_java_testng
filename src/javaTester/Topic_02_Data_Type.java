package javaTester;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Kieu du lieu nguyen thuy (Primitive)
		// So nguyen: byte short int long (khong co phan thap phan)
		byte bNumber = 127;
		
		short Number = 32000;
		
		int iNumber = 499233299;
		
		long lNumber = 83294;
		
		System.out.println(bNumber);
		System.out.println(Number);
		System.out.println(iNumber);
		System.out.println(lNumber);
		
		// So thuc: float double (co phan thap phan)
		float studenPoint = 9.5f;
		
		double employeeSalary = 35.6d;
		
		System.out.println(studenPoint);
		System.out.println(employeeSalary);
		
		// Logic: boolean
		boolean status = true; //Nam
		
		status = false; //Nu
		
		System.out.println(status);
		
		// Ki tu
		char a = 'A';
		
		System.out.println(a);
		
		// Kieu du lieu tham chieu (Reference)
		// Ngoai cac kieu du lieu tren thi con lai la cac kieu du lieu tham chieu
		// Class
		// FirefoxDriver driver = new FirefoxDriver();
		
		// Interface
		// WebElement firstNameTextbox;
		
		// String
		String firstName = "Automation Testing";
		
		System.out.println(firstName);
		
		// Object
		// Object people;
		
		// Array
		// String[] studentName = {"Nguyen Van A", "Le Van Hung", "Nguyen Thi Loan"};
		
		// Collection: List/ Set/ Queue
		// List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		// Map
		// Map<String, Integer> student = new HashMap<String, Integer>();
	}

}
