package com.snobot.coordinate_gui.desktop_app.trajectory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.coordinate_gui.model.Coordinate;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.gen.PathGenerator;
import com.team254.lib.trajectory.gen.TextFileSerializer;
import com.team254.lib.trajectory.gen.TrajectoryGenerator;
import com.team254.lib.trajectory.gen.WaypointSequence;
import com.team254.lib.trajectory.gen.WaypointSequence.Waypoint;

public class GeneratePathFromConfigFile
{


    public static void x(String aConfigFile, String aOutFile, String aOutDirectory)
    {
        TrajectoryGenerator.Config trajectoryConfig = new TrajectoryGenerator.Config();
        WaypointSequence waypoints = new WaypointSequence(10000);

        try
        {
            BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(new File(aConfigFile))));
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null)
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
                if (firstLine)
                {
                    firstLine = false;
                    trajectoryConfig.dt = Double.parseDouble(st.nextToken());
                    trajectoryConfig.max_acc = Double.parseDouble(st.nextToken());
                    trajectoryConfig.max_jerk = Double.parseDouble(st.nextToken());
                    trajectoryConfig.max_vel = Double.parseDouble(st.nextToken());
                }
                else
                {
                    double x = Double.parseDouble(st.nextToken());
                    double y = Double.parseDouble(st.nextToken());
                    double angle = Double.parseDouble(st.nextToken());
                    waypoints.addWaypoint(new Waypoint(x, y, angle));
                }
            }

            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        double wheelbase = 25.5 / 12;

        generate(trajectoryConfig, waypoints, aOutDirectory, aOutFile, wheelbase);
    }

    public static List<Coordinate> generateX(TrajectoryGenerator.Config aConfig, WaypointSequence aPoints, String aDirectory, String aOutFile,
            double aWheelbase)
    {
        Path path = PathGenerator.makePath(aPoints, aConfig, aWheelbase, aOutFile);

        List<Coordinate> output = PathUtils.getCoordinatesFromPath(path);

        return output;
    }

    public static List<Coordinate> generate(TrajectoryGenerator.Config aConfig, WaypointSequence aPoints, String aDirectory, String aOutFile,
            double aWheelbase)
    {
        Path path = PathGenerator.makePath(aPoints, aConfig, aWheelbase, aOutFile);
        
        List<Coordinate> output = generateX(aConfig, aPoints, aDirectory, aOutFile, aWheelbase);

        // Outputs to the directory supplied as the first argument.
        TextFileSerializer js = new TextFileSerializer();
        String serialized = js.serialize(path);
        // System.out.print(serialized);
        String fullpath = joinPaths(aDirectory, aOutFile);
        System.out.println(fullpath);
        if (!writeFile(fullpath, serialized))
        {
            System.err.println(fullpath + " could not be written!!!!");
            System.exit(1);
        }
        else
        {
            System.out.println("Wrote " + fullpath);
        }

        return output;
    }

    public static String joinPaths(String path1, String path2)
    {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);

        System.out.println(path1);
        System.out.println(path2);
        return file2.getPath();
    }

    private static boolean writeFile(String path, String data)
    {
        try
        {
            File file = new File(path);

            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        }
        catch (IOException e)
        {
            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {
        String config = "C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/trajectory_config/abcd.yml";
        String outDir = "C:/Users/PJ/Documents/GitHub/Frc2016/RobotCode/snobot2016/resources/traj";
        String outFile = "abcd";

        x(config, outFile, outDir);
    }
}
