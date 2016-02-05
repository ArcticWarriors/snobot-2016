package com.snobot2016.drivetrain;

import com.snobot.xlib.ISubsystem;

public interface IDriveTrain extends ISubsystem
{
    void setLeftRightSpeed(double left, double right);

    double getRightEncoderDistance();

    double getLeftEncoderDistance();
}
