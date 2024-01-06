package testng;

import org.testng.Assert;

public class Topic_02_Assert {

	public static void main(String[] args) {
		String fullname = "Automation Testing";
		// TODO Auto-generated method stub
		// Assert
		// Mong đợi 1 điều kiện trả về ĐÚNG
		Assert.assertTrue(fullname.contains("Automation"));
		// Mong đợi 1 điều kiện trả về SAI
		Assert.assertFalse(fullname.contains("Manual"));
		// Mong đợi bằng nhau
		Assert.assertEquals(fullname, "Automation Testing");
	}

}
