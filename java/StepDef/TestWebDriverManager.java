package StepDef;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebDriverManager {
public static void main(String args[]) throws InterruptedException{
	//String chromeDriverPath = System.getProperty("user.dir") + "//resources//chromedriver.exe";
	//System.setProperty("webdriver.chrome.driver", "C://eclipse-workspace//PriceCompare//resources//chromedriver.exe");
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--start-maximized");
	ChromeDriver driver = new ChromeDriver(options);
	driver.get("https://www.facebook.com");
	
}
}
