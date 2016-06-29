package com.snobot.coordinate_gui.desktop_app;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snobot.coordinate_gui.desktop_app.config.CoordinateLayerConfigPanel;
import com.snobot.coordinate_gui.desktop_app.config.CreatePointsLayerConfigPanel;
import com.snobot.coordinate_gui.desktop_app.config.PlaybackConfigPanel;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.CreatePointsLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class CoordinateGuiMain extends JFrame
{
    private CoordinateGuiMain()
    {
        CoordinateLayerRenderProps coordinateLayerRenderProps = new CoordinateLayerRenderProps();
        RobotLayerRenderProps robotLayerRenderProps = new RobotLayerRenderProps();
        CreatePointsLayerRenderProps createPontsLayerRenderProps = new CreatePointsLayerRenderProps();

        TestCoordinateGui coordinateGui = new TestCoordinateGui(coordinateLayerRenderProps, robotLayerRenderProps, createPontsLayerRenderProps);
        PlaybackConfigPanel playbackConfig = new PlaybackConfigPanel(coordinateGui);
        CoordinateLayerConfigPanel coordinateLayerConfigPanel = new CoordinateLayerConfigPanel(coordinateLayerRenderProps);
        CreatePointsLayerConfigPanel createPointsConfigPanel = new CreatePointsLayerConfigPanel(coordinateGui.getLayerManager(),
                createPontsLayerRenderProps,
                coordinateGui.getCreatePointsDataProvider(), coordinateGui.getTrajectoryPreivewDataProvider());

        JPanel configContaner = new JPanel();
        configContaner.setLayout(new BoxLayout(configContaner, BoxLayout.Y_AXIS));
        configContaner.add(playbackConfig);
        configContaner.add(coordinateLayerConfigPanel);
        configContaner.add(createPointsConfigPanel);

        add(BorderLayout.CENTER, coordinateGui.getComponent());
        add(BorderLayout.EAST, configContaner);
    }

    public static void main(String[] args)
    {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                CoordinateGuiMain frame = new CoordinateGuiMain();
                frame.pack();
                frame.setVisible(true);
                frame.repaint();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }
}
