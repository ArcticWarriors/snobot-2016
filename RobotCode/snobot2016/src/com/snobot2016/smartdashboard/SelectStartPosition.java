package com.snobot2016.smartdashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
    }
}
