package com.snobot2016.scaling;

/**
 * Author Jeffrey/Michael
 * interface for scaling class
 */
import com.snobot.xlib.ISubsystem;

public interface IScaling extends ISubsystem
{
    void pullUpWall();
    // moves robot up wall

    void lowerDownWall();
    // lowers robot down wall

    void tiltLower();
    // raises tilting arm

    void tiltRaise();
    // lowers tilting arm
}
