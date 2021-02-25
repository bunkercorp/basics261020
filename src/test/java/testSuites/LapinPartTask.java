package testSuites;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LapinPartTask {
    public static WebDriver browser;

    public static void isSelectedOption(){
        List<WebElement> people = browser.findElements(By.className("xp__guests__count"));
        List<String> strPeople = new ArrayList<>();
            for(WebElement person: people){
                strPeople.add(person.getText());
        }
        System.out.println(strPeople);
            strPeople.stream().filter(s -> s.contains(String.format("%d"))).collect(Collectors.toList());
            browser.findElement(By.className("sb-searchbox__button")).click();
        System.out.println(strPeople);
    }

    public static void validateForm(){
        String city = browser.findElement(By.id("ss")).getAttribute("value");
        String checkInDate = browser.findElement(By.cssSelector("dev[date-placeholder=Дата Заезда")).getText();
        String checkOutDate = browser.findElement(By.cssSelector("dev[date-placeholder=Дата отъезда")).getText();
        String numberOfNights = browser.findElement(By.className("sb-dates__los")).getText();
    }

}
