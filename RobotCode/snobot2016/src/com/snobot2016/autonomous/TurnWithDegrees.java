package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.command.Command;

public class TurnWithDegrees extends Command
{
    private final IDriveTrain mDriveTrain;
    private final IPositioner mPositioner;
    private final double mAngleToTurn;
    private final double mSpeed;
    private final double mDeadband;
    private double mEndAngle;
    private boolean mFinished;

    public TurnWithDegrees(IDriveTrain aDriveTrain, IPositioner aPositioner, double aTurnDegrees, double aSpeed)
    {
        this(aDriveTrain, aPositioner, aTurnDegrees, aSpeed, 5);
    }

    public TurnWithDegrees(IDriveTrain aDriveTrain, IPositioner aPositioner, double aTurnDegrees, double aSpeed, double aDeadband)
    {
        mDriveTrain = aDriveTrain;
        mPositioner = aPositioner;
        mAngleToTurn = aTurnDegrees;
        mSpeed = aSpeed;
        mDeadband = aDeadband;
    }

    @Override
    protected void initialize()
    {
        mEndAngle = mAngleToTurn + mPositioner.getOrientationDegrees();
        mFinished = false;
    }

    @Override
    protected void execute()
    {
        double error = mPositioner.getOrientationDegrees() - mEndAngle;

        // turn left
        if (error > mDeadband)
        {
            mDriveTrain.setLeftRightSpeed(-mSpeed, mSpeed);
        }
        // turn right
        else if (error < -mDeadband)
        {
            mDriveTrain.setLeftRightSpeed(mSpeed, -mSpeed);
        }
        else
        {
            mDriveTrain.stop();
            mFinished = true;
        }

    }

    @Override
    protected boolean isFinished()
    {
        return mFinished;
    }

    @Override
    protected void end()
    {
        mDriveTrain.setLeftRightSpeed(0, 0);
    }

    @Override
    protected void interrupted()
    {

    }

}
