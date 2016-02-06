package com.snobot2016;

import java.text.SimpleDateFormat;

import com.snobot.xlib.ACommandParser;
import com.snobot.xlib.ASnobot;
import com.snobot.xlib.Logger;
import com.snobot2016.autonomous.CommandParser;
import com.snobot2016.camera.Camera;
import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.drivetrain.SnobotDriveTrain;
import com.snobot2016.harvester.Harvester;
import com.snobot2016.harvester.IHarvester;
import com.snobot2016.joystick.IDriverJoystick;
import com.snobot2016.joystick.IOperatorJoystick;
import com.snobot2016.joystick.SnobotDriveFlightStick;
import com.snobot2016.joystick.SnobotDriverJoystick;
import com.snobot2016.joystick.SnobotOperatorJoystick;
import com.snobot2016.light.Light;
import com.snobot2016.positioner.IPositioner;
import com.snobot2016.positioner.Positioner;
import com.snobot2016.scaling.IScaling;
import com.snobot2016.scaling.Scaling;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
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
    private IDriverJoystick mDriverXbox;
    private IDriverJoystick mDriverFlightStick;
    private Joystick mRawDriverJoystick;
    
    // Scaling
    private SpeedController mScaleMoveMotor;
    private SpeedController mScaleTiltMotor;
    private IOperatorJoystick mOperatorJoystick;
    private Joystick mRawOperatorJoystick;
    private IScaling mScaling;
    

    // Harvester
    private SpeedController mHarvesterPivotMotor;
    private SpeedController mHarvesterRollerMotor;
    private IHarvester mHarvester;
    
    // Positioner
    private IPositioner mSnobotPositioner;
    private Gyro mGyro;
    
    // Autonomous
    private ACommandParser mCommandParser;
    private CommandGroup mCommandGroup;

    private AxisCamera mAxisCamera;
    private Camera mCamera;

    // Light
    private Light mCameraLight;
    private Relay mCameraRelay;
    private Logger mLogger;

    public Snobot()
    {
        super(new SimpleDateFormat("yyyyMMdd_hhmmssSSS"));

        // Raw Joysticks
        mRawDriverJoystick = new Joystick(Properties2016.sDRIVER_JOYSTICK_PORT.getValue());
        mRawOperatorJoystick = new Joystick(Properties2016.sOPERATOR_JOYSTICK_PORT.getValue());

        // Our Joysticks
        mDriverXbox = new SnobotDriverJoystick(mRawDriverJoystick);
        mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
        mDriverFlightStick = new SnobotDriveFlightStick();
        mSubsystems.add(mDriverXbox);
        mSubsystems.add(mOperatorJoystick);
        mSubsystems.add(mDriverFlightStick);

        // Drive train
        mLeftDriveEncoder = new Encoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(), Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());
        mRightDriveEncoder = new Encoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(), Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());
        mDriveLeftMotor = new Talon(Properties2016.sDRIVER_LEFT_MOTOR_PORT.getValue());
        mDriveRightMotor = new Talon(Properties2016.sDRIVER_RIGHT_MOTOR_PORT.getValue());
        mDrivetrain = new SnobotDriveTrain(mDriveLeftMotor, mDriveRightMotor, mLeftDriveEncoder, mRightDriveEncoder, mDriverXbox, mDriverFlightStick);
        mSubsystems.add(mDrivetrain);

        // Scaling
        mScaleMoveMotor = new Talon(Properties2016.sSCALE_MOVE_MOTOR_PORT.getValue());
        mScaleTiltMotor = new Talon(Properties2016.sSCALE_TILT_MOTOR_PORT.getValue());
        mScaling = new Scaling(mScaleMoveMotor, mScaleTiltMotor, mOperatorJoystick, mLogger);
        mSubsystems.add(mScaling);

        // Harvester
        mHarvesterPivotMotor = new Talon(Properties2016.sHARVESTER_PIVOT_MOTOR_PORT.getValue());
        mHarvesterRollerMotor = new Talon(Properties2016.sHARVESTER_ROLLER_MOTOR_PORT.getValue());
        mHarvester = new Harvester(mHarvesterRollerMotor, mHarvesterPivotMotor, mOperatorJoystick, mLogger);
        mSubsystems.add(mHarvester);

        // Positioner
        mGyro = new AnalogGyro(Properties2016.sGYRO_SENSOR_PORT.getValue());
        mSnobotPositioner = new Positioner(mGyro, mDrivetrain);
        mSubsystems.add(mSnobotPositioner);

        // Camera
        mCameraRelay = new Relay(Properties2016.sLIGHT_RELAY.getValue());
        mCameraLight = new Light(mCameraRelay, mLogger);
        mSubsystems.add(mCameraLight);

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

        // Autonomous
        mCommandParser = new CommandParser(this);
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
        String testSingleDir = Properties2016.sAUTON_DIRECTORY.getValue() + "Autonomous/TestSingleAutonomous/";
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestDriveStraightADistance_Backwards");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestDriveStraightADistance_Forwards");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestStupidDriveStraight_Backwards");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestStupidDriveStraight_Fowards");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestStupidTurn_Left");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestStupidTurn_Right");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestTurnWithDegrees_Left");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestTurnWithDegrees_Right");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_000Degrees");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_045Degrees");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_090Degrees");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_135Degrees");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_180Degrees");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_225Degrees");
//        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_270Degrees");
        mCommandGroup = mCommandParser.readFile(testSingleDir + "TestGoToXY_315Degrees");
        mCommandGroup.start();
    }

    public IDriveTrain getDriveTrain()
    {
        return this.mDrivetrain;
    }

    public IPositioner getPositioner()
    {
        return this.mSnobotPositioner;
    }
    
}










