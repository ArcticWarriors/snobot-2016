package com.snobot2016.smartdashboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.snobot.xlib.SnobotAutonCrawler;
import com.snobot2016.Properties2016;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Andrew/Alec
 */
public class SelectAutonomous
{
    /**
     * The sendable chooser this class makes
     */
    private SendableChooser mSendableChooser;

    /**
     * The constructor uses the loadAutonFiles method below it to create the
     * Sendable Chooser from all of the auton modes in our specified folder.
     */
    public SelectAutonomous()
    {
        mSendableChooser = loadAutonFiles(Properties2016.sAUTON_POST_DEFENSE_DIRECTORY.getValue() + "/", "");

    }

    /**
     * This is just part of the auton crawler wee lifted from last year. It
     * makes a chooser out of all the files in a specified folder.
     */
    public SendableChooser loadAutonFiles(String aDir, String aIgnoreString)
    {
        SendableChooser output = new SendableChooser();
        File autonDr = new File(aDir);

        System.out.println("Reading auton files from directory " + autonDr.getAbsolutePath());
        System.out.println(" Using filter : \"" + aIgnoreString + "\"");

        try
        {
            SnobotAutonCrawler fileProcessor = new SnobotAutonCrawler(aIgnoreString);
            Files.walkFileTree(Paths.get(autonDr.toURI()), fileProcessor);

            for (Path p : fileProcessor.getPaths())
            {
                output.addObject(p.getFileName().toString(), p.toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * Returns the selected filepath as a String.
     */
    public String getSelected()
    {
        return (String) mSendableChooser.getSelected();
    }

    /**
     * Puts the chooser on the smart dashboard when called.
     */
    public void putOnDash()
    {
        SmartDashboard.putData("Select Autonomous Goal", mSendableChooser);
    }
}
