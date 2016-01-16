/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import com.snobot.simulator.RobotStateSingleton;

import edu.wpi.first.wpilibj.Timer;

public class HALUtil extends JNIWrapper
{
    // *****************************************************
    // Our Stuff
    // *****************************************************

    private static final double sCYCLE_TIME = .02; //The period that the main loop should be run at
    private static double sWaitTime = .02; // The time to sleep. You can run
                                           // simulations faster/slower by
                                           // changing this. For example,
                                           // making the wait time 1 second,
                                           // means one 20ms cycle will happen
                                           // each second, 50x slower than
                                           // normal. Or, you could make it
                                           // .002, which would make the code
                                           // execute at 10x speed
    private static double sMatchTime = 0;

    public static double getCycleTime()
    {
        return sCYCLE_TIME;
    }
    
    public static void setWaitTime(double aTime)
    {
        sWaitTime = aTime;
    }

    // *****************************************************
    // /Our Stuff
    // *****************************************************

    public static final int NULL_PARAMETER = -1005;
    public static final int SAMPLE_RATE_TOO_HIGH = 1001;
    public static final int VOLTAGE_OUT_OF_RANGE = 1002;
    public static final int LOOP_TIMING_ERROR = 1004;
    public static final int INCOMPATIBLE_STATE = 1015;
    public static final int ANALOG_TRIGGER_PULSE_OUTPUT_ERROR = -1011;
    public static final int NO_AVAILABLE_RESOURCES = -104;
    public static final int PARAMETER_OUT_OF_RANGE = -1028;

    // public static final int SEMAPHORE_WAIT_FOREVER = -1;
    // public static final int SEMAPHORE_Q_PRIORITY = 0x01;

    public static long initializeMutexNormal()
    {
        return 0;
    }

    public static void deleteMutex(long sem)
    {

    }

    public static void takeMutex(long sem)
    {

    }

    // public static native long initializeSemaphore(int initialValue);
    // public static native void deleteSemaphore(long sem);
    // public static native byte takeSemaphore(long sem, int timeout);
    public static long initializeMultiWait()
    {
        return 0;
    }

    public static void deleteMultiWait(long sem)
    {

    }

    public static void takeMultiWait(long sem, long m)
    {
        Timer.delay(sWaitTime);
        sMatchTime += sCYCLE_TIME;

        RobotStateSingleton.get().updateLoopListeners();
    }

    public static short getFPGAVersion()
    {
        return 0;
    }

    public static int getFPGARevision()
    {
        return 0;
    }

    public static long getFPGATime()
    {
        return (long) (sMatchTime * 1e6);
    }

    public static boolean getFPGAButton()
    {
        return false;
    }

    public static String getHALErrorMessage(int code)
    {
        return "";
    }

    public static int getHALErrno()
    {
        return 0;
    }

    public static String getHALstrerror(int errno)
    {
        return "";
    }

    public static String getHALstrerror()
    {
        return getHALstrerror(getHALErrno());
    }
}
