package com.itheima;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test002 {

    public static void main(String[] args) {
        try {
            byte[] str = "a12345678".getBytes();
            for (byte b : str) {
                System.out.print(b+" ");
            }
            System.out.println();
            System.out.println("str:" + new String(str));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update("a12345678".getBytes());
            byte[] digest = md5.digest();
            for (byte b : digest) {
                System.out.print(b+" ");
            }
            System.out.println();
            System.out.println("digest:" + new String(digest));
            System.out.println(Hex.encodeHex(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
