/**
 * 
 */
package com.snobot2016.harvester;

import com.snobot.xlib.ISubsystem;

/**
 * Author Jeffrey/Michael 
 *
 * interface for harvester class
 *
 */
public interface IHarvester extends ISubsystem
{
    /**
     * Lowers the Harvester in a position able to acquire BOULDERS
     */
    void lowerHarvester();
    
    /**
     * Raises the Harvester for the beginning of the match
     */
    void raiseHarvester();
    
    /**
     * Turns the roller on so that the robot can acquire BOULDERS
     */
    void rollIn();
   
    /**
     * Reverses the roller so that the BOULDERS can be scored
     */
    void rollOut();
    
}
