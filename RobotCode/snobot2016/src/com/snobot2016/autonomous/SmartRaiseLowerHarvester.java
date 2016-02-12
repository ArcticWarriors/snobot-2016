package com.snobot2016.autonomous;

import com.snobot2016.harvester.IHarvester;

import edu.wpi.first.wpilibj.command.Command;

public class SmartRaiseLowerHarvester extends Command
{

    private IHarvester mHarvester;
    private String mRaiseOrLower; 
    private boolean mFinished;
    
    public SmartRaiseLowerHarvester(IHarvester aHarvester, String aRaiseOrLower)
    {
        mRaiseOrLower = aRaiseOrLower;
        mFinished = false;
    }
    
    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void execute()
    {
       if(mRaiseOrLower.equalsIgnoreCase("Raise"))
       {
           mHarvester.raiseHarvester();
           if(!mHarvester.goodToLowerVoltage())
           {
               mFinished = true;
           }
       }
       else if(mRaiseOrLower.equalsIgnoreCase("Lower"))
       {
           mHarvester.lowerHarvester();
           if(!mHarvester.goodToRaiseVoltage())
           {
               mFinished = true;
           }
       }
       else
       {
           mHarvester.stopHarvester();
           mFinished = true;
       }
        
    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
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
