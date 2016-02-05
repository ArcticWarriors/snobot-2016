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

    // private String mAutonGoalsPath;

    public AutonFactory(IPositioner aPositioner, Snobot aSnobot)
    {
        mSelectStartPosition = new SelectStartPosition(aPositioner);
        mDefenseInFront = new DefenseInFront();
        mSelectAutonomous = new SelectAutonomous();
        mCommandParser = new CommandParser(aSnobot);

        this.putOnDash();

        mDefensesAutonsPath = Properties2016.sAUTON_DIRECTORY.getValue() + "Autonomous/RealAutonomousModes/DefenseAutons/";
        // mAutonGoalsPath = Properties2016.sAUTON_DIRECTORY.getValue() +
        // "Autonomous/RealAutonomousModes/AutonomousThingsToDo";
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
            cobbledCommandGroup.addSequential(mCommandParser.readFile(mDefensesAutonsPath + mDefenseInFront.getSelected()));
            // switch (mDefenseInFront.getSelected())
            // {
            // case LOW_BAR:
            // mCommandParser.readFile(mDefensesAutonsPath + "LOW_BAR");
            // break;
            // case PORTCULLIS:
            // mCommandParser.readFile(mDefensesAutonsPath + "PORTCULLIS");
            // break;
            // case CHIVAL_DE_FRISE:
            // mCommandParser.readFile(mDefensesAutonsPath + "CHIVAL_DE_FRISE");
            // break;
            // case MOAT:
            // mCommandParser.readFile(mDefensesAutonsPath + "MOAT");
            // break;
            // case RAMPARTS:
            // mCommandParser.readFile(mDefensesAutonsPath + "RAMPARTS");
            // break;
            // case DRAWBRIDGE:
            // mCommandParser.readFile(mDefensesAutonsPath + "DRAWBRIDGE");
            // break;
            // case SALLY_PORT:
            // mCommandParser.readFile(mDefensesAutonsPath + "SALLY_PORT");
            // break;
            // case ROCK_WALL:
            // mCommandParser.readFile(mDefensesAutonsPath + "ROCK_WALL");
            // break;
            // case ROUGH_TERRAIN:
            // mCommandParser.readFile(mDefensesAutonsPath + "ROUGH_TERRAIN");
            // break;
            // case OUTER_WORKS:
            // mCommandParser.readFile(mDefensesAutonsPath + "OUTER_WORKS");
            // break;
            // }
        }
        cobbledCommandGroup.addSequential(mCommandParser.readFile(mSelectAutonomous.getSelected()));
        return cobbledCommandGroup;
    }
}
