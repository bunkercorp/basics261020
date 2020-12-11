/*
Jaden Smith, the son of Will Smith, is the star of films such as The Karate Kid (2010) and After Earth (2013).
Jaden is also known for some of his philosophy that he delivers via Twitter.
When writing on Twitter, he is known for almost always capitalizing every word.

Your task is to convert strings to how they would be written by Jaden Smith.
The strings are actual quotes from Jaden Smith, but they are not capitalized in the same way he originally typed them.

Example:

Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"

Note that the Java version expects a return value of null for an empty string or null.

*/

public class JadenCase {
    public static String toJadenCase(String phrase) {
        if (phrase == null ||
                phrase.isEmpty() || phrase.equals(" ")) return null;
        char[] array = phrase.toLowerCase().toCharArray();
        array[0] = Character.toUpperCase(phrase.charAt(0));
        int counter = 0;
        for (int i = 0; i < phrase.length(); i++) {
            final boolean words = (phrase.charAt(i) == 39 ||
                    phrase.charAt(i) == 45 ||
                    phrase.charAt(i) == 95 ||
                    (phrase.charAt(i) > 47 && phrase.charAt(i) < 58) ||
                    (phrase.charAt(i) > 64 && phrase.charAt(i) < 91) ||
                    phrase.charAt(i) > 96 && phrase.charAt(i) < 123);
            if (!words) counter++;
            else if (words && counter > 0) {
                array[i] = Character.toUpperCase(phrase.charAt(i));
                counter = 0;
            }
        }
        return String.valueOf(array);
    }
}