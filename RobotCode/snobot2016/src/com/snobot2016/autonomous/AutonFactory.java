package com.snobot2016.autonomous;

import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.Snobot;
import com.snobot2016.positioner.IPositioner;
import com.snobot2016.smartdashboard.DefenseInFront;
import com.snobot2016.smartdashboard.SelectAutonomous;
import com.snobot2016.smartdashboard.SelectStartPosition;
import com.snobot2016.smartdashboard.SelectStartPosition.StartPositions;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

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


        mDefenseTable = NetworkTable.getTable(SmartDashBoardNames.sDEFENSE_AUTON_TABLE);
        mPostDefenseTable = NetworkTable.getTable(SmartDashBoardNames.sPOST_DEFENSE_AUTON_TABLE);

        mDefenseCommandParser = new CommandParser(aSnobot, mDefenseTable);
        mPostDefenseCommandParser = new CommandParser(aSnobot, mPostDefenseTable);

        this.putOnDash();
        addListeners();
    }

    private void addListeners()
    {
        // Whenever the SendableChooser is changed (different button selected),
        // this will call build auton, forcing the position to update, and the
        // command parsers to re-send the autonomous mode
        ITableListener selectionChangeListener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                buildAnAuton();
            }
        };

        ITableListener saveDefenseModeListener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                if (mDefenseTable.getBoolean(SmartDashBoardNames.sSAVE_AUTON, false))
                {
                    System.out.println("Saving defense");
                    mDefenseCommandParser.saveAutonMode();
                }
            }
        };

        ITableListener savePostDefenseModeListener = new ITableListener()
        {

            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                if (mPostDefenseTable.getBoolean(SmartDashBoardNames.sSAVE_AUTON, false))
                {
                    System.out.println("Saving Post defense");
                    mPostDefenseCommandParser.saveAutonMode();
                }
            }
        };

        mDefenseInFront.addChangeListener(selectionChangeListener);
        mSelectAutonomous.addChangeListener(selectionChangeListener);
        mSelectStartPosition.addChangeListener(selectionChangeListener);

        mDefenseTable.addTableListener(SmartDashBoardNames.sSAVE_AUTON, saveDefenseModeListener, true);
        mPostDefenseTable.addTableListener(SmartDashBoardNames.sSAVE_AUTON, savePostDefenseModeListener, true);
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
