package com.snobot2016;

import com.snobot.xlib.PropertyManager.IntegerProperty;
import com.snobot.xlib.PropertyManager.StringProperty;

public class Properties2016
{
    // Joysticks
    public static final IntegerProperty sDRIVER_JOYSTICK_PORT = new IntegerProperty("DriverJoystickPort", 0);

    // Speed Controller
    public static final IntegerProperty sDRIVER_LEFT_MOTOR_PORT = new IntegerProperty("DriverLeftMotor", 0);
    public static final IntegerProperty sDRIVER_RIGHT_MOTOR_PORT = new IntegerProperty("DriverRightMotor", 1);

    // Encoder
    public static final IntegerProperty sLEFT_DRIVE_ENCODER_PORT_A = new IntegerProperty("LeftDriveEncoderA", 0);
    public static final IntegerProperty sLEFT_DRIVE_ENCODER_PORT_B = new IntegerProperty("LeftDriveEncoderB", 1);
    public static final IntegerProperty sRIGHT_DRIVE_ENCODER_PORT_A = new IntegerProperty("RightDriveEncoderA", 2);
    public static final IntegerProperty sRIGHT_DRIVE_ENCODER_PORT_B = new IntegerProperty("RightDriveEncoderB", 3);

    //Gyro
    public static final IntegerProperty sGYRO_SENSOR_PORT = new IntegerProperty("GyroSensor", 1);
    
    //Camera
    public static final StringProperty sCAMERA_HOST_IP = new StringProperty("CameraHostIP", "10.1.74.11");
    
    //Logger
    public static final IntegerProperty sLOG_COUNT = new IntegerProperty("LogCount", 25);
    public static final StringProperty sLOG_FILE_PATH = new StringProperty("LogFilePath", "logs/");

}
