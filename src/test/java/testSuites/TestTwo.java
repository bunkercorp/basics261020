package testSuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestTwo {
    @Test
    public static void test() throws MalformedURLException {

        Browser.getBrowser().get("https://ru.wikipedia.org/wiki/PandaBoard");

        final WebElement titleOfArticle = Browser.getBrowser().findElements(By.cssSelector("[id = 'content'] [id = 'firstHeading']")).get(0);
        String title = titleOfArticle.getText();

        Assert.assertEquals(title, "PandaBoard");
    }
}
