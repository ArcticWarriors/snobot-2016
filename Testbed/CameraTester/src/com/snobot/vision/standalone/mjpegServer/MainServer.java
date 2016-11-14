package com.snobot.vision.standalone.mjpegServer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.yaml.snakeyaml.Yaml;

public class MainServer
{
    private static final String IMAGE_CONFIG = "config/test_images.yml";

    @SuppressWarnings("unchecked")
    public MainServer() throws IOException
    {
        MjpgServer server = MjpgServer.getInstance();

        Yaml yaml = new Yaml();
        Map<String, Object> config = (Map<String, Object>) yaml.load(new FileInputStream(IMAGE_CONFIG));
        List<String> files = (List<String>) config.get("images");

        while (true)
        {
            for (String file : files)
            {
                BufferedImage image = ImageIO.read(new File(file));

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();

                try
                {
                    Thread.sleep(1000);
                    server.update(imageInByte);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        new MainServer();
    }
}
