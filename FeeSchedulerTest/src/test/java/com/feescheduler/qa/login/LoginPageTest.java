package com.feescheduler.qa.login;

import org.testng.Assert;
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

/**
 * <b> Login into Fee Scheduler</b>
 * <p>
 * 1.Login into Application.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author manas jena
 *
 */

@Listeners({ AllureReportListener.class })

public class LoginPageTest extends BaseTest {
	FeeSchedulerLoginPage feeschedulerloginPage;
	HomePage homePage;

	public LoginPageTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {
		initialization();
		feeschedulerloginPage = new FeeSchedulerLoginPage();

	}

	@Test(priority = 1, description = "Verifying login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Description("TestCase Description : Verify Fee Scheduler login page title test")
	@Story("Story Name : To check Fee scheduler login")
	public void loginPageTitleTest() {
		String title_loginPage = feeschedulerloginPage.verifyPageTitle();
		Assert.assertEquals(title_loginPage, prop.getProperty("fs_login_page_title"));
	}

	@Test(priority = 2, description = "Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("TestCase Description : Verify Fee Scheduler login test")
	@Story("Story Name : To check Fee scheduler login")
	public void loginTest() throws InterruptedException {
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
			String title1 = feeschedulerloginPage.verifyPageTitle();
			title = title1;
		}
		Assert.assertEquals(title, prop.getProperty("fs_loggedin_title"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
