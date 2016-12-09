package utils;

import java.util.Random;

/**
 * Created by jimmyhsu on 2016/12/9.
 */
public class QrCodeUtil {
    public static String createPassword(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$^*()_[]{}<>~`=,.;:?|";
        StringBuffer sb = new StringBuffer();
        int maxLength = chars.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(maxLength - 1)));
        }
        return sb.toString();
    }
}
