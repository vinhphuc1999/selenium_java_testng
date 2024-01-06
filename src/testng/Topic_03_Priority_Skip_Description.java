package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority_Skip_Description {
		@Test (priority = 1)
		public void EndUser_Create_New_Employee() {
			
		}
		@Test (enabled = false)
		public void EndUser_View_New_Employee() {
			
		}
		@Test (priority = 3 , description = "JIRA-7979 - Create a new employee and verify!")
		public void EndUser_Edit_New_Employee() {
			
		}
		@Test (priority = 4)
		public void EndUser_Move_New_Employee() {
			
		}
	}
