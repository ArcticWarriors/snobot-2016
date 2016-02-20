package com.snobot.sd2016.coordinategui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.snobot.sd2016.coordinategui.pointDrawers.FadedPointDrawer;
import com.snobot.sd2016.coordinategui.pointDrawers.PointDrawer;

public class FieldDrawerPanel extends JPanel
{
    private double mPointMemory;

    private PointDrawer mPointDrawer;
    
    private FieldDrawer mFieldDrawer;
    private Color mDefaultColor;

    private JTextField mXField;
    private JTextField mYField;
    private JTextField mAngleField;
    
    public FieldDrawerPanel(FieldProperties aFieldProps)
    {
        mPointMemory = 300;
        mFieldDrawer = new FieldDrawer(aFieldProps);

        mPointDrawer = new FadedPointDrawer(mFieldDrawer);
        
        setLayout(new BorderLayout());
        add(mFieldDrawer);
        
        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        
        JLabel lblX = new JLabel("X");
        panel.add(lblX);
        
        mXField = new JTextField();
        panel.add(mXField);
        mXField.setColumns(5);
        
        JLabel lblY = new JLabel("Y");
        panel.add(lblY);
        
        mYField = new JTextField();
        panel.add(mYField);
        mYField.setColumns(5);
        
        JLabel lblAngle = new JLabel("Angle");
        panel.add(lblAngle);
        
        mAngleField = new JTextField();
        mAngleField.setColumns(5);
        panel.add(mAngleField);
        addComponentListener(mResizeListener);
    }
    
    /**
     * Sets the number of points to draw when the drawing mode is Fade
     * @param value The number of points to use in fade mode
     */
    public void setPointMemory(Integer value)
    {
        double dVal = value;
        setPointMemory(dVal);
    }
    public void setPointMemory(Double value)
    {
        mPointMemory = value;
    }
    
    public double getPointMemory()
    {
        return mPointMemory;
    }

    public FieldDrawer getFieldDrawer() {
        return mFieldDrawer;
    }

    public void clearPoints() {
        mFieldDrawer.clearPoints();
    }


    public void addPoint(Coordinate aCoord) {
        mFieldDrawer.addPoint(aCoord);

        mXField.setText(    String.format("%.2f", aCoord.x));
        mYField.setText(    String.format("%.2f", aCoord.y));
        mAngleField.setText(String.format("%.2f", aCoord.angle));
    }


    public void updateScale() {
        mFieldDrawer.updateScale();
    }

    public Color getPointColor() {
        return mDefaultColor;
    }


    public void setPointColor(Color aColor) {
        mDefaultColor = aColor;
        mPointDrawer.setColor(aColor);
    }

    
    /**
     * Listens for when the panel is resized so the scall can be updated.
     */
    private ComponentListener mResizeListener = new ComponentAdapter() {

        @Override
        public void componentResized(ComponentEvent e)
        {
            updateScale();
//            System.out.println("Resizing " + getSize());
        }
    };
}
