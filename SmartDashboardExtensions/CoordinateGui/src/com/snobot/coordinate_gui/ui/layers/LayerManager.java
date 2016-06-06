package com.snobot.coordinate_gui.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.snobot.coordinate_gui.model.PixelConverter;

public class LayerManager extends JPanel
{
    protected final List<ILayer> mLayers;
    protected PixelConverter mConverter;
    protected Object mLock;

    protected ComponentAdapter mResizeListener = new ComponentAdapter()
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            mConverter.updateScaleFactor(getWidth(), getHeight(), 27, 54);
            repaint();
        }
    };

    public LayerManager(PixelConverter aConverter, Object aLock)
    {
        mLayers = new ArrayList<>();
        mConverter = aConverter;
        mLock = aLock;
        addComponentListener(mResizeListener);
    }

    public void addLayer(ILayer aLayer)
    {
        mLayers.add(aLayer);
    }

    @Override
    public Dimension getPreferredSize()
    {
        Dimension output = null;

        for (ILayer layer : mLayers)
        {
            Dimension d = layer.getPreferredSize();
            if (d != null)
            {
                output = d;
            }
        }

        return output;
    }

    @Override
    public void paint(Graphics g)
    {
        synchronized (mLock)
        {
            Graphics2D graphics = (Graphics2D) g;

            for (ILayer layer : mLayers)
            {
                layer.render(graphics);
            }
        }
    }

}
