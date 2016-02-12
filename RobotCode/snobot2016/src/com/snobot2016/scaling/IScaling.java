package com.snobot2016.scaling;

/**
 * Author Jeffrey/Michael
 * interface for scaling class
 * 
 */

import com.snobot.xlib.ISubsystem;

public interface IScaling extends ISubsystem
{
    /**
     * Pulls the robot up the wall
     */
    void pullUpWall();
    
    /**
     * Lowers the robot down the wall
     */
    void lowerDownWall();

    /**
     * Puts the robot arm back in its resting place
     */
    void tiltLower();

    /**
     * Raises the arm against the wall
     */
    void tiltRaise();
}
