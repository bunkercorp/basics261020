/*

Polycarpus works as a DJ in the best Berland nightclub, and he often uses dubstep music in his performance.
Recently, he has decided to take a couple of old songs and make dubstep remixes from them.

Let's assume that a song consists of some number of words. To make the dubstep remix of this song,
Polycarpus inserts a certain number of words "WUB" before the first word of the song (the number may be zero),
after the last word (the number may be zero), and between words (at least one between any pair of neighbouring words),
and then the boy glues together all the words, including "WUB", in one string and plays the song at the club.

For example, a song with words "I AM X" can transform into a dubstep remix as "WUBIWUBAMWUBWUBX"
and cannot transform into "WUBWUBIAMWUBX".

Recently, Jonny has heard Polycarpus's new dubstep track, but since he isn't into modern music,
he decided to find out what was the initial song that Polycarpus remixed. Help Jonny restore the original song.

Input
The input consists of a single non-empty string, consisting only of uppercase English letters, the string's length
doesn't exceed 200 characters

Output
Return the words of the initial song that Polycarpus used to make a dubsteb remix. Separate the words with a space.


*/

public class Dubster {

    public static String songDecoder(String song) {
        final boolean isNotEmpty = song != null && !song.isEmpty();
        if (!isNotEmpty)
            return null;

        char[] songChars = song.toCharArray();

        if (songChars.length >= 200)
            return null;

        for (char songChar : songChars) {
            final boolean isAlpha = Character.isAlphabetic(songChar);
           //Character.isUppercase
            final boolean isUppercase = songChar >= 'A' && songChar <= 'Z';
            if (!isAlpha || !isUppercase)
                return null;
        }

        StringBuilder sb = new StringBuilder();
        int i, j;
        for (i = 0; i < song.length() - 2; i++) {
            if (song.substring(i, i + 3).equals("WUB")) {
                sb.append(" ");
                i += 2;
            } else {
                sb.append(song.substring(i, i + 1));
            }

            //есть более щадящий способ это сделать, кроме как циклом в цикле удалять из накопителя. Можно просто туда не писать.
            for (j = 1; j < sb.length(); j++)
                if ((sb.substring(j - 1, j).equals(" ")) && (sb.substring(j, j + 1).equals(" "))) {
                    sb.delete(j - 1, j);
                }
        }

        sb.append(song.substring(i));

        if (sb.substring(0, 1).equals(" ")) sb.delete(0, 1);

        if ((sb.length() > 2) && (sb.substring(sb.length() - 1, sb.length()).equals(" ")))
            sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

}
