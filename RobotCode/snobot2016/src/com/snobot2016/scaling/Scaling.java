package com.snobot2016.scaling;

import com.snobot2016.joystick.IOperatorJoystick;
import com.snobot2016.joystick.SnobotOperatorJoystick;

import edu.wpi.first.wpilibj.SpeedController;

public class Scaling implements IScaling
{
    SpeedController mScaleMotor;
    IOperatorJoystick mJoystick;
    
    
    public Scaling(SpeedController aScaleMotor, IOperatorJoystick mOperatorJoystick)
    {
        mScaleMotor = aScaleMotor;
        mJoystick = mOperatorJoystick;
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
        
    }

    @Override
    public void control()
    {
        // TODO Auto-generated method stub
        setScaleSpeed(mJoystick.getScaleMotorSpeed());
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
    public boolean extendUpWall()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean pullUpWall()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean lowerDownWall()
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    public void setScaleSpeed(double aSpeed)
    {
        mScaleMotor.set(aSpeed);
    }

}
