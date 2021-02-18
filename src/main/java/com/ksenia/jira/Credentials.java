package com.ksenia.jira;

import java.util.Base64;

public class Credentials {
    private static final String USER = "okrybak";
    private static final String PASSWORD = "Qazxsw23";
    private static final String DELIMITER = ":";

    public static String getBase64Creds() {
        return Base64.getEncoder().encodeToString((USER + DELIMITER + PASSWORD).getBytes());
    }
}
