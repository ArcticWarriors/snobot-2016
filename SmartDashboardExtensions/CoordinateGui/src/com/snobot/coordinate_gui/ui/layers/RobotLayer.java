package com.snobot.coordinate_gui.ui.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.model.PixelConverter;

public class RobotLayer implements ILayer
{
    protected final DataProvider<Coordinate> mDataProvider;
    protected final PixelConverter mPixelConverter;
    protected final double mRobotWidth;
    protected final double mRobotHeight;

    private int mDotSize;
    private Color mReferencePointColor;
    private Color mRobotColor;

    public RobotLayer(DataProvider<Coordinate> aDataProvider, PixelConverter aPixelConverter, double aRobotWidth, double aRobotHeight)
    {
        mDataProvider = aDataProvider;
        mPixelConverter = aPixelConverter;
        mRobotWidth = aRobotWidth;
        mRobotHeight = aRobotHeight;

        mDotSize = 3;
        mReferencePointColor = Color.red;
        mRobotColor = Color.red;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        Coordinate coord = mDataProvider.getMostRecentData();
        if (coord != null)
        {
            drawRobot(aGraphics, coord);
            drawReferencePoint(aGraphics, coord);
        }
    }

    private void drawRobot(Graphics2D g, Coordinate c)
    {
        double centerX = mPixelConverter.convertXPoint(c.x);
        double centerY = mPixelConverter.convertYPoint(c.y);

        double widthInPixels = mPixelConverter.convertPixels(mRobotWidth);
        double heightInPixels = mPixelConverter.convertPixels(mRobotHeight);

        double robotCenter_x = centerX - widthInPixels / 2;
        double robotCenter_y = centerY - heightInPixels / 2;

        Rectangle2D robot = new Rectangle2D.Double(0, 0, widthInPixels, heightInPixels);

        int pivotX = (int) (robotCenter_x + widthInPixels / 2);
        int pivotY = (int) (robotCenter_y + heightInPixels / 2);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(c.angle), pivotX, pivotY);
        transform.translate(robotCenter_x, robotCenter_y);

        Shape shape = transform.createTransformedShape(robot);
        g.setColor(mRobotColor);
        g.fill(shape);
    }

    private void drawReferencePoint(Graphics2D g, Coordinate c)
    {
        double centerX = mPixelConverter.convertXPoint(c.x);
        double centerY = mPixelConverter.convertYPoint(c.y);
        double heightInPixels = mPixelConverter.convertPixels(mRobotHeight);

        double halfRobotHeight = heightInPixels / 2;

        double dx = halfRobotHeight * Math.sin(Math.toRadians(c.angle));
        double dy = halfRobotHeight * Math.cos(Math.toRadians(c.angle));

        int pointX = (int) (centerX + dx - mDotSize / 2);
        int pointY = (int) (centerY - dy - mDotSize / 2);

        g.setColor(mReferencePointColor);
        g.fillOval(pointX, pointY, mDotSize, mDotSize);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return null;
    }

}
