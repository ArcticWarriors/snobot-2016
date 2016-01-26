package com.snobot2016.positioner;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.Utilities;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Positioner implements ISubsystem, IPositioner
{
    private double mXPosition;
    private double mYPosition;
    private double mOrientation;
    private double mTotalDistance;
    private double mChangeInDistance;
    private double mLastDistance;
    private Timer mTimer;
    private double mLastTime;
    private Gyro mGyro;
    private IDriveTrain mDriveTrain;
    private double mSpeed;

    // private

    public Positioner(Gyro aGyro, IDriveTrain aDriveTrain)
    {
        mXPosition = 0;
        mYPosition = 0;
        mOrientation = 0;
        mTotalDistance = 0;
        mChangeInDistance = 0;
        mLastDistance = 0;
        mLastTime = 0;
        mSpeed = 0;
        mGyro = aGyro;
        mDriveTrain = aDriveTrain;
        mTimer = new Timer();
        // mAcceleration = 0;
    }

    @Override
    public void init()
    {
        mTimer.start();
    }

    @Override
    public void update()
    {
        // Orientation
        mOrientation = Utilities.boundAngle0to360Degrees(mGyro.getAngle());

        // ChangeInDistance and X/Y
        // TODO Need to account for slips when driving over defenses
        mTotalDistance = (mDriveTrain.getRightEncoderDistance() + mDriveTrain.getLeftEncoderDistance()) / 2;
        mChangeInDistance = mTotalDistance - mLastDistance;
        mXPosition += mChangeInDistance * Math.sin(mOrientation);
        mYPosition += mChangeInDistance * Math.cos(mOrientation);

        // Update
        mSpeed = (mChangeInDistance) / (mTimer.get() - mLastTime);
        mLastTime = mTimer.get();
        mLastDistance = mTotalDistance;
    }

    public double getXPosition()
    {
        return mXPosition;
    }

    public double getYPosition()
    {
        return mYPosition;
    }

    public double getOrientationDegrees()
    {
        return mOrientation;
    }

    public double getOrientationRadians()
    {
        return Math.toRadians(mOrientation);
    }

    public double getTotalDistance()
    {
        return mTotalDistance;
    }

    public void setXPosition(double inputX)
    {
        mXPosition = inputX;
    }

    public void setYposition(double inputY)
    {
        mYPosition = inputY;
    }

    public void setOrientationRadians(double inputRadians)
    {
        mOrientation = Math.toDegrees(inputRadians);
    }

    public void setOrientationDegrees(double inputDegrees)
    {
        mOrientation = inputDegrees;
    }

    @Override
    public void control()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashBoardNames.sX_POSITION, mXPosition);
        SmartDashboard.putNumber(SmartDashBoardNames.sY_POSITION, mYPosition);
        SmartDashboard.putNumber(SmartDashBoardNames.sORIENTATION, mOrientation);
        SmartDashboard.putNumber(SmartDashBoardNames.sSPEED, mSpeed);
    }

    @Override
    public void updateLog()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

    }

}