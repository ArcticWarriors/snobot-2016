package com.snobot.simulator.robot_sim;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.EncoderWrapper;
import com.snobot.simulator.module_wrapper.SpeedControllerWrapper;
import com.snobot2016.Properties2016;

public class Snobot2016Simulator
{
    public Snobot2016Simulator()
    {
        // Encoders
        EncoderWrapper leftDriveEnc = SensorActuatorRegistry.get().getEncoder(
                Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(),
                Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());
        EncoderWrapper rightDriveEnc = SensorActuatorRegistry.get().getEncoder(
                Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(),
                Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());
        leftDriveEnc.setName("Left Drive");
        rightDriveEnc.setName("Right Drive");
        
        //Speed Controllers
        SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_LEFT_MOTOR_PORT.getValue());
        SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_RIGHT_MOTOR_PORT.getValue());

        leftDriveMotor.setName("Left Drive");
        rightDriveMotor.setName("Right Drive");

        // Hook up sensors
        leftDriveEnc.setSpeedController(leftDriveMotor);
        rightDriveEnc.setSpeedController(rightDriveMotor);
    }

}
