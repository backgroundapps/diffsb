package unit.utils;

import diff.utils.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    // TEST [diffString]
    @Test
    public void shouldShowOffsetAddedInfo(){
        assertEquals("ADDED: b", StringUtils.diffString("a", "ab"));
    }

    @Test
    public void shouldBeTHeSame(){
        assertEquals("EQUALS", StringUtils.diffString("a", "a"));
    }

    @Test
    public void shouldReturnNullForSecondNull(){
        assertEquals(null, StringUtils.diffString("a", null));
    }

    @Test
    public void shouldReturnNullForBothNull(){
        assertEquals(null, StringUtils.diffString(null, null));
    }

    @Test
    public void shouldShowOffsetAddedInfoACB(){
        assertEquals("ADDED: bc", StringUtils.diffString("a", "abc"));
    }

    @Test
    public void shouldShowOffsetAddedInfoJSON_BC(){
        assertEquals("ADDED: name: b, name: c}", StringUtils.diffString("{name: a}", "{name: a, name: b, name: c}"));
    }

    @Test
    public void shouldShowOffsetAddedQuotedInfoJSON_BC(){
        assertEquals("ADDED: \"name\": \"b\", \"name\": \"c\"}", StringUtils.diffString("{\"name\": \"a\"}", "{\"name\": \"a\", \"name\": \"b\", \"name\": \"c\"}"));
    }

    @Test
    public void shouldShowOffsetRemovedInfo(){
        assertEquals("REMOVED: b", StringUtils.diffString("ab", "a"));
    }

    @Test
    public void shouldShowOffsetRemovedInfoABC(){
        assertEquals("REMOVED: bc", StringUtils.diffString("abc", "a"));
    }

    @Test
    public void shouldShowOffsetRemovedInfoJSON_B(){
        assertEquals("REMOVED: name b}", StringUtils.diffString("{name a, name b}", "{name: a}"));
    }

    @Test
    public void shouldShowOffsetRemovedInfoJSON_BC(){
        assertEquals("REMOVED: name b, name: c}", StringUtils.diffString("{name a, name b, name: c}", "{name: a}"));
    }

    @Test
    public void shouldShowOffsetRemovedQuotedInfoJSON_BC(){
        assertEquals("REMOVED: \"name\": \"b\", \"name\": \"c\"}", StringUtils.diffString("{\"name\": \"a\", \"name\": \"b\", \"name\": \"c\"}", "{\"name\": \"a\"}"));
    }

    // TEST [isFirstStringBigger]
    @Test
    public void shouldValidateFirstAsBiggerString(){
        assertTrue(StringUtils.isLeftStringBigger("ab", "a"));
    }
}
