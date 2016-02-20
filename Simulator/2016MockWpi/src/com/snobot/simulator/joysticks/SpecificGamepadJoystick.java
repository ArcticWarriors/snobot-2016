package com.snobot.simulator.joysticks;

import java.util.List;

import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class SpecificGamepadJoystick extends BaseJoystick
{

    public SpecificGamepadJoystick(String aName, List<Identifier> aAxisList, List<Identifier> aButtonList, List<Identifier> aPOV)
    {
        super(aName);

        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();

        Controller[] cs = ce.getControllers();
        for (int i = 0; i < cs.length; i++)
        {
            if (!cs[i].getName().equals(aName))
            {
                continue;
            }

            mController = cs[i];
        }

        mAxis.addAll(aAxisList);
        mButtons.addAll(aButtonList);
        mPOV.addAll(aPOV);

        mAxisValues = new short[aAxisList.size()];
        mPovValues = new short[aPOV.size()];
    }
}
