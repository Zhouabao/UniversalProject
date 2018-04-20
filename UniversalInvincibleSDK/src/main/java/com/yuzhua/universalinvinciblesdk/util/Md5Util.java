package com.yuzhua.universalinvinciblesdk.util;

import java.security.MessageDigest;

/**
 * @author chenli
 * @version 1.0
 * @date 2018/3/21
 * @copyRights
 * @since 1.0
 */
public class Md5Util {

    public static String MD5toLower(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5toLower");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toLowerCase().toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
