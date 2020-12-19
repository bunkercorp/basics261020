import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public class URL {

    public final String scheme;
    public int port;
    public final String path;
    public final String host;
    public String authority;
    public final String fragment;
    private final String str;

    private URL(String inputScheme, String inputAuthority, int inputPort, String inputHost, String inputPath, String inputFragment) {

        authority = inputAuthority;
        scheme = inputScheme;
        port = inputPort;
        host = inputHost;
        path = inputPath;
        fragment = inputFragment;

        StringBuilder resultString = new StringBuilder();
        resultString.append(scheme).append(":").append("//");
        if (authority != null)
            resultString.append(authority).append("@");
        resultString.append(host);
        if (((!scheme.equals("http")) || (port != 80)) && ((!scheme.equals("https")) || (port != 443)) && (port != 0))
            resultString.append(":").append(port);
        if (path != null)
            resultString.append("/").append(path);
        if (fragment != null)
            resultString.append("#").append(fragment);

        str = resultString.toString();
    }

    @Override
    public String toString() {
        return str;
    }

    public static class Composer {
        private String composerScheme;
        private String composerAuthority;
        private int composerPort;
        private String composerHost;
        private String composerPath;
        private String composerFragment;


        public static boolean hasSpace(String input) {
            final boolean hasSpace;
            return hasSpace = (input.indexOf(' ') != -1);
        }

        public static boolean hasColumn(String input) {
            final boolean hasColumn;
            return hasColumn = (input.indexOf(58) != -1);
        }

        public static boolean hasDot(String input) {
            final boolean hasDot;
            return hasDot = (input.indexOf(46) != -1);
        }

        public static boolean isEmpty(String input) {
            final boolean isEmpty;
            return isEmpty = (input == null) || (input.isEmpty());
        }

        public static boolean validateIP4(final String ip) {
            String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
            return ip.matches(PATTERN);
        }

        public static boolean validateIP6(final String ip) {
            String PATTERN = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";
            return ip.matches(PATTERN);
        }


        public Composer isSecure(boolean scheme) {
            if (scheme) {
                composerScheme = "https";
                return this;
            } else
                composerScheme = "http";
            return this;
        }

        public Composer authority(String inputLogin, String inputPassword) {
            if ((hasSpace(inputLogin)) || (hasSpace(inputPassword)) || (isEmpty(inputLogin) && !isEmpty(inputPassword))) {
                composerAuthority = " ";
                return this;
            } else if (isEmpty(inputLogin)) {
                composerAuthority = null;
                return this;
            } else if (isEmpty(inputPassword)) {
                composerAuthority = inputLogin;
                return this;
            } else
                composerAuthority = String.format("%s:%s", inputLogin, inputPassword);
            return this;
        }

        public Composer authority(String inputLogin) {
            if (hasSpace(inputLogin)) {
                composerAuthority = " ";
                return this;
            } else if (isEmpty(inputLogin)) {
                composerAuthority = null;
                return this;
            } else
                composerAuthority = inputLogin;
            return this;
        }

        public Composer port(int inputPort) {
            if ((inputPort < 0) || (inputPort > 65536)) {
                composerPort = -1;
                return this;
            } else if (inputPort == 0) {
                composerPort = 0;
                return this;
            } else
                composerPort = inputPort;
            return this;
        }

        public Composer path(String inputPath) {
            if (hasSpace(inputPath)) {
                composerPath = " ";
                return this;
            } else if (isEmpty(inputPath)) {
                composerPath = null;
                return this;
            } else composerPath = inputPath;
            return this;
        }

        public Composer path(String... inputPath) {
            StringBuilder stringComposerPath = new StringBuilder();
            for (int i = 0; i < inputPath.length; i++) {
                if (hasSpace(inputPath[i])) {
                    composerPath = " ";
                    return this;
                } else if (isEmpty(inputPath[i])) {
                    composerPath = null;
                    return this;
                } else {
                    stringComposerPath.append(inputPath[i])
                            .append("/");
                    composerPath = stringComposerPath.toString();
                }
            }
            return this;
        }

        public Composer fragment(String inputFragment) {
            if (hasSpace(inputFragment)) {
                composerFragment = " ";
                return this;
            } else if (isEmpty(inputFragment)) {
                composerFragment = null;
                return this;
            }
            composerFragment = inputFragment;
            return this;
        }


        public Composer host(String inputHost) {
            if (hasSpace(inputHost) || isEmpty(inputHost)
                    || (!hasDot(inputHost) && !inputHost.equals("localhost"))
                    || (inputHost.matches("\\P{IsAlphabetic}+") && !validateIP4(inputHost))
                    || (hasColumn(inputHost) && !validateIP6(inputHost))
            ) {
                composerHost = null;
                return this;
            } else
                composerHost = inputHost;
            return this;
        }


        public URL compose() {


            if (((composerScheme != null) && (composerHost != null)) && ((composerFragment == null) || (composerAuthority == null) || (composerPort == 0) || (composerPath == null))) {
                return new URL(composerScheme, composerAuthority, composerPort, composerHost, composerPath, composerFragment);
            } else if ((composerScheme == null) || (composerHost == null) || (composerFragment.equals(" "))
                    || (composerPath.equals(" ")) || (composerPort == -1) || (composerAuthority.equals(" "))) {
                return null;
            }

            return null;
        }

    }


}
