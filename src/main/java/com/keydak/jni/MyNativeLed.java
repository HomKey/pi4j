package com.keydak.jni;

public class MyNativeLed {
    static {
        System.loadLibrary("MyNativeLed");
    }
    private native void testLed();

    public static void main(String[] args) {
    }
}
