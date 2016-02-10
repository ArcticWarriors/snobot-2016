package com.snobot2016.scaling;

import com.snobot.xlib.Logger;
import com.snobot2016.Properties2016;

/**
 * Author Jeffrey/Michael
 * class for scaling arm of the robot
 * creates auto-climb feature
 * 
 */

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
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
    private AnalogInput mPot; // Potentiometer
    private double mAngle; // Current Potentiometer Angle
    private boolean mIsUp; // Is scaling up
    private boolean mIsDown; // Is scaling down

    public Scaling(SpeedController aScaleMoveMotor, SpeedController aScaleTiltMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger,
            AnalogInput aPot)
    {
        mScaleMoveMotor = aScaleMoveMotor;
        mScaleTiltMotor = aScaleTiltMotor;
        mJoystick = aOperatorJoystick;
        mLogger = aLogger;
        mTimer = new Timer();
        mPot = aPot; // Potentiometer
    }

    @Override
    public void init()
    {

    }

    @Override
    public void update()
    {
        double high_angle = Properties2016.sSCALE_HIGH_ANGLE.getValue();
        double low_angle = Properties2016.sSCALE_LOW_ANGLE.getValue();
        calculateAngle(mPot.getVoltage());
        if (mAngle == high_angle)
        {
            mIsUp = true;
            mIsDown = false;
        }
        else if (mAngle == low_angle)
        {
            mIsUp = false;
            mIsDown = true;
        }
        else
        {
            mIsDown = false;
            mIsUp = false;
        }
    }

    @Override
    public void control()
    {
        setScaleSpeedMove(mJoystick.getScaleMoveSpeed());
        double tiltspeed = mJoystick.getScaleTiltSpeed();
        if (mIsDown = true && tiltspeed < 0)
        {
            setScaleSpeedTilt(0);
            // Ensures motor will not go lower than lowest possible angle
        }
        else if (mIsUp = true && tiltspeed > 0)
        {
            setScaleSpeedTilt(0);
            // Ensures motor will not go higher than highest possible angle
        }
        else
        {
            setScaleSpeedTilt(tiltspeed);
        }

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
        // puts scale motor, tilt motor, current angle and timer on
        // SmartDashboard
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_MOVE_MOTOR, mMoveSpeed);
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_TILT_MOTOR, mTiltSpeed);
        SmartDashboard.putNumber(SmartDashBoardNames.sTIMER, mTimer.get());
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALNG_CURRENT_ANGLE, mAngle);
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
    public void extendUpWall()
    {
        setScaleSpeedTilt(1);
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

    public void calculateAngle(double voltage)
    {
        double high_angle = Properties2016.sSCALE_HIGH_ANGLE.getValue();
        double low_angle = Properties2016.sSCALE_LOW_ANGLE.getValue();
        double high_volts = Properties2016.sSCALE_HIGH_VOLTAGE.getValue();
        double low_volts = Properties2016.sSCALE_LOW_VOLTAGE.getValue();
        mAngle = ((high_angle - low_angle) / (high_volts - low_volts)) * mPot.getVoltage();
        // Grabs properties of minimum and maximum configs for potentiometer,
        // Obtains the angle of the scaling arm, given the voltage of
        // a configured potentiometer
    }

    public void getAngle(double mAngle)
    {

    }

    @Override
    public void tiltLower()
    {
        mScaleTiltMotor.set(1);
    }

    @Override
    public void tiltRaise()
    {
        mScaleTiltMotor.set(-1);
    }

}
