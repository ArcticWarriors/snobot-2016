package com.snobot2016.autonomous.trajectory;

import java.util.ArrayList;
import java.util.List;

import com.snobot.xlib.Utilities;
import com.snobot.xlib.motion_profile.trajectory.IdealSplineSerializer;
import com.snobot.xlib.motion_profile.trajectory.SplineSegment;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.drivetrain.IDriveTrain;
import com.snobot2016.positioner.IPositioner;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class TrajectoryPathCommand extends Command
{
    private IDriveTrain mDrivetrain;
    private IPositioner mPositioner;
    private TrajectoryFollower followerLeft = new TrajectoryFollower("left");
    private TrajectoryFollower followerRight = new TrajectoryFollower("right");
    private Path mPath;
    private double mStartingLeftDistance;
    private double mStartingRightDistance;
    private double mKTurn;

    private ITable mTable;

    private double mLastLeftDistance;
    private double mLastRightDistance;

    private String mSDIdealName;
    private String mSDRealName;

    public TrajectoryPathCommand(IDriveTrain aDrivetrain, IPositioner aPositioner, Path aPath)
    {
        mDrivetrain = aDrivetrain;
        mPositioner = aPositioner;
        mPath = aPath;

        mTable = NetworkTable.getTable(SmartDashBoardNames.sSPLINE_NAMESPACE);
        mSDIdealName = SmartDashBoardNames.sSPLINE_IDEAL_POINTS;
        mSDRealName = SmartDashBoardNames.sSPLINE_REAL_POINT;

        double kP = 0;
        double kD = 0;
        double kVelocity = .08;
        double kAccel = 0;

        mKTurn = .001;

        followerLeft.configure(kP, 0, kD, kVelocity, kAccel);
        followerRight.configure(kP, 0, kD, kVelocity, kAccel);

        followerLeft.reset();
        followerRight.reset();

        followerLeft.setTrajectory(aPath.getLeftWheelTrajectory());
        followerRight.setTrajectory(aPath.getRightWheelTrajectory());

        if (mTable.getString(mSDIdealName, "").isEmpty())
        {
            sendIdealPath();
        }
    }

    @Override
    protected void initialize()
    {
        mStartingLeftDistance = mDrivetrain.getLeftEncoderDistance();
        mStartingRightDistance = mDrivetrain.getRightEncoderDistance();

        sendIdealPath();
    }

    @Override
    protected void execute()
    {
        double dt = .02;

        double distanceL = mDrivetrain.getLeftEncoderDistance() - mStartingLeftDistance;
        double distanceR = mDrivetrain.getRightEncoderDistance() - mStartingRightDistance;

        double speedLeft = followerLeft.calculate(distanceL);
        double speedRight = followerRight.calculate(distanceR);

        double goalHeading = Math.toDegrees(followerLeft.getHeading());
        double observedHeading = mPositioner.getOrientationDegrees();

        double angleDiff = Utilities.getDifferenceInAngleDegrees(observedHeading, goalHeading);

        double turn = mKTurn * angleDiff;

        mDrivetrain.setLeftRightSpeed(speedLeft + turn, speedRight - turn);

        SplineSegment segment = new SplineSegment();
        segment.left_pos = distanceL;
        segment.left_vel = (distanceL - mLastLeftDistance) / dt;
        segment.right_pos = distanceR;
        segment.right_vel = (distanceR - mLastRightDistance) / dt;
        segment.heading = Utilities.boundAngleNeg180to180Degrees(observedHeading);

        String point_info = followerLeft.getCurrentSegment() + "," + IdealSplineSerializer.serializePathPoint(segment);

        mTable.putString(mSDRealName, point_info);

        mLastLeftDistance = distanceL;
        mLastRightDistance = distanceR;
    }

    @Override
    protected boolean isFinished()
    {
        return followerLeft.isFinishedTrajectory();
    }

    @Override
    protected void end()
    {
        mDrivetrain.stop();

    }

    @Override
    protected void interrupted()
    {

    }

    private void sendIdealPath()
    {
        List<SplineSegment> segments = new ArrayList<SplineSegment>();

        Trajectory left = mPath.getLeftWheelTrajectory();
        Trajectory right = mPath.getRightWheelTrajectory();

        for (int i = 0; i < left.getNumSegments(); ++i)
        {
            SplineSegment segment = new SplineSegment();
            segment.left_pos = left.getSegment(i).pos;
            segment.left_vel = left.getSegment(i).vel;
            segment.right_pos = right.getSegment(i).pos;
            segment.right_vel = right.getSegment(i).vel;
            segment.heading = right.getSegment(i).heading;

            segments.add(segment);
        }

        mTable.putString(mSDIdealName, IdealSplineSerializer.serializePath(segments));
    }

}
