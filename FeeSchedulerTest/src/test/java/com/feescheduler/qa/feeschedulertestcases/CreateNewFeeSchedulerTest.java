package com.feescheduler.qa.feeschedulertestcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.feescheduler.qa.ExtentReportListener.AllureReportListener;
import com.feescheduler.qa.base.BaseTest;
import com.feescheduler.qa.pages.FeeSchedulerLoginPage;
import com.feescheduler.qa.pages.FeeSchedulerPage;
import com.feescheduler.qa.pages.HomePage;
import com.feescheduler.qa.util.Xls_Reader;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * <p>
 * <b>37965-Create a Fee Schedule with positive data</b>
 * </p>
 * <p>
 * 1.In RC3 application URL click on the login button enter login credentials
 * (FSMMIT1/FSMMIT1).
 * </p>
 * <p>
 * 2.Go to the FEE SCHEDULER tab on the left side of the screen click on it.
 * <p>
 * 3.A new window of Fee Scheduler appears.
 * <p>
 * 4.Click on FS management tab.
 * <p>
 * 5.Click on Create New Fee Sheduler Button.
 * 
 * <p>
 * 6.Enter Fee Scheduler Name.
 * <p>
 * 7.Enter Fee Scheduler description.
 * <p>
 * 8.Enter effective date.
 * <p>
 * 9.Enter termination Date.
 * <p>
 * 10.Enter AWP percentage.
 * <p>
 * 11.Click on check AWP check box.
 * <p>
 * 12.Click on Save button.
 * <p>
 * 13.Search newly created Fee scheduler.
 * <p>
 * 14. Get the FS name and verify with input xls data.
 * <p>
 * 15.Click on Edit button.
 * <p>
 * 16.Validate the Name and check box is checked.
 *
 * @author manas jena
 *
 */

@Listeners({ AllureReportListener.class })
public class CreateNewFeeSchedulerTest extends BaseTest {
	FeeSchedulerLoginPage feeschedulerloginPage;
	HomePage homePage;
	FeeSchedulerPage feeSchedulePage;
	String feeScheduleName;
	String description;
	String effectiveDate;
	String terminationDate;
	String pricingMethodology;
	String percentageApplied;

	public CreateNewFeeSchedulerTest() {
		super();
	}

	@BeforeMethod
	public void setUP() throws InterruptedException {
		initialization();
		feeschedulerloginPage = new FeeSchedulerLoginPage();
		feeSchedulePage = new FeeSchedulerPage();
		feeschedulerloginPage.clickOnLogin();
		Thread.sleep(3000);
		homePage = feeschedulerloginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(3000);
		String title = feeschedulerloginPage.verifyPageTitle();
		System.out.println(title);
		Thread.sleep(5000);
		if (title.equalsIgnoreCase("Login ??? ReimbursementCodes")) {
			feeschedulerloginPage.TerminateSession();
			Thread.sleep(5000);
			homePage = feeschedulerloginPage.login_afterTermination(prop.getProperty("username"),
					prop.getProperty("password"));
			Thread.sleep(10000);

		}
	}

	@Test(priority = 1, description = "Create New FeeScheduler with Positive Data")
	@Severity(SeverityLevel.CRITICAL)
	@Description("TestCase Description : Create New FeeScheduler with Positive Data ")
	@Story("Story Name : To check Creation New FeeScheduler with Positive Data")
	public void createNewFeeSchedulerTest() throws InterruptedException {
		SoftAssert softAssertion = new SoftAssert();
		String path = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\feescheduler\\qa\\testdata\\AppData.xlsx";
		Xls_Reader reader = new Xls_Reader(path);

		homePage.clickOnFeeSchedulerTab();
		if (homePage.checkNumberOfTab() == 1) {
			homePage.clickOnKillSessionPopUpVisible();
			Thread.sleep(5000);
		}

		else {
			homePage.switchTabToFeeScheduler();
		}

		homePage.switchTabToParentTab();
		homePage.switchTabToFeeScheduler();

		feeSchedulePage.clickOnFeeSchedulerMgmt();
		Thread.sleep(5000);

		int rowCount = reader.getRowCount("FeeScheduleMgmt");

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			try {
				feeScheduleName = reader.getCellData("FeeScheduleMgmt", "Fee Schedule Name", rowNum);
				description = reader.getCellData("FeeScheduleMgmt", "Description", rowNum);
				effectiveDate = reader.getCellData("FeeScheduleMgmt", "Effective Date", rowNum);
				terminationDate = reader.getCellData("FeeScheduleMgmt", "Termination Date", rowNum);
				percentageApplied = reader.getCellData("FeeScheduleMgmt", "Percentage Applied", rowNum);

//			System.out.println(feeScheduleName);
//			System.out.println(description);
//			System.out.println(effectiveDate);
//			System.out.println(terminationDate);
//			System.out.println(percentage);
				feeSchedulePage.clickOnCreateNewButton();
				Thread.sleep(3000);
				feeSchedulePage.enterFeeSchedulerName(feeScheduleName);
				feeSchedulePage.enterFeeSchedulerDesc(description);
				feeSchedulePage.selectEffectiveDate(effectiveDate);
				feeSchedulePage.selectTerminateDate(terminationDate);

				feeSchedulePage.enterAWPPercentage(percentageApplied);

				feeSchedulePage.clickOnAWPCheckBox();
				feeSchedulePage.clickOnSaveButton();
				Thread.sleep(3000);
				feeSchedulePage.searchFS(feeScheduleName);
				Thread.sleep(3000);
				feeSchedulePage.clickOnSearchButton();
				Thread.sleep(3000);
				String str1 = feeSchedulePage.getText();
				Thread.sleep(3000);
				softAssertion.assertTrue(feeScheduleName.equalsIgnoreCase(str1), "Fee schedule is not created");
				feeSchedulePage.clickOnRow();
				feeSchedulePage.clickEeditExistingFeeSchedule();
				String str2 = feeSchedulePage.getFSNameEdit();
				softAssertion.assertTrue(feeScheduleName.equalsIgnoreCase(str2),
						"FS name is not matching in edit page");
				boolean checkBoxStatus = feeSchedulePage.getcheckBoxStatus();
				softAssertion.assertTrue(checkBoxStatus);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
