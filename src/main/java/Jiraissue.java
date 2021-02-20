
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Jiraissue {

    String idProject;
    String idOfType;
    String idOfPriority;
    String label;
    String content;
    String summary;



    public Jiraissue(String idPr, String idType, String idPriority,  String label,
            String content, String summary) {
        idProject = idPr;
        idOfType = idType;
        idOfPriority = idPriority;
        label = label;
        content = content;
        summary = summary;


    }


    public static class Buildit {
        String idProject;
        String idOfType;
        String idOfPriority;
        String label;
        String content;
        String summary;


        public static boolean isExists(String str1, String str2) {
            return str1.equalsIgnoreCase(str2);

        }

        public Buildit projectId(String project) throws IOException, InterruptedException {
            String method = "GET";
            String getProjectURL = "https://jira.ithillel.com/rest/api/2/project/" + project;

            String response = HttpRequest.getApi(getProjectURL, method);
            JSONObject resObj = new JSONObject(response);


            if (isExists(resObj.get("key").toString(), project))
                idProject = resObj.getString("id");
            else
                idProject = null;

            return this;

        }

        public Buildit ofType(String issueTypeDisplayName) throws IOException, InterruptedException {

            String method = "GET";
            String getTypeURL = "https://jira.ithillel.com/rest/api/2/issuetype";

            String response = HttpRequest.getApi(getTypeURL, method);
            JSONArray resArr = new JSONArray(response);

            //System.out.println(response);

            int i = 0;

            while (i < resArr.length()) {

                if (isExists(resArr.getJSONObject(i).getString("name"), issueTypeDisplayName)) {
                    idOfType = resArr.getJSONObject(i).getString("id");
                    return this;
                } else {
                    i++;
                }
            }
            idOfType = null;
            return this;
        }

        public Buildit withPriority(String priorityDisplayName) throws IOException, InterruptedException {
            String method = "GET";
            String getPriorityURL = "https://jira.ithillel.com/rest/api/2/priority";

            String response = HttpRequest.getApi(getPriorityURL, method);
            JSONArray resArr = new JSONArray(response);

            System.out.println(response);

            int i = 0;

            while (i < resArr.length()) {

                if (isExists(resArr.getJSONObject(i).getString("name"), priorityDisplayName)) {
                    idOfPriority = resArr.getJSONObject(i).getString("id");
                    return this;
                } else {
                    i++;
                }
            }
            idOfPriority = null;
            return this;
        }

        public Jiraissue build(){
//            if(idProject == null){
//                //return null
//                return new Jiraissue();
//            }

            return new Jiraissue(
                    idProject,
           idOfType,
           idOfPriority,
           label,
            content,
             summary);
        }

        static public void main(String[] args) throws InterruptedException, IOException {

        }


    }
}
