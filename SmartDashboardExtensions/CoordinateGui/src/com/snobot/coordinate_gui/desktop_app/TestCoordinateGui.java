package com.snobot.coordinate_gui.desktop_app;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.ui.layers.CoordinateLayer;
import com.snobot.coordinate_gui.ui.layers.CreatePointsLayer;
import com.snobot.coordinate_gui.ui.layers.FieldImageLayer;
import com.snobot.coordinate_gui.ui.layers.RobotLayer;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.CreatePointsLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class TestCoordinateGui extends BaseCoordinateGui
{
    private static final String FIELD_IMAGE_PATH = "/com/snobot/coordinate_gui/desktop_app/TestVerticalField.png";

    private static final double FIELD_WIDTH = 27;
    private static final double FIELD_HEIGHT = 54;

    private static final double CENTER_POINT_X = 13.5;
    private static final double CENTER_POINT_Y = 27;

    private static final double ROBOT_WIDTH = 36 / 12.0;
    private static final double ROBOT_HEIGHT = 44 / 12.0;

    protected FieldImageLayer mFieldLayer;
    protected CoordinateLayer mCoordinateLayer;
    protected RobotLayer mRobotLayer;
    protected CreatePointsLayer createPointsLayer;

    protected DataProvider<Coordinate> mCreatePointsDataProvider;
    protected DataProvider<Coordinate> mTrajectoryPreviewDataProvider;

    public TestCoordinateGui(CoordinateLayerRenderProps aCoordinateLayerRenderProps, RobotLayerRenderProps aRobotLayerRenderProps,
            CreatePointsLayerRenderProps aCreatePontsLayerRenderProps)
    {
        super(CENTER_POINT_X, CENTER_POINT_Y);
        
        mCreatePointsDataProvider = new DataProvider<Coordinate>();
        mTrajectoryPreviewDataProvider = new DataProvider<Coordinate>();

        mFieldLayer = new FieldImageLayer(FIELD_IMAGE_PATH, mConverter, FIELD_WIDTH, FIELD_HEIGHT);
        mRobotLayer = new RobotLayer(mCoordinateDataProvider, aRobotLayerRenderProps, mConverter, ROBOT_WIDTH, ROBOT_HEIGHT);
        mCoordinateLayer = new CoordinateLayer(mCoordinateDataProvider, aCoordinateLayerRenderProps, mConverter);
        createPointsLayer = new CreatePointsLayer(mLayerManager, mCreatePointsDataProvider, mTrajectoryPreviewDataProvider,
                aCreatePontsLayerRenderProps, mConverter);

        mLayerManager.addLayer(mFieldLayer);
        mLayerManager.addLayer(mRobotLayer);
        mLayerManager.addLayer(mCoordinateLayer);
        mLayerManager.addLayer(createPointsLayer);
    }

    public DataProvider<Coordinate> getCreatePointsDataProvider()
    {
        return mCreatePointsDataProvider;
    }

    public DataProvider<Coordinate> getTrajectoryPreivewDataProvider()
    {
        return mTrajectoryPreviewDataProvider;
    }
}
