/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.AnalogWrapper;

public class AnalogJNI extends JNIWrapper
{
    private static AnalogWrapper getWrapperFromBuffer(long buffer)
    {
        int port = (int) buffer;
        return SensorActuatorRegistry.get().getAnalog().get(port);
    }

    private static double sAnalogSampleRate;

    /**
     * <i>native declaration :
     * AthenaJava\target\native\include\HAL\Analog.h:58</i><br>
     * enum values
     */
    public static interface AnalogTriggerType
    {
        /**
         * <i>native declaration :
         * AthenaJava\target\native\include\HAL\Analog.h:54</i>
         */
        public static final int kInWindow = 0;
        /**
         * <i>native declaration :
         * AthenaJava\target\native\include\HAL\Analog.h:55</i>
         */
        public static final int kState = 1;
        /**
         * <i>native declaration :
         * AthenaJava\target\native\include\HAL\Analog.h:56</i>
         */
        public static final int kRisingPulse = 2;
        /**
         * <i>native declaration :
         * AthenaJava\target\native\include\HAL\Analog.h:57</i>
         */
        public static final int kFallingPulse = 3;
    };

    public static long initializeAnalogInputPort(long port_pointer)
    {
        AnalogWrapper wrapper = new AnalogWrapper((int) port_pointer);
        SensorActuatorRegistry.get().register(wrapper, (int) port_pointer);

        return port_pointer;
    }

    public static void freeAnalogInputPort(long port_pointer)
    {

    }

    public static long initializeAnalogOutputPort(long port_pointer)
    {
        AnalogWrapper wrapper = new AnalogWrapper((int) port_pointer);
        SensorActuatorRegistry.get().register(wrapper, (int) port_pointer);

        return port_pointer;
    }

    public static void freeAnalogOutputPort(long port_pointer)
    {

    }

    public static boolean checkAnalogModule(byte module)
    {
        return false;
    }

    public static boolean checkAnalogInputChannel(int pin)
    {
        return !SensorActuatorRegistry.get().getAnalog().containsKey(pin);
    }

    public static boolean checkAnalogOutputChannel(int pin)
    {
        return false;
    }

    public static void setAnalogOutput(long port_pointer, double voltage)
    {

    }

    public static double getAnalogOutput(long port_pointer)
    {
        return 0;
    }

    public static void setAnalogSampleRate(double samplesPerSecond)
    {
        sAnalogSampleRate = samplesPerSecond;
    }

    public static double getAnalogSampleRate()
    {
        return sAnalogSampleRate;
    }

    public static void setAnalogAverageBits(long analog_port_pointer, int bits)
    {

    }

    public static int getAnalogAverageBits(long analog_port_pointer)
    {
        return 1;
    }

    public static void setAnalogOversampleBits(long analog_port_pointer, int bits)
    {

    }

    public static int getAnalogOversampleBits(long analog_port_pointer)
    {
        return 0;
    }

    public static short getAnalogValue(long analog_port_pointer)
    {
        return 0;
    }

    public static int getAnalogAverageValue(long analog_port_pointer)
    {
        return 0;
    }

    public static int getAnalogVoltsToValue(long analog_port_pointer, double voltage)
    {
        return 0;
    }

    public static double getAnalogVoltage(long analog_port_pointer)
    {
        return getWrapperFromBuffer(analog_port_pointer).getVoltage();
    }

    public static double getAnalogAverageVoltage(long analog_port_pointer)
    {
        return 0;
    }

    public static int getAnalogLSBWeight(long analog_port_pointer)
    {
        return 256;
    }

    public static int getAnalogOffset(long analog_port_pointer)
    {
        return 0;
    }

    public static boolean isAccumulatorChannel(long analog_port_pointer)
    {
        return false;
    }

    public static void initAccumulator(long analog_port_pointer)
    {

    }

    public static void resetAccumulator(long analog_port_pointer)
    {
        getWrapperFromBuffer(analog_port_pointer).setAccumulator(0);
    }

    public static void setAccumulatorCenter(long analog_port_pointer, int center)
    {

    }

    public static void setAccumulatorDeadband(long analog_port_pointer, int deadband)
    {

    }

    public static long getAccumulatorValue(long analog_port_pointer)
    {
        return 0;
    }

    public static int getAccumulatorCount(long analog_port_pointer)
    {
        return 0;
    }

    public static void getAccumulatorOutput(long analog_port_pointer, LongBuffer value, IntBuffer count)
    {
        double accum_value = getWrapperFromBuffer(analog_port_pointer).getAccumulator();
        accum_value *= 1000000000;
        accum_value *= .007; // Volts per degree second
        accum_value *= 100;

        value.put((long) accum_value);
        count.put(1);
    }

    public static long initializeAnalogTrigger(long port_pointer, IntBuffer index)
    {
        return port_pointer;
    }

    public static void cleanAnalogTrigger(long analog_trigger_pointer)
    {

    }

    public static void setAnalogTriggerLimitsRaw(long analog_trigger_pointer, int lower, int upper)
    {

    }

    public static void setAnalogTriggerLimitsVoltage(long analog_trigger_pointer, double lower, double upper)
    {

    }

    public static void setAnalogTriggerAveraged(long analog_trigger_pointer, boolean useAveragedValue)
    {

    }

    public static void setAnalogTriggerFiltered(long analog_trigger_pointer, boolean useFilteredValue)
    {

    }

    public static boolean getAnalogTriggerInWindow(long analog_trigger_pointer)
    {
        return false;
    }

    public static boolean getAnalogTriggerTriggerState(long analog_trigger_pointer)
    {
        return false;
    }

    public static boolean getAnalogTriggerOutput(long analog_trigger_pointer, int type)
    {
        return false;
    }
}

