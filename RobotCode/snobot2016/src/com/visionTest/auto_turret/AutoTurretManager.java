package com.visionTest.auto_turret;

import com.snobot.xlib.ISubsystem;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.positioner.IPositioner;
import com.visionTest.shooter.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurretManager implements ISubsystem
{
    private final Shooter mShooter;
    private final IPositioner mPositioner;

    private String mLastReceivedInfo;

    private boolean mAutoAiming;
    private double mDesiredTurretAngle;

    private IStateManager mStateManager;

    public AutoTurretManager(IPositioner aPositioner, Shooter aShooter)
    {
        mPositioner = aPositioner;
        mShooter = aShooter;
        mStateManager = new StateManager();

        setAutoAiming(true);
    }

    private void setAutoAiming(boolean aAutoAiming)
    {
        mAutoAiming = aAutoAiming;
        mShooter.setAutoAiming(mAutoAiming);
    }

    @Override
    public void init()
    {

    }



    @Override
    public void update()
    {
        mStateManager.saveRobotState(
                Timer.getFPGATimestamp(), 
                mPositioner.getXPosition(), 
                mPositioner.getYPosition(), 
                mPositioner.getOrientationDegrees(),
                mShooter.getTurretAngle());

        String camera_info = SmartDashboard.getString(SmartDashBoardNames.sCAMERA_INFO, "");

        if (!camera_info.isEmpty() && !camera_info.equals(mLastReceivedInfo))
        {
            mLastReceivedInfo = camera_info;

            String[] parts = camera_info.split(",");
            double timestamp = Double.parseDouble(parts[0]);
            double camera_angle = Double.parseDouble(parts[1]);
            double camera_distance = Double.parseDouble(parts[2]);

            if (mStateManager.canCalculate(timestamp))
            {
                mStateManager.setCurrentCameraState(timestamp, camera_angle, camera_distance);
            }
        }

        double absolute_desired_angle = mStateManager.calculateDesiredAngleFromState();
        mDesiredTurretAngle = absolute_desired_angle - mPositioner.getOrientationDegrees();
    }

    @Override
    public void control()
    {
        if (mAutoAiming)
        {
            mShooter.setDesiredTurretAngle(mDesiredTurretAngle);
            mShooter.moveToDesiredAngle();
        }
    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashBoardNames.sTARGET_LOC_X, mStateManager.getTargetLocation().mX);
        SmartDashboard.putNumber(SmartDashBoardNames.sTARGET_LOC_Y, mStateManager.getTargetLocation().mY);
    }

    @Override
    public void updateLog()
    {

    }

    @Override
    public void stop()
    {

    }

    public boolean isAutoAiming()
    {
        return mAutoAiming;
    }

}
