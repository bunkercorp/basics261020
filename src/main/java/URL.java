import java.util.regex.Pattern;

public class URL {
    public final String proto;
    public final int number;
    public final String strPath;
    public final String user;
    public final String pwd;
    public final String strFrag;
    public final String host;

    private URL(String pr, String usr, String pw, String hst, int prt, String pth, String frag){
        proto = pr;
        user = usr;
        pwd = pw;
        host = hst;
        number = prt;
        strPath = pth;
        strFrag = frag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(proto);
        if (!user.isEmpty() && !pwd.isEmpty()) sb.append(user).append(":").append(pwd).append("@");
        else if (pwd.isEmpty() && !user.isEmpty()) sb.append(user).append("@");
        sb.append(host.toLowerCase());
        if (number != 80 && number !=443) sb.append(":").append(number);
        if (strPath != null) sb.append(strPath);
        if (strFrag != null) sb.append("#").append(strFrag);
        return sb.toString();
    }

    public static class Composer{
        final private String s = "not valid";
        private String protocol;
        private int portNumber;
        private String Path;
        private String User = "";
        private String Password = "";
        private String Fragment;
        private String server;

        private static boolean validString(String input) {
            return  (input == null || input.isEmpty());
        }

        private static boolean validChars(String input){
            for (int i = 0; i < input.length(); i++){
                final boolean chrs = (input.charAt(i) > 35 && input.charAt(i) < 60 ||
                        input.charAt(i) == 61 || input.charAt(i) == 95 ||
                        input.charAt(i) == 126 || input.charAt(i) == 33 ||
                        input.charAt(i) > 63 && input.charAt(i) < 91 ||
                        input.charAt(i) > 96 && input.charAt(i) < 123);
                if (!chrs) return false;
            }
            return true;
        }

        public Composer addHost(String hst) {
            if (validString(hst)) return this;
            final String ipv4
                    = "^[^0](25[0-5]|2[0-4][0-9]|[1][0-9][0-9]|[1-9]?[0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[1][0-9][0-9]|[1-9]?[0-9])\\." +
                    "(25[0-5]|2[0-4][0-9]|[1][0-9][0-9]|[1-9]?[0-9])\\." +
                    "(25[0-5]|2[0-4][0-9]|[1][0-9][0-9]|[1-9]?[0-9])$";
            final String ipv6 = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
            Pattern s = Pattern.compile(ipv4, Pattern.CASE_INSENSITIVE);
            Pattern s2 = Pattern.compile(ipv6, Pattern.CASE_INSENSITIVE);
            if (s.matcher(hst).matches() || s2.matcher(hst).matches()) {
                server = hst;
                return this;
            }
            for (int j = hst.length() - 1; j > 0; j--){
                if (hst.charAt(j) == 46) break;
                else if (hst.charAt(j) > 47 && hst.charAt(j) < 58)
                    return this;
            }
            for (int i = 0; i < hst.length(); i++) {
                final boolean notValidChar = (hst.charAt(0) == 45 ||
                        hst.charAt(0) == 46 ||
                        hst.charAt(hst.length() - 1) == 46);
                if (notValidChar) return this;
                if (i < hst.length() - 1) {
                    if (hst.charAt(i) == 46 && hst.charAt(i + 1) == 46) return this;
                }
                final boolean words = (hst.charAt(i) == 45 || hst.charAt(i) == 46 ||
                        (hst.charAt(i) > 47 && hst.charAt(i) < 58) ||
                        (hst.charAt(i) > 64 && hst.charAt(i) < 91) ||
                        hst.charAt(i) > 96 && hst.charAt(i) < 123);
                if (!words) {
                    return this;
                }
            }
            server = hst;
            return this;
        }

        public Composer isSecure(boolean proto){
            if (proto) {
                protocol = "https://";
                portNumber = 443;
            }
            else{
                protocol = "http://";
                portNumber = 80;
            }
            return this;
        }

        public Composer port(int number) {
            if (number > 65535 || number < 1) {
                portNumber = 0;
                return this;
            }
            else portNumber = number;
            return this;
        }

        public Composer authority(String usr) {
            if (validString(usr)) {
                User = s;
                return this;
            }
            for (int i = 1; i < usr.length(); i++) {
                if (!validChars(usr)) {
                    User = s;
                    return this;
                }
            }
            User = usr;
            return this;
        }

        public Composer authority(String usr, String pwd) {
            if (validString(usr) || pwd == null) {
                User = s;
                return this;
            }
            for (int i = 1; i < usr.length(); i++) {
                if (!validChars(usr)) {
                    User = s;
                    return this;
                }
            }
            User = usr;
            if (pwd.isEmpty()) return this;
            Password = pwd;
            return this;
        }

        public Composer path(String pth) {
            if (validString(pth) ||
                    !pth.startsWith("/")
                    || pth.endsWith("/")) {
                Path = s;
                return this;
            }
            for (int i = 1; i < pth.length(); i++) {
                if (!validChars(pth) || pth.charAt(i) == 47 && pth.charAt(i-1) == 47) {
                    Path = s;
                    return this;
                }
            }
            Path = pth;
            return this;
        }

        public Composer path(String ... paths){
            StringBuilder sb = new StringBuilder();
            for(String path : paths){
                if (validString(path)) {
                    Path = s;
                    return this;
                }
                for (int i = 0; i < path.length(); i++){
                    if (!validChars(path)) {
                        Path = s;
                        return this;
                    }
                }
                sb.append("/").append(path);
            }
            Path = sb.toString();
            return this;
        }

        public Composer fragment(String frgmnt) {
            if (validString(frgmnt)) return this;
            if (frgmnt.indexOf(' ') == -1) {
                Fragment = frgmnt;
                return this;
            } else {
                Fragment =s;
                return this;
            }
        }

        public URL Compose() {
            if (server == null || protocol == null || portNumber == 0 ||
                    User == "not valid" || Path == "not valid" ||
                    server == "not valid" || Fragment == "not valid") return null;
            return new URL(protocol, User, Password, server, portNumber, Path, Fragment);
        }
    }
}
