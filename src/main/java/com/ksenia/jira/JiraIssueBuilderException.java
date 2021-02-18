package com.ksenia.jira;

public class JiraIssueBuilderException extends RuntimeException {

    public JiraIssueBuilderException(String message) {
        super(message);
    }

    public JiraIssueBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
