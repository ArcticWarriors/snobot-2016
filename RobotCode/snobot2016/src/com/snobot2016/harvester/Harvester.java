package com.snobot2016.harvester;

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Harvester implements IHarvester
{
    private SpeedController mPivotMotor;
    private SpeedController mRollerMotor;
    IOperatorJoystick mJoystick;
    private double mRollerSpeed;
    private double mPivotSpeed;
    
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
        // TODO -generated method stub
        
    }

    @Override
    public void control()
    {
        if (mJoystick.getHarvesterRollerSpeedForward()&& mJoystick.getHarvesterRollerSpeedReverse())
        {
            setRollerMotorSpeed(0);
            mRollerSpeed = 0;
        }
        else if (mJoystick.getHarvesterRollerSpeedForward())
        {
            setRollerMotorSpeed(1);
            mRollerSpeed = 1;
        }
        else if (mJoystick.getHarvesterRollerSpeedReverse())
        {
            setRollerMotorSpeed(-1);
            mRollerSpeed = -1;
        }
        else
        {
            setRollerMotorSpeed(0);
            mRollerSpeed = 0;
        }
        
        
        if (mJoystick.getHarvesterPivotSpeedUp()&& mJoystick.getHarvesterPivotSpeedDown())
        {
            setPivotMotorSpeed(0);
            mPivotSpeed = 0;
        }
        else if (mJoystick.getHarvesterPivotSpeedUp())
        {
            setPivotMotorSpeed(1);
            mPivotSpeed = 1;
        }
        else if (mJoystick.getHarvesterPivotSpeedDown())
        {
            setPivotMotorSpeed(-1);
            mPivotSpeed = -1;
        }
        else
        {
            setPivotMotorSpeed(0);
            mPivotSpeed = 0;
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
    	SmartDashboard.putNumber(SmartDashBoardNames.sROLLER_MOTOR, mRollerSpeed);
    	SmartDashboard.putNumber(SmartDashBoardNames.sPIVOT_MOTOR, mPivotSpeed);
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
