package com.snobot.coordinate_gui.desktop_app.config;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.snobot.coordinate_gui.desktop_app.trajectory.PathUtils;
import com.snobot.coordinate_gui.model.Coordinate;
import com.snobot.coordinate_gui.model.DataProvider;
import com.snobot.coordinate_gui.ui.layers.ILayerManager;
import com.snobot.coordinate_gui.ui.renderProps.CreatePointsLayerRenderProps;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.gen.PathGenerator;
import com.team254.lib.trajectory.gen.TrajectoryGenerator;
import com.team254.lib.trajectory.gen.TrajectoryGenerator.Config;
import com.team254.lib.trajectory.gen.WaypointSequence;
import com.team254.lib.trajectory.gen.WaypointSequence.Waypoint;

public class CreatePointsLayerConfigPanel extends JPanel
{
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final DataProvider<Coordinate> mTrajectoryConfigDataProvider;
    private final DataProvider<Coordinate> mTrajectoryPreviewDataProvider;
    private final CreatePointsLayerRenderProps mRenderProps;
    private final ILayerManager mLayerManager;

    private JTextField mCustomAngleField;
    private JTextField mDragSeperationField;
    private JRadioButton mRdbtnZeroDegrees = new JRadioButton("0 Degrees");
    private JRadioButton mRdbtnCalculateAngle = new JRadioButton("Calculate Angle");
    private JRadioButton mRdbtnCustomAngle = new JRadioButton("");

    private String mDefaultProfileDirectory = ".";
    private String mDefaultConfigDirectory = ".";

    private ActionListener mPointAngleTypeListener = new ActionListener()
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (mRdbtnZeroDegrees.isSelected())
            {
                mRenderProps.setAngleCalculationType(CreatePointsLayerRenderProps.AngleCalculationType.Zero);
                mCustomAngleField.setEnabled(false);
            }
            else if (mRdbtnCalculateAngle.isSelected())
            {
                mRenderProps.setAngleCalculationType(CreatePointsLayerRenderProps.AngleCalculationType.Calculate);
                mCustomAngleField.setEnabled(false);
            }
            else if (mRdbtnCustomAngle.isSelected())
            {
                mRenderProps.setAngleCalculationType(CreatePointsLayerRenderProps.AngleCalculationType.Custom);
                mCustomAngleField.setEnabled(true);
            }
            else
            {
                System.out.println("Unknown selection");
            }
        }
    };
    private JButton btnGeneratePath;

    public CreatePointsLayerConfigPanel(ILayerManager aLayerManager, CreatePointsLayerRenderProps aRenderProps,
            DataProvider<Coordinate> aTrajConfigDataProvider,
            DataProvider<Coordinate> aTrajPrevDataProvider)
    {
        mLayerManager = aLayerManager;
        mTrajectoryConfigDataProvider = aTrajConfigDataProvider;
        mTrajectoryPreviewDataProvider = aTrajPrevDataProvider;
        mRenderProps = aRenderProps;
        initComponents();
    }

    private void generateFromConfig()
    {
        TrajectoryGenerator.Config trajectoryConfig = new Config();
        trajectoryConfig.dt = .02;
        trajectoryConfig.max_acc = 120;
        trajectoryConfig.max_jerk = 480;
        trajectoryConfig.max_vel = 45;

        double wheelbase = 25.5 / 12;

        WaypointSequence waypoints = new WaypointSequence(1000);

        for (Coordinate coord : mTrajectoryConfigDataProvider.getAllData())
        {
            waypoints.addWaypoint(new Waypoint(coord.x * 12, coord.y * 12, coord.angle));
        }

        Path path = PathGenerator.makePath(waypoints, trajectoryConfig, wheelbase, "TempPath");

        mTrajectoryPreviewDataProvider.clear();
        for (Coordinate coord : PathUtils.getCoordinatesFromPath(path))
        {
            mTrajectoryPreviewDataProvider.addData(coord);
        }

        mLayerManager.render();
    }

    public String getDefaultProfileDirectory()
    {
        return mDefaultProfileDirectory;
    }

    public void setDefaultProfileDirectory(String aDirectory)
    {
        mDefaultProfileDirectory = aDirectory;
    }

    public String getDefaultConfigDirectory()
    {
        return mDefaultConfigDirectory;
    }

    public void setDefaultConfigDirectory(String aDirectory)
    {
        mDefaultConfigDirectory = aDirectory;
    }

    private void initComponents()
    {

        JPanel singlePointPanel = new JPanel();
        singlePointPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Single Point Angle", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));

        JLabel lblDragSeperation = new JLabel("Drag Seperation");

        mDragSeperationField = new JTextField();
        mDragSeperationField.setColumns(10);

        btnGeneratePath = new JButton("Generate Path");
        btnGeneratePath.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                generateFromConfig();
            }
        });
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addComponent(singlePointPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(mDragSeperationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE).addComponent(lblDragSeperation).addComponent(btnGeneratePath))
                        .addContainerGap(234, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(singlePointPanel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE).addGap(11))
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(btnGeneratePath)
                                                        .addGap(42)
                                                        .addComponent(lblDragSeperation)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(mDragSeperationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE).addGap(41))).addGap(34)));
        GridBagLayout gbl_singlePointPanel = new GridBagLayout();
        gbl_singlePointPanel.columnWidths = new int[]
        { 23, 0, 0 };
        gbl_singlePointPanel.rowHeights = new int[]
        { 38, 0, 38, 0 };
        gbl_singlePointPanel.columnWeights = new double[]
        { 0.0, 1.0, Double.MIN_VALUE };
        gbl_singlePointPanel.rowWeights = new double[]
        { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        singlePointPanel.setLayout(gbl_singlePointPanel);

        mRdbtnZeroDegrees = new JRadioButton("0 Degrees");
        mRdbtnZeroDegrees.setSelected(true);
        buttonGroup.add(mRdbtnZeroDegrees);
        GridBagConstraints gbc_rdbtnDegrees = new GridBagConstraints();
        gbc_rdbtnDegrees.anchor = GridBagConstraints.WEST;
        gbc_rdbtnDegrees.gridwidth = 2;
        gbc_rdbtnDegrees.fill = GridBagConstraints.VERTICAL;
        gbc_rdbtnDegrees.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnDegrees.gridx = 0;
        gbc_rdbtnDegrees.gridy = 0;
        singlePointPanel.add(mRdbtnZeroDegrees, gbc_rdbtnDegrees);


        mRdbtnCalculateAngle = new JRadioButton("Calculate Angle");
        buttonGroup.add(mRdbtnCalculateAngle);
        GridBagConstraints gbc_rdbtnCalculateAngle = new GridBagConstraints();
        gbc_rdbtnCalculateAngle.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnCalculateAngle.anchor = GridBagConstraints.WEST;
        gbc_rdbtnCalculateAngle.gridwidth = 2;
        gbc_rdbtnCalculateAngle.fill = GridBagConstraints.VERTICAL;
        gbc_rdbtnCalculateAngle.gridx = 0;
        gbc_rdbtnCalculateAngle.gridy = 1;
        singlePointPanel.add(mRdbtnCalculateAngle, gbc_rdbtnCalculateAngle);

        mRdbtnCustomAngle = new JRadioButton("");
        buttonGroup.add(mRdbtnCustomAngle);
        GridBagConstraints gbc_radioButton = new GridBagConstraints();
        gbc_radioButton.anchor = GridBagConstraints.WEST;
        gbc_radioButton.insets = new Insets(0, 0, 0, 5);
        gbc_radioButton.gridx = 0;
        gbc_radioButton.gridy = 2;
        singlePointPanel.add(mRdbtnCustomAngle, gbc_radioButton);

        mCustomAngleField = new JTextField();
        GridBagConstraints gbc_mCustomAngleField = new GridBagConstraints();
        gbc_mCustomAngleField.fill = GridBagConstraints.HORIZONTAL;
        gbc_mCustomAngleField.gridx = 1;
        gbc_mCustomAngleField.gridy = 2;
        singlePointPanel.add(mCustomAngleField, gbc_mCustomAngleField);
        mCustomAngleField.setColumns(10);
        setLayout(groupLayout);

        mRdbtnZeroDegrees.addActionListener(mPointAngleTypeListener);
        mRdbtnCalculateAngle.addActionListener(mPointAngleTypeListener);
        mRdbtnCustomAngle.addActionListener(mPointAngleTypeListener);
        mCustomAngleField.addActionListener(mPointAngleTypeListener);

    }
}
