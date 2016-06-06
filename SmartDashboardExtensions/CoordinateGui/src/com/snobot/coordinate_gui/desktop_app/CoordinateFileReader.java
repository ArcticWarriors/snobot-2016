package com.snobot.coordinate_gui.desktop_app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.snobot.coordinate_gui.model.Coordinate;

/**
 * Reads a file containing a list of coordinates to add to the field
 * @author Rich
 */
public class CoordinateFileReader
{

    private static final String sDELIMETER = ",";

    private CoordinateFileReader()
    {
    }

    /**
     * Reads a file containing a list of coordinates.
     * @param aFile The file to read
     * @return The list of coordinates that were read
     */
    public static List<Coordinate> readFile(String aFile)
    {
        String line;
        ArrayList<Coordinate> data = new ArrayList<Coordinate>();

        try
        {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, sDELIMETER);
                Coordinate c = new Coordinate(
                        Double.parseDouble(st.nextToken()),
                        Double.parseDouble(st.nextToken()),
                        Double.parseDouble(st.nextToken()));

                data.add(c);
            }
            
            br.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }

        return data;
    }
}
