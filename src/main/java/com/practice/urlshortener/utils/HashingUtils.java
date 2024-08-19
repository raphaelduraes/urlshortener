package com.practice.urlshortener.utils;

import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

public class HashingUtils {
    public static String getSHA256EncodedValueOf(String txt) throws NoSuchAlgorithmException {
        return DigestUtils.sha256Hex(txt);
    }
}
