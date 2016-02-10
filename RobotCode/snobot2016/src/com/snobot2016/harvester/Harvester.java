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
            setRollerMotorSpeed(0);
        }
        else if (mOperatorJoystick.isHarvesterRollerForward())
        {
            setRollerMotorSpeed(1);
        }
        else if (mOperatorJoystick.isHarvesterRollerReverse())
        {
            setRollerMotorSpeed(-1);
        }
        else
        {
            setRollerMotorSpeed(0);
        }

        // button setup for harvester pivot
        if (mOperatorJoystick.isHarvesterPivotUp()&& mOperatorJoystick.isHarvesterPivotDown())
        {
            setPivotMotorSpeed(0);
        }
        else if (mOperatorJoystick.isHarvesterPivotUp())
        {
            setPivotMotorSpeed(1);
        }
        else if (mOperatorJoystick.isHarvesterPivotDown())
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
        mLogger.updateLogger(this.percentageLowered());
    }

    @Override
    public void stop()
    {
        setRollerMotorSpeed(0);
        setPivotMotorSpeed(0);
    }

    @Override
    public void raiseHarvester()
    {
        if(this.goodToLowerVoltage())
        {
            setPivotMotorSpeed(-1);
        }
    }

    @Override
    public void lowerHarvester()
    {
        if(this.goodToRaiseVoltage())
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
        mRollerMotor.set(aRollerSpeed);
    }

    private void setPivotMotorSpeed(double aPivotSpeed)
    {
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
//    
//    public void raiseOrLowerUntilLimit(double aPivotSpeed)
//    {
//        
//        if(mHarvesterPot.getVoltage() > Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue())
//        {
//            if(aPivotSpeed >0)
//            {
//                mPivotMotor.set(-aPivotSpeed);
//            }
//            else
//            {
//                mPivotMotor.set(aPivotSpeed);
//            }
//            
//        }
//        else if(mHarvesterPot.getVoltage() < Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue())
//        {
//            if(aPivotSpeed >0)
//            {
//                mPivotMotor.set(aPivotSpeed);
//            }
//            else
//            {
//                mPivotMotor.set(-aPivotSpeed);
//            }
//        }
//        else
//        {
//            mPivotMotor.set(0);
//        }
//    }

}
