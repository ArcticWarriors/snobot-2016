package com.snobot2016.positioner;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.Utilities;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Monitors the robot's X/Y-position, orientation, total distance traveled,
 * change in distance traveled, and speed.
 * 
 * @author Alec/Andrew
 *
 */
public class Positioner implements ISubsystem, IPositioner
{
    private double mXPosition;
    private double mYPosition;
    private double mOrientation;
    private double mTotalDistance;
    private double mChangeInDistance;
    private double mLastDistance;
    private Timer mTimer;
    private double mLastTime;
    private Gyro mGyro;
    private IDriveTrain mDriveTrain;
    private double mSpeed;

    // private

    /**
     * Creates a new Positioner object.
     * 
     * @param aGyro
     *            The gyro sensor to use.
     * @param aDriveTrain
     *            The drivetrain to use.
     */
    public Positioner(Gyro aGyro, IDriveTrain aDriveTrain)
    {
        mXPosition = 0;
        mYPosition = 0;
        mOrientation = 0;
        mTotalDistance = 0;
        mChangeInDistance = 0;
        mLastDistance = 0;
        mLastTime = 0;
        mSpeed = 0;
        mGyro = aGyro;
        mDriveTrain = aDriveTrain;
        mTimer = new Timer();
        // mAcceleration = 0;
    }

    /**
     * Starts timer.
     */
    @Override
    public void init()
    {
        mTimer.start();
    }

    /**
     * Calculates the robot's current X/Y-position, orientation, distance
     * traveled, and speed.
     */
    @Override
    public void update()
    {
        // Orientation
        mOrientation = Utilities.boundAngle0to360Degrees(mGyro.getAngle());
        double orientationRadians = Math.toRadians(mOrientation);

        // ChangeInDistance and X/Y
        // TODO Need to account for slips when driving over defenses
        mTotalDistance = (mDriveTrain.getRightEncoderDistance() + mDriveTrain.getLeftEncoderDistance()) / 2;
        mChangeInDistance = mTotalDistance - mLastDistance;
        mXPosition += mChangeInDistance * Math.sin(orientationRadians);
        mYPosition += mChangeInDistance * Math.cos(orientationRadians);

        // Update
        mSpeed = (mChangeInDistance) / (mTimer.get() - mLastTime);
        mLastTime = mTimer.get();
        mLastDistance = mTotalDistance;
    }

    /**
     * @return The robot's current X-position.
     */
    public double getXPosition()
    {
        return mXPosition;
    }

    /**
     * @return The robot's current Y-position.
     */
    public double getYPosition()
    {
        return mYPosition;
    }

    /**
     * @return The robot's current orientation in degrees.
     */
    public double getOrientationDegrees()
    {
        return mOrientation;
    }

    /**
     * @return the robot's current orientation in radians.
     */
    public double getOrientationRadians()
    {
        return Math.toRadians(mOrientation);
    }

    /**
     * @return The total distance traversed by the robot.
     */
    public double getTotalDistance()
    {
        return mTotalDistance;
    }

    /**
     * Assigns a new X-position to the robot.
     * 
     * @param inputX
     *            The new X-position.
     */
    public void setXPosition(double inputX)
    {
        mXPosition = inputX;
    }

    /**
     * Assigns a new Y-position to the robot.
     * 
     * @param inputY
     *            The new Y-position.
     */
    public void setYPosition(double inputY)
    {
        mYPosition = inputY;
    }

    /**
     * Assigns a new orientation in radians to the robot.
     * 
     * @param inputRadians
     *            The new orientation in radians.
     */
    public void setOrientationRadians(double inputRadians)
    {
        mOrientation = Math.toDegrees(inputRadians);
    }

    /**
     * Assigns a new orientation in degrees to the robot.
     * 
     * @param inputDegrees
     *            The new orientation in degrees.
     */
    public void setOrientationDegrees(double inputDegrees)
    {
        mOrientation = inputDegrees;
    }

    @Override
    public void control()
    {

    }

    @Override
    public void rereadPreferences()
    {

    }

    /**
     * Puts the robot's X/Y-position, orientation, and speed on the
     * SmartDashboard.
     */
    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putNumber(SmartDashBoardNames.sX_POSITION, mXPosition);
        SmartDashboard.putNumber(SmartDashBoardNames.sY_POSITION, mYPosition);
        SmartDashboard.putNumber(SmartDashBoardNames.sORIENTATION, mOrientation);
        SmartDashboard.putNumber(SmartDashBoardNames.sSPEED, mSpeed);
    }

    /**
     * Sends the robot's X/Y-position, orientation, total distance traveled,
     * change in distance traveled, and speed to the logger.
     */
    @Override
    public void updateLog()
    {
        // TODO Update logger

    }

    @Override
    public void stop()
    {

    }

}