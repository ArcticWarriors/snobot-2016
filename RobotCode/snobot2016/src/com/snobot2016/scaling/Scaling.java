package com.snobot2016.scaling;

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Scaling implements IScaling
{
    private SpeedController mScaleMoveMotor;
    private SpeedController mScaleTiltMotor;
    private IOperatorJoystick mJoystick;
    private double mMoveSpeed;
    private double mTiltSpeed;
    private boolean mFinalCountDown;
    private boolean mAmIClimbing;
    private Timer mTimer;
    
    
    public Scaling(SpeedController aScaleMoveMotor, SpeedController aScaleTiltMotor,IOperatorJoystick aOperatorJoystick)
    {
        mScaleMoveMotor = aScaleMoveMotor;
        mScaleTiltMotor = aScaleTiltMotor;
        mJoystick = aOperatorJoystick; 
        mTimer = new Timer();
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
        setScaleSpeedMove(mJoystick.getScaleMoveSpeed());
        mMoveSpeed = mJoystick.getScaleMoveSpeed();
        setScaleSpeedTilt(mJoystick.getScaleTiltSpeed());
        mTiltSpeed = mJoystick.getScaleTiltSpeed();
    
        if (mJoystick.isFinalCountDown())
        {
        	mAmIClimbing = true;
        	mTimer.start();
        }
        if (mAmIClimbing)
        {
        	setScaleSpeedMove (1);
        	mTimer.get();
        }
        if (mTimer.get() >10)
       {
    	   mTimer.stop();
    	   setScaleSpeedMove (0);
    	   mAmIClimbing = false;
       }
        
    }

    @Override
    public void rereadPreferences()
    {
        
    }

    @Override
    public void updateSmartDashboard()
    {
    	 SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_MOVE_MOTOR, mMoveSpeed);  
    	 SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_TILT_MOTOR, mTiltSpeed);
    	 SmartDashboard.putNumber(SmartDashBoardNames.sTIMER, mTimer.get());
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
    public boolean extendUpWall()
    {
        return false;
    }

    @Override
    public boolean pullUpWall()
    {
        return false;
    }

    @Override
    public boolean lowerDownWall()
    {
        return false;
    }
    
    public void setScaleSpeedMove(double aSpeedMove)
    {
        mScaleMoveMotor.set(aSpeedMove);
    }

    public void setScaleSpeedTilt(double aSpeedTilt)
    {
        mScaleTiltMotor.set(aSpeedTilt);
	}
}
