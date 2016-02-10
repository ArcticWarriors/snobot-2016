package com.snobot2016;

import java.text.SimpleDateFormat;

import com.snobot.xlib.ASnobot;
import com.snobot2016.autonomous.AutonFactory;
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
import edu.wpi.first.wpilibj.AnalogInput;
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
    // Raw Joysticks
    private Joystick mRawDriverJoystick;
    private Joystick mRawDriverJoystick2;
    private Joystick mRawOperatorJoystick;

    // Our Joysticks
    private IDriverJoystick mDriverXbox;
    private IDriverJoystick mDriverFlightStick;

    // Drivetrain
    private SpeedController mDriveLeftMotor;
    private SpeedController mDriveRightMotor;
    private Encoder mLeftDriveEncoder;
    private Encoder mRightDriveEncoder;
    private IDriveTrain mDrivetrain;

    // Scaling
    private SpeedController mScaleMoveMotor;
    private SpeedController mScaleTiltMotor;
    private IOperatorJoystick mOperatorJoystick;
    private IScaling mScaling;

    // Harvester
    private SpeedController mHarvesterPivotMotor;
    private SpeedController mHarvesterRollerMotor;
    private IHarvester mHarvester;
    private AnalogInput mHarvesterPot;

    // Positioner
    private IPositioner mSnobotPositioner;
    private Gyro mGyro;

    // Autonomous
    private CommandGroup mAutonCommand;
    private AutonFactory mAutonFactory;

    private AxisCamera mAxisCamera;
    private Camera mCamera;

    // Light
    private Light mCameraLight;
    private Relay mCameraRelay;

    public Snobot()
    {
        super(new SimpleDateFormat("yyyyMMdd_hhmmssSSS"));

        // Raw Joysticks
        mRawDriverJoystick = new Joystick(Properties2016.sDRIVER_JOYSTICK_PORT.getValue());
        mRawDriverJoystick2 = new Joystick(Properties2016.sDRIVER_JOYSTICK_PORT2.getValue());
        mRawOperatorJoystick = new Joystick(Properties2016.sOPERATOR_JOYSTICK_PORT.getValue());

        // Our Joysticks
        mDriverXbox = new SnobotDriverJoystick(mRawDriverJoystick);
        mOperatorJoystick = new SnobotOperatorJoystick(mRawOperatorJoystick);
        mDriverFlightStick = new SnobotDriveFlightStick(mRawDriverJoystick, mRawDriverJoystick2);
        mSubsystems.add(mDriverXbox);
        mSubsystems.add(mOperatorJoystick);
        mSubsystems.add(mDriverFlightStick);

        // Drive train
        mLeftDriveEncoder = new Encoder(Properties2016.sLEFT_DRIVE_ENCODER_PORT_A.getValue(), Properties2016.sLEFT_DRIVE_ENCODER_PORT_B.getValue());
        mRightDriveEncoder = new Encoder(Properties2016.sRIGHT_DRIVE_ENCODER_PORT_A.getValue(),
                Properties2016.sRIGHT_DRIVE_ENCODER_PORT_B.getValue());
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
        mHarvester = new Harvester(mHarvesterRollerMotor, mHarvesterPivotMotor, mOperatorJoystick, mLogger, mHarvesterPot);
        mSubsystems.add(mHarvester);

        // Positioner
        mGyro = new AnalogGyro(Properties2016.sGYRO_SENSOR_PORT.getValue());
        mSnobotPositioner = new Positioner(mGyro, mDrivetrain, mLogger);
        mSubsystems.add(mSnobotPositioner);

        // Autonomous
        mAutonFactory = new AutonFactory(this.getPositioner(), this);

        // Camera
        mCameraRelay = new Relay(Properties2016.sLIGHT_RELAY.getValue());
        mCameraLight = new Light(mCameraRelay, mLogger);
        mSubsystems.add(mCameraLight);

        if (Properties2016.sENABLE_CAMERA.getValue())
        {
            System.out.println("Enabling camera");
            mAxisCamera = new AxisCamera(Properties2016.sCAMERA_HOST_IP.getValue());
            mAxisCamera.writeBrightness(10);
            mCamera = new Camera(mAxisCamera);
            
        }
        else
        {
            System.out.println("Not enabling camera");
        }
    }

    @Override
    public void robotInit()
    {
        init();
    }

    @Override
    public void autonomousInit()
    {
        mAutonCommand = mAutonFactory.buildAnAuton();
        mAutonCommand.start();
    }

    public IDriveTrain getDriveTrain()
    {
        return this.mDrivetrain;
    }

    public IPositioner getPositioner()
    {
        return this.mSnobotPositioner;
    }

    public IHarvester getHarvester()
    {
        return this.mHarvester;
    }

    public IScaling getScaling()
    {
        return this.mScaling;
    }
}
