package com.snobot2016.joystick;

import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotOperatorJoystick implements IOperatorJoystick
{
    private Joystick mJoystick;
    private double mMotorSpeed;
    
    
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
        // TODO Auto-generated method stub
        mMotorSpeed = mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS); 
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
    public double getScaleMotorSpeed()
    {
        // TODO Auto-generated method stub
        return mMotorSpeed;
    }
    
}
