package com.snobot2016.harvester;

import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.SpeedController;

public class Harvester implements IHarvester
{
    private SpeedController mPivotMotor;
    private SpeedController mRollerMotor;
    IOperatorJoystick mJoystick;
    
    public Harvester(SpeedController aHarvesterRollerMotor, SpeedController aHarvesterPivotMotor,IOperatorJoystick aOperatorJoystick)
    {
        mRollerMotor = aHarvesterRollerMotor;
        mPivotMotor = aHarvesterPivotMotor;
        mJoystick = aOperatorJoystick; 
        
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
        if (mJoystick.getHarvesterRollerSpeedForward()&& mJoystick.getHarvesterRollerSpeedReverse())
        {
            setRollerMotorSpeed(0);
        }
        else if (mJoystick.getHarvesterRollerSpeedForward())
        {
            setRollerMotorSpeed(1);
        }
        else if (mJoystick.getHarvesterRollerSpeedReverse())
        {
            setRollerMotorSpeed(-1);
        }
        else
        {
            setRollerMotorSpeed(0);
        }
        
        
        if (mJoystick.getHarvesterPivotSpeedUp()&& mJoystick.getHarvesterPivotSpeedDown())
        {
            setPivotMotorSpeed(0);
        }
        else if (mJoystick.getHarvesterPivotSpeedUp())
        {
            setPivotMotorSpeed(1);
        }
        else if (mJoystick.getHarvesterPivotSpeedDown())
        {
            setPivotMotorSpeed(-1);
        }
        else
        {
            setPivotMotorSpeed(0);
        }

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
    public boolean raiseHarvester()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean rollIn()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean rollOut()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean lowerHarvester()
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    private void setRollerMotorSpeed(double aRollerSpeed)
    {
        mRollerMotor.set(aRollerSpeed);
    }

    private void setPivotMotorSpeed(double aPivotSpeed)
    {
        mPivotMotor.set(aPivotSpeed);
    }

}
