package com.snobot2016.autonomous;

import java.util.List;

import com.snobot.xlib.ACommandParser;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.Snobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Creates commands from a file path and adds them to a CommandGroup.
 * 
 * @author Alec/Andrew
 *
 */
public class CommandParser extends ACommandParser
{
    protected Snobot mSnobot;

    /**
     * Creates a CommandParser object.
     * 
     * @param aSnobot
     *            The robot using the CommandParser.
     */
    public CommandParser(Snobot aSnobot)
    {
        super(" ", "#");
        mSnobot = aSnobot;
    }

    /**
     * Takes a list of Strings and creates a Command.
     * 
     * @param args
     *            The command's name and parameters.
     */
    @Override
    protected Command parseCommand(List<String> args)
    {
        String commandName = args.get(0);
        Command newCommand = null;
        try
        {
            switch (commandName)
            {
            case "StupidDriveStraight":
                newCommand = new StupidDriveStraight(mSnobot.getDriveTrain(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)));
                break;

            case "DriveStraightADistance":
                newCommand = new DriveStraightADistance(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)));
                break;

            case "StupidTurn":
                newCommand = new StupidTurn(mSnobot.getDriveTrain(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)));
                break;

            case "TurnWithDegrees":
                newCommand = new TurnWithDegrees(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)));
                break;

            case "GoToXY":
                newCommand = new GoToXY(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)), Double.parseDouble(args.get(3)));
                break;
            case "RaiseHarvester":
                newCommand = new RaiseHarvester(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case "LowerHarvester":
                newCommand = new LowerHarvester(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case "RollerIntake":
                newCommand = new RollerIntake(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case "RollerOuttake":
                newCommand = new RollerOuttake(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case "TiltLowerScaler":
                newCommand = new TiltLowerScaler(Double.parseDouble(args.get(1)), mSnobot.getScaling());
            case "TiltRaiseScaler":
                newCommand = new TiltRaiseScaler(Double.parseDouble(args.get(1)), mSnobot.getScaling());

            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.err.println("You have not specified enough aguments for the command: " + commandName + ". " + e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println("Failed to parse the command: " + commandName + ". " + e.getMessage());
            e.printStackTrace();
        }
        return newCommand;
    }

    /**
     * Puts the command's text file contents and parsing results on the
     * SmartDashboard.
     * 
     * @param aCommandString
     *            Contents of the command's text file.
     */
    @Override
    protected void publishParsingResults(String aCommandString)
    {
        if (!mErrorText.isEmpty())
        {
            aCommandString += "\n\n# There was an error parsing the commands...\n#\n";
            aCommandString += mErrorText;
        }

        ITable table = NetworkTable.getTable("SmartDashboard");

        table.putString(SmartDashBoardNames.sROBOT_COMMAND_TEXT, aCommandString);
        table.putBoolean(SmartDashBoardNames.sSUCCESFULLY_PARSED_AUTON, mSuccess);
    }
}
