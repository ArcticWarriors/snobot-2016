package com.snobot.simulator.module_wrapper;


public class EncoderWrapper extends ASensorWrapper
{
    private SpeedControllerWrapper mMotorWrapper;

    public EncoderWrapper(int aIndexA, int aIndexB)
    {
        super("Encoder (" + aIndexA + ", " + aIndexB + ")");
    }

    public void reset()
    {

    }

    public int getRaw()
    {
        return 0;
    }

    public boolean isHookedUp()
    {
        return mMotorWrapper != null;
    }

    public void setSpeedController(SpeedControllerWrapper aMotorWrapper)
    {
        mMotorWrapper = aMotorWrapper;
    }
}