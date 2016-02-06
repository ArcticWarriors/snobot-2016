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

    public SnobotOperatorJoystick(Joystick aJoystick)
    {
        mJoystick = aJoystick;
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void update()
    {
        mMotorTiltSpeed = mJoystick.getRawAxis(XboxButtonMap.RIGHT_Y_AXIS);
        mMotorMoveSpeed = mJoystick.getRawAxis(XboxButtonMap.LEFT_Y_AXIS);

        mMotorRollerSpeedForward = mJoystick.getRawButton(XboxButtonMap.A_BUTTON);
        mMotorRollerSpeedReverse = mJoystick.getRawButton(XboxButtonMap.B_BUTTON);
        mMotorPivotSpeedUp = mJoystick.getRawButton(XboxButtonMap.X_BUTTON);
        mMotorPivotSpeedDown = mJoystick.getRawButton(XboxButtonMap.Y_BUTTON);
        mFinalCountDown = mJoystick.getRawButton(XboxButtonMap.START_BUTTON);
    }

    @Override
    public void control()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard()
    {
        // TODO Auto-generated method stub

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
    public double getScaleMoveSpeed()
    {
        // TODO Auto-generated method stub
        return mMotorMoveSpeed;
    }

    @Override
    public double getScaleTiltSpeed()
    {

        return mMotorTiltSpeed;
    }

    @Override
    public boolean isHarvesterPivotUp()
    {
        // TODO Auto-generated method stub
        return mMotorPivotSpeedUp;
    }

    @Override
    public boolean isHarvesterPivotDown()
    {
        // TODO Auto-generated method stub
        return mMotorPivotSpeedDown;
    }


	@Override
	public boolean isFinalCountDown() {
		// TODO Auto-generated method stub
		return mFinalCountDown;
	}


    @Override
    public boolean isHarvesterRollerForward()
    {
        // TODO Auto-generated method stub
        return mMotorRollerSpeedForward;
    }

    @Override
    public boolean isHarvesterRollerReverse()
    {
        // TODO Auto-generated method stub
        return mMotorRollerSpeedReverse;
    }


}
