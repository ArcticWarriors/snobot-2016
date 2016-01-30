/**
 * 
 */
package com.snobot2016.harvester;

import com.snobot.xlib.ISubsystem;

/**
 * @author Michael_000
 *
 */
public interface IHarvester extends ISubsystem
{
    boolean lowerHarvester();
    boolean raiseHarvester();
    boolean rollIn();
    boolean rollOut();
}
