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

    public static long rotate (long n) {
        StringBuilder input = new StringBuilder();
        input.append(n);
        int size = input.length();
        long[] arrList = new long[size];
        arrList[0] = n;
        for (int i = 1, count = 0; i < size; i++, count++) {

            for (int j = 0, change = size - 1; j < size && change > 0; j++) {
                if (j == count) {
                    arrList[i] += (input.charAt(j) - 48) * Math.pow(10, 0);
                } else {
                    arrList[i] += (input.charAt(j) - 48) * Math.pow(10, change);
                    change--;
                }

            }

            input.delete(0, input.length());
            input.append(arrList[i]);

        }
        Arrays.sort(arrList);
        return arrList[size-1];
    }
}
