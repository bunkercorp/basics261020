import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Victim {

    @Test
    public static void test() throws IOException {
        final String payload = System.getProperty("payload");
        BufferedWriter writer = new BufferedWriter(new FileWriter("payload.txt"));
        writer.write(payload);
        writer.close();
    }

}
