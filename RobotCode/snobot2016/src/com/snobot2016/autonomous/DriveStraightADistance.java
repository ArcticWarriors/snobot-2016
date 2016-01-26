package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightADistance extends Command
{
    private IDriveTrain mDriveTrain;
    private IPositioner mPositioner;
    private double mDistance;
    private double mSpeed;
    private double mStartDistance;

    public DriveStraightADistance(IDriveTrain aDriveTrain, IPositioner aPositioner, double aDistance, double aSpeed)
    {
        mDriveTrain = aDriveTrain;
        mPositioner = aPositioner;
        mDistance = aDistance;
        mSpeed = aSpeed;
    }

    @Override
    protected void initialize()
    {
        mStartDistance = mPositioner.getTotalDistance();
    }

    @Override
    protected void execute()
    {
        mDriveTrain.setLeftRightSpeed(mSpeed, mSpeed);

    }

    @Override
    protected boolean isFinished()
    {
        return (mStartDistance + mDistance) <= mPositioner.getTotalDistance();
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

}
