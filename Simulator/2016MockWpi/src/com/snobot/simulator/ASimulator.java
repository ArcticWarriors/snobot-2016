package com.snobot.simulator;

import java.util.ArrayList;
import java.util.List;

public class ASimulator implements ISimulatorUpdater
{
    protected List<ISimulatorUpdater> mSimulatorComponenets;

    protected ASimulator()
    {
        mSimulatorComponenets = new ArrayList<>();
    }

    @Override
    public void update()
    {
        for (ISimulatorUpdater simulator : mSimulatorComponenets)
        {
            simulator.update();
        }
    }
}
