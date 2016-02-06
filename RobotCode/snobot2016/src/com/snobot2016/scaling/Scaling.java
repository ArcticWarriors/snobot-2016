package com.snobot2016.scaling;

import com.snobot.xlib.Logger;

/**
 * Author Jeffrey/Michael
 * class for scaling arm of the robot
 * creates auto-climb feature
 * 
 */

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Scaling implements IScaling
{
    private SpeedController mScaleMoveMotor;
    private SpeedController mScaleTiltMotor;
    private IOperatorJoystick mJoystick;
    private double mMoveSpeed;
    private double mTiltSpeed;
    private boolean mAmIClimbing;
    private Timer mTimer;
    private Logger mLogger;

    public Scaling(SpeedController aScaleMoveMotor, SpeedController aScaleTiltMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger)
    {
        mScaleMoveMotor = aScaleMoveMotor;
        mScaleTiltMotor = aScaleTiltMotor;
        mJoystick = aOperatorJoystick;
        mLogger = aLogger;
        mTimer = new Timer();
    }

    @Override
    public void init()
    {
        
    }

    @Override
    public void update()
    {

    }

    @Override
    public void control()
    {
        setScaleSpeedMove(mJoystick.getScaleMoveSpeed());
        setScaleSpeedTilt(mJoystick.getScaleTiltSpeed());
        mTiltSpeed = mJoystick.getScaleTiltSpeed();

        // boolean for auto climb feature
        if (mJoystick.isFinalCountDown())
        {
            mAmIClimbing = true;
            mTimer.start();
        }
        if (mAmIClimbing)
        {
            setScaleSpeedMove(1);
            mTimer.get();
        }
        if (mTimer.get() > 10)
        {
            mTimer.stop();
            setScaleSpeedMove(0);
            mAmIClimbing = false;
        }

    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {
        // puts scale motor, tilt motor, and timer on SmartDashboard,
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_MOVE_MOTOR, mMoveSpeed);
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_TILT_MOTOR, mTiltSpeed);
        SmartDashboard.putNumber(SmartDashBoardNames.sTIMER, mTimer.get());
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mMoveSpeed);
        mLogger.updateLogger(mTiltSpeed);
    }

    @Override
    public void stop()
    {
        setScaleSpeedMove(0);
        setScaleSpeedTilt(0);
    }

    @Override
    public void pullUpWall()
    {
        setScaleSpeedMove(1);
    }

    @Override
    public void lowerDownWall()
    {
        setScaleSpeedMove(-1);
    }

    public void setScaleSpeedMove(double aSpeedMove)
    {
        mScaleMoveMotor.set(aSpeedMove);
    }

    public void setScaleSpeedTilt(double aSpeedTilt)
    {
        mScaleTiltMotor.set(aSpeedTilt);
    }

    @Override
    public void tiltRaise()
    {
        mScaleTiltMotor.set(1);
    }

    @Override
    public void tiltLower()
    {
        mScaleTiltMotor.set(-1);
    }

}
