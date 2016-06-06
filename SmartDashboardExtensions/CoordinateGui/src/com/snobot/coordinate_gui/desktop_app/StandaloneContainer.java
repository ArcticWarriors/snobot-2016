package com.snobot.coordinate_gui.desktop_app;

import java.util.List;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.model.Coordinate;

public class StandaloneContainer
{

    protected ThreadedCoordinateUpdater mLivePointDrawer;
    protected List<Coordinate> mLoadedPoints;
    protected BaseCoordinateGui mGui;

    public StandaloneContainer(BaseCoordinateGui aGui)
    {
        mLoadedPoints = CoordinateFileReader.readFile("TestFiles/xxx.log");
        mGui = aGui;
        mLivePointDrawer = new ThreadedCoordinateUpdater(mGui, 5);
    }

    public void drawPoints()
    {
        mLivePointDrawer.terminate();
        mLivePointDrawer.drawPoints(mLoadedPoints);
    }
}
