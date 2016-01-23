package com.snobot2016.camera;


import com.snobot2016.SmartDashBoardNames;

import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.*;

/**
 *Main implementation for the Axis Camera
 *
 *@author Calvin Do
 *@author Adam Donle
 */

public class Camera implements ICamera
{
   private AxisCamera mCamera;
   private HSLImage mNewImage;
   private boolean mUpdateImageSuccess;
   
   public Camera(AxisCamera aCamera)
   {
       mCamera = aCamera;
//       mNewImage = aNewImage;, HSLImage aNewImage, boolean aUpdateImageSuccess
//       mUpdateImageSuccess = aUpdateImageSuccess;
   }

@Override
public double getYaw()
{
    // TODO Auto-generated method stub
    return 0;
}

@Override
public double getDistanceToTarget()
{
    // TODO Auto-generated method stub
    return 0;
}


public HSLImage getImage()
{
    // TODO Auto-generated method stub
    mNewImage = null;
    try
    {
        mNewImage = mCamera.getImage();
    }
    catch (NIVisionException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   
    return mNewImage;
}

@Override
public boolean getUpdateImage()
{
    // TODO Auto-generated method stub
    mUpdateImageSuccess = mCamera.getImage(mNewImage);
    SmartDashboard.putBoolean(SmartDashBoardNames.sUpdateImageSuccess, mUpdateImageSuccess);
    return mUpdateImageSuccess;
}

}
