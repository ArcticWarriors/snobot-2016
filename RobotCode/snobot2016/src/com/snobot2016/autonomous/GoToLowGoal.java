package com.snobot2016.autonomous;

import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This class decides which low goal to drive to and moves to it.
 * 
 * @author Andrew
 *
 */
public class GoToLowGoal extends Command
{
    private IPositioner mPositioner;
    private IDriveTrain mDriveTrain;
    private boolean mGoRight;
    private Command mGoToXYPath;
    private double mXPosition;
    private double mYPosition;
    private double mMaxTurnVel;
    private double mMaxTurnAccel;
    private double mMaxDriveVel;
    private double mMaxDriveAccel;
    private CommandGroup mCommandGroup;

    /**
     * 
     * @param aPositioner
     *            The Robot's positioner
     * @param aDriveTrain
     *            The robot's drive train
     * @param aMaxTurnVel
     *            The maximum speed you want the robot to travel at when turning
     * @param aMaxTurnAccel
     *            The maximum speed you want the robot to accelerate at when
     *            turning
     * @param aMaxDriveVel
     *            The maximum speed you want the robot to travel at when driving
     * @param aMaxDriveAccel
     *            The maximum speed you want the robot to accelerate at when
     *            driving
     */
    public GoToLowGoal(IPositioner aPositioner, IDriveTrain aDriveTrain, double aMaxTurnVel, double aMaxTurnAccel, double aMaxDriveVel,
            double aMaxDriveAccel)
    {
        mDriveTrain = aDriveTrain;
        mPositioner = aPositioner;
        mMaxTurnVel = aMaxTurnVel;
        mMaxTurnAccel = aMaxTurnAccel;
        mMaxDriveVel = aMaxDriveVel;
        mMaxDriveAccel = aMaxDriveAccel;
        mYPosition = 305;

    }

    /**
     * Init- if statement to decide which low goal to go to; also adds the
     * correct GoToXYPath command
     */
    @Override
    protected void initialize()
    {
        if (mPositioner.getXPosition() >= 0)
        {
            mGoRight = false;
            mXPosition = 200;
        }
        else
        {
            mGoRight = true;
            mXPosition = -200;
        }

        mGoToXYPath = new GoToXYPath(mDriveTrain, mPositioner, mXPosition, mYPosition, mMaxTurnVel, mMaxTurnAccel, mMaxDriveVel, mMaxDriveAccel);
        mCommandGroup = new CommandGroup();
        mCommandGroup.addSequential(mGoToXYPath);
    }

    /**
     * Starts the command group
     */
    @Override
    protected void execute()
    {
        mCommandGroup.start();
    }

    /**
     * Returns whether or not the command group is finished
     */
    @Override
    protected boolean isFinished()
    {
        return !mCommandGroup.isRunning();
    }

    @Override
    protected void end()
    {
        mDriveTrain.stop();
    }

    @Override
    protected void interrupted()
    {

    }

}
