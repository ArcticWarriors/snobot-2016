package com.visionTest.shooter;

import com.snobot.xlib.ISubsystem;
import com.snobot2016.Properties2016;
import com.snobot2016.SmartDashBoardNames;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter implements ISubsystem
{
    private SpeedController mTurretMotor;
    private SpeedController mShooterMotor;
    private AnalogInput mTurretPot;

    private double mTurretAngle;

    private boolean mAutoAiming;
    private double mDesiredTurretAngle;

    public Shooter(SpeedController aTurretMotor, SpeedController aShooterMotor, AnalogInput aTurretPot)
    {
        mTurretMotor = aTurretMotor;
        mShooterMotor = aShooterMotor;
        mTurretPot = aTurretPot;

        mDesiredTurretAngle = 0;
    }

    public void control()
    {
        if (!mAutoAiming)
        {
            moveToDesiredAngle();
        }
    }

    public void moveToDesiredAngle()
    {
        moveTurretToAngle(mDesiredTurretAngle);
    }

    public void setDesiredTurretAngle(double aAngle)
    {
        mDesiredTurretAngle = aAngle;
    }

    public double getTurretAngle()
    {
        return mTurretAngle;
    }

    private void moveTurretToAngle(double aAngle)
    {
        double error = aAngle - getTurretAngle();
        double kp = Properties2016.sTURRET_KP.getValue();
        kp = .005;

        mTurretMotor.set(error * kp);
    }

    @Override
    public void init()
    {

    }

    @Override
    public void update()
    {
        mTurretAngle = mTurretPot.getVoltage() / 5.0 * 360;
    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashBoardNames.sTURRET_ANGLE, mTurretAngle);
        SmartDashboard.putNumber(SmartDashBoardNames.sTURRET_MOTOR, mTurretMotor.get());
    }

    @Override
    public void updateLog()
    {

    }

    @Override
    public void stop()
    {
        mTurretMotor.set(0);
        mShooterMotor.set(0);
    }

    public void setAutoAiming(boolean aAutoAiming)
    {
        mAutoAiming = aAutoAiming;
    }
}
