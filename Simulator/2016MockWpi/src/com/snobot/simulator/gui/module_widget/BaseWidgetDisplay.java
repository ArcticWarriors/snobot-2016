package com.snobot.simulator.gui.module_widget;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.snobot.simulator.module_wrapper.ASensorWrapper;

public abstract class BaseWidgetDisplay<ItemType extends ASensorWrapper> extends JPanel
{

    protected Map<Integer, Container> mWidgetMap = new HashMap<>();

    public BaseWidgetDisplay(Map<Integer, ItemType> aMap)
    {
    	setLayout(new GridBagLayout());

    	int i = 0;
        for (Entry<Integer, ItemType> pair : aMap.entrySet())
        {
        	GridBagConstraints gc = new GridBagConstraints();
        	gc.gridy = i;
        	
            Container panelPair = createWidget(pair);
            mWidgetMap.put(pair.getKey(), panelPair);
            
            gc.gridx = 0;
            add(new JLabel("" + pair.getValue().getName()), gc);

            gc.gridx = 1;
            add(panelPair, gc);

            ++i;
        }
    }

    protected abstract Container createWidget(Entry<Integer, ItemType> pair);

    public abstract void update(Map<Integer, ItemType> aMap);

    public boolean isEmpty()
    {
        return mWidgetMap.isEmpty();
    }
}
