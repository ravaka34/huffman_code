package com.encoding.test;

public class TestByte {

    public static void main(String[] args) {
        byte b1 = 0;
        b1 = (byte) (b1 | 1);
        b1 = (byte) (b1 << 8);
        System.out.println(b1);
    }
}
