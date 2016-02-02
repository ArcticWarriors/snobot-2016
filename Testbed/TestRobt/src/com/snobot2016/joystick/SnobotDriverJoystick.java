package com.snobot2016.joystick;

import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotDriverJoystick implements IDriverJoystick
{
    private Joystick mJoystick;
    private double mLeftSpeed;
    private double mRightSpeed;

    public SnobotDriverJoystick(Joystick aJoystick)
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
        mRightSpeed = mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
        mLeftSpeed = mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
    }

    @Override
    public void control()
    {

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
    public double getRightSpeed()
    {
        return mRightSpeed;
    }

    @Override
    public double getLeftSpeed()
    {
        return mLeftSpeed;
    }

}
