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
    // lowers harvester

    void raiseHarvester();
    // raises harvester

    void rollIn();
    // rotates Harvester inwards

    void rollOut();
    // rotates Harvester outwards

}
