package com.snobot.simulator.module_wrapper;

public class DigitalSourceWrapper extends ASensorWrapper
{

    private boolean mState;

    public DigitalSourceWrapper(int index)
    {
        super("Digital Source" + index);
        mState = false;
    }

    public boolean get()
    {
        return mState;
    }

    public void set(boolean aState)
    {
        mState = aState;
    }
}
