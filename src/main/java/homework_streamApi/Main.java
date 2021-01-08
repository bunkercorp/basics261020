package homework_streamApi;

public class Main {
    public static void main(String[] args) {

        Beautify a = new Beautify ();
        a.beautify("5#eWE", Beautify.Type.CAPITALIZE);
        System.out.println(a);

        CharEntries b = new CharEntries();
        b.charEnries("54er#r# 54");
        System.out.println(b);

        CountWords c = new CountWords();
        c.numberOfWords ("6t^r####e");
        System.out.println(c);

        WordLengths d = new WordLengths();
        d.wordLengths("#f#dd#34");
        System.out.println(d);

    }
}
