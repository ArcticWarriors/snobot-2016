package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToXY extends Command
{
    private IDriveTrain mDriveTrain;
    private IPositioner mPositioner;
    private double mFinalXCoor;
    private double mFinalYCoor;
    private double mCurrentX;
    private double mCurrentY;
    private double mTurnDegrees;
    private double mDriveDistance;
    private double mSpeed;
    private Command mTurnWithDegrees;
    private Command mDriveStraightADistance;
    private CommandGroup mCommandGroup;

    public GoToXY(IDriveTrain aDriveTrain, IPositioner aPositioner, double aXCoor, double aYCoor, double aSpeed)
    {
        mDriveTrain = aDriveTrain;
        mPositioner = aPositioner;
        mFinalXCoor = aXCoor;
        mFinalYCoor = aYCoor;
        mSpeed = aSpeed;

    }

    @Override
    protected void initialize()
    {
        mCommandGroup = new CommandGroup();
        mCurrentX = mPositioner.getXPosition();
        mCurrentY = mPositioner.getYPosition();
        mDriveDistance = Math.sqrt((Math.pow((mFinalXCoor - mCurrentX), 2)) + (Math.pow((mFinalYCoor - mCurrentY), 2)));
        mTurnDegrees = Math.atan((mFinalXCoor - mCurrentX) / (mFinalYCoor - mCurrentY));
        mTurnWithDegrees = new TurnWithDegrees(mDriveTrain, mPositioner, mTurnDegrees, mSpeed);
        mDriveStraightADistance = new DriveStraightADistance(mDriveTrain, mPositioner, mDriveDistance, mSpeed);
        mCommandGroup.addSequential(mTurnWithDegrees);
        mCommandGroup.addSequential(mDriveStraightADistance);
        mCommandGroup.start();

    }

    @Override
    protected void execute()
    {

    }

    @Override
    protected boolean isFinished()
    {
        if (!mCommandGroup.isRunning())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void end()
    {
        mDriveTrain.setLeftRightSpeed(0, 0);
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub

    }

}
