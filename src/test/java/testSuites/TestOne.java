package testSuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestOne {
    @Test
    public static void test() throws MalformedURLException {

        Browser.getBrowser().get("https://www.native-english.ru/grammar");

        final WebElement titleOfArticle = Browser.getBrowser().findElements(By.cssSelector("[class = 'content'] [class = 'page'] [class = 'page__content'] [class = 'title']")).get(0);
        String title = titleOfArticle.getText();

        Assert.assertEquals(title, "Грамматика английского языка");
    }
}
