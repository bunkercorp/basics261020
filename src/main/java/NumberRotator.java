/*

Take a number: 56789. Rotate left, you get 67895.
Keep the first digit in place and rotate left the other digits: 68957.
Keep the first two digits in place and rotate the other ones: 68579.
Keep the first three digits and rotate left the rest: 68597. Now it is over since keeping the first four it remains only one digit which rotated is itself.

You have the following sequence of numbers:
56789 -> 67895 -> 68957 -> 68579 -> 68597
and you must return the greatest: 68957.

Calling this function max_rot (or maxRot or ... depending on the language)
max_rot(56789) should return 68957

*/


import javax.sound.midi.Soundbank;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class NumberRotator {

    public static long rotate(long n) {
        int multiplier = n < 0 ? -1 : 1;
        String s = "" + n * multiplier;
        int length = s.length();

        // незачем хранить всю историю преобразований, тебе только последнее всегда нужно
        long[] temp = new long[s.length()];
        temp[0] = n;
        // абсолютно все входные значения заслуживают внимания?
        for (int i = 0; i < length - 1; i++) {
            String s1 = s.substring(0, i);
            String s2 = s.substring(i + 1);
            s = s1 + s2 + s.charAt(i);
            temp[i + 1] = multiplier * Long.parseLong(s);
        }
        long max = n;
        for (long i : temp) {
            if (max < i) max = i;
        }
        return max;
    }
}
