
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Jiraissue {

    public String key;
    public Jiraissue(String projectKey){
        key = projectKey;
    }


    private class Buildit {
        String issueTypeDisplayName;
        String priorityDisplayName;
        String label;
        String content;
        String summary;



        public String ofType(String issueTypeDisplayName) {
            String res = "string";

            return res;
        }
    }

    public static boolean isExists(String str1, String str2){
        return str1.equalsIgnoreCase(str2);

    }

    public static String projectId(String project) throws IOException, InterruptedException {
        //String idProject;
        boolean isProject;
        String method = "GET";
        String getProjectURL = "https://jira.ithillel.com/rest/api/2/project/"+project;

        String response = HttpRequest.getApi(getProjectURL, method);
        JSONObject resObj = new JSONObject(response);


        if (isExists(resObj.get("key").toString(), project))
            return resObj.getString("id");
        else
            return null;


    }

     public static String ofType(String issueTypeDisplayName) throws IOException, InterruptedException {

         String idOfType;
         String method = "GET";
         String getTypeURL = "https://jira.ithillel.com/rest/api/2/issuetype";

         String response = HttpRequest.getApi(getTypeURL, method);
         JSONArray resArr = new JSONArray(response);

         System.out.println(response);

         int i = 0;

         while (i < resArr.length()) {

             if (isExists(resArr.getJSONObject(i).getString("name"), issueTypeDisplayName)) {
                 idOfType = resArr.getJSONObject(i).getString("id");
                 return idOfType;
             } else {
                 i++;
             }
         }
         return  null;
     }

     public static String withPriority(String priorityDisplayName) throws IOException, InterruptedException {
         String idOfPriority;
         String method = "GET";
         String getPriorityURL = "https://jira.ithillel.com/rest/api/2/priority";

         String response = HttpRequest.getApi(getPriorityURL, method);
         JSONArray resArr = new JSONArray(response);

         System.out.println(response);

         int i = 0;

         while (i < resArr.length()) {

             if (isExists(resArr.getJSONObject(i).getString("name"), priorityDisplayName)) {
                 idOfPriority = resArr.getJSONObject(i).getString("id");
                 return idOfPriority;
             } else {
                 i++;
             }
         }
         return  null;
     }

    static public void main(String[] args) throws InterruptedException, IOException {

        final String project = "AQ";

        String id = projectId(project);
        String type = ofType("Ошибка");
        System.out.println(type);
        System.out.println(withPriority("low"));
        String str1 = "str";
        String str2 = "STR";

       // System.out.println(str1.equalsIgnoreCase(str2));

    }


}
