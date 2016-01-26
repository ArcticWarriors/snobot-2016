package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.command.Command;

public class TurnWithDegrees extends Command
{
    private IDriveTrain mDriveTrain;
    private IPositioner mPositioner;
    private double mTurnDegrees;
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
        if (mTurnDegrees < 0)
        {
            if (mStartingAngle + (mTurnDegrees + 360) >= mPositioner.getOrientationDegrees())
            {
                return true;
            }
        }
        else if (mTurnDegrees >= 360)
        {
            if (mStartingAngle + (mTurnDegrees - 360) >= mPositioner.getOrientationDegrees())
            {
                return true;
            }
        }
        else if (mStartingAngle + mTurnDegrees >= mPositioner.getOrientationDegrees())
        {
            return true;
        }
        return false;

    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

}
