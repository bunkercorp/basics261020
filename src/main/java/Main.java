public class Main {
    public static void main(String[] args) {
        URL url = new URL.Composer()
                .authority(null, null)
                .fragment("dasdadasd")
                .port(65)
                .host("apple.com")
                .isSecure(false)
                //.path("1")
                .path("hu", "d/a", "yu")
                .compose();
        System.out.println(url);

    }
}



