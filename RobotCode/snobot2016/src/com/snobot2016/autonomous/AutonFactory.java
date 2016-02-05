package com.snobot2016.autonomous;

import com.snobot2016.Properties2016;
import com.snobot2016.Snobot;
import com.snobot2016.positioner.IPositioner;
import com.snobot2016.smartdashboard.DefenseInFront;
import com.snobot2016.smartdashboard.SelectAutonomous;
import com.snobot2016.smartdashboard.SelectStartPosition;
import com.snobot2016.smartdashboard.SelectStartPosition.StartPositions;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonFactory
{
    private DefenseInFront mDefenseInFront;
    private SelectStartPosition mSelectStartPosition;
    private SelectAutonomous mSelectAutonomous;
    private CommandParser mCommandParser;
    private String mDefensesAutonsPath;

    public AutonFactory(IPositioner aPositioner, Snobot aSnobot)
    {
        mSelectStartPosition = new SelectStartPosition(aPositioner);
        mDefenseInFront = new DefenseInFront();
        mSelectAutonomous = new SelectAutonomous();
        mCommandParser = new CommandParser(aSnobot);

        this.putOnDash();

        mDefensesAutonsPath = Properties2016.sAUTON_DIRECTORY.getValue() + "Autonomous/RealAutonomousModes/DefenseAutons/";
    }

    public void putOnDash()
    {
        mSelectAutonomous.putOnDash();
        mSelectStartPosition.putOnDash();
        mDefenseInFront.putOnDash();
    }

    public CommandGroup buildAnAuton()
    {
        mSelectStartPosition.setStartPosition();

        CommandGroup cobbledCommandGroup = new CommandGroup();
        boolean goingThroughDefense = true;
        if (mSelectStartPosition.getSelected() == StartPositions.SPY_POSITION)
        {
            goingThroughDefense = false;
        }

        if (goingThroughDefense)
        {
            cobbledCommandGroup.addSequential(mCommandParser.readFile(mDefensesAutonsPath + mDefenseInFront.getSelected() + ".txt"));
        }
        cobbledCommandGroup.addSequential(mCommandParser.readFile(mSelectAutonomous.getSelected()));
        return cobbledCommandGroup;
    }
}
