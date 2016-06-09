package com.snobot.coordinate_gui.desktop_app;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.ui.layers.ILayerManager.IFieldClickListener;

public class StandaloneContainer
{

    protected BaseCoordinateGui mGui;

    public StandaloneContainer(BaseCoordinateGui aGui)
    {
        mGui = aGui;

        mGui.getLayerManager().addFieldClickListener(mListener);
    }

    private IFieldClickListener mListener = new IFieldClickListener()
    {

        @Override
        public void fieldClicked(double aXFeet, double aYFeet)
        {
            System.out.println("Field Clicked at " + aXFeet + ", " + aYFeet);
        }
    };
}
