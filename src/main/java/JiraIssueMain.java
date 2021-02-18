import com.google.common.collect.Lists;
import java.io.IOException;


public class JiraIssueMain {

    public static void main(String[] args) throws IOException {


        JiraIssue newJiraIssue = new JiraIssue.Builder("AQANS")
                .ofType("Task")
                .withPriority("Lowest")
                .withLabels(Lists.newArrayList("A","B","C","D"))
                .withDescription("Detailed description here")
                .withSummary("Title of the project")
                .create();

        System.out.println(newJiraIssue);



    }
}
