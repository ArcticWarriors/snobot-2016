package com.snobot2016.autonomous;

import com.snobot2016.Snobot;
import com.snobot2016.positioner.IPositioner;
import com.snobot2016.smartdashboard.DefenseInFront;
import com.snobot2016.smartdashboard.SelectAutonomous;
import com.snobot2016.smartdashboard.SelectStartPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonFactory
{
    private IPositioner mPositioner;
    private DefenseInFront mDefenseInFront;
    private SelectStartPosition mSelectStartPosition;
    private SelectAutonomous mSelectAutonomous;
    public CommandGroup mCobbledCommandGroup;
    private CommandParser mCommandParser;
    private Snobot mSnobot;

    public AutonFactory(IPositioner aPositioner, Snobot aSnobot)
    {
        mPositioner = aPositioner;
        mSelectStartPosition = new SelectStartPosition(aPositioner);
        mDefenseInFront = new DefenseInFront();
        mSelectAutonomous = new SelectAutonomous();
        mSelectStartPosition.putOnDash();
        mDefenseInFront.putOnDash();
        mSelectAutonomous.putOnDash();
        mCobbledCommandGroup = new CommandGroup();
        mCommandParser = new CommandParser(mSnobot);
    }

    private void setPosition()
    {
        mSelectStartPosition.setStartPosition();
    }

}
