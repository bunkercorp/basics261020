import com.google.common.base.Strings;
import com.jsunsoft.http.HttpRequestBuilder;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JiraIssue {

    public final String projectKey;
    public final String projectType;
    public final String projectPriority;
    public final Collection<String> projectLabels;
    public final String projectDescription;
    public final String projectSummary;


    JiraIssue(String inputprojectKey, String inputprojectType, String inputprojectPriority, Collection<String> inputprojectLabels, String inputprojectDescription, String inputprojectSummary) {

        projectKey = inputprojectKey;
        projectType = inputprojectType;
        projectPriority = inputprojectPriority;
        projectLabels = inputprojectLabels;
        projectDescription = inputprojectDescription;
        projectSummary = inputprojectSummary;

    }

    @Override
    public String toString() {
        return String.format("Key: %s \nType: %s \nPriority: %s \nLabels: %s \nDescription: %s \nSummary: %s", projectKey, projectType, projectPriority, projectLabels, projectDescription, projectSummary);
    }


    public static class Builder {
        private String builderprojectKey;
        private String builderOftype;
        private String builderwithPriority;
        private Collection<String> builderwithLabels;
        private String builderwithDescription;
        private String builderwithSummary;


        public static boolean hasSpace(String input) {
            final boolean hasSpace;
            return hasSpace = (input.indexOf(' ') != -1);
        }


        public static boolean isEmpty(String input) {
            final boolean isEmpty;
            return isEmpty = (input == null) || (input.isEmpty());
        }

        public Builder getprojectKey(String projectKey) {
            if ((hasSpace(projectKey)) || (isEmpty(projectKey))) {
                builderprojectKey = null;
                return this;
            } else
                builderprojectKey = projectKey;
            return this;
        }

        public Builder ofType(String issueTypeDisplayName) {
            if ((hasSpace(issueTypeDisplayName)) || (isEmpty(issueTypeDisplayName))) {
                builderOftype = "Story";
                return this;
            } else
                builderOftype = issueTypeDisplayName;
            return this;
        }

        public Builder withPriority(String priorityDisplayName) {
            if ((hasSpace(priorityDisplayName)) || (isEmpty(priorityDisplayName))) {
                builderwithPriority = "Medium";
                return this;
            } else
                builderwithPriority = priorityDisplayName;
            return this;
        }

        public Builder withLabels(Collection<String> labels) {
            if ((labels == null) || (labels.isEmpty())) {
                builderwithLabels = null;
                return this;
            } else
                builderwithLabels = labels;
            return this;
        }

        public Builder withDescription(String content) {
            if (isEmpty(content)) {
                builderwithDescription = null;
                return this;
            } else builderwithDescription = content;
            return this;
        }

        public Builder withSummary(String summary) {
            if (isEmpty(summary)) {
                builderwithSummary = null;
                return this;
            } else builderwithSummary = summary;
            return this;
        }


        public JiraIssue create() throws IOException {

            try {
                final String creds = "LOGIN:PASS";

                final String urlStr = "https://jira.ithillel.com/rest/api/2/issue/";
                final URL urlObj = new URL(urlStr);
                final HttpsURLConnection httpCon = (HttpsURLConnection) urlObj.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Authorization", "Basic " + new Base64().encodeToString(creds.getBytes()));


                JsonObject createIssue = javax.json.Json.createObjectBuilder()
                        .add("fields",
                                javax.json.Json.createObjectBuilder().add("project",
                                        javax.json.Json.createObjectBuilder().add("key", builderprojectKey))
                                        .add("summary", builderwithSummary)
                                        .add("description", builderwithDescription)
                                        .add("labels", String.valueOf(builderwithLabels))
                                        .add("priority", builderwithPriority)
                                        .add("issuetype",
                                                javax.json.Json.createObjectBuilder().add("name", builderOftype))
                        ).build();

                System.out.println(createIssue.toString());

                final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
                out.write(createIssue.toString());
                out.close();


                final List<String> result = new ArrayList<String>();

                try {
                    InputStream inputStream = httpCon.getInputStream();
                    System.out.println(inputStream);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            BufferedReader rd;
//
//            try {
//                rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
//            } catch (Exception e) {
//                rd = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
//            }
//
//            String line;
//
//            while ((line = rd.readLine()) != null) {
//                result.add(line);
//            }
//
//            System.out.println(result);


            return new JiraIssue(builderprojectKey, builderOftype, builderwithPriority, builderwithLabels, builderwithDescription, builderwithSummary);

        }

    }


}
