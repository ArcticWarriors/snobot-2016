package com.snobot2016.drivetrain;

import com.snobot2016.joystick.IDriverJoystick;

import edu.wpi.first.wpilibj.CANTalon;

public class SnobotDriveTrainWithCan extends ASnobotDrivetrain
{
    public SnobotDriveTrainWithCan(
            CANTalon aLeftMotor, 
    		CANTalon aLeftMotorB, 
    		CANTalon aRightMotor, 
    		CANTalon aRightMotorB, 
            IDriverJoystick aDriverJoyStick)
    {
        super(aLeftMotor, aLeftMotorB, aRightMotor, aRightMotorB, aDriverJoyStick);
    }

    @Override
    public double getRightEncoderDistance()
    {
        return ((CANTalon) mRightMotor).getEncPosition();
    }

    @Override
    public double getLeftEncoderDistance()
    {
        return ((CANTalon) mLeftMotor).getEncPosition();
    }

}
