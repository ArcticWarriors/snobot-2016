/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.communication;

import java.nio.ByteBuffer;

import com.snobot.simulator.RobotStateSingleton;
import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.joysticks.JoystickFactory;

import edu.wpi.first.wpilibj.hal.JNIWrapper;

/**
 * JNI Wrapper for library <b>FRC_NetworkCommunications</b><br>
 */
public class FRCNetworkCommunicationsLibrary extends JNIWrapper
{
    // **************************************************
    // Our stuff
    // **************************************************
    private static RobotStateSingleton sRobotState = RobotStateSingleton.get();
    private static final JoystickFactory sJoystickFactory = JoystickFactory.get();

    // **************************************************
    // /Our stuff
    // **************************************************

    /** Module type from LoadOut.h */
    public static interface tModuleType
    {
        public static final int kModuleType_Unknown = 0x00;
        public static final int kModuleType_Analog = 0x01;
        public static final int kModuleType_Digital = 0x02;
        public static final int kModuleType_Solenoid = 0x03;
    };

    /** Target class from LoadOut.h */
    public static interface tTargetClass
    {
        public static final int kTargetClass_Unknown = 0x00;
        public static final int kTargetClass_FRC1 = 0x10;
        public static final int kTargetClass_FRC2 = 0x20;
        public static final int kTargetClass_FRC2_Analog = kTargetClass_FRC2 | FRCNetworkCommunicationsLibrary.tModuleType.kModuleType_Analog;
        public static final int kTargetClass_FRC2_Digital = kTargetClass_FRC2 | FRCNetworkCommunicationsLibrary.tModuleType.kModuleType_Digital;
        public static final int kTargetClass_FRC2_Solenoid = kTargetClass_FRC2 | FRCNetworkCommunicationsLibrary.tModuleType.kModuleType_Solenoid;
        public static final int kTargetClass_FamilyMask = 0xF0;
        public static final int kTargetClass_ModuleMask = 0x0F;
    };

    /** Resource types from UsageReporting.h */
    public static interface tResourceType
    {
        public static final int kResourceType_Controller = 0;
        public static final int kResourceType_Module = 1;
        public static final int kResourceType_Language = 2;
        public static final int kResourceType_CANPlugin = 3;
        public static final int kResourceType_Accelerometer = 4;
        public static final int kResourceType_ADXL345 = 5;
        public static final int kResourceType_AnalogChannel = 6;
        public static final int kResourceType_AnalogTrigger = 7;
        public static final int kResourceType_AnalogTriggerOutput = 8;
        public static final int kResourceType_CANJaguar = 9;
        public static final int kResourceType_Compressor = 10;
        public static final int kResourceType_Counter = 11;
        public static final int kResourceType_Dashboard = 12;
        public static final int kResourceType_DigitalInput = 13;
        public static final int kResourceType_DigitalOutput = 14;
        public static final int kResourceType_DriverStationCIO = 15;
        public static final int kResourceType_DriverStationEIO = 16;
        public static final int kResourceType_DriverStationLCD = 17;
        public static final int kResourceType_Encoder = 18;
        public static final int kResourceType_GearTooth = 19;
        public static final int kResourceType_Gyro = 20;
        public static final int kResourceType_I2C = 21;
        public static final int kResourceType_Framework = 22;
        public static final int kResourceType_Jaguar = 23;
        public static final int kResourceType_Joystick = 24;
        public static final int kResourceType_Kinect = 25;
        public static final int kResourceType_KinectStick = 26;
        public static final int kResourceType_PIDController = 27;
        public static final int kResourceType_Preferences = 28;
        public static final int kResourceType_PWM = 29;
        public static final int kResourceType_Relay = 30;
        public static final int kResourceType_RobotDrive = 31;
        public static final int kResourceType_SerialPort = 32;
        public static final int kResourceType_Servo = 33;
        public static final int kResourceType_Solenoid = 34;
        public static final int kResourceType_SPI = 35;
        public static final int kResourceType_Task = 36;
        public static final int kResourceType_Ultrasonic = 37;
        public static final int kResourceType_Victor = 38;
        public static final int kResourceType_Button = 39;
        public static final int kResourceType_Command = 40;
        public static final int kResourceType_AxisCamera = 41;
        public static final int kResourceType_PCVideoServer = 42;
        public static final int kResourceType_SmartDashboard = 43;
        public static final int kResourceType_Talon = 44;
        public static final int kResourceType_HiTechnicColorSensor = 45;
        public static final int kResourceType_HiTechnicAccel = 46;
        public static final int kResourceType_HiTechnicCompass = 47;
        public static final int kResourceType_SRF08 = 48;
        public static final int kResourceType_AnalogOutput = 49;
        public static final int kResourceType_VictorSP = 50;
        public static final int kResourceType_TalonSRX = 51;
        public static final int kResourceType_CANTalonSRX = 52;
        public static final int kResourceType_ADXL362 = 53;
        public static final int kResourceType_ADXRS450 = 54;
        public static final int kResourceType_RevSPARK = 55;
        public static final int kResourceType_MindsensorsSD540 = 56;
        public static final int kResourceType_DigitalFilter = 57;
    };

    /** Instances from UsageReporting.h */
    public static interface tInstances
    {
        public static final int kLanguage_LabVIEW = 1;
        public static final int kLanguage_CPlusPlus = 2;
        public static final int kLanguage_Java = 3;
        public static final int kLanguage_Python = 4;

        public static final int kCANPlugin_BlackJagBridge = 1;
        public static final int kCANPlugin_2CAN = 2;

        public static final int kFramework_Iterative = 1;
        public static final int kFramework_Sample = 2;
        public static final int kFramework_CommandControl = 3;

        public static final int kRobotDrive_ArcadeStandard = 1;
        public static final int kRobotDrive_ArcadeButtonSpin = 2;
        public static final int kRobotDrive_ArcadeRatioCurve = 3;
        public static final int kRobotDrive_Tank = 4;
        public static final int kRobotDrive_MecanumPolar = 5;
        public static final int kRobotDrive_MecanumCartesian = 6;

        public static final int kDriverStationCIO_Analog = 1;
        public static final int kDriverStationCIO_DigitalIn = 2;
        public static final int kDriverStationCIO_DigitalOut = 3;

        public static final int kDriverStationEIO_Acceleration = 1;
        public static final int kDriverStationEIO_AnalogIn = 2;
        public static final int kDriverStationEIO_AnalogOut = 3;
        public static final int kDriverStationEIO_Button = 4;
        public static final int kDriverStationEIO_LED = 5;
        public static final int kDriverStationEIO_DigitalIn = 6;
        public static final int kDriverStationEIO_DigitalOut = 7;
        public static final int kDriverStationEIO_FixedDigitalOut = 8;
        public static final int kDriverStationEIO_PWM = 9;
        public static final int kDriverStationEIO_Encoder = 10;
        public static final int kDriverStationEIO_TouchSlider = 11;

        public static final int kADXL345_SPI = 1;
        public static final int kADXL345_I2C = 2;

        public static final int kCommand_Scheduler = 1;

        public static final int kSmartDashboard_Instance = 1;
    };

    /**
     * Report the usage of a resource of interest. <br>
     *
     * <br>
     * $
     * 
     * @param resource
     *            one of the values in the tResourceType above (max value 51). <br>
     * @param instanceNumber
     *            an index that identifies the resource instance. <br>
     * @param context
     *            an optional additional context number for some cases (such as
     *            module number). Set to 0 to omit. <br>
     * @param feature
     *            a string to be included describing features in use on a
     *            specific resource. Setting the same resource more than once
     *            allows you to change the feature string.<br>
     *            Original signature :
     *            <code>uint32_t report(tResourceType, uint8_t, uint8_t, const char*)</code>
     */
    public static int FRCNetworkCommunicationUsageReportingReport(byte resource, byte instanceNumber, byte context, String feature)
    {
        if (resource == tResourceType.kResourceType_Solenoid)
        {
            int port = (int) instanceNumber;
            SensorActuatorRegistry.get().getSolenoids().get(port).setIsReal(true);
        }
        return 0;
    }

    public static void setNewDataSem(long mutexId)
    {

    }

    public static void FRCNetworkCommunicationObserveUserProgramStarting()
    {

    }

    public static void FRCNetworkCommunicationObserveUserProgramDisabled()
    {

    }

    public static void FRCNetworkCommunicationObserveUserProgramAutonomous()
    {

    }

    public static void FRCNetworkCommunicationObserveUserProgramTeleop()
    {

    }

    public static void FRCNetworkCommunicationObserveUserProgramTest()
    {

    }

    public static void FRCNetworkCommunicationReserve()
    {

    }

    public static HALControlWord HALGetControlWord()
    {
        return sRobotState.getControlWord();
    }

    private static native int NativeHALGetAllianceStation();

    public static HALAllianceStationID HALGetAllianceStation()
    {
        switch (NativeHALGetAllianceStation())
        {
        case 0:
            return HALAllianceStationID.Red1;
        case 1:
            return HALAllianceStationID.Red2;
        case 2:
            return HALAllianceStationID.Red3;
        case 3:
            return HALAllianceStationID.Blue1;
        case 4:
            return HALAllianceStationID.Blue2;
        case 5:
            return HALAllianceStationID.Blue3;
        default:
            return null;
        }
    }

    public static int kMaxJoystickAxes = 12;
    public static int kMaxJoystickPOVs = 12;

    public static short[] HALGetJoystickAxes(byte joystickNum)
    {
        return sJoystickFactory.get(joystickNum).getAxisValues();
    }

    public static short[] HALGetJoystickPOVs(byte joystickNum)
    {
        return sJoystickFactory.get(joystickNum).getPovValues();
    }

    public static int HALGetJoystickButtons(byte joystickNum, ByteBuffer count)
    {
        int num_buttons = sJoystickFactory.get(joystickNum).getButtonCount();
        int masked_values = sJoystickFactory.get(joystickNum).getButtonMask();

        count.clear();
        count.put((byte) num_buttons);
        count.position(0);

        return masked_values;
    }

    public static int HALSetJoystickOutputs(byte joystickNum, int outputs, short leftRumble, short rightRumble)
    {
        sJoystickFactory.get(joystickNum).setRumble(leftRumble);
        return 0;
    }

    public static int HALGetJoystickIsXbox(byte joystickNum)
    {
        return 0;
    }

    public static int HALGetJoystickType(byte joystickNum)
    {
        return 0;
    }

    public static String HALGetJoystickName(byte joystickNum)
    {
        return "Joystick " + joystickNum;
    }

    public static int HALGetJoystickAxisType(byte joystickNum, byte axis)
    {
        return 0;
    }

    public static float HALGetMatchTime()
    {
        return sRobotState.getMatchTime();
    }

    public static boolean HALGetSystemActive()
    {
        return false;
    }

    public static boolean HALGetBrownedOut()
    {
        return false;
    }

    public static int HALSetErrorData(String error)
    {
        return 0;
    }
}
