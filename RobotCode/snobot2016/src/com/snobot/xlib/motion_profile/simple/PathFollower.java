package com.snobot.xlib.motion_profile.simple;

public class PathFollower
{
    private ISetpointIterator mSetpointIterator;
    private double kV;
    private double kA;
    private double kP;
    private double mPosition;
    private double mVelocity;
    private double mDeltaTime;

    public PathFollower(ISetpointIterator aSetpointIterator, double aKV, double aKA, double aKP)
    {
        new PathFollower(aSetpointIterator, aKV, aKA, aKP, 0.0, 0.0, 0.0);
    }

    public PathFollower(ISetpointIterator aSetpointIterator, double aKV, double aKA, double aKP, double aPosition, double aVelocity, double aDt)
    {
        mSetpointIterator = aSetpointIterator;
        kV = aKV;
        kA = aKA;
        kP = aKP;
        mPosition = aPosition;
        mVelocity = aVelocity;
        mDeltaTime = aDt;
    }

    public double calcMotorSpeed()
    {
        if (mSetpointIterator.isFinished())
        {
            return 0;
        }
        else
        {
            PathSetpoint mPathPoint = mSetpointIterator.getNextSetpoint(0, 0, .02); //TODO fill out first two values
            double error = mPathPoint.mPosition - mPosition;
            double mVelocityTerm = kV * mPathPoint.mVelocity;
            double mAccelerationTerm = kA * mPathPoint.mAcceleration;
            double mPositionTerm = kP * error;
            double output = mVelocityTerm + mAccelerationTerm + mPositionTerm;
            return output;
        }
    }

    public boolean isFinished()
    {
        return mSetpointIterator.isFinished();
    }
}