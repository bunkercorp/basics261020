package homeworks.url;

public class Main {

    public static void main(String[] args) {

        Url url = new Url("google.com").newComposer()
                .authority(null, "ygf6stf7ye")
                .authority("blabla")
                .fragment("ughuhdirg")
                .port(65)
                .isSecure(false)
                //.path("1")
                .path("hu", "d/a", "yu")
                .compose();

        System.out.println(url);

    }
}
