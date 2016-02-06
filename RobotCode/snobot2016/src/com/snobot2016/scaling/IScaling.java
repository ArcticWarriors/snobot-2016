package com.snobot2016.scaling;

import com.snobot.xlib.ISubsystem;

public interface IScaling extends ISubsystem
{
    boolean extendUpWall();

    boolean pullUpWall();

    boolean lowerDownWall();
}
