package unit.utils;

import diff.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class FileUtilsTest {
    @Test
    public void shouldCreateFile() throws IOException {
        FileUtils.createFile("f", "", ".json");
        assertTrue(new File("f.json").exists());
        new File("f.json").delete();
    }

    @Test
    public void shouldRemoveFile() throws UnsupportedEncodingException {
        FileUtils.deleteComparedFiles("f.json");
        assertFalse(new File("f.json").exists());
    }
}
