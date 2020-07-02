package com.keydak.gpio;

import com.diozero.api.PinInfo;
import com.keydak.core.jdkdio10.JdkDeviceIoDeviceFactory;
import com.keydak.core.jdkdio10.JdkDeviceIoGpioOutputDevice;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.ConsoleColor;
import jdk.dio.DeviceConfig;
import jdk.dio.gpio.GPIOPinConfig;

public class GpioService {
    public static void Control() throws InterruptedException {
        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        // turn off gpio pin #01
        pin.low();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        // toggle the current state of gpio pin #01 (should turn on)
        pin.toggle();
        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        // toggle the current state of gpio pin #01  (should turn off)
        pin.toggle();
        System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        // turn on gpio pin #01 for 1 second and then off
        System.out.println("--> GPIO state should be: ON for only 1 second");
        pin.pulse(1000, true); // set second argument to 'true' use a blocking call

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

        System.out.println("Exiting ControlGpioExample");
    }

    public static void JdkDeviceGpioControl(){
//        GPIOPinConfig pin_config = new GPIOPinConfig(.UNASSIGNED).setPinNumber(gpio).
//                setDirection(GPIOPinConfig.DIR_INPUT_ONLY).setDriveMode(GPIOPinConfig.MODE_INPUT_PULL_UP).setTrigger(GPIOPinConfig.TRIGGER_BOTH_EDGES).build();
//        try (GPIOPin pin = DeviceManager.open(GPIOPin.class, pin_config)) {
//            pin.setInputListener(this);
//            System.out.println("Waiting 20s for events..., thread name=" + Thread.currentThread().getName());
//            SleepUtil.sleepSeconds(20);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public static void Listen() throws InterruptedException {
        System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        Pin pin1 = CommandArgumentParser.getPin(
                RaspiPin.class,    // pin provider class to obtain pin instance from
                RaspiPin.GPIO_03  // default pin if no pin argument found
                );             // argument array to search in
        PinPullResistance pull = CommandArgumentParser.getPinPullResistance(
                PinPullResistance.PULL_UP);  // default pin pull resistance if no pull argument found
        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalInput pin = gpio.provisionDigitalInputPin(pin1, pull);

        // set shutdown state for this pin
        pin.setShutdownOptions(true);

        // create and register gpio pin listener
        pin.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " +
                        ConsoleColor.conditional(
                                event.getState().isHigh(), // conditional expression
                                ConsoleColor.GREEN,        // positive conditional color
                                ConsoleColor.RED,          // negative conditional color
                                event.getState()));        // text to display
            }
        });
        System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

        // keep program running until user aborts (CTRL-C)
        Thread.sleep(Integer.MAX_VALUE);

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }
}
