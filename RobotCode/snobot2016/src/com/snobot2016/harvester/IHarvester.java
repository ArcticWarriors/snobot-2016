/**
 * 
 */
package com.snobot2016.harvester;

import com.snobot.xlib.ISubsystem;

/**
 * Author Jeffrey/Michael interface for harvester class
 */
public interface IHarvester extends ISubsystem
{
    void lowerHarvester();

    void raiseHarvester();

    void rollIn();

    void rollOut();
}
