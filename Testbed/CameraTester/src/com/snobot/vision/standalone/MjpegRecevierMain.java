package com.snobot.vision.standalone;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.snobot.vision.standalone.MjpegReceiver.ImageReceiver;

public class MjpegRecevierMain
{
    private MjpegReceiver reciever;
    private VisionTestPanel testPanel;

    private MjpegReceiver.ImageReceiver imageReciver = new ImageReceiver()
    {

        @Override
        public void onImage(Image image)
        {
            testPanel.setOriginalImage(image);
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

    public MjpegRecevierMain(String urlAddress)
    {
        reciever = new MjpegReceiver();
        testPanel = new VisionTestPanel();
        List<ImageReceiver> imageReceivers = Arrays.asList(imageReciver);

        Thread receiverThread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                reciever.runReceiver(urlAddress, imageReceivers);
            }
        });
        receiverThread.start();

        JFrame frame = new JFrame();
        frame.addWindowListener(closingListener);
        frame.setLayout(new BorderLayout());
        frame.add(testPanel, BorderLayout.CENTER);
        frame.setSize(700, 540);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        String urlAddress = "http://127.0.0.1:5800";
        if (args.length == 1)
        {
            urlAddress = args[0];
        }
        new MjpegRecevierMain(urlAddress);
    }
}
