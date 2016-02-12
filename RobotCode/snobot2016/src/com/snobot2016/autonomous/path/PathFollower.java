package com.snobot2016.autonomous.path;

import com.snobot.xlib.motion_profile.simple.ISetpointIterator;
import com.snobot.xlib.motion_profile.simple.IdealPlotSerializer;
import com.snobot.xlib.motion_profile.simple.PathSetpoint;
import com.snobot2016.SmartDashBoardNames;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class PathFollower
{
    private ISetpointIterator mSetpointIterator;
    private double mKv;
    private double mKa;
    private double kKp;

    private int mPathPoint;
    private ITable mTable;

    private double mLastError;
    private double mLastPosition;

    public PathFollower(ISetpointIterator aSetpointIterator, double aKV, double aKA, double aKP)
    {
        mSetpointIterator = aSetpointIterator;
        mKv = aKV;
        mKa = aKA;
        kKp = aKP;

        mTable = NetworkTable.getTable(SmartDashBoardNames.sPATH_NAMESPACE);
    }

    public void init()
    {
        mTable.putString(SmartDashBoardNames.sPATH_IDEAL_POINTS, IdealPlotSerializer.serializePath(mSetpointIterator.getIdealPath()));
    }

    public double calcMotorSpeed(double aCurrPosition)
    {
        if (mSetpointIterator.isFinished())
        {
            return 0;
        }
        else
        {
            double dt = .02;
            double velocity = (aCurrPosition - mLastPosition) / dt;


            PathSetpoint setpoint = mSetpointIterator.getNextSetpoint(0, 0, .02);
            PathSetpoint realPoint = new PathSetpoint(setpoint.mSegment, dt, aCurrPosition, velocity, 0);

            double error = setpoint.mPosition - aCurrPosition;
            double p_term = kKp * error;
            double v_term = mKv * setpoint.mVelocity;
            double a_term = mKa * setpoint.mAcceleration;

            double output = v_term + a_term + p_term;

            // Update smart dashbaord
            String point_info = mPathPoint + "," + IdealPlotSerializer.serializePathPoint(realPoint);
            mTable.putString(SmartDashBoardNames.sPATH_POINT, point_info);

            mLastError = error;
            mLastPosition = aCurrPosition;
            ++mPathPoint;

            return output;
        }
    }

    public boolean isFinished()
    {
        return mSetpointIterator.isFinished();
    }
}