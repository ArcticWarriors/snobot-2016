package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class StupidTurn extends Command
{
    private IDriveTrain mDriveTrain;
    private double mSpeed;
    private double mTime;
    private Timer mTimer;

    public StupidTurn(IDriveTrain aDriveTrain, double aSpeed, double aTime)
    {
        mDriveTrain = aDriveTrain;
        mSpeed = aSpeed;
        mTime = aTime;
        mTimer = new Timer();
    }

    @Override
    protected void initialize()
    {
        mTimer.start();
    }

    @Override
    protected void execute()
    {
        mDriveTrain.setLeftRightSpeed(mSpeed, -mSpeed);
    }

    @Override
    protected boolean isFinished()
    {
        if (mTimer.get() >= mTime)
        {
            return true;
        }
        return false;
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
        mTimer.stop();

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

}
