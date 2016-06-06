package com.snobot.coordinate_gui.ui.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.model.PixelConverter;

public class CoordinateLayer implements ILayer
{
    protected final DataProvider<Coordinate> mDataProvider;
    protected final PixelConverter mPixelConverter;

    protected int mSize;
    protected int mPointMemory;
    private Color mColor;
    private boolean mFadeOverTime;

    public CoordinateLayer(DataProvider<Coordinate> aDataProvider, PixelConverter aPixelConverter)
    {
        mDataProvider = aDataProvider;
        mPixelConverter = aPixelConverter;

        mSize = 10;
        mPointMemory = 100;
        mColor = Color.green;
        mFadeOverTime = true;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        Iterator<Coordinate> rev_iterator = mDataProvider.getReverseIterator();
        int coordinateCtr = 0;

        while (rev_iterator.hasNext())
        {
            if (mPointMemory != -1 && coordinateCtr >= mPointMemory)
            {
                break;
            }

            Coordinate coord = rev_iterator.next();

            float opacity = 1.0f - ((float) coordinateCtr / mPointMemory);
            Color color;

            if (mFadeOverTime)
            {
                color = new Color(mColor.getRed() / 255.0f, mColor.getGreen() / 255.0f, mColor.getBlue() / 255.0f, opacity);
            }
            else
            {
                color = mColor;
            }

            paintCoordinate(aGraphics, coord, color);
            ++coordinateCtr;
        }
    }

    private void paintCoordinate(Graphics2D aGraphics, Coordinate aCoordinate, Color aColor)
    {
        if (aCoordinate != null)
        {
            int x = mPixelConverter.convertXPoint(aCoordinate.x);
            int y = mPixelConverter.convertYPoint(aCoordinate.y);

            aGraphics.setColor(aColor);
            aGraphics.fillOval(x - mSize / 2, y - mSize / 2, mSize, mSize);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }

    public void setSize(int aSize)
    {
        this.mSize = aSize;
    }

    public void setPointMemory(int aPointMemory)
    {
        this.mPointMemory = aPointMemory;
    }

    public void setColor(Color aColor)
    {
        this.mColor = aColor;
    }

    public void setFadeOverTime(boolean aFadeOverTime)
    {
        this.mFadeOverTime = aFadeOverTime;
    }

}
