package com.snobot2016.autonomous;

import com.snobot2016.harvester.IHarvester;

import edu.wpi.first.wpilibj.command.Command;

public class SuperSmartRaiseLowerHarvester extends Command
{
    private double mPotGoal;
    private IHarvester mHarvester;
    private boolean mFinished;

    public SuperSmartRaiseLowerHarvester(IHarvester aHarvester, double aPotGoal)
    {
        mPotGoal = aPotGoal;
        mHarvester = aHarvester;
    }
    
    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void execute()
    {
        mFinished = mHarvester.moveToPercentage(mPotGoal);
    }

    @Override
    protected boolean isFinished()
    {
        return mFinished;
    }

    @Override
    protected void end()
    {
        mHarvester.stopHarvester();
    }

    @Override
    protected void interrupted()
    {
        
    }

}
