package com.snobot2016.scaling;

/**
 * Author Jeffrey/Michael
 * interface for scaling class
 */
import com.snobot.xlib.ISubsystem;

public interface IScaling extends ISubsystem
{
    void extendUpWall();

    void pullUpWall();

    void lowerDownWall();
}
