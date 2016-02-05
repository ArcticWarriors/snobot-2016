package com.snobot2016.smartdashboard;

import com.snobot2016.Properties2016;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelectStartPosition
{
    private SendableChooser mPickPoint;

    public enum StartPositions
    {
        FIRST_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 26.25, 90), SECOND_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG
                .getValue(), 79.125, 90), THIRD_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 132, 90), FOURTH_POSITION(
                30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 184.875, 90), FIFTH_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG
                .getValue(), 237.75, 90), SPY_POSITION(306, 11.5, 270);

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

    private IPositioner mPositioner;

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

    public void putOnDash()
    {
        SmartDashboard.putData("Select Start Position: ", mPickPoint);
    }

    public StartPositions getSelected()
    {
        return (StartPositions) mPickPoint.getSelected();
    }

    public void setStartPosition()
    {
        mPositioner.setXPosition(this.getSelected().mX);
        System.out.println(mPositioner.getXPosition());
        mPositioner.setYPosition(this.getSelected().mY);
        System.out.println(mPositioner.getYPosition());
    }
}
