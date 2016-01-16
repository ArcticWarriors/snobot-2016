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
    private IDriverJoystick mJoystick;

    private Encoder mLeftEncoder;
    private Encoder mRightEncoder;

    public SnobotDriveTrain(SpeedController aLeftMotor, SpeedController aRightMotor, Encoder aLeftEncoder, Encoder aRightEncoder,
            IDriverJoystick aJoyStick)
    {
        mLeftMotor = aLeftMotor;
        mRightMotor = aRightMotor;
        mLeftEncoder = aLeftEncoder;
        mRightEncoder = aRightEncoder;
        mJoystick = aJoyStick;
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void update()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void control()
    {
        setLeftRightSpeed(mJoystick.getLeftSpeed(), mJoystick.getRightSpeed());
    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getLeftEncoderDistance()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
