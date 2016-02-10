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

    // Relay
    public static final IntegerProperty sLIGHT_RELAY = new IntegerProperty("LightRelay", 1);

    // Camera
    public static final StringProperty sCAMERA_HOST_IP = new StringProperty("CameraHostIP", "10.1.74.11");
    public static final BooleanProperty sENABLE_CAMERA;

    // Autonomous
    public static final StringProperty sAUTON_DIRECTORY;
    public static final StringProperty sAUTON_DEFENSE_DIRECTORY;
    public static final StringProperty sAUTON_POST_DEFENSE_DIRECTORY;
    public static final DoubleProperty sAUTON_SETUP_OVERHANG = new DoubleProperty("AutonSetupOverhange", 2);

    // Logger
    public static final IntegerProperty sLOG_COUNT = new IntegerProperty("LogCount", 25);
    public static final StringProperty sLOG_FILE_PATH;

    // Drive path
    public static final DoubleProperty sDRIVE_PATH_KP = new DoubleProperty("DrivePathKP", 0.0174);
    public static final DoubleProperty sDRIVE_PATH_KD = new DoubleProperty("DrivePathKD", 0);
    public static final DoubleProperty sDRIVE_PATH_KV = new DoubleProperty("DrivePathKVel", .009);
    public static final DoubleProperty sDRIVE_PATH_KA = new DoubleProperty("DrivePathKAccel", 0.0037);

    // Turn Path
    public static final DoubleProperty sTURN_PATH_KP = new DoubleProperty("TurnPathKP", 0.005);
    public static final DoubleProperty sTURN_PATH_KD = new DoubleProperty("TurnPathKD", 0);
    public static final DoubleProperty sTURN_PATH_KV = new DoubleProperty("TurnPathKVel", 0.0053);
    public static final DoubleProperty sTURN_PATH_KA = new DoubleProperty("TurnPathKAccel", 0.00174);

    static
    {
        if (RobotBase.isSimulation())
        {
            System.out.println("Using simulation constants");
            sENABLE_CAMERA = new BooleanProperty("EnableCamera", false);
            sLOG_FILE_PATH = new StringProperty("LogFilePath", "logs/");

            sAUTON_DIRECTORY = new StringProperty("AutonomousDir", "../../RobotCode/snobot2016/resources/");
        }
        else
        {
            System.out.println("Using tactical constants");
            sENABLE_CAMERA = new BooleanProperty("EnableCamera", true);
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

            sAUTON_DIRECTORY = new StringProperty("AutonomousDir", "/home/lvuser/snobot2016/auton/");
        }

        sAUTON_DEFENSE_DIRECTORY = new StringProperty("AutonThingsToDoDir", 
                sAUTON_DIRECTORY + "Autonomous/RealAutonomousModes/AutonomousThingsToDo");

        sAUTON_POST_DEFENSE_DIRECTORY = new StringProperty("AutonThingsToDoDir",
                sAUTON_DIRECTORY + "Autonomous/RealAutonomousModes/AutonomousThingsToDo");
    }

}
