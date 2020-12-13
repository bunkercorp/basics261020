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


public class NumberRotator {

    public static long rotate (long n) {

        if (n == 0) {
            return 0;
        }

        long max = n;
        String originalNumberStr = Long.toString(n);

        if (n < 0) {
            originalNumberStr = originalNumberStr.substring(1);
        }
        // тебе незачем хранить все предыдущие версии, одной достаточно
        long [] numbers = new long [originalNumberStr.length()];

        for (int i = 0; i < originalNumberStr.length(); i++) {
            if (i < originalNumberStr.length() - 1) {
                originalNumberStr = originalNumberStr.substring(0, i) +
                        originalNumberStr.substring(i + 1) +
                        originalNumberStr.substring(i, i + 1); // charAt

                numbers[i] = n < 0 ? -Long.parseLong(originalNumberStr) : Long.parseLong(originalNumberStr);
            }
        }
        numbers[numbers.length - 1] = n;

        for (int i = 0; i < numbers.length; i++) {
            max = Math.max(numbers[i], max);
        }
        return max;
    }
}
