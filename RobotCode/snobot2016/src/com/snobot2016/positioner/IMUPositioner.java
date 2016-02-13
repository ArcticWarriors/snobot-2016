package com.snobot2016.positioner;

import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.Logger;
import com.snobot.xlib.Utilities;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.drivetrain.IDriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Monitors the robot's X/Y-position, orientation, total distance traveled, and
 * speed.
 * 
 * @author Alec/Andrew
 *
 */
public class IMUPositioner implements IPositioner, ISubsystem
{
    private double mXPosition;
    private double mYPosition;
    private double mOrientation;
    private double mTotalDistance;
    private double mChangeInDistance;
    private Timer mTimer;
    private Gyro mGyro;
    private Accelerometer mAccelerometer;
    private IDriveTrain mDriveTrain;
    private double mVelocityX;
    private double mVelocityY;
    private double mSpeed;
    public Logger mLogger;
    private double mOffset;
    private double mLastTime;

    /**
     * Creates a new Positioner object.
     * 
     * @param aGyro
     *            The Gyro to use.
     * @param aAccelerometer
     *            The accelerometer to use.
     * @param aDriveTrain
     *            The DriveTrain to use.
     * @param aLogger
     *            The robot's Logger.
     */
    public IMUPositioner(Gyro aGyro, Accelerometer aAccelerometer, IDriveTrain aDriveTrain, Logger aLogger)
    {
        mGyro = aGyro;
        mAccelerometer = aAccelerometer;
        mDriveTrain = aDriveTrain;
        mLogger = aLogger;
        mXPosition = 0;
        mYPosition = 0;
        mOrientation = 0;
        mTotalDistance = 0;
        mChangeInDistance = 0;
        mTimer = new Timer();
        mVelocityX = 0;
        mVelocityY = 0;
        mSpeed = 0;
        mLastTime = 0;
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
        mOrientation = Utilities.boundAngle0to360Degrees(mGyro.getAngle() + mOffset);

        double deltaTime = mTimer.get() - mLastTime;

        double accelX = mAccelerometer.getX();
        mVelocityX += (accelX * deltaTime);
        double xDistance = (mVelocityX * deltaTime) + (.5 * accelX * Math.pow(deltaTime, 2));
        mXPosition += xDistance;

        double accelY = mAccelerometer.getY();
        mVelocityY += (accelY * deltaTime);
        double yDistance = (mVelocityY * deltaTime) + (.5 * accelY * Math.pow(deltaTime, 2));
        mYPosition += yDistance;

        mTotalDistance += Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        mSpeed = Math.sqrt(Math.pow(mVelocityX, 2) + Math.pow(mVelocityY, 2));
        mLastTime = mTimer.get();
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
        mLogger.updateLogger(mXPosition);
        mLogger.updateLogger(mYPosition);
        mLogger.updateLogger(mOrientation);
        mLogger.updateLogger(mSpeed);
    }

    @Override
    public void stop()
    {

    }

    /**
     * @return The robot's current X-position.
     */
    @Override
    public double getXPosition()
    {
        return mXPosition;
    }

    /**
     * @return The robot's current Y-position.
     */
    @Override
    public double getYPosition()
    {
        return mYPosition;
    }

    /**
     * @return The robot's current orientation in degrees.
     */
    @Override
    public double getOrientationDegrees()
    {
        return mOrientation;
    }

    /**
     * @return the robot's current orientation in radians.
     */
    @Override
    public double getOrientationRadians()
    {
        return Math.toRadians(mOrientation);
    }

    /**
     * @return The total distance traversed by the robot.
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public void setOrientationDegrees(double inputDegrees)
    {
        mOrientation = inputDegrees;
    }

}
