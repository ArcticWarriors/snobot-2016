package com.snobot.simulator.robot_sim;

import com.snobot.simulator.ASimulator;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.AnalogWrapper;
import com.snobot.simulator.module_wrapper.EncoderWrapper;
import com.snobot.simulator.module_wrapper.SpeedControllerWrapper;
import com.snobot.simulator.module_wrapper.TankDriveGyroSimulator;
import com.snobot2016.Properties2016;

public class Snobot2016Simulator extends ASimulator
{
    public Snobot2016Simulator()
    {
        // Speed Controllers
        SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_LEFT_MOTOR_PORT.getValue());
        SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_RIGHT_MOTOR_PORT.getValue());
        leftDriveMotor.setName("Left Drive");
        rightDriveMotor.setName("Right Drive");
        
        // Encoders
        EncoderWrapper leftDriveEncoder  = SensorActuatorRegistry.get().getEncoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(),  Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());
        EncoderWrapper rightDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(), Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());
        leftDriveEncoder.setName("Left Drive");
        rightDriveEncoder.setName("Right Drive");
        leftDriveEncoder.setSpeedController(leftDriveMotor);
        rightDriveEncoder.setSpeedController(rightDriveMotor);

        // Analaog
        AnalogWrapper gyro = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sGYRO_SENSOR_PORT.getValue());
        gyro.setName("Gyro");

        // Set Parameters
        leftDriveMotor.setMotorParameters(12);
        rightDriveMotor.setMotorParameters(12);
        
        TankDriveGyroSimulator gyroSim = new TankDriveGyroSimulator(leftDriveEncoder, rightDriveEncoder, gyro);
        mSimulatorComponenets.add(gyroSim);
    }

}
