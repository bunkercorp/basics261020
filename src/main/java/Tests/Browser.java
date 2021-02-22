package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Browser {

    static WebDriver browser = null;

    private Browser() {
    }

    public static WebDriver getBrowser() throws MalformedURLException {

        final boolean isRemote = "true".equalsIgnoreCase(System.getProperty("remote"));
        if (browser == null) {

            if (System.getProperty("browser").equals("chrome")) {

                if (isRemote) {
                    browser =
                            new RemoteWebDriver(new URL("http://10.0.0.69:4444/wd/hub"), DesiredCapabilities.chrome());

                } else {
                    final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
                    System.setProperty("webdriver.chrome.driver", binPath);
                    browser = new ChromeDriver();
                }

            }
        } else if
        (System.getProperty("browser").equals("firefox")) {
            if (isRemote) {
                 browser =
                        new RemoteWebDriver(new URL("http://10.0.0.69:4444/wd/hub"), DesiredCapabilities.firefox());

            } else {
                final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", binPath);
                browser = new FirefoxDriver();
            }

        }
        return browser;
    }

}


