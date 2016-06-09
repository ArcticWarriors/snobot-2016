package com.snobot.coordinate_gui.desktop_app.config;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;

public class CoordinateLayerConfigPanel extends JPanel
{
    protected CoordinateLayerRenderProps mRenderProperties;

    protected JButton mPointColorBtn;
    protected JFormattedTextField mPointSizeField;
    protected JFormattedTextField mPointMemoryField;
    protected JCheckBox mFadeOverTimeCheckbox;

    public CoordinateLayerConfigPanel(CoordinateLayerRenderProps coordinateLayerRenderProps)
    {
        mRenderProperties = coordinateLayerRenderProps;
        initComponents();

        loadSettingsFromProperties();
    }

    private void loadSettingsFromProperties()
    {
        mPointSizeField.setValue(mRenderProperties.getPointSize());
        mPointMemoryField.setValue(mRenderProperties.getPointMemory());
        mPointColorBtn.setBackground(mRenderProperties.getPointColor());
        mFadeOverTimeCheckbox.setSelected(mRenderProperties.isFadeOverTime());
    }

    private void onPointColorPressed()
    {
        Color newColor = JColorChooser.showDialog(this, "Point Color", mRenderProperties.getPointColor());
        if (newColor != null)
        {
            mRenderProperties.setPointColor(newColor);
            loadSettingsFromProperties();
        }
    }

    private void onPointSizeChanged()
    {
        Number value = (Number) mPointSizeField.getValue();
        mRenderProperties.setPointSize(value.intValue());
        loadSettingsFromProperties();
    }

    private void onPointMemoryChanged()
    {
        Number value = (Number) mPointMemoryField.getValue();
        mRenderProperties.setPointMemory(value.intValue());
        loadSettingsFromProperties();
    }

    private void onFadeOverTimePressed()
    {
        mRenderProperties.setFadeOverTime(mFadeOverTimeCheckbox.isSelected());
        loadSettingsFromProperties();
    }

    private void initComponents()
    {

        JLabel lblFadeTime = new JLabel("Point Size");
        JLabel lblPointMemory = new JLabel("Point Memory");

        // Point Size
        {
            NumberFormat format = NumberFormat.getInstance();
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(0);
            formatter.setMaximum(30);
            formatter.setAllowsInvalid(false);

            mPointSizeField = new JFormattedTextField(formatter);
            mPointSizeField.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    onPointSizeChanged();
                }
            });
            mPointSizeField.addFocusListener(new FocusAdapter()
            {
                @Override
                public void focusLost(FocusEvent e)
                {
                    onPointSizeChanged();
                }
            });
        }

        // Point Memory Field
        {
            NumberFormat format = NumberFormat.getInstance();
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setMinimum(0);
            formatter.setMaximum(1000);
            formatter.setAllowsInvalid(false);

            mPointMemoryField = new JFormattedTextField(formatter);
            mPointMemoryField.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    onPointMemoryChanged();
                }
            });
            mPointMemoryField.addFocusListener(new FocusAdapter()
            {
                @Override
                public void focusLost(FocusEvent e)
                {
                    onPointMemoryChanged();
                }
            });
        }

        JLabel lblPointColor = new JLabel("Point Color");

        mPointColorBtn = new JButton("");
        mPointColorBtn.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                onPointColorPressed();
            }
        });

        mFadeOverTimeCheckbox = new JCheckBox("Fade Over Time");
        mFadeOverTimeCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
        mFadeOverTimeCheckbox.addItemListener(new ItemListener()
        {

            @Override
            public void itemStateChanged(ItemEvent e)
            {
                onFadeOverTimePressed();
            }
        });


        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(mFadeOverTimeCheckbox, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addGroup(
                                                                groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblPointMemory)
                                                                        .addComponent(lblFadeTime).addComponent(lblPointColor))
                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                        .addGroup(
                                                                groupLayout
                                                                        .createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(mPointColorBtn, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                                                        .addGroup(
                                                                                groupLayout
                                                                                        .createParallelGroup(Alignment.LEADING, false)
                                                                                        .addComponent(mPointSizeField, GroupLayout.DEFAULT_SIZE, 79,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(mPointMemoryField, GroupLayout.DEFAULT_SIZE,
                                                                                                79, Short.MAX_VALUE))))).addGap(286)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.BASELINE)
                                        .addComponent(mPointSizeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE).addComponent(lblFadeTime))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblPointMemory)
                                        .addComponent(mPointMemoryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(
                                groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(mPointColorBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPointColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(mFadeOverTimeCheckbox).addContainerGap(198, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }
}
