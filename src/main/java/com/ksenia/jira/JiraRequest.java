package com.ksenia.jira;

import org.json.JSONArray;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class JiraRequest {

    private static final String HOST = "https://jira.ithillel.com/rest/api/2/";

    public enum JiraAPICall {
        PROJECT("project"), ISSUE_TYPE("issuetype"), PRIORITY("priority"), ISSUE("issue");

        private String url;

        JiraAPICall(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public static String createIssue(JiraAPICall jiraAPICall, String payload) {
        try {
            return request(HOST + jiraAPICall.getUrl(), "POST", payload);
        }
        catch (IOException e) {
            throw new JiraIssueBuilderException("Failed to create jira issue", e);
        }
    }

    public static Integer getPropertyId(JiraAPICall jiraAPICall, String property) throws JiraIssueBuilderException {
        try {
            final JSONArray array = new JSONArray(request(HOST + jiraAPICall.getUrl(),
                    "GET", null));
            for (int i = 0; i < array.length(); i += 1) {
                if (array.getJSONObject(i).getString("name").contentEquals(property)) {
                    return array.getJSONObject(i).getInt("id");
                }
            }
            throw new JiraIssueBuilderException(String.format("Invalid %s value %s", jiraAPICall.getUrl(), property));
        }
        catch (IOException e) {
            throw new JiraIssueBuilderException("Failed to get list of " + jiraAPICall.getUrl(), e);
        }
    }

    private static String request(final String urlStr, final String requestMethod, final String payload)
            throws IOException {
        final URL urlObj = new URL(urlStr);
        final HttpsURLConnection httpCon = (HttpsURLConnection) urlObj.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(requestMethod);
        httpCon.setRequestProperty("Authorization", "Basic " + Credentials.getBase64Creds());
        httpCon.setRequestProperty("User-Agent", "Legit user agent, for sure");

        if (payload != null) {
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setRequestProperty("Content-Length", "" + payload.length());
            final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(payload);
            out.close();
        }

        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        }
        catch (Exception e) {
            rd = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }

        String line;
        StringBuilder result = new StringBuilder();

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

}
