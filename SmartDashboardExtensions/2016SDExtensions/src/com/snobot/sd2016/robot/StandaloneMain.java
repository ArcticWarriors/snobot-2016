package com.snobot.sd2016.robot;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class StandaloneMain
{
    private double mScaleMotorSpeed;
    private double mInakeMotorSpeed;
    private double mScaleTiltMotorSpeed;
    private double mInakeTiltMotorSpeed;

    private double mClimbTiltAngle;
    private double mIntakeTiltAngle;

    private StandaloneMain()
    {
        final RobotDrawer2016 drawerPanel = new RobotDrawer2016();
        

        JFrame frame = new JFrame();

        frame.setVisible(true);
        frame.setContentPane(drawerPanel);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent arg0)
            {

                // Scale Motor
                if (arg0.getKeyChar() == 'q')
                {
                    mScaleMotorSpeed += .05;
                    drawerPanel.setScaleMotorSpeed(mScaleMotorSpeed);
                }
                else if (arg0.getKeyChar() == 'w')
                {
                    mScaleMotorSpeed -= .05;
                    drawerPanel.setScaleMotorSpeed(mScaleMotorSpeed);
                }

                // Intake Motor
                else if (arg0.getKeyChar() == 'a')
                {
                    mInakeMotorSpeed += .05;
                    drawerPanel.setInakeMotorSpeed(mInakeMotorSpeed);
                }
                else if (arg0.getKeyChar() == 's')
                {
                    mInakeMotorSpeed -= .05;
                    drawerPanel.setInakeMotorSpeed(mInakeMotorSpeed);
                }

                // Scale Tilt
                else if (arg0.getKeyChar() == 'z')
                {
                    mScaleTiltMotorSpeed += .05;
                    drawerPanel.setScaleTiltMotorSpeed(mScaleTiltMotorSpeed);
                }
                else if (arg0.getKeyChar() == 'x')
                {
                    mScaleTiltMotorSpeed -= .05;
                    drawerPanel.setScaleTiltMotorSpeed(mScaleTiltMotorSpeed);
                }

                // Intake Tilt
                else if (arg0.getKeyChar() == 'o')
                {
                    mInakeTiltMotorSpeed += .05;
                    drawerPanel.setInakeTiltMotorSpeed(mInakeTiltMotorSpeed);
                }
                else if (arg0.getKeyChar() == 'p')
                {
                    mInakeTiltMotorSpeed -= .05;
                    drawerPanel.setInakeTiltMotorSpeed(mInakeTiltMotorSpeed);
                }

                // Scale Tilt Angle
                else if (arg0.getKeyChar() == 'k')
                {
                    mClimbTiltAngle += 5;
                    drawerPanel.setClimbTiltAngle(mClimbTiltAngle);
                }
                else if (arg0.getKeyChar() == 'l')
                {
                    mClimbTiltAngle -= 5;
                    drawerPanel.setClimbTiltAngle(mClimbTiltAngle);
                }

                // Intake Tilt Angle
                else if (arg0.getKeyChar() == 'n')
                {
                    mIntakeTiltAngle += 5;
                    drawerPanel.setIntakeTiltAngle(mIntakeTiltAngle);
                }
                else if (arg0.getKeyChar() == 'm')
                {
                    mIntakeTiltAngle -= 5;
                    drawerPanel.setIntakeTiltAngle(mIntakeTiltAngle);
                }

                System.out.println(drawerPanel);
                drawerPanel.repaint();
            }
        });
    }

    public static void main(String[] args)
    {
        new StandaloneMain();
    }
}
