package com.snobot2016.harvester;

import com.snobot.xlib.Logger;
import com.snobot2016.Properties2016;
/**
 * Author Jeffrey/Michael
 * creates harvester for Snobot
 * 
 */
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Harvester implements IHarvester
{
    private SpeedController mPivotMotor;
    private SpeedController mRollerMotor;
    private double mRoller;
    private double mPivot;
    private IOperatorJoystick mOperatorJoystick;
    private Logger mLogger;
    private AnalogInput mHarvesterPot;
    private double mVoltage;

    public Harvester(SpeedController aHarvesterRollerMotor, SpeedController aHarvesterPivotMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger, AnalogInput aHarvesterPot)
    {
        mRollerMotor = aHarvesterRollerMotor;
        mPivotMotor = aHarvesterPivotMotor;
        mOperatorJoystick = aOperatorJoystick;
        mLogger = aLogger;
        mHarvesterPot = aHarvesterPot;
        
    }

    @Override
    public void init()
    {
    }

    @Override
    public void update()
    {
        mVoltage = mHarvesterPot.getVoltage();
    }

    @Override
    public void control()
    {
        // button setup for harvester roller
        if (mOperatorJoystick.isHarvesterRollerForward() && mOperatorJoystick.isHarvesterRollerReverse())
        {
            stopRoller();
        }
        else if (mOperatorJoystick.isHarvesterRollerForward())
        {
            rollIn();
        }
        else if (mOperatorJoystick.isHarvesterRollerReverse())
        {
            rollOut();
        }
        else
        {
            stopRoller();
        }

        // button setup for harvester pivot
        if (mOperatorJoystick.isHarvesterPivotUp()&& mOperatorJoystick.isHarvesterPivotDown())
        {
            stopHarvester();
        }
        else if (mOperatorJoystick.isHarvesterPivotUp())
        {
            raiseHarvester();
        }
        else if (mOperatorJoystick.isHarvesterPivotDown())
        {
            lowerHarvester();
        }
        else
        {
            stopHarvester();
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
        // displays pivot and roller motor on SmartDashboard
        SmartDashboard.putNumber(SmartDashBoardNames.sPIVOT_MOTOR, mPivot);
        SmartDashboard.putNumber(SmartDashBoardNames.sROLLER_MOTOR, mRoller);
        SmartDashboard.putNumber(SmartDashBoardNames.sPOT_PERCENTAGE, this.percentageLowered());
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mPivot);
        mLogger.updateLogger(mRoller);
        mLogger.updateLogger(mVoltage);
        mLogger.updateLogger(percentageLowered());
    }

    @Override
    public void stop()
    {
        stopRoller();
        stopHarvester();
    }

    @Override
    public void raiseHarvester()
    {
        if(goodToLowerVoltage())
        {
            setPivotMotorSpeed(-1);
        }
    }

    @Override
    public void lowerHarvester()
    {
        if(goodToRaiseVoltage())
        {
            setPivotMotorSpeed(1);
        }
    }

    @Override
    public void rollIn()
    {
        setRollerMotorSpeed(1);
    }

    @Override
    public void rollOut()
    {
        setRollerMotorSpeed(-1);
    }

    private void setRollerMotorSpeed(double aRollerSpeed)
    {
        mRoller = aRollerSpeed;
        mRollerMotor.set(aRollerSpeed);
    }

    private void setPivotMotorSpeed(double aPivotSpeed)
    {
        mPivot = aPivotSpeed;
        mPivotMotor.set(aPivotSpeed);
    }
    
    private boolean goodToLowerVoltage()
    {
        return (mVoltage > Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue());  
    }
    
    private boolean goodToRaiseVoltage()
    {
        return (mVoltage < Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue());
    }
    
    private double percentageLowered()
    {
        return ((mVoltage / Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue()) *100);
    }
    
    private void stopHarvester()
    {
        setPivotMotorSpeed(0);
    }
    
    private void stopRoller()
    {
        setRollerMotorSpeed(0);
    }

}
