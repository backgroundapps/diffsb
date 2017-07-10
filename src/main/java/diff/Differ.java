package diff;

import diff.utils.FileUtils;
import diff.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Responsible to compare based on the informed ID
 */
public class Differ {
    private String id;

    public Differ(String id) {
        this.id = id;
    }

    public String invoke() throws IOException {
        String out;

        String leftJsonName = id + "_left.json";
        File left_json = new File(leftJsonName);

        String rightJsonName = id + "_right.json";
        File right_json = new File(rightJsonName);

        if(left_json.exists() && right_json.exists()){
            String leftLine = new String(Files.readAllBytes(Paths.get(leftJsonName)), "UTF-8");
            String rightLine = new String(Files.readAllBytes(Paths.get(rightJsonName)), "UTF-8");
            out = StringUtils.diffString(leftLine, rightLine);

            //Removing file after comparing
            FileUtils.deleteComparedFiles(id);

        } else{
            out = "FILE NOT FOUND";
        }
        return out;
    }
}
