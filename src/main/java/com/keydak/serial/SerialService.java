package com.keydak.serial;

import gnu.io.CommDriver;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

public class SerialService {
    public void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader r = new BufferedReader(reader);

                (new Thread(new SerialReader(r))).start();

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public static void listPorts() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {//列出可以用的串口
        System.out.println("列出可以用的串口：");
        Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        System.out.println("===========1");
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            System.out.println(portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType()));
        }
        System.out.println("===========2");

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("gnu.io.rxtx.SerialPorts"));
        System.out.println(System.getProperty("gnu.io.SerialPorts"));
        System.out.println(System.getProperty("gnu.io.rxtx.ParallelPorts"));
        System.out.println(System.getProperty("gnu.io.ParallelPorts"));

        CommDriver var3 = (CommDriver)Class.forName("gnu.io.RXTXCommDriver").newInstance();
        var3.initialize();
        System.out.println("++++");
    }

    private static String getPortTypeName(int portType) {
        switch (portType) {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }
}
