package com.snobot.sd2016.coordinategui.standalone;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;

import com.snobot.sd2016.coordinategui.Coordinate;
import com.snobot.sd2016.coordinategui.FieldDrawer;
import com.snobot.sd2016.coordinategui.FieldDrawerPanel;
import com.snobot.sd2016.coordinategui.widget.CoordinateWidget;


public class StandalonePanel extends JPanel 
{
	private static final long serialVersionUID = 3475280226420887056L;
	

	private static final String sPROPERTIES_FILE = "properties.properties";
	private static final String sDRAW_AUTON_KEY = "draw_auton";
	private static final String sPOINT_MEMORY = "point_memory";
	private static final String sPOINT_COLOR = "point_color";
    private static final String sDRAWING_MODE_KEY = "drawing_mode";
    private static final String sUPDATE_RATE_KEY = "update_rate_ms";
	
	
	
    private int mSleepTime;

	private FieldDrawerPanel mFieldDrawer;
	private GuiUpdaterThreadedThing mLivePointDrawer;
	private Properties mProperties = new Properties();

    private List<Coordinate> mLoadedPoints;

	
	public StandalonePanel()
	{
        
		mFieldDrawer = new FieldDrawerPanel(new CoordinateWidget.FieldProperties2014());
		setLayout(new BorderLayout(0, 0));
		
		add(mFieldDrawer);
	}

	public FieldDrawer getFieldDrawer() {
		return mFieldDrawer.getFieldDrawer();
	}
    
    public void loadPointFile(final String aFile)
    {        
    	mLoadedPoints = CoordinateFileReader.readFile(aFile);
    	drawPoints();
    }
    
    public void drawPoints()
    {
    	if(mLoadedPoints == null)
    	{
    		return;
    	}
        if(mLivePointDrawer != null)
        {
        	mLivePointDrawer.terminate();
        }
        mLivePointDrawer = null;
        mLivePointDrawer = new GuiUpdaterThreadedThing(mFieldDrawer.getFieldDrawer(), mSleepTime);
        mLivePointDrawer.drawPoints(mLoadedPoints);
    }
	
}
