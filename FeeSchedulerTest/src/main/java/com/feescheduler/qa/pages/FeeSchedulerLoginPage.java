package com.feescheduler.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.feescheduler.qa.base.BaseTest;

import io.qameta.allure.Step;

public class FeeSchedulerLoginPage extends BaseTest {

	// Page Factory

	@FindBy(xpath = "//*[@id='loginbtn']")
	WebElement loginButton;

	@FindBy(name = "username")
	List<WebElement> userName;

	@FindBy(name = "password")
	List<WebElement> passWord;

	@FindBy(xpath = "//*[@id='btnLoginSubmit1']")
	WebElement submitButton;
	
	@FindBy(xpath = "//*[@id='terminateSession']")
	WebElement clickHereToTerminate;
	
	@FindBy(xpath = "//*[@id='btnLoginSubmit']")
	WebElement login_submit;
	
	

	// Initializing page

	public FeeSchedulerLoginPage() {
		PageFactory.initElements(driver, this);
	}

	// public methods

	@Step("getting login page title...")
	public String verifyPageTitle() {
			return driver.getTitle();
	}
	
	@Step("clicking on login button...")
	public void clickOnLogin()
	{
		loginButton.click();
		
	}

	@Step("loggin into application by entering username and passwrod...")
	public HomePage login(String un,String pwd)
	{
		
		userName.get(0).sendKeys(un);
		passWord.get(0).sendKeys(pwd);
		submitButton.click();
		
		return new HomePage();
		
	}
	
	@Step("User session is active at other location and to terminate the session click on terminate...")
	public void TerminateSession()
	{
		clickHereToTerminate.click();
		
	}
	
	@Step("loggin into application by entering username and passwrod...")
	public HomePage login_afterTermination(String un,String pwd)
	{
		
		userName.get(1).sendKeys(un);
		passWord.get(1).sendKeys(pwd);
		login_submit.click();
		
		return new HomePage();
		
	}
	
	

}
