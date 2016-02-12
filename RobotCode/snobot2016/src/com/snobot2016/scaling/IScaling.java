package com.snobot2016.scaling;

/**
 * Author Jeffrey/Michael
 * interface for scaling class
 */
import com.snobot.xlib.ISubsystem;
import com.snobot.xlib.PropertyManager.DoubleProperty;
import com.snobot2016.Properties2016;

public interface IScaling extends ISubsystem
{
    public enum ScaleAngles
    {
        Ground(Properties2016.sSCALE_GROUND_ANGLE), MoveForIntake(Properties2016.sGET_OUT_OF_THE_WAY_OF_INTAKE), Vertical(
                Properties2016.sVERTICAL), Hook(Properties2016.sHOOK_ANGLE);

        private DoubleProperty mAngle;

        ScaleAngles(DoubleProperty aAngle)
        {
            mAngle = aAngle;
        }

        public double getDesiredAngle()
        {
            return mAngle.getValue();
        }
    }

    void pullUpWall();
    // moves robot up wall

    void lowerDownWall();
    // lowers robot down wall

    void tiltLower();
    // raises tilting arm

    void tiltRaise();
    // lowers tilting arm

    boolean reachGoalAngle(ScaleAngles goal);
}
