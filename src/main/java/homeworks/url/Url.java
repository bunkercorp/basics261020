package homeworks.url;

public class Url {
    private final String schemeConst = "http";
    private final String schemeSecureConst = "https";
    private final String portConst = "80";
    private final String portSecureConst = "443";
    private final int maxPortConst = 65636;

    private String scheme;
    private String host;
    private String port;
    private String path;
    private String authority_login;
    private String authority_passw;
    private String fragment;

    //private.
    public Url(String host) {
        if (host != null) {
            this.host = host;
            scheme = schemeSecureConst;
            port = portSecureConst;
        }
    }


    public class Composer {

        public Composer isSecure(boolean isSecure) {
            if (isSecure) {
             // воу-воу. Нужно собирать данные и потом в едином порыве их пробрасыват ьв конструктор, а не вот это вот.
                Url.this.scheme = schemeSecureConst;
                if (Url.this.port == null) {
                    Url.this.port = portSecureConst;
                }
            } else {
                Url.this.scheme = schemeConst;
                if (Url.this.port == null) {
                    Url.this.port = portConst;
                }
            }
            return this;
        }

        public Composer port(int port) {
            if (port >= 0 && port < maxPortConst) {
                Url.this.port = String.valueOf(port);
            }
            return this;
        }

        public Composer path(String path) {
            if (Url.this.path == null && !path.isEmpty() && !path.contains(" ") && !path.contains("\'") && !path.contains("/")) {
                Url.this.path = path;
            }
            return this;
        }

        public Composer path(String... paths) {
            if (Url.this.path == null) {
                StringBuilder strBuilder = new StringBuilder();
                for (String path : paths) {
                    if (!path.isEmpty() && !path.contains(" ") && !path.contains("\'") && !path.contains("/")) {
                        strBuilder.append(path).append("/");
                    }
                }
                if (strBuilder.length() > 0 && strBuilder.charAt(strBuilder.length() - 1) == '/') {
                    strBuilder.deleteCharAt(strBuilder.length() - 1);
                }
                Url.this.path = strBuilder.toString();
            }
            return this;
        }

        public Composer authority(String login) {
            if (!login.isEmpty() && Url.this.authority_login == null && Url.this.authority_passw == null) {
                Url.this.authority_login = login;
            }
            return this;
        }

        public Composer authority(String login, String passw) {
            if (!passw.isEmpty() && Url.this.authority_login == null && Url.this.authority_passw == null) {
                Url.this.authority_login = login;
                Url.this.authority_passw = passw;
            }
            return this;
        }

        public Composer fragment(String fragment) {
            if (!fragment.isEmpty()) {
                Url.this.fragment = fragment;
            }
            return this;
        }

        public Url compose() {
            Url url = new Url(Url.this.host);
            url.scheme = Url.this.scheme;
            url.port = Url.this.port;
            url.path = Url.this.path;
            url.authority_login = Url.this.authority_login;
            url.authority_passw = Url.this.authority_passw;
            url.fragment = Url.this.fragment;

            return url;
        }
    }

    public Composer newComposer() {
        return new Url(host).new Composer();
    }

    @Override
    public String toString() {
        if (host == null) {
            return null;
        }
// а чем
        String login = "";
        if (authority_login != null && authority_passw == null) {
            login = String.format("%s@", authority_login);
        } else if (authority_login == null && authority_passw != null) {
            login = String.format(":%s@", authority_passw);
        } else if (authority_login != null && authority_passw != null) {
            login = String.format("%s:%s@", authority_login, authority_passw);
        }
// же не
        String port = "";
        if (!this.port.isEmpty() && !this.port.equals(portConst) && !this.port.equals(portSecureConst)) {
            port = ":" + this.port;
        }
// угодил
        String fragment = "";
        if (this.fragment != null) {
            fragment = "#" + this.fragment;
        }
// StringBuilder ?
        String path = "";
        if (this.path != null) {
            path = "/" + this.path;
        }

        return String.format("%s://%s%s%s%s%s", scheme, login, host, port, path, fragment);
    }
}
