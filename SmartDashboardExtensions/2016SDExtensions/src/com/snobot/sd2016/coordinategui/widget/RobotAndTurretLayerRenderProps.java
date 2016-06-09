package com.snobot.sd2016.coordinategui.widget;

import java.awt.Color;

import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class RobotAndTurretLayerRenderProps extends RobotLayerRenderProps
{
    protected Color mTurretColor;
    protected Color mLineOfSightColor;

    public RobotAndTurretLayerRenderProps()
    {
        mTurretColor = Color.blue;
        mLineOfSightColor = Color.black;
    }

    public Color getTurretColor()
    {
        return mTurretColor;
    }

    public void setTurretColor(Color aColor)
    {
        mTurretColor = aColor;
    }

    public Color getLineOfSightColor()
    {
        return mLineOfSightColor;
    }

    public void setLineOfSightColor(Color aColor)
    {
        mLineOfSightColor = aColor;
    }
}
