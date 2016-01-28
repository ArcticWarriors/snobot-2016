package com.snobot2016.light;

import com.snobot2016.SmartDashBoardNames;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Light implements ILight
{
    Relay mRelay;

    private boolean mLight;

    public Light(Relay aRelay)
    {
        mRelay = aRelay;
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void update()
    {
        mLight = SmartDashboard.getBoolean(SmartDashBoardNames.sSNOBOT_LIGHT, true);

    }

    @Override
    public void control()
    {
        if (mLight)
        {
            mRelay.set(Value.kForward);
        }
        else
        {
            mRelay.set(Value.kOff);
        }
    }

    @Override
    public void rereadPreferences()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putBoolean(SmartDashBoardNames.sSNOBOT_LIGHT, mLight);
    }

    @Override
    public void updateLog()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLight(boolean aOn)
    {
        // TODO Auto-generated method stub
        mLight = (aOn);
    }

    @Override
    public boolean getLight()
    {
        // TODO Auto-generated method stub
        return mLight;
    }

}
