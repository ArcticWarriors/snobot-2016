package com.snobot.vision.standalone;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;

import com.snobot.vision.VisionAlgorithm;
import com.snobot.vision.VisionAlgorithm.ProcessedImageListener;

public class VisionTestPanel extends JPanel
{
    private JLabel originalImageLabel;
    private JLabel thresholdImageLabel;
    private VisionAlgorithm algorithm = new VisionAlgorithm();

    public VisionTestPanel(VisionAlgorithm aAlgorithm)
    {
        originalImageLabel = new JLabel();
        thresholdImageLabel = new JLabel();
        algorithm = aAlgorithm;
        algorithm.addListener(imageListener);

        add(originalImageLabel);
        add(thresholdImageLabel);
    }

    public void setOriginalImage(BufferedImage image)
    {
        algorithm.processImage(image);
    }

    private VisionAlgorithm.ProcessedImageListener imageListener = new ProcessedImageListener()
    {

        @Override
        public void onCalculation(Mat original, Mat postThreshold)
        {
            originalImageLabel.setIcon(new ImageIcon(matToImage(original)));
            thresholdImageLabel.setIcon(new ImageIcon(matToImage(postThreshold)));
        }
    };

    public static BufferedImage matToImage(Mat m)
    {
        // Fastest code
        // output can be assigned either to a BufferedImage or to an Image

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

}
