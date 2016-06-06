package com.snobot.coordinate_gui.model;


public class PixelConverter
{
    protected double mXCenterFeet;
    protected double mYCenterFeet;
    protected double mScaleFactor;

    protected int mXCenterPixels;

    public PixelConverter(double aFieldCenterX, double aFieldCenterY)
    {
        mXCenterFeet = aFieldCenterX;
        mYCenterFeet = aFieldCenterY;
    }

    public int convertPixels(double aFeet)
    {
        return (int) (aFeet * mScaleFactor);
    }

    public int convertXPoint(double aFeet)
    {
        return mXCenterPixels - convertPixels(mXCenterFeet - aFeet);
    }

    public int convertYPoint(double aFeet)
    {
        return convertPixels(mYCenterFeet - aFeet);
    }

    public void updateScaleFactor(int widthPixels, int heightPixels, double widthFeet, double heightFeet)
    {
        double horizontalScaleFactor = (widthPixels / widthFeet);
        double verticalScaleFactor = (heightPixels / heightFeet);

        double minScaleFactor = Math.min(horizontalScaleFactor, verticalScaleFactor);

        // System.out.println("XX " + getWidth() + ", " + getHeight());
        // System.out.println("YY " + preferredSize.width + ", " +
        // preferredSize.height);
        // System.out.println(minScaleFactor);

        mScaleFactor = minScaleFactor;
        mXCenterPixels = (int) (widthFeet * mScaleFactor);
    }
}
