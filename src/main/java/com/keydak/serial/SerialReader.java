package com.keydak.serial;

import java.io.BufferedReader;
import java.io.IOException;

public class SerialReader implements Runnable {
    BufferedReader in;

    public SerialReader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
