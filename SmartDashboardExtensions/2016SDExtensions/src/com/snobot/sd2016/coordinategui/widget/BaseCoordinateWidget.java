package com.snobot.sd2016.coordinategui.widget;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.snobot.sd.util.AutoUpdateWidget;
import com.snobot.sd2016.config.SmartDashBoardNames;
import com.snobot.sd2016.coordinategui.Coordinate;
import com.snobot.sd2016.coordinategui.FieldDrawerPanel;
import com.snobot.sd2016.coordinategui.FieldProperties;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;

public class BaseCoordinateWidget extends AutoUpdateWidget
{
    
    private FieldDrawerPanel mPanel;

    public BaseCoordinateWidget(boolean aDebug, FieldProperties aFieldProperties)
    {
        super(aDebug, 100);

        mPanel = new FieldDrawerPanel(aFieldProperties);
        
        setLayout(new BorderLayout());
        add(mPanel, BorderLayout.CENTER);
        
        addComponentListener(mResizeListener);
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
    
    /**
     * Resize listener.  Updates the scale of field panel when the panel has been
     * resized
     */
    private ComponentListener mResizeListener = new ComponentAdapter() 
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
          mPanel.updateScale();
        }
    };

    @Override
    protected void poll() throws Exception
    {
        Coordinate c = new Coordinate();
        c.x = Robot.getTable().getNumber(SmartDashBoardNames.sX_POSITION, 0) / 12.0;
        c.y = Robot.getTable().getNumber(SmartDashBoardNames.sY_POSITION, 0) / 12.0;
        c.angle = Robot.getTable().getNumber(SmartDashBoardNames.sORIENTATION, 0);
        mPanel.addPoint(c);
    }
}
