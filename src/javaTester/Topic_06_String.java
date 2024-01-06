package javaTester;

import java.util.Random;

public class Topic_06_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://automationfc.vn/khoa-hoc/fullstack-selenium-java/113994";
		String username = "admin";
		String password = "admin";
		String[] arrayUrl = url.split("//");
		url = arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
	}

}
