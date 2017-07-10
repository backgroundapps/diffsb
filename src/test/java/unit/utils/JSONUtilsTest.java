package unit.utils;

import diff.utils.JSONUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class JSONUtilsTest {
    @Test
    public void shouldReturnNull() throws UnsupportedEncodingException {
        assertEquals(null, JSONUtils.encode(null));
        assertEquals(null, JSONUtils.decode(null));
    }

    @Test
    public void shouldEncodeBase64UTF8() throws UnsupportedEncodingException {
        assertEquals("e30=", JSONUtils.encode("{}"));
    }

    @Test
    public void shouldEncodeABase64UTF8() throws UnsupportedEncodingException {
        assertEquals("eyJhIjoiYSJ9", JSONUtils.encode("{\"a\":\"a\"}"));
    }

    @Test
    public void shouldDencodeBase64UTF8(){
        assertEquals("{}", JSONUtils.decode("e30="));
    }

    @Test
    public void shouldDencodeABase64UTF8(){
        assertEquals("{\"a\":\"a\"}", JSONUtils.decode("eyJhIjoiYSJ9"));
    }

    @Test
    public void isEqualReturnNull(){
        assertFalse(JSONUtils.compare(null, null));
        assertFalse(JSONUtils.compare(null, ""));
        assertFalse(JSONUtils.compare("", null));
    }

    @Test
    public void isEqual(){
        assertTrue(JSONUtils.compare("", ""));
    }

    @Test
    public void isEqualEncoded64(){
        assertTrue(JSONUtils.compare("eyJhIjoiYSJ9", "eyJhIjoiYSJ9"));
    }

    @Test
    public void isNotEqual(){
        assertFalse(JSONUtils.compare("", "1"));
    }

    @Test
    public void isNotEqualEncoded64(){
        assertFalse(JSONUtils.compare("", "eyJhIjoiYSJ9"));
    }

    @Test
    public void isSameSize(){
        assertTrue(JSONUtils.isSameSize("eyJhIjoiYSJ9", "eyJhIjoiYSJ9"));
    }

    @Test
    public void isSameSizeDifferentFiles(){
        assertTrue(JSONUtils.isSameSize("eyJhIjoiYSJ9", "eyJhIjoiYiJ9"));
    }


    @Test
    public void isNotSameSize(){
        assertFalse(JSONUtils.isSameSize("eyJhIjoiYSJ9", "eyJhIjoiYWIifQ=="));
    }

}
