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
        StringBuilder res = new StringBuilder();

        for( int i = 0; i < song.length(); i++){
            // song.length() не зависит от i и условие по ИЛИ, делать этому вычислению в цикле нечего
            if (!Character.isUpperCase(song.charAt(i)) || song.length() > 200)
                return null;
             // а смысл этого continue? можно просто if(!все то что в условии){работы;}
            if(song.charAt(i) == 'W' && song.charAt(i+=1) == 'U' && song.charAt(i+=1) == 'B')
                continue;
            else{
                res.append(song.charAt(i));
                if(song.indexOf("WUB", i) == i+1
                        && song.indexOf("WUB", (song.length() - 4)) != i+1){
                    res.append(' ');
                }
            }
        }
        return res.toString();
    }

//    public static void main(String[] args){
//        System.out.println(songDecoder("WUBWUBABCWUB"));
//    }
}
