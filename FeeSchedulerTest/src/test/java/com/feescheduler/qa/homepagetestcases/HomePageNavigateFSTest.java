package com.feescheduler.qa.homepagetestcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.feescheduler.qa.ExtentReportListener.AllureReportListener;
import com.feescheduler.qa.base.BaseTest;
import com.feescheduler.qa.pages.FeeSchedulerLoginPage;
import com.feescheduler.qa.pages.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Listeners({AllureReportListener.class})
public class HomePageNavigateFSTest extends BaseTest {
	FeeSchedulerLoginPage feeschedulerloginPage;
	HomePage homePage;

	public HomePageNavigateFSTest() {
		super();
	}

	@BeforeMethod
	public void setUP() throws InterruptedException {
		initialization();
		feeschedulerloginPage = new FeeSchedulerLoginPage();
		feeschedulerloginPage.clickOnLogin();
		Thread.sleep(3000);
		homePage = feeschedulerloginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(3000);
		String title = feeschedulerloginPage.verifyPageTitle();
		System.out.println(title);
	    Thread.sleep(5000);
		if (title.equalsIgnoreCase("Login – ReimbursementCodes")) {
			feeschedulerloginPage.TerminateSession();
			Thread.sleep(5000);
			homePage = feeschedulerloginPage.login_afterTermination(prop.getProperty("username"),
					prop.getProperty("password"));
			Thread.sleep(10000);

		}
	}

	@Test(priority = 1,description = "Navigate to Fee Scheduler")
	@Severity(SeverityLevel.CRITICAL)
	@Description("TestCase Description : Verify after clicking on  Fee Scheduler tab navigate to Fee Scheduler Page ")
	@Story("Story Name : To check Fee scheduler navigation")
	public void navigateToFeeSchedulerTest() throws InterruptedException {
		homePage.clickOnFeeSchedulerTab();
		if (homePage.checkNumberOfTab() == 1) {
			homePage.clickOnKillSessionPopUpVisible();
			Thread.sleep(3000);
		}

		else {
			homePage.switchTabToFeeScheduler();
		

		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
