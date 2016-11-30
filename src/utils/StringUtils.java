package utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by jimmyhsu on 2016/11/30.
 */
public class StringUtils {
    public static String getUtf8String(String src) {
        try {
            return new String(src.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
