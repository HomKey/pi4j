package com.keydak.gpio;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.util.Scanner;

public class GpioListener {
    private static final GpioController CONTROLLER = GpioFactory.getInstance();
//    private static final GpioPinDigitalInput BUTTON_09 = CONTROLLER.provisionDigitalInputPin(RaspiPin.GPIO_03);
    private static final GpioPinDigitalInput BUTTON_02 = CONTROLLER.provisionDigitalInputPin(RaspiPin.GPIO_02);
    private static final GpioPinDigitalOutput LED = CONTROLLER.provisionDigitalOutputPin(RaspiPin.GPIO_06);


    public void run() {
        BUTTON_02.setShutdownOptions(true);
        BUTTON_02.addListener((GpioPinListenerDigital) event -> {
            boolean state = event.getState().isHigh();
            System.out.println("2--->pin:" + event.getPin() + "|state:" + state);
        });
//        BUTTON_09.setShutdownOptions(true);
//        BUTTON_09.addListener((GpioPinListenerDigital) event -> {
//            boolean state = event.getState().isHigh();
//            System.out.println("9--->pin:" + event.getPin() + "|state:" + state);
//        });
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
