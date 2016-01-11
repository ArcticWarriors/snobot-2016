/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

public class PWMJNI extends DIOJNI
{
    public static boolean allocatePWMChannel(long digital_port_pointer)
    {
        return false;
    }

    public static void freePWMChannel(long digital_port_pointer)
    {

    }

    public static void setPWM(long digital_port_pointer, short value)
    {

    }

    public static native short getPWM(long digital_port_pointer);

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
}
