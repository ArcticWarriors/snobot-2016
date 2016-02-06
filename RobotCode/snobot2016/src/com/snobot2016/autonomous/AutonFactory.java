package com.snobot2016.autonomous;

import com.snobot2016.Properties2016;
import com.snobot2016.Snobot;
import com.snobot2016.positioner.IPositioner;
import com.snobot2016.smartdashboard.DefenseInFront;
import com.snobot2016.smartdashboard.SelectAutonomous;
import com.snobot2016.smartdashboard.SelectStartPosition;
import com.snobot2016.smartdashboard.SelectStartPosition.StartPositions;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class AutonFactory
{
    private DefenseInFront mDefenseInFront;
    private SelectStartPosition mSelectStartPosition;
    private SelectAutonomous mSelectAutonomous;

    private ITable mDefenseTable;
    private ITable mPostDefenseTable;

    private CommandParser mDefenseCommandParser;
    private CommandParser mPostDefenseCommandParser;

    public AutonFactory(IPositioner aPositioner, Snobot aSnobot)
    {
        mSelectStartPosition = new SelectStartPosition(aPositioner);
        mDefenseInFront = new DefenseInFront();
        mSelectAutonomous = new SelectAutonomous();

        mDefenseTable = NetworkTable.getTable(Properties2016.sDEFENSE_AUTON_TABLE);
        mPostDefenseTable = NetworkTable.getTable(Properties2016.sPOST_DEFENSE_AUTON_TABLE);

        mDefenseCommandParser = new CommandParser(aSnobot, mDefenseTable);
        mPostDefenseCommandParser = new CommandParser(aSnobot, mPostDefenseTable);

        this.putOnDash();
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
            cobbledCommandGroup.addSequential(mDefenseCommandParser.readFile(mDefenseInFront.getDefensePath()));
        }
        cobbledCommandGroup.addSequential(mPostDefenseCommandParser.readFile(mSelectAutonomous.getSelected()));
        return cobbledCommandGroup;
    }
}
