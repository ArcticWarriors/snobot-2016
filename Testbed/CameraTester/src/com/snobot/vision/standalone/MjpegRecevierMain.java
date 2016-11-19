package com.snobot.vision.standalone;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.opencv.core.Core;
import org.yaml.snakeyaml.Yaml;

import com.snobot.vision.HslThreshold;
import com.snobot.vision.VisionAlgorithm;
import com.snobot.vision.standalone.MjpegReceiver.ImageReceiver;
import com.snobot.vision.standalone.panels.HslTuningPanel;

public class MjpegRecevierMain
{
    private MjpegReceiver reciever;
    private VisionTestPanel visionPanel;
    private HslTuningPanel tuningPanel;
    private VisionAlgorithm visionAlgorithm;

    private MjpegReceiver.ImageReceiver imageReciver = new ImageReceiver()
    {

        @Override
        public void onImage(BufferedImage image)
        {
            visionPanel.setOriginalImage(image);
        }
    };

    private WindowListener closingListener = new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            reciever.stop();
            e.getWindow().dispose();
        }
    };

    @SuppressWarnings("unchecked")
    public MjpegRecevierMain(String urlAddress, String thresholdConfigFile) throws FileNotFoundException
    {
        Yaml yaml = new Yaml();
        Map<String, Map<String, Object>> thresholdConfig = (Map<String, Map<String, Object>>) yaml.load(new FileInputStream(thresholdConfigFile));
        HslThreshold minThreshold = (HslThreshold) thresholdConfig.get("thresholds").get("min");
        HslThreshold maxThreshold = (HslThreshold) thresholdConfig.get("thresholds").get("max");

        visionAlgorithm = new VisionAlgorithm();
        reciever = new MjpegReceiver();
        visionPanel = new VisionTestPanel(visionAlgorithm);
        tuningPanel = new HslTuningPanel();
        tuningPanel.setListener(visionAlgorithm);
        tuningPanel.setThresholds(minThreshold, maxThreshold);

        List<ImageReceiver> imageReceivers = Arrays.asList(imageReciver);

        JFrame frame = new JFrame();
        Thread receiverThread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    reciever.runReceiver(urlAddress, imageReceivers);
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(frame, "Error running receiver", "Error", JOptionPane.ERROR_MESSAGE, null);
                    frame.dispose();
                }
            }
        });
        receiverThread.start();

        frame.addWindowListener(closingListener);
        frame.setLayout(new BorderLayout());
        frame.add(visionPanel, BorderLayout.CENTER);
        frame.add(tuningPanel, BorderLayout.NORTH);
        frame.setSize(700, 540);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String urlAddress = "http://127.0.0.1:5800";
        String thresholdConfig = "config/test_images_original_threshold.yml";
        if (args.length == 1)
        {
            urlAddress = args[0];
        }
        new MjpegRecevierMain(urlAddress, thresholdConfig);
    }
}
