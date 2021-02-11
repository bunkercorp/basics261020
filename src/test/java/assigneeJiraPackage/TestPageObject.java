package assigneeJiraPackage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class TestPageObject {

    Creds ola = new Creds();

    public String requestJiraData(final String urlStr, final String requestMethod, final String payload) throws IOException {

        final URL urlObj = new URL(urlStr);
        final HttpsURLConnection httpCon = (HttpsURLConnection) urlObj.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(requestMethod);
        httpCon.setRequestProperty("Authorization", "Basic " + new Base64().encodeToString(ola.creds.getBytes()));
        httpCon.setRequestProperty("User-Agent", "Legit user agent, for sure");

        if (payload != null) {
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setRequestProperty("Content-Length", "" + (payload == null ? 0 : payload.length()));
            final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(payload);
            out.close();
        }

        BufferedReader rd;
        try {
            rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (
                Exception e) {
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
