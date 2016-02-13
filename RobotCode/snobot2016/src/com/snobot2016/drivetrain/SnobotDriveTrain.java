package com.snobot2016.drivetrain;

import com.snobot2016.Properties2016;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IDriverJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotDriveTrain implements IDriveTrain
{
    private SpeedController mLeftMotor;
    private SpeedController mRightMotor;
    private IDriverJoystick mDriveJoystick;
    private RobotDrive mRobotDrive;

    private Encoder mLeftEncoder;
    private Encoder mRightEncoder;

    public SnobotDriveTrain(SpeedController aLeftMotor, SpeedController aRightMotor, Encoder aLeftEncoder, Encoder aRightEncoder,
            IDriverJoystick aDriverJoyStick)
    {
        mLeftMotor = aLeftMotor;
        mRightMotor = aRightMotor;
        mLeftEncoder = aLeftEncoder;
        mRightEncoder = aRightEncoder;
        mDriveJoystick = aDriverJoyStick;
        
        mRobotDrive = new RobotDrive(aLeftMotor, aRightMotor);

        mLeftEncoder.setDistancePerPulse(Properties2016.sLEFT_ENCODER_DIST_PER_PULSE.getValue());
        mRightEncoder.setDistancePerPulse(Properties2016.sRIGHT_ENCODER_DIST_PER_PULSE.getValue());
        mRobotDrive.setSafetyEnabled(false);
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
    	
        if (mDriveJoystick.isArcadeMode())
        {
            mRobotDrive.arcadeDrive(mDriveJoystick.getArcadePower(), mDriveJoystick.getArcadeTurn());
        }
        else
        {
    		mRobotDrive.tankDrive(-mDriveJoystick.getLeftSpeed(), -mDriveJoystick.getRightSpeed());
        }

    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashBoardNames.sLEFT_DRIVE_MOTOR_ENCODER, mLeftEncoder.getDistance());
        SmartDashboard.putNumber(SmartDashBoardNames.sRIGHT_DRIVE_MOTOR_ENCODER, mRightEncoder.getDistance());
        SmartDashboard.putNumber(SmartDashBoardNames.sLEFT_DRIVE_MOTOR_SPEED, mLeftMotor.get());
        SmartDashboard.putNumber(SmartDashBoardNames.sRIGHT_DRIVE_MOTOR_SPEED, mRightMotor.get());
    }

    @Override
    public void updateLog()
    {

    }

    @Override
    public void stop()
    {
        setLeftRightSpeed(0, 0);

    }

    @Override
    public void setLeftRightSpeed(double left, double right)
    {
    	mRobotDrive.setLeftRightMotorOutputs(left, right);
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
