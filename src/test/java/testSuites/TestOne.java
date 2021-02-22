package testSuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static testSuites.Browser.getBrowser;

public class TestOne {
    @Test
    public static void test() throws MalformedURLException {

        getBrowser().get("https://www.native-english.ru/grammar");

        final WebElement titleOfArticle = getBrowser().findElements(By.cssSelector("[class = 'content'] [class = 'page'] [class = 'page__content'] [class = 'title']")).get(0);
        String title = titleOfArticle.getText();

        Assert.assertEquals(title, "Грамматика английского языка");
    }
}
