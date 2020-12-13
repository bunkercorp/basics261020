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
        if (phrase == null) {
            return null;
        } else if (phrase.equals("")) { // ?? if(phrase == null || phrase.isEmpty())return null;
            return null;
        }
        char[] phraseArray = phrase.toCharArray();
        phraseArray[0] = Character.toUpperCase(phraseArray[0]);
        for (int i = 0; i < phraseArray.length; i++) {
            // а как же дефис - подчерк - апостроф ?
            if (Character.isSpaceChar(phraseArray[i]) || !Character.isLetterOrDigit(phraseArray[i])) {
                phraseArray[i + 1] = Character.toUpperCase(phraseArray[i + 1]);
            }
        }
        StringBuilder backToString = new StringBuilder();
        for (char c : phraseArray) {
            backToString.append(c);
        }
        return backToString.toString();
    }
}
