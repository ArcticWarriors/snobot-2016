package com.snobot.vision.standalone;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.yaml.snakeyaml.Yaml;

public class Main
{

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException
    {
        Yaml yaml = new Yaml();
        Map<String, Object> config = (Map<String, Object>) yaml.load(new FileInputStream("test_images.yml"));
        List<String> files = (List<String>) config.get("images");
        boolean oneAtATime = Boolean.parseBoolean(config.get("one_at_a_time").toString());


        for (String file : files)
        {
            JPanel testPanel = new VisionTestPanel(file);

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
                JDialog frame = new JDialog();
                frame.setTitle(file);
                frame.setModal(true);
                frame.setLayout(new BorderLayout());
                frame.add(testPanel, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
        }
        System.out.println(files);

    }
}
