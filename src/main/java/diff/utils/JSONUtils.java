package diff.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class JSONUtils {
    public static String encode(String json) throws UnsupportedEncodingException {
        if (json == null) return null;

        return Base64.getEncoder().encodeToString(json.getBytes("UTF-8"));
    }

    public static String decode(String json) {
        if (json == null) return null;

        return new String(Base64.getDecoder().decode(json));
    }

    public static boolean compare(String left, String rigth) {
        return !(left == null || rigth == null) && left.equals(rigth);
    }

    public static boolean isSameSize(String left, String rigth) {
        return decode(left).getBytes().length == decode(rigth).getBytes().length;
    }

    public static void buildFromEncodedJSONFile(String name, String encodedJson) throws IOException {
        FileUtils.createFile(name, decode(encodedJson), ".json");
    }



}
