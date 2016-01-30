package com.snobot2016.smartdashboard;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefenseInFront
{
    private SendableChooser mDefenseInFront;

    public DefenseInFront()
    {
        mDefenseInFront = new SendableChooser();
    }

    private void Defenses()
    {
        mDefenseInFront.addDefault("Low Bar", Defenses.LOW_BAR);
        mDefenseInFront.addObject("Portcullis", Defenses.PORTCULLIS);
        mDefenseInFront.addObject("Chival de Frise", Defenses.CHIVAL_DE_FRISE);
        mDefenseInFront.addObject("Moat", Defenses.MOAT);
        mDefenseInFront.addObject("Ramparts", Defenses.RAMPARTS);
        mDefenseInFront.addObject("Drawbridge", Defenses.DRAWBRIDGE);
        mDefenseInFront.addObject("Sally Port", Defenses.SALLY_PORT);
        mDefenseInFront.addObject("Rock Wall", Defenses.ROCK_WALL);
        mDefenseInFront.addObject("Rough Terrain", Defenses.ROUGH_TERRAIN);
    }

    public void putOnDash()
    {
        SmartDashboard.putData("Defense in front of the Robot:", mDefenseInFront);
    }

    public Defenses getSelected()
    {
        return (com.snobot2016.smartdashboard.Defenses) mDefenseInFront.getSelected();
    }
}
