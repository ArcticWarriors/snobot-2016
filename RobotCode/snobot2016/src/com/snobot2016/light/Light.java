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

    }

    @Override
    public void updateSmartDashboard()
    {
        SmartDashboard.putBoolean(SmartDashBoardNames.sSNOBOT_LIGHT, mLight);
    }

    @Override
    public void updateLog()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public boolean isLightOn()
    {
        return mLight;
    }

}
