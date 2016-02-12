package com.snobot.sd2016.spline_plotter;

/**
 * Container for the information on a single trajectory point
 * 
 * @author PJ
 *
 */
public class SplineSegment
{

    /** Position of the left wheel */
    public double mLeftSidePosition;

    /** Velocity of the left wheel */
    public double mLeftSideVelocity;

    /** Position of the right wheel */
    public double mRightSidePosition;

    /** Velocity of the right wheel */
    public double mRightSideVelocity;

    /** Heading of the robot, in degrees */
    public double mRobotHeading;

    public SplineSegment()
    {
        this(0, 0, 0, 0, 0);
    }

    public SplineSegment(double aLeftPos, double aLeftVel, double aRightPos, double aRightVel, double aHeading)
    {
        this.mLeftSidePosition = aLeftPos;
        this.mLeftSideVelocity = aLeftVel;
        this.mRightSidePosition = aRightPos;
        this.mRightSideVelocity = aRightVel;
        this.mRobotHeading = aHeading;
    }

    @Override
    public String toString()
    {
        return "SplineSegment [left_pos=" + mLeftSidePosition + ", left_vel=" + mLeftSideVelocity + ", right_pos=" + mRightSidePosition + ", right_vel=" + mRightSideVelocity
                + ", heading=" + mRobotHeading + "]";
    }

}