/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

public class SolenoidJNI extends JNIWrapper
{
    public static long initializeSolenoidPort(long portPointer)
    {
        return 0;
    }

    public static void freeSolenoidPort(long port_pointer)
    {

    }

    public static long getPortWithModule(byte module, byte channel)
    {
        return 0;
    }

    public static void setSolenoid(long port, boolean on)
    {

    }

    public static boolean getSolenoid(long port)
    {
        return false;
    }

    public static native byte getAllSolenoids(long port);

    public static int getPCMSolenoidBlackList(long pcm_pointer)
    {
        return 0;
    }

    public static boolean getPCMSolenoidVoltageStickyFault(long pcm_pointer)
    {
        return false;
    }

    public static boolean getPCMSolenoidVoltageFault(long pcm_pointer)
    {
        return false;
    }

    public static void clearAllPCMStickyFaults(long pcm_pointer)
    {

    }
}
