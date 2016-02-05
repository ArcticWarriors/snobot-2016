package com.snobot2016.joystick;

import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotOperatorJoystick implements IOperatorJoystick
{
    private Joystick mJoystick;
    private double mMotorTiltSpeed;
    private double mMotorMoveSpeed;

    private boolean mMotorRollerSpeedForward;
    private boolean mMotorRollerSpeedReverse;
    private boolean mMotorPivotSpeedUp;
    private boolean mMotorPivotSpeedDown;
    private boolean mFinalCountDown;

    public SnobotOperatorJoystick(Joystick aJoystick)
    {
        mJoystick = aJoystick;
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void update()
    {
        mMotorTiltSpeed = mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
        mMotorMoveSpeed = mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);

        mMotorRollerSpeedForward = mJoystick.getRawButton(XboxButtonMap.A_BUTTON);
        mMotorRollerSpeedReverse = mJoystick.getRawButton(XboxButtonMap.B_BUTTON);
        mMotorPivotSpeedUp = mJoystick.getRawButton(XboxButtonMap.X_BUTTON);
        mMotorPivotSpeedDown = mJoystick.getRawButton(XboxButtonMap.Y_BUTTON);
        mFinalCountDown = mJoystick.getRawButton(XboxButtonMap.START_BUTTON);
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

    }

    @Override
    public double getScaleMoveSpeed()
    {
        return mMotorMoveSpeed;
    }

    @Override
    public double getScaleTiltSpeed()
    {

        return mMotorTiltSpeed;
    }

    @Override
    public boolean getHarvesterPivotSpeedUp()
    {
        return mMotorPivotSpeedUp;
    }

    @Override
    public boolean getHarvesterPivotSpeedDown()
    {
        return mMotorPivotSpeedDown;
    }

    @Override
    public boolean getHarvesterRollerSpeedForward()
    {
        return mMotorRollerSpeedForward;
    }

    @Override
    public boolean getHarvesterRollerSpeedReverse()
    {
        return mMotorRollerSpeedReverse;
    }

    @Override
    public boolean getFinalCountDown()
    {
        return mFinalCountDown;
    }
}
