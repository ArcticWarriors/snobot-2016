package com.snobot.coordinate_gui.desktop_app.trajectory;

import java.util.ArrayList;
import java.util.List;

import com.snobot.coordinate_gui.model.Coordinate;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team254.lib.trajectory.io.TextFileDeserializer;

public class PathUtils
{
    public static List<Coordinate> getCoordinatesFromFile(String aFile)
    {
        TextFileDeserializer deserializer = new TextFileDeserializer();
        return getCoordinatesFromPath(deserializer.deserializeFromFile(aFile));
    }

    public static List<Coordinate> getCoordinatesFromPath(Path path)
    {
        List<Coordinate> output = new ArrayList<Coordinate>();
        Trajectory left = path.getLeftWheelTrajectory();
        Trajectory right = path.getRightWheelTrajectory();

        for (int i = 0; i < left.getNumSegments(); ++i)
        {
            Segment left_segment = left.getSegment(i);
            Segment right_segment = right.getSegment(i);

            double avgX = (left_segment.x + right_segment.x) / 2;
            double avgY = (left_segment.y + right_segment.y) / 2;
            double avgAngle = Math.toDegrees((left_segment.heading + right_segment.heading) / 2);

            output.add(new Coordinate(avgY / 12, avgX / 12, avgAngle));
        }

        return output;
    }
}
