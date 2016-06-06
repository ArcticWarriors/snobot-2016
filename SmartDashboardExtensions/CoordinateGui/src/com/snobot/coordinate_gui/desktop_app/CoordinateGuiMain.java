package com.snobot.coordinate_gui.desktop_app;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class CoordinateGuiMain extends JFrame
{
    private CoordinateGuiMain()
    {
        TestCoordinateGui coordinateGui = new TestCoordinateGui();
        StandaloneContainer container = new StandaloneContainer(coordinateGui);

        add(BorderLayout.CENTER, coordinateGui.getComponent());

        container.drawPoints();
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
