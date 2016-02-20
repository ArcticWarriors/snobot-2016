package com.snobot.simulator.joysticks;

import java.util.ArrayList;
import java.util.List;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

public class BaseJoystick implements IMockJoystick
{

    protected final String mName;
    protected final List<Identifier> mAxis;
    protected final List<Identifier> mButtons;
    protected final List<Identifier> mPOV;
    protected short[] mAxisValues;
    protected short[] mPovValues;
    protected Controller mController;

    public BaseJoystick(String aName)
    {
        mAxis = new ArrayList<>();
        mButtons = new ArrayList<>();
        mPOV = new ArrayList<>();
        mName = aName;

        mAxisValues = new short[mAxis.size()];
        mPovValues = new short[mPOV.size()];
    }

    @Override
    public int getAxisCount()
    {
        return mAxis.size();
    }

    @Override
    public int getButtonCount()
    {
        return mButtons.size();
    }

    @Override
    public void setRumble(short s)
    {
        // TODO implement rumble...
    }

    @Override
    public short[] getAxisValues()
    {

        if (mController != null)
        {
            mController.poll();

            for (int i = 0; i < mAxis.size(); ++i)
            {
                Component c = mController.getComponent(mAxis.get(i));
                if (c != null)
                {
                    mAxisValues[i] = (short) (c.getPollData() * 127);
                }
            }
        }
        else
        {
            System.err.println("Controller is null.  The simulator could not setup a controller of type [" + mName + "]");
        }

        return mAxisValues;
    }

    @Override
    public int getButtonMask()
    {

        int output = 0;

        if (mController != null)
        {
            mController.poll();

            for (int i = 0; i < mButtons.size(); ++i)
            {
                Component component = mController.getComponent(mButtons.get(i));
                if (component != null)
                {
                    int pressed = component.getPollData() == 0 ? 0 : 1;
                    output += (pressed << i);
                }
            }
        }
        else
        {
            System.err.println("Controller is null.  The simulator could not setup a controller of type [" + mName + "]");
        }

        return output;
    }

    @Override
    public short[] getPovValues()
    {
        for (int i = 0; i < mPOV.size(); ++i)
        {
            Identifier id = mPOV.get(i);
            Component component = mController.getComponent(id);
            if (component != null)
            {
                double value = component.getPollData();
                if (value == 0)
                {
                    mPovValues[i] = -1;
                }
                else
                {
                    mPovValues[i] = (short) ((value - .25) * 360);
                }
            }
        }

        return mPovValues;
    }

    public String getName()
    {
        return mName;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
