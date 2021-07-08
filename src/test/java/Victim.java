import org.testng.annotations.Test;

public class Victim {

    @Test
    public static void test() {
        final String lorem = System.getProperty("lorem");
        final int ipsum = Integer.parseInt(System.getProperty("ipsum"));
        System.out.printf("lorem='%s' ipsum=%d\n", lorem, ipsum);
    }

}
