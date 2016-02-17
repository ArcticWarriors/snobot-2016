package com.snobot2016.drivetrain;

import com.snobot2016.Properties2016;
import com.snobot2016.joystick.IDriverJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class SnobotDriveTrainWithEncoders extends ASnobotDrivetrain
{
    private final Encoder mLeftEncoder;
    private final Encoder mRightEncoder;

    public SnobotDriveTrainWithEncoders(
    		SpeedController aLeftMotor, 
    		SpeedController aLeftMotorB, 
    		SpeedController aRightMotor, 
    		SpeedController aRightMotorB, 
    		Encoder aLeftEncoder, 
    		Encoder aRightEncoder,
            IDriverJoystick aDriverJoyStick)
    {
        super(aLeftMotor, aLeftMotorB, aRightMotor, aRightMotorB, aDriverJoyStick);

        mLeftEncoder = aLeftEncoder;
        mRightEncoder = aRightEncoder;
        
        mLeftEncoder.setDistancePerPulse(Properties2016.sLEFT_ENCODER_DIST_PER_PULSE.getValue());
        mRightEncoder.setDistancePerPulse(Properties2016.sRIGHT_ENCODER_DIST_PER_PULSE.getValue());

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
