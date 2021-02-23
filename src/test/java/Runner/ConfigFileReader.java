package Runner;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class ConfigFileReader {
	@BeforeSuite(alwaysRun = true)
	public String getReportConfigPath() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			prop.load(getClass().getClassLoader()
					.getResourceAsStream(System.getProperty("user.dir") + "//PriceCompare//resources//config//config.properties"));
			String reportConfigPath = System.getProperty("user.dir") + "//PriceCompare//resources//config//config.properties";
			return reportConfigPath;
					/*prop.getProperty("reportConfigPath");
			if (reportConfigPath != null)
				return reportConfigPath;
			else
				throw new RuntimeException(
						"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
*/
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}
}
