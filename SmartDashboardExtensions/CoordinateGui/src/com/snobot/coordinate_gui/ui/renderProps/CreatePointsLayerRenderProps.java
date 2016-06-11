package com.snobot.coordinate_gui.ui.renderProps;

import java.awt.Color;

import com.snobot.coordinate_gui.model.Coordinate;

public class CreatePointsLayerRenderProps extends CoordinateLayerRenderProps
{
    public enum AngleCalculationType
    {
        Zero, Calculate, Custom
    }

    protected AngleCalculationType mAngleCalculationType;
    private boolean mSnapToGrid;
    private double mGridFactorX;
    private double mGridFactorY;
    private double mMinDragDistance;

    public CreatePointsLayerRenderProps()
    {
        setFadeOverTime(false);
        setPointSize(5);
        setPointMemory(-1);

        mSnapToGrid = true;
        mGridFactorX = .25;
        mGridFactorY = .25;
        mMinDragDistance = .25;
        mAngleCalculationType = AngleCalculationType.Calculate;
    }

    public int getActivePointSize()
    {
        return 5;
    }

    public Color getActivePointColor()
    {
        return Color.red;
    }

    public double getAngle(Coordinate aLastPoint, double aXFeet, double aYFeet)
    {
        double output = 0;

        switch (mAngleCalculationType)
        {
        case Zero:
            output = 0;
            break;
        case Calculate:
        {
            if (aLastPoint != null)
            {
                double dx = aXFeet - aLastPoint.x;
                double dy = aYFeet - aLastPoint.y;
                output = Math.toDegrees(Math.atan2(dx, dy));
            }
            break;
        }
        case Custom:
            output = 90;
            break;
        default:
            System.err.println("Unknown angle calculation type " + mAngleCalculationType);
        }

        return output;
    }

    public boolean isSnapToGrid()
    {
        return mSnapToGrid;
    }

    public double getGridFactorX()
    {
        return mGridFactorX;
    }

    public double getGridFactorY()
    {
        return mGridFactorY;
    }

    public void setAngleCalculationType(AngleCalculationType aCalculationType)
    {
        mAngleCalculationType = aCalculationType;
    }

    public double getMinDragDistance()
    {
        return mMinDragDistance;
    }
}
