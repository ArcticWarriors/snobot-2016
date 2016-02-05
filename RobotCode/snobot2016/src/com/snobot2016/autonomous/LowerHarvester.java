package com.snobot2016.autonomous;

import com.snobot2016.harvester.IHarvester;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LowerHarvester extends Command
{
    private Timer mTimer;
    private double mTime;
    private IHarvester mHarvester;

    public LowerHarvester(double aTime, IHarvester aHarvester)
    {
        mTimer = new Timer();
        mTime = aTime;
        mHarvester = aHarvester;

    }

    @Override
    protected void initialize()
    {
        mTimer.start();
    }

    @Override
    protected void execute()
    {
        if (mTimer.get() < mTime)
        {
            mHarvester.lowerHarvester();
        }
    }

    @Override
    protected boolean isFinished()
    {
        if (mTimer.get() > mTime)
        {
            return true;
        }
        return false;
    }

    @Override
    protected void end()
    {
        mHarvester.stop();
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }
}
