package com.snobot.sd2016.coordinategui.widget;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.model.PixelConverter;
import com.snobot.coordinate_gui.ui.layers.RobotLayer;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class RobotAndTurretLayer extends RobotLayer
{
    protected final DataProvider<Double> mTurretAngleDataProvider;
    protected final double mTurretWidth;
    protected final double mTurretLength;

    protected Color mTurretColor;
    protected Color mLineOfSightColor;

    public RobotAndTurretLayer(
            DataProvider<Coordinate> aCoordinateDataProvider, 
            DataProvider<Double> aTurretAngleDataProvider, 
            RobotLayerRenderProps aRobotLayerRenderProps, 
            PixelConverter aPixelConverter,
            double aRobotWidth, double 
            aRobotHeight)
    {
        super(aCoordinateDataProvider, aRobotLayerRenderProps, aPixelConverter, aRobotWidth, aRobotHeight);

        mTurretAngleDataProvider = aTurretAngleDataProvider;
        mTurretWidth = 10 / 12.0;
        mTurretLength = 25 / 12.0;

        mTurretColor = Color.black;
        mLineOfSightColor = Color.cyan;
    }

    @Override
    public void render(Graphics2D aGraphics)
    {
        Coordinate coord = mDataProvider.getMostRecentData();
        if (coord != null)
        {
            drawRobot(aGraphics, coord);
            drawReferencePoint(aGraphics, coord);
            drawTurret(aGraphics, coord);
        }
    }
    
    protected void drawTurret(Graphics2D aGraphics, Coordinate aCoordinate)
    {
        double turretAngle = 0;
        Double last_angle = mTurretAngleDataProvider.getMostRecentData();
        if (last_angle != null)
        {
            turretAngle = last_angle;
        }

        double angle_to_draw = aCoordinate.angle - (180 - turretAngle);

        double centerX = mPixelConverter.convertXFeetToPixels(aCoordinate.x);
        double centerY = mPixelConverter.convertYFeetToPixels(aCoordinate.y);

        double widthInPixels = mPixelConverter.convertFeetToPixels(mTurretWidth);
        double heightInPixels = mPixelConverter.convertFeetToPixels(mTurretLength);

        double turretCenter_x = centerX - widthInPixels / 2;
        double turretCenter_y = centerY - heightInPixels / 2;

        int pivotX = (int) (turretCenter_x + widthInPixels / 2);
        int pivotY = (int) (turretCenter_y + heightInPixels / 2);

        Rectangle2D turret = new Rectangle2D.Double(0, 0, widthInPixels, heightInPixels);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle_to_draw), pivotX, pivotY);
        transform.translate(turretCenter_x, turretCenter_y);

        Shape shape = transform.createTransformedShape(turret);
        aGraphics.setColor(mTurretColor);
        aGraphics.fill(shape);

        double ray_extent = 10000;
        double ray_angle = angle_to_draw;
        // double ray_angle = turretAngle;
        double xxx = -mPixelConverter.convertFeetToPixels(Math.sin(Math.toRadians(ray_angle)) * ray_extent);
        double yyy = mPixelConverter.convertFeetToPixels(Math.cos(Math.toRadians(ray_angle)) * ray_extent);

        double ray_end_x = pivotX + xxx;
        double ray_end_y = pivotY + yyy;

        // double ray_end_x = pivotX + Math.sin(Math.toRadians(ray_angle)) *
        // ray_extent;
        // double ray_end_y = pivotY + Math.cos(Math.toRadians(ray_angle)) *
        // ray_extent;

        // System.out.println("Ray End: " + ray_end_x + ", " + ray_end_y);
        // double ray_end_x = Math.cos(Math.toRadians(turretAngle)) * 10000;
        // double ray_end_y = Math.sin(Math.toRadians(turretAngle)) * 10000;
        aGraphics.draw(new Line2D.Double(pivotX, pivotY, ray_end_x, ray_end_y));
    }
}
