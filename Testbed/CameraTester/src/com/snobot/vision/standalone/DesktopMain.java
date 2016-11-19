package com.snobot.vision.standalone;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.yaml.snakeyaml.Yaml;

import com.snobot.vision.HslThreshold;
import com.snobot.vision.VisionAlgorithm;

public class DesktopMain
{

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Yaml yaml = new Yaml();
        Map<String, Object> config = (Map<String, Object>) yaml.load(new FileInputStream("config/test_images.yml"));
        List<String> files = (List<String>) config.get("images");
        boolean oneAtATime = Boolean.parseBoolean(config.get("one_at_a_time").toString());
        String thresholdsFile = (String) config.get("threshold_config");

        Map<String, Map<String, Object>> thresholdConfig = (Map<String, Map<String, Object>>) yaml.load(new FileInputStream(thresholdsFile));
        HslThreshold minThreshold = (HslThreshold) thresholdConfig.get("thresholds").get("min");
        HslThreshold maxThreshold = (HslThreshold) thresholdConfig.get("thresholds").get("max");

        for (String file : files)
        {
            BufferedImage image = ImageIO.read(new File(file));

            VisionAlgorithm algorithm = new VisionAlgorithm();
            algorithm.setThresholds(minThreshold, maxThreshold);

            VisionTestPanel testPanel = new VisionTestPanel(algorithm);
            testPanel.setOriginalImage(image);

            if (oneAtATime)
            {
                JDialog frame = new JDialog();
                frame.setTitle(file);
                frame.setModal(true);
                frame.setLayout(new BorderLayout());
                frame.add(testPanel, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
            else
            {
                JFrame frame = new JFrame();
                frame.setTitle(file);
                frame.setLayout(new BorderLayout());
                frame.add(testPanel, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
        }

    }
}
