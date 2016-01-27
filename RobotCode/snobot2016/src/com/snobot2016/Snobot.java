package com.snobot2016;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.snobot.xlib.ACommandParser;
import com.snobot.xlib.ASnobot;
import com.snobot2016.autonomous.CommandParser;
import com.snobot2016.autonomous.IPositioner;
import com.snobot2016.autonomous.Positioner;
import com.snobot2016.camera.Camera;
import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.drivetrain.SnobotDriveTrain;
import com.snobot2016.joystick.IDriverJoystick;
import com.snobot2016.joystick.SnobotDriverJoystick;
import com.snobot2016.logger.Logger;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Snobot extends ASnobot
{
    // Drivetrain
    private SpeedController mDriveLeftMotor;
    private SpeedController mDriveRightMotor;
    private Encoder mLeftDriveEncoder;
    private Encoder mRightDriveEncoder;
    private IDriveTrain mDrivetrain;
    private IDriverJoystick mDriverJoystick;
    private Joystick mRawDriverJoystick;

    // Positioner
    private IPositioner mSnobotPositioner;
    private Gyro mGyro;

    // Autonomous
    private ACommandParser mCommandParser;
    private CommandGroup mCommandGroup;

    private AxisCamera mAxisCamera;
    private Camera mCamera;
    
    private Logger mLogger;
    private SimpleDateFormat mLogDateFormat;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
    	
    	
        // Motors
        mDriveLeftMotor = new Talon(Properties2016.sDRIVER_LEFT_MOTOR_PORT.getValue());
        mDriveRightMotor = new Talon(Properties2016.sDRIVER_RIGHT_MOTOR_PORT.getValue());

        // Digital
        mLeftDriveEncoder = new Encoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(), Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());
        mRightDriveEncoder = new Encoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(),
                Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());
        //Analog
        mGyro = new AnalogGyro(Properties2016.sGYRO_SENSOR_PORT.getValue());

        // UI
        mRawDriverJoystick = new Joystick(Properties2016.sDRIVER_JOYSTICK_PORT.getValue());

        mDriverJoystick = new SnobotDriverJoystick(mRawDriverJoystick);

        // Modules
        mDrivetrain = new SnobotDriveTrain(mDriveLeftMotor, mDriveRightMotor, mLeftDriveEncoder, mRightDriveEncoder, mDriverJoystick);
        mDrivetrain.control();
        mSubsystems.add(mDrivetrain);

        mSubsystems.add(mDriverJoystick);
        
        // Autonomous
        mCommandParser = new CommandParser(this);

        mCommandGroup = mCommandParser.readFile(Properties2016.sAUTON_DIRECTORY.getValue() + "TestAuton");

        mSnobotPositioner = new Positioner(mGyro, mDrivetrain);
        mSubsystems.add(mSnobotPositioner);

        // Camera
        if (Properties2016.sENABLE_CAMERA.getValue())
        {
            System.out.println("Enabling camera");
            mAxisCamera = new AxisCamera(Properties2016.sCAMERA_HOST_IP.getValue());
            mCamera = new Camera(mAxisCamera);
        }
        else
        {
            System.out.println("Not enabling camera");
        }

        //Logger
        mLogDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
        String headerDate = mLogDateFormat.format(new Date());
        mLogger = new Logger(headerDate);
        
        this.init();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    public void autonomousInit()
    {
        mCommandGroup.start();
    }

    @Override
    public void updateLog()
    {
        String logDate = mLogDateFormat.format(new Date());
        if (mLogger.logNow())
        {
            mLogger.startLogEntry(logDate);

            mLogger.updateLogger(mDrivetrain.getLeftEncoderDistance());
            mLogger.updateLogger(mDrivetrain.getRightEncoderDistance());
           

//            for (ISubsystem iSubsystem : mSubsystems)
//            {
//                iSubsystem.updateLog();
//            }

            mLogger.endLogger();
        }
    }

    public IDriveTrain getDriveTrain()
    {
        return this.mDrivetrain;
    }

}
