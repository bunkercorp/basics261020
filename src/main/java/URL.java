import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URL {

    private boolean isSecure;
    private int port = -1;
    private String path;
    private String authority;
    private String fragment;
    private String host;


    @Override
    public String toString() {
        StringBuilder url = new StringBuilder();
        if (isSecure) {
            url.append("https");
        } else {
            url.append("http");
        }
        url.append("://");
        if (!(authority == null || authority.length() == 0)) {
            url.append(authority).append("@");
        }
        url.append(host);
        boolean isHidePort = port == 443 && isSecure;
        isHidePort |= port == 80 && !isSecure;
        if (!isHidePort) {
            url.append(":").append(port);
        }
        if (!(path == null || path.length() == 0)) {
            url.append("/").append(path);
        }
        if (!(fragment == null || fragment.length() == 0)) {
            url.append("#").append(fragment);
        }
        return url.toString();

    }

    public static class Composer {

        private URL url = new URL();

        private final String SUB_DELIMS = "!$&'()*+,;=";
        private final String NOT_RESERVED_SYMBOL;

        private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
        private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";


        public Composer() {
            StringBuilder alphaBuilder = new StringBuilder();
            for (char c = 'a'; c <= 'z'; c++) {
                alphaBuilder.append(c).append((char) (c - 32));
            }

            String ALPHA = alphaBuilder.toString();

            StringBuilder digitBuilder = new StringBuilder();
            for (char c = '0'; c <= '9'; c++) {
                digitBuilder.append(c);
            }

            String DIGIT = digitBuilder.toString();

            NOT_RESERVED_SYMBOL = ALPHA + DIGIT + "-._~";
        }


        private boolean validateHost() {
            if (url.host == null || url.host.length() < 6) {
                return false;
            }

            return validateStringData(url.host, NOT_RESERVED_SYMBOL + SUB_DELIMS + "%")
                    || isHostIpV4() || isHostIpV6();
        }

        private boolean isHostIpV4() {
            Pattern patternIpv4 = Pattern.compile(ipv4Pattern);
            Matcher matcherIpv4 = patternIpv4.matcher(url.host);
            return matcherIpv4.matches();

        }

        private boolean isHostIpV6() {
            Pattern patternIpv6 = Pattern.compile(ipv6Pattern);
            Matcher matcherIpv6 = patternIpv6.matcher(url.host);
            return matcherIpv6.matches();
        }

        private boolean validateStringData(String data, String delimiters) {

            for (int i = 0; i < data.length(); i++) {
                if (delimiters.indexOf(data.charAt(i)) == -1) {
                    return false;
                }
            }

            return true;
        }

        private boolean validatePort() {
            return 1 <= url.port && url.port <= 65535;
        }

        private boolean validatePath() {
            if (url.path == null || url.path.length() == 0) {
                return true;
            }

            return validateStringData(url.path, NOT_RESERVED_SYMBOL + SUB_DELIMS + "%:@/");

        }

        private boolean validateAuthority() {
            if (url.authority == null || url.authority.length() == 0) {
                return true;
            }
            return validateStringData(url.authority, NOT_RESERVED_SYMBOL + SUB_DELIMS + "%:");
        }

        public Composer isSecure(boolean value) {
            url.isSecure = value;
            return this;
        }

        public Composer host(String codeUrl) {
            url.host = codeUrl;
            return this;
        }

        public Composer port(int codeMassage) {
            url.port = codeMassage;
            return this;
        }

        public Composer path(String paths) {
            url.path = paths;
            return this;
        }

        public Composer path(String... paths) {
            StringBuilder pathsCompilation = new StringBuilder();


            for (String s : paths) {
                pathsCompilation.append(s).append("/");
            }
            url.path = pathsCompilation.toString();
            return this;
        }

        public Composer authority(String userName) {
            url.authority = userName;
            return this;
        }

        public Composer authority(String userName, String password) {

            if (password == null || password.isEmpty()) {
                url.authority = userName;
            }
            if (userName == null || userName.length() == 0) {
                url.authority = null;
            } else {
                url.authority = userName + ":" + password;
            }
            return this;
        }

        public Composer fragment(String fragments) {
            url.fragment = fragments;
            return this;
        }

        public URL compose() {
            if (url.port == -1) {
                url.port = url.isSecure ? 443 : 80;
            }
            boolean isValid = validateHost();
            isValid &= validateAuthority();
            isValid &= validatePath();
            isValid &= validatePort();
            if (!isValid) {
                return null;
            }

            return url;
        }


    }


}






