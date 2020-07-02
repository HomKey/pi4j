package com.keydak;

import com.keydak.arm.FriendlyService;
import com.keydak.dio.DioService;
import com.keydak.dvlopt.GpioLinux;
import com.keydak.file.FileMonitor;
import com.keydak.file.FileMonitorJDK;
import com.keydak.gpio.GpioListener;
import com.keydak.gpio.GpioService;
import com.keydak.serial.SerialService;

import java.util.Scanner;

public class ApplicationMain {

    public static void main(String[] args) throws Exception {
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyUSB0:/dev/ttySAC2");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
//        FileMonitor.test();
//        new GpioListener().run();
//        new DioService().listener();
//        FileMonitorJDK.test();
//        GpioService.Control();
//        GpioService.Listen();
//        new GpioLinux().getInfo(Integer.parseInt(number));
        try {
            SerialService.listPorts();  //显示当前可用的串口号
            new SerialService().connect(number);
        } catch (Exception e)  {
            e.printStackTrace();
        }
//        new FriendlyService().connect(number);
    }
}
