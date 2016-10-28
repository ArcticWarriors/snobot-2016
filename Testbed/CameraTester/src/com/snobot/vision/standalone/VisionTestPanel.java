package com.snobot.vision.standalone;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisionTestPanel extends JPanel
{
    private JLabel originalImageLabel;

    public VisionTestPanel(String file)
    {
        originalImageLabel = new JLabel();

        try
        {
            Image image = ImageIO.read(new File(file));
            originalImageLabel.setIcon(new ImageIcon(image));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        add(originalImageLabel);
    }

}
