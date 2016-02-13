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
    private AnalogInput mPot; // Tilt Motor Potentiometer
    private Timer mTimer;
    private double mMoveSpeed;
    private double mTiltSpeed;
    private boolean mAmIClimbing;
    private double mAngle; // Current Potentiometer Angle
    private boolean mIsUp; // Is scaling up
    private boolean mIsDown; // Is scaling down

    public Scaling(SpeedController aScaleMoveMotor, SpeedController aScaleTiltMotor, IOperatorJoystick aOperatorJoystick, Logger aLogger,
            AnalogInput aPot)
    {
        mScaleMoveMotor = aScaleMoveMotor;
        mScaleTiltMotor = aScaleTiltMotor;
        mJoystick = aOperatorJoystick;
        mLogger = aLogger;
        mTimer = new Timer();
        mPot = aPot; // Potentiometer
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
        calculateAngle(mPot.getVoltage());

        if (mAngle == high_angle)
        {
            mIsUp = true;
            mIsDown = false;
        }
        else if (mAngle == low_angle)
        {
            mIsUp = false;
            mIsDown = true;
        }
        else
        {
            mIsDown = false;
            mIsUp = false;
        }
    }

    @Override
    public void control()
    {
        controlClimber();

        if (mJoystick.isScaleGoToGroundPressed())
        {
            System.out.println("GOING INTO GROUND");
            reachGoalAngle(ScaleAngles.Ground);
        }
        else if (mJoystick.isScaleGoToHookPositionPressed())
        {
            System.out.println("GOING INTO HOOK");
            reachGoalAngle(ScaleAngles.Hook);
        }
        else if (mJoystick.isScaleMoveForIntakePressed())
        {
            System.out.println("GOING TO MOVE FOR INTAKE");
            reachGoalAngle(ScaleAngles.MoveForIntake);
        }
        else if (mJoystick.isScaleGoToVerticalPressed())
        {
            System.out.println("GOING INTO VERTICAL");
            reachGoalAngle(ScaleAngles.Vertical);
        }
        else
        {
            controlTilt();
        }
    }

    private void controlTilt()
    {
        mTiltSpeed = mJoystick.getScaleTiltSpeed();

        // Ensures motor will not go lower than lowest possible angle
        if (mIsDown && mTiltSpeed < 0)
        {
            mTiltSpeed = 0;
        }
        // Ensures motor will not go higher than highest possible angle
        else if (mIsUp && mTiltSpeed > 0)
        {
            mTiltSpeed = 0;
        }

        setScaleSpeedTilt(mTiltSpeed);
    }

    private void controlClimber()
    {
        double joystickSpeed = mJoystick.getScaleMoveSpeed();

        // Check to see if they want to auto-scale
        if (mJoystick.isFinalCountDown())
        {
            mAmIClimbing = true;
            mTimer.start();
        }

        // If we are scaling, set the motor speed to the climbing speed
        if (mAmIClimbing)
        {
            joystickSpeed = 1;
        }

        // This means that we were climbing and have finished. Stop the motor,
        // reset all of the tracking variables
        if (mAmIClimbing && mTimer.get() > 10)
        {
            mTimer.stop();
            mTimer.reset();
            mAmIClimbing = false;
            joystickSpeed = 0;
        }

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
        SmartDashboard.putNumber(SmartDashBoardNames.sSCALNG_CURRENT_ANGLE, getAngle());
        SmartDashboard.putNumber(SmartDashBoardNames.sTIMER, mTimer.get());
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mMoveSpeed);
        mLogger.updateLogger(mTiltSpeed);
    }

    @Override
    public void stop()
    {
        setScaleSpeedMove(0);
        setScaleSpeedTilt(0);
    }

    @Override
    public void pullUpWall()
    {
        setScaleSpeedMove(1);
    }

    @Override
    public void lowerDownWall()
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

        mAngle = ((high_angle - low_angle) / (high_volts - low_volts)) * (voltage - low_volts);
        // Grabs properties of minimum and maximum configs for potentiometer,
        // Obtains the angle of the scaling arm, given the voltage of
        // a configured potentiometer
    }

    public double getAngle()
    {
        return mAngle;
    }

    @Override
    public void tiltRaise()
    {
        mScaleTiltMotor.set(1);
    }

    @Override
    public void tiltLower()
    {
        mScaleTiltMotor.set(-1);
    }

    @Override
    public boolean reachGoalAngle(ScaleAngles goal)
    {
        double goalAngle = goal.getDesiredAngle();
        double kP = Properties2016.sK_P_ANGLE.getValue();
        double error = (goalAngle - mAngle);
        System.out.println("CHANGING SPEED TO: " + (error * kP));
        mScaleTiltMotor.set(error * kP);
        return (Math.abs(error) < 5);
    }

}
