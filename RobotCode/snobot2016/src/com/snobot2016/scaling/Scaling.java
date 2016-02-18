package com.snobot2016.scaling;

import com.snobot.xlib.Logger;
import com.snobot2016.Properties2016;
/**
 * Author Jeffrey/Michael
 * class for scaling arm of the robot
 * creates auto-climb feature
 * 
 */
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.joystick.IOperatorJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Scaling implements IScaling
{
    private Logger mLogger;
    private SpeedController mScaleMoveMotor;
    private SpeedController mScaleTiltMotor;
    private IOperatorJoystick mJoystick;
    private Timer mTimer;
    private double mTiltSpeed;
    private boolean mAmIClimbing;
    private AnalogInput mTiltPot; // Tilt Motor Potentiometer
    private double mScaleTiltAngle; // Current Potentiometer Angle
    private boolean mIsScalingMechanismUp; // Is scaling up
    private boolean mIsDown; // Is scaling down
    private AnalogInput mExtensionPot; // Extension Potentiometer
    private double mExtended; // Current Potentiometer Distance Extended
    private double mVoltage;
    private boolean mSafeToRaise;
    private boolean mSafeToLower;
    // private Ultrasonic mScaleUltrasonic;
    private static final double sHIGH_SCALE_TILT_MARGINAL_ERROR = 5; // error
                                                                     // margin
                                                                     // to find
                                                                     // if scale
                                                                     // tilt is
                                                                     // near max
    private static final double sLOW_SCALE_TILT_MARGINAL_ERROR = 5; // error
                                                                    // margin to
                                                                    // find if
                                                                    // scale
                                                                    // tilt is
                                                                    // near min

    public Scaling(SpeedController aScaleMoveMotor, SpeedController aScaleTiltMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger,
            AnalogInput aTiltPot, AnalogInput aExtensionPot)
    {
        mScaleMoveMotor = aScaleMoveMotor;
        mScaleTiltMotor = aScaleTiltMotor;
        mJoystick = aOperatorJoystick;
        mLogger = aLogger;
        mTimer = new Timer();
        mTiltPot = aTiltPot; // Tilt Potentiometer
        mExtensionPot = aExtensionPot; // Extension Potentiometer

    }

    @Override
    public void init()
    {

    }

    @Override
    public void update()
    {
        double high_angle = Properties2016.sSCALE_HIGH_ANGLE.getValue();
        double low_angle = Properties2016.sSCALE_LOW_ANGLE.getValue();
        calculateAngle(mTiltPot.getVoltage());

        if (mScaleTiltAngle <= (high_angle + sHIGH_SCALE_TILT_MARGINAL_ERROR) || mScaleTiltAngle >= (high_angle - sHIGH_SCALE_TILT_MARGINAL_ERROR))
        {
            mIsScalingMechanismUp = true;
            mIsDown = false;
        }
        else if ((mScaleTiltAngle <= (low_angle + sLOW_SCALE_TILT_MARGINAL_ERROR) || mScaleTiltAngle >= (low_angle - sLOW_SCALE_TILT_MARGINAL_ERROR)))
        {
            mIsScalingMechanismUp = false;
            mIsDown = true;
        }
        else
        {
            mIsDown = false;
            mIsScalingMechanismUp = false;
        }

        {
            mVoltage = mExtensionPot.getVoltage();
            mExtended = (((mVoltage - Properties2016.sMIN_SCALE_EXTENSION_POT_VOLTAGE.getValue())
                    / (Properties2016.sMAX_SCALE_EXTENSION_POT_VOLTAGE.getValue() - Properties2016.sMIN_SCALE_EXTENSION_POT_VOLTAGE.getValue()))
                    * 100);

            // System.out.println(mVoltage);
        }

        // System.out.println("mAngle is " + mAngle + " high angle is: " +
        // high_angle + " low angle is: " + low_angle);
        mSafeToRaise = mScaleTiltAngle < high_angle;
        mSafeToLower = mScaleTiltAngle > low_angle;
    }

    @Override
    public void control()
    {
        controlClimber();

        if (mJoystick.isScaleGoToGroundPressed())
        {
            // System.out.println("GOING INTO GROUND");
            reachingGoalAngle(ScaleAngles.Ground);
        }
        else if (mJoystick.isScaleGoToHookPositionPressed())
        {
            // System.out.println("GOING INTO HOOK");
            reachingGoalAngle(ScaleAngles.Hook);
        }
        else if (mJoystick.isScaleMoveForIntakePressed())
        {
            // System.out.println("GOING TO MOVE FOR INTAKE");
            reachingGoalAngle(ScaleAngles.MoveForIntake);
        }
        else if (mJoystick.isScaleGoToVerticalPressed())
        {
            // System.out.println("GOING INTO VERTICAL");
            reachingGoalAngle(ScaleAngles.Vertical);
        }
        else
        {
            controlTilt();
        }
    }

    private void controlTilt()
    {
        mTiltSpeed = mJoystick.getScaleTiltSpeed();

        // // Ensures motor will not go lower than lowest possible angle
        // if (mIsDown && mTiltSpeed < 0)
        // {
        // mTiltSpeed = 0;
        // }
        // // Ensures motor will not go higher than highest possible angle
        // else if (mIsScalingMechanismUp && mTiltSpeed > 0)
        // {
        // mTiltSpeed = 0;
        // }

        System.out.println("TILT: " + mTiltSpeed);
        setScaleSpeedTilt(mTiltSpeed);
    }

    private void controlClimber()
    {
        double joystickSpeed = mJoystick.getScaleMoveSpeed();

        // // Check to see if they want to auto-scale
        // if (mJoystick.isFinalCountDown())
        // {
        // mAmIClimbing = true;
        // mTimer.start();
        // }
        //
        // // If we are scaling, set the motor speed to the climbing speed
        // if (mAmIClimbing)
        // {
        // joystickSpeed = 1;
        // }
        //
        // // This means that we were climbing and have finished. Stop the
        // motor,
        // // reset all of the tracking variables
        // if (mAmIClimbing && mTimer.get() > 10)
        // {
        // mTimer.stop();
        // mTimer.reset();
        // mAmIClimbing = false;
        // joystickSpeed = 0;
        // }

        setScaleSpeedMove(joystickSpeed);
    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {
        // puts scale motor, tilt motor, current angle and timer on
        // SmartDashboard
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_MOVE_MOTOR, mScaleMoveMotor.get());
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_TILT_MOTOR, mScaleTiltMotor.get());
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_TILT_POT_VOLTAGE, mTiltPot.getVoltage());
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALNG_CURRENT_ANGLE, getAngle());
        SmartDashboard.putNumber(SmartDashBoardNames.sTIMER, mTimer.get());
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALE_CURRENT_POSITION, percentageScaled());
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mTiltSpeed);
        mLogger.updateLogger(mIsScalingMechanismUp);
        mLogger.updateLogger(mIsDown);
    }

    @Override
    public void stop()
    {
        setScaleSpeedMove(0);
        setScaleSpeedTilt(0);
    }

    @Override
    public void raiseScaleExtensionLadder()
    {
        setScaleSpeedMove(1);
    }

    @Override
    public void lowerScaleExtensionLadder()
    {
        setScaleSpeedMove(-1);
    }

    public void setScaleSpeedMove(double aSpeedMove)
    {
        mScaleMoveMotor.set(aSpeedMove);
    }

    public void setScaleSpeedTilt(double aSpeedTilt)
    {
        mScaleTiltMotor.set(aSpeedTilt);
    }

    public void calculateAngle(double voltage)
    {
        double high_angle = Properties2016.sSCALE_HIGH_ANGLE.getValue();
        double low_angle = Properties2016.sSCALE_LOW_ANGLE.getValue();
        double high_volts = Properties2016.sSCALE_HIGH_VOLTAGE.getValue();
        double low_volts = Properties2016.sSCALE_LOW_VOLTAGE.getValue();

        mScaleTiltAngle = ((high_angle - low_angle) / (high_volts - low_volts)) * (voltage - low_volts);
        // Grabs properties of minimum and maximum configs for potentiometer,
        // Obtains the angle of the scaling arm, given the voltage of
        // a configured potentiometer
    }

    public double getAngle()
    {
        return mScaleTiltAngle;
    }

    @Override
    public void raiseScaleTiltMechanism()
    {
        mScaleTiltMotor.set(1);
    }

    @Override
    public void lowerScaleTiltMechanism()
    {
        mScaleTiltMotor.set(-1);
    }

    @Override
    public boolean reachingGoalAngle(ScaleAngles goal)
    {
        double goalAngle = goal.getDesiredAngle();
        double kP = Properties2016.sK_P_SCALE_TILT_ANGLE.getValue();
        double error = (goalAngle - mScaleTiltAngle);
        double mSpeed = (error * kP);
        // System.out.println("CHANGING SPEED TO: " + mSpeed);
        System.out.println(" The goal angle is: " + goalAngle + ". The current angle is: " + mScaleTiltAngle);
        if (mSpeed > 0)
        {
            if (safeToRaise())
            {
                mScaleTiltMotor.set(mSpeed);
            }
        }
        else
        {
            if (safeToLower())
            {
                mScaleTiltMotor.set(mSpeed);
            }
        }
        return (Math.abs(error) < 5);
    }

    @Override
    public double percentageScaled()
    {
        return mExtended;
    }

    @Override
    public boolean safeToRaise()
    {
        return mSafeToRaise;
    }

    @Override
    public boolean safeToLower()
    {
        return mSafeToLower;
    }

}
