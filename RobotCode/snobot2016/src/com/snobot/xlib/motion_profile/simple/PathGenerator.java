package com.snobot.xlib.motion_profile.simple;

import java.util.ArrayList;
import java.util.List;

import com.snobot.xlib.motion_profile.simple.PathSetpoint.TrapezoidSegment;

/**
 * Class to generate a path, based on a {@link PathConfig}
 * 
 * @author PJ
 *
 */
public class PathGenerator
{
    public PathGenerator()
    {

    }

    /**
     * Generates a list of setpoints for the given path config
     * 
     * @param aConfig
     *            The config to use to generate the path
     * 
     * @return The path to run
     */
    public List<PathSetpoint> generate(PathConfig aConfig)
    {
        return generate(aConfig.mMaxVelocity, aConfig.mMaxAcceleration, aConfig.mEndpoint, aConfig.mExpectedDt);
    }

    private List<PathSetpoint> generate(double aMaxVelocity, double aMaxAccel, double aPosition, double aDt)
    {
        ArrayList<PathSetpoint> output = new ArrayList<>();

        double t1 = aMaxVelocity / aMaxAccel;
        double t2 = aPosition / aMaxVelocity;
        double t3 = aMaxVelocity / aMaxAccel + aPosition / aMaxVelocity;

        double pos = 0.0;
        double vel = 0.0;
        for (double t = 0; t < t1; t += aDt)
        {
            pos = .5 * aMaxAccel * (t * t);
            vel = aMaxAccel * t;
            PathSetpoint point = new PathSetpoint(TrapezoidSegment.Acceleration, aDt, pos, vel, aMaxAccel);
            output.add(point);

        }

        for (double t = t1; t < t2; t += aDt)
        {
            pos = .5 * ((aMaxVelocity * aMaxVelocity) / aMaxAccel) + aMaxVelocity * (t - (aMaxVelocity / aMaxAccel));
            vel = aMaxVelocity;
            PathSetpoint point = new PathSetpoint(TrapezoidSegment.ConstantVelocity, aDt, pos, vel, 0);
            output.add(point);
        }

        for (double t = t2; t < t3; t += aDt)
        {
            pos = aPosition - .5 * aMaxAccel * Math.pow((t - ((aMaxVelocity / aMaxAccel) + (aPosition / aMaxVelocity))), 2);
            vel = aMaxAccel * ((aMaxVelocity / aMaxAccel) + (aPosition / aMaxVelocity) - t);
            PathSetpoint point = new PathSetpoint(TrapezoidSegment.Deceleration, aDt, pos, vel, -aMaxAccel);
            output.add(point);
        }

        if (output.get(output.size() - 1).mVelocity != 0)
        {
            output.add(new PathSetpoint(TrapezoidSegment.Deceleration, aDt, aPosition, 0, 0));
        }

        return output;
    }
}
