
public class Main {


    public static  void main(String[] args){

        URL myUrl = new URL.Composer()
                .isSecure(true)
                .authority(" ", "passw345")
                .path("")
                .port(467)
                .host("loca.lhyui.rt")
                .fragment("#yui")
                .composer();
        System.out.println(myUrl.toString());

    }
}
