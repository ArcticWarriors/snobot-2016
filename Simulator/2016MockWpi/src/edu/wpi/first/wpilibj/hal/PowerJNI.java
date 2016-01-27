/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

public class PowerJNI extends JNIWrapper
{
    public static native float getVinVoltage();

    public static native float getVinCurrent();

    public static native float getUserVoltage6V();

    public static native float getUserCurrent6V();

    public static boolean getUserActive6V()
    {
        return false;
    }

    public static int getUserCurrentFaults6V()
    {
        return 0;
    }

    public static native float getUserVoltage5V();

    public static native float getUserCurrent5V();

    public static boolean getUserActive5V()
    {
        return false;
    }

    public static int getUserCurrentFaults5V()
    {
        return 0;
    }

    public static native float getUserVoltage3V3();

    public static native float getUserCurrent3V3();

    public static boolean getUserActive3V3()
    {
        return false;
    }

    public static int getUserCurrentFaults3V3()
    {
        return 0;
    }
}
