package testSuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class VovkLastThree {
    @Test
    public static void test() throws MalformedURLException {

        final WebDriverWait wait = new WebDriverWait(Browser.getBrowser(), 10);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class = 'hidden_on_maps'] [class = 'close_inspire_filter_block']")));

        final WebElement popup = Browser.getBrowser().findElements(By.cssSelector(("[class = 'hidden_on_maps'] [class = 'close_inspire_filter_block']"))).get(0);
        popup.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class = 'js-sr-hotel-link hotel_name_link url']")));

        final WebElement firstHostel = Browser.getBrowser().findElements(By.className("[class = 'js-sr-hotel-link hotel_name_link url']")).get(0);
        firstHostel.click();

        ArrayList<String> tabs2 = new ArrayList<String>(Browser.getBrowser().getWindowHandles());
        Browser.getBrowser().switchTo().window(tabs2.get(1));
        Browser.getBrowser().close();
        Browser.getBrowser().switchTo().window(tabs2.get(0));

        final WebElement priceCheaper = Browser.getBrowser().findElements(By.cssSelector(("[class = 'bui-price-display__value prco-inline-block-maker-helper prco-f-font-heading ']"))).get(0);
        final String price = priceCheaper.getText();

        final WebElement checkBoxeSelectedOne = Browser.getBrowser().findElements(By.cssSelector("[class = 'hprt-block'] [class = 'hprt-nos-select js-hprt-nos-select']")).get(0);
        checkBoxeSelectedOne.click();

        final WebElement secondInTheList = Browser.getBrowser().findElements(By.cssSelector("[class = 'hprt-block'] [class = 'hprt-nos-select js-hprt-nos-select']")).get(0);
        secondInTheList.click();

        final WebElement PriceTwo = Browser.getBrowser().findElements(By.cssSelector("[class = 'hprt-reservation-total-price bui-price-display__value prco-inline-block-maker-helper']")).get(0);
        final String totalPrice = PriceTwo.getText();

        Assert.assertEquals(price, totalPrice);

    }
}
