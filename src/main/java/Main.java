
import org.apache.commons.validator.routines.InetAddressValidator;
import java.net.Inet4Address;
import java.lang.Object;
import java.net.InetAddress;

public class Main {


    public static  void main(String[] args){

        URL myUrl = new URL.Composer()
                .isSecure(false)
                .authority("name")
                .path("ert", "dfgh")
                .host("localhost")
                .fragment("#yui")
                .composer();
        System.out.println(myUrl.toString());

    }
}
