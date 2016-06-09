package com.snobot.sd2016.coordinategui.widget;

import java.util.List;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.ui.layers.CoordinateLayer;
import com.snobot.coordinate_gui.ui.layers.FieldImageLayer;
import com.snobot.coordinate_gui.ui.layers.RobotLayer;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;
import com.snobot.sd2016.spline_plotter.SplineSegment;

public class CoordinateGui2016 extends BaseCoordinateGui
{
    private static final String FIELD_IMAGE_PATH = "/com/snobot/sd2016/coordinategui/widget/2016Field.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double CENTER_POINT_X = 13.5;
    private static final double CENTER_POINT_Y = 27;

    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    protected FieldImageLayer mFieldLayer;
    protected CoordinateLayer mCoordinateLayer;

    protected DataProvider<Coordinate> mTrajectoryDataProvider;
    protected CoordinateLayer mTrajectoryLayer;

    protected DataProvider<Double> mTurretAngleDataProvider;
    protected RobotLayer mRobotLayer;

    public CoordinateGui2016(
            CoordinateLayerRenderProps aTrajectoryLayerRenderProps, 
            CoordinateLayerRenderProps aCoordinateLayerRenderProps, 
            RobotLayerRenderProps aRobotLayerRenderProps)
    {
        super(CENTER_POINT_X, CENTER_POINT_Y);

        mTrajectoryDataProvider = new DataProvider<>();
        mTurretAngleDataProvider = new DataProvider<>();

        mFieldLayer = new FieldImageLayer(FIELD_IMAGE_PATH, mConverter, FIELD_WIDTH, FIELD_HEIGHT);
        mRobotLayer = new RobotAndTurretLayer(mCoordinateDataProvider, mTurretAngleDataProvider, aRobotLayerRenderProps, mConverter, ROBOT_WIDTH,
                ROBOT_HEIGHT);
        mCoordinateLayer = new CoordinateLayer(mCoordinateDataProvider, aCoordinateLayerRenderProps, mConverter);
        mTrajectoryLayer = new CoordinateLayer(mTrajectoryDataProvider, aTrajectoryLayerRenderProps, mConverter);

        mLayerManager.addLayer(mFieldLayer);
        mLayerManager.addLayer(mRobotLayer);
        mLayerManager.addLayer(mTrajectoryLayer);
        mLayerManager.addLayer(mCoordinateLayer);
    }

    public void setPath(List<SplineSegment> aSplineSegments)
    {
        synchronized (mDataLock)
        {
            mTrajectoryDataProvider.clear();
            for (SplineSegment segment : aSplineSegments)
            {
                Coordinate coord = new Coordinate(segment.mAverageX / 12.0, segment.mAverageY / 12.0, segment.mRobotHeading);
                mTrajectoryDataProvider.addData(coord);
            }
        }
        mLayerManager.repaint();
    }

    public void addTurretAngle(double aTurretAngle)
    {
        synchronized (mDataLock)
        {
            mTurretAngleDataProvider.addData(aTurretAngle);
        }
        mLayerManager.repaint();
    }
}
