/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

public class DigitalGlitchFilterJNI extends JNIWrapper
{
    public static void setFilterSelect(long digital_port_pointer, int filter_index)
    {

    }

    public static int getFilterSelect(long digital_port_pointer)
    {
        return 0;
    }

    public static void setFilterPeriod(int filter_index, int fpga_cycles)
    {

    }

    public static int getFilterPeriod(int filter_index)
    {
        return 0;
    }
}
