package com.feescheduler.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.feescheduler.qa.base.BaseTest;

import io.qameta.allure.Step;

public class FeeSchedulerPage extends BaseTest {

	@FindBy(xpath = "//li/a/span[contains(text(),'Fee Schedule')]")
	WebElement FeeSchedulerMgmt;

	@FindBy(xpath = "//button[contains(text(),'Fee Schedule')]")
	WebElement CreateNewButton;

	@FindBy(xpath = "//*[@id='feeScheduleName']")
	WebElement feeScheduleName;

	@FindBy(xpath = "//*[@id='feeScheduleDesc']")
	WebElement feeScheduleDesc;

	@FindBy(xpath = "//*[@id='effectiveDate']")
	WebElement effectiveDate;

	@FindBy(xpath = "//*[@id='terminationDate']")
	WebElement terminationDate;

	@FindBy(xpath = "//td[contains(text(),'AWP (Average Wholesale Price)')]//following::input[1]")
	WebElement AWP_percentage_Input;

	@FindBy(xpath = "//td[contains(text(),'AWP (Average Wholesale Price)')]//following::input[2]")
	WebElement AWP_CheckBox;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement SaveButton;

	@FindBy(xpath = "//*[@placeholder='Search Fee Scheduler']")
	WebElement searchFeeScheduler;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	WebElement searchButton;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")
	List<WebElement> tableRowWebTable;

	@FindBy(xpath = "//button[contains(text(),'Edit Existing Fee Schedule')]")
	WebElement editExistingFS;

	public FeeSchedulerPage() {
		PageFactory.initElements(driver, this);
	}

	@Step("clicking on Fee schedule management tab..")
	public void clickOnFeeSchedulerMgmt() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", FeeSchedulerMgmt);

	}

	@Step("clicking on Fee schedule management tab..")
	public void clickOnCreateNewButton() {
		CreateNewButton.click();
	}

	@Step("Getting title name..")
	public String getTitle() {
		return driver.getTitle();
	}

	@Step("Entering fee scheduler name...")
	public void enterFeeSchedulerName(String str) {
		feeScheduleName.sendKeys(str);

	}

	@Step("Entering fee scheduler description...")
	public void enterFeeSchedulerDesc(String str) {
		feeScheduleDesc.sendKeys(str);
	}

	@Step("Selecting effective date...")
	public void selectEffectiveDate(String str) {
		effectiveDate.sendKeys(str);
	}

	@Step("Selecting termination date...")
	public void selectTerminateDate(String str) {
		terminationDate.sendKeys(str);
	}

	@Step("Entering  AWP percentage ...")
	public void enterAWPPercentage(String str) {
		AWP_percentage_Input.sendKeys(str);
	}

	@Step("Selecting AWP checkbox ...")
	public void clickOnAWPCheckBox() {
		AWP_CheckBox.click();
	}

	@Step("Clicking on save button...")
	public void clickOnSaveButton() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", SaveButton);

	}

	@Step("Searching newly created fee scheduler...")
	public void searchFS(String str) {
		searchFeeScheduler.sendKeys(str);
	}

	@Step("clicking on search button...")
	public void clickOnSearchButton() {
		searchButton.click();
	}

	@Step("getting Fee Scheduler web table...")
	public WebElement getBaseTable() {
		WebElement webTable = driver.findElement(By.tagName("table"));
		return webTable;

	}

//	@Step("Getting web table row...")
//	public WebElement tableRow() {
//		WebElement fs_Table_row = getBaseTable().findElements(By.)
//		return fs_Table_row;
//	}

	@Step("Getting webtable row value..")
	public String getText() {
		String text =tableRowWebTable.get(0).getText();

		return text;
	}

	@Step("Selecting fee scheduler from list...")
	public void clickOnRow() {
		tableRowWebTable.get(0).click();
	}

	@Step("clicking on Edit Existing fee schedule button...")
	public void clickEeditExistingFeeSchedule() {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", editExistingFS);

	}

	@Step("Checking name in edit page...")
	public String getFSNameEdit() {
		String name = feeScheduleName.getText();
		return name;
	}

	@Step("Checking checkbox Status...")
	public boolean getcheckBoxStatus() {
		boolean checkBoxStatus = AWP_CheckBox.isSelected();
		return checkBoxStatus;
	}

}
