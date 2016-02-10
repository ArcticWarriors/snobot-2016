package com.snobot2016;

import com.snobot.xlib.PropertyManager.BooleanProperty;
import com.snobot.xlib.PropertyManager.DoubleProperty;
import com.snobot.xlib.PropertyManager.IntegerProperty;
import com.snobot.xlib.PropertyManager.StringProperty;

import edu.wpi.first.wpilibj.RobotBase;

public class Properties2016
{
    // Joysticks
    public static final IntegerProperty sDRIVER_JOYSTICK_PORT = new IntegerProperty("DriverJoystickPort", 0);
    public static final IntegerProperty sOPERATOR_JOYSTICK_PORT = new IntegerProperty("OperatorJoystickPort", 1);
    public static final IntegerProperty sDRIVER_JOYSTICK_PORT2 = new IntegerProperty("DriverJoystickPort2", 2);

    // Speed Controller
    public static final IntegerProperty sDRIVER_LEFT_MOTOR_PORT = new IntegerProperty("DriverLeftMotor", 0);
    public static final IntegerProperty sDRIVER_RIGHT_MOTOR_PORT = new IntegerProperty("DriverRightMotor", 1);
    public static final IntegerProperty sSCALE_MOVE_MOTOR_PORT = new IntegerProperty("ScaleMoveMotor", 2);
    public static final IntegerProperty sSCALE_TILT_MOTOR_PORT = new IntegerProperty("ScaleTiltMotor", 3);
    public static final IntegerProperty sHARVESTER_ROLLER_MOTOR_PORT = new IntegerProperty("HarvesterRollerMotor", 4);
    public static final IntegerProperty sHARVESTER_PIVOT_MOTOR_PORT = new IntegerProperty("HarvesterPivotMotor", 5);

    // Digital
    public static final IntegerProperty sLEFT_DRIVE_ENCODER_PORT_A = new IntegerProperty("LeftDriveEncoderA", 0);
    public static final IntegerProperty sLEFT_DRIVE_ENCODER_PORT_B = new IntegerProperty("LeftDriveEncoderB", 1);
    public static final IntegerProperty sRIGHT_DRIVE_ENCODER_PORT_A = new IntegerProperty("RightDriveEncoderA", 2);
    public static final IntegerProperty sRIGHT_DRIVE_ENCODER_PORT_B = new IntegerProperty("RightDriveEncoderB", 3);

    // Analog
    public static final IntegerProperty sGYRO_SENSOR_PORT = new IntegerProperty("GyroSensor", 1);
    public static final IntegerProperty sHARVESTER_POT_PORT = new IntegerProperty("HarvesterPotPort", 2);
    public static final DoubleProperty sMIN_HARVESTER_POT_VOLTAGE = new DoubleProperty("MinPotVoltage", 1);
    public static final DoubleProperty sMAX_HARVESTER_POT_VOLTAGE = new DoubleProperty("MaxPotVoltage", 4);
    public static final IntegerProperty sSCALE_POT_PORT = new IntegerProperty("Scaling Potentiometer", 2);

    // Relay
    public static final IntegerProperty sLIGHT_RELAY = new IntegerProperty("LightRelay", 1);

    // Camera
    public static final StringProperty sCAMERA_HOST_IP = new StringProperty("CameraHostIP", "10.1.74.11");
    public static final BooleanProperty sENABLE_CAMERA;

    // Autonomous
    public static final StringProperty sAUTON_DIRECTORY;
    public static final DoubleProperty sAUTON_SETUP_OVERHANG = new DoubleProperty("AutonSetupOverhange", 2);
    public static final StringProperty sDEFENSE_AUTON_DIRECTORY = new StringProperty("DefenseAutonDir",
            "../../RobotCode/snobot2016/resources/Autonomous/RealAutonomousModes/DefenseAutons");
    public static final StringProperty sPOST_DEFENSE_AUTON_DIRECTORY = new StringProperty("AutonThingsToDoDir",
            "../../RobotCode/snobot2016/resources/Autonomous/RealAutonomousModes/AutonomousThingsToDo");
    public static final String sDEFENSE_AUTON_TABLE = "DefenseAutonTable";
    public static final String sPOST_DEFENSE_AUTON_TABLE = "PostDefenseAutonTable";

    // Logger
    public static final IntegerProperty sLOG_COUNT = new IntegerProperty("LogCount", 25);
    public static final StringProperty sLOG_FILE_PATH;

    // Scaler Pot
    public static final DoubleProperty sSCALE_HIGH_ANGLE = new DoubleProperty("Maximum Angle", 90);
    public static final DoubleProperty sSCALE_LOW_ANGLE = new DoubleProperty("Minimum Angle", 0);
    public static final DoubleProperty sSCALE_HIGH_VOLTAGE = new DoubleProperty("Maximum Voltage", 3.0);
    public static final DoubleProperty sSCALE_LOW_VOLTAGE = new DoubleProperty("Minimum Voltage", 1.0);

    static
    {
        if (RobotBase.isSimulation())
        {
            System.out.println("Using simulation constants");
            sENABLE_CAMERA = new BooleanProperty("EnableCamera", false);
            sAUTON_DIRECTORY = new StringProperty("AutonomousDir", "../../RobotCode/snobot2016/resources/");
            sLOG_FILE_PATH = new StringProperty("LogFilePath", "logs/");
        }
        else
        {
            System.out.println("Using tactical constants");
            sENABLE_CAMERA = new BooleanProperty("EnableCamera", true);
            sAUTON_DIRECTORY = new StringProperty("AutonomousDir", "/home/lvuser/snobot2016/auton/");
            sLOG_FILE_PATH = new StringProperty("LogFilePath", "/u/logs/"); // TODO
                                                                            // CHECK
                                                                            // IF
                                                                            // THIS
                                                                            // IS
                                                                            // ACTUALLY
                                                                            // USB
                                                                            // DRIVE
                                                                            // ON
                                                                            // ROBORIO
        }
    }

}
