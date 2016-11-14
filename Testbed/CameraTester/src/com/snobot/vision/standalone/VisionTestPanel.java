package com.snobot.vision.standalone;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisionTestPanel extends JPanel
{
    private JLabel originalImageLabel;

    public VisionTestPanel()
    {
        originalImageLabel = new JLabel();
        add(originalImageLabel);
    }

    public void setOriginalImage(Image image)
    {
        originalImageLabel.setIcon(new ImageIcon(image));
    }

}
