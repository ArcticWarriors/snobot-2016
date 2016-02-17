package com.snobot.sd2016.coordinategui.pointDrawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.snobot.sd2016.coordinategui.Coordinate;


public interface PointDrawer
{

    public void drawPoints(Graphics g, List<Coordinate> aCoordinates);
    
    public static class NullPointDrawer implements PointDrawer
    {

        @Override
        public void drawPoints(Graphics g, List<Coordinate> aCoordinates) {
        }

        @Override
        public void setColor(Color aColor)
        {
            // TODO Auto-generated method stub

        }
        
    }

    public void setColor(Color aColor);
}
