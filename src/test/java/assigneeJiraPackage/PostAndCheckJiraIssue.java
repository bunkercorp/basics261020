package assigneeJiraPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class PostAndCheckJiraIssue {

    private static String keyNewCreatedIssue;
    public static String projectKey = "AQANS";
    public static String type = "Test";
    public static String priority = "Lowest";
    public static String description = "OlalaDescription";
    public static String summary = "TestOla";

    @BeforeTest
    public static void postIssue() throws IOException {

        ArrayList<String> labels = new ArrayList<>();
        labels.add("olala");
        labels.add("ururu");

        JiraIssue myNewCreatedIssue = new JiraIssue.Builder(projectKey)
                .ofType(type)
                .withPriorityDisplayName(priority)
                .withLabels(labels)
                .withContentDescription(description)
                .withSummary(summary)
                .build();
        keyNewCreatedIssue = myNewCreatedIssue.keyNewCreatedIssue;
    }

    @Test
    public static void test() {

        final WebDriverWait wait = new WebDriverWait(Browser.getBrowser(), 10);

        Browser.getBrowser().get("https://jira.ithillel.com/browse/" + keyNewCreatedIssue);

        TestPageObject loginAndSubmit = new TestPageObject();
        loginAndSubmit.authorization();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class = 'labels-wrap value editable-field inactive'] [class = 'labels'")));

        final WebElement currentType = Browser.getBrowser().findElement(By.id("type-val"));
        final WebElement currentPriority = Browser.getBrowser().findElement(By.id("priority-val"));
        final WebElement currentLabels = Browser.getBrowser().findElement(By.cssSelector("[class = 'labels-wrap value editable-field inactive'] [class = 'labels'"));
        final WebElement currentDescription = Browser.getBrowser().findElement(By.className("user-content-block"));
        final WebElement currentSummary = Browser.getBrowser().findElement(By.id("summary-val"));


        Assert.assertEquals(currentType.getText(), type);
        Assert.assertEquals(currentPriority.getText(), priority);
        Assert.assertEquals(currentLabels.getText(), "olala ururu");
        Assert.assertEquals(currentDescription.getText(), description);
        Assert.assertEquals(currentSummary.getText(), summary);

        Browser.getBrowser().quit();
    }

}
