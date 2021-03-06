package com.feescheduler.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.feescheduler.qa.util.PageLoadTimeOut;
import com.feescheduler.qa.util.WebEventListener;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public BaseTest() {
		String path = System.getProperty("user.dir");
		System.out.println(path); 
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					path+"\\src\\main\\java\\com\\feescheduler\\qa\\config\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",path+"\\driver\\chromedriver.exe");
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("disable-extensions");
			opt.addArguments("--start-maximized");
			driver = new ChromeDriver(opt);
		}
			

		else if (browserName.equalsIgnoreCase("firefox")) {
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", path+"\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.edge.driver",path+"\\driver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
			
		e_driver=new EventFiringWebDriver(driver);
		eventListener=new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("PAGE_LOADTIMEOUT")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("IMPLICIT_WAIT")), TimeUnit.SECONDS);

	}

}
