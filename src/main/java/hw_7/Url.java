package hw_7;

public class Url {
    private final String scheme;
    private final String authority;
    private final String host;
    private final int port;
    private final String path;
    private final String fragment;

    public Url(String scheme, String authority, String host, int port, String path, String fragment) {
        this.scheme = scheme;
        this.authority = authority;
        this.host = host;
        this.port = port;
        this.path = path;
        this.fragment = fragment;
    }

    public static class Builder {
        private String scheme;
        private String authority;
        private final String host;
        private int port;
        private String path;
        private String fragment;

        public Builder(String host) {
          // в IPv6 нет точек. И что?
            if (host == null || host.isEmpty() || !host.contains(".")) {
                throw new RuntimeException("Host for URl is required");
            }
            this.host = host;
        }

        public Builder isSecure(boolean isSecure) {
            if (isSecure) {
                this.scheme = "https";
                this.port = 443;
            } else {
                this.scheme = "http";
                this.port = 80;
            }
            return this;
        }

        public Builder authority(String user) {
            if (isStringNotEmptyWithoutSpaces(user)) {
                this.authority = user;
            }
            return this;
        }

        public Builder authority(String user, String password) {
            if (isStringNotEmptyWithoutSpaces(user) && isStringNotEmptyWithoutSpaces(password)) {
                this.authority = String.format("%s:%s", user, password);
            }
            return this;
        }

        public Builder port(int port) {
            if (port >= 0 && port <= 65535) {
                this.port = port;
            }
            return this;
        }

        public Builder path(String path) {
            if (isStringNotEmptyWithoutSpaces(path)) {
                this.path = path.charAt(0) != '/' ? "/" + path : path;
            }
            return this;
        }

        public Builder path(String... paths) {
            StringBuilder stringPath = new StringBuilder();
            for (String path : paths) {
                if (isStringNotEmptyWithoutSpaces(path) && !path.contains("/")) {
                    stringPath.append("/").append(path);
                }
            }
            this.path = stringPath.toString();
            return this;
        }

        public Builder fragment(String fragment) {
            if (isStringNotEmptyWithoutSpaces(fragment)) {
                this.fragment = fragment;
            }
            return this;
        }
// а где возврат нулла если данные ,собранные билдером, невалидны?
        public Url build() {
            return new Url(scheme, authority, host, port, path, fragment);
        }

        private boolean isStringNotEmptyWithoutSpaces(String value) {
            return value != null && !value.contains(" ") && !value.trim().isEmpty() && !value.contains("//")
                    && !value.contains("@") && !value.contains("#");
        }
    }

    @Override
    public String toString() {
        StringBuilder url = new StringBuilder(scheme);
        url.append("://");
        if (authority != null) {
            url.append(authority).append("@");
        }
        url.append(host).append(":");
        url.append(port);
        if (path != null && !path.isEmpty()) {
            url.append(path);
        }
        url.append("#").append(fragment);
        return url.toString();
    }
}


