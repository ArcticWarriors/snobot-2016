/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.DigitalSourceWrapper;

public class DIOJNI extends JNIWrapper
{
    public static long initializeDigitalPort(long port_pointer)
    {
        return port_pointer;
    }

    public static void freeDigitalPort(long port_pointer)
    {

    }

    public static boolean allocateDIO(long digital_port_pointer, boolean input)
    {
        int pin = (int) digital_port_pointer;
        DigitalSourceWrapper wrapper = new DigitalSourceWrapper(pin);
        SensorActuatorRegistry.get().register(wrapper, pin);

        return false;
    }

    public static void freeDIO(long digital_port_pointer)
    {

    }

    public static void setDIO(long digital_port_pointer, short value)
    {

    }

    public static boolean getDIO(long digital_port_pointer)
    {
        return false;
    }

    public static boolean getDIODirection(long digital_port_pointer)
    {
        return false;
    }

    public static void pulse(long digital_port_pointer, double pulseLength)
    {

    }

    public static boolean isPulsing(long digital_port_pointer)
    {
        return false;
    }

    public static boolean isAnyPulsing()
    {
        return false;
    }

    public static short getLoopTiming()
    {
        return (short) (HALUtil.getCycleTime() * 1e3);
    }
}
