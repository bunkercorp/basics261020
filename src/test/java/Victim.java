import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class Victim {

    @Test
    public static void test() throws IOException {
        final String url = System.getProperty("payload");
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://10.0.0.69:4444/wd/hub"), caps);
        driver.get(url);
    }

}
