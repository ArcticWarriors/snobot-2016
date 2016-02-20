package com.snobot.simulator.joysticks;

import java.util.Arrays;

import net.java.games.input.Component.Identifier;

public class XboxJoystick extends SpecificGamepadJoystick
{

    private static final Identifier[] sAXIS = new Identifier[]
    {       Identifier.Axis.X, // Left x
            Identifier.Axis.Y, // Left Y
            Identifier.Axis.Z, // Left Trigger
            Identifier.Axis.RZ, // Right Trigger
            Identifier.Axis.RX, // Right x
            Identifier.Axis.RY, // Right y
    };

    private static final Identifier[] sBUTTONS = new Identifier[]
    {       Identifier.Button._0, // A
            Identifier.Button._1, // B
            Identifier.Button._2, // X
            Identifier.Button._3, // Y
            Identifier.Button._4, // LB
            Identifier.Button._5, // RB
            Identifier.Button._6, // windows/back
            Identifier.Button._7, // lines/start
            Identifier.Button._8, // Left Joystick In
            Identifier.Button._9, // Right Joystick In
    };

    private static final Identifier[] sPOV = new Identifier[] 
    {  
            Identifier.Axis.POV
    };

    public XboxJoystick()
    {
        super("Controller (Xbox One For Windows)", Arrays.asList(sAXIS), Arrays.asList(sBUTTONS), Arrays.asList(sPOV));
    }
}
