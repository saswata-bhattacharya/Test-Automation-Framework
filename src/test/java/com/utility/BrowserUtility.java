package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // initialise instance variable driver!!
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching browser for " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			driver.set(new SafariDriver());
		}
	}

//	public BrowserUtility(Browser browserName) {
//		if (browserName == Browser.CHROME) {
//			driver.set(new ChromeDriver());
//		} else if (browserName == Browser.EDGE) {
//			driver.set(new EdgeDriver());
//		}
//
//		else if (browserName == Browser.FIREFOX) {
//			driver.set(new FirefoxDriver());
//		}
//
//		else if (browserName == Browser.SAFARI) {
//			driver.set(new SafariDriver());
//		} else {
//			logger.error("Invalid browser value!! Input correct browser value....");
//			System.err.print("Invalid browser value!! Input correct browser value....");
//		}
//	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old"); // headless
				options.addArguments("--window-size=1920,1080"); // full screen
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
		        options.addArguments("--headless"); // Run in headless mode
		        options.addArguments("--disable-gpu"); // Disable GPU acceleration (for stability)
		        options.addArguments("--window-size=1920,1080"); // Set window size
		        driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		}

		else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless = old"); // headless
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}	
		}

		else if (browserName == Browser.SAFARI) {
			if (isHeadless) {
				throw new UnsupportedOperationException("Headless mode is not supported for Safari.");
			} else {
				driver.set(new SafariDriver());
			}	
			
		} else {
			logger.error("Invalid browser value!! Input correct browser value....");
			System.err.print("Invalid browser value!! Input correct browser value....");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Visting the website " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);

		logger.info("Element found and performing the click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);

		logger.info("Element found and now enter the text  " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning the visible text " + element.getText());
		return element.getText();

	}

	public String takeScreenshot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + "-" + timeStamp + ".png";
		File screenshotfile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
