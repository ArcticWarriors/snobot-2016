package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class StupidDriveStraight extends Command{
	private Timer mTimer;
	private IDriveTrain mDriveTrain;
	private double mTime;
	private double mSpeed;
	
	public StupidDriveStraight(IDriveTrain aDriveTrain, double aTime, double aSpeed)
	{
		mTimer = new Timer();
		mTime = aTime;
		mDriveTrain = aDriveTrain;
		mSpeed = aSpeed;
	}
	@Override
	protected void initialize() {
		mTimer.start();
	}

	@Override
	protected void execute() {
		mDriveTrain.setLeftRightSpeed(mSpeed, mSpeed);
		
	}

	@Override
	protected boolean isFinished() {
		if(mTimer.get() >= mTime)
		{
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		mTimer.stop();
		mDriveTrain.setLeftRightSpeed(0, 0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
