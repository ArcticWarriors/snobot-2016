package com.snobot2016.autonomous;

import com.snobot2016.harvester.IHarvester;

import edu.wpi.first.wpilibj.command.Command;

public class SmartRaiseLowerHarvester extends Command
{

    private IHarvester mHarvester; 
    private boolean mFinished;
    private boolean mRaise;
    
    public SmartRaiseLowerHarvester(IHarvester aHarvester, String aRaiseOrLower)
    {
        mRaise = aRaiseOrLower.equalsIgnoreCase("Raise");
        mHarvester = aHarvester;
        mFinished = false;
        
        
        if (!aRaiseOrLower.equals("Raise") && !aRaiseOrLower.equals("Lower"))
        {
            throw new UnsupportedOperationException(aRaiseOrLower + " is not equal to Raise or Lower.");
        }
    }
    
    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void execute()
    {
       if(mRaise)
       {
           mHarvester.raiseHarvester();
           if(!mHarvester.goodToRaise())
           {
               mFinished = true;
           }
       }
       else
       {
           mHarvester.lowerHarvester();
           if(!mHarvester.goodToLower())
           {
               mFinished = true;
           }
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
