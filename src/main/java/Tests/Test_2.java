package Tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.net.MalformedURLException;

public class Test_2 {

    @Test
    public static void test() throws InterruptedException, AWTException, MalformedURLException {

        final WebDriverWait wait = new WebDriverWait(Browser.getBrowser(), 30);

        Browser.getBrowser().get("https://www.gismeteo.ua/");

        Browser.getBrowser().findElement(By.cssSelector("h3 > .blue.link")).click();

        Thread.sleep(2000);

    }
}
