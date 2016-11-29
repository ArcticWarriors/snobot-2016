package snobot.com.visionapp;

import android.util.Log;

import java.nio.ByteBuffer;

import snobot.com.visionapp.utils.RobotConnection;

/**
 * Created by PJ on 11/24/2016.
 */

public class VisionRobotConnection extends RobotConnection {

    private static final String sTAG = "RobotConnection";


    public VisionRobotConnection() {
        super();
    }

    public VisionRobotConnection(String host, int port) {
        super(host, port);
    }

    public void handleMessage(String message) {
        if("heartbeat".equals(message))
        {
            mLastHeartbeatReceiveTime = System.currentTimeMillis();
        }

        Log.i(sTAG, "Parsing messages: " + message);
    }


    @Override
    protected void onRobotConnected() {
        Log.i(sTAG, "Connected");
    }

    @Override
    protected void onRobotDisconnected() {
        Log.i(sTAG, "Disconnected");
    }

    @Override
    protected void sendHeartbeatMessage() {
        String message  = "heartbeat\n";
        send(ByteBuffer.wrap(message.getBytes()));
    }

    public void sendPictureTakenMessage()
    {
        String message = "pictureTaken\n";
        send(ByteBuffer.wrap(message.getBytes()));
    }
}
