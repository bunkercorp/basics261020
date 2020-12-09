import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DubsterSongDecoderTests {
    @Test
    public void test1() {
        assertEquals("ABC", Dubster.songDecoder("WUBWUBABCWUB"));
    }

    @Test
    public void test2() {
        assertEquals("R L", Dubster.songDecoder("RWUBWUBWUBLWUB"));
    }

    @Test
    public void test3() {
        assertEquals("", Dubster.songDecoder("WUBWUBWUB"));
    }

    @Test
    public void test4() {
        assertEquals("LOREM IPSUM DOLOR SIT AMET", Dubster.songDecoder("LOREMWUBIPSUMWUBWUBWUBWUBWUBDOLORWUBSITWUBWUBAMETWUB"));
    }
    @Test
    public void test5() {
        assertEquals("ghgugWUBWUBWgtUB", Dubster.songDecoder("ghgugWUBWUBWgtUB"));
    }
}
