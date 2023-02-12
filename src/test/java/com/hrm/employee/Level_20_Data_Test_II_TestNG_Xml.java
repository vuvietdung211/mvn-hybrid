package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.hrm.DashBoardPO;
import pageObject.hrm.EmployeeListPO;
import pageObject.hrm.LoginPageObject;
import pageObject.hrm.MyInfoPO;
import pageObject.hrm.PageGeneratorManager;
import pageObject.hrm.PersonalDetailPO;


public class  Level_20_Data_Test_II_TestNG_Xml extends BaseTest{
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - STEP 01: Open browser '" + browserName + "' and  navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		adminUserName = "Admin";
		adminPassword = "admin123";
		empFirstName = "Atomation";
		empLastName = "FC";
		empUserName = "automation";
		empPassword = "Automation@123";
		statusValue = "Enabled";
		empFullName = empFirstName + " " + empLastName;
		
		
		log.info("Pre-Condition - STEP 02: Log in with usernam = " + adminUserName + "and password = " + adminPassword);
		loginPage.enterToTextboxByID(driver, adminUserName, "txtUsername");
		loginPage.enterToTextboxByID(driver, adminPassword, "txtPassword");
		loginPage.clickToButtonByID(driver, "btnLogin");
		dashboardPage = PageGeneratorManager.getDashboardPO(driver);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01_Step_01:Open Employee List Page ");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List" );
		employeeListPage = PageGeneratorManager.getEmployeeList(driver);
		
		log.info("Add_New_01 - Step_02:Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		
		log.info("Add_New_01 - Step_03:Input valid value to 'First Name' textbox");
		employeeListPage.enterToTextboxByID(driver,empFirstName,"firstName");
		
		log.info("Add_New_01 - Step_04:Input valid value to 'Last Name' textbox");
		employeeListPage.enterToTextboxByID(driver,empLastName,"lastName");
		
		log.info("Add_New_01 - Step_05:Get value of 'Employee ID' ");
		employeeListPage.getValueTextboxByID(driver, "value","employeeId");
		
		log.info("Add_New_01 - Step_06:Click to 'Create Login Details' checkbox");
		employeeListPage.checkToCheckBoxByLabel(driver, "Create Login Details");
		
		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		employeeListPage.enterToTextboxByID(driver, empUserName,"user_name");
		
		log.info("Add_New_01 Step 08: Enter valid info to 'Password' textbox");
		employeeListPage.enterToTextboxByID(driver, empPassword,"user_password");
		
		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		employeeListPage.enterToTextboxByID(driver, empPassword,"re_password");
		
		log.info("Add_New_01 Step 10: Select 'Enabled' value in 'Status' dropdown");
		employeeListPage.selectDropdownByID(driver,"Enabled","status");
		
		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		personalDetailPage = PageGeneratorManager.getPersonalDetailPO(driver);
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		personalDetailPage.openSubMenuPage(driver, "PIM", "Employee List" );
		employeeListPage = PageGeneratorManager.getEmployeeList(driver);
		
		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		employeeListPage.enterToTextboxByID(driver, empFullName,"empsearch_employee_name_empName");
		
		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.isJQueryAjaxLoadedSuccess(driver);
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.isJQueryAjaxLoadedSuccess(driver);
		
		log.info("Add_New_01 - Step 15: Verify Employee Infomation displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInTableIDAtRowColumnIndex(driver, "resultTable", "Last Name", "1"), "Anderson");
		
	}
	

	@Test
	public void Employee_02_Upload_Avatar_Employee() {
		log.info("Upload_Avatar_02 - Step 01: Log in with Employee role");
		employeeListPage.logoutToSystem(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.loginToSystem(driver, empUserName, empPassword);
		dashboardPage = PageGeneratorManager.getDashboardPO(driver);
	
		
		log.info("Upload_Avatar_02 - Step 02: Open Personal Detail page");
		dashboardPage.openMenuPage(driver, "My Info");
		personalDetailPage = PageGeneratorManager.getPersonalDetailPO(driver);
		
		log.info("Upload_Avatar_02 - Step 03: Click to Change Photo image");
		personalDetailPage.clickToChangePhotoImage();
		
		log.info("Upload_Avatar_02 - Step 04: Upload new photo image");
		personalDetailPage.uploadImage(driver, avatarFilePath);
		
		log.info("Upload_Avatar_02 - Step 04: Upload new photo image");
		personalDetailPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Upload_Avatar_02 - Step 05: Verify new photo upload is displayed");
		personalDetailPage.isUploadAvatarSuccessMessageDisplayed();
		personalDetailPage.isNewAvatarImageDisplayed();
			
	}
	
	@Test
	public void Employee_03_Update_Info() {
		log.info("Personal_Detail_03 - Step 01: Open 'Personal Details' tab at Side bar");
		
		
		log.info("Personal_Detail_03 - Step 02: Verify all fields at 'Personal Details' tab are disabled");
		
		
		log.info("Personal_Detail_03 - Step 03: Click to 'Edit' button at 'Personal Details' form");
		
		
		log.info("Personal_Detail_03 - Step 04: Verify 'Employee Id' textbox is disabled");
		
		
		log.info("Personal_Detail_03 - Step 05: Verify 'Driver's License Number' textbox is disabled");
		
		
		log.info("Personal_Detail_03 - Step 06: Verify 'SSN Number' textbox is disabled");
		
		
		log.info("Personal_Detail_03 - Step 07: Verify 'SIN Number' textbox is disabled");
		
		
		log.info("Personal_Detail_03 - Step 08: Verify 'Date of Birth' textbox is disabled");
		
		
		log.info("Personal_Detail_03 - Step 09: Enter new value to 'First Name' textbox");
		
		
		log.info("Personal_Detail_03 - Step 10: Enter new value to 'Last Name' textbox");
		
		
		log.info("Personal_Detail_03 - Step 11: Select new value to 'Gender' radio button");
		
		
		log.info("Personal_Detail_03 - Step 12: Select new value to 'Marital Status' dropdown");
		
		
		log.info("Personal_Detail_03 - Step 13: Select new value to 'Nationality' dropdown");
		
		
		log.info("Personal_Detail_03 - Step 14: ");
	}
	
	
	
	@AfterClass
	public void afterClass() {
		log.info("Post-Condition - STEP 01: Close browser");
		driver.quit();
	}
	private WebDriver driver;

	LoginPageObject loginPage;
	PersonalDetailPO personalDetailPage;
	EmployeeListPO employeeListPage;
	DashBoardPO dashboardPage;
	MyInfoPO myInfoPage;
	String adminUserName, adminPassword, empFirstName, empLastName, empUserName, empPassword, employeeID, statusValue;
	String empFullName;
	String avatarFilePath = GlobalConstants.UPLOAD_FOLDER_PATH + "ninhbinh.jpg";
}
