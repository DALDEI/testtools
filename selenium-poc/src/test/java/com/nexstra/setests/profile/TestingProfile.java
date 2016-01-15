package com.nexstra.setests.profile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestingProfile {
	@Override
	public String toString() {
		return "TestingProfile [baseUrl=" + baseUrl + ", adminUser="
				+ adminUser + ", adminPassword=" + adminPassword + ", klaUser="
				+ klaUser + ", klaPassword=" + klaPassword + ", browser="
				+ browser + "]";
	}

	private String baseUrl = "http://store2.nexstra.com";
	private String adminUser;
	private String adminPassword;
	private String klaUser;
	private String klaPassword;
	
	private String browser;
	
	private TestingProfile(){}
	
	public static TestingProfile load(){
		TestingProfile ret = new TestingProfile();
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
			String profileFile = System.getProperty("testProfile");
	 
			if(profileFile == null){
				profileFile= "test.profile";
			}
			input = new FileInputStream(profileFile);
	 
			// load a properties file
			prop.load(input);
	 
			ret.baseUrl= (prop.getProperty("baseUrl"));
			ret.adminUser= (prop.getProperty("adminUser"));
			ret.adminPassword= (prop.getProperty("adminPassword"));
			ret.browser= (prop.getProperty("browser"));
			ret.klaUser= (prop.getProperty("klaUser"));
			ret.klaPassword= (prop.getProperty("klaPassword"));
			
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return ret;
		}
	}
	
	
	public String getBaseUrl() {
	 return baseUrl;
	}
	public String getAdminUser() {
		
		return adminUser;

	}
	public String getAdminPassword() {
		return adminPassword;
	}
	
	
	public WebDriver getDriver() {
		WebDriver driver = null;
		
		switch(browser.toUpperCase()){
			case "IE": {
				driver = new InternetExplorerDriver();
				break;
			}
			case "CHROME": {
				System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
				
				driver = new ChromeDriver();
				break;
			}
			case "REMOTE":{
				DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
				
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			default: {
				driver = new FirefoxDriver();break;
			}
			
			

		}
		return driver;

//		if ("IE".equalsIgnoreCase(System.getProperty("browser"))) {
////			https://code.google.com/p/selenium/wiki/InternetExplorerDriver
////			http://blog.httpwatch.com/2009/04/23/fixing-the-ie-8-warning-do-you-want-to-view-only-the-webpage-content-that-was-delivered-securely/
//			WebDriver driver = new InternetExplorerDriver();
//			return driver;
//		}else if ("Chrome".equalsIgnoreCase(System.getProperty("browser"))) {
////			https://code.google.com/p/selenium/wiki/InternetExplorerDriver
//			WebDriver driver = new ChromeDriver();
//			return driver;
//		}  else {
//			String bin= System.getProperty("webdriver.firefox.bin");
////			System.setProperty("webdriver.firefox.profile", "SE");
//			WebDriver driver = new FirefoxDriver();
//			
//			return driver;
//		}
	}

	public String getKlaUser() {
		return klaUser;
	}

	public void setKlaUser(String klaUser) {
		this.klaUser = klaUser;
	}

	public String getKlaPassword() {
		return klaPassword;
	}

	public void setKlaPassword(String klaPassword) {
		this.klaPassword = klaPassword;
	}
	
	
}
