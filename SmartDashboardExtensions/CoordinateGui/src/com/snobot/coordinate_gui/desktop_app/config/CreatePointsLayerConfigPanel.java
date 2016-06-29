package com.snobot.coordinate_gui.desktop_app.config;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

public class CreatePointsLayerConfigPanel extends JPanel
{
    private static final String sDEFAULT_DIRECTORY = "C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/trajectory_config";
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
                System.out.println("Unknown selectio");
            }
        }
    };

    public CreatePointsLayerConfigPanel(ILayerManager aLayerManager, CreatePointsLayerRenderProps aRenderProps,
            DataProvider<Coordinate> aTrajConfigDataProvider,
            DataProvider<Coordinate> aTrajPrevDataProvider)
    {
        mLayerManager = aLayerManager;
        mTrajectoryConfigDataProvider = aTrajConfigDataProvider;
        mTrajectoryPreviewDataProvider = aTrajPrevDataProvider;
        mRenderProps = aRenderProps;
        initComponents();
        
        // String config_path = "position1_post_defense.yml";
        // String preview_path = "Position1ToLowGoal.csv";

        // String config_path = "position2_post_defense.yml";
        // String preview_path = "Position2ToLowGoal.csv";

        // String config_path = "position3_post_defense.yml";
        // String preview_path = "Position3ToLowGoal.csv";

        // String config_path = "position4_post_defense.yml";
        // String preview_path = "Position4ToLowGoal.csv";

        String config_path = "position5_post_defense.yml";
        String preview_path = "Position5ToLowGoal.csv";

        // loadTrajectoryPreview("C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/traj/LowBarToLowGoal.csv");
        // loadTrajectoryPreview("C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/traj/Position2ToLowGoal.csv");
        loadTrajectoryPreview("C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/traj/" + preview_path);

        mTrajectoryConfigDataProvider.clear();

        System.out.println("\n\n");
        for (Coordinate coord : TrajectoryConfigReader.load("C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/trajectory_config/" + config_path))
        {
            mTrajectoryConfigDataProvider.addData(coord);
            System.out.println(coord);
        }

        System.out.println(mTrajectoryConfigDataProvider.getAllData());
    }

    private void clearTrajectoryPreivew()
    {
        mTrajectoryPreviewDataProvider.clear();
    }

    private void loadTrajectoryPreview(String aFile)
    {
        clearTrajectoryPreivew();

        for (Coordinate coord : PathUtils.getCoordinatesFromFile(aFile))
        {
            mTrajectoryPreviewDataProvider.addData(coord);
        }

        System.out.println(mTrajectoryPreviewDataProvider.getAllData());

        mLayerManager.render();
    }

    private void loadProfileBtnPressed()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(sDEFAULT_DIRECTORY));
        int returnValue = fc.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            mTrajectoryConfigDataProvider.clear();

            for (Coordinate coord : TrajectoryConfigReader.load(fc.getSelectedFile().getAbsolutePath()))
            {
                mTrajectoryConfigDataProvider.addData(coord);
            }

            loadTrajectoryPreview(fc.getSelectedFile().getAbsolutePath());
        }
    }

    private void saveProfileBtnPressed()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(sDEFAULT_DIRECTORY));
        int returnValue = fc.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            TrajectoryConfigReader.dump(mTrajectoryConfigDataProvider.getAllData(), fc.getSelectedFile().getAbsolutePath());
            loadTrajectoryPreview(fc.getSelectedFile().getAbsolutePath());
        }
    }

    private void initComponents()
    {

        JButton btnLoadProfile = new JButton("Load Profile");
        btnLoadProfile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                loadProfileBtnPressed();
            }
        });

        JButton btnSaveProfile = new JButton("Save Profile");
        btnSaveProfile.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveProfileBtnPressed();
            }
        });

        JPanel singlePointPanel = new JPanel();
        singlePointPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Single Point Angle", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));

        JLabel lblDragSeperation = new JLabel("Drag Seperation");

        mDragSeperationField = new JTextField();
        mDragSeperationField.setColumns(10);
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
                                        .addComponent(btnSaveProfile)
                                        .addComponent(btnLoadProfile)
                                        .addComponent(lblDragSeperation)
                                        .addComponent(mDragSeperationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
                groupLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(singlePointPanel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE).addGap(11))
                                        .addGroup(
                                                groupLayout
                                                        .createSequentialGroup()
                                                        .addComponent(btnSaveProfile)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(btnLoadProfile)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
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
