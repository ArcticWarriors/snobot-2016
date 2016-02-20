package com.snobot2016.autonomous;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.snobot.xlib.ACommandParser;
import com.snobot.xlib.motion_profile.simple.ISetpointIterator;
import com.snobot.xlib.motion_profile.simple.PathConfig;
import com.snobot.xlib.motion_profile.simple.PathGenerator;
import com.snobot.xlib.motion_profile.simple.PathSetpoint;
import com.snobot.xlib.motion_profile.simple.StaticSetpointIterator;
import com.snobot2016.Properties2016;
import com.snobot2016.SmartDashBoardNames;
import com.snobot2016.Snobot;
import com.snobot2016.autonomous.path.DriveStraightPath;
import com.snobot2016.autonomous.path.DriveTurnPath;
import com.snobot2016.scaling.IScaling.ScaleAngles;
import com.snobot2016.smartdashboard.DefenseInFront;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Creates commands from a file path and adds them to a CommandGroup.
 * 
 * @author Alec/Andrew
 *
 */
public class CommandParser extends ACommandParser
{

    private static final double sEXPECTED_DT = .02;

    protected Snobot mSnobot;
    protected ITable mAutonTable;
    protected String mParserName;
    protected CommandParser mDefenseParser;
    protected DefenseInFront mDefenseGetter;

    public CommandParser(Snobot aSnobot, ITable aAutonTable, String aParserName)
    {
        this(aSnobot, aAutonTable, aParserName, null, null);
    }

    /**
     * Creates a CommandParser object.
     * 
     * @param aSnobot
     *            The robot using the CommandParser.
     */
    public CommandParser(Snobot aSnobot, ITable aAutonTable, String aParserName, CommandParser aDefenseParser, DefenseInFront aDefenseGetter)
    {
        super(" ", "#");
        mSnobot = aSnobot;
        mAutonTable = aAutonTable;
        mParserName = aParserName;
        mDefenseParser = aDefenseParser;
        mDefenseGetter = aDefenseGetter;
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
            case Properties2016.sSTUPID_DRIVE_STRAIGHT:
                newCommand = new StupidDriveStraight(mSnobot.getDriveTrain(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)));
                break;

            case Properties2016.sDRIVE_STRAIGHT_A_DISTANCE:
                newCommand = new DriveStraightADistance(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)));
                break;

            case Properties2016.sSTUPID_TURN:
                newCommand = new StupidTurn(mSnobot.getDriveTrain(), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)));
                break;

            case Properties2016.sTURN_WITH_DEGREES:
                newCommand = new TurnWithDegrees(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)));
                break;
            case Properties2016.sGO_TO_XY:
                newCommand = new GoToXY(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)), Double.parseDouble(args.get(3)));
                break;
            case Properties2016.sGO_TO_XY_PATH:
                newCommand = new GoToXYPath(mSnobot.getDriveTrain(), mSnobot.getPositioner(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)), Double.parseDouble(args.get(3)), Double.parseDouble(args.get(4)),
                        Double.parseDouble(args.get(5)), Double.parseDouble(args.get(6)));
                break;
            case Properties2016.sRAISE_HARVESTER:
                newCommand = new RaiseHarvester(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case Properties2016.sLOWER_HARVESTER:
                newCommand = new LowerHarvester(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case Properties2016.sROLLER_INTAKE:
                newCommand = new RollerIntake(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case Properties2016.sROLLER_OUTTAKE:
                newCommand = new RollerOuttake(Double.parseDouble(args.get(1)), mSnobot.getHarvester());
                break;
            case Properties2016.sTILT_LOWER_SCALER:
                newCommand = new TiltLowerScaler(Double.parseDouble(args.get(1)), mSnobot.getScaling());
                break;
            case Properties2016.sTILT_RAISE_SCALER:
                newCommand = new TiltRaiseScaler(Double.parseDouble(args.get(1)), mSnobot.getScaling());
                break;
            case Properties2016.sSMART_HARVESTER:
                newCommand = new SmartRaiseLowerHarvester(mSnobot.getHarvester(), args.get(1));
                break;
            case Properties2016.sSUPER_SMART_HARVESTER:
                newCommand = new SuperSmartRaiseLowerHarvester(mSnobot.getHarvester(), Double.parseDouble(args.get(1)));
                break;
            case Properties2016.sSMART_SCALER:
                newCommand = new SmartScaler(mSnobot.getScaling(), ScaleAngles.valueOf(args.get(1)));
                break;
            case Properties2016.sDRIVE_STRAIGHT_PATH:

            {
                PathConfig dudePathConfig = new PathConfig(Double.parseDouble(args.get(1)), // Endpoint
                        Double.parseDouble(args.get(2)), // Max Velocity
                        Double.parseDouble(args.get(3)), // Max Acceleration
                        sEXPECTED_DT);

                ISetpointIterator dudeSetpointIterator;

                // TODO create dynamic iterator, way to switch
                if (true)
                {
                    PathGenerator dudePathGenerator = new PathGenerator();
                    List<PathSetpoint> dudeList = dudePathGenerator.generate(dudePathConfig);
                    dudeSetpointIterator = new StaticSetpointIterator(dudeList);
                }

                newCommand = new DriveStraightPath(mSnobot.getDriveTrain(), mSnobot.getPositioner(), dudeSetpointIterator);
                break;

            }

            case Properties2016.sDRIVE_TURN_PATH:
            {
                PathConfig dudePathConfig = new PathConfig(Double.parseDouble(args.get(1)), // Endpoint
                        Double.parseDouble(args.get(2)), // Max Velocity
                        Double.parseDouble(args.get(3)), // Max Acceleration
                        sEXPECTED_DT);

                ISetpointIterator dudeSetpointIterator;

                // TODO create dynamic iterator, way to switch
                if (true)
                {
                    dudeSetpointIterator = new StaticSetpointIterator(dudePathConfig);
                }

                newCommand = new DriveTurnPath(mSnobot.getDriveTrain(), mSnobot.getPositioner(), dudeSetpointIterator);
                break;
            }
            case Properties2016.sFUDGE_THE_POSITION:
            {
                double newX;
                double newY;
                if (args.get(1).equals("Same"))
                {
                    newX = mSnobot.getPositioner().getXPosition();
                }
                else
                {
                    newX = Double.parseDouble(args.get(1));
                }

                if (args.get(2).equals("Same"))
                {
                    newY = mSnobot.getPositioner().getYPosition();
                }
                else
                {
                    newY = Double.parseDouble(args.get(2));
                }

                newCommand = new FudgeThePosition(mSnobot.getPositioner(), newX, newY);
                break;
            }

            case Properties2016.sGO_TO_LOW_GOAL:
                newCommand = new GoToLowGoal(mSnobot.getPositioner(), mSnobot.getDriveTrain(), Double.parseDouble(args.get(1)),
                        Double.parseDouble(args.get(2)), Double.parseDouble(args.get(3)), Double.parseDouble(args.get(4)));
                break;
            case Properties2016.sCROSS_DEFENSE:
            {
                if (mDefenseParser != null)
                {
                    newCommand = mDefenseParser.readFile(mDefenseGetter.getDefensePath());
                }
                else
                {
                    addError("Defense parser or defense getter is null");
                }
                break;
            }
            default:
                addError("Received unexpected command name '" + commandName + "'");
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            addError("You have not specified enough aguments for the command: " + commandName + ". " + e.getMessage());
        }
        catch (Exception e)
        {
            addError("Failed to parse the command: " + commandName + ". " + e.getMessage());
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

        mAutonTable.putString(SmartDashBoardNames.sROBOT_COMMAND_TEXT, aCommandString);
        mAutonTable.putBoolean(SmartDashBoardNames.sSUCCESFULLY_PARSED_AUTON, mSuccess);
    }

    @Override
    public CommandGroup readFile(String aFilePath)
    {
        if (aFilePath == null)
        {
            aFilePath = "NOT FOUND!";
        }

        mAutonTable.putString(SmartDashBoardNames.sAUTON_FILENAME, aFilePath);
        return super.readFile(aFilePath);
    }

    public void saveAutonMode()
    {
        String new_text = mAutonTable.getString(SmartDashBoardNames.sROBOT_COMMAND_TEXT, "");
        String filename = mAutonTable.getString(SmartDashBoardNames.sAUTON_FILENAME, "auton_file.txt");

        System.out.println("*****************************************");
        System.out.println("(\"" + mParserName + "\") - Saving auton mode to " + filename);
        System.out.println("*****************************************");

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
            bw.write(new_text);
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
