package com.snobot.coordinate_gui.desktop_app.log_gen;

import java.io.IOException;

import com.snobot.coordinate_gui.model.Coordinate;

public class SpiralGen extends GeneratorBase
{
    public SpiralGen() throws IOException
    {
        super("TestFiles/xxx.log");
    }
    
    public void generate() throws IOException
    {
        double angle = Math.PI/2;
        for (int i=0; i< 500; i++) 
        {
            angle = 0.05 * i;
            double x=(.017*i)*Math.sin(angle);
            double y=(.017*i)*Math.cos(angle);
            
            Coordinate c = new Coordinate(x, y, 90 + Math.toDegrees(angle));
            super.writeCoord(c);
        }
        
        flush();
    }

    public static void main(String[] args) throws IOException
    {
        new SpiralGen().generate();
    }
}