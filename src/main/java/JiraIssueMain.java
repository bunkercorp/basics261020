import java.io.IOException;
import java.util.Collections;

public class JiraIssueMain {

    public static void main(String[] args) throws IOException {

        JiraIssue newJiraIssue = new JiraIssue.Builder()

                .getprojectKey("AQ-25")
                .ofType("Task")
                .withPriority("High")
                .withLabels(Collections.singleton("Label"))
                .withDescription("Detailed description here")
                .withSummary("Title of the project")
                .create();

        System.out.println(newJiraIssue);


    }
}
