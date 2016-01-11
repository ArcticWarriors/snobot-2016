/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.SpeedControllerWrapper;

public class PWMJNI extends DIOJNI
{

    public static boolean allocatePWMChannel(long digital_port_pointer)
    {
        boolean canAllocate = !SensorActuatorRegistry.get().getSpeedControllers().containsKey(digital_port_pointer);

        SpeedControllerWrapper wrapper = new SpeedControllerWrapper((int) digital_port_pointer);
        SensorActuatorRegistry.get().register(wrapper, (int) digital_port_pointer);

        return canAllocate;
    }

    public static void freePWMChannel(long digital_port_pointer)
    {

    }

    public static void setPWM(long digital_port_pointer, short value)
    {

    }

    public static short getPWM(long digital_port_pointer)
    {
        return 0;
    }

    public static void latchPWMZero(long digital_port_pointer)
    {

    }

    public static void setPWMPeriodScale(long digital_port_pointer, int squelchMask)
    {

    }

    public static long allocatePWM()
    {
        return 0;
    }

    public static void freePWM(long pwmGenerator)
    {

    }

    public static void setPWMRate(double rate)
    {

    }

    public static void setPWMDutyCycle(long pwmGenerator, double dutyCycle)
    {

    }

    public static void setPWMOutputChannel(long pwmGenerator, int pin)
    {

    }

    // *************************************************
    // Our custom functions
    // *************************************************
    private static SpeedControllerWrapper getWrapperFromBuffer(int digital_port_pointer)
    {
        return SensorActuatorRegistry.get().getSpeedControllers().get(digital_port_pointer);
    }

    public static void __setPWM(long digital_port_pointer, double speed)
    {
        getWrapperFromBuffer((int) digital_port_pointer).set(speed);
    }

    public static double __getPWM(long digital_port_pointer)
    {
        return getWrapperFromBuffer((int) digital_port_pointer).get();
    }
}
