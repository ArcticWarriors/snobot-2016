package com.snobot2016.harvester;

import com.snobot.xlib.Logger;
/**
 * Author Jeffrey/Michael
 * creates harvester for Snobot
 * 
 */
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
    private Logger mLogger;

    public Harvester(SpeedController aHarvesterRollerMotor, SpeedController aHarvesterPivotMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger)
    {
        mRollerMotor = aHarvesterRollerMotor;
        mPivotMotor = aHarvesterPivotMotor;
        mOperatorJoystick = aOperatorJoystick;
        mLogger = aLogger;

    }

    @Override
    public void init()
    {

    }

    @Override
    public void update()
    {

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

    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mPivot);
        mLogger.updateLogger(mRoller);
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
        setPivotMotorSpeed(-1);
    }

    @Override
    public void lowerHarvester()
    {
        setPivotMotorSpeed(1);
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

}
