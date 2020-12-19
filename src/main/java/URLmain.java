public class URLmain {

    public static void main(String[] args) {

        URL newLink = new URL.Composer()
                .isSecure(true)
                .authority("abs")
                .authority("abs", "123")
                //    .port(443)
                .host("google.com")
                //   .path("", "ipsum")
                //    .fragment("")
                .compose();

        System.out.println(newLink);


    }
}
