package com.snobot2016.joystick;

import com.snobot.xlib.XboxButtonMap;

import edu.wpi.first.wpilibj.Joystick;

public class SnobotOperatorJoystick implements IOperatorJoystick
{
    private Joystick mJoystick;
    private double mMotorTiltSpeed;
    private double mMotorMoveSpeed;

    private boolean mMotorRollerSpeedForward;
    private boolean mMotorRollerSpeedReverse;
    private boolean mMotorPivotSpeedUp;
    private boolean mMotorPivotSpeedDown;
    private boolean mFinalCountDown;
    private boolean mHarvesterUp;
    private boolean mHarvesterDown;

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
        mMotorTiltSpeed = mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
        mMotorMoveSpeed = mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);

        mMotorRollerSpeedForward = mJoystick.getRawButton(XboxButtonMap.A_BUTTON);
        mMotorRollerSpeedReverse = mJoystick.getRawButton(XboxButtonMap.B_BUTTON);
        mHarvesterDown = mJoystick.getRawButton(XboxButtonMap.X_BUTTON);
        mHarvesterUp = mJoystick.getRawButton(XboxButtonMap.Y_BUTTON);
        mFinalCountDown = mJoystick.getRawButton(XboxButtonMap.START_BUTTON);
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

    @Override
    public double getScaleMoveSpeed()
    {
        return mMotorMoveSpeed;
    }

    @Override
    public double getScaleTiltSpeed()
    {

        return mMotorTiltSpeed;
    }

//    @Override
//    public boolean isHarvesterPivotUp()
//    {
//        return mMotorPivotSpeedUp;
//    }
//
//    @Override
//    public boolean isHarvesterPivotDown()
//    {
//        return mMotorPivotSpeedDown;
//    }


	@Override
    public boolean isFinalCountDown()
    {
		return mFinalCountDown;
	}


    @Override
    public boolean isHarvesterRollerForward()
    {
        return mMotorRollerSpeedForward;
    }

    @Override
    public boolean isHarvesterRollerReverse()
    {
        return mMotorRollerSpeedReverse;
    }

    @Override
    public boolean isHarvesterUp()
    {
        return mHarvesterUp;
    }

    @Override
    public boolean isHarvesterDown()
    {
        return mHarvesterDown;
    }

}
