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
    private boolean mGoodToRaise;
    private boolean mGoodToLower;
    private double mPotPercentage;
    private double mHarvesterPivotSpeed;

    public Harvester(SpeedController aHarvesterRollerMotor, SpeedController aHarvesterPivotMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger,
            AnalogInput aHarvesterPot)
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
        mGoodToRaise = (mVoltage > Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue());
        mGoodToLower = (mVoltage < Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue());
        mPotPercentage = (((mVoltage - Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue())
                / (Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue() - Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue())) * 100);

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
        if (mOperatorJoystick.isHarvesterUp() && mOperatorJoystick.isHarvesterDown())
        {
            stopHarvester();
        }
        else if (mOperatorJoystick.isHarvesterUp())
        {
            moveToPercentage(100);
        }
        else if (mOperatorJoystick.isHarvesterDown())
        {
            moveToPercentage(0);
        }
        else
        {
            stopHarvester();
        }

    }

    @Override
    public void rereadPreferences()
    {

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
        if (goodToRaise())
        {
            setPivotMotorSpeed(-1);
        }
    }

    @Override
    public void lowerHarvester()
    {
        if (goodToLower())
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

    @Override
    public void setRollerMotorSpeed(double aRollerSpeed)
    {
        mRoller = aRollerSpeed;
        mRollerMotor.set(aRollerSpeed);
    }

    @Override
    public void setPivotMotorSpeed(double aPivotSpeed)
    {
        mPivot = aPivotSpeed;
        mPivotMotor.set(aPivotSpeed);
    }

    @Override
    public boolean goodToRaise()
    {
        return mGoodToRaise;
    }

    @Override
    public boolean goodToLower()
    {
        return mGoodToLower;
    }

    @Override
    public double percentageLowered()
    {
        return mPotPercentage;
    }

    @Override
    public void stopHarvester()
    {
        setPivotMotorSpeed(0);
    }

    @Override
    public void stopRoller()
    {
        setRollerMotorSpeed(0);
    }

    /**
     * 
     */
    @Override
    public boolean moveToPercentage(double aPotGoal)
    {
        boolean raise = true;
        double error = aPotGoal - percentageLowered();
        mHarvesterPivotSpeed = Properties2016.sHARVESTER_POT_KP.getValue() * error;
        if (mHarvesterPivotSpeed > 0)
        {
            raise = false;
        }
        if (raise)
        {
            if (goodToRaise())
            {
                if (Math.abs(error) < 10)
                {
                    stopHarvester();
                    return true;
                }
                else
                {
                    setPivotMotorSpeed(mHarvesterPivotSpeed);
                    return false;
                }
            }
        }
        else
        {
            if (goodToLower())
            {
                if (Math.abs(error) < 10)
                {
                    stopHarvester();
                    return true;
                }
                else
                {
                    setPivotMotorSpeed(mHarvesterPivotSpeed);
                    return false;
                }
            }
        }
        return false;
    }

}
