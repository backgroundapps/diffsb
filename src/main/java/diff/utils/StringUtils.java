package diff.utils;

public class StringUtils {
    private enum STATUS {ADDED, REMOVED, EQUALS}

    public static String diffString(String left, String rigth) {
        if (left == null || rigth == null) return null;

        if(left.equals(rigth)){
            return STATUS.EQUALS.toString();

        } else if (isLeftStringBigger(left, rigth)) {
            return STATUS.REMOVED + ": " + left.substring(rigth.length()).trim();

        } else {
            return STATUS.ADDED + ": " + rigth.substring(left.length()).trim();
        }

    }

    public static boolean isLeftStringBigger(String left, String right) {
        return left.length() > right.length();
    }
}
