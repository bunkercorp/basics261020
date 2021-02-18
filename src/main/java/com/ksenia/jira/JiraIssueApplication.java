package com.ksenia.jira;

public class JiraIssueApplication {
    public static void main(String[] args) {
        final String createdIssue = new JavaIssue("AQANS")
                .ofType("Ошибка")
                .withPriority("Low")
                .withLabels(null)
                .withDescription("resttest")
                .withSummary("Testoxy")
                .create();

        System.out.println(createdIssue);
    }
}
