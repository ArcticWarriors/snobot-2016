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
        SpeedControllerWrapper leftDriveMotor;
        SpeedControllerWrapper rightDriveMotor;

        // Speed Controllers
        if (Properties2016.sIS_REAL_ROBOT)
        {
            leftDriveMotor = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_LEFT_A_PORT);
            rightDriveMotor = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_RIGHT_A_PORT);

            SpeedControllerWrapper leftDriveMotorB = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_LEFT_B_PORT);
            SpeedControllerWrapper rightDriveMotorB = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_RIGHT_B_PORT);

            leftDriveMotor.setName("Left Drive (A)");
            leftDriveMotorB.setName("Left Drive (B)");
            rightDriveMotor.setName("Right Drive (A)");
            rightDriveMotorB.setName("Right Drive (B)");
        }
        else
        {
            leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_LEFT_MOTOR_PORT);
            rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_RIGHT_MOTOR_PORT);

            leftDriveMotor.setName("Left Drive");
            rightDriveMotor.setName("Right Drive");
        }
        SpeedControllerWrapper scaleLiftMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sSCALE_MOVE_MOTOR_PORT);
        SpeedControllerWrapper scaleTiltMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sSCALE_TILT_MOTOR_PORT);
        SpeedControllerWrapper intakeMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sHARVESTER_ROLLER_MOTOR_PORT);
        SpeedControllerWrapper intakeTiltMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sHARVESTER_PIVOT_MOTOR_PORT);

        scaleLiftMotor.setName("Scale (Lift)");
        scaleTiltMotor.setName("Scale (Tilt)");
        intakeMotor.setName("Intake (Roller)");
        intakeTiltMotor.setName("Intake (Tilt)");

        // Encoders
        EncoderWrapper leftDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A,
                Properties2016.sLEFT_DRIVE_ENCODER_PORT_B);
        EncoderWrapper rightDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A,
                Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B);
        leftDriveEncoder.setName("Left Drive");
        rightDriveEncoder.setName("Right Drive");
        leftDriveEncoder.setSpeedController(leftDriveMotor);
        rightDriveEncoder.setSpeedController(rightDriveMotor);
        leftDriveEncoder.setDistancePerTick(Properties2016.sLEFT_ENCODER_DIST_PER_PULSE.getValue());
        rightDriveEncoder.setDistancePerTick(Properties2016.sRIGHT_ENCODER_DIST_PER_PULSE.getValue());

        // Relay
        RelayWrapper lightRelay = SensorActuatorRegistry.get().getRelays().get(Properties2016.sLIGHT_RELAY);
        lightRelay.setName("Cam. Light");

        // Analaog
        AnalogWrapper gyro = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sGYRO_SENSOR_PORT);
        AnalogWrapper scaleTiltPot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sSCALE_POT_PORT);
        AnalogWrapper scaleLiftPot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sEXTENSION_POT_PORT);
        AnalogWrapper intakePot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sHARVESTER_POT_PORT);
        gyro.setName("Gyro");
        scaleTiltPot.setName("Scale Pot");
        scaleLiftPot.setName("Scale Lift");
        intakePot.setName("Intake Pot");

        // Set Parameters
        double drivetrainSpeed = 3.8 * 12;
        leftDriveMotor.setMotorParameters(drivetrainSpeed);
        rightDriveMotor.setMotorParameters(-drivetrainSpeed);
        scaleTiltMotor.setMotorParameters(30); // Degrees / second
        scaleTiltMotor.setPosition(30);
        intakeTiltMotor.setMotorParameters(120);
        scaleLiftMotor.setMotorParameters(10);

        TankDriveGyroSimulator gyroSim = new TankDriveGyroSimulator(leftDriveEncoder, rightDriveEncoder, gyro);
        mSimulatorComponenets.add(gyroSim);

        // Scaling Potentiometer
        PotentiometerSimulator scaleTiltPotSim = new PotentiometerSimulator(scaleTiltPot, scaleTiltMotor);
        mSimulatorComponenets.add(scaleTiltPotSim);

        double scalePotThrow = Properties2016.sSCALE_HIGH_ANGLE.getValue() - Properties2016.sSCALE_LOW_ANGLE.getValue();
        double scalePotMinVoltage = Properties2016.sSCALE_LOW_VOLTAGE.getValue();
        double scalePotMaxVoltage = Properties2016.sSCALE_HIGH_VOLTAGE.getValue();
        scaleTiltPotSim.setParameters(scalePotThrow, scalePotMinVoltage, scalePotMaxVoltage);

        // Scaling Lifting Potentiometer
        PotentiometerSimulator scaleLiftPotSim = new PotentiometerSimulator(scaleLiftPot, scaleLiftMotor);
        mSimulatorComponenets.add(scaleLiftPotSim);

        double scaleLiftPotMinVoltage = Properties2016.sMIN_EXTENSION_POT_VOLTAGE.getValue();
        double scaleLiftPotMaxVoltage = Properties2016.sMAX_EXTENSION_POT_VOLTAGE.getValue();
        scaleLiftPotSim.setParameters(100, scaleLiftPotMinVoltage, scaleLiftPotMaxVoltage);

        // Harvester Potentiometer
        PotentiometerSimulator intakePotSim = new PotentiometerSimulator(intakePot, intakeTiltMotor);
        mSimulatorComponenets.add(intakePotSim);

        double intakePotMinVoltage = Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue();
        double intakePotMaxVoltage = Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue();
        intakePotSim.setParameters(100, intakePotMinVoltage, intakePotMaxVoltage);
    }

}
