package com.snobot.sd2016.robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import com.snobot.sd.util.Util;

public class RobotDrawer2016 extends JPanel
{
    // Chassis dimensions
    private static final double sCHASSIS_WIDTH = 35;
    private static final double sCHASSIS_HEIGHT = 8.25;

    private static final double sROBOT_WIDTH = 100;
    private static final double sROBOT_HEIGHT = 100;

    // Drawing Locations
    private static final double sCHASSIS_X_START = 25;
    private static final double sCHASSIS_Y_START = 80;

    // Component Colors
    private static final Color sROBOT_BASE_COLOR = Color.black;
    private static final Color sROBOT_HARVESTER_COLOR = Color.blue;
    private static final Color sROBOT_SCALE_COLOR = Color.gray;
    private static final Color sROBOT_SCALE_EXTENSION_COLOR = Color.cyan;
    // Boulder Harvester
    private static final double sHARVESTER_HEIGHT = 9.5;
    private static final double sHARVESTER_WIDTH = 4;
    private static final double sHARVESTER_X_START = (sCHASSIS_X_START + sHARVESTER_WIDTH);
    private static final double sHARVESTER_Y_START = (sCHASSIS_Y_START - sHARVESTER_HEIGHT);
    // Scale
    private static final double sSCALE_WIDTH = 30;
    private static final double sSCALE_HEIGHT = 4;
    private static final double sSCALE_X_START = (sCHASSIS_X_START + sHARVESTER_WIDTH);
    private static final double sSCALE_Y_START = (sCHASSIS_Y_START - sSCALE_HEIGHT);
    // Scale Extension
    private static final double sSCALE_EXTENSION_WIDTH = 30;
    private static final double sSCALE_EXTENSION_HEIGHT = 4;
    private static final double sSCALE_EXTENSION_X_START = (sCHASSIS_X_START + sHARVESTER_WIDTH + 2);
    private static final double sSCALE_EXTENSION_Y_START = (sCHASSIS_Y_START - sSCALE_EXTENSION_HEIGHT);
    /**
     * The scaling factor used for drawing. For example, 1 would mean draw every
     * inch as one pixel, 5 would mean draw every inch as 5 pixels
     */
    private double mScaleFactor;

    // Robot State
    private double mScaleMotorSpeed;
    private double mInakeMotorSpeed;
    private double mScaleTiltMotorSpeed;
    private double mInakeTiltMotorSpeed;

    private double mClimbTiltAngle;
    private double mIntakeTiltAngle;

    private double mExtensionHeight;

    public RobotDrawer2016()
    {
        updateSize();
        setPreferredSize(new Dimension(200, 200));

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent arg0)
            {
                updateSize();
            }
        });
    }

    public void updateSize()
    {
        double horizontalScaleFactor = (getWidth() / sROBOT_WIDTH);
        double verticalScaleFactor = (getHeight() / sROBOT_HEIGHT);

        double minScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);

        mScaleFactor = minScaleFactor;

        // System.out.println(this);
        // System.out.println("Scale Factor: " + mScaleFactor);
        // System.out.println("  Horizontal factor : " + horizontalScaleFactor +
        // ", width : " + getWidth() + ", " + sROBOT_WIDTH);
        // System.out.println("  Vertical factor : " + verticalScaleFactor +
        // ", height : " + getHeight() + ", " + sROBOT_HEIGHT);

        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g.clearRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());

        // Draw Robot Parts
        drawRobotBase(g2d);
        drawScale(g2d);
        drawScaleExtension(g2d);
        drawHarvester(g2d);
    }

    private void drawRobotBase(Graphics2D g2d)
    {
        Rectangle2D robotBase = new Rectangle2D.Double(sCHASSIS_X_START * mScaleFactor, sCHASSIS_Y_START * mScaleFactor,
                sCHASSIS_WIDTH * mScaleFactor, sCHASSIS_HEIGHT * mScaleFactor);

        g2d.setColor(sROBOT_BASE_COLOR);
        g2d.fill(robotBase);
    }

    private void drawHarvester(Graphics2D g2d)
    {
        Color color = sROBOT_HARVESTER_COLOR;
        if (getInakeTiltMotorSpeed() != 0)
        {
            color = Util.getMotorColor(getInakeTiltMotorSpeed());
        }
        else
        {
            color = sROBOT_HARVESTER_COLOR;
        }
        Rectangle2D robotHarvester = new Rectangle2D.Double(sHARVESTER_X_START, sHARVESTER_Y_START, sHARVESTER_WIDTH, sHARVESTER_HEIGHT);

        AffineTransform transform = new AffineTransform();
        transform.scale(mScaleFactor, mScaleFactor);
        transform.rotate(Math.toRadians(mIntakeTiltAngle), sHARVESTER_X_START, (sHARVESTER_Y_START + sHARVESTER_HEIGHT));

        Shape shape = transform.createTransformedShape(robotHarvester);

        g2d.setColor(color);
        g2d.fill(shape);

    }

    private void drawScale(Graphics2D g2d)
    {
        Color color = sROBOT_SCALE_COLOR;
        if (getScaleTiltMotorSpeed() != 0)
        {
            color = Util.getMotorColor(getScaleTiltMotorSpeed());
        }
        else
        {
            color = sROBOT_SCALE_COLOR;
        }
        Rectangle2D robotScale = new Rectangle2D.Double(sSCALE_X_START, sSCALE_Y_START, sSCALE_WIDTH, sSCALE_HEIGHT);

        AffineTransform transform = new AffineTransform();
        transform.scale(mScaleFactor, mScaleFactor);
        transform.rotate(Math.toRadians(mClimbTiltAngle), sSCALE_X_START, (sSCALE_Y_START + sSCALE_HEIGHT));

        Shape shape = transform.createTransformedShape(robotScale);

        g2d.setColor(color);
        g2d.fill(shape);

    }

    private void drawScaleExtension(Graphics2D g2d)
    {
        Color color = sROBOT_SCALE_EXTENSION_COLOR;
        if (getScaleMotorSpeed() != 0)
        {
            color = Util.getMotorColor(getScaleMotorSpeed());
        }
        else
        {
            color = sROBOT_SCALE_EXTENSION_COLOR;
        }

        Rectangle2D robotScale = new Rectangle2D.Double((sSCALE_EXTENSION_X_START - mExtensionHeight), (sSCALE_EXTENSION_Y_START),
                sSCALE_EXTENSION_WIDTH, sSCALE_EXTENSION_HEIGHT);

        AffineTransform transform = new AffineTransform();
        transform.scale(mScaleFactor, mScaleFactor);
        transform.rotate(Math.toRadians(mClimbTiltAngle), sSCALE_EXTENSION_X_START, (sSCALE_EXTENSION_Y_START + sSCALE_EXTENSION_HEIGHT));

        Shape shape = transform.createTransformedShape(robotScale);

        g2d.setColor(color);
        g2d.fill(shape);

    }

    public double getScaleFactor()
    {
        return mScaleFactor;
    }

    public void setScaleFactor(double mScaleFactor)
    {
        this.mScaleFactor = mScaleFactor;
    }

    public double getScaleMotorSpeed()
    {
        return mScaleMotorSpeed;
    }

    public void setScaleMotorSpeed(double mScaleMotorSpeed)
    {
        this.mScaleMotorSpeed = mScaleMotorSpeed;
    }

    public double getInakeMotorSpeed()
    {
        return mInakeMotorSpeed;
    }

    public void setInakeMotorSpeed(double mInakeMotorSpeed)
    {
        this.mInakeMotorSpeed = mInakeMotorSpeed;
    }

    public double getClimbTiltAngle()
    {
        return mClimbTiltAngle;
    }

    public void setClimbTiltAngle(double mClimbTiltAngle)
    {
        this.mClimbTiltAngle = mClimbTiltAngle;
    }

    public double getIntakeTiltAngle()
    {
        return mIntakeTiltAngle;
    }

    public void setIntakeTiltAngle(double mIntakeTiltAngle)
    {
        this.mIntakeTiltAngle = mIntakeTiltAngle;
    }

    public double getScaleTiltMotorSpeed()
    {
        return mScaleTiltMotorSpeed;
    }

    public void setScaleTiltMotorSpeed(double mScaleTiltMotorSpeed)
    {
        this.mScaleTiltMotorSpeed = mScaleTiltMotorSpeed;
    }

    public double getInakeTiltMotorSpeed()
    {
        return mInakeTiltMotorSpeed;
    }

    public void setInakeTiltMotorSpeed(double mInakeTiltMotorSpeed)
    {
        this.mInakeTiltMotorSpeed = mInakeTiltMotorSpeed;
    }

    public void setExtensionHeight(double aExtensionHeight)
    {
        mExtensionHeight = aExtensionHeight;
        updateSize();
    }

    public double getExtensionHeight()
    {
        return mExtensionHeight;
    }

    @Override
    public String toString()
    {
        return "RobotDrawer2016 [mScaleMotorSpeed=" + mScaleMotorSpeed + ", mInakeMotorSpeed=" + mInakeMotorSpeed + ", mScaleTiltMotorSpeed="
                + mScaleTiltMotorSpeed + ", mInakeTiltMotorSpeed=" + mInakeTiltMotorSpeed + ", mClimbTiltAngle=" + mClimbTiltAngle
                + ", mIntakeTiltAngle=" + mIntakeTiltAngle + ", mScaleFactor=" + mScaleFactor + "]";
    }

}
