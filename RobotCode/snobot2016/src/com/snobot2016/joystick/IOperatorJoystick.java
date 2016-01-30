package com.snobot2016.joystick;

import com.snobot.xlib.ISubsystem;

public interface IOperatorJoystick extends ISubsystem

{
    double getScaleTiltSpeed();
    double getScaleMoveSpeed();       
}
