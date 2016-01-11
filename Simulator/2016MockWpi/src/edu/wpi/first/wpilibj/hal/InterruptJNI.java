/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

public class InterruptJNI extends JNIWrapper
{
    public interface InterruptJNIHandlerFunction
    {
        void apply(int interruptAssertedMask, Object param);
    };

    public static long initializeInterrupts(int interruptIndex, boolean watcher)
    {
        return 0;
    }

    public static void cleanInterrupts(long interrupt_pointer)
    {

    }

    public static int waitForInterrupt(long interrupt_pointer, double timeout, boolean ignorePrevious)
  {
    return 0;
  }

    public static void enableInterrupts(long interrupt_pointer)
    {

    }

    public static void disableInterrupts(long interrupt_pointer)
    {

    }

    public static double readRisingTimestamp(long interrupt_pointer)
    {
        return 0;
    }

    public static double readFallingTimestamp(long interrupt_pointer)
    {
        return 0;
    }

    public static void requestInterrupts(long interrupt_pointer, byte routing_module, int routing_pin, boolean routing_analog_trigger)
  {

  }

    public static void attachInterruptHandler(long interrupt_pointer, InterruptJNIHandlerFunction handler, Object param)
  {

  }

    public static void setInterruptUpSourceEdge(long interrupt_pointer, boolean risingEdge, boolean fallingEdge)
  {

  }
}
