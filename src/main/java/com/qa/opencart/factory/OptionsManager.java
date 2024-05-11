package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	private Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
			//System.out.println("Browser is running in headless mode");
			
			Log.info("Browser is running in headless mode");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Browser is running in incognito mode");
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("Browser is running in headless mode");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Browser is running in incognito mode");
			fo.addArguments("--incognito");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("Browser is running in headless mode");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Browser is running in inprivate mode");
			eo.addArguments("--inprivate");
		}
		return eo;
	}
	

}
