package com.snobot2016;

import com.snobot.xlib.PropertyManager.BooleanProperty;
import com.snobot.xlib.PropertyManager.IntegerProperty;
import com.snobot.xlib.PropertyManager.StringProperty;

import edu.wpi.first.wpilibj.RobotBase;

public class Properties2016
{
    // Joysticks
    public static final IntegerProperty sDRIVER_JOYSTICK_PORT = new IntegerProperty("DriverJoystickPort", 0);
    public static final IntegerProperty sOPERATOR_JOYSTICK_PORT = new IntegerProperty("OperatorJoystickPort", 1);
    
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
    
    //Logger
    public static final IntegerProperty sLOG_COUNT = new IntegerProperty("LogCount", 25);
    public static final StringProperty sLOG_FILE_PATH;

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
            sLOG_FILE_PATH = new StringProperty("LogFilePath", "/u/logs/"); //TODO CHECK IF THIS IS ACTUALLY USB DRIVE ON ROBORIO 
        }
    }




}
