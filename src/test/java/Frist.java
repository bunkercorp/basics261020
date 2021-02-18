import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Frist {

    @Test
    public static void test() throws MalformedURLException, InterruptedException {
        final String whatToGoogle = System.getProperty("query");

        WebDriver browser =
                new RemoteWebDriver(new URL("http://10.0.0.69:4444/wd/hub"), DesiredCapabilities.chrome());

        browser.get(String.format("https://%s", whatToGoogle));
//
//        final WebElement inputField = browser.findElement(By.xpath("//div[@id='searchform']//input[@name='q']"));
//        inputField.sendKeys(whatToGoogle);
//
//        final WebElement searchBtn = browser.findElements(By.cssSelector("[id = 'searchform'] [name = 'btnK']")).get(1);
//        browser.findElement(By.cssSelector("[alt = 'Google']")).click();
//        searchBtn.click();
        Thread.sleep(5000);
        browser.quit();

    }

}
