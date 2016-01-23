package com.snobot.simulator.robot_sim;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.EncoderWrapper;
import com.snobot.simulator.module_wrapper.SpeedControllerWrapper;
import com.snobot2016.Properties2016;

public class Snobot2016Simulator
{
    public Snobot2016Simulator()
    {
        SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers()
                .get(Properties2016.sDRIVER_LEFT_MOTOR_PORT.getValue());
        EncoderWrapper leftDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(),
                Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());

        SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers()
                .get(Properties2016.sDRIVER_RIGHT_MOTOR_PORT.getValue());
        EncoderWrapper rightDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(),
                Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());

        leftDriveMotor.setMotorParameters(12);
        leftDriveEncoder.setSpeedController(leftDriveMotor);

        rightDriveMotor.setMotorParameters(12);
        rightDriveEncoder.setSpeedController(rightDriveMotor);

        leftDriveEncoder.setName("Left Drive");
        rightDriveEncoder.setName("Right Drive");

        leftDriveMotor.setName("Left Drive");
        rightDriveMotor.setName("Right Drive");
    }

}
