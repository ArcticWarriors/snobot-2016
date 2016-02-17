package com.snobot2016.joystick;

import com.snobot.xlib.Utilities;
import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotOperatorJoystick implements IOperatorJoystick
{
    private Joystick mJoystick;
    private double mScaleTiltSpeed;
    private double mScaleClimbSpeed;

    private boolean mMotorRollerSpeedForward;
    private boolean mMotorRollerSpeedReverse;
    private boolean mMotorPivotSpeedUp;
    private boolean mMotorPivotSpeedDown;
    private boolean mFinalCountDown;
    private boolean mHarvesterUp;
    private boolean mHarvesterDown;

    private boolean mGroundAngle;
    private boolean mMoveForIntakeAngle;
    private boolean mVerticalAngle;
    private boolean mHookAngle;

    public SnobotOperatorJoystick(Joystick aJoystick)
    {
        mJoystick = aJoystick;
    }

    @Override
    public void init()
    {

    }

    @Override
    public void update()
    {
        mScaleTiltSpeed = Utilities.stopInDeadband(mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS), .02);
        mScaleClimbSpeed = Utilities.stopInDeadband(mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS), .01);

        mMotorRollerSpeedForward = mJoystick.getRawButton(XboxButtonMap.A_BUTTON);
        mMotorRollerSpeedReverse = mJoystick.getRawButton(XboxButtonMap.B_BUTTON);
        mHarvesterDown = mJoystick.getRawButton(XboxButtonMap.X_BUTTON);
        mHarvesterUp = mJoystick.getRawButton(XboxButtonMap.Y_BUTTON);
        mFinalCountDown = mJoystick.getRawButton(XboxButtonMap.START_BUTTON);

        mGroundAngle = mJoystick.getRawButton(XboxButtonMap.LEFT_TRIGGER);
        mMoveForIntakeAngle = mJoystick.getRawButton(XboxButtonMap.RIGHT_TRIGGER);
        mVerticalAngle = mJoystick.getRawButton(XboxButtonMap.L3_BUTTON);
        mHookAngle = mJoystick.getRawButton(XboxButtonMap.R3_BUTTON);
    }

    @Override
    public void control()
    {

    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {

    }

    @Override
    public void updateLog()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public double getScaleMoveSpeed()
    {
        return mScaleClimbSpeed;
    }

    @Override
    public double getScaleTiltSpeed()
    {

        return mScaleTiltSpeed;
    }

    @Override
    public boolean isFinalCountDown()
    {
        return mFinalCountDown;
    }

    @Override
    public boolean isHarvesterRollerForward()
    {
        return mMotorRollerSpeedForward;
    }

    @Override
    public boolean isHarvesterRollerReverse()
    {
        return mMotorRollerSpeedReverse;
    }

    @Override
    public boolean isHarvesterUp()
    {
        return mHarvesterUp;
    }

    @Override
    public boolean isHarvesterDown()
    {
        return mHarvesterDown;
    }

    public boolean isScaleGoToGroundPressed()
    {
        return mGroundAngle;
    }

    @Override
    public boolean isScaleGoToVerticalPressed()
    {
        return mVerticalAngle;
    }

    @Override
    public boolean isScaleMoveForIntakePressed()
    {
        return mMoveForIntakeAngle;
    }

    @Override
    public boolean isScaleGoToHookPositionPressed()
    {
        return mHookAngle;
    }

}
