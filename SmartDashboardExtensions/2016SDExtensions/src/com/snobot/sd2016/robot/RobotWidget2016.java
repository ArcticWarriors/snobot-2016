package com.snobot.sd2016.robot;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.snobot.sd.util.AutoUpdateWidget;
import com.snobot.sd2016.config.SmartDashBoardNames;

import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;

public class RobotWidget2016 extends AutoUpdateWidget
{
    private RobotDrawer2016 mDrawer;

    public RobotWidget2016(boolean aDebug, long update_ms)
    {
        super(false, 20);
        mDrawer = new RobotDrawer2016();
        setLayout(new BorderLayout());
        add(mDrawer, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void propertyChanged(Property arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void poll() throws Exception
    {
        double pot_percentage, roller_motor, roller_pivot_motor, scale_move_motor, scale_tilt_motor;

        pot_percentage = Robot.getTable().getNumber(SmartDashBoardNames.sPOT_PERCENTAGE, 0);
        roller_motor = Robot.getTable().getNumber(SmartDashBoardNames.sROLLER_MOTOR, 0);
        roller_pivot_motor = Robot.getTable().getNumber(SmartDashBoardNames.sPIVOT_MOTOR, 0);
        scale_move_motor = Robot.getTable().getNumber(SmartDashBoardNames.sSCALE_MOVE_MOTOR, 0);
        scale_tilt_motor = Robot.getTable().getNumber(SmartDashBoardNames.sSCALE_TILT_MOTOR, 0);

        mDrawer.setIntakeTiltAngle((pot_percentage * 0.9));
        mDrawer.setInakeMotorSpeed(roller_motor);
        mDrawer.setInakeTiltMotorSpeed(roller_pivot_motor);
        mDrawer.setScaleMotorSpeed(scale_move_motor);
        mDrawer.setScaleTiltMotorSpeed(scale_tilt_motor);
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

}
