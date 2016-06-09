package com.snobot.coordinate_gui.desktop_app.config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.snobot.coordinate_gui.BaseCoordinateGui;
import com.snobot.coordinate_gui.desktop_app.CoordinateFileReader;
import com.snobot.coordinate_gui.desktop_app.ThreadedCoordinateUpdater;
import com.snobot.coordinate_gui.model.Coordinate;

public class PlaybackConfigPanel extends JPanel
{
    private JTextField mFileDisplay;
    private JSpinner mUpdateIntervalSelector;
    private ThreadedCoordinateUpdater mLivePointDrawer;

    public PlaybackConfigPanel(BaseCoordinateGui aGui)
    {
        initComponents();

        mLivePointDrawer = new ThreadedCoordinateUpdater(aGui);

        mFileDisplay.setText("TestFiles/xxx.log");
        mUpdateIntervalSelector.setValue(5);
    }

    private void onLoadFileBtn()
    {
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            mFileDisplay.setText(fc.getName());
        }
    }

    private void onStartBtn()
    {
        List<Coordinate> loadedPoints = CoordinateFileReader.readFile(mFileDisplay.getText());
        mLivePointDrawer.drawPoints(loadedPoints);
    }

    private void onStopBtn()
    {
        mLivePointDrawer.terminate();
    }

    private void onIntervalChange(ChangeEvent e)
    {
        Number value = (Number) mUpdateIntervalSelector.getValue();
        mLivePointDrawer.setSleepTime(value.longValue());
    }

    private void initComponents()
    {

        mFileDisplay = new JTextField();
        mFileDisplay.setEnabled(false);
        mFileDisplay.setEditable(false);
        mFileDisplay.setColumns(10);

        mUpdateIntervalSelector = new JSpinner();
        mUpdateIntervalSelector.setModel(new SpinnerNumberModel(new Long(5), new Long(0), new Long(1000), new Long(1)));
        mUpdateIntervalSelector.addChangeListener(new ChangeListener()
        {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                onIntervalChange(e);
            }
        });

        JButton btnLoadFile = new JButton("Load File");
        btnLoadFile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onLoadFileBtn();
            }
        });

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onStartBtn();
            }
        });

        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                onStopBtn();
            }
        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(
                                                groupLayout.createSequentialGroup().addComponent(btnStart)
                                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnStop)
                                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(mUpdateIntervalSelector))
                                        .addComponent(mFileDisplay, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnLoadFile).addContainerGap(80, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(mFileDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLoadFile))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnStart)
                                        .addComponent(btnStop)
                                        .addComponent(mUpdateIntervalSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)).addContainerGap(237, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }
}
