package com.snobot.sd2016.coordinategui.widget;

import java.awt.BorderLayout;
import java.awt.Color;

import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;
import com.snobot.sd.util.AutoUpdateWidget;
import com.snobot.sd2016.config.SmartDashBoardNames;
import com.snobot.sd2016.spline_plotter.IdealSplineSerializer;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class CoordinateWidet2016 extends AutoUpdateWidget
{
    public static final String NAME = "2016 Coordinate Widget";
    
    private CoordinateGui2016 mCoordinateGui;

    public CoordinateWidet2016()
    {
        this(false);
    }

    public CoordinateWidet2016(boolean aDebug)
    {
        super(aDebug, 10);

        CoordinateLayerRenderProps trajectoryLayerRenderProps = new CoordinateLayerRenderProps();
        CoordinateLayerRenderProps coordinateLayerRenderProps = new CoordinateLayerRenderProps();
        RobotLayerRenderProps robotLayerRenderProps = new RobotLayerRenderProps();

        trajectoryLayerRenderProps.setFadeOverTime(false);
        trajectoryLayerRenderProps.setPointSize(5);
        trajectoryLayerRenderProps.setPointMemory(-1);
        trajectoryLayerRenderProps.setPointColor(Color.red);

        mCoordinateGui = new CoordinateGui2016(trajectoryLayerRenderProps, coordinateLayerRenderProps, robotLayerRenderProps);

        setLayout(new BorderLayout());
        add(mCoordinateGui.getComponent(), BorderLayout.CENTER);

        setSize(100, 100);

        initializeTrajectoryListener();
    }

    private void initializeTrajectoryListener()
    {

        ITable mTable = NetworkTable.getTable(SmartDashBoardNames.sSPLINE_NAMESPACE);
        ITableListener idealSplineListener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                mCoordinateGui.setPath(IdealSplineSerializer.deserializePath(arg2.toString()));
            }
        };
        mTable.addTableListener(SmartDashBoardNames.sSPLINE_IDEAL_POINTS, idealSplineListener, true);
    }


    @Override
    public void init()
    {
        revalidate();
        repaint();
    }

    @Override
    public void propertyChanged(Property property)
    {

    }

    @Override
    protected void poll() throws Exception
    {
        Coordinate c = new Coordinate();
        c.x = Robot.getTable().getNumber(SmartDashBoardNames.sX_POSITION, 0) / 12.0;
        c.y = Robot.getTable().getNumber(SmartDashBoardNames.sY_POSITION, 0) / 12.0;
        c.angle = Robot.getTable().getNumber(SmartDashBoardNames.sORIENTATION, 0);

        double turret_position = Robot.getTable().getNumber(SmartDashBoardNames.sTURRET_ANGLE, 0);

        if (mCoordinateGui != null)
        {
            mCoordinateGui.addCoordinate(c);
            mCoordinateGui.addTurretAngle(turret_position);
        }
    }
}
