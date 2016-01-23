package com.snobot2016;

import com.snobot.xlib.PropertyManager.IntegerProperty;

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
}
