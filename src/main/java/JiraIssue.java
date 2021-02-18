import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JiraIssue {

    public String urlNewIssue;
    public String idNewIssue;
    public String keyNewIssue;

    public void setResponse(String response) {
        JSONObject jiraResponse = new JSONObject(response);
        idNewIssue = jiraResponse.getString("id");
        keyNewIssue = jiraResponse.getString("key");
        urlNewIssue = jiraResponse.getString("self");
    }

    public String toString() {
        return String.format("%s\n%s\n%s", idNewIssue, keyNewIssue, urlNewIssue);
    }


    public static class Builder {
        private String projectKey = "null";
        private String builderOftype;
        private String builderwithPriority = "1";
        private List<String> labels = new ArrayList<>();
        private String builderwithDescription;
        private String builderwithSummary;
        private List<String> errors = new ArrayList<>();
        public JiraIssue newJiraIssue;
        private int projectId = -1;

        public Builder(String projectKey) throws IOException {
            String response = JiraConnection.requestJiraData("https://jira.ithillel.com/rest/api/2/project", "GET", null);
            JSONArray projectsParsed = new JSONArray(response);
            for (int i = 0; i < projectsParsed.length(); i += 1) {
                if (projectsParsed.getJSONObject(i).getString("name").contentEquals(projectKey)) {
                    projectId = projectsParsed.getJSONObject(i).getInt("id");
                    break;
                }
            }
            this.projectKey = projectKey;
            newJiraIssue = new JiraIssue();
        }

        public final static class jiraIssueErrors extends Error {
            public jiraIssueErrors(ArrayList<String> errors) {
                super("Cannot create an issue:\n\t" + String.join("\n\t", errors));
            }
        }


        public static boolean isNotEmpty(String input) {
            return input != null && !input.isEmpty();
        }


        public Builder ofType(String issueTypeDisplayName) {
            if (isNotEmpty(issueTypeDisplayName))
                builderOftype = issueTypeDisplayName;
            return this;
        }


        public Builder withPriority(String priorityDisplayName) throws IOException {
            String response = JiraConnection.requestJiraData("https://jira.ithillel.com/rest/api/2/priority", "GET", null);
            JSONArray prioritiesParsed = new JSONArray(response);
            for (int i = 0; i < prioritiesParsed.length(); i += 1) {
                if (prioritiesParsed.getJSONObject(i).getString("name").contentEquals(priorityDisplayName)) {
                    builderwithPriority = prioritiesParsed.getJSONObject(i).getString("id");
                    break;
                }
            }
            return this;
        }

        public Builder withLabels(ArrayList<String> labels) {
            labels.forEach(
                    label -> {
                        if (isNotEmpty(label))
                            this.labels.add(label);
                    }
            );
            return this;
        }

        public Builder withDescription(String content) {
            if (isNotEmpty(content))
                builderwithDescription = content;
            return this;
        }

        public Builder withSummary(String summary) {
            if (isNotEmpty(summary))
                builderwithSummary = summary;
            return this;
        }


        public JiraIssue create() throws IOException {

            if (builderwithSummary == null)
                errors.add("Add summary");

            if (builderOftype == null)
                errors.add("Trouble with issue type");

            if (projectId > 0 && builderwithSummary != null && builderOftype != null) {
                final int[] issueTypeid = {-1};

                String response = JiraConnection.requestJiraData(String.format("https://jira.ithillel.com/rest/api/2/project/%d", projectId), "GET", null);
                JSONArray typesParsed = new JSONObject(response).getJSONArray("issueTypes");
                for (int i = 0; i < typesParsed.length(); i += 1) {
                    if (typesParsed.getJSONObject(i).getString("name").contentEquals(builderOftype)) {
                        issueTypeid[0] = typesParsed.getJSONObject(i).getInt("id");
                        break;
                    }
                }

                if (issueTypeid[0] == -1) {
                    errors.add("Entered issue type doesn't exist");
                    issueTypeid[0] = 0;
                }

                if (issueTypeid[0] > 0) {

                    JSONArray labels = new JSONArray();
                    this.labels.stream().distinct().forEach(labels::put);

                    JSONObject payload = new JSONObject();
                    JSONObject fields = new JSONObject();
                    fields.put("project", new JSONObject().put("id", projectId));
                    fields.put("summary", builderwithSummary);
                    fields.put("labels", labels);
                    fields.put("description", builderwithDescription);
                    fields.put("priority", new JSONObject().put("id", builderwithPriority));
                    fields.put("reporter", new JSONObject().put("name", "jen.kravt"));
                    fields.put("issuetype", new JSONObject().put("id", issueTypeid[0]));

                    payload.put("fields", fields);

                    System.out.println(payload.toString());

                    String responseNewIssue = JiraConnection.requestJiraData("https://jira.ithillel.com/rest/api/2/issue", "POST", payload.toString());


                    newJiraIssue.setResponse(responseNewIssue);
                } else {
                    errors.add("Project is not valid");
                }
            }
            if (!errors.isEmpty()) {
                throw new jiraIssueErrors((ArrayList<String>) errors);
            }

            return newJiraIssue;
        }
    }
}





