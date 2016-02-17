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
        //////////////////////////////
        // Drivetrain
        //////////////////////////////
        {
            double drivetrainSpeed = 3.8 * 12;
            AnalogWrapper gyro;

            if (Properties2016.sUSE_SPI_GYRO)
            {
                gyro = SensorActuatorRegistry.get().getAnalog().get(100);
            }
            else
            {
                gyro = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sGYRO_SENSOR_PORT);
            }
            gyro.setName("Gyro");
            
            if (Properties2016.sIS_REAL_ROBOT)
            {
                SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_LEFT_A_PORT);
                SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_RIGHT_A_PORT);
                SpeedControllerWrapper leftDriveMotorB = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_LEFT_B_PORT);
                SpeedControllerWrapper rightDriveMotorB = SensorActuatorRegistry.get().getCanSpeedControllers().get(Properties2016.sDRIVE_CAN_RIGHT_B_PORT);

                leftDriveMotor.setName("Left Drive (A)");
                leftDriveMotorB.setName("Left Drive (B)");
                rightDriveMotor.setName("Right Drive (A)");
                rightDriveMotorB.setName("Right Drive (B)");

                leftDriveMotor.setMotorParameters(drivetrainSpeed);
                rightDriveMotor.setMotorParameters(-drivetrainSpeed);
            }
            else
            {
                SpeedControllerWrapper leftDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_LEFT_MOTOR_PORT);
                SpeedControllerWrapper rightDriveMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sDRIVER_RIGHT_MOTOR_PORT);
                EncoderWrapper leftDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A, Properties2016.sLEFT_DRIVE_ENCODER_PORT_B);
                EncoderWrapper rightDriveEncoder = SensorActuatorRegistry.get().getEncoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A, Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B);

                leftDriveMotor.setName("Left Drive");
                rightDriveMotor.setName("Right Drive");
                leftDriveEncoder.setName("Left Drive");
                rightDriveEncoder.setName("Right Drive");

                leftDriveEncoder.setSpeedController(leftDriveMotor);
                rightDriveEncoder.setSpeedController(rightDriveMotor);
                leftDriveEncoder.setDistancePerTick(Properties2016.sLEFT_ENCODER_DIST_PER_PULSE.getValue());
                rightDriveEncoder.setDistancePerTick(Properties2016.sRIGHT_ENCODER_DIST_PER_PULSE.getValue());

                leftDriveMotor.setMotorParameters(drivetrainSpeed);
                rightDriveMotor.setMotorParameters(-drivetrainSpeed);

                TankDriveGyroSimulator gyroSim = new TankDriveGyroSimulator(leftDriveEncoder, rightDriveEncoder, gyro);
                mSimulatorComponenets.add(gyroSim);

            }
        }
        
        
        //////////////////////////////
        // Scaler
        //////////////////////////////
        {
            SpeedControllerWrapper scaleLiftMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sSCALE_MOVE_MOTOR_PORT);
            SpeedControllerWrapper scaleTiltMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sSCALE_TILT_MOTOR_PORT);

            AnalogWrapper scaleTiltPot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sSCALE_POT_PORT);
            AnalogWrapper scaleLiftPot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sEXTENSION_POT_PORT);

            scaleLiftMotor.setName("Scale (Lift)");
            scaleTiltMotor.setName("Scale (Tilt)");
            scaleTiltPot.setName("Scale Pot");
            scaleLiftPot.setName("Scale Lift");

            scaleTiltMotor.setMotorParameters(30); // Degrees / second
            scaleTiltMotor.setPosition(30);
            scaleLiftMotor.setMotorParameters(10);

            PotentiometerSimulator scaleTiltPotSim = new PotentiometerSimulator(scaleTiltPot, scaleTiltMotor);
            mSimulatorComponenets.add(scaleTiltPotSim);

            double scalePotThrow = Properties2016.sSCALE_HIGH_ANGLE.getValue() - Properties2016.sSCALE_LOW_ANGLE.getValue();
            double scalePotMinVoltage = Properties2016.sSCALE_LOW_VOLTAGE.getValue();
            double scalePotMaxVoltage = Properties2016.sSCALE_HIGH_VOLTAGE.getValue();
            scaleTiltPotSim.setParameters(scalePotThrow, scalePotMinVoltage, scalePotMaxVoltage);

            // Scaling Lifting Potentiometer
            PotentiometerSimulator scaleLiftPotSim = new PotentiometerSimulator(scaleLiftPot, scaleLiftMotor);
            mSimulatorComponenets.add(scaleLiftPotSim);

            double scaleLiftPotMinVoltage = Properties2016.sMIN_SCALE_EXTENSION_POT_VOLTAGE.getValue();
            double scaleLiftPotMaxVoltage = Properties2016.sMAX_SCALE_EXTENSION_POT_VOLTAGE.getValue();
            scaleLiftPotSim.setParameters(100, scaleLiftPotMinVoltage, scaleLiftPotMaxVoltage);
        }

        //////////////////////////////
        // Intake
        //////////////////////////////
        {
            SpeedControllerWrapper intakeMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sHARVESTER_ROLLER_MOTOR_PORT);
            SpeedControllerWrapper intakeTiltMotor = SensorActuatorRegistry.get().getSpeedControllers().get(Properties2016.sHARVESTER_PIVOT_MOTOR_PORT);
            AnalogWrapper intakePot = SensorActuatorRegistry.get().getAnalog().get(Properties2016.sHARVESTER_POT_PORT);
            PotentiometerSimulator intakePotSim = new PotentiometerSimulator(intakePot, intakeTiltMotor);

            intakePot.setName("Intake Pot");
            intakeMotor.setName("Intake (Roller)");
            intakeTiltMotor.setName("Intake (Tilt)");
            intakeTiltMotor.setMotorParameters(120);

            double intakePotMinVoltage = Properties2016.sMIN_HARVESTER_POT_VOLTAGE.getValue();
            double intakePotMaxVoltage = Properties2016.sMAX_HARVESTER_POT_VOLTAGE.getValue();
            intakePotSim.setParameters(100, intakePotMinVoltage, intakePotMaxVoltage);

            mSimulatorComponenets.add(intakePotSim);
        }

        //////////////////////////////
        // Light
        //////////////////////////////
        {
            RelayWrapper lightRelay = SensorActuatorRegistry.get().getRelays().get(Properties2016.sLIGHT_RELAY);
            lightRelay.setName("Cam. Light");
        }
    }

}
