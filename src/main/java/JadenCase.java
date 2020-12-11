import java.util.Arrays;

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

        final boolean isNotEmpty = phrase != null && !phrase.isEmpty();
        if (!isNotEmpty)
            return null;

        char[] phraseToChars = phrase.toCharArray();

        phraseToChars[0] = Character.toUpperCase(phraseToChars[0]);
        for (int i = 0; i < phrase.length(); i++) {
            final boolean isAlpha = Character.isAlphabetic(phraseToChars[i]);
            final boolean isNumeric = Character.isDigit(phraseToChars[i]);
            final boolean isUnderscore = phraseToChars[i] == '_';
            final boolean isHyphen = phraseToChars[i] == '-';
            final boolean isApostrophe = phraseToChars[i] == '\'';
            final boolean charIsNOTaWord = !isAlpha && !isNumeric && !isUnderscore && !isHyphen && !isApostrophe;
            if (charIsNOTaWord) {
                phraseToChars[i + 1] = Character.toUpperCase(phraseToChars[i + 1]);
            }
        }

        return String.valueOf(phraseToChars);
    }
}
