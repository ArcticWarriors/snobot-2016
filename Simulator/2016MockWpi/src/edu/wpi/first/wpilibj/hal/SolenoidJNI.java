/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.util.Map.Entry;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.SolenoidWrapper;

public class SolenoidJNI extends JNIWrapper
{
    public static long initializeSolenoidPort(long portPointer)
    {
        int pin = (int) portPointer;
        SolenoidWrapper wrapper = new SolenoidWrapper(pin);
        SensorActuatorRegistry.get().register(wrapper, pin);

        return portPointer;
    }

    public static void freeSolenoidPort(long port_pointer)
    {

    }

    public static long getPortWithModule(byte module, byte channel)
    {
        return channel;
    }

    public static void setSolenoid(long port, boolean on)
    {
        getWrapperFromBuffer(port).set(on);
    }

    public static boolean getSolenoid(long port)
    {
        return getWrapperFromBuffer(port).get();
    }

    public static byte getAllSolenoids(long port)
    {
        byte output = 0;

        for (Entry<Integer, SolenoidWrapper> pair : SensorActuatorRegistry.get().getSolenoids().entrySet())
        {
            if (pair.getValue().get())
            {
                output |= 1 << pair.getKey();
            }
            // output |= pair.
        }

        return output;
    }

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

    // ******************************************
    // Our additions
    // ******************************************
    private static SolenoidWrapper getWrapperFromBuffer(long digital_port_pointer)
    {
        int port = (int) digital_port_pointer;
        return SensorActuatorRegistry.get().getSolenoids().get(port);
    }
}
