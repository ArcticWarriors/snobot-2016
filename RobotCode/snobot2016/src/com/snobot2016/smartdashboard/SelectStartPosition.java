package com.snobot2016.smartdashboard;

import com.snobot2016.Properties2016;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author Andrew/Alec
 */
public class SelectStartPosition
{
    /*
     * The sendable chooser the class returns.
     */
    private SendableChooser mPickPoint;
    /*
     * The positioner.
     */
    private IPositioner mPositioner;

    /*
     * This is the enum that becomes the start positions chooser. The numbers
     * are the X, Y, and Orientations for each position.
     */
    public enum StartPositions
    {
        FIRST_POSITION(26.25, 30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 0), 
        SECOND_POSITION(79.125, 30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 0), 
        THIRD_POSITION(132, 30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 0), 
        FOURTH_POSITION(184.875, 30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 0), 
        FIFTH_POSITION(237.75, 30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 0), 
        SPY_POSITION(11.5, 306, 180);

        final double mX;
        final double mY;
        final double mOrientation;

        StartPositions(double aX, double aY, double aAngle)
        {
            mX = aX;
            mY = aY;
            mOrientation = aAngle;
        }

    }

    /*
     * Constructor: news up and adds objects to the sendable chooser.
     */
    public SelectStartPosition(IPositioner aPositioner)
    {
        mPickPoint = new SendableChooser();
        mPickPoint.addDefault("Position 1", StartPositions.FIRST_POSITION);
        mPickPoint.addObject("Position 2", StartPositions.SECOND_POSITION);
        mPickPoint.addObject("Position 3", StartPositions.THIRD_POSITION);
        mPickPoint.addObject("Position 4", StartPositions.FOURTH_POSITION);
        mPickPoint.addObject("Position 5", StartPositions.FIFTH_POSITION);
        mPickPoint.addObject("Spy Bot", StartPositions.SPY_POSITION);

        mPositioner = aPositioner;
    }

    /*
     * Puts the chooser on the smart dashboard.
     */
    public void putOnDash()
    {
        SmartDashboard.putData("Select Start Position: ", mPickPoint);
    }

    /*
     * Returns the selected position.
     */
    public StartPositions getSelected()
    {
        return (StartPositions) mPickPoint.getSelected();
    }

    /*
     * Gives the selected position data to the positioner.
     */
    public void setStartPosition()
    {
        mPositioner.setXPosition(this.getSelected().mX);
        mPositioner.setYPosition(this.getSelected().mY);
        mPositioner.setOrientationDegrees(this.getSelected().mOrientation);
    }
}
