package com.snobot.simulator.joysticks;

import java.util.Arrays;
import java.util.Collection;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Rumbler;

public class PrintJinputThings
{

    public static void printAll()
    {
        printAll(Arrays.asList(Type.UNKNOWN, Type.KEYBOARD));
    }

    // http://www.java-gaming.org/index.php?topic=16866.0
    public static void printAll(Collection<Type> typesToIgnore)
    {
        Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();

        System.out.println("Joysticks:");

        for (int i = 0; i < ca.length; i++)
        {
            Type type = ca[i].getType();
            if (typesToIgnore.contains(type))
            {
                continue;
            }

            Component[] components = ca[i].getComponents();

            /* Get the name of the controller */
            System.out.println("  " + ca[i].getName());
            System.out.println("  Type: " + ca[i].getType().toString());
            System.out.println("  PortType: " + ca[i].getPortType().toString());
            System.out.println("  PortNumber: " + ca[i].getPortNumber());
            System.out.println("  Component Count: " + components.length);

            for (int j = 0; j < components.length; j++)
            {

                /* Get the components name */
                System.out.print("    Component " + j + ":  \t" + components[j].getName());
                System.out.print("\tIdentifier: " + components[j].getIdentifier().getName() + "\tComponentType:\t");
                if (components[j].isRelative())
                {
                    System.out.print("(Relative");
                }
                else
                {
                    System.out.print("(Absolute");
                }
                if (components[j].isAnalog())
                {
                    System.out.print(" Analog)");
                }
                else
                {
                    System.out.print(" Digital)");
                }

                System.out.println();
            }

            Rumbler[] rumblers = ca[i].getRumblers();
            System.out.println("  Rumbler Count : " + rumblers.length);

            for (int j = 0; j < rumblers.length; j++)
            {
                System.out.println(rumblers[j]);
                // mController.getRumblers();
            }

            System.out.println("\n\n");
        }

        // System.exit(0);

    }

    public static void printAxis(Controller aController)
    {
        Component[] components = aController.getComponents();

        for (int j = 0; j < components.length; j++)
        {

            if (components[j].isAnalog())
            {
                System.out.println(
                        "Analog Value [" + j + "]\t= " + components[j].getPollData() + "\t(" + components[j].getIdentifier().getName() + ")");
            }
        }
        System.out.println();
    }

    public static void printButtons(Controller aController)
    {
        Component[] components = aController.getComponents();

        for (int j = 0; j < components.length; j++)
        {

            if (!components[j].isAnalog())
            {
                System.out.println(
                        "Digital Value [" + j + "]\t= " + components[j].getPollData() + "\t(" + components[j].getIdentifier().getName() + ")");
            }
        }
        System.out.println();
    }
}