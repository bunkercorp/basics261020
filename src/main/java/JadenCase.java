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

        if (phrase == null || phrase.equals(""))
            return null;

        StringBuilder wordsNew = new StringBuilder(phrase);

        if (Character.isAlphabetic(phrase.codePointAt(0)))
            wordsNew.setCharAt(0, Character.toUpperCase(phrase.charAt(0)));

        for (int i = 1; i < phrase.length(); i++) {
            final boolean symb1 = phrase.charAt(i - 1) >= '!' && phrase.charAt(i - 1) <= ',';
            final boolean symb2 = phrase.charAt(i - 1) >= '.' && phrase.charAt(i - 1) <= '/';
            final boolean symb3 = phrase.charAt(i - 1) >= ':' && phrase.charAt(i - 1) <= '@';
            final boolean symb4 = phrase.charAt(i - 1) >= '[' && phrase.charAt(i - 1) <= '^';
            final boolean symb5 = phrase.charAt(i - 1) >= '{' && phrase.charAt(i - 1) <= '~';

            if (Character.isAlphabetic(phrase.charAt(i)) && (Character.isWhitespace(phrase.charAt(i - 1))
                    || symb1 || symb2 || symb3 || symb4 || symb5))
                wordsNew.setCharAt(i, Character.toUpperCase(phrase.charAt(i)));
        }
        return wordsNew.toString();


    }
}
