package com.snobot2016.joystick;

import com.snobot.xlib.Logger;
import com.snobot2016.SmartDashBoardNames;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnobotDriveJoystickFactory implements IDriverJoystick
{
    private enum DriveMode
    {
        Xbox, Flightsticks, Arcade
    }
    
    private DriveMode mDriveMode;

    private IDriverJoystick mXboxJoystick;
    private IDriverJoystick mFlightstickJoytick;
    private IDriverJoystick mHaloJoytick;
    private Logger mLogger;
    private SendableChooser mDriveModeSelector;
    
    public SnobotDriveJoystickFactory(IDriverJoystick aXboxJoystick,IDriverJoystick aFlightstickJoytick, IDriverJoystick aHaloJoystick, Logger aLogger)
    {
        mXboxJoystick = aXboxJoystick;    
        mFlightstickJoytick = aFlightstickJoytick;
        mHaloJoytick = aHaloJoystick;
        mLogger = aLogger;
        
        mDriveMode = DriveMode.Xbox;
        
        mDriveModeSelector = new SendableChooser();
        mDriveModeSelector.addDefault("Xbox", DriveMode.Xbox);
        mDriveModeSelector.addObject("Fligthstick", DriveMode.Flightsticks);
        mDriveModeSelector.addObject("Arcade", DriveMode.Arcade);
        SmartDashboard.putData(SmartDashBoardNames.sDRIVER_JOSTICK_MODE, mDriveModeSelector);
        
    }

    @Override
    public void init()
    {
        
    }

    @Override
    public void update()
    {
        mDriveMode = (DriveMode) mDriveModeSelector.getSelected();
        
    }

    @Override
    public void control()
    {
        
    }

    @Override
    public void rereadPreferences()
    {

    }

    @Override
    public void updateSmartDashboard()
    {
     
    }

    @Override
    public void updateLog()
    {
        mLogger.updateLogger(mDriveMode.toString());
        
    }

    @Override
    public void stop()
    {

    }

    @Override
    public double getRightSpeed()
    {
        switch(mDriveMode)
        {
        case Xbox:
            return mXboxJoystick.getRightSpeed();
        case Flightsticks:
            return mFlightstickJoytick.getRightSpeed();
        case Arcade:
            return mHaloJoytick.getRightSpeed();
        }
        
        return 0;
    }

    @Override
    public double getLeftSpeed()
    {
        switch(mDriveMode)
        {
        case Xbox:
            return mXboxJoystick.getLeftSpeed();
        case Flightsticks:
            return mFlightstickJoytick.getLeftSpeed();
        case Arcade:
            return mHaloJoytick.getLeftSpeed();
        }
        
        return 0;
    }

    @Override
    public double getArcadePower()
    {
        switch(mDriveMode)
        {
        case Arcade:
            return mHaloJoytick.getArcadePower();
        default:
            return 0;
        }
        
    }

    @Override
    public double getArcadeTurn()
    {
        switch(mDriveMode)
        {
        case Arcade:
            return mHaloJoytick.getArcadeTurn();
        default:
            return 0;
        }
    }

    @Override
    public boolean isArcadeMode()
    {
        return mDriveMode == DriveMode.Arcade;
    }

}
