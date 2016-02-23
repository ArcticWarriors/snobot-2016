package com.snobot2016.joystick;

import com.snobot.xlib.Utilities;
import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotOperatorJoystick implements IOperatorJoystick
{
    private Joystick mJoystick;

    // Overrides
    private double mScaleTiltOverrideSpeed;
    private double mHarvesterTiltOverrideSpeed;

    // Scaling
    private double mClimbSpeed;
    private boolean mScaleGoToGround;
    private boolean mScaleGoToHarvest;
    private boolean mScaleGoToVertical;
    private boolean mScaleGoToHang;

    // Harvester
    private double mHarvestorSpeed;
    private boolean mMoveHarvestorToUp;
    private boolean mMoveHarvestorToDown;

    public SnobotOperatorJoystick(Joystick aJoystick)
    {
        mJoystick = aJoystick;
    }

    @Override
    public void init()
    {

    }

    @Override
    public void update()
    {
        // Overrides
        mScaleTiltOverrideSpeed = Utilities.stopInDeadband(-mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS), .09);
        mHarvesterTiltOverrideSpeed = Utilities.stopInDeadband(mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS), .06);

        mClimbSpeed = 0;

        if (mJoystick.getPOV() == XboxButtonMap.D_PAD_UP)
        {
            mClimbSpeed = 1;
        }
        else if (mJoystick.getPOV() == XboxButtonMap.D_PAD_DOWN)
        {
            mClimbSpeed = -1;
        }
        else if (mJoystick.getPOV() == XboxButtonMap.D_PAD_NULL)
        {
            mClimbSpeed = 0;
        }

        mScaleGoToGround = mJoystick.getRawButton(XboxButtonMap.A_BUTTON);
        mScaleGoToHarvest = mJoystick.getRawButton(XboxButtonMap.X_BUTTON);
        mScaleGoToVertical = mJoystick.getRawButton(XboxButtonMap.Y_BUTTON);
        mScaleGoToHang = mJoystick.getRawButton(XboxButtonMap.B_BUTTON);

        double rightTrigSpeed = mJoystick.getRawAxis(XboxButtonMap.RIGHT_TRIGGER);
        double leftTrigSpeed = mJoystick.getRawAxis(XboxButtonMap.LEFT_TRIGGER);

        mHarvestorSpeed = -(rightTrigSpeed - leftTrigSpeed);

        mMoveHarvestorToUp = mJoystick.getRawButton(XboxButtonMap.RB_BUTTON);
        mMoveHarvestorToDown = mJoystick.getRawButton(XboxButtonMap.LB_BUTTON);
    }

    @Override
    public void control()
    {

    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {

    }

    @Override
    public void updateLog()
    {

    }

    @Override
    public void stop()
    {

    }

    ///////////////////////////////////////////////
    // Emergency Overrides (for when sensors break)
    ///////////////////////////////////////////////
    
    @Override
    public double getScaleTiltOverrideSpeed()
    {
        return mScaleTiltOverrideSpeed;
    }

    @Override
    public double getHarvestorTiltOverrideSpeed()
    {
        return mHarvesterTiltOverrideSpeed;
    }

    ///////////////////////////////////////////////
    // Scaling
    ///////////////////////////////////////////////
    
    @Override
    public double getScaleMoveSpeed()
    {
        return mClimbSpeed;
    }

    @Override
    public boolean isScaleGoToGroundPressed()
    {
        return mScaleGoToGround;
    }

    @Override
    public boolean isScaleGoToVerticalPressed()
    {
        return mScaleGoToVertical;
    }

    @Override
    public boolean isScaleMoveForIntakePressed()
    {
        return mScaleGoToHarvest;
    }

    @Override
    public boolean isScaleGoToHookPositionPressed()
    {
        return mScaleGoToHang;
    }

    @Override
    public boolean isFinalCountDown()
    {
        return false; // TODO Disabled for now
    }

    @Override
    public double getHarvesterIntakeSpeed()
    {
        return mHarvestorSpeed;
    }

    @Override
    public boolean moveHarvesterToUpPosition()
    {
        return mMoveHarvestorToUp;
    }

    @Override
    public boolean moveHarvesterToDownPosition()
    {
        return mMoveHarvestorToDown;
    }

}
