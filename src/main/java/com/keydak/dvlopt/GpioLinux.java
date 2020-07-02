package com.keydak.dvlopt;

import io.dvlopt.linux.gpio.GpioChipInfo;
import io.dvlopt.linux.gpio.GpioDevice;
import io.dvlopt.linux.gpio.GpioEventRequest;

import java.io.IOException;
import java.io.InputStream;

public class GpioLinux {

    private GpioDevice instance;

    public void getInfo(int number){
        try{
            instance = new GpioDevice(number);
            GpioChipInfo gpioChipInfo = instance.requestChipInfo();
            String label = gpioChipInfo.getLabel();
            int lines = gpioChipInfo.getLines();
            String name = gpioChipInfo.getName();
            System.out.println("label:" +label);
            System.out.println("lines:" +lines);
            System.out.println("name:" +name);
//            instance.requestEvent(new GpioEventRequest())
        }catch (IOException io){
            io.printStackTrace();
            System.out.println(io.getMessage());
        }finally {
            this.close();
        }
    }

    public void close(){
        try {
            if (null != instance){
                instance.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
