package com.snobot2016.joystick;

import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotDriveArcadeJoystick implements IDriverJoystick
{
    private double mArcadePower;
    private double mArcadeTurn;
    private Joystick mJoystick;
    
    
    public SnobotDriveArcadeJoystick(Joystick aJoystick)
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
        mArcadePower = mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);
        mArcadeTurn = mJoystick.getRawAxis(XboxButtonMap.RIGHT_X_AXIS);
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
    public double getRightSpeed()
    {
        return 0;
    }

    @Override
    public double getLeftSpeed()
    {
        return 0;
    }

    @Override
    public double getArcadePower()
    {
        return mArcadePower;
    }

    @Override
    public double getArcadeTurn()
    {
        return mArcadeTurn;
    }

    @Override
    public boolean isArcadeMode()
    {
        return true;
    }

}
