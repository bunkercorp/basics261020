package com.ksenia.jira;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.ksenia.jira.JiraRequest.JiraAPICall.*;


public class JavaIssue {
    private Integer projectId;
    private Integer issueTypeId;
    private Integer priorityId;
    private Collection<String> labels;
    private String content;
    private String summary;
    private List<String> builderErrors = new ArrayList<>();

    public JavaIssue(String projectKey) {
        try {
            this.projectId = JiraRequest.getPropertyId(PROJECT, projectKey);
        }
        catch (JiraIssueBuilderException e) {
            this.builderErrors.add(e.getMessage());
        }
    }

    public JavaIssue ofType(String issueTypeDisplayName) {
        try {
            this.issueTypeId = JiraRequest.getPropertyId(ISSUE_TYPE, issueTypeDisplayName);
        }
        catch (JiraIssueBuilderException e) {
            this.builderErrors.add(e.getMessage());
        }

        return this;
    }

    public JavaIssue withPriority(String priorityDisplayName) {
        try {
            this.priorityId = JiraRequest.getPropertyId(PRIORITY, priorityDisplayName);
        }
        catch (JiraIssueBuilderException e) {
            this.builderErrors.add(e.getMessage());
        }
        return this;
    }

    public JavaIssue withLabels(Collection<String> labels) {
        this.labels = labels;
        return this;
    }

    public JavaIssue withDescription(String content) {
        this.content = content;
        return this;
    }

    public JavaIssue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String create() {
        if (!this.builderErrors.isEmpty()) {
            System.out.println("Failed to create jira issue because of: " + builderErrors);
        }
        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        fields.put("project", new JSONObject().put("id", "" + projectId));
        fields.put("summary", summary);
        fields.put("description", content);
        fields.put("reporter", new JSONObject().put("name", "okrybak"));
        fields.put("issuetype", new JSONObject().put("id", issueTypeId));
        fields.put("priority", new JSONObject().put("id", priorityId));
        if (this.labels != null && !this.labels.isEmpty()) {
            JSONArray labels = new JSONArray();
            this.labels.stream().distinct().forEach(labels::put);
            fields.put("labels", labels);
        }
        issue.put("fields", fields);

        return JiraRequest.createIssue(ISSUE, issue.toString());
    }
}
