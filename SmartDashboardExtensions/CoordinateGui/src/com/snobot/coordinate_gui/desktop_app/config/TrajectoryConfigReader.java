package com.snobot.coordinate_gui.desktop_app.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.coordinate_gui.model.Coordinate;

public class TrajectoryConfigReader
{

    public static void dump(Collection<Coordinate> points, String aFile)
    {

//        try
//        {
//            Yaml yaml = new Yaml();
//            FileWriter fw = new FileWriter(new File(aFile));
//            yaml.dump(points, fw);
//            // yaml.dump(mDataProvider.getAllData(), fw);
//            fw.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(aFile)));

            bw.write(".02,120,480,45\n");

            for (Coordinate c : points)
            {
                bw.write("" + c.x + "," + c.y + "," + c.angle + "\n");
            }

            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Coordinate> load(String aFile)
    {
        List<Coordinate> output = new ArrayList<Coordinate>();

        // try
        // {
        // Yaml yaml = new Yaml();
        // FileReader fr = new FileReader(new File(aFile));
        // output = (List<Coordinate>) yaml.load(fr);
        // // yaml.dump(mDataProvider.getAllData(), fw);
        // fr.close();
        // }
        // catch (IOException e)
        // {
        // e.printStackTrace();
        // }

        try
        {
            BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(new File(aFile))));
            String line;
            boolean firstLine = true;
            
            while((line = br.readLine()) != null)
            {
                if (line.isEmpty())
                {
                    continue;
                }
                if (line.startsWith("#"))
                {
                    continue;
                }

                StringTokenizer st = new StringTokenizer(line, ",");
                if(firstLine)
                {
                    firstLine = false;
                }
                else
                {
                    double x = Double.parseDouble(st.nextToken());
                    double y = Double.parseDouble(st.nextToken());
                    double angle = Double.parseDouble(st.nextToken());
                    output.add(new Coordinate(x / 12, y / 12, angle));
                }
            }
            
            br.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return output;
    }
}
