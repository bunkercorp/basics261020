import org.apache.commons.codec.binary.Base64;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpRequest {

    public static String getApi(String urlApi, String method) throws InterruptedException, IOException {
        //String jiraKey = key;

        final Creds oAuth = new Creds();
        final URL urlObj = new URL(urlApi);
        final HttpsURLConnection httpCon = (HttpsURLConnection) urlObj.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(method);
        httpCon.setRequestProperty("Authorization", "Basic " + new Base64().encodeToString(oAuth.creds.getBytes()));
        StringBuilder result = new StringBuilder();
        //String result;

        BufferedReader rd;

        try {
            rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        } catch (Exception e) {
            rd = new BufferedReader(new InputStreamReader(httpCon.getErrorStream()));
        }

        String line;

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        //result = result.toString();

       // System.out.println("before" + result);

//            while(result.charAt(0) != '{'){
//            result.deleteCharAt(0);
//        }

        //System.out.println("after" + result);


            return result.toString();
        //JSONObject response = new JSONObject(result.toString());
        //System.out.println("return" + result);
        //return response;
    }


}
