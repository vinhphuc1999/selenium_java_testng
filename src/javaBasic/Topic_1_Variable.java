package javaBasic;

import java.util.Scanner;

public class Topic_1_Variable {
	static int studentNumber;
	static boolean status;
	static final String browserName = "Chrome";

	String studentName = "Automation FC";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(studentNumber);
		System.out.println(status);
		Topic_1_Variable topic_01 = new Topic_1_Variable();
		System.out.println(topic_01.studentName);
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println(name);
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
