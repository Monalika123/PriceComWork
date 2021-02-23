package Runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;
import com.paulhammant.ngwebdriver.NgWebDriver;
import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "resources/feature", glue = { "StepDef" }, tags = {
		"@runTestAmazon,@runTestFlipkart" }, plugin = { "pretty", "html:target/reports",
				"json:target/cucumber-reports/cucumber.json",
				"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:" })

public class TestRunner extends AbstractTestNGCucumberTests {
	public static Properties config = null;
	public static WebDriver driver;
	public static String baseUrl;

	@BeforeSuite(alwaysRun = true)
	public void LoadConfigProperty() throws IOException {
		System.out.println("------------------------Before Suite------------------");
		config = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "//resources//config//config.properties");
		config.load(ip);
		// Set up extent report to create new file each time report gets created
		setupExtentReport();

	}

	public void configureDriverPath() throws IOException {
		if (System.getProperty("os.name").startsWith("Linux")) {
			System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		}
		if (System.getProperty("os.name").startsWith("Mac")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/geckodriver";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		}
		if (System.getProperty("os.name").startsWith("Windows")) {
			String firefoxDriverPath = System.getProperty("user.dir") + "//resources//geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			/////////////////////////////////////////////////////////////
			String chromeDriverPath = System.getProperty("user.dir") + "//resources//chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			// String phantomDriverPath = System.getProperty("user.dir") +
			// "//src//test//resources//drivers//windows//phantomjs.exe";
			// System.out.println(System.getProperty("user.dir"));
			// System.setProperty("phantomjs.binary.path", phantomDriverPath);

		}
	}

	public void openBrowser() throws Exception {
		// loads the config options
		LoadConfigProperty();
		// configures the driver path
		configureDriverPath();

		if (config.getProperty("browserType").equals("firefox")) {
			// String firefoxDriverPath = System.getProperty("user.dir") +
			// "//resources//geckodriver.exe";
			// System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			driver = new FirefoxDriver();

		} else if (config.getProperty("browserType").equals("chrome")) {
			// String chromeDriverPath = System.getProperty("user.dir") +
			// "//resources//chromedriver.exe";
			// System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			Boolean headless = Boolean.valueOf(config.getProperty("headless"));
			options.setHeadless(headless);
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--start-maximized");
			options.addArguments("Zoom 100%");
			/// options.addArguments("--proxy-server='direct://'");
			// options.addArguments("--proxy-bypass-list=*");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-extensions");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability("acceptSslCerts", true);
			capabilities.setCapability("acceptInsecureCerts", true);
			driver = new ChromeDriver(options);

		} else if (config.getProperty("browserType").equals("phantom")) {

			Capabilities caps = new DesiredCapabilities();
			/*
			 * Enable javascript for browser
			 */
			((DesiredCapabilities) caps).setJavascriptEnabled(true);
			((DesiredCapabilities) caps).setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			((DesiredCapabilities) caps).setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			((DesiredCapabilities) caps).setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY, true);
			((DesiredCapabilities) caps).setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);

			/*
			 * Set capability for the executable path for phantomjs
			 */
			((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					"D://Users//raian//versions//GLQSAutomation//src//test//resources//drivers//windows//phantomjs.exe");
			((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
					new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
			/*
			 * return capability instance to increase re-usability of capability
			 */
			driver = new PhantomJSDriver(caps);
			driver.manage().window().setSize(new Dimension(1000, 800));

			// driver= new PhantomJSDriver();
		}

	}

	public void setUpBrowserAndOpenLink(String loginType) throws Exception {
		 openBrowser();
		 maximizeWindow();
		 implicitWait(30);
		 deleteAllCookies();

		LoadConfigProperty();
		if (loginType.contains("amazon")) {
			// baseUrl = config.getProperty("AmazonURL");
			driver.get("https://www.amazon.in/");
			implicitWait(30);
		} else if (loginType.contains("flipkart")) {
			baseUrl = config.getProperty("FlipkartURL");
			driver.get(baseUrl);
			implicitWait(30);
		}
	}

	public void maximizeWindow() throws InterruptedException {
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}

	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void setEnv(String portal) throws Exception {

		if (portal.contains("Amazon")) {
			baseUrl = "https://www.amazon.in";
		} else {
			baseUrl = "https://www.flipkart.com";
		}
		driver.get(baseUrl);
	}

	/*
	 * Code to generate extent report with date and timestamp not used for
	 * cucumber-extent report
	 */
	public static void setupExtentReport() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath("target/Extent/" + timeStamp.replace(":", "_").replace(".", "_") + ".html");
	}

	public static void generateExtentReport() throws IOException {
		Reporter.loadXMLConfig(new File("resources/config/extent.xml"));
		Reporter.setSystemInfo("Test User", System.getProperty("user.name"));
		Reporter.setSystemInfo("User Name", "Monalika");
		Reporter.setSystemInfo("Application Name", "Random Apps");
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));

		Reporter.setTestRunnerOutput("passed");

	}

	@AfterSuite(alwaysRun = true)
	public void generateHTMLReports() throws IOException {
		System.out.println("------------------------After Suite------------------");

		// call to cucumber extent report
		ReportHelper.generateCucumberReport();

		// call to extent report
		generateExtentReport();
	}

	// @AfterTest
	public void quit() {
		// driver.quit();

		System.out.println("Releasing resources now.....");
		if (null != driver) {
			driver.close();
			driver.quit(); // CLOSES ALL THE OPEN BROWSER SESSIONS LEAVING OTHER STEP EXECUTIONS INCOMPLETE
		}
	}

	public void takeScreenshotandAttachtoReport() {
		String screenshotName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(":", "_")
				.replace(".", "_");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
		File trgtFile = new File(path);
		trgtFile.getParentFile().mkdir();
		try {
			trgtFile.createNewFile();
			Files.copy(scrFile, trgtFile);
			Reporter.addScreenCaptureFromPath(trgtFile.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void click_Enter(WebElement ele) {
		ele.sendKeys(Keys.ENTER);
	}

	// Click the element
	public static void focusAndClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	// Scroll the element into view
	public static void scrollAndClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	// Scroll by specific amount
	public static Boolean scrollAndFind(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,600);", element);
		return element.isDisplayed();
	}

	// Scroll to the respective element
	public static void scrollPage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	// waitForAngulatRequestsToFinish While opening an URL
	public void waitForAngularRequestsToFinish(String TestUrls) {
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
		driver.get(TestUrls);
		ngDriver.waitForAngularRequestsToFinish();
	}
}
