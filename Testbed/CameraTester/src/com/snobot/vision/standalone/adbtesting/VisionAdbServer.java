package com.snobot.vision.standalone.adbtesting;

import java.nio.ByteBuffer;
import java.util.logging.Level;

public class VisionAdbServer extends RobotConnectionServer
{
    private static final String sRESTART_APP_COMMAND = 
            "shell am force-stop snobit.com.cameratester \\; " + 
            "am start snobit.com.cameratester/snobit.com.cameratester.SelfieCameraActivity";

    public VisionAdbServer(int bindPort)
    {
        super(bindPort);

        AdbBridge adb = new AdbBridge(sRESTART_APP_COMMAND);
        adb.start();
        adb.reversePortForward(bindPort, bindPort);
        // adb.reversePortForward(5800, 5800);
    }

    @Override
    public void handleMessage(String message, double timestamp)
    {
        if ("heartbeat".equals(message))
        {
            String outMessage = "heartbeat";
            ByteBuffer buffer = ByteBuffer.wrap(outMessage.getBytes());
            send(buffer);
        }

        sLOGGER.log(Level.INFO, message);
    }

    public double getTimestamp()
    {
        return System.currentTimeMillis();
    }

}
