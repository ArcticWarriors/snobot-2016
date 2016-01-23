/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

public class CompressorJNI extends JNIWrapper
{
    public static long initializeCompressor(byte module)
    {
        return 0;
    }

    public static boolean checkCompressorModule(byte module)
    {
        return false;
    }

    public static boolean getCompressor(long pcm_pointer)
    {
        return false;
    }

    public static void setClosedLoopControl(long pcm_pointer, boolean value)
    {

    }

    public static boolean getClosedLoopControl(long pcm_pointer)
    {
        return false;
    }

    public static boolean getPressureSwitch(long pcm_pointer)
    {
        return false;
    }

    public static native float getCompressorCurrent(long pcm_pointer);

    public static boolean getCompressorCurrentTooHighFault(long pcm_pointer)
    {
        return false;
    }

    public static boolean getCompressorCurrentTooHighStickyFault(long pcm_pointer)
    {
        return false;
    }

    public static boolean getCompressorShortedStickyFault(long pcm_pointer)
    {
        return false;
    }

    public static boolean getCompressorShortedFault(long pcm_pointer)
    {
        return false;
    }

    public static boolean getCompressorNotConnectedStickyFault(long pcm_pointer)
    {
        return false;
    }

    public static boolean getCompressorNotConnectedFault(long pcm_pointer)
    {
        return false;
    }

    public static void clearAllPCMStickyFaults(long pcm_pointer)
    {

    }
}
