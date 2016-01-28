package com.snobot2016.light;

import com.snobot.xlib.ISubsystem;

public interface ILight extends ISubsystem
{
    void setLight(boolean aOn);

    boolean getLight();
}
