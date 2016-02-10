package com.snobot.simulator.module_wrapper;

import com.snobot.simulator.ISimulatorUpdater;

public class PotentiometerSimulator implements ISimulatorUpdater
{
    private AnalogWrapper mWrapper;
    private SpeedControllerWrapper mSpeedController;

    private double mThrow;
    private double mMaxVoltage;
    private double mMinVoltage;

    public PotentiometerSimulator(AnalogWrapper aWrapper, SpeedControllerWrapper aSpeedController)
    {
        mWrapper = aWrapper;
        mSpeedController = aSpeedController;

        mThrow = mMaxVoltage = mMinVoltage = 1;
    }

    public void setParameters(double aThrow, double aMaxVoltage, double aMinVoltage)
    {
        mThrow = aThrow;
        mMaxVoltage = aMaxVoltage;
        mMinVoltage = aMinVoltage;
    }

    public void update()
    {
        double voltage_diff = mMinVoltage - mMaxVoltage;
        double ipv = mThrow / voltage_diff;
        double voltage = mMaxVoltage - (mSpeedController.getPosition() - mThrow) / ipv;

        mWrapper.setVoltage(voltage);
    }

}