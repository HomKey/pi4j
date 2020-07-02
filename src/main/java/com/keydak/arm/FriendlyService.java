package com.keydak.arm;

import com.FriendlyThings.HardwareControler;

public class FriendlyService {
    private int devfd = -1;
    private final int MAXLINES = 200;
    private StringBuilder remoteData = new StringBuilder(256 * MAXLINES);
    private final int BUFSIZE = 512;
    private byte[] buf = new byte[BUFSIZE];
    public void connect(String devName){
        System.out.println(devName);
        devfd = HardwareControler.openSerialPort(devName, 9600, 8, 1); // "/dev/ttySAC0"
        System.out.println("devfd:" + devfd);
        while (true){
            HardwareControler.write(devfd, new byte[]{0x31,0x32});
            if (HardwareControler.select(devfd, 0, 0) == 1){
                int retSize = HardwareControler.read(devfd, buf, BUFSIZE);
                if (retSize > 0) {
                    String str = new String(buf, 0, retSize);
                    remoteData.append(str);
                    System.out.println(remoteData.toString());
                }
                break;
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (devfd >0 ){
            HardwareControler.close(devfd);
        }
    }
}
