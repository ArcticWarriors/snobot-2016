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
    
    // sets roller motor speed
    void setRollerMotorSpeed(double aRollerSpeed);
    
    // sets pivot motor speed
    void setPivotMotorSpeed(double aPivotSpeed);
    
    /*
     * Checks if the current potentiometer voltage is higher than the minimum voltage
     */
    boolean goodToLowerVoltage();
    
    /*
     * Checks if the current potentiometer voltage is lower than the maximum voltage
     */
    boolean goodToRaiseVoltage();
    
    /*
     * Converts the the current voltage out of the maximum voltage into a percentage (for widget usage)
     */
    double percentageLowered();
    
    // Sets the harvester to 0 speed
    void stopHarvester();
    
    // Sets the roller to 0 speed
    void stopRoller();
}
