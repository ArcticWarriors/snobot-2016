package com.snobot.simulator.module_wrapper;

public class EncoderWrapper extends ASensorWrapper
{
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
}