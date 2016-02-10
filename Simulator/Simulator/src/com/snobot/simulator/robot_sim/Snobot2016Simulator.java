package com.snobot.simulator.robot_sim;

import com.snobot.simulator.ASimulator;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.AnalogWrapper;
import com.snobot.simulator.module_wrapper.EncoderWrapper;
import com.snobot.simulator.module_wrapper.PotentiometerSimulator;
import com.snobot.simulator.module_wrapper.RelayWrapper;
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
        SpeedControllerWrapper scaleLiftMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sSCALE_MOVE_MOTOR_PORT.getValue());
        SpeedControllerWrapper scaleTiltMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sSCALE_TILT_MOTOR_PORT.getValue());
        SpeedControllerWrapper intakeMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sHARVESTER_ROLLER_MOTOR_PORT.getValue());
        SpeedControllerWrapper intakeTiltMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sHARVESTER_PIVOT_MOTOR_PORT.getValue());

        leftDriveMotor.setName("Left Drive");
        rightDriveMotor.setName("Right Drive");
        scaleLiftMotor.setName("Scale (Lift)");
        scaleTiltMotor.setName("Scale (Tilt)");
        intakeMotor.setName("Intake (Roller)");
        intakeTiltMotor.setName("Intake (Tilt)");

        // Encoders
        EncoderWrapper leftDriveEncoder  = SensorActuatorRegistry.get().getEncoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(),  Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());
        EncoderWrapper rightDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(), Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());
        leftDriveEncoder.setName("Left Drive");
        rightDriveEncoder.setName("Right Drive");
        leftDriveEncoder.setSpeedController(leftDriveMotor);
        rightDriveEncoder.setSpeedController(rightDriveMotor);

        // Relay
        RelayWrapper lightRelay = SensorActuatorRegistry.get().getRelays().get(Properties2016.sLIGHT_RELAY.getValue());
        lightRelay.setName("Cam. Light");

        // Analaog
        AnalogWrapper gyro = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sGYRO_SENSOR_PORT.getValue());
        AnalogWrapper scalePot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sSCALE_POT_PORT.getValue());
        gyro.setName("Gyro");
        scalePot.setName("Scale Pot");

        // Set Parameters
        double drivetrainSpeed = 3.8 * 12;
        leftDriveMotor.setMotorParameters(drivetrainSpeed);
        rightDriveMotor.setMotorParameters(-drivetrainSpeed);
        scaleTiltMotor.setMotorParameters(30); // Degrees / second

        TankDriveGyroSimulator gyroSim = new TankDriveGyroSimulator(leftDriveEncoder, rightDriveEncoder, gyro);
        mSimulatorComponenets.add(gyroSim);

        PotentiometerSimulator scalePotSim = new PotentiometerSimulator(scalePot, scaleTiltMotor);
        mSimulatorComponenets.add(scalePotSim);

        double scalePotThrow = Properties2016.sSCALE_HIGH_ANGLE.getValue() - Properties2016.sSCALE_LOW_ANGLE.getValue();
        double scalePotMinVoltage = Properties2016.sSCALE_LOW_VOLTAGE.getValue();
        double scalePotMaxVoltage = Properties2016.sSCALE_HIGH_VOLTAGE.getValue();
        scalePotSim.setParameters(scalePotThrow, scalePotMinVoltage, scalePotMaxVoltage);
    }

}
