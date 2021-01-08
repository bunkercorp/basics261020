package assigneeJiraPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

    static WebDriver browser = null;

    private Browser() {
    }

    public static WebDriver getBrowser() {

        if (browser == null) {
            if (System.getProperty("browser").equals("chrome")) {
                final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", binPath);
                browser = new ChromeDriver();
            } else if
            (System.getProperty("browser").equals("firefox")) {
                final String binPath = String.format("%s/bin/geckodriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", binPath);
                browser = new FirefoxDriver();
            }
        }
        return browser;
    }
}