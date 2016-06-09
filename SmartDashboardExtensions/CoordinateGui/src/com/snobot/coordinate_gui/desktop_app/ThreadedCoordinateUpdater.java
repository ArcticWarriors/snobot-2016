package com.snobot.coordinate_gui.desktop_app;

import java.util.ArrayList;
import java.util.List;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.model.Coordinate;

public class ThreadedCoordinateUpdater implements Runnable
{
    BaseCoordinateGui mDataProvider;
	List<Coordinate> mPoints;
	long mSleepTime;
	boolean mRunning;
	
    private Thread mUpdateThread;

    public ThreadedCoordinateUpdater(BaseCoordinateGui aDataProvider)
	{
        mDataProvider = aDataProvider;
		mPoints = new ArrayList<Coordinate>();

        mSleepTime = 1;
		mRunning = false;
	}

    public void setSleepTime(long aSleepTime)
    {
        mSleepTime = aSleepTime;
    }
	
	public void drawPoints(final List<Coordinate> aPoints)
	{
        mDataProvider.clearCoordinates();
        mPoints = aPoints;

        mUpdateThread = new Thread(this);
        mUpdateThread.start();
	}
	
	public void terminate()
	{
		mRunning = false;
        mUpdateThread = null;
	}
	
	public void run()
	{
		mRunning = true;
        try
        {
            for(int i = 0; i < mPoints.size() && mRunning; ++i)
            {
                mDataProvider.addCoordinate(mPoints.get(i));
                Thread.sleep(mSleepTime);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        mRunning = false;
        
        System.out.println("Done w/ thread");
	}
}
