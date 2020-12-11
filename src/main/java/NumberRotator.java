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


import java.util.Arrays;

public class NumberRotator {

    public static long rotate(long n) {
        long nOriginal = n;
        if (n < 0)
            n = n * (-1);
        int length = String.valueOf(n).length();

        String numberInString = String.valueOf(n);

        char[] numberInChar = numberInString.toCharArray();

        long[] arrayResult = new long[length];
        arrayResult[length - 1] = nOriginal;

        for (int j = 0; j < length - 1; j++) {
            char movingChar = numberInChar[j];

            for (int i = j; i < length - 1; i++)
                numberInChar[i] = numberInChar[i + 1];

            numberInChar[length - 1] = movingChar;
            String numberNextinString = String.valueOf(numberInChar);

            int numberNext = Integer.parseInt(numberNextinString);
            arrayResult[j] = numberNext;
        }

        if (nOriginal < 0)
            for (int i = 0; i < length - 1; i++) {
                arrayResult[i] = arrayResult[i] * (-1);
            }

        long a = arrayResult[0];
        for (long c : arrayResult) {
            a = Math.max(a, c);
        }

        //    System.out.println(a);
        return a;
    }
}
