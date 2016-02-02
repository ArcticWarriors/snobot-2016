package com.snobot2016.joystick;

import com.snobot.xlib.ISubsystem;

public interface IDriverJoystick extends ISubsystem
{
    double getRightSpeed();

    double getLeftSpeed();
}
