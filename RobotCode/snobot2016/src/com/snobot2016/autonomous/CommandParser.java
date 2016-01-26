package com.snobot2016.autonomous;

import java.util.List;

import com.snobot.xlib.ACommandParser;
import com.snobot2016.Snobot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandParser extends ACommandParser
{
    protected Snobot mSnobot;

    public CommandParser(Snobot aSnobot)
    {
        super(" ", "#");
        mSnobot = aSnobot;
    }

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
                System.out.println(mSnobot);
                System.out.println(mSnobot.getDriveTrain());
                System.out.println("Args 1 " + args.get(1));
                System.out.println("Args 1 " + args.get(2));
                newCommand = new StupidDriveStraight(mSnobot.getDriveTrain(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)));
                break;
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

    @Override
    protected void publishParsingResults(String fileContents)
    {
        System.out.println(fileContents);
    }

}
