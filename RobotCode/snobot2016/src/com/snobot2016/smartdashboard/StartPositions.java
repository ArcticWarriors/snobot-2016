package com.snobot2016.smartdashboard;

import com.snobot2016.Properties2016;

public enum StartPositions
{
    FIRST_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 26.25, 90), SECOND_POSITION(
            30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 79.125, 90), THIRD_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(),
                    132, 90), FOURTH_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 184.875,
                            90), FIFTH_POSITION(30 - Properties2016.sAUTON_SETUP_OVERHANG.getValue(), 237.75, 90), SPY_POSITION(306, 11.5, 270);

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
