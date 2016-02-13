package com.snobot2016.positioner;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.Logger;
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
    public Logger mLogger;
    private double mOffset;

    // private

    /**
     * Creates a new Positioner object.
     * 
     * @param aGyro
     *            The Gyro to use.
     * @param aDriveTrain
     *            The DriveTrain to use.
     * @param aLogger
     *            The robot's Logger.
     */
    public Positioner(Gyro aGyro, IDriveTrain aDriveTrain, Logger aLogger)
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
        mLogger = aLogger;
        mOffset = 0;
    }

    /**
     * Starts timer and adds headers to Logger.
     */
    @Override
    public void init()
    {
        mTimer.start();

        mLogger.addHeader("X-coordinate");
        mLogger.addHeader("Y-coordinate");
        mLogger.addHeader("Orientation");
        mLogger.addHeader("Speed");
    }

    /**
     * Calculates the robot's current X/Y-position, orientation, distance
     * traveled, and speed.
     */
    @Override
    public void update()
    {
        // Orientation
        mOrientation = (mGyro.getAngle() + mOffset);
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
    	setOrientationDegrees(Math.toDegrees(inputRadians));
    }

    /**
     * Assigns a new orientation in degrees to the robot.
     * 
     * @param inputDegrees
     *            The new orientation in degrees.
     */
    public void setOrientationDegrees(double inputDegrees)
    {
        //mOffset = Utilities.boundAngle0to360Degrees(inputDegrees);
        mOffset = (inputDegrees);

        mGyro.reset();
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
        SmartDashboard.putNumber(SmartDashBoardNames.sORIENTATION, Utilities.boundAngle0to360Degrees(mOrientation));
        SmartDashboard.putNumber(SmartDashBoardNames.sSPEED, mSpeed);
    }

    /**
     * Sends the robot's X/Y-position, orientation, total distance traveled,
     * change in distance traveled, and speed to the logger.
     */
    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mXPosition);
        mLogger.updateLogger(mYPosition);
        mLogger.updateLogger(mOrientation);
        mLogger.updateLogger(mSpeed);
    }

    @Override
    public void stop()
    {

    }

}
