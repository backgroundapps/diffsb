package diff.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    /**
     * Create a new file.
     *
     * @param name normal string name
     * @param extension extension with .(dot) included
     */
    public static void createFile(String name, String content, String extension) throws IOException {
        FileWriter fw = new FileWriter(name + extension);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(content);
        out.close();

    }

    public static void deleteComparedFiles(String id) {
        new File(id + "_left.json").delete();
        new File(id + "_right.json").delete();
    }
}
