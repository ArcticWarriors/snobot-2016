package com.visionTest.auto_turret;

public interface IStateManager
{
    public class RobotState
    {
        public double mRobotX;
        public double mRobotY;
        public double mAbsoluteAngle;

        @Override
        public String toString()
        {
            return "RobotState [mRobotX=" + mRobotX + ", mRobotY=" + mRobotY + ", mAbsoluteAngle=" + mAbsoluteAngle + "]";
        }

    }

    public class TargetLocation
    {
        public double mX;
        public double mY;

        @Override
        public String toString()
        {
            return "TargetLocation [mX=" + mX + ", mY=" + mY + "]";
        }

    }

    public abstract void setCurrentCameraState(double aTimestamp, double aCameraOffset, double aCameraDistance);

    public abstract void saveRobotState(double aTime, double aRobotX, double aRobotY, double aRobotAngle, double aTurretAngle);

    public abstract boolean canCalculate(double aTimestamp);

    public abstract RobotState getStateHistory(double aTimestamp);

    public abstract TargetLocation getTargetLocation();

    public abstract double calculateDesiredAngleFromState();

}