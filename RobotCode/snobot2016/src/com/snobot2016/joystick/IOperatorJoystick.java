package com.snobot2016.joystick;

import com.snobot.xlib.ISubsystem;

public interface IOperatorJoystick extends ISubsystem

{
    // Scaling
    double getScaleTiltSpeed();
    double getScaleMoveSpeed();
    boolean isFinalCountDown();

    // Harvester
//    boolean isHarvesterPivotUp();
//    boolean isHarvesterPivotDown();
    boolean isHarvesterRollerForward();
    boolean isHarvesterRollerReverse();
    boolean isHarvesterUp();
    boolean isHarvesterDown();
}

