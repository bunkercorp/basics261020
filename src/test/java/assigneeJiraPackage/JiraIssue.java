package assigneeJiraPackage;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JiraIssue {

    public String jiraResponse;
    public String urlNewCreatedIssue;
    public String idNewCreatedIssue;
    public String keyNewCreatedIssue;

    public void setResponse(String response) {
        jiraResponse = response;
        JSONObject jiraResponseForWork = new JSONObject(jiraResponse);
        idNewCreatedIssue = jiraResponseForWork.optString("id");
        keyNewCreatedIssue = jiraResponseForWork.optString("key");
        urlNewCreatedIssue = jiraResponseForWork.optString("self");
    }

    public String toString() {
        return String.format("%s\n%s\n%s", idNewCreatedIssue, keyNewCreatedIssue, urlNewCreatedIssue);
    }

    public static class Builder {
        private final String projectKey;
        private String issueTypeId;
        private String priorityId = "1";
        private ArrayList<String> labels;
        private String contentDescription;
        private String summary;
        private final TestPageObject testPageObject;
        public JiraIssue newJiraIssue;

        public Builder(String projectKey) {
            this.projectKey = projectKey;
            newJiraIssue = new JiraIssue();
            testPageObject = new TestPageObject();
        }

        public final static class jiraIssueErrors extends Error {
            public jiraIssueErrors(ArrayList<String> errors) {
                super("Cannot create an issue:\n\t" + String.join("\n\t", errors));
            }
        }

        private void isFieldValid(String fieldName, String fieldValue, List<String> errorList) {
            if (fieldValue == null || fieldValue.isEmpty()) {
                errorList.add(fieldName + " is not valid");
            }
        }

        public Builder ofType(String issueTypeDisplayName) throws IOException {
            String response = testPageObject.requestJiraData("https://jira.ithillel.com/rest/api/2/issuetype", "GET", null);
            JSONArray prioritiesParsed = new JSONArray(response);
            for (int i = 0; i < prioritiesParsed.length(); i += 1) {
                if (prioritiesParsed.getJSONObject(i).getString("name").contentEquals(issueTypeDisplayName)) {
                    issueTypeId = prioritiesParsed.getJSONObject(i).getString("id");
                    break;
                }
            }
            return this;
        }

        public Builder withPriorityDisplayName(String priorityDisplayName) throws IOException {
            String response = testPageObject.requestJiraData("https://jira.ithillel.com/rest/api/2/priority", "GET", null);
            JSONArray prioritiesParsed = new JSONArray(response);
            for (int i = 0; i < prioritiesParsed.length(); i += 1) {
                if (prioritiesParsed.getJSONObject(i).getString("name").contentEquals(priorityDisplayName)) {
                    priorityId = prioritiesParsed.getJSONObject(i).getString("id");
                    break;
                }
            }
            return this;
        }

        public Builder withLabels(ArrayList<String> labels) {
            this.labels = labels;
            return this;
        }

        public Builder withContentDescription(String contentDescription) {
            this.contentDescription = contentDescription;
            return this;
        }

        public Builder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public JiraIssue build() throws IOException {

            ArrayList<String> errorsList = new ArrayList<>();

            int projectId = -1;
            String response = testPageObject.requestJiraData("https://jira.ithillel.com/rest/api/2/project", "GET", null);
            JSONArray prioritiesParsed = new JSONArray(response);
            for (int i = 0; i < prioritiesParsed.length(); i += 1) {
                if (prioritiesParsed.getJSONObject(i).getString("name").contentEquals(this.projectKey)) {
                    projectId = prioritiesParsed.getJSONObject(i).getInt("id");
                    break;
                }
            }

            if (projectId >= 0) {

                isFieldValid("Summary", summary, errorsList);
                isFieldValid("Content description", contentDescription, errorsList);
                isFieldValid("Issue TypeId", issueTypeId, errorsList);

                if (errorsList != null && errorsList.isEmpty()) {
                    JSONObject payload = new JSONObject();
                    JSONObject fields = new JSONObject();
                    fields.put("project", new JSONObject().put("id", "" + projectId));
                    fields.put("summary", summary);
                    fields.put("description", contentDescription);
                    fields.put("reporter", new JSONObject().put("name", "olga.vovk.oiv"));
                    fields.put("issuetype", new JSONObject().put("id", issueTypeId));
                    fields.put("priority", new JSONObject().put("id", priorityId));
                    if (this.labels != null && !this.labels.isEmpty()) {
                        JSONArray labels = new JSONArray();
                        this.labels.stream().distinct().forEach(labels::put);
                        fields.put("labels", labels);
                    }
                    payload.put("fields", fields);

                    String responseNewIssue = testPageObject.requestJiraData("https://jira.ithillel.com/rest/api/2/issue", "POST", payload.toString());
                    newJiraIssue.setResponse(responseNewIssue);
                }
            } else {
                errorsList.add("Project is not valid");
            }
            if (!errorsList.isEmpty()) {
                throw new jiraIssueErrors(errorsList);
            }
            return newJiraIssue;
        }

    }

    public static void main(String[] args) throws IOException {

        ArrayList<String> labels = new ArrayList<>();
        labels.add("olala");
        labels.add("ururu");

        JiraIssue myNewCreatedIssue = new JiraIssue.Builder("AQANS")
                .ofType("Ошибка")
                .withPriorityDisplayName("Lowest")
                .withLabels(null)
                .withContentDescription("olala")
                .withSummary("TestOla")
                .build();

        System.out.println(myNewCreatedIssue);
    }
}
