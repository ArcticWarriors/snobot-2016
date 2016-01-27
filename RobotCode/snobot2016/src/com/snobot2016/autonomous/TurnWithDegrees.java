package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.command.Command;

public class TurnWithDegrees extends Command
{
    private IDriveTrain mDriveTrain;
    private IPositioner mPositioner;
    private double mTurnDegrees;
    private double mFinalAngle;
    private double mSpeed;
    boolean finished;

    public TurnWithDegrees(IDriveTrain aDriveTrain, IPositioner aPositioner, double aTurnDegrees, double aSpeed)
    {
        mDriveTrain = aDriveTrain;
        mPositioner = aPositioner;
        mTurnDegrees = aTurnDegrees;
        mSpeed = aSpeed;
    }

    @Override
    protected void initialize()
    {
        mFinalAngle = mTurnDegrees + mPositioner.getOrientationDegrees();
        finished = false;

    }

    @Override
    protected void execute()
    {

        double error = mPositioner.getOrientationDegrees() - mFinalAngle;
        System.out.println("Error: " + error);
        double deaband = 5;
        if (error > deaband)
        {
            System.out.println("  left");
            // turn left
            mDriveTrain.setLeftRightSpeed(-mSpeed, mSpeed);
        }
        else if (error < -deaband)
        {
            System.out.println("  right");
            // turn right
            mDriveTrain.setLeftRightSpeed(mSpeed, -mSpeed);
        }
        else
        {
            System.out.println("three");
            mDriveTrain.stop();
            finished = true;
        }

    }

    @Override
    protected boolean isFinished()
    {
        return finished;

    }

    @Override
    protected void end()
    {
        mDriveTrain.setLeftRightSpeed(0, 0);

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

}
