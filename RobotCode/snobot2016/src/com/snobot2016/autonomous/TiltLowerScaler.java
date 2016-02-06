package com.snobot2016.autonomous;

import com.snobot2016.scaling.IScaling;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class controls the tilt of the scaling mechanism during autonomous. IN
 * ITS CURRENT STATE THIS CLASS DOES NOT HAVE ANY FUNCTION DUE TO METHODS
 * MISSING FROM THE "SCALING" CLASS!
 * 
 * @author avdonle
 *
 */
public class TiltLowerScaler extends Command
{
    private Timer mTimer;
    private double mTime;
    private IScaling mScaling;

    /**
     * Passes values to local variables.
     * 
     * @param aTime
     *            Set time controlling when the tilt motor runs.
     * @param aScaling
     *            Gets mScaling functionality.
     */
    public TiltLowerScaler(double aTime, IScaling aScaling)
    {
        mTimer = new Timer();
        mTime = aTime;
        mScaling = aScaling;
    }

    /**
     * Starts local timer.
     */
    @Override
    protected void initialize()
    {
        mTimer.start();
    }

    /**
     * Calls the "TiltLower"<--(DOES NOT EXIST) method if within the time given.
     * NEEDS WORK!!! NO TILT LOWERING METHOD.
     */
    @Override
    protected void execute()
    {
        if (mTimer.get() < mTime)
        {
            mScaling.tiltLower();
        }
    }

    /**
     * Informs on if the tilt motor is running.
     * 
     * @return Tilt motor running or not.
     */
    @Override
    protected boolean isFinished()
    {
        if (mTimer.get() > mTime)
        {
            return true;
        }
        return false;
    }

    /**
     * Stops the tilt motor.
     */
    @Override
    protected void end()
    {
        mScaling.stop();
    }

    @Override
    protected void interrupted()
    {

    }

}
