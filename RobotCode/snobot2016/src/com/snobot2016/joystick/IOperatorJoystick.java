package com.snobot2016.joystick;

import com.snobot.xlib.ISubsystem;

public interface IOperatorJoystick extends ISubsystem

{
   // Scaling
    double getScaleTiltSpeed();
    double getScaleMoveSpeed();
    
    boolean getFinalCountDown();

    //Harvester
    boolean getHarvesterPivotSpeedUp();
    boolean getHarvesterPivotSpeedDown();
    
    boolean getHarvesterRollerSpeedForward();
    boolean getHarvesterRollerSpeedReverse();
}
