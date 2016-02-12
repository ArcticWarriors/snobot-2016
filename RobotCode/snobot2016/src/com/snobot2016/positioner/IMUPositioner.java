package com.snobot2016.positioner;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.Logger;
import com.snobot.xlib.Utilities;
import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class IMUPositioner implements IPositioner, ISubsystem
{
    private double mXPosition;
    private double mYPosition;
    private double mOrientation;
    private double mTotalDistance;
    private double mChangeInDistance;
    private Timer mTimer;
    private Gyro mGyro;
    private Accelerometer mAccelerometer;
    private IDriveTrain mDriveTrain;
    private double mSpeedX;
    private double mSpeedY;
    public Logger mLogger;
    private double mOffset;
    private double mAccelerationX;
    private double mAccelerationY;
    private double mLastTime;

    public IMUPositioner(Gyro aGyro, Accelerometer aAccelerometer, IDriveTrain aDriveTrain, Logger aLogger)
    {
        mGyro = aGyro;
        mAccelerometer = aAccelerometer;
        mDriveTrain = aDriveTrain;
        mLogger = aLogger;
        mXPosition = 0;
        mYPosition = 0;
        mOrientation = 0;
        mTotalDistance = 0;
        mChangeInDistance = 0;
        mTimer = new Timer();
        mSpeedX = 0;
        mSpeedY = 0;
        mAccelerationX = 0;
        mAccelerationY = 0;
        mLastTime = 0;
    }

    @Override
    public void init()
    {

        mTimer.start();

        mLogger.addHeader("X-coordinate");
        mLogger.addHeader("Y-coordinate");
        mLogger.addHeader("Orientation");
        mLogger.addHeader("Speed");
    }

    @Override
    public void update()
    {

        // Orientation
        mOrientation = Utilities.boundAngle0to360Degrees(mGyro.getAngle() + mOffset);
        double orientationRadians = Math.toRadians(mOrientation);

        mAccelerationX = mAccelerometer.getX();
        mSpeedX += (mAccelerationX * mLastTime);

        mAccelerationY = mAccelerometer.getX();
        mSpeedY += (mAccelerationX * mLastTime);
        // double changeInDistanceX = mSpeedX;
        // double changeInDistanceY;
        // mTotalDistance = mSpeed
        mLastTime = mTimer.get();
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
        // TODO Auto-generated method stub

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

    @Override
    public double getXPosition()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getYPosition()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getOrientationDegrees()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getOrientationRadians()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getTotalDistance()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setXPosition(double inputX)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setYPosition(double inputY)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setOrientationRadians(double inputRadians)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setOrientationDegrees(double inputDegrees)
    {
        // TODO Auto-generated method stub

    }

}
