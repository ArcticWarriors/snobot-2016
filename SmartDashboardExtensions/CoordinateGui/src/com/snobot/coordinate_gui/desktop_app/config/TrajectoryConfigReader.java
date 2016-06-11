package com.snobot.coordinate_gui.desktop_app.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import com.snobot.coordinate_gui.model.Coordinate;

public class TrajectoryConfigReader
{

    public static void dump(Collection<?> points, String aFile)
    {
        try
        {
            Yaml yaml = new Yaml();
            FileWriter fw = new FileWriter(new File(aFile));
            yaml.dump(points, fw);
            // yaml.dump(mDataProvider.getAllData(), fw);
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Coordinate> load(String aFile)
    {
        List<Coordinate> output = new ArrayList<Coordinate>();

        try
        {
            Yaml yaml = new Yaml();
            FileReader fr = new FileReader(new File(aFile));
            output = (List<Coordinate>) yaml.load(fr);
            // yaml.dump(mDataProvider.getAllData(), fw);
            fr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return output;
    }
}
