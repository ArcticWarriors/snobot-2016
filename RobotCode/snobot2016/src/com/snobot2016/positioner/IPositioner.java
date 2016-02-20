package com.snobot2016.positioner;

import com.snobot.xlib.ISubsystem;

public interface IPositioner extends ISubsystem
{

    public double getXPosition();

    public double getYPosition();

    public double getOrientationDegrees();

    public double getOrientationRadians();

    public double getTotalDistance();

    public void setPosition(double aX, double aY, double aAngle);
}
