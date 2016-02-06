package com.snobot2016.harvester;

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Harvester implements IHarvester
{
    private SpeedController mPivotMotor;
    private SpeedController mRollerMotor;
    private double mRoller;
    private double mPivot;
    private IOperatorJoystick mOperatorJoystick;
    
    public Harvester(SpeedController aHarvesterRollerMotor, SpeedController aHarvesterPivotMotor,IOperatorJoystick aOperatorJoystick)
    {
        mRollerMotor = aHarvesterRollerMotor;
        mPivotMotor = aHarvesterPivotMotor;
        mOperatorJoystick = aOperatorJoystick; 
        
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
        if (mOperatorJoystick.getHarvesterRollerSpeedForward()&& mOperatorJoystick.getHarvesterRollerSpeedReverse())
        {
            setRollerMotorSpeed(0);
        }
        else if (mOperatorJoystick.getHarvesterRollerSpeedForward())
        {
            setRollerMotorSpeed(1);
        }
        else if (mOperatorJoystick.getHarvesterRollerSpeedReverse())
        {
            setRollerMotorSpeed(-1);
        }
        else
        {
            setRollerMotorSpeed(0);
        }
        
        
        if (mOperatorJoystick.getHarvesterPivotSpeedUp()&& mOperatorJoystick.getHarvesterPivotSpeedDown())
        {
            setPivotMotorSpeed(0);
        }
        else if (mOperatorJoystick.getHarvesterPivotSpeedUp())
        {
            setPivotMotorSpeed(1);
        }
        else if (mOperatorJoystick.getHarvesterPivotSpeedDown())
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
        SmartDashboard.putNumber(SmartDashBoardNames.sPIVOT_MOTOR, mPivot);  
        SmartDashboard.putNumber(SmartDashBoardNames.sROLLER_MOTOR, mRoller);
        
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
