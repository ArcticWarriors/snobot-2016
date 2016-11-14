package com.snobot.vision.standalone;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

public class MjpegReceiver
{
    private static final int[] START_BYTES = new int[]{ 0xFF, 0xD8 };
    private static final int[] END_BYTES = new int[]{ 0xFF, 0xD9 };
    private boolean mRunning;

    public interface ImageReceiver
    {
        public void onImage(Image image);
    }

    public MjpegReceiver()
    {
        mRunning = true;
    }

    public void stop()
    {
        mRunning = false;
    }

    private BufferedImage parseImage(InputStream stream, ByteArrayOutputStream imageBuffer) throws IOException
    {
        imageBuffer.reset();
        for (int i = 0; i < START_BYTES.length;)
        {
            int b = stream.read();
            if (b == START_BYTES[i])
            {
                i++;
            }
            else
            {
                i = 0;
            }
        }

        for (int i = 0; i < START_BYTES.length; ++i)
        {
            imageBuffer.write(START_BYTES[i]);
        }

        for (int i = 0; i < END_BYTES.length;)
        {
            int b = stream.read();
            imageBuffer.write(b);
            if (b == END_BYTES[i])
            {
                i++;
            }
            else
            {
                i = 0;
            }
        }

        return ImageIO.read(new ByteArrayInputStream(imageBuffer.toByteArray()));
    }

    public void runReceiver(String imageUrl, List<ImageReceiver> imageRecievers)
    {
        System.out.println("Attempting to receive MJPEG stream from : " + imageUrl);

        ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();
        InputStream stream = null;

        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            stream = urlConn.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        while (mRunning)
        {
            try
            {
                BufferedImage image = parseImage(stream, imageBuffer);

                for (ImageReceiver recv : imageRecievers)
                {
                    recv.onImage(image);
                }
            }
            catch (Exception e)
            {
                System.err.println("Could not save image : " + e.getMessage());
            }
        }
    }
}
