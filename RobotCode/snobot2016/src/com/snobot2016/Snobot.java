package com.snobot2016;

import com.snobot.xlib.ASnobot;
import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.drivetrain.SnobotDriveTrain;
import com.snobot2016.joystick.IDriverJoystick;
import com.snobot2016.joystick.SnobotDriverJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

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

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        // Motors
        mDriveLeftMotor = new Talon(0);
        mDriveRightMotor = new Talon(1);

        // Digital
        mLeftDriveEncoder = new Encoder(0, 1);
        mRightDriveEncoder = new Encoder(2, 3);

        // UI
        mRawDriverJoystick = new Joystick(0);

        mDriverJoystick = new SnobotDriverJoystick(mRawDriverJoystick);

        // Modules
        mDrivetrain = new SnobotDriveTrain(mDriveLeftMotor, mDriveRightMotor, mLeftDriveEncoder, mRightDriveEncoder, mDriverJoystick);
        mDrivetrain.control();
        mSubsystems.add(mDrivetrain);

        mSubsystems.add(mDriverJoystick);
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

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {

    }

    @Override
    public void updateLog()
    {

    }

}
