package com.snobot.xlib.motion_profile.simple;

public interface ISetpointIterator
{
    /**
     * Gets the next setpoint to drive to for the current iteration
     * 
     * @param aPosition
     *            The current position (distance or angle) of the robot
     * @param aVelocity
     *            The current velocity (ft/s or deg/s) of the robot
     * 
     * @return Returns the next setpoint to drive to, or NULL if there isn't one
     */
    PathSetpoint getNextSetpoint(double aPosition, double aVelocity);

    /**
     * Indicates that you are finished with the list
     * 
     * @return True if finished, false otherwise
     */
    public boolean isFinished();
}
