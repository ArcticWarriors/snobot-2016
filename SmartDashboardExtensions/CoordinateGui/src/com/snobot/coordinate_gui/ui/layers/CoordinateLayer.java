package com.snobot.coordinate_gui.ui.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.model.PixelConverter;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;

public class CoordinateLayer implements ILayer
{
    protected final DataProvider<Coordinate> mDataProvider;
    protected final PixelConverter mPixelConverter;
    protected final CoordinateLayerRenderProps mRenderProperties;

    public CoordinateLayer(DataProvider<Coordinate> aDataProvider, CoordinateLayerRenderProps aRenderProps, PixelConverter aPixelConverter)
    {
        mDataProvider = aDataProvider;
        mRenderProperties = aRenderProps;
        mPixelConverter = aPixelConverter;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        Iterator<Coordinate> rev_iterator = mDataProvider.getReverseIterator();
        int coordinateCtr = 0;

        while (rev_iterator.hasNext())
        {
            int pointMemory = mRenderProperties.getPointMemory();
            if (coordinateCtr >= pointMemory)
            {
                break;
            }

            Coordinate coord = rev_iterator.next();

            float opacity = 1.0f - ((float) coordinateCtr / pointMemory);
            Color defaultColor = mRenderProperties.getPointColor();
            Color color;

            if (mRenderProperties.isFadeOverTime())
            {
                color = new Color(defaultColor.getRed() / 255.0f, defaultColor.getGreen() / 255.0f, defaultColor.getBlue() / 255.0f, opacity);
            }
            else
            {
                color = defaultColor;
            }

            paintCoordinate(aGraphics, coord, color);
            ++coordinateCtr;
        }
    }

    private void paintCoordinate(Graphics2D aGraphics, Coordinate aCoordinate, Color aColor)
    {
        if (aCoordinate != null)
        {
            int x = mPixelConverter.convertXFeetToPixels(aCoordinate.x);
            int y = mPixelConverter.convertYFeetToPixels(aCoordinate.y);
            int size = mRenderProperties.getPointSize();

            aGraphics.setColor(aColor);
            aGraphics.fillOval(x - size / 2, y - size / 2, size, size);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }

    // public void setSize(int aSize)
    // {
    // this.mSize = aSize;
    // }
    //
    // public void setPointMemory(int aPointMemory)
    // {
    // this.mPointMemory = aPointMemory;
    // }
    //
    // public void setColor(Color aColor)
    // {
    // this.mColor = aColor;
    // }
    //
    // public void setFadeOverTime(boolean aFadeOverTime)
    // {
    // this.mFadeOverTime = aFadeOverTime;
    // }

}
