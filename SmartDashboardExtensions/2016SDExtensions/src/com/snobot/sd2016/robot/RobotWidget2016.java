package com.snobot.sd2016.robot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.snobot.sd.util.AutoUpdateWidget;
import com.snobot.sd2016.config.SmartDashBoardNames;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;

public class RobotWidget2016 extends AutoUpdateWidget
{
    public static final String NAME = "2016 Robot Widget";

    private RobotDrawer2016 mDrawer;

    public RobotWidget2016()
    {
        super(false, 200);
        setLayout(new BorderLayout());
        mDrawer = new RobotDrawer2016();
        add(mDrawer, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void propertyChanged(Property arg0)
    {
    }

    @Override
    protected void poll() throws Exception
    {
        double roller_pot_position, roller_motor, roller_pivot_motor;
        double scale_pot_angle, scale_move_motor, scale_tilt_motor, scale_height;

        roller_pot_position = Robot.getTable().getNumber(SmartDashBoardNames.sHARVESTER_POT_PERCENTAGE, 0);
        roller_motor = Robot.getTable().getNumber(SmartDashBoardNames.sHARVESTER_ROLLER_MOTOR, 0);
        roller_pivot_motor = Robot.getTable().getNumber(SmartDashBoardNames.sHARVESTER_PIVOT_MOTOR, 0);

        scale_pot_angle = Robot.getTable().getNumber(SmartDashBoardNames.sSCALNG_CURRENT_ANGLE, 0);
        scale_move_motor = Robot.getTable().getNumber(SmartDashBoardNames.sSCALE_MOVE_MOTOR, 0);
        scale_tilt_motor = Robot.getTable().getNumber(SmartDashBoardNames.sSCALE_TILT_MOTOR, 0);
        scale_height = Robot.getTable().getNumber(SmartDashBoardNames.sSCALE_CURRENT_POSITION, 0);

        if (mDrawer != null)
        {
            mDrawer.setInakeMotorSpeed(roller_motor);
            mDrawer.setIntakeTiltAngle(-(roller_pot_position * 0.9));
            mDrawer.setInakeTiltMotorSpeed(roller_pivot_motor);

            mDrawer.setScaleMotorSpeed(scale_move_motor);
            mDrawer.setScaleTiltMotorSpeed(scale_tilt_motor);
            mDrawer.setClimbTiltAngle(scale_pot_angle);
            mDrawer.setExtensionHeight(scale_height);
        }

        repaint();
    }

    @Override
    public void init()
    {
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent arg0)
            {
                mDrawer.updateSize();
            }
        });
    }

}
