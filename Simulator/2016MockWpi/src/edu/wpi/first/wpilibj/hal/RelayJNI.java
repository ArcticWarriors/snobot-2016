/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.util.Map;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.RelayWrapper;

public class RelayJNI extends DIOJNI
{
    private static RelayWrapper getWrapperFromBuffer(long digital_port_pointer)
    {
        int port = (int) digital_port_pointer;

        Map<Integer, RelayWrapper> relays = SensorActuatorRegistry.get().getRelays();

        if (!relays.containsKey(port))
        {
            relays.put(port, new RelayWrapper(port));
        }

        return relays.get(port);
    }

    public static void setRelayForward(long digital_port_pointer, boolean on)
    {
        getWrapperFromBuffer(digital_port_pointer).setRelayForwards(on);
    }

    public static void setRelayReverse(long digital_port_pointer, boolean on)
    {
        getWrapperFromBuffer(digital_port_pointer).setRelayReverse(on);
    }

    public static boolean getRelayForward(long digital_port_pointer)
    {
        return getWrapperFromBuffer(digital_port_pointer).getRelayForwards();
    }

    public static boolean getRelayReverse(long digital_port_pointer)
    {
        return getWrapperFromBuffer(digital_port_pointer).getRelayReverse();
    }
}
