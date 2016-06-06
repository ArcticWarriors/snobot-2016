package com.snobot.coordinate_gui.desktop_app.log_gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.snobot.coordinate_gui.model.Coordinate;

public class GeneratorBase
{
    private BufferedWriter bw;

    public GeneratorBase(String aFile) throws IOException
    {
        bw = new BufferedWriter(new FileWriter(aFile));
    }
    
    public void writeCoord(Coordinate c) throws IOException
    {
        bw.write(c.x + ", " + c.y + ", " + c.angle + "\n");
    }
    
    public void flush() throws IOException
    {
        bw.flush();
    }
}