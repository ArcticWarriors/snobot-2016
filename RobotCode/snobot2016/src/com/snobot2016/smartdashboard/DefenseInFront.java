package com.snobot2016.smartdashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefenseInFront
{
    private SendableChooser mDefenseInFront;

    public enum Defenses
    {
        LOW_BAR, PORTCULLIS, CHIVAL_DE_FRISE, MOAT, RAMPARTS, DRAWBRIDGE, SALLY_PORT, ROCK_WALL, ROUGH_TERRAIN, OUTER_WORKS, DO_NOTHING
    }

    public DefenseInFront()
    {
        mDefenseInFront = new SendableChooser();
        mDefenseInFront.addDefault("Low Bar", Defenses.LOW_BAR);
        mDefenseInFront.addObject("Portcullis", Defenses.PORTCULLIS);
        mDefenseInFront.addObject("Chival de Frise", Defenses.CHIVAL_DE_FRISE);
        mDefenseInFront.addObject("Moat", Defenses.MOAT);
        mDefenseInFront.addObject("Ramparts", Defenses.RAMPARTS);
        mDefenseInFront.addObject("Drawbridge", Defenses.DRAWBRIDGE);
        mDefenseInFront.addObject("Sally Port", Defenses.SALLY_PORT);
        mDefenseInFront.addObject("Rock Wall", Defenses.ROCK_WALL);
        mDefenseInFront.addObject("Rough Terrain", Defenses.ROUGH_TERRAIN);
        mDefenseInFront.addObject("Just Reach Outer Works", Defenses.OUTER_WORKS);
        mDefenseInFront.addObject("Do Nothing", Defenses.DO_NOTHING);
    }

    public void putOnDash()
    {
        SmartDashboard.putData("Defense in front of the Robot:", mDefenseInFront);
    }

    public Defenses getSelected()
    {
        return (Defenses) mDefenseInFront.getSelected();
    }
}
