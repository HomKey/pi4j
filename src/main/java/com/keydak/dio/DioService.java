package com.keydak.dio;

import jdk.dio.DeviceConfig;
import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;
import jdk.dio.gpio.GPIOPinConfig;
import jdk.dio.gpio.PinEvent;
import jdk.dio.gpio.PinListener;

import java.io.IOException;

public class DioService {

    public void listener(){
        try {
            GPIOPin open2 =DeviceManager.open(GPIOPin.class, new GPIOPinConfig(DeviceConfig.DEFAULT,9,GPIOPinConfig.DIR_INPUT_ONLY,GPIOPinConfig.MODE_INPUT_PULL_UP,GPIOPinConfig.TRIGGER_BOTH_EDGES,false));
            open2.setInputListener(event -> {
                System.out.println("=======");
                System.out.println("value:" + event.getValue());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
