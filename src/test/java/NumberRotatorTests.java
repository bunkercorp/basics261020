import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberRotatorTests {
    private static void testing(long actual, long expected) {
        assertEquals(expected, actual);
    }
    @Test
    public void test() {
        testing(NumberRotator.rotate(38458215), 85821534);
        testing(NumberRotator.rotate(195881031), 988103115);
        testing(NumberRotator.rotate(896219342), 962193428);
        testing(NumberRotator.rotate(69418307), 94183076);
        testing(NumberRotator.rotate(0), 0);
        testing(NumberRotator.rotate(-4132), -1234);
        testing(NumberRotator.rotate(1), 1);
        testing(NumberRotator.rotate(-10085), -851);
    }
}
