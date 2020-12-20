package hw_7;

public class Main {
    public static void main(String[] args) {
        Url url = new Url.Builder("pop.ru")
                .isSecure(false)
                .authority("va//no")
//                .authority("Vano", "Nano")
//                .port(90)
//                .path("/hello")
//                .path("he llo", "", "/wor/ld")
                .path()
                .fragment("null")
                .build();
        System.out.println(url);
    }
}
