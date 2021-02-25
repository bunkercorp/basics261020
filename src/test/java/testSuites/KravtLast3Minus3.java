package testSuites;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.awt.*;
import java.net.MalformedURLException;

public class KravtLast3Minus3 {
    @Test
    public static void test() throws InterruptedException, AWTException, MalformedURLException {

        final WebDriverWait wait = new WebDriverWait(Browser.getBrowser(), 30);

        Browser.getBrowser().get("https://www.booking.com/searchresults.ru.html?label=gen173nr-1FCAEoggI46AdIM1gEaOkBiAEBmAEhuAEXyAEM2AEB6AEB-AELiAIBqAIDuALCzN-BBsACAdICJDFjMzQyMWUxLWFhODAtNGI5OS05YjUwLTRkZmJkZWEyZmI5OdgCBuACAQ&sid=6ccee8efa8778b3e2b3359b603495c48&sb=1&sb_lp=1&src=index&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Findex.ru.html%3Flabel%3Dgen173nr-1FCAEoggI46AdIM1gEaOkBiAEBmAEhuAEXyAEM2AEB6AEB-AELiAIBqAIDuALCzN-BBsACAdICJDFjMzQyMWUxLWFhODAtNGI5OS05YjUwLTRkZmJkZWEyZmI5OdgCBuACAQ%3Bsid%3D6ccee8efa8778b3e2b3359b603495c48%3Bsb_price_type%3Dtotal%26%3B&ss=Barcelona%2C+Catalonia%2C+Spain&is_ski_area=&checkin_year=2021&checkin_month=2&checkin_monthday=25&checkout_year=2021&checkout_month=2&checkout_monthday=28&group_adults=2&group_children=0&no_rooms=1&b_h4u_keep_filters=&from_sf=1&ss_raw=barcelona&ac_position=0&ac_langcode=en&ac_click_type=b&dest_id=-372490&dest_type=city&iata=BCN&place_id_lat=41.3871&place_id_lon=2.17001&search_pageview_id=0da67ee1c7eb014f&search_selected=true&search_pageview_id=0da67ee1c7eb014f&ac_suggestion_list_length=5&ac_suggestion_theme_list_length=0");

        if (Browser.getBrowser().findElement(By.cssSelector("[title='Барселона Отели ']")).isDisplayed())
            System.out.println("We're in Barcelona!");
        else
            System.out.println("Oh, no!");

        final String h1All = Browser.getBrowser().findElement(By.cssSelector("div[role='heading'] > h1")).getText();

        Thread.sleep(5000);
        Browser.getBrowser().findElement(By.cssSelector("div#filter_hoteltype > div[role='group'] > a:nth-of-type(4) > .bui-checkbox > .bui-checkbox__label.css-checkbox.filter_item > .filter_label")).click();

        Thread.sleep(5000);
        final String h1Hostel = Browser.getBrowser().findElement(By.cssSelector("div[role='heading'] > h1")).getText();
if (!h1Hostel.equals(h1All))
    System.out.println("List has changed");
else
    System.out.println("Oh, no!");


        Thread.sleep(5000);
        Browser.getBrowser().close();

    }
}
