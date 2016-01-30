package com.snobot2016.joystick;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.RobotDrive;

import com.snobot.xlib.FlightSticksButtonMap;

public class SnobotDriveFlightStick implements IDriverJoystick {

	private Joystick mLeft;
	private Joystick mRight;
	private double mRightSpeed;
	private double mLeftSpeed;
	
	public void init() {
		
		mLeft = new Joystick(1);
		mRight = new Joystick(2);
	}

	@Override
	public void update() 
	{
		
		mLeftSpeed = mLeft.getY();
        mRightSpeed = mRight.getY();
          
	}

	@Override
	public void control() {
		
	}

	@Override
	public void rereadPreferences() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getRightSpeed() {
		
		return mRightSpeed;
	
	}

	@Override
	public double getLeftSpeed() {
		
		return mLeftSpeed;
	
	}
	

	
}
