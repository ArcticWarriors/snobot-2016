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
    // lowers harvester
    void lowerHarvester();
    
    // raises harvester
    void raiseHarvester();
    
    // rotates Harvester inwards
    void rollIn();
   
    // rotates Harvester outwards
    void rollOut();
    
    // sets roller motor speed
    void setRollerMotorSpeed(double aRollerSpeed);
    
    // sets pivot motor speed
    void setPivotMotorSpeed(double aPivotSpeed);
    
    /*
     * Checks if the current potentiometer voltage is higher than the minimum voltage
     */
    boolean goodToRaise();
    
    /*
     * Checks if the current potentiometer voltage is lower than the maximum voltage
     */
    boolean goodToLower();
    
    /*
     * Converts the the current voltage out of the maximum voltage into a percentage (for widget usage)
     */
    double percentageLowered();
    
    // Sets the harvester to 0 speed
    void stopHarvester();
    
    // Sets the roller to 0 speed
    void stopRoller();
    
    /*
     * Raises or Lowers the harvester until it hits the limit, if angle is reached, return true
     */
    boolean moveToPercentage(double aPotGoal);

    
}
