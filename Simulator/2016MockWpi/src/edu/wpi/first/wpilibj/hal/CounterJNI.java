/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.nio.IntBuffer;

public class CounterJNI extends JNIWrapper
{
    public static long initializeCounter(int mode, IntBuffer index)
    {
        return 0;
    }

    public static void freeCounter(long counter_pointer)
    {

    }

    public static void setCounterAverageSize(long counter_pointer, int size)
    {

    }

    public static void setCounterUpSource(long counter_pointer, int pin, boolean analogTrigger)
    {

    }

    public static void setCounterUpSourceEdge(long counter_pointer, boolean risingEdge, boolean fallingEdge)
    {

    }

    public static void clearCounterUpSource(long counter_pointer)
    {

    }

    public static void setCounterDownSource(long counter_pointer, int pin, boolean analogTrigger)
    {

    }

    public static void setCounterDownSourceEdge(long counter_pointer, boolean risingEdge, boolean fallingEdge)
    {

    }

    public static void clearCounterDownSource(long counter_pointer)
    {

    }

    public static void setCounterUpDownMode(long counter_pointer)
    {

    }

    public static void setCounterExternalDirectionMode(long counter_pointer)
    {

    }

    public static void setCounterSemiPeriodMode(long counter_pointer, boolean highSemiPeriod)
    {

    }

    public static void setCounterPulseLengthMode(long counter_pointer, double threshold)
    {

    }

    public static int getCounterSamplesToAverage(long counter_pointer)
    {
        return 0;
    }

    public static void setCounterSamplesToAverage(long counter_pointer, int samplesToAverage)
    {

    }

    public static void resetCounter(long counter_pointer)
    {

    }

    public static int getCounter(long counter_pointer)
    {
        return 0;
    }

    public static double getCounterPeriod(long counter_pointer)
    {
        return 0;
    }

    public static void setCounterMaxPeriod(long counter_pointer, double maxPeriod)
    {

    }

    public static void setCounterUpdateWhenEmpty(long counter_pointer, boolean enabled)
    {

    }

    public static boolean getCounterStopped(long counter_pointer)
    {
        return false;
    }

    public static boolean getCounterDirection(long counter_pointer)
    {
        return false;
    }

    public static void setCounterReverseDirection(long counter_pointer, boolean reverseDirection)
    {

    }
}
