/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.lang.Runtime;

/**
 * The NotifierJNI class directly wraps the C++ HAL Notifier.
 *
 * This class is not meant for direct use by teams. Instead, the
 * edu.wpi.first.wpilibj.Notifier class, which corresponds to the C++ Notifier
 * class, should be used.
 */
public class NotifierJNI extends JNIWrapper
{
    /**
     * Callback function
     */
    public interface NotifierJNIHandlerFunction
    {
        void apply(long curTime);
    }

    /**
     * Initializes the notifier.
     */
    public static long initializeNotifier(NotifierJNIHandlerFunction func)
    {
        return 0;
    }

    /**
     * Deletes the notifier object when we are done with it.
     */
    public static void cleanNotifier(long notifierPtr)
    {

    }

    /**
     * Sets the notifier to call the callback in another triggerTime
     * microseconds.
     */
    public static void updateNotifierAlarm(long notifierPtr, long triggerTime)
    {

    }

    /**
     * Tells the notifier to stop calling the callback.
     */
    public static void stopNotifierAlarm(long notifierPtr)
    {

    }
}
