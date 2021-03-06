package com.feescheduler.qa.pages;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.feescheduler.qa.base.BaseTest;
import com.feescheduler.qa.util.VisibleElement;

import io.qameta.allure.Step;

public class HomePage extends BaseTest {

	@FindBy(xpath = "//a/span[contains(text(),'Fee Scheduler')]")
	WebElement FeeSchedulerTab;

	@FindBy(xpath = "//button[contains(text(),'Kill Session and Open')]")
	WebElement KillSessionAndOpen;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@Step("Fee scheduler tab is displayed...")
	public boolean feeScheduerIsDisplayed() {
		return FeeSchedulerTab.isDisplayed();
	}

	@Step("Click on fee scheduler tab...")
	public void clickOnFeeSchedulerTab() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", FeeSchedulerTab);

	}

	public void visibleFeeSchedulerTab() {
		VisibleElement.visibileElement(driver, FeeSchedulerTab, 200);
	}

	@Step("Kill anther session and open new pop up clicking...")
	public boolean isKillSessionPopUpVisible() {
		return KillSessionAndOpen.isDisplayed();
	}

	public void clickOnKillSessionPopUpVisible() {
		KillSessionAndOpen.click();
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	} // isAlertPresent()

//	public void clickOnFeeSchedulerTab() {
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", FeeSchedulerTab);
//		
//	

//		if (KillSessionAndOpen.isDisplayed()) {
//			KillSessionAndOpen.click();
//		}
//		else {
//			System.out.println("Kill session popUp window is not available.");
//		}

	// return new FeeSchedulerPage();

	// }

	@Step("Switching to Fee scheduler tab..")
	public void switchTabToFeeScheduler() {
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				// Switch to the opened tab
				driver.switchTo().window(actual);

			}
		}

	}

	@Step("Switching back to home page tab..")
	public void switchTabToParentTab() {
		String currentHandle = driver.getWindowHandle();

		driver.switchTo().window(currentHandle);

	}

	@Step("Number of opened tab checking...")
	public int checkNumberOfTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No. of tabs: " + tabs.size());
		return tabs.size();
	}

}
