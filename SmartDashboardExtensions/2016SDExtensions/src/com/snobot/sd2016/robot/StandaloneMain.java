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

    static double height = 0;

    private StandaloneMain()
    {
        final RobotDrawer2016 drawerPanel = new RobotDrawer2016();

        JFrame frame = new JFrame();

        frame.setVisible(true);
        frame.setContentPane(drawerPanel);
        frame.setSize(1000, 1000);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent arg0)
            {
                // Intake Motor Speed
                if (arg0.getKeyChar() == 'n')
                {
                    mInakeMotorSpeed += .05;
                    drawerPanel.setInakeMotorSpeed(mInakeMotorSpeed);
                }
                else if (arg0.getKeyChar() == 'm')
                {
                    mInakeMotorSpeed -= .05;
                    drawerPanel.setInakeMotorSpeed(mInakeMotorSpeed);
                }

                // Intake Tilt Speed and Angle
                else if (arg0.getKeyChar() == 'd')
                {
                    mInakeTiltMotorSpeed += .05;
                    drawerPanel.setInakeTiltMotorSpeed(mInakeTiltMotorSpeed);

                    mIntakeTiltAngle += 5;
                    drawerPanel.setIntakeTiltAngle(mIntakeTiltAngle);
                }
                else if (arg0.getKeyChar() == 'a')
                {
                    mInakeTiltMotorSpeed -= .05;
                    drawerPanel.setInakeTiltMotorSpeed(mInakeTiltMotorSpeed);

                    mIntakeTiltAngle -= 5;
                    drawerPanel.setIntakeTiltAngle(mIntakeTiltAngle);
                }

                // Scaling Height
                else if (arg0.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    mScaleMotorSpeed += .05;
                    drawerPanel.setScaleMotorSpeed(mScaleMotorSpeed);

                    height -= 0.05;
                    drawerPanel.setExtensionHeight(height);
                }
                else if (arg0.getKeyCode() == KeyEvent.VK_UP)
                {
                    mScaleMotorSpeed -= .05;
                    drawerPanel.setScaleMotorSpeed(mScaleMotorSpeed);

                    height += 0.05;
                    drawerPanel.setExtensionHeight(height);
                }

                // Scale Tilt Speed and Angle
                else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    mScaleTiltMotorSpeed += .05;
                    drawerPanel.setScaleTiltMotorSpeed(mScaleTiltMotorSpeed);

                    mClimbTiltAngle += 5;
                    drawerPanel.setClimbTiltAngle(mClimbTiltAngle);
                }
                else if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    mScaleTiltMotorSpeed -= .05;
                    drawerPanel.setScaleTiltMotorSpeed(mScaleTiltMotorSpeed);

                    mClimbTiltAngle -= 5;
                    drawerPanel.setClimbTiltAngle(mClimbTiltAngle);
                }

                // Scale Motor
                // if (arg0.getKeyChar() == 'q')
                // {
                // mScaleMotorSpeed += .05;
                // drawerPanel.setScaleMotorSpeed(mScaleMotorSpeed);
                // }
                // else if (arg0.getKeyChar() == 'w')
                // {
                // mScaleMotorSpeed -= .05;
                // drawerPanel.setScaleMotorSpeed(mScaleMotorSpeed);
                // }

                // Intake Motor
                // else if (arg0.getKeyChar() == 'a')
                // {
                // mInakeMotorSpeed += .05;
                // drawerPanel.setInakeMotorSpeed(mInakeMotorSpeed);
                // }
                // else if (arg0.getKeyChar() == 's')
                // {
                // mInakeMotorSpeed -= .05;
                // drawerPanel.setInakeMotorSpeed(mInakeMotorSpeed);
                // }

                // Scale Tilt
                // else if (arg0.getKeyChar() == 'z')
                // {
                // mScaleTiltMotorSpeed += .05;
                // drawerPanel.setScaleTiltMotorSpeed(mScaleTiltMotorSpeed);
                // }
                // else if (arg0.getKeyChar() == 'x')
                // {
                // mScaleTiltMotorSpeed -= .05;
                // drawerPanel.setScaleTiltMotorSpeed(mScaleTiltMotorSpeed);
                // }
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
