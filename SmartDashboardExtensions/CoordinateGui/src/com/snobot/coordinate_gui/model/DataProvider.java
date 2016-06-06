package com.snobot.coordinate_gui.model;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class DataProvider<DataType>
{
    protected Deque<DataType> mCoordinates;

    public DataProvider()
    {
        mCoordinates = new LinkedList<>();
    }

    public void addData(DataType aCoordinate)
    {
        mCoordinates.add(aCoordinate);
    }

    public DataType getMostRecentData()
    {
        return mCoordinates.peekLast();
    }

    public Iterator<DataType> getReverseIterator()
    {
        return mCoordinates.descendingIterator();
    }

    public void clear()
    {
        mCoordinates.clear();
    }
}
