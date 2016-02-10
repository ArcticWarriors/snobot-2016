package com.snobot2016.autonomous.path;

import com.snobot.xlib.motion_profile.simple.ISetpointIterator;
import com.snobot2016.Properties2016;
import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightPath extends Command
{
    private IDriveTrain mDriveTrain;
    private PathFollower mPathFollower;

    public DriveStraightPath(IDriveTrain aDriveTrain, ISetpointIterator aSetpointIterator)
    {
        mDriveTrain = aDriveTrain;

        double kP = Properties2016.sDRIVE_PATH_KP.getValue();
        // double kD = Properties2016.sDRIVE_PATH_KD.getValue();
        double kVelocity = Properties2016.sDRIVE_PATH_KV.getValue();
        double kAccel = Properties2016.sDRIVE_PATH_KA.getValue();

        mPathFollower = new PathFollower(aSetpointIterator, kVelocity, kAccel, kP);
    }

    @Override
    protected void initialize()
    {
        mPathFollower.init();
    }

    @Override
    protected void execute()
    {
        double currentPosition = 0; // TODO get
        double motorSpeed = mPathFollower.calcMotorSpeed(currentPosition);
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
