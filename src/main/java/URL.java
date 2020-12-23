import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.InetAddressValidator;

public class URL {

    public enum Sheme{
        http,
        https;
    }


    final public Sheme sheme;
    final public String uname;
    final public String pwd;
    final public String host;
    final public int port;
    final public String path;
    final public String fragment;


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
// смысл?
    private URL(){
        sheme = null;
        uname = null;
        pwd = null;
        host = null;
        port = 0;
        path = null;
        fragment = null;

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
            output.append(":").append(port);
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
        private String composerPwd;
        private String composerHost;
        private String composerFragment;

        public Composer isSecure(boolean a){
            if(a){
                composerSheme = Sheme.https;
                composerPort = 443;
            }

            return this;
        }

        // смысл? этот код встречается только в одном методе.
        private boolean isIP (String str){
            InetAddressValidator validator = InetAddressValidator.getInstance();
           return validator.isValid(str);

        }

        public  Composer host(String str){
            if(str.isEmpty())
                return this;

            boolean isRightDomen = str.substring(3).contains(".")
                    && !str.substring(str.length() - 2).contains(".") && !str.contains(" ");

            if(isIP(str) || isRightDomen || str.equals("localhost"))
                composerHost = str;

            return this;
        }

        public Composer port(int a){
            if( a < 0 || a > 65535)
//              с чего бы? этот метод всегда должен вернуть инстанцию билдера
                return null;
            composerPort = a;
            return this;
        }


        public Composer path (String input){
            StringBuilder buildPath = new StringBuilder();
            if(composerPath != null)
            buildPath.append(composerPath);

            if(!input.contains(" ") && !input.isEmpty()){
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

                }
            }
            return this;

        }

        public Composer authority(String uname){
            if(uname.length() > 0 && !uname.contains(" ")) {
                    composerUname = uname;
            }

            return this;
        }

        public Composer authority(String uname, String pwd){
            authority(uname);

            if(pwd.length() > 0 && !pwd.contains(" ")) {
                composerPwd = pwd;
            }

            return this;
        }

        public Composer fragment(String input) {
            if(!input.isEmpty() && !input.contains(" ")){
               composerFragment = input.indexOf("#") == 0? input : "#" + input;
            }
            return this;

        }

        public URL composer(){
            if(composerHost == null){
               //return null
                return new URL();
            }

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
