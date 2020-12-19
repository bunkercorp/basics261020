
import org.apache.commons.validator.routines.InetAddressValidator;
import java.net.Inet4Address;
import java.lang.Object;
import java.net.InetAddress;

public class Main {


    public static  void main(String[] args){
       // String str = new String("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
//        System.out.println(str.indexOf("//"));
        //InetAddressValidator validator = InetAddressValidator.getInstance();
        //System.out.println(validator.isValid(str));

        URL myUrl = new URL.Composer()
                .isSecure(true)
                .path("ert", "dfgh")
                .composer();
        System.out.println(myUrl.toString());


        //InetAddress ip = new InetAddress("47686464848");
    }
}
