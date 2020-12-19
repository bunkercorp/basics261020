import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.InetAddressValidator;

public class URL {

    public enum Sheme{
        http,
        https;
    }


    public Sheme sheme;
    public String uname;
    public String pwd;
    public String host;
    public int port;
    public String path;
    public String fragment;


    private URL( Sheme inputSh, String inputUname, String inputPwd, String inputHost,
                 int inputPort, String inputPath,  String inputFr){

        sheme = inputSh;
        uname = inputUname;
        pwd = inputPwd;
        host = inputHost;
        port = inputPort;
        path = inputPath;
        fragment = inputFr;


    }

    @Override
    public String toString(){
        if(host == null)
            return null;
        StringBuilder output = new StringBuilder();
        output.append(sheme).append("://");
        if(uname != null) {
            output.append(uname);
            if (pwd != null)
                output.append(":").append(pwd);
            output.append("@");
        }
        output.append(host);
        if(port != 0 && port != 80 && port != 443 )
            output.append(port);
        if(path != null)
            output.append(path);
        if(fragment != null)
            output.append(fragment);

            return output.toString();
    }





    public static class Composer{
        private Sheme composerSheme = Sheme.http;
        private int composerPort = 80;
        private String composerPath;
        private String composerUname;
        private  String composerPwd;
        private String composerHost;
        private String composerFragment;

        public Composer isSecure(boolean a){
            if(a){
                composerSheme = Sheme.https;
                composerPort = 443;
            }

            return this;
        }



        private boolean isIP (String str){
            InetAddressValidator validator = InetAddressValidator.getInstance();
           return validator.isValid(str);

        }

        public  Composer host(String str){
            boolean isRightDomen = str.substring(3).contains(".")
                    && !str.substring(str.length() - 2).contains(".");
            if(isIP(str) || isRightDomen)
                composerHost = str;

            return this;
        }

        public Composer port(int a){
            if( a < 0 || a > 65535)
                return null;
            composerPort = a;
            return this;
        }

//        private Composer emptyPort(){
//            if((composerPort < 1 && composerShema == Sheme.https)
//            composerPort = 443;
//            return this;
//        }

        public Composer path (String input){
            StringBuilder buildPath = new StringBuilder();
            buildPath.append(composerPath);
            if(!input.contains(" ")){
                if(input.indexOf("/") == 0)
                buildPath.append(input);
                else buildPath.append("/").append(input);
            }
            composerPath = buildPath.toString();
            return this;
        }

        public Composer path (String... args){

            if(args.length > 0){
                for(String text : args){
                    path(text);
//                    if(text.indexOf("/") !=0)
//                        composerPath = composerPath.concat("/" + text);
//                    else composerPath = composerPath.concat(text);
                }
            }
            return this;

        }

        public Composer authority(String uname){
            if(!uname.substring(uname.length() - 1).contains("@"))
                composerUname = uname + "@";
            else composerUname = uname;

            return this;
        }

        public Composer authority(String uname, String pwd){
            if(uname.length() > 0 && pwd.length() > 0) {
                composerUname = uname + ":";
                composerPwd = pwd + "@";

            }

            return this;
        }

        public Composer fragment(String input) {
            if(!input.isEmpty()){
               composerFragment = input.indexOf("#") == 0? input : "#" + input;
            }
            return this;

        }

        public URL composer(){


            return new URL(
                    composerSheme,
                    composerUname,
                    composerPwd,
                    composerHost,
                    composerPort,
                    composerPath,
                    composerFragment);
        }


    }



}
