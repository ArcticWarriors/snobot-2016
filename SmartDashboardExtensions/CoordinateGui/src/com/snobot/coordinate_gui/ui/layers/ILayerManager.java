package com.snobot.coordinate_gui.ui.layers;

public interface ILayerManager
{
    public interface IFieldClickListener
    {
        public void fieldClicked(double aXFeet, double aYFeet);
    }

    public void addLayer(ILayer aLayer);

    public void addFieldClickListener(IFieldClickListener aListener);

    public void removeFieldClickListener(IFieldClickListener aListener);
}
