package com.snobot2016.smartdashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelectStartPosition
{
    private SendableChooser mPickPoint;

    public SelectStartPosition()
    {
        mPickPoint = new SendableChooser();
    }

    private void choices()
    {
        mPickPoint.addDefault("Position 1", StartPositions.FIRST_POSITION);
        mPickPoint.addObject("Position 2", StartPositions.SECOND_POSITION);
        mPickPoint.addObject("Position 3", StartPositions.THIRD_POSITION);
        mPickPoint.addObject("Position 4", StartPositions.FOURTH_POSITION);
        mPickPoint.addObject("Position 5", StartPositions.FIFTH_POSITION);

    }

    public void putOnDash()
    {
        SmartDashboard.putData("Select Start Position: ", mPickPoint);
    }

    public StartPositions getSelected()
    {
        return (StartPositions) mPickPoint.getSelected();
    }
}
