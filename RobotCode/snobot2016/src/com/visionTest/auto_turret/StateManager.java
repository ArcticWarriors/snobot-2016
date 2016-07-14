package com.visionTest.auto_turret;


public class StateManager implements IStateManager
{

    @Override
    public void setCurrentCameraState(double aTimestamp, double aCameraOffset, double aCameraDistance)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveRobotState(double aTime, double aRobotX, double aRobotY, double aRobotAngle, double aTurretAngle)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canCalculate(double aTimestamp)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public RobotState getStateHistory(double aTimestamp)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TargetLocation getTargetLocation()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double calculateDesiredAngleFromState()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
