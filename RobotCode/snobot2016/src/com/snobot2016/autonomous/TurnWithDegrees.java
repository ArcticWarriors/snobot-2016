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
    private double mStartingAngle;

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
        mStartingAngle = mPositioner.getOrientationDegrees();
        mFinalAngle = mTurnDegrees + mStartingAngle;

    }

    @Override
    protected void execute()
    {
        if (mTurnDegrees > 0)
        {
            mDriveTrain.setLeftRightSpeed(-mSpeed, mSpeed);
        }
        else if (mTurnDegrees < 0)
        {
            mDriveTrain.setLeftRightSpeed(mSpeed, -mSpeed);
        }
        else
        {
            mDriveTrain.stop();
        }

    }

    @Override
    protected boolean isFinished()
    {
        if (mTurnDegrees == 0)
        {
            return true;
        }
        else if (mFinalAngle >= mPositioner.getOrientationDegrees() && mTurnDegrees < 0 && mPositioner.getOrientationDegrees() < mStartingAngle)
        {
            return true;
        }
        else if (mFinalAngle <= mPositioner.getOrientationDegrees() && mTurnDegrees < 0 && mPositioner.getOrientationDegrees() > mStartingAngle)
        {
            return true;
        }
        else
        {
            return false;
        }

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
