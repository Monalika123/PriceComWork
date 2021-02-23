package StepDef;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import Runner.TestRunner;

public class Cucumberhooks extends TestRunner {
	public static Scenario scenario;
	
	@Before
	public void SetUp(Scenario scenario) {
		Reporter.assignAuthor("Monalika Sahoo");
		System.out.println("-------------Before Hook starts-----------------");
		System.out.println("This will run before the Scenario: " + scenario.getName() + " -- Started ");
		try {
//			 openBrowser();
//			 maximizeWindow();
//			 implicitWait(30);
//			 deleteAllCookies();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------Before Hook ends-----------------");
	}

	@After(order = 1)
	public void takeScreenshot(Scenario scenario) throws IOException {
		System.out.println("----------------After Hook starts--------------");
		try {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (scenario.isFailed()) {

			String screenshotName = scenario.getSourceTagNames() + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date()).replace(":", "_").replace(".", "_");
			try {
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(
						System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png");
				Files.copy(sourcePath, destinationPath);
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
			/*
			 * code to take screenshot and attach it in cucumber-html-reports
			 */
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@After(order = 0)
	public void CloseBrowser(Scenario scenario) throws IOException {

		System.out.println(
				"This will run after the Scenario: " + scenario.getName() + " Status - " + scenario.getStatus());
		// keeping it commented as do not want to close browser
		quit();
		System.out.println("--------------After Hook ends----------------");
	}
}
