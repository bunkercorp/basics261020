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

    private static String phraseAfterFormating = "";

    public static String toJadenCase(String phrase) {

        // phrase == null || phrase.isEmpty()
        if (phrase == null || phrase == "") {
            return null;
        }
        // StringBuilder?
        phraseAfterFormating = phraseAfterFormating + phrase.substring(0, 1).toUpperCase();
        for (int i = 1; i < phrase.length(); i++) {
            if (" ".equals(phrase.substring(i - 1, i)))
                phraseAfterFormating = phraseAfterFormating + phrase.substring(i, i + 1).toUpperCase();
            else
                phraseAfterFormating = phraseAfterFormating + phrase.substring(i, i + 1); // phrase.substring(i, i + 1) это не более чем phrase.charAt(i)
        }
        return phraseAfterFormating;
    }
}
