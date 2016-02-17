package com.snobot.sd2016.coordinategui.widget;

import com.snobot.sd2016.coordinategui.FieldProperties;

/**
 *
 * @author PJ
 */
public class CoordinateWidget extends BaseCoordinateWidget
{
    public static final String NAME = "2016 Field Widget";
	
	public static class FieldProperties2014 extends FieldProperties
	{
	    public FieldProperties2014()
	    {
            super("2016Field.png", 27.0, 54.0);
	    }
	}

    /**
     * Constructor.  Sets up the properties and the field drawer
     */
    public CoordinateWidget()
    {
        super(false, new FieldProperties2014());
    }
}
