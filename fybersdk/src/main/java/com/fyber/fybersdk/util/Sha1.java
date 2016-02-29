package com.fyber.fybersdk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class produces Sha1 hashes based on a given String
 * @author Francesco Rigoni
 *
 */
public class Sha1 {


    private final static String HEX = "0123456789ABCDEF";

    /**
     * Returns the SHA1 hash for the provided String
     *
     * @param text
     * @return the SHA1 hash or null if an error occurs
     */
    public static String getHash(String text) {

        try {

            MessageDigest md;
            md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes(),
                    0, text.length());
            byte[] sha1hash = md.digest();

            return toHex(sha1hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static String toHex(byte[] buf) {

        if (buf == null) return "";

        int l = buf.length;
        StringBuffer result = new StringBuffer(2 * l);

        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }

        return result.toString();

    }

    private static void appendHex(StringBuffer sb, byte b) {

        sb.append(HEX.charAt((b >> 4) & 0x0f))
                .append(HEX.charAt(b & 0x0f));

    }


}

