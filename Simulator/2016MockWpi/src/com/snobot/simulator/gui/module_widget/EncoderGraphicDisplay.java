package com.snobot.simulator.gui.module_widget;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.snobot.simulator.SensorActuatorRegistry.EncoderPair;
import com.snobot.simulator.module_wrapper.EncoderWrapper;

public class EncoderGraphicDisplay extends BaseWidgetDisplay<EncoderPair, EncoderWrapper>
{
    private class EncoderWrapperDisplay extends JPanel
    {

        private JTextField mDistanceField;

        public EncoderWrapperDisplay()
        {
            mDistanceField = new JTextField(15);
            add(mDistanceField);
        }

        public void updateDisplay(EncoderWrapper aValue)
        {
            if(aValue.isHookedUp())
            {
                mDistanceField.setText("" + aValue.getRaw());
            }
            else
            {
                mDistanceField.setText("Not Hooked Up");
            }
        }
    }

    public EncoderGraphicDisplay(Map<EncoderPair, EncoderWrapper> map)
    {
        super(map);
        setBorder(new TitledBorder("Encoders (Digital Ports)"));
    }

    @Override
    public void update(Map<EncoderPair, EncoderWrapper> aMap)
    {
        for (Entry<EncoderPair, EncoderWrapper> pair : aMap.entrySet())
        {
            ((EncoderWrapperDisplay) mWidgetMap.get(pair.getKey())).updateDisplay(pair.getValue());
        }
    }

    @Override
    protected EncoderWrapperDisplay createWidget(Entry<EncoderPair, EncoderWrapper> pair)
    {
        return new EncoderWrapperDisplay();
    }
}
