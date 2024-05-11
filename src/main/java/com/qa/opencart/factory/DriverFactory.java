package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		//System.out.println("Test case is running in :" + browserName);
		
		Log.info("Test case is running in :" + browserName);
		
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "ff":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Plz provide the right browser...");
			throw new BrowserException("NO BROWSER FOUND...");

		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// envName=qa,stage,prod
		// mvn clean install -Denv="qa"
		// to read the command line property(-Denv="qa")we use system class
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		// String envName = "stage";
		System.out.println("Running in env :" + envName);
		try {
			if (envName == null) {
				System.out.println("EnvName is not given .... hence running in QA env");
				Log.info("EnvName is not given .... hence running in QA env");
				ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/config.stage.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
					break;
				default:
					System.out.println("Please pass the right env nsame..." + envName);
					throw new FrameworkException(AppError.ENV_NOT_FOUND + envName);
				}
			}
		} catch (FileNotFoundException e) {
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Takes Screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot"+ methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	
}
