package com.snobot2016.drivetrain;

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IDriverJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotDriveTrain implements IDriveTrain
{
    private SpeedController mLeftMotor;
    private SpeedController mRightMotor;
    private IDriverJoystick mXbaxJoystick;
    private IDriverJoystick mFlightStick;
    
    private Encoder mLeftEncoder;
    private Encoder mRightEncoder;

    private boolean mXbax = true;
    
    public SnobotDriveTrain(SpeedController aLeftMotor, SpeedController aRightMotor, Encoder aLeftEncoder, Encoder aRightEncoder,
            IDriverJoystick aJoyStick, IDriverJoystick aFlightStick)
    {
        mLeftMotor = aLeftMotor;
        mRightMotor = aRightMotor;
        mLeftEncoder = aLeftEncoder;
        mRightEncoder = aRightEncoder;
        mXbaxJoystick = aJoyStick;
        mFlightStick = aFlightStick;
    }

    @Override
    public void init()
    {
    }

    @Override
    public void update()
    {
    	mXbax = SmartDashboard.getBoolean(SmartDashBoardNames.sSNOBOT_FLIGHTSTICKS, true);

    }

    @Override
    public void control()
    {
    	if (mXbax)
    	{
    		setLeftRightSpeed(mXbaxJoystick.getLeftSpeed(), mXbaxJoystick.getRightSpeed());
    	}
    	else
    	{
    		setLeftRightSpeed(mFlightStick.getLeftSpeed(), mFlightStick.getRightSpeed());
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
        // TODO Auto-generated method stub
        SmartDashboard.putNumber(SmartDashBoardNames.sLEFT_DRIVE_MOTOR_ENCODER, mLeftEncoder.getDistance());

        SmartDashboard.putNumber(SmartDashBoardNames.sRIGHT_DRIVE_MOTOR_ENCODER, mRightEncoder.getDistance());

        SmartDashboard.putNumber(SmartDashBoardNames.sLEFT_DRIVE_MOTOR_SPEED, mLeftMotor.get());

        SmartDashboard.putNumber(SmartDashBoardNames.sRIGHT_DRIVE_MOTOR_SPEED, mRightMotor.get());
        
        SmartDashboard.putBoolean(SmartDashBoardNames.sSNOBOT_FLIGHTSTICKS, mXbax);
    }

    @Override
    public void updateLog()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop()
    {
        setLeftRightSpeed(0, 0);

    }

    @Override
    public void setLeftRightSpeed(double left, double right)
    {
        mLeftMotor.set(left);
        mRightMotor.set(right);
    }

    @Override
    public double getRightEncoderDistance()
    {
        return mRightEncoder.getDistance();
    }

    @Override
    public double getLeftEncoderDistance()
    {
        return mLeftEncoder.getDistance();
    }

}
