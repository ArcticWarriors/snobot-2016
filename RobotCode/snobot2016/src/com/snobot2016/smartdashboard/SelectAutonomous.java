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

public class SelectAutonomous
{
    private SendableChooser mSendableChooser;

    public SelectAutonomous()
    {
        mSendableChooser = this
                .loadAutonFiles(Properties2016.sAUTON_DIRECTORY.getValue() + "Autonomous/RealAutonomousModes/AutonomousThingsToDo", "");

    }

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

    public String getSelected()
    {
        return (String) mSendableChooser.getSelected();
    }

    public void putOnDash()
    {
        SmartDashboard.putData("Select Autonomous Goal", mSendableChooser);
    }
}
