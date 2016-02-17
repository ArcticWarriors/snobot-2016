package com.snobot.sd2016.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import com.snobot.sd2016.coordinategui.Coordinate;


public class FadedPointDrawer implements PointDrawer
{
    private Color mPointColor;
    private int mDotSize;
    private double mPointMemory;
    private FeetToPixelConverter mConverter;
    
    public FadedPointDrawer(FeetToPixelConverter aConverter)
    {
        mDotSize = 6;
        mConverter = aConverter;
        mPointColor = Color.green;
        mPointMemory = 30;
    }
    
    public void setPointMemory(double aPointMemory)
    {
        mPointMemory = aPointMemory;
    }

    @Override
    public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {

        int size = aCoordinates.size();        
        int lMin = (int) (size - mPointMemory);
        if(lMin < 0)
        {
            lMin = 0;
        }
        
        for (int i = size-1; i >= lMin; --i)
        {
            double lSubtract = (255 - (i + 1 - lMin) / mPointMemory * 255);
            int alpha = (int) (mPointColor.getAlpha() - lSubtract);
            
            g.setColor(new Color(mPointColor.getRed(), mPointColor.getGreen(), mPointColor.getBlue(), alpha));
            drawCoordinate(g, aCoordinates.get(i));
        }
    }

    public void setColor(Color aColor) {
        mPointColor = aColor;
    }

    /**
     * Draws a coordinate onto the field. Assumes the color was already set
     * 
     * @param g
     *            The graphics to draw onto
     * @param c
     *            The coordinate to draw
     */
    protected void drawCoordinate(Graphics g, Coordinate c)
    {
        Point center = mConverter.convertPoint(new Point2D.Double(c.x, -c.y));

        int x = center.x - mDotSize / 2;
        int y = center.y - mDotSize / 2;
        g.fillOval(x, y, mDotSize, mDotSize);
    }
}
