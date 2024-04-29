package com.practice.urlshortener.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashingUtils {
    public static byte[] getMD5ValueOf(String content) throws NoSuchAlgorithmException {
            return MessageDigest.getInstance("MD5").digest(content.getBytes());
    }

    public static String getBase64EncodedValueOf(byte[] value) {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.encodeToString(value);
    }
}
