/*

Description:
We want to generate a function that computes the series starting from 0 and ending until the given number following the sequence:
0 1 3 6 10 15 21 28 36 45 55 ....
which is created by
0, 0+1, 0+1+2, 0+1+2+3, 0+1+2+3+4, 0+1+2+3+4+5, 0+1+2+3+4+5+6, 0+1+2+3+4+5+6+7 etc..

Input: LastNumber
Output: series and result

Example:

Input: 6
Output: '0+1+2+3+4+5+6 = 21'

Input: -15
Output: '-15 < 0'

Input: > 0
Output: '0 = 0'

*/

import java.util.Arrays;

public class SequenceSum {

    public static String showSequence(int value) {
        final boolean equal0 = value == 0;
        final boolean less0 = value < 0;
        int sum = 0;
        String result = null;
        if (equal0)
            return result = "0 = 0";
        if (less0)
            return result = String.format("%s < 0", value);
        else {
            int[] array = new int[value + 1];
            for (int i = 0; i < value; i++) {
                array[value - i] = value - i;
            }

            for (int element : array) {
                sum += element;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(array[0]);
            for (int i = 1; i < array.length; i++) {
                sb.append('+').append(array[i]);
            }
            sb.append(' ').append('=').append(' ').append(sum);
            return sb.toString();
        }
    }

}
