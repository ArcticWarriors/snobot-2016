package com.snobot2016.autonomous;

import com.snobot.xlib.motion_profile.simple.ISetpointIterator;
import com.snobot.xlib.motion_profile.simple.PathFollower;
import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightProfile extends Command
{
    private IDriveTrain mDriveTrain;
    private PathFollower mPathFollower;

    public DriveStraightProfile(IDriveTrain aDriveTrain, ISetpointIterator aSetpointIterator, double aKV, double aKA, double aKP, double aDistance,
            double aMaxVelocity, double aMaxAccel, double aDt)
    {
        mDriveTrain = aDriveTrain;
        mPathFollower = new PathFollower(aSetpointIterator, aKV, aKA, aKP);
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        double motorSpeed = mPathFollower.calcMotorSpeed();
        mDriveTrain.setLeftRightSpeed(motorSpeed, motorSpeed);
    }

    @Override
    protected boolean isFinished()
    {
        return mPathFollower.isFinished();
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    @Override
    protected void interrupted()
    {

    }
}
