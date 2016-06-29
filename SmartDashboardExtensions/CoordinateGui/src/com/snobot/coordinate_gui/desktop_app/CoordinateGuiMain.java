package com.snobot.coordinate_gui.desktop_app;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.yaml.snakeyaml.Yaml;

import com.snobot.coordinate_gui.desktop_app.config.CoordinateLayerConfigPanel;
import com.snobot.coordinate_gui.desktop_app.config.CreatePointsLayerConfigPanel;
import com.snobot.coordinate_gui.desktop_app.config.PlaybackConfigPanel;
import com.snobot.coordinate_gui.desktop_app.settings.GuiSettings;
import com.snobot.coordinate_gui.desktop_app.settings.ImmutablesRepresenter;
import com.snobot.coordinate_gui.ui.renderProps.CoordinateLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.CreatePointsLayerRenderProps;
import com.snobot.coordinate_gui.ui.renderProps.RobotLayerRenderProps;

public class CoordinateGuiMain extends JFrame
{
    private static final String sPROPERTIES_FILE = "gui_properties.yml";

    private CoordinateLayerRenderProps coordinateLayerRenderProps;
    private RobotLayerRenderProps robotLayerRenderProps;
    private CreatePointsLayerRenderProps createPontsLayerRenderProps;

    private TestCoordinateGui coordinateGui;
    private PlaybackConfigPanel playbackConfig;
    private CoordinateLayerConfigPanel coordinateLayerConfigPanel;
    private CreatePointsLayerConfigPanel createPointsConfigPanel;

    private CoordinateGuiMain()
    {
        GuiSettings settings = loadConfiguration();

        setLocation(settings.getFrameSettings().getFrameX(), settings.getFrameSettings().getFrameY());
        setSize(settings.getFrameSettings().getFrameWidth(), settings.getFrameSettings().getFrameHeight());

        coordinateLayerRenderProps = settings.getCoordinateLayerRenderProperties();
        robotLayerRenderProps = new RobotLayerRenderProps();
        createPontsLayerRenderProps = new CreatePointsLayerRenderProps();

        coordinateGui = new TestCoordinateGui(coordinateLayerRenderProps, robotLayerRenderProps, createPontsLayerRenderProps);
        playbackConfig = new PlaybackConfigPanel(coordinateGui);
        coordinateLayerConfigPanel = new CoordinateLayerConfigPanel(coordinateLayerRenderProps);
        createPointsConfigPanel = new CreatePointsLayerConfigPanel(coordinateGui.getLayerManager(),
                createPontsLayerRenderProps,
                coordinateGui.getCreatePointsDataProvider(), coordinateGui.getTrajectoryPreivewDataProvider());

        createPointsConfigPanel.setDefaultConfigDirectory(settings.getCreatePointsLayerSettings().getDefaultConfigDirectory());
        createPointsConfigPanel.setDefaultProfileDirectory(settings.getCreatePointsLayerSettings().getDefaultProfileDirectory());

        JPanel configContaner = new JPanel();
        configContaner.setLayout(new BoxLayout(configContaner, BoxLayout.Y_AXIS));
        configContaner.add(playbackConfig);
        configContaner.add(coordinateLayerConfigPanel);
        configContaner.add(createPointsConfigPanel);

        add(BorderLayout.CENTER, coordinateGui.getComponent());
        add(BorderLayout.EAST, configContaner);

        addWindowListener(mCloseListener);

    }

    private void saveConfiguration()
    {
        GuiSettings settings = new GuiSettings();
        settings.getFrameSettings().setFrameX(getX());
        settings.getFrameSettings().setFrameY(getY());
        settings.getFrameSettings().setFrameWidth(getWidth());
        settings.getFrameSettings().setFrameHeight(getHeight());

        settings.setCoordinateLayerRenderProperties(coordinateLayerRenderProps);

        settings.getCreatePointsLayerSettings().setDefaultProfileDirectory(createPointsConfigPanel.getDefaultProfileDirectory());
        settings.getCreatePointsLayerSettings().setDefaultConfigDirectory(createPointsConfigPanel.getDefaultConfigDirectory());

        try
        {
            Yaml yaml = new Yaml(new ImmutablesRepresenter());
            yaml.dump(settings, new FileWriter(new File(sPROPERTIES_FILE)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private GuiSettings loadConfiguration()
    {
        GuiSettings settings = null;

        try
        {
            Yaml yaml = new Yaml(new ImmutablesRepresenter());
            File file = new File(sPROPERTIES_FILE);

            if (file.exists())
            {
                settings = (GuiSettings) yaml.load(new FileReader(file));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (settings == null)
        {
            settings = new GuiSettings();
        }

        return settings;
    }

    private WindowListener mCloseListener = new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            saveConfiguration();
        }
    };

    public static void main(String[] args)
    {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                CoordinateGuiMain frame = new CoordinateGuiMain();
                frame.setVisible(true);
                frame.repaint();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }
}
